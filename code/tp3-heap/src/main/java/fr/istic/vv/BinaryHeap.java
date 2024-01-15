import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

class BinaryHeap<T> {
    private final Comparator<T> comparator;
    private final ArrayList<T> heap;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        T minElement = heap.get(0);
        int lastIdx = heap.size() - 1;
        T lastElement = heap.remove(lastIdx);

        if (lastIdx > 0) {
            heap.set(0, lastElement);
            heapifyDown(0);
        }

        return minElement;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        return heap.get(0);
    }

    public void push(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    public int count() {
        return heap.size();
    }

    private void heapifyUp(int childIdx) {
        while (childIdx > 0) {
            int parentIdx = (childIdx - 1) / 2;
            if (compare(heap.get(childIdx), heap.get(parentIdx)) >= 0) {
                break;
            }

            swap(childIdx, parentIdx);
            childIdx = parentIdx;
        }
    }

    private void heapifyDown(int parentIdx) {
        int childIdx;
        int size = heap.size();

        while ((childIdx = 2 * parentIdx + 1) < size) {
            if (childIdx + 1 < size && compare(heap.get(childIdx + 1), heap.get(childIdx)) < 0) {
                childIdx++;
            }

            if (compare(heap.get(parentIdx), heap.get(childIdx)) <= 0) {
                break;
            }

            swap(parentIdx, childIdx);
            parentIdx = childIdx;
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

    private boolean isEmpty() {
        return heap.isEmpty();
    }
}
