package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    // Test cases
    static String NULL = null;
    static String EMPTY = "";
    static String UNEQUAL = "(([{}]]])";
    static String EQUAL_BALANCED = "([{}])"; // covers EQUAL_CONTAINS_BALANCED, EQUAL_STARTS_WITH_OPENING, EQUAL_ENDS_WITH_CLOSING
    static String EQUAL_UNBALANCED = "([{}(]))"; // covers EQUAL_CONTAINS_UNBALANCED
    static String EQUAL_STARTS_WITH_CLOSING = ")({[]}"; // covers SEQUENCE_STARTS_WITH_CLOSING
    static String EQUAL_ENDS_WITH_OPENING = "[]{})("; // covers SEQUENCE_ENDS_WITH_OPENING
    static String SEQUENCE_BALANCED = "([{}])({[]})"; // covers SEQUENCE_CONTAINS_BALANCED, SEQUENCE_STARTS_WITH_OPENING, SEQUENCE_ENDS_WITH_CLOSING
    static String SEQUENCE_UNBALANCED = "()(][)"; // covers SEQUENCE_CONTAINS_UNBALANCED

    @Test
    void testStringEmpty() {
        assertTrue(isBalanced(EMPTY));
    }

    @Test
    void testStringNull() {
        assertThrows(IllegalArgumentException.class, () -> isBalanced(NULL));
    }

    @Test
    void testUnequal() {
        assertFalse(isBalanced(UNEQUAL));
    }

    @Test
    void testEqualBalanced() {
        assertTrue(isBalanced(EQUAL_BALANCED));
    }

    @Test
    void testEqualUnbalanced() {
        assertFalse(isBalanced(EQUAL_UNBALANCED));
    }

    @Test
    void testEqualStartsWithClosing() {
        assertFalse(isBalanced(EQUAL_STARTS_WITH_CLOSING));
    }

    @Test
    void testEqualEndsWithOpening() {
        assertFalse(isBalanced(EQUAL_ENDS_WITH_OPENING));
    }

    @Test
    void testSequenceBalanced() {
        assertTrue(isBalanced(SEQUENCE_BALANCED));
    }

    @Test
    void testSequenceUnbalanced() {
        assertFalse(isBalanced(SEQUENCE_UNBALANCED));
    }
}