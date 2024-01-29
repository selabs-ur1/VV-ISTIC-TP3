package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class BinaryHeap<T> {
    private final List<T> heap;
    private final Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    public T pop() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        T root = heap.get(0);
        T lastElement = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            heapifyDown();
        }

        return root;
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
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

    private void heapifyUp() {
        int index = heap.size() - 1;

        while (hasParent(index) && compare(heap.get(index), getParent(index)) < 0) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;

        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);

            if (hasRightChild(index) && compare(getRightChild(index), getLeftChild(index)) < 0) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (compare(heap.get(index), heap.get(smallerChildIndex)) < 0) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }

            index = smallerChildIndex;
        }
    }

    private boolean hasParent(int index) {
        return index > 0;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private T getParent(int index) {
        return heap.get(getParentIndex(index));
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < heap.size();
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private T getLeftChild(int index) {
        return heap.get(getLeftChildIndex(index));
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < heap.size();
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private T getRightChild(int index) {
        return heap.get(getRightChildIndex(index));
    }

    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    private int compare(T a, T b) {
        return comparator.compare(a, b);
    }
}
