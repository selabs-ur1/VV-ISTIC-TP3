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
    public void testSingleCharacters() {
        assertFalse(isBalanced("{"));
        assertFalse(isBalanced(")"));
        assertFalse(isBalanced("["));
    }

    @Test
    public void testSimpleBalancedStrings() {
        assertTrue(isBalanced("{}"));
        assertTrue(isBalanced("[]"));
        assertTrue(isBalanced("()"));
    }

    @Test
    public void testNestedBalancedStrings() {
        assertTrue(isBalanced("{[()]}"));
        assertTrue(isBalanced("({})"));
    }

    @Test
    public void testMixedBalancedStrings() {
        assertTrue(isBalanced("{[()()]}"));
        assertTrue(isBalanced("{[][]}({})"));
    }

    @Test
    public void testUnbalancedStrings() {
        assertFalse(isBalanced("]["));
        assertFalse(isBalanced("([)]"));
        assertFalse(isBalanced("{"));
        assertFalse(isBalanced("[{"));
        assertFalse(isBalanced("[)"));
    }

}