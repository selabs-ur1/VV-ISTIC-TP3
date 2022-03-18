package fr.istic.vv;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for BinaryHeap 
 */

public class BinaryHeapTest {

    private static Integer [] heap;
    private static BinaryHeap binaryHeap;
    private static int CAPACITY =7;
    static Comparator<Integer> compa = new Comparator<Integer>() {
        @Override
        public int compare(Integer lElement, Integer rElement) {
            return lElement.compareTo(rElement);
        }
    };


    @BeforeEach
    public void setup() {
        heap = new Integer[CAPACITY];
        binaryHeap = new BinaryHeap<Integer>(compa, heap);
    }

    @AfterAll
    public static void tearDownPop(){
    }

    /**
     * test pop ok 
     */
    @Test
    public void testPopOK(){

        binaryHeap.push(4);
        binaryHeap.push(3);
        binaryHeap.push(2);
        binaryHeap.push(1);
        binaryHeap.push(8);
        binaryHeap.push(11);
        binaryHeap.push(19);

        assertEquals(1, binaryHeap.pop());
        assertEquals(false, binaryHeap.isFull());
        assertEquals(false, binaryHeap.isEmpty());
    }

    /**
     * test pop ok2
     */
    @Test
    public void testPopOK2(){

        binaryHeap.push(4);
        binaryHeap.push(3);
        binaryHeap.push(2);
        binaryHeap.push(1);
        binaryHeap.push(8);
        binaryHeap.push(0);
        binaryHeap.push(19);

        assertEquals(0, binaryHeap.pop());
        assertEquals(false, binaryHeap.isFull());
        assertEquals(false, binaryHeap.isEmpty());
    }

    /**
     * test pop with heap empty
     */
    @Test
    public void testPopNotOK(){

        assertEquals(false, binaryHeap.isFull());
        assertEquals(true, binaryHeap.isEmpty());
        assertThrows(NoSuchElementException.class, () -> binaryHeap.pop(), "Heap is empty");
    }

    /**
     * test push element in binaryheap OK 
     */
    @Test
    public void testPushOK(){
        binaryHeap.push(4);
        binaryHeap.push(3);
        binaryHeap.push(2);
        binaryHeap.push(1);
        binaryHeap.push(8);
        binaryHeap.push(11);

        assertEquals(CAPACITY,binaryHeap.count());
        assertEquals(false, binaryHeap.isFull());
        assertEquals(false, binaryHeap.isEmpty());
     }


     /**
     * test push element in binaryheap full
     */
    @Test
    public void testPushNotOKBinaryHeapIsFull(){
        binaryHeap.push(4);
        binaryHeap.push(3);
        binaryHeap.push(2);
        binaryHeap.push(1);
        binaryHeap.push(8);
        binaryHeap.push(11);
        binaryHeap.push(19);

        assertEquals(true, binaryHeap.isFull());
        assertThrows(NoSuchElementException.class, () -> binaryHeap.push(5), "Heap is full");
     }

     /**
      * test method peek 
      */

     @Test
    public void testPeekOK(){
        binaryHeap.push(4);
        binaryHeap.push(3);
        binaryHeap.push(2);
        binaryHeap.push(1);
        binaryHeap.push(8);
        binaryHeap.push(11);
        binaryHeap.push(19);

        assertEquals(1,  binaryHeap.peek());
        assertEquals(CAPACITY, binaryHeap.count());
    }

    /**
    * test method peek not ok
    * peek element from the empty heap
    */
    @Test
    public void testPeekNotOKHeapEmpty(){
        assertThrows(NoSuchElementException.class, () -> binaryHeap.peek(), "Heap is empty");
    }

    /**
     * test method count
    */
    @Test
    public void testCountOk(){
         binaryHeap.push(4);
         binaryHeap.push(3);
         binaryHeap.push(2);
         binaryHeap.push(1);
         binaryHeap.push(8);
         binaryHeap.push(11);
         binaryHeap.push(19);

         assertEquals(CAPACITY, binaryHeap.count());
     } 
}