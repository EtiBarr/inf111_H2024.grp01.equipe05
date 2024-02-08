package utilitaires;

public class Vect2D {

    private double longueurX;
    private double longueurY;

    public Vect2D(){ //constructeur par defaut

        this.longueurX = 5; //valeur sans significance
        this.longueurY = 7;
    }

    public Vect2D(double x, double y){ //constructeur par parametres

        this.longueurX = x;
        this.longueurY = y;
    }

    public Vect2D(Vect2D vector){ //constructeur par copie

        this.longueurX = vector.longueurX;
        this.longueurY = vector.longueurY;
    }

    public double getLongueurX(){ // accesseur informateur pour longueur x
        return longueurX;
    }

    public double getLongueurY(){ // accesseur informateur pour longueur y
        return longueurY;
    }

    public double getLongueur(){ //get Longueur qui retourne la longuer

        return Math.sqrt(Math.pow(longueurX, 2) + Math.pow(longueurY, 2));
    }

    public double getAngle(){ // calcule l'angle du vector

        return Math.atan2(longueurY, longueurX);
    }

    public Vect2D calculerDiff(Vect2D posFin){ //method qui calcule la difference entre la position initiale et finale

        double diffX = posFin.longueurX - this.longueurX;
        double diffY = posFin.longueurY - this.longueurY;

        return new Vect2D(diffX, diffY);
    }

    public void diviser(double a){ //method qui divise le vecteur par la valeur fournie

        this.longueurX /= a;
        this.longueurY /= a;
    }

    public void ajouter(double x, double y){ //method qui rajoute les valeurs fournie au vecteur

        this.longueurX += x;
        this.longueurY += y;
    }

    @Override
    public String toString() { // method qui transforme l'address de l'objet en string

        return String.format("Les coordonee du vecteur sont: [X =" + this.getLongueurX() + ", Y=" + this.getLongueurY() + "]");
    }

    public boolean equals(Vect2D vecteur){ //method qui compare les coordonee de deux vector

        return this.getLongueurY() == vecteur.getLongueurY() && this.getLongueurX() == vecteur.getLongueurX();
    }

    public static void main(String[] args){

/* Test de la class
        Vect2D vector = new Vect2D(8, 5);


        Vect2D posIni = new Vect2D(7, 5);
        Vect2D posFin = new Vect2D(3, 2);

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
*/
    }
}



