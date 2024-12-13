package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.*;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    @Test
    public void testEmptyString() {
        assertTrue(isBalanced(""));
    }

    @Test
    public void testSingleCharacters1() {
        assertFalse(isBalanced("{"));
    }
    @Test
    public void testSingleCharacters2() {
        assertFalse(isBalanced(")"));
    }

    @Test
    public void testSingleCharacters3() {
        assertFalse(isBalanced("["));
    }

    @Test
    public void testSimpleBalancedStrings1() {
        assertTrue(isBalanced("{}"));
    }
    @Test
    public void testSimpleBalancedStrings2() {
        assertTrue(isBalanced("[]"));
    }
    @Test
    public void testSimpleBalancedStrings3() {
        assertTrue(isBalanced("()"));
    }

    @Test
    public void testNestedBalancedStrings1() {
        assertTrue(isBalanced("{[()]}"));
    }

    @Test
    public void testNestedBalancedStrings2() {
        assertTrue(isBalanced("({})"));
    }

    @Test
    public void testMixedBalancedStrings1() {
        assertTrue(isBalanced("{[][]}({})"));
    }

    @Test
    public void testMixedBalancedStrings2() {
        assertTrue(isBalanced("{[()()]}"));
    }

    @Test
    public void testUnbalancedStrings1() {
        assertFalse(isBalanced("]["));
    }

    @Test
    public void testUnbalancedStrings2() {
        assertFalse(isBalanced("([)]"));
    }

    @Test
    public void testUnbalancedStrings3() {
        assertFalse(isBalanced("{"));
    }

    @Test
    public void testUnbalancedStrings4() {
        assertFalse(isBalanced("[{"));
    }

    @Test
    public void testUnbalancedStrings5() {
        assertFalse(isBalanced("[)"));
    }

}