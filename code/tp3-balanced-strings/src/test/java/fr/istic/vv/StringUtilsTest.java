package fr.istic.vv;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    
    @Test
    public void testEmptyString() {
        // Test for the empty string (should be balanced)
        assertTrue(StringUtils.isBalanced(""));
    }

    @Test
    public void testSingleSymbol() {
        // Test for single symbol input (should not be balanced)
        assertFalse(StringUtils.isBalanced("{"));
        assertFalse(StringUtils.isBalanced("}"));
        assertFalse(StringUtils.isBalanced("["));
        assertFalse(StringUtils.isBalanced("]"));
        assertFalse(StringUtils.isBalanced("("));
        assertFalse(StringUtils.isBalanced(")"));
    }

    @Test
    public void testExtraClosingSymbol() {
        // Test for strings with extra closing symbols (should not be balanced)
        assertFalse(StringUtils.isBalanced("{}}"));
        assertFalse(StringUtils.isBalanced("{}[()])"));
        assertFalse(StringUtils.isBalanced("{}(())]"));
    }

    @Test
    public void testExtraOpeningSymbol() {
        // Test for strings with extra opening symbols (should not be balanced)
        assertFalse(StringUtils.isBalanced("{()()"));
        assertFalse(StringUtils.isBalanced("({()}"));
        assertFalse(StringUtils.isBalanced("[()()()()"));
    }

    @Test
    public void testMismatchedSymbols() {
        // Test for mismatched pairs (should not be balanced)
        assertFalse(StringUtils.isBalanced("{)"));
        assertFalse(StringUtils.isBalanced("[}"));
        assertFalse(StringUtils.isBalanced("[(])"));
        assertFalse(StringUtils.isBalanced("({[})]"));
    }

    @Test
    public void testBalancedSymbols() {
        // Test for properly balanced strings with symbols only (should be balanced)
        assertTrue(StringUtils.isBalanced("{[]()}"));
        assertTrue(StringUtils.isBalanced("([()])"));
        assertTrue(StringUtils.isBalanced("[()]"));
        assertTrue(StringUtils.isBalanced("({})"));
    }

    @Test
    public void testStringUtilsWithOtherCharacters() {
        // Test for balanced strings with other characters in between (should be balanced)
        assertTrue(StringUtils.isBalanced("{a + b}"));
        assertTrue(StringUtils.isBalanced("[1, 2, 3]"));
        assertTrue(StringUtils.isBalanced("a(b+c)"));
        assertTrue(StringUtils.isBalanced("x(y(z))"));
    }

    @Test
    public void testComplexStringUtils() {
        // Test for complex balanced string (should be balanced)
        assertTrue(StringUtils.isBalanced("{[({[]})]}"));
        assertTrue(StringUtils.isBalanced("[(a+b) * (c-d)]"));
    }

    @Test
    public void testComplexUnbalancedString() {
        // Test for complex unbalanced string (should not be balanced)
        assertFalse(StringUtils.isBalanced("{[a+b]}]"));
        assertFalse(StringUtils.isBalanced("([a+b]{apdspfksdpf([)k})"));
    }
}