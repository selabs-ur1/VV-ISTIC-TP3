package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testEmptyString(){
        String s = "";
        assertEquals(true, isBalanced(s));
    }

    @Test
    void testBalancedString(){
        String s = "([{}])";
        assertEquals(true, isBalanced(s));
    }

    @Test
    void testUnbalancedString(){
        String s = "([}]";
        assertEquals(false, isBalanced(s));
    }

    @Test
    void testOneTypeString(){
        String s = "((()))";
        assertEquals(true, isBalanced(s));
    }

    @Test
    void testMixedString(){
        String s = "({[}])"; 
        assertEquals(false, isBalanced(s));
    }

    @Test
    void testQ2_1(){
        String s = ")";
        assertEquals(false, isBalanced(s));
    }

    @Test
    void testQ2_2(){
        String s = "]";
        assertEquals(false, isBalanced(s));
    }

    @Test
    void testQ3_1(){
        String s = "())";
        assertEquals(false, isBalanced(s));
    }

    @Test
    void testQ3_2(){
        String s = "{)";
        assertEquals(false, isBalanced(s));
    }

    @Test
    void testQ3_3(){
        String s = "{}{";
        assertEquals(false, isBalanced(s));
    }

    @Test
    void testQ3_4(){
        String s = "(}";
        assertEquals(false, isBalanced(s));
    }

    @Test
    void testQ3_5(){
        String s = "[][";
        assertEquals(false, isBalanced(s));
    }

    @Test
    void testQ3_6(){
        String s = "{]";
        assertEquals(false, isBalanced(s));
    }

    @Test
    void testQ4_1(){
        String s = "()(";
        assertEquals(false, isBalanced(s));
    }




}