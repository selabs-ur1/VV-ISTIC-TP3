package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    public void testNull() {
        assertTrue(isBalanced(null));
    }

    @Test
    public void testEmptyString() {
        assertTrue(isBalanced(""));
    }

    @Test
    public void testValidBalancedString1() {
        assertTrue(isBalanced("{[]}"));
    }

    @Test
    public void testValidBalancedString2() {
        assertTrue(isBalanced("()"));
    }

    @Test
    public void testValidBalancedString3() {
        assertTrue(isBalanced("({})"));
    }

    @Test
    public void testInvalidStringWithExtraClosingParenthesis() {
        assertFalse(isBalanced("}("));
    }

    @Test
    public void testValidBalancedStringWithNestedParentheses() {
        assertTrue(isBalanced("[({})]"));
    }

    @Test
    public void testInvalidStringWithUnmatchedOpeningParenthesis() {
        assertFalse(isBalanced("{("));
    }

    @Test
    public void testInvalidStringWithMisnestedParentheses() {
        assertFalse(isBalanced("([)]"));
    }

    @Test
    public void testInvalidStringWithOnlyOpeningParenthesis() {
        assertFalse(isBalanced("{"));
    }

    @Test
    public void testInvalidStringWithOnlyClosingParentheses() {
        assertFalse(isBalanced("}}"));
    }

    @Test
    public void testValidStringWithText() {
        assertTrue(isBalanced("{He(l)lo}"));
    }

    @Test
    public void testInvalidStringWithExcessClosingParentheses() {
        assertFalse(isBalanced("({[()]}}"));
    }

    @Test
    public void testInvalidStringWithMultipleUnmatchedParentheses() {
        assertFalse(isBalanced("{[}"));
    }

    @Test
    public void testInvalidStringWithAllClosedParentheses() {
        assertFalse(isBalanced(")]}"));
    }

    @Test
    public void testInvalidStringWithAllOpenParentheses() {
        assertFalse(isBalanced("{[("));
    }

    @Test
    public void testValidBalancedStringWithNestedAndSequentialParentheses() {
        assertTrue(isBalanced("{[()]}"));
    }
}
