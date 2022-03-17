package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

class BinaryHeap<T> {

    private ArrayList<T> array;
    private Comparator<T> comparator;

    /**
     * Create an instance of BinaryHeap.
     * The first node parent is the element max (index 0) and the last node children the minimal (index size-1)
     * @param comparator represents the ordering criterion between the objects in the heap.
     */
    public BinaryHeap(Comparator<T> comparator, ArrayList<T> array) {
        this.array = array;
        this.comparator = comparator;
        this.array.sort(this.comparator);
    }

    private void rearrangeMaxHeap(int root, int i){
        int largest = i; // Initialize largest as root
        int left = (2 * i) + 1; //Left node
        int right = (2 * i) + 2; //Right node

        // Left child is larger than root and largest
        if (left < root ){
            if(this.comparator.compare(this.array.get(left), this.array.get(largest)) == 1){
                largest = left;
            }
        }

        // Right child is larger than root and largest
        if (right < root) {
            if(this.comparator.compare(this.array.get(right), this.array.get(largest)) == 1){
                largest = right;
            }
        }

        // Rearrange the heap if largest is not root
        if (largest != i) {
            T swap = this.array.get(i);
            this.array.set(i, this.array.get(largest));
            this.array.set(largest, swap);
        }
    }

    public void sort() {
        int nbElement = count();

        // Get the node parent
        for (int i = nbElement; i >= 0; i--){
            rearrangeMaxHeap(nbElement, i);
        }

        // Extract one by one an element from heap
        for (int i = nbElement - 1; i >= 1; i--) {
            // Move current root to end
            T temp = this.array.get(0);
            this.array.set(0, this.array.get(i));
            this.array.set(i, temp);

            // Get the max on the reduced heap for a new extraction
            rearrangeMaxHeap( i, 0);
        }
    }

    /**
     * Returns and removes the minimum object of the heap.
     * @return the minimum object of the heap.
     * @throws NoSuchElementException if the heap is empty.
     */
    public T pop() throws NoSuchElementException {
        if (array.isEmpty()) throw new NoSuchElementException("The heap is empty") ;
        T result = this.array.get(0);
        array.remove(result);
        return result;
    }

    /**
     * Return without removes it the minimum object of the heap.
     * @return the minimum object of the heap.
     * @throws NoSuchElementException if the heap is empty.
     */
    public T peek() throws NoSuchElementException {
        if (array.isEmpty()) throw new NoSuchElementException("The heap is empty") ;
        return array.get(0);
    }

    /**
     * Add an element to the BinaryHeap
     * @param element element
     */
    public void push(T element) {
        array.add(element);
        sort();
    }

    /**
     * Give the number of elements in the BinaryHeap
     * @return the number of elements
     */
    public int count() {
        return array.size();
    }

}