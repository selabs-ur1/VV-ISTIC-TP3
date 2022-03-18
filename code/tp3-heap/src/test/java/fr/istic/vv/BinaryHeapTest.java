package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryHeapTest {

    Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer lElement, Integer rElement) {
            return lElement.compareTo(rElement);
        }
    };

    private Integer [] heap;
    private BinaryHeap binaryHeap;

    @BeforeEach
    public void setup() {
        heap = new Integer[4];
        binaryHeap = new BinaryHeap<Integer>(comparator, heap);
    }

    @Test
    public void testPop(){

        binaryHeap.push(4);
        binaryHeap.push(3);
        binaryHeap.push(2);
        binaryHeap.push(1);

        assertEquals(1, binaryHeap.pop());
    }

    @Test
    public void testPush(){

        binaryHeap.push(4);
        binaryHeap.push(3);
        binaryHeap.push(2);
        binaryHeap.push(1);

        assertEquals(4,binaryHeap.count());
    }

    @Test
    public void testPeek(){
        binaryHeap.push(4);
        binaryHeap.push(3);
        binaryHeap.push(2);
        binaryHeap.push(1);

        assertEquals(1,  binaryHeap.peek());
    }

    @Test
    public void testCount(){

        binaryHeap.push(4);
        binaryHeap.push(3);
        binaryHeap.push(2);
        binaryHeap.push(1);

        assertEquals(4, binaryHeap.count());
    }

    @Test
    public void testIsEmpty(){
        try {
            binaryHeap.isEmpty();
            fail("NoSuchElementException not catch");
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }

    }

}