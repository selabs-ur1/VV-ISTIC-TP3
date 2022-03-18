package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    private final Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer a, Integer b) {
            if (a==null && b==null) {
                return 0;
            } else if (a==null)
            {
                return b;
            }
            else if (b==null)
            {
                return a;
            }else {
                if(a.equals(b)) return 0;
                if (a<b) return 1;
                return -1;
            }

        }
    };

    @Test
    public void testPeek() {

        BinaryHeap bh = new BinaryHeap(comparator);

        bh.push(35);
        bh.push(29);
        bh.push(6);
        bh.push(17);

        int[] direction = bh.getDirection(4);
        System.out.println(bh.peek());
    }


}