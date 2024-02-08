package utilitaires;

//pas sure qu'elle genre de file chainee que le prof veut, donc j'ai fait trois version
//celle pas en commentaire et la deuxiemme sont celle qui semble etre plus enligner avec ce que le prof veut tandis que
//la derniere avec le node semble etre un mocel plus populaire, avec les nodes


//for now enleverElement literally just removes the value and doesn't return it so we can't do anything with it,
//which feels wrong which is why i did it differently in the second version, but for now it's like this since that's
//what the prof seems to ask for. we'll see what is needed later on

import modele.communication.Message;

import java.io.File;
import java.util.Arrays;


//there is two of the same, one with ints the other one with Messages
//linked queue
public class FileChainee { //structure "first in - first out. Les valeurs rentre par l'arriere et sorte de l'avant

    private Message data;
    private FileChainee next;

    public FileChainee(){   //constructeur

        this.data = null;
        this.next = null;
    }

    public void ajouterElement(Message valeur){ //aussi connue come un enQueue

        FileChainee nouvelleValeur = new FileChainee();
        nouvelleValeur.data = valeur;

        if(estVide()){
            this.data = valeur;
        }else{
            FileChainee current = this;
            while(current.next != null){
                current = current.next;
            }
            current.next = nouvelleValeur;
        }
    }

    public void enleverElement(){ //aussi connue comme un deQueue

        if(estVide()){
            System.out.println("La file est vide"); //erreur vue que la file est vide
            return;
        }

        if(this.next != null){
            this.data = this.next.data;
            this.next = this.next.next;
        } else{
            this.data = null; //si next est null, ca veut dire qu'on est entrain de prendre la derniere valeur
        }
    }

    public Message pop(){ //meme chose que enleverElement mais retourne la valeur enlever

        if(estVide()){
            System.out.println("La file est vide");
            return null;
        }

        Message pop = this.data;

        if(this.next != null){
            this.data = this.next.data;
            this.next = this.next.next;
        } else{
            this.data = null; //si next est null, ca veut dire qu'on est entrain de prendre la derniere valeur
        }

        return pop;
    }

    public boolean estVide(){ //si data == -1, la file est vide et donc ont va retourner true
        return this.data == null;
    }

    public String toString(){ //pas demander, juste pour tester ****************

        String output;

        if(!estVide()) {

            //taking the elements out of the que to print them all
            Message[] array = new Message[100]; //if more then 100 in the queue, the test will break
            int i = 0;
            while(data != null){
                array[i] = this.pop();
                i++;
            }
            output = "The object holds: " + Arrays.toString(array);

            //putting the elemnts back in the queue to keep the queue intact after printing
            for (Message k : array) {
                this.ajouterElement(k);
            }
        } else {
            output = "La pile est vide";
        }
        return output;
    }


    public static void main(String[] args){

        FileChainee file = new FileChainee();

        Message test = new Message(3) {
            @Override
            public long getTempsEnvoi() {
                return super.getTempsEnvoi();
            }
        };

        file.ajouterElement(test);
        file.enleverElement();
        System.out.println(file);

    }
}


/*
public class FileChainee { //structure "first in - first out. Les valeurs rentre par l'arriere et sorte de l'avant

    private int data;
    private FileChainee next;

    public FileChainee(){   //constructeur

    this.data = -1;
    this.next = null;
    }

    public void ajouterElement(int valeur){ //aussi connue come un enQueue

        FileChainee nouvelleValeur = new FileChainee();
        nouvelleValeur.data = valeur;

        if(estVide()){
            this.data = valeur;
        }else{
            FileChainee current = this;
            while(current.next != null){
                current = current.next;
            }
            current.next = nouvelleValeur;
        }
    }

    public void enleverElement(){ //aussi connue comme un deQueue

        if(estVide()){
            System.out.println("La file est vide"); //erreur vue que la file est vide
            return;
        }

        if(this.next != null){
            this.data = this.next.data;
            this.next = this.next.next;
        } else{
            this.data = -1; //si next est null, ca veut dire qu'on est entrain de prendre la derniere valeur
        }
    }

    public int pop(){ //meme chose que enleverElement mais retourne la valeur enlever

        if(estVide()){
            System.out.println("La file est vide");
            return -1;
        }

        int pop = this.data;

        if(this.next != null){
            this.data = this.next.data;
            this.next = this.next.next;
        } else{
            this.data = -1; //si next est null, ca veut dire qu'on est entrain de prendre la derniere valeur
        }

        return pop;
    }

    public boolean estVide(){ //si data == -1, la file est vide et donc ont va retourner true
        return this.data == -1;
    }

    public String toString(){ //pas demander, juste pour tester ****************

        String output;

        if(!estVide()) {

            //taking the elements out of the que to print them all
            int[] array = new int[100]; //if more then 100 in the queue, the test will break
            int i = 0;
            while(data != -1){
                array[i] = this.pop();
                i++;
            }
             output = "The object holds: " + Arrays.toString(array);

            //putting the elemnts back in the queue to keep the queue intact after printing
            for (int k : array) {
                this.ajouterElement(k);
            }
            } else {
            output = "La pile est vide";
        }
        return output;
    }


    public static void main(String[] args){

        FileChainee file = new FileChainee();

        file.ajouterElement(3);
        file.ajouterElement(4);
        file.enleverElement();
        System.out.println(file);

    }
}
*/




    /*
    public class FileChainee { //structure "firs in - first out. Les valeurs rentre par l'arriere et sorte de l'avant

    int avant;
    int arriere;
    int[] array;
    //FileSimplementChainee next;


    public FileChainee() { //constructueur pour cree une nouvelle fille

        this.avant = -1;    //initialize a -1, pour savoir quand il est vide
        this.arriere = -1;
        this.array = new int[100]; //initialize l'array avex 100 vue que je ne sais pas la grosseure desire encore
    }

    public boolean estVide(){
        return avant == -1; //vue qu'il est initialiser a -1, ont peux simplement verifier comme ca
    }

    public void ajouterElement(int valeur){ //aussi connue come un enQueue

        if(estVide()){
            avant = 0;
            arriere = 0;
        } else{
            arriere++;
        }
        array[arriere] = valeur;

    }

    public int enleverElement(){ //aussi connue come un deQueue

        if(estVide()){
            return -1; //doit verifier que la valeur retourner n'est pas -1 quand ont l'utilise
        }
        if(avant == arriere){ //si l'avant et l'arriere est egale, c'est forcement la derniere valeurs, et donc il va etre vide apres
            avant = -1;
            arriere = -1;
        }
        int pop = array[avant];
        avant++;
        return pop;
    }
}

*/


//le node, ou package, qui contient le data qui va etre contenue dans la liste chainee. Ont en a pas vue que ce n'est pas
//demander dans l'enoncer, mais ont pourrait le rajouter
/* version avec node

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class FileChainee {
    private Node avant;
    private Node arriere;

    public FileChainee() {
        this.avant = this.arriere = null;
    }

    public void ajouterElement(int data) {
        Node newNode = new Node(data);

        if (estVide()) {
            avant = arriere = newNode;
        } else {
            arriere.next = newNode;
            arriere = newNode;
        }
    }

    public void enleverElement() {
        if (estVide()) {
            System.out.println("La file est vide");
            return;
        }

        avant = avant.next;

        if (avant == null) {
            arriere = null;
        }
    }

    public boolean estVide() {
        return avant == null;
    }


}
*/