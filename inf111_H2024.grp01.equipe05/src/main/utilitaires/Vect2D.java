package main.utilitaires;

/**
 * classe utilitaire qui implémente un vecteur à deux
 * dimensions.
  */

public class Vect2D {

    private double longueurX;
    private double longueurY;



    /**
     * constructeur par default
     */
    public Vect2D(){

        this.longueurX = 12; //valeur sans significance ************** will need to modify when the default value is known
        this.longueurY = 25;
    }

    /**
     * constructeur par parametres
     * @param x la longueur designer x
     * @param y la longueur designer y
     */

    public Vect2D(double x, double y){

        this.longueurX = x;
        this.longueurY = y;
    }

    /**
     *  constructeur par copie
     * @param vector un vecteur
     */
    public Vect2D(Vect2D vector){

        this.longueurX = vector.longueurX;
        this.longueurY = vector.longueurY;
    }

    /**
     * accesseur informateur pour Longeur X
     * @return longueurX
     */

    public double getLongueurX(){
        return longueurX;
    }

    /**
     * accesseur informateur pour Longeur y
     * @return longeurY
     */

    public double getLongueurY(){
        return longueurY;
    }

    /**
     * accesseur informateur pour getLongeur
     * @return longueur
     */

    public double getLongueur(){

        return Math.sqrt(Math.pow(longueurX, 2) + Math.pow(longueurY, 2));
    }

    /**
     * accesseur informateur pour getAngle
     * @return angle
     */
    public double getAngle(){

        if (this.longueurX == 0){
            throw new ArithmeticException("Division par zero");
        }
        return Math.atan2(longueurY, longueurX); //atan2 divide val1 par val2 automatiquement
    }

    /**
     * method qui calcule la difference entre la position initiale et finale
     * @param posFin variable que represente ou le vecteur fini
     * @return differenceVect
     */

    public Vect2D calculerDiff(Vect2D posFin){

        double diffX = posFin.longueurX - this.longueurX;
        double diffY = posFin.longueurY - this.longueurY;

        return new Vect2D(diffX, diffY);
    }

    /**
     * method qui divise le vecteur par la valeur fournie
     * @param a la valeur qu'on veux diviser du vecteur
     */

    public void diviser(double a){

        if (a == 0){
            throw new ArithmeticException("Division par zero");
        }

        this.longueurX /= a;
        this.longueurY /= a;
    }

    /**
     * method qui rajoute les valeurs fournie au vecteur
     * @param x la longueur qui doit etre rajouter a la coordoner x
     * @param y la longueur qui doit etre rajouter a la coordonner y
     */

    public void ajouter(double x, double y){

        this.longueurX += x;
        this.longueurY += y;
    }

    /**
     *  method qui transforme l'address de l'objet en string
     * @return string
     */

    @Override
    public String toString() {

        return String.format("Les coordonee du vecteur sont: [X =" + this.getLongueurX() + ", Y=" + this.getLongueurY() + "]");
    }

    /**
     * method qui compare l'egalite les coordonee de deux vector
     * @param vecteur le vecteur donc nous voulons valider
     * @return boolean
     */

    public boolean equals(Vect2D vecteur){

        if(vecteur == null){
            throw new NullPointerException("The vector cannot be null");
        }

        return this.getLongueurY() == vecteur.getLongueurY() && this.getLongueurX() == vecteur.getLongueurX();
    }

}



