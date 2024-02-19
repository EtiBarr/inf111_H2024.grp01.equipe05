package main.modele.communication;
/*
  Classe qui implémente le protocol de communication entre le Rover
  et le Centre d'opération.

  Il se base sur une interprétation libre du concept de Nack:
  	https://webrtcglossary.com/nack/

  Les messages envoyés sont mémorisé. À l'aide du compte unique
  le transporteur de message peut identifier les Messages manquant
  dans la séquence et demander le renvoi d'un Message à l'aide du Nack.

  Pour contourner la situation ou le Nack lui-même est perdu, le Nack
  est renvoyé periodiquement, tant que le Message manquant n'a pas été reçu.

  C'est également cette classe qui gère les comptes unique.

  Les messages reçu sont mis en file pour être traité.

  La gestion des messages reçu s'effectue comme une tâche s'exécutant indépendamment (Thread)

  Quelques détails:
   - Le traitement du Nack a priorité sur tout autre message.
   - Un message NoOp est envoyé périodiquement pour s'assurer de maintenir
     une communication active et identifier les messages manquants en bout de séquence.

  Services offerts:
   - TransporteurMessage
   - receptionMessageDeSatellite
   - run

  @author Frederic Simard, ETS
 * @version Hiver, 2024
 */

import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;


//questions to ask the prof
//do we need to use out own linked queue or do we need to implement a ready made list (the guide says that we have to implement two)
//where do we actually make the list of messages, sent and tosend
//is it an issue if i name a little bit in english
public abstract class TransporteurMessage extends Thread {

	LinkedList<Message> listMessageRecu = new LinkedList<>();
	LinkedList<Message> listMessageEnvoyer = new LinkedList<>();

	// compteur de message
	protected CompteurMessage compteurMsg;
	// lock qui protège la liste de messages reçu
	private ReentrantLock lock = new ReentrantLock();
	
	/**
	 * Constructeur, initialise le compteur de messages unique
	 */
	public TransporteurMessage() {
		compteurMsg = new CompteurMessage();		
	}
	
	/**
	 * Méthode gérant les messages reçu du satellite. La gestion se limite
	 * à l'implémentation du Nack, les messages spécialisé sont envoyés
	 * aux classes dérivés
	 * @param msg, message reçu
	 */

	public void receptionMessageDeSatellite(Message msg) {
		lock.lock();

		try {

			//i'm not sure if the way i am checking the position is the best ****************might have to change the way the position is allocated
			//maybe i am dropping the value one instance too far in the array

			int nbNack = 0;
				if(msg instanceof Nack){
					//addFirst will add the message at the start of the list, ass desired
					listMessageRecu.addFirst(msg);
					nbNack++;
				}else{

					// this is to account for the position of the message according to the amount of nacks that were placed in the array
					int position = nbNack + msg.getCompte();
					//add the new message at the desired position
					listMessageRecu.add(position, msg);
				}
		}finally {
			lock.unlock();
		}
	}


	@Override
	/*
	 * Tâche effectuant la gestion des messages reçu
	 */

	public void run() {

		System.out.println("Transporteur works great");

		int compteCourant = 0;
		
		while(true) {
			System.out.println("Transporteur works great");

			lock.lock();
			
			try {

				System.out.println("Transporteur works great");

				boolean nackSent = false;

				while(!listMessageRecu.isEmpty() && !nackSent){ //Tant qu’il y a des messages et qu’aucun Nack n’a été envoyé

						Message nextMessage = listMessageRecu.getFirst(); //Obtient le prochain message à gérer (début de la liste)

						if(nextMessage instanceof Nack){ //S’il s’agit d’un Nack

							int nextMessageCompte = nextMessage.getCompte(); //obtient le compte du message manquant

							while(!listMessageEnvoyer.isEmpty() //cherche ce message dans la file des messages envoyés en enlevant tous les messages
									&& listMessageEnvoyer.getFirst().getCompte() < nextMessageCompte //au compte inférieur au passage
									|| listMessageEnvoyer.getFirst() instanceof Nack){ //ou estInstance de Nack.

								listMessageEnvoyer.poll(); //not sure if i should use removeFirst instead *****
							}

							Message messageAEnvoyer = listMessageEnvoyer.peek(); //peek le message à envoyer (obtient sans enlever)

							envoyerMessage(messageAEnvoyer); //envoi le message à répéter

							//nextMessage est le message Nack dans cetter instance
							listMessageRecu.remove(nextMessage); //Enlever le message Nack de la liste des reçus.

						}else if(nextMessage.getCompte() != compteCourant){ //Sinon s’il y a un message manquant  (comparer le compteCourant)

							Message messageNack = new Nack(compteCourant);
							envoyerMessage(messageNack); // envoi un Nack avec la valeur du message manquant (compteCourant)

							nackSent = true; //marque qu’un Nack a été envoyé (pour quitter la boucle)

						}else if(nextMessage.getCompte() < compteCourant){ //Sinon, si le compte du message est inférieur à compteCourant

							listMessageRecu.poll(); //rejete le message, car il s’agit d’un duplicat
						}else{ // Sinon,
							gestionnaireMessage(nextMessage); //fait suivre le message au gestionnaireMessage
							listMessageRecu.remove(nextMessage); //défile le message
							compteCourant++; //incrémente le compteCourant
						}
					}

				int compteUnique = compteurMsg.getCompteActuel(); //Obtient un nouveau compte unique (CompteurMsg)

				Message noOpMessage = new NoOp(compteUnique);
				envoyerMessage(noOpMessage); //Envoi un message NoOp

			}finally{
				lock.unlock();
			}
			
			// pause, cycle de traitement de messages
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * méthode abstraite utilisé pour envoyer un message
	 * @param msg, le message à envoyer
	 */
	abstract protected void envoyerMessage(Message msg);

	/**
	 * méthode abstraite utilisé pour effectuer le traitement d'un message
	 * @param msg, le message à traiter
	 */
	abstract protected void gestionnaireMessage(Message msg);

	

}
