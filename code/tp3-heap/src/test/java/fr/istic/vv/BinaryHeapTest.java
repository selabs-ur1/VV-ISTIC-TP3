package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * Classe de test de la classe BinaryHeap sur des entiers
 */
class BinaryHeapIntTest {

    BinaryHeap<Integer> heap;
    Comparator<Integer> comparator;

    @BeforeEach
    public void setUp(){
        comparator = Comparator.<Integer>naturalOrder();
        heap = new BinaryHeap<>(comparator);
    }

    @Test
    public void testPushSimpleElement(){
        heap.push(5);

        assertEquals(heap.getHeapAsArray().size(), 1);
        assertEquals(heap.getHeapAsArray().get(0), 5);
    }

    @Test
    public void testPushNull(){
        heap.push(null);

        assertTrue(heap.getHeapAsArray().isEmpty());
    }

    @Test
    public void testPushElementAndNull(){
        heap.push(7);
        heap.push(4);
        heap.push(null);
        heap.push(9);

        assertEquals(heap.getHeapAsArray().size(), 3);
    }

    @Test
    public void testPushAndWellSorted(){
        heap.push(4);
        heap.push(7);
        heap.push(1);

        assertEquals(heap.getHeapAsArray().size(), 3);
        assertEquals(heap.getHeapAsArray().get(0), 1);
        assertEquals(heap.getHeapAsArray().get(1), 7);
        assertEquals(heap.getHeapAsArray().get(2), 4);

    }

    @Test
    public void testPushNegatives(){
        heap.push(4);
        heap.push(7);
        heap.push(-1);

        assertEquals(heap.getHeapAsArray().size(), 3);
        assertEquals(heap.getHeapAsArray().get(0), -1);
        assertEquals(heap.getHeapAsArray().get(1), 7);
        assertEquals(heap.getHeapAsArray().get(2), 4);
    }

    @Test
    public void testPop(){
        heap.push(4);
        heap.push(9);
        heap.push(1);

        Integer poped = heap.pop();

        assertEquals(poped, 1);
        assertEquals(heap.getHeapAsArray().size(), 2);
    }

    @Test
    public void testPopNull(){
        assertNull(heap.pop());
    }

    @Test
    public void testPeekSimpleElement(){
        heap.push(1);

        assertEquals(heap.peek(), 1);
    }

    @Test
    public void testPeekMultipleElements(){
        heap.push(4);
        heap.push(9);
        heap.push(1);

        assertEquals(heap.peek(), 1);
    }

    @Test
    public void testPeekNull(){
        assertNull(heap.peek());
    }

    @Test
    public void testCountEmpty(){
        assertEquals(heap.count(), 0);
    }

    @Test
    public void testCountWithElements(){
        heap.push(40);
        heap.push(92);
        heap.push(6);

        assertEquals(heap.count(), 3);
    }

    @Test
    public void testCountWithElementsAndNull(){
        heap.push(40);
        heap.push(92);
        heap.push(null);
        heap.push(6);

        assertEquals(heap.count(), 3);
    }


}