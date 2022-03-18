package fr.istic.vv;

import java.util.Comparator;
import java.util.NoSuchElementException;

class BinaryHeap<T> {

    private int heapSize;
    private T[] heap;
    private Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public BinaryHeap(Comparator<T> comparator, T[] heap) {
        heapSize = 0;
        this.comparator = comparator;
        this.heap = heap;
    }

    public void isEmpty(){
        if (heapSize == 0) throw new NoSuchElementException("Heap is empty");
    }

    public T pop() {
        try {
            isEmpty();
            T newHeap = this.heap[0];
            delete(0);
            return newHeap;
        }catch (NoSuchElementException e){
            return (T) e;
        }
    }

    public T peek() {
        try {
            isEmpty();
            return this.heap[0];
        }catch (NoSuchElementException e){
            return (T) e;
        }
    }

    public void push(T element) {
        heap[heapSize++] = element;
        heapifyUp(heapSize - 1);
    }

    public int count() {
        return this.heap.length;
    }

    private void heapifyUp(int childIndex) {
        T tmp = heap[childIndex];
        while (childIndex > 0 && (comparator.compare(tmp, heap[parent(childIndex)]) == -1)) {
            heap[childIndex] = heap[parent(childIndex)];
            childIndex = parent(childIndex);
        }
        heap[childIndex] = tmp;
    }

    private void heapifyDown(int index)
    {
        int child;
        T tmp = heap[index];
        while (kthChild(index, 1) < heapSize) {
            child = minimumChild(index);
            if (comparator.compare(heap[child],tmp)==-1)
                heap[index] = heap[child];
            else
                break;
            index = child;
        }
        heap[index] = tmp;
    }

    public T delete(int index) {
        try {
            isEmpty();
            T keyItem = heap[index];
            heap[index] = heap[heapSize - 1];
            heapSize--;
            heapifyDown(index);
            return keyItem;
        }catch (NoSuchElementException e){
            return (T) e;
        }
    }

    private int minimumChild(int index) {
        int minchildIndex = kthChild(index, 1);
        int k = 2;
        int position = kthChild(index, k);
        while ((k <= 2) && (position < heapSize)) {
            if (comparator.compare(heap[position],heap[minchildIndex])==-1)
                minchildIndex = position;
            position = kthChild(index, k++);
        }
        return minchildIndex;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int kthChild(int index, int k) {
        return 2 * index + k;
    }
}