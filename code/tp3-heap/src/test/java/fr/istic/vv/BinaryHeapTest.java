import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    @Test
    void testPopEmptyHeap() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.naturalOrder());
        assertThrows(NoSuchElementException.class, heap::pop);
    }

    @Test
    void testPeekEmptyHeap() {
        BinaryHeap<String> heap = new BinaryHeap<>(Comparator.naturalOrder());
        assertThrows(NoSuchElementException.class, heap::peek);
    }

    @Test
    void testPushAndPop() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.naturalOrder());

        heap.push(3);
        heap.push(1);
        heap.push(4);
        heap.push(2);

        assertEquals(1, heap.pop());
        assertEquals(2, heap.pop());
        assertEquals(3, heap.pop());
        assertEquals(4, heap.pop());

        assertTrue(heap.count() == 0);
    }

    @Test
    void testPeek() {
        BinaryHeap<String> heap = new BinaryHeap<>(Comparator.naturalOrder());

        heap.push("banana");
        heap.push("apple");
        heap.push("cherry");

        assertEquals("apple", heap.peek());
        assertEquals("apple", heap.peek()); // Peek should not remove the element
    }

    @Test
    void testCount() {
        BinaryHeap<Character> heap = new BinaryHeap<>(Comparator.naturalOrder());

        heap.push('z');
        heap.push('x');
        heap.push('a');

        assertEquals(3, heap.count());

        heap.pop();
        assertEquals(2, heap.count());

        heap.pop();
        assertEquals(1, heap.count());

        heap.pop();
        assertEquals(0, heap.count());
    }

    @Test
    void testCustomComparator() {
        Comparator<String> lengthComparator = Comparator.comparing(String::length);
        BinaryHeap<String> heap = new BinaryHeap<>(lengthComparator);

        heap.push("apple");
        heap.push("banana");
        heap.push("kiwi");

        assertEquals("kiwi", heap.pop());
        assertEquals("apple", heap.pop());
        assertEquals("banana", heap.pop());
    }
}
