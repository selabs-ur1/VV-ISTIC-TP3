package fr.istic.vv;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

class BinaryHeapTest {

    @Test
    void testPopEmptyHeap() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.<Integer>naturalOrder());
        assertThrows(IllegalStateException.class, heap::pop);
    }

    @Test
    void testPop() {
        // Input Space Partitioning:
        // - Empty heap
        // - Heap with one element
        // - Heap with multiple elements

        BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.<Integer>naturalOrder());

        // Empty heap, expect IllegalStateException
        assertThrows(IllegalStateException.class, heap::pop);

        // Heap with one element
        heap.push(5);
        assertEquals(5, heap.pop());

        // Heap with multiple elements
        heap.push(7);
        heap.push(3);
        heap.push(10);
        heap.push(1);

        // After popping, the smallest element (1) should be removed
        assertEquals(1, heap.pop());
        assertEquals(3, heap.pop());
        assertEquals(7, heap.pop());
        assertEquals(10, heap.pop());
        // After popping all elements, the heap should be empty
        assertThrows(IllegalStateException.class, heap::pop);
    }

    @Test
    void testPeekEmptyHeap() {
        BinaryHeap<String> heap = new BinaryHeap<>(Comparator.<String>naturalOrder());
        assertThrows(IllegalStateException.class, heap::peek);
    }

    @Test
    void testPeek() {
        // Input Space Partitioning:
        // - Empty heap
        // - Heap with one element
        // - Heap with multiple elements

        BinaryHeap<String> heap = new BinaryHeap<>(Comparator.<String>naturalOrder());

        // Empty heap, expect IllegalStateException
        assertThrows(IllegalStateException.class, heap::peek);

        // Heap with one element
        heap.push("apple");
        assertEquals("apple", heap.peek());

        // Heap with multiple elements
        heap.push("banana");
        heap.push("cherry");
        heap.push("date");

        // The smallest element ("apple") should be at the root
        assertEquals("apple", heap.peek());
    }

    @Test
    void testPush() {
        // Input Space Partitioning:
        // - Pushing elements in ascending order
        // - Pushing elements in descending order
        // - Pushing elements in random order

        BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.<Integer>reverseOrder());

        // Pushing elements in ascending order
        heap.push(1);
        heap.push(2);
        heap.push(3);
        assertEquals(3, heap.pop());
        assertEquals(2, heap.pop());
        assertEquals(1, heap.pop());

        // Pushing elements in descending order
        heap.push(3);
        heap.push(2);
        heap.push(1);
        assertEquals(3, heap.pop());
        assertEquals(2, heap.pop());
        assertEquals(1, heap.pop());

        // Pushing elements in random order
        heap.push(5);
        heap.push(1);
        heap.push(8);
        heap.push(3);
        assertEquals(8, heap.pop());
        assertEquals(5, heap.pop());
        assertEquals(3, heap.pop());
        assertEquals(1, heap.pop());
    }

    @Test
    void testCount() {
        // Input Space Partitioning:
        // - Empty heap
        // - Heap with one element
        // - Heap with multiple elements

        BinaryHeap<String> heap = new BinaryHeap<>(Comparator.<String>naturalOrder());

        // Empty heap, count should be 0
        assertEquals(0, heap.count());

        // Heap with one element, count should be 1
        heap.push("apple");
        assertEquals(1, heap.count());

        // Heap with multiple elements, count should be the number of elements
        heap.push("banana");
        heap.push("cherry");
        heap.push("date");
        assertEquals(4, heap.count());
    }
}
