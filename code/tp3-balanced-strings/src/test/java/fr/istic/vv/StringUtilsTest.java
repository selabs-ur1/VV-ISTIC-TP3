package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    public void testIsBalancedWithBalancedAndDepth() {
        assertTrue(StringUtils.isBalanced("{[()]}"));
    }

    @Test
    public void testIsBalancedWithUnbalancedAndDepth() {
        assertFalse(StringUtils.isBalanced("(}{)"));
    }

    @Test
    public void testIsBalancedWithBalancedPairOfBrace() {
        assertTrue(StringUtils.isBalanced("{}"));
    }

    @Test
    public void testIsBalancedWithUnbalancedPairOfBrace() {
        assertFalse(StringUtils.isBalanced("}{"));
    }

    @Test
    public void testIsBalancedWithBalancedPairOfBracket() {
        assertTrue(StringUtils.isBalanced("[]"));
    }

    @Test
    public void testIsBalancedWithUnbalancedPairOfBracket() {
        assertFalse(StringUtils.isBalanced("]["));
    }

    @Test
    public void testIsBalancedWithBalancedPairOfParenthese() {
        assertTrue(StringUtils.isBalanced("()"));
    }

    @Test
    public void testIsBalancedWithUnbalancedPairOfParenthese() {
        assertFalse(StringUtils.isBalanced(")("));
    }

    @Test
    public void testIsBalancedWithNoDepthChecked() {
        assertFalse(StringUtils.isBalanced("{(}{})"));
    }

    @Test
    public void testIsBalancedWithClosingSymbolFirst() {
        assertFalse(StringUtils.isBalanced("}(){"));
    }

    @Test
    public void testIsBalancedWithOnlyOpening() {
        assertFalse(StringUtils.isBalanced("{[("));
    }

    @Test
    public void testIsBalancedWithEmptyString() {
        assertTrue(StringUtils.isBalanced(""));
    }

    @Test
    public void testIsBalancedWithBalancedCompleteString() {
        assertTrue(StringUtils.isBalanced("toto(titi){[tutu]tata}"));
    }

    @Test
    public void testIsBalancedWithUnbalancedCompleteString() {
        assertFalse(StringUtils.isBalanced("toto(titi){[tututata}]"));
    }


}