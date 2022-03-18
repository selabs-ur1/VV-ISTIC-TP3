package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    private BinaryHeap<Integer> heap;

    @BeforeEach
    void beforeEach(){
        Comparator<Integer> comparator = Integer::compareTo;//Comparator.<Integer>naturalOrder()
        heap = new BinaryHeap<>(comparator);
    }

    @Test
    void pop() {
    }

    @Test
    void peek() {
        heap.push(1);
        assertEquals(1, heap.peek());
    }

    @Test
    void push() {
    }

    @Test
    void testCountEmptyHeap() {
        assertEquals(0, heap.count());
    }
}
