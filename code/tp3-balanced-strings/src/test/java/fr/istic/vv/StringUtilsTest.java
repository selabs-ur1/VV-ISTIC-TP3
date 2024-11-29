package fr.istic.vv;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @Test
    void testEmptyString() {
        assertTrue(StringUtils.isBalanced(""));
    }

    @Test
    void testSingleOpeningSymbol() {
        assertFalse(StringUtils.isBalanced("{"));
    }

    @Test
    void testSingleClosingSymbol() {
        assertFalse(StringUtils.isBalanced(")"));
    }

    @Test
    void testNoSymbols() {
        assertTrue(StringUtils.isBalanced("abc123"));
    }

    @Test
    void testSimpleBalancedPair() {
        assertTrue(StringUtils.isBalanced("()"));
    }

    @Test
    void testBalancedWithOtherCharacters() {
        assertTrue(StringUtils.isBalanced("a(b)c"));
    }

    @Test
    void testClosingBeforeOpening() {
        assertFalse(StringUtils.isBalanced(")("));
    }

    @Test
    void testTooManyOpenings() {
        assertFalse(StringUtils.isBalanced("((("));
    }

    @Test
    void testIncorrectOrder() {
        assertFalse(StringUtils.isBalanced("([)]"));
    }

    @Test
    void testSimpleNestedBalanced() {
        assertTrue(StringUtils.isBalanced("({})"));
    }

    @Test
    void testSimpleNestedUnbalanced() {
        assertFalse(StringUtils.isBalanced("({)"));
    }

    @Test
    void testDeeplyNestedBalanced() {
        assertTrue(StringUtils.isBalanced("({[()]})"));
    }

    @Test
    void testDeeplyNestedUnbalanced() {
        assertFalse(StringUtils.isBalanced("({[()]}{"));
    }
}