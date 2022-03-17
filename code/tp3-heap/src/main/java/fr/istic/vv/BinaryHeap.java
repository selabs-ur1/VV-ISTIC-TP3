package fr.istic.vv;


import java.lang.management.GarbageCollectorMXBean;
import java.util.Comparator;
import java.util.Objects;

class BinaryHeap<T> {

    private T value;
    private Comparator<T> comparator;
    private BinaryHeap<T> left;
    private BinaryHeap<T> right;

    public BinaryHeap<T> getLeft() {
        return left;
    }

    public BinaryHeap<T> getRight() {
        return right;
    }

    public BinaryHeap(Comparator<T> comparator) {
        Objects.requireNonNull(comparator);
        this.comparator=comparator;
    }

    public T pop() {
        T v = value;
        if(right!=null) {
            value = right.pop();
        }
        else{
            if(left!=null){
                value = left.pop();
            }
            else{
                value = null;//pas d'enfants
            }
        }
        return v;
    }

    public T peek() {
        return value;
    }

    public void push(T element) {
        Objects.requireNonNull(element);
        if(value==null){
            value = element;
        }
        //TODO : check that left or right < element < value to keep tree organized
        else if(comparator.compare(value, element)>=0){
            //will go to left side
            if(left!=null){
                    left.push(element);
            }
            else {
                left = new BinaryHeap<T>(comparator);
                left.push(element);
            }
        }
        else{
            //will go to right side
            if(right!=null){
                right.push(element);
            }
            else {
                right = new BinaryHeap<T>(comparator);
                right.push(element);
            }
        }
    }

    public int count() {
        int v = (value==null)?0:1;
        if(left!=null){
            v+=left.count();
        }
        if(right!=null){
            v+= right.count();
        }
        return v;
    }

}
