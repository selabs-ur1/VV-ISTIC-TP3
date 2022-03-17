package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    private static BinaryHeap<Integer> binaryHeap ;
    private static BinaryHeap<Integer> binaryHeapDoubleData;
    private static BinaryHeap<Integer> binaryHeapEmpty ;
    private static BinaryHeap<Integer> binaryHeapStartByMinData;

    @BeforeEach
    public void setUp(){
        binaryHeap = new BinaryHeap<>((int1, int2) -> {
            if (int1 > int2) return 1 ;
            else if (int1 < int2) return -1 ;
            else return 0;
        }, new ArrayList<>(Arrays.asList(13, 12, 14, 6, 5, 8, 10)));

        binaryHeapDoubleData = new BinaryHeap<>((int1, int2) -> {
            if (int1 > int2) return 1 ;
            else if (int1 < int2) return -1 ;
            else return 0;
        }, new ArrayList<>(Arrays.asList(13, 13, -2, -2, 3, 3)));

        binaryHeapStartByMinData = new BinaryHeap<>((int1, int2) -> {
            if (int1 > int2) return 1 ;
            else if (int1 < int2) return -1 ;
            else return 0;
        }, new ArrayList<>(Arrays.asList(-4, -6, 2, -1, 3, -3)));

        binaryHeapEmpty = new BinaryHeap<>((int1, int2) -> {
            if (int1 > int2) return 1 ;
            else if(int1 < int2) return -1 ;
            else return 0;
        }, new ArrayList<>());
    }

    @Test
    public void testCountFullBinaryHeap(){
        assertEquals(binaryHeap.count(),7);
    }

    @Test
    public void testCountDoubleBinaryHeap(){
        assertEquals(binaryHeapDoubleData.count(),6);
    }

    @Test
    public void testCountEmptyBinaryHeap(){
        assertEquals(binaryHeapEmpty.count(),0);
    }

    @Test
    public void testPeekFullBinaryHeap(){
        assertEquals(binaryHeap.peek(),5);
    }

    @Test
    public void testPeekEmptyBinaryHeap() throws NoSuchElementException {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            binaryHeapEmpty.peek();
        });
        assertTrue(exception.getMessage().contains("The heap is empty"));
    }

    @Test
    public void testPopFullBinaryHeap(){
        assertEquals(binaryHeap.pop(),5);
        assertEquals(binaryHeap.peek(), 6);
        assertEquals(binaryHeap.count(), 6);
    }

    @Test
    public void testPopDoubleHeap(){
        assertEquals(binaryHeapDoubleData.pop(),-2);
        assertEquals(binaryHeapDoubleData.peek(), -2);
        assertEquals(binaryHeapDoubleData.count(), 5);
    }

    @Test
    public void testPopEmptyBinaryHeap() throws NoSuchElementException {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            binaryHeapEmpty.pop();
        });
        assertTrue(exception.getMessage().contains("The heap is empty"));
    }

    @Test
    public void testPushFullBinaryHeap(){
        binaryHeap.push(-6);
        assertEquals(binaryHeap.count(), 8);
        assertEquals(binaryHeap.peek(), -6);
    }

    @Test
    public void testPushEmptyBinaryHeap(){
        binaryHeapEmpty.push(8);
        assertEquals(binaryHeapEmpty.count(), 1);
        assertEquals(binaryHeapEmpty.peek(), 8);
    }

    @Test
    public void testPushMultipleBinaryHeap(){
        binaryHeap.push(1);
        binaryHeap.push(-5);
        assertEquals(binaryHeap.count(), 9);
        assertEquals(binaryHeap.peek(), -5);
    }

    @Test
    public void testSortEmptyBinaryHeap(){
        binaryHeapEmpty.push(0);
        binaryHeapEmpty.push(90);
        binaryHeapEmpty.sort();
        assertEquals(binaryHeapEmpty.count(), 2);
        assertEquals(binaryHeapEmpty.peek(), 0);
    }

    @Test
    public void testSortEmptyBinaryHeap2(){
        binaryHeapEmpty.push(90);
        binaryHeapEmpty.push(0);
        binaryHeapEmpty.sort();
        assertEquals(binaryHeapEmpty.count(), 2);
        assertEquals(binaryHeapEmpty.peek(), 0);
    }
}