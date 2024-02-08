import modele.communication.Message;
import modele.satelliteRelai.SatelliteRelai;
import utilitaires.FileChainee;
import utilitaires.Vect2D;

public class ProgrammePrincipal {


	/**
	 * Programme principale, instancie les éléments de la simulation,
	 * les lie entre eux, puis lance la séquence de test.
	 * @param args, pas utilisé
	 */

	public static void testVect2d(){	//works like a charm

		Vect2D vector = new Vect2D(8, 5);


		Vect2D posIni = new Vect2D(7, 5);
		Vect2D posFin = new Vect2D(7, 5);

		double longueur = vector.getLongueur();
		double angle = vector.getAngle();

		Vect2D posDiff = posIni.calculerDiff(posFin);

		System.out.println("longueur est egale a:" + longueur);
		System.out.println("l'angle est egale a:" + angle);
		System.out.println(posDiff);

		System.out.println(vector);
		double a = 2;

		vector.ajouter(3, 4);

		System.out.println(vector);

		System.out.println(posIni);
		System.out.println(posFin);
		boolean answer = posIni.equals(posFin);
		System.out.println(answer);
	}

	public static void testFileChaine(){ //works like a charm

			FileChainee file = new FileChainee();

		Message test = new Message(3) {
			@Override
			public long getTempsEnvoi() {
				return super.getTempsEnvoi();
			}
		};

			file.ajouterElement(test);
			file.ajouterElement(test);
			file.ajouterElement(test);
			file.ajouterElement(test);
			file.ajouterElement(test);
			file.ajouterElement(test);
			file.ajouterElement(test);

			file.enleverElement();
			file.enleverElement();
			file.enleverElement();

			System.out.println(file);


	}

	public static void testEnvoyerMessage(){

		SatelliteRelai satellite = new SatelliteRelai();
		Message test = new Message(1) {
			@Override
			public long getTempsEnvoi() {
				return super.getTempsEnvoi();
			}
		};
		Message test2 = new Message(2) {
			@Override
			public long getTempsEnvoi() {
				return super.getTempsEnvoi();
			}
		};


		satellite.envoyerMessageVersRover(test);
		satellite.envoyerMessageVersCentrOp(test);
		satellite.envoyerMessageVersRover(test2);
		satellite.envoyerMessageVersCentrOp(test2);

	}

	/* Post part one notes
	Currently, FileChinee has a lot of code that is just there for testing, so the results are different due to that,
	especially with  the toString array
	There are many different variations of FileChainee, i have to find out what the prof wants to know what the final
	design of the linked queue will be
	SatelliteRelai has extra print's to be able to see what is happening
	Everything seems to be working fine atm
	 */


	public static void main(String[] args){


		SatelliteRelai satellite = new SatelliteRelai();
		satellite.start();
		testVect2d();
		//testFileChaine();
		testEnvoyerMessage();
	}

}
