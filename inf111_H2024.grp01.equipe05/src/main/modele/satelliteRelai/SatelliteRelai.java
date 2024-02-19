package main.modele.satelliteRelai;

/*
  Classe simulant le satellite relai

  Le satellite ne se contente que de transferer les messages du Rover vers le centre de contrôle
  et vice-versa.

  Le satellite exécute des cycles à intervale de TEMPS_CYCLE_MS. Période à
  laquelle tous les messages en attente sont transmis. Ceci est implémenté à
  l'aide d'une tâche (Thread).

  Le relai satellite simule également les interférence dans l'envoi des messages.

  Services offerts:
   - lierCentrOp
   - lierRover
   - envoyerMessageVersCentrOp
   - envoyerMessageVersRover

  @author Frederic Simard, ETS
 * @version Hiver, 2024
 */

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import main.modele.centreControle.CentreControle;
import main.modele.communication.Message;
import main.modele.rover.Rover;
import main.utilitaires.FileChainee;

public class SatelliteRelai extends Thread{

	public FileChainee messageCentreOp = new FileChainee();
	public FileChainee messageRover = new FileChainee();


	static final int TEMPS_CYCLE_MS = 500;
	static final double PROBABILITE_PERTE_MESSAGE = 0.15;
	
	ReentrantLock lock = new ReentrantLock();
	
	private Random rand = new Random();


	public CentreControle centreControle;
	public Rover rover;

	public  void lierCentreOp(CentreControle centreControle){ //not sure if it should be the Message object or not

		this.centreControle = centreControle; //link to centreControle

	}

	public void lierRover(Rover rover){

		this.rover = rover; //link to rover

	}


	/**
	 * Méthode permettant d'envoyer un message vers le centre d'opération
	 * @param msg, message à envoyer
	 */
	public void envoyerMessageVersCentrOp(Message msg) {

		lock.lock();

		
		try {

			if(rand.nextDouble() > PROBABILITE_PERTE_MESSAGE){

				messageCentreOp.ajouterElement(msg);
				System.out.println("Relai centreOp works great");

			}
			
		}finally {
			lock.unlock();
		}
	}
	
	/**
	 * Méthode permettant d'envoyer un message vers le rover
	 * @param msg, message à envoyer
	 */
	public void envoyerMessageVersRover(Message msg) {


		lock.lock();
		
		try {

			if(rand.nextDouble() > PROBABILITE_PERTE_MESSAGE){

				messageRover.ajouterElement(msg);
				System.out.println("Relai rover works great");

			}
			
		}finally {
			lock.unlock();
		}
	}

	@Override
	public void run() {

		System.out.println("Relai works great");

		while(true) {

			while (!messageCentreOp.estVide()) {
				if (centreControle != null) {
					centreControle.receptionMessageDeSatellite(messageCentreOp.pop());
				}
			}
				while (!messageRover.estVide()) {
					if (rover != null) {
						rover.receptionMessageDeSatellite(messageRover.pop());
					}
				}


			// attend le prochain cycle
			try {
				Thread.sleep(TEMPS_CYCLE_MS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	

}
