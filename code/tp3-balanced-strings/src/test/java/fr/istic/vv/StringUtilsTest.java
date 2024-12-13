package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testEmptyStringIsBalanced() {
        assertTrue(isBalanced(""));
    }

    @Test
    void testSingleOpenOrCloseCharIsNotBalanced() {
        assertFalse(isBalanced("("));
        assertFalse(isBalanced("["));
        assertFalse(isBalanced("{"));
        assertFalse(isBalanced(")"));
        assertFalse(isBalanced("]"));
        assertFalse(isBalanced("}"));
    }

    @Test
    void testPairInTheCorrectOrderIsBalanced() {
        assertTrue(isBalanced("()"));
        assertTrue(isBalanced("[]"));
        assertTrue(isBalanced("{}"));
    }

    @Test
    void testPairInTheWrongOrderIsNotBalanced() {
        assertFalse(isBalanced(")("));
        assertFalse(isBalanced("]["));
        assertFalse(isBalanced("}{"));
    }

    @Test
    void testMultiplePairsInTheCorrectOrderAreBalanced() {
        assertTrue(isBalanced("()[]{}"));
        assertTrue(isBalanced("()[](){}"));
        assertTrue(isBalanced("()()[](){}"));
    }

    @Test
    void testMultiplePairsInTheWrongOrderAreNotBalanced() {
        assertFalse(isBalanced("([)]"));
    }

    @Test
    void testMultiplePairsWithNestedPairsAreBalanced() {
        assertTrue(isBalanced("([]{})"));
        assertTrue(isBalanced("{([])}"));
    }

    @Test
    void testMultiplePairsWithNestedPairsInTheWrongOrderAreNotBalanced() {
        assertFalse(isBalanced("([){}]"));
        assertFalse(isBalanced("{(}{}"));
    }

    @Test
    void testOpenCharWithoutClosingCharIsNotBalanced() {
        assertFalse(isBalanced("({}]"));
    }

    @Test
    void testSubstringIsNotBalanced() {
        assertFalse(isBalanced("({])"));
    }
}