package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testEmptyString() {
        assertTrue(isBalanced(""));
    }

    @Test
    void testSingleOpenParenthesis() {
        assertFalse(isBalanced("("));
    }

    @Test
    void testSingleOpenBracket() {
        assertFalse(isBalanced("["));
    }

    @Test
    void testSingleOpenBrace() {
        assertFalse(isBalanced("{"));
    }

    @Test
    void testSingleCloseParenthesis() {
        assertFalse(isBalanced(")"));
    }

    @Test
    void testSingleCloseBracket() {
        assertFalse(isBalanced("]"));
    }

    @Test
    void testSingleCloseBrace() {
        assertFalse(isBalanced("}"));
    }

    @Test
    void testSinglePairParentheses() {
        assertTrue(isBalanced("()"));
    }

    @Test
    void testSinglePairBrackets() {
        assertTrue(isBalanced("[]"));
    }

    @Test
    void testSinglePairBraces() {
        assertTrue(isBalanced("{}"));
    }

    @Test
    void testMultiplePairsParenthesesBracketsBraces() {
        assertTrue(isBalanced("()[]{}"));
    }

    @Test
    void testMultiplePairsNested1() {
        assertTrue(isBalanced("{[()]}"));
    }

    @Test
    void testMultiplePairsNested2() {
        assertTrue(isBalanced("[{()}]"));
    }

    @Test
    void testUnbalancedMismatchedPair1() {
        assertFalse(isBalanced("(]"));
    }

    @Test
    void testUnbalancedMismatchedPair2() {
        assertFalse(isBalanced("([)]"));
    }

    @Test
    void testUnbalancedMismatchedPair3() {
        assertFalse(isBalanced("{[}]"));
    }

    @Test
    void testNestedBalancedParenthesesBracketsBraces() {
        assertTrue(isBalanced("({[]})"));
    }

    @Test
    void testNestedBalancedBracketsParenthesesBraces() {
        assertTrue(isBalanced("[({})]"));
    }

    @Test
    void testUnbalancedNested1() {
        assertFalse(isBalanced("({[})]"));
    }

    @Test
    void testUnbalancedNested2() {
        assertFalse(isBalanced("[({)]}"));
    }

    @Test
    void testUnbalancedNested3() {
        assertFalse(isBalanced("{[(])}"));
    }

    @Test
    void testEvenNumberOfCharsBalanced1() {
        assertTrue(isBalanced("([]{})"));
    }

    @Test
    void testEvenNumberOfCharsBalanced2() {
        assertTrue(isBalanced("{[({})]}"));
    }

    @Test
    void testEvenNumberOfCharsBalanced3() {
        assertTrue(isBalanced("{{}}"));
    }

    @Test
    void testOddNumberOfCharsUnbalanced1() {
        assertFalse(isBalanced("{{}"));
    }

    @Test
    void testOddNumberOfCharsUnbalanced2() {
        assertFalse(isBalanced("{}}"));
    }

    @Test
    void testUnbalancedOpenSymbols1() {
        assertFalse(isBalanced("{)"));
    }

    @Test
    void testUnbalancedOpenSymbols2() {
        assertFalse(isBalanced("{]"));
    }

    @Test
    void testUnbalancedOpenSymbols3() {
        assertFalse(isBalanced("[}"));
    }

    @Test
    void testUnbalancedOpenSymbols4() {
        assertFalse(isBalanced("[)"));
    }

    @Test
    void testUnbalancedOpenSymbols6() {
        assertFalse(isBalanced("(}"));
    }
}