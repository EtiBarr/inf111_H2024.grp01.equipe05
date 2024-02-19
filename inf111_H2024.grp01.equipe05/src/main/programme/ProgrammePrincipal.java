package main.programme;

import main.modele.centreControle.CentreControle;
import main.modele.communication.Message;
import main.modele.rover.Rover;
import main.modele.satelliteRelai.SatelliteRelai;


public class ProgrammePrincipal {


	/**
	 * Programme principale, instancie les éléments de la simulation,
	 * les lie entre eux, puis lance la séquence de test.
     */


	public static void main(String[] args){



		SatelliteRelai satellite = new SatelliteRelai();	//instance of satelite
		satellite.start();

		CentreControle centreControle = new CentreControle(satellite);	//instance of controle center
		Rover rover = new Rover(satellite);	//instance of rover

		satellite.lierCentreOp(centreControle); //linking
		satellite.lierRover(rover); //linking



	}

}
