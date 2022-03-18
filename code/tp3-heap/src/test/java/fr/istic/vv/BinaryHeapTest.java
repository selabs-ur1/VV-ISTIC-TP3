package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

 class BinaryHeapTest {

     Comparator<Integer> comparator = new Comparator<Integer>() {
         @Override
         public int compare(Integer lElement, Integer rElement) {
             return lElement.compareTo(rElement);
         }
     };
    private static BinaryHeap<Integer> binaryHeap ;
    private ArrayList<Integer> arrayList;
    @BeforeEach
    public void setUp(){
        arrayList = new ArrayList<>();
        binaryHeap = new BinaryHeap<>(comparator, arrayList);
    }

    @Test
    public void testCountEmpty(){
        assertEquals(binaryHeap.count(),0);
    }
     @Test
     public void testPushTrue(){
        binaryHeap.push(1);
        assertEquals(binaryHeap.count(),1);
     }

     @Test
     public void testPushTwoTrue(){
         binaryHeap.push(1);
         binaryHeap.push(2);
         assertEquals(binaryHeap.count(),2);
         assertEquals(binaryHeap.array.get(0), 2);
     }

     @Test
     public void testPushSeveralTrue(){
         binaryHeap.push(3);
         binaryHeap.push(1);
         binaryHeap.push(4);
         binaryHeap.push(2);
         System.out.println(binaryHeap.getArray());
         assertEquals(binaryHeap.array.get(0), 1);
         assertEquals(binaryHeap.count(),4);
     }

}