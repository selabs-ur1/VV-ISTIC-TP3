package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void isBalancedNullTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            isBalanced(null);
        });

        assertEquals("String is null", exception.getMessage());
    }

    @Test
    void isBalancedEmptyTest() {
        assertTrue(isBalanced(""));
    }

    @Test
    void isBalancedUnmatchedOpeningSymbolsTest() {
        assertFalse(isBalanced("{"));
    }

    @Test
    void isBalancedUnmatchedClosingSymbolsTest() {
        assertFalse(isBalanced(")"));
    }

    @Test
    void isBalancedSinglePairSymbolsTest() {
        assertTrue(isBalanced("[]"));
    }

    @Test
    void isBalancedMixedSymbolWrongOrderTest() {
        assertFalse(isBalanced("}{"));
    }

    @Test
    void isBalancedUnnestedMixedSymbolCorrectOrderTest() {
        assertTrue(isBalanced("{}()"));
    }

    @Test
    void isBalancedUnnestedMixedSymbolWrongOrderTest() {
        assertFalse(isBalanced("{}]["));
    }

    @Test
    void isBalancedNestedPairSymbolTest() {
        assertTrue(isBalanced("{()}"));
    }

    @Test
    void isBalancedNestedUnbalancedTest() {
        assertFalse(isBalanced("{)("));
    }

    @Test
    void isBalancedUnbalancedParenthesesTest() {
        assertFalse(isBalanced("}()"));
    }

    @Test
    void isBalancedUnbalancedSquareBracketsTest() {
        assertFalse(isBalanced("{]["));
    }
}
