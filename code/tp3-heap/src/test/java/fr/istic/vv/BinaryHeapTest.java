package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    BinaryHeap<Integer> heap;
    Comparator<Integer> comparator;

    @BeforeEach
    public void init(){
        comparator = Comparator.<Integer>naturalOrder();
        heap = new BinaryHeap<>(comparator);
    }

    @Test
    public void testPushOk(){
        heap.push(1);
        assertEquals(heap.getHeap().get(0), 1);
        assertEquals(heap.getHeap().size(),1);
    }

    @Test
    public void testPushNull(){
        heap.push(null);
        assertTrue(heap.getHeap().isEmpty());
    }

    @Test
    public void testPushOnNull(){
        heap.push(1);
        heap.push(2);
        heap.push(3);
        heap.push(4);
        heap.push(5);
        heap.push(6);
        heap.pop();
        heap.push(10);
        assertEquals(heap.getHeap().get(3),10);
    }

    @Test
    public void testSortOk(){
        heap.push(2);
        heap.push(1);
        assertEquals(heap.getHeap().get(0), 1);
        assertEquals(heap.getHeap().get(1), 2);
        assertEquals(heap.getHeap().size(),2);
    }

    @Test
    public void testSortUnordered(){
        heap.push(22);
        heap.push(12);
        heap.push(36);
        heap.push(42);
        heap.push(47);
        heap.push(26);
        heap.push(58);
        heap.push(79);
        heap.push(13);
        heap.push(0);
        assertEquals(heap.getHeap(), Arrays.asList(0,12,26,22,13,36,58,79,42,47));
    }

    @Test
    public void testWithNegatives(){
        heap.push(0);
        heap.push(-1);
        heap.push(-6);
        heap.push(-100);
        heap.push(-1658);
        heap.push(-2);
        heap.push(42);
        heap.push(-3);
        heap.push(22);
        assertEquals(heap.getHeap(), Arrays.asList(-1658,-100,-2,-3,-6,-1,42,0,22));
    }

    @Test
    public void testExtremes(){
        heap.push(2147483647);
        heap.push(-2147483647);
        heap.push(0);
        heap.push(1);
        heap.push(-1);
        assertEquals(heap.getHeap(), Arrays.asList(-2147483647,-1,0,2147483647,1));
    }

    @Test
    public void testCount(){
        assertEquals(heap.count(),heap.getHeap().size());
        heap.push(1);
        assertEquals(heap.count(),heap.getHeap().size());
    }

    @Test
    public void testCountDontCountNull(){
        heap.push(1);
        heap.push(2);
        heap.push(3);
        heap.push(4);
        heap.push(5);
        heap.push(6);
        heap.pop();
        assertNull(heap.getHeap().get(3));
        assertEquals(heap.getHeap().size(),6);
        assertEquals(heap.count(),5);
    }

    @Test
    public void testPeek(){
        heap.push(1);
        assertEquals(heap.peek(),1);
    }

    @Test
    public void testPeekEmpty(){
        assertNull(heap.peek());
    }

    @Test
    public void testPop(){
        heap.push(1);
        assertEquals(heap.pop(),1);
        assertEquals(heap.getHeap().size(),0);
    }

    @Test
    public void testPopEmpty(){
        assertNull(heap.pop());
    }

    /** Test mostly intended to test conditional of internal methods like smallerChild*/
    @Test
    public void testPopItAll(){
        for (int i = 0; i < 15; i++){
            heap.push(i);
        }
        assertNotNull(heap.pop());
        assertNotNull(heap.pop());
        assertNotNull(heap.pop());
        for (int i = 3; i < 15; i++){
            assertEquals(heap.getHeap().get(0),i);
            heap.pop();
        }
    }
}