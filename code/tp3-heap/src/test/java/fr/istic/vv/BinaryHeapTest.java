package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

class BinaryHeapTest {

  @Test
	void test() {
    BinaryHeap<Integer> heap = new BinaryHeap<Integer>((a, b) -> Integer.compare(a, b), Integer.class);
    
    assertThrows(NoSuchElementException.class, () -> heap.pop());
		assertTrue(heap.count() == 0);
    
    heap.push(2);
    
    assertTrue(heap.peek()==2 && heap.count()==1);
    
    heap.push(10);
		heap.push(1);
    
    assertTrue(heap.pop()==1 && heap.pop()==2 && heap.pop()==10);
  }

}
