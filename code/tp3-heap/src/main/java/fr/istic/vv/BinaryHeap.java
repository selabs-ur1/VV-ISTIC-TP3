package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Source : https://www.edureka.co/blog/binary-heap-in-java/
 * https://www.techiedelight.com/min-heap-max-heap-implementation-in-java/ */
class BinaryHeap<T> {

    private final List<T> heap;
    private final Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    /** Return the value of the smaller node and delete it*/
    public T pop() {
        if (!heap.isEmpty()){
            T root = heap.get(0);
            heap.set(0,null);
            sortHeapFromRoot();
            return root;
        } else return null;
    }

    /**  */
    public T peek() {
        if (heap.isEmpty()) return null;
        else return heap.get(0);
    }

    public void push(T element) {
        if (element != null){
            int nextNull = heap.indexOf(null);
            if (nextNull != -1){
                heap.set(nextNull,element);
                sortEntryEndToRoot(nextNull);
            } else {
                heap.add(element);
                sortEntryEndToRoot(heap.size()-1);
            }
        }
    }

    public int count() {
        int count = 0;
        for (T item : heap) {
            if (item != null) count++;
        }
        return count;
    }

    /** Sort a null node from root */
    private void sortHeapFromRoot(){
        if (!heap.isEmpty()){
            int i = 0;
            while (hasChild(i)){
                int iChild = smallerChild(i);
                swap(i,iChild);
                i = iChild;
            }
            trim();
        }
    }

    /** Remove the trailing null values to not affect the list size with empty values */
    private void trim(){
        if (!heap.isEmpty() && heap.get(heap.size()-1) == null){
            heap.remove(heap.size()-1);
        }
    }

    /** Sort the last entry of the heap with it's parents */
    private void sortEntryEndToRoot(int i){
        // If order not already correct for the first value and it's parent
        if (comparator.compare(heap.get(i),heap.get(parent(i))) < 0){
            while (i > 0){
                if (comparator.compare(heap.get(i),heap.get(parent(i))) < 0){
                    swap(i, parent(i));
                }
                i = parent(i);
            }
        }
    }

    /** swap values at two indexes */
    private void swap(int x, int y) {
        // swap with a child having greater value
        T temp = heap.get(x);
        heap.set(x, heap.get(y));
        heap.set(y, temp);
    }

    /** return parent of `heap[i]` */
    private int parent(int i) {
        // if `i` is already a root node
        if (i == 0) {
            return 0;
        }
        return (i - 1) / 2;
    }

    /** return left child of `heap[i]` */
    private int left(int i) {
        int left = (2*i + 1);
        if (left < heap.size() && heap.get(left) != null) return left;
        else return -1;
    }

    /** return right child of `heap[i]` */
    private int right(int i) {
        int right = (2*i + 2);
        if (right < heap.size() && heap.get(right) != null) return right;
        else return -1;
    }

    private boolean hasChild(int i){
        return hasLeft(i) || hasRight(i);
    }

    private boolean hasLeft(int i){
        return left(i) != -1;
    }

    private boolean hasRight(int i){
        return right(i) != -1;
    }

    /** return the index of the smaller child of i or -1 if i has no child*/
    private int smallerChild(int i) {
        if (hasChild(i)) {
            if (hasLeft(i) && hasRight(i)) {
                if (comparator.compare(heap.get(left(i)), heap.get(right(i))) < 0) return left(i);
                else return right(i);
            } else if (hasLeft(i)) return left(i);
            else return right(i);
        } else return -1; // This line should be impossible to attain
    }

    // TEST GETTERS

    public List<T> getHeap() {
        return heap;
    }
}