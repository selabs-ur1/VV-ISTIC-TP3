package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    BinaryHeap<Integer> heap;

    @BeforeEach
    public void setUp() {
       heap  = new BinaryHeap<>(null);
    }

    @Test
    @DisplayName("test EmptyHeap in pop()")
    public void testErrorPopEmptyHeap() {
        assertEquals(0, heap.count(), "The heap is empty");
        assertThrows(NoSuchElementException.class, () -> heap.pop());
    }

    @Test
    @DisplayName("test EmptyHeap in peek()")
    public void testErrorPeekEmptyHeap() {
        assertEquals(0, heap.count(), "The heap is empty");
        assertThrows(NoSuchElementException.class, () -> heap.peek());
    }

    @Test
    public void testErrorPushNullElement() {
        //BinaryHeap<Integer> heap = new BinaryHeap<>(null);
        heap.push(1);
        assertThrows(IllegalArgumentException.class,()-> heap.push(null));
    }

    @Test
    public void testPushPeek1() {
        heap.push(1);
        heap.push(2);
        assertEquals(1,heap.peek(),"The top of the heap should be 1");
    }

    @Test
    public void testPushPeek2() {
        heap.push(2);
        heap.push(1);
        assertEquals(1,heap.peek(),"The top of the heap should be 1 no matter the order of push");
    }

    @Test
    public void testPushPeek3() {
        heap.push(3);
        heap.push(1);
        heap.push(2);
        assertEquals(1,heap.peek(),"The top of the heap should always be the minimum object: 1");
    }

    @Test
    public void testPushPeek4() {
        heap.push(3);
        heap.push(1);
        heap.push(2);
        heap.push(0);
        assertEquals(0,heap.peek(),"The top of the heap should always be the minimum object: 0");
    }

    @Test
    public void testPushPop1() {
        int original = 1;
        heap.push(original);
        int onTop = heap.pop();
        assertEquals(original, onTop, "Element on top of the heap should be " + original);
    }

    @Test
    public void testPushPop2() {
        heap.push(1);
        heap.pop();
        assertEquals(0, heap.count(), "The heap should be empty");
    }

    @Test
    public void testPushPop3() {
        heap.push(10);
        heap.push(11);
        int top = heap.pop();
        assertEquals(10, top, "Element on top of the heap should be 10");
        assertEquals(1, heap.count(), "The size of the heap should be 1");
    }

    @Test
    public void testPushPop4() {
        heap.push(1);
        heap.push(2);
        int top1 = heap.pop();
        assertEquals(1, top1, "Element on top of the heap should be 1");
        assertEquals(1, heap.count(), "The size of the heap should be 1");

        int top2 = heap.pop();
        assertEquals(2, top2, "Element on top of the heap should be 2");
        assertEquals(0, heap.count(), "The heap should be empty");
    }

    @Test
    public void testPushPop5() {
        heap.push(3);
        heap.push(1);
        heap.push(2);

        int top1 = heap.pop();
        assertEquals(1, top1, "Element on top of the heap should be 1");
        assertEquals(2, heap.count(), "The size of the heap should be 2");

        int top2 = heap.pop();
        assertEquals(2, top2, "Element on top of the heap should be 2");
        assertEquals(1, heap.count(), "The size of the heap should be 1");
    }


    // new test cases for pop() after statement coverage
    @Test
    public void testPushPop6() {
        heap.push(30);
        heap.push(100);
        heap.push(20);
        heap.push(40);
        heap.push(400);

        int top1 = heap.pop();
        assertEquals(20, top1, "Element on top of the heap should be 20");
        assertEquals(4, heap.count(), "The size of the heap should be 4");
    }


    @Test
    public void testPushPopPeek1() {
        heap.push(3);
        heap.push(1);

        int top1 = heap.pop();
        assertEquals(1, top1, "Element on top of the heap should be 1");
        assertEquals(1, heap.count(), "The size of the heap should be 1");
        assertEquals(3, heap.peek(), "Element on top of the heap should be 3");

        heap.push(2);

        int top2 = heap.pop();
        assertEquals(2, top2, "Element on top of the heap should be 2");
        assertEquals(1, heap.count(), "The size of the heap should be 1");
        assertEquals(3, heap.peek(), "Element on top of the heap should be 3");
    }

    @Test
    public void testPushPopPeek2() {
        heap.push(3);
        heap.push(1);

        int top1 = heap.pop();
        assertEquals(1, top1, "Element on top of the heap should be 1");
        assertEquals(1, heap.count(), "The size of the heap should be 1");
        assertEquals(3, heap.peek(), "Element on top of the heap should be 3");

        heap.push(2);
        heap.push(0);

        int top2 = heap.pop();
        assertEquals(0, top2, "Element on top of the heap should be 0");
        assertEquals(2, heap.count(), "The size of the heap should be 2");
        assertEquals(2, heap.peek(), "Element on top of the heap should be 2");
    }


    @Test
    public void testPushPopPeek3() {
        heap.push(1);

        heap.pop();

        heap.push(2);
        heap.push(4);

        int top2 = heap.pop();

        assertEquals(2, top2, "Element on top of the heap should be 2");
        assertEquals(1, heap.count(), "The size of the heap should be 1");
        assertEquals(4, heap.peek(), "Element on top of the heap should be 4");
    }

    @Test
    public void testPushPopPeek4() {
        heap.push(1);

        heap.pop();

        heap.push(2);
        heap.push(4);
        heap.push(3);

        int top2 = heap.pop();

        assertEquals(2, top2, "Element on top of the heap should be 2");
        assertEquals(2, heap.count(), "The size of the heap should be 2");
        assertEquals(3, heap.peek(), "Element on top of the heap should be 3");
    }

    // new test cases after mutation testing
    @Test
    public void testPushPopPeek5() {
        heap.push(1);

        heap.pop();

        heap.push(2);
        heap.push(40);
        heap.push(3);
        heap.push(12);
        heap.push(10);
        heap.push(42);

        heap.pop();
        heap.pop();
        heap.pop();
        int top4 = heap.pop();

        assertEquals(12, top4, "Element on top of the heap should be 12");
        assertEquals(2, heap.count(), "The size of the heap should be 2");
        assertEquals(40, heap.peek(), "Element on top of the heap should be 40");

    }

    @Test
    public void testPushPop7() {

        heap.push(2);
        heap.push(40);
        heap.push(23);
        heap.push(12);
        heap.push(10);
        heap.push(42);

        heap.pop();
        int top2  = heap.pop();
        assertEquals(10, top2, "Element on top of the heap should be 10");
        int top3 = heap.pop();
        assertEquals(12, top3, "Element on top of the heap should be 12");
        heap.pop();
        int top5 = heap.pop();
        assertEquals(40, top5, "Element on top of the heap should be 40");
    }

}
