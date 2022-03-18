package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

@Test
void test(){
    BinaryHeap<Integer> bin = new BinaryHeap<Integer>((x,y) -> Integer.compare(x, y));
    assertTrue(bin.count() == 0);

    bin.push(1);

    assertTrue(bin.count() == 1);

    assertTrue(bin.peek() == 1);
    assertTrue(bin.pop() == 1);
    assertFalse(bin.count() == 0);
}
}