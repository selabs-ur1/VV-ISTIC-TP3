package fr.istic.vv;

public class Node<T extends Comparable<T>> {

    public T value = null;

    public Node(T value) {
        this.value = value;
    }
}
