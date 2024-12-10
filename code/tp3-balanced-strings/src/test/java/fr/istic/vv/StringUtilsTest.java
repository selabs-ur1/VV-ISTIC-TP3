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
    void testSingleOpenParentheses() {
        assertFalse(isBalanced("("));
    }

    @Test
    void testShortBalancedParentheses() {
        assertTrue(isBalanced("()"));
    }

    @Test
    void testShortUnbalancedParentheses() {
        assertFalse(isBalanced("(]"));
    }

    @Test
    void testShortBalancedBrackets() {
        assertTrue(isBalanced("[]"));
    }

    @Test
    void testShortUnbalancedBrackets() {
        assertFalse(isBalanced("[}"));
    }

    @Test
    void testShortBalancedBraces() {
        assertTrue(isBalanced("{}"));
    }

    @Test
    void testShortUnbalancedBraces() {
        assertFalse(isBalanced("{)"));
    }

    @Test
    void testMediumBalancedMixed() {
        assertTrue(isBalanced("([])"));
    }

    @Test
    void testMediumNotEqualStack() {
        assertFalse(isBalanced("()]"));
    }

    @Test
    void testMediumUnbalancedMixed() {
        assertFalse(isBalanced("([)]"));
    }

    @Test
    void testMediumBalancedComplex() {
        assertTrue(isBalanced("{[()]}"));
    }

    @Test
    void testMediumUnbalancedComplex() {
        assertFalse(isBalanced("{[(])}"));
    }

    @Test
    void testMediumBalancedWithNonRelevantChars() {
        assertTrue(isBalanced("a(b)c"));
    }

    @Test
    void testMediumUnbalancedWithNonRelevantChars() {
        assertFalse(isBalanced("a(b]c"));
    }

    @Test
    void testLongBalancedComplex() {
        assertTrue(isBalanced("({[({[({[({[()]})]})]})]})"));
    }

    @Test
    void testLongUnbalancedComplex() {
        assertFalse(isBalanced("({[({[({[({[()]})]})]})]})}"));
    }

    @Test
    void testLongBalancedWithNonRelevantChars() {
        assertTrue(isBalanced("a{b[c(d)e]f}g"));
    }

    @Test
    void testLongUnbalancedWithNonRelevantChars() {
        assertFalse(isBalanced("a{b[c(d)e]f}g)"));
    }

    @Test
    void testOpenBracketsNotEmptyNotMatchingPair() {
        assertFalse(isBalanced("({]"));
    }

    @Test
    void testSingleCloseParentheses() {
        assertFalse(isBalanced(")"));
    }

    @Test
    void testSingleOpenBracket() {
        assertFalse(isBalanced("["));
    }

    @Test
    void testSingleCloseBracket() {
        assertFalse(isBalanced("]"));
    }

    @Test
    void testSingleOpenBrace() {
        assertFalse(isBalanced("{"));
    }

    @Test
    void testSingleCloseBrace() {
        assertFalse(isBalanced("}"));
    }

    @Test
    void testNestedBalancedParentheses() {
        assertTrue(isBalanced("(())"));
    }

    @Test
    void testNestedUnbalancedParentheses() {
        assertFalse(isBalanced("(()"));
    }

    @Test
    void testNestedBalancedBrackets() {
        assertTrue(isBalanced("[[]]"));
    }

    @Test
    void testNestedUnbalancedBrackets() {
        assertFalse(isBalanced("[[]"));
    }

    @Test
    void testNestedBalancedBraces() {
        assertTrue(isBalanced("{{}}"));
    }

    @Test
    void testNestedUnbalancedBraces() {
        assertFalse(isBalanced("{{}"));
    }

    @Test
    void testMixedBalancedBrackets() {
        assertTrue(isBalanced("{[()]}"));
    }

    @Test
    void testMixedUnbalancedBrackets() {
        assertFalse(isBalanced("{[(])}"));
    }

    @Test
    void testBalancedWithNonBracketCharacters() {
        assertTrue(isBalanced("a{b[c(d)e]f}g"));
    }

    @Test
    void testUnbalancedWithNonBracketCharacters() {
        assertFalse(isBalanced("a{b[c(d)e]f}g)"));
    }
}