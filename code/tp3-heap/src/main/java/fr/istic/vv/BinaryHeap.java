package fr.istic.vv;


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
        this.comparator=comparator;
    }

    public T pop() {
        T v = value;
        if(left!=null){
            value = left.pop();
        }
        else{
            if(right!=null){
                value = right.pop();
            }
            else{
                value = null;//delete this
            }
        }
        return v;
    }

    public T peek() {
        return value;
    }

    public void push(T element) {
        if(comparator.compare(value, element)<=0){
            if(left==null){
                value = element;
            }
            else {
                left.push(element);
            }
        }
        else{
            if(right==null){
                value = element;
            }else{
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
