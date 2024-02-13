package utilitaires;


import modele.communication.Message;

import java.util.Arrays;

/**
 * linked queue
 * structure "first in - first out. Les valeurs rentre par l'arriere et sorte de l'avant
 */

public class FileChainee {

    /**
     * des Node, c'est les block qui contienent l'information dans ma file
     * implemented pour faciliter l'implementation de ma file
     */
    private class Node{
        Message message;
        Node suivant;

            public Node(Message msg){
                this.message = msg;
                this.suivant = null;
            }
    }

    private Node tete;
    private Node fin;

    /**
     * constructeur
     */
    public FileChainee(){

        this.tete = null;
        this.fin = null;
    }

    /**
     * aussi connue come un enQueue
     * rajoute un element a la tete de la file
     * @param msg le message qu'on desire rajouter a la liste
     */
    public void ajouterElement(Message msg){

        Node nouvelleNode = new Node(msg);

        if(estVide()){
            tete = nouvelleNode;
        }else{
            fin.suivant = nouvelleNode; //rajoute nouvelle element a l'arriere
        }
        fin = nouvelleNode;
    }

    /**
     * aussi connue comme un deQueue
     * enleve le premier element de la file
     */
    public void enleverElement(){

        if(estVide()){
            System.out.println("La file est vide"); //erreur vue que la file est vide

        }else{
            tete = tete.suivant;

            //si la tete est null, la fin et la tete sont la meme chose
            if(tete == null){
                fin = null;
            }
        }


    }

    /**
     * la methodes pop n'est pas demander, je l'ai emplementer seulement pour pouvoir tester la file
     * meme chose que enleverElement mais retourne la valeur enlever
     * @return l'element enlever
     */

    public Message pop(){

        Message pop;

        if(estVide()){
            System.out.println("La file est vide"); //erreur vue que la file est vide
            pop = null;
        }else{
             pop = tete.message;

            tete = tete.suivant;

            //si la tete est null, la fin et la tete sont la meme chose
            if(tete == null){
                fin = null;
            }
        }

        return pop;
    }

    /**
     * si la tete == null, la file est vide et donc ont va retourner true
     * @return true or false
     */
    public boolean estVide(){
        return this.tete == null;
    }

    /**
     * pas demander, juste pour valider les output
     * transforme la file en array pour pouvoir toute printe d'un coup. aurrait aussi pue depiler et rempiler la file
     * @return string output
     */
    public String toString(){

        String output;

        if(!estVide()) {

            //taking the elements out of the que to print them all
            Message[] array = new Message[15]; //if more then 100 in the queue, the test will break
            int i = 0;
            while(i < array.length){
                array[i] = this.pop();
                i++;
            }
            output = "The object holds: " + Arrays.toString(array);

            //putting the elemnts back in the queue to keep the queue intact after printing
            for (Message k : array) {
                this.ajouterElement(k);
            }
        } else {
            output = "La file est vide";
        }
        return output;
    }

}
