package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {

    private final Comparator<T> comparator;
    private final List<T> heap;

    public BinaryHeap(Comparator<T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    public BinaryHeap(Comparator<T> integerComparator, Comparator<T> comparator, List<T> heap) {
        this.comparator = comparator;
        this.heap = heap;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T min = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapifyDown();
        return min;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    public void push(T element) {
        heap.add(element);
        heapifyUp();
    }

    public int count() {
        return heap.size();
    }

    private boolean isEmpty() {
        return heap.isEmpty();
    }

    private void heapifyUp() {
        int index = heap.size() - 1;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (compare(heap.get(index), heap.get(parentIndex)) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (true) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int smallest = index;

            if (leftChildIndex < heap.size() && compare(heap.get(leftChildIndex), heap.get(smallest)) < 0) {
                smallest = leftChildIndex;
            }

            if (rightChildIndex < heap.size() && compare(heap.get(rightChildIndex), heap.get(smallest)) < 0) {
                smallest = rightChildIndex;
            }

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private int compare(T a, T b) {
        return comparator.compare(a, b);
    }
}
