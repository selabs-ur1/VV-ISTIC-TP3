package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testIsBalancedEmptyString() {
        assertTrue(isBalanced(""));
    }

    @Test
    void testIsBalancedNullString() {
        assertThrows(NullPointerException.class, () -> isBalanced(null));
    }

    @Test
    void testIsBalancedSingleOpeningBracket() {
        assertFalse(isBalanced("("));
    }

    @Test
    void testIsBalancedSingleClosingBracket() {
        assertFalse(isBalanced(")"));
    }

    @Test
    void testIsBalancedSingleOpeningCurlyBracket() {
        assertFalse(isBalanced("{"));
    }

    @Test
    void testIsBalancedSingleClosingCurlyBracket() {
        assertFalse(isBalanced("}"));
    }

    @Test
    void testIsBalancedSingleOpeningSquareBracket() {
        assertFalse(isBalanced("["));
    }

    @Test
    void testIsBalancedSingleClosingSquareBracket() {
        assertFalse(isBalanced("]"));
    }

    @Test
    void testIsBalancedNormalBrackets() {
        assertTrue(isBalanced("()"));
    }

    @Test
    void testIsBalancedNormalCurlyBrackets() {
        assertTrue(isBalanced("{}"));
    }

    @Test
    void testIsBalancedNormalSquareBrackets() {
        assertTrue(isBalanced("[]"));
    }

    @Test
    void testIsBalancedMultipleChar() {
        assertTrue(isBalanced("()(){[()]}"));
    }
}