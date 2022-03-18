package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Classe qui représente un tas minimié
 */
class BinaryHeap<T> {

    private final List<T> heapAsArray;
    private final Comparator<T> comparator;

    //constructeur
    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heapAsArray = new ArrayList<>();
    }

    public List<T> getHeapAsArray() { return this.heapAsArray; }

    /**
     * Enlève la valeur la plus petite du tas
     * @return la valeur retirée
     */
    public T pop() {
        if(!heapAsArray.isEmpty()){
            T smallerNode = heapAsArray.get(0);

            heapAsArray.set(0, null);
            triTas();

            return smallerNode;
        }
        //throw NoSuchElementException;
        return null;
    }

    /**
     * Renvoie la valeur la plus petite du tas
     * @return la plus petite valeur
     */
    public T peek() {
        return !heapAsArray.isEmpty() ? heapAsArray.get(0) : null;
    }

    /**
     * Ajoute un élément dans le tas
     * @param element l'élément à ajouter
     */
    public void push(T element) {
        if (element != null){
            int placeLibre = heapAsArray.indexOf(null);
            if (placeLibre != -1){
                heapAsArray.set(placeLibre,element);
                triDernierNoeudInsere(placeLibre);
            } else {
                heapAsArray.add(element);
                triDernierNoeudInsere(heapAsArray.size()-1);
            }
        }
    }

    /**
     * Compte le nombre de noeuds du tas
     * @return le nombre de noeuds non null
     */
    public int count() {
        int numberNotNull = 0;
        for (T item : heapAsArray) {
            if (item != null)
                numberNotNull++;
        }
        return numberNotNull;
    }

    private void triTas(){
        if (!heapAsArray.isEmpty()){

            int i = 0;
            while (!estFeuille(i)){
                int j = plusPetitFils(i);
                swap(i,j);
                i = j;
            }
            retireNull();
        }
    }

    private void retireNull(){
        if (!heapAsArray.isEmpty() && heapAsArray.get(heapAsArray.size()-1) == null){
            heapAsArray.remove(heapAsArray.size()-1);
        }
    }

    private void swap(int x, int y) {
        T aux = heapAsArray.get(x);
        heapAsArray.set(x, heapAsArray.get(y));
        heapAsArray.set(y, aux);
    }

    private boolean estFeuille(int node){
        return !(aFilsGauche(node) || aFilsDroit(node));
    }

    private boolean aFilsGauche(int i){
        return getFilsGauche(i) != -1;
    }

    private boolean aFilsDroit(int i){
        return getfilsDroit(i) != -1;
    }

    private int getFilsGauche(int i){
        int filsGauche = 2*i + 1;
        return (filsGauche < heapAsArray.size() && heapAsArray.get(filsGauche) != null) ? filsGauche : -1;
    }

    private int getfilsDroit(int i){
        int filsDroit = 2*i + 2;
        return (filsDroit < heapAsArray.size() && heapAsArray.get(filsDroit) != null) ? filsDroit : -1;
    }

    private int getPere(int i){
        return i==0 ? 0 : (i - 1) / 2;
    }

    private int plusPetitFils(int i) {
        if (!estFeuille(i)) {
            if (aFilsGauche(i) && aFilsDroit(i)) {
                if (comparator.compare(heapAsArray.get(getFilsGauche(i)), heapAsArray.get(getfilsDroit(i))) < 0) return getFilsGauche(i);
                else
                    return getfilsDroit(i);
            } else if (aFilsGauche(i))
                return getFilsGauche(i);
            else if (aFilsDroit(i))
                return getfilsDroit(i);
        }
        return -1; //cas du noeud racine
    }

    private void triDernierNoeudInsere(int i){
        if (comparator.compare(heapAsArray.get(i),heapAsArray.get(getPere(i))) < 0){
            while (i > 0){
                if (comparator.compare(heapAsArray.get(i),heapAsArray.get(getPere(i))) < 0){
                    swap(i, getPere(i));
                }
                i = getPere(i);
            }
        }
    }

}