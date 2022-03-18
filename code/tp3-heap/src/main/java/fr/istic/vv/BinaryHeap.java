package fr.istic.vv;

import java.util.Comparator;

class BinaryHeap<T, E extends Throwable> {

    T value;
    BinaryHeap<T, E> leftChild;
    BinaryHeap<T, E> rightChild;

    Comparator<T> comparator;
    Throwable exception;

    public BinaryHeap(Comparator<T> comparator, Throwable E) {
        this.exception = E;
        this.comparator = comparator;
    }

    public T pop() {
        // TODO
        return value;
    }

    public T peek() throws Throwable {
        // TODO
        if (value==null) throw exception;
        return value;
    }


    public void push(T element)  {
        if (value==null) this.value = element;
        switch (comparator.compare(value,element)){
            case 0 : this.value = element; break;
            case -1:
                if (this.leftChild==null) this.leftChild = new BinaryHeap<T, E>(comparator,exception);
                this.leftChild.push(element);
                break;
            case 1 :
                if (this.rightChild==null) this.rightChild = new BinaryHeap<T, E>(comparator,exception);
                this.rightChild.push(element);
                break;
        }
    }

    public int count(){
        int result = 0;
        if(value != null) result++;
        if(leftChild != null) result+= leftChild.count();
        if(rightChild != null) result+= rightChild.count();
        return result;
    }

}