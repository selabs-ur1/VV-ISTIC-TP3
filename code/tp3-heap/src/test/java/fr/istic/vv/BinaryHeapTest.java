package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    BinaryHeap heap ;

    @BeforeEach
    void init(){
        Comparator comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if(a==b) return 0;
                if (a<b) return 1;
                return -1;
            }
        };
        Exception e = new NotSuchElementException() {};
        heap = new BinaryHeap(comparator,e);
    }

    @Test
    void binaryHeapPushTest_1(){
        heap.push(1);
        assertEquals(1,heap.count());
    }

    @Test
    void binaryHeapPushTest_2() throws Throwable {
        heap.push(1);
        assertEquals(1,heap.peek());
    }

    @Test
    void binaryHeapPushTest_3() {
        heap.push(1);
        heap.push(1);
        heap.push(2);
        heap.pop();
        assertEquals(1,heap.count());
    }

    @Test
    void binaryHeapPopTest_1() {
        int minimum = 1;
        heap.push(minimum);
        heap.push(minimum*2);
        heap.push(minimum*3);
        heap.push(minimum*4);
        heap.push(minimum*5);
        assertEquals(minimum,heap.pop());
    }

//    @Test
//    void binaryHeapPopTest_2() {
//        assertThrows(BinaryHeap.NotSuchElementException.class,() -> heap.pop());
//    }
//
//    @Test
//    void binaryHeapPopTest_3() {
//        int minimum = 1;
//        heap.push(minimum);
//        heap.pop();
//        assertThrows(BinaryHeap.NotSuchElementException.class,() -> heap.peek());
//    }

    @Test
    void binaryHeapPeekTest_1() throws Throwable {
        int minimum = 1;
        heap.push(minimum);
        heap.push(minimum*2);
        heap.push(minimum*3);
        heap.push(minimum*4);
        heap.push(minimum*5);
        assertEquals(minimum,heap.peek());
    }

//    @Test
//    void binaryHeapPeekpTest_2() {
//        assertThrows(BinaryHeap.NotSuchElementException.class,() -> heap.peek());
//    }

}