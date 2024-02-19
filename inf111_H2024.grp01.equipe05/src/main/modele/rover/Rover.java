package main.modele.rover;

import main.modele.communication.Message;
import main.modele.communication.TransporteurMessage;
import main.modele.satelliteRelai.SatelliteRelai;

public class Rover extends TransporteurMessage {

     private SatelliteRelai satelliteRelai;

     public Rover(SatelliteRelai satelliteRelai){

        super();
        this.satelliteRelai = satelliteRelai;
     }
     protected void envoyerMessage(Message msg){

         if (satelliteRelai != null) {
             satelliteRelai.envoyerMessageVersCentrOp(msg);
             satelliteRelai.messageCentreOp.ajouterElement(msg); // Add the message to the sent messages queue in SatelliteRelai
         }

         receptionMessageDeSatellite(msg); // not sure that this is the right method to call

     }

     protected void gestionnaireMessage(Message msg){

         System.out.println("Nom de la classe" + msg.getClass() + "Le numero du message recu:" + msg.getCompte());

     }
}
