package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    public void testEmptyString() {
        assertTrue(isBalanced(""));
    }
    @Test
    public void testSimpleBalancedStringsWithOneSymbol() {
        assertTrue(isBalanced("()"));
    }

    @Test
    public void testSimpleBalancedStringsWithTwoSymbols() {
        assertTrue(isBalanced("()[]"));
    }

    @Test
    public void testSimpleBalancedStringsWithThreeSymbols() {
        assertTrue(isBalanced("()[]"));
    }

    @Test
    public void testOneNestedBalancedStrings() {
        assertTrue(isBalanced("([])"));
    }

    @Test
    public void testTwoNestedBalancedStrings() {
        assertTrue(isBalanced("([{}])"));
    }

    @Test
    public void testMixedBalancedString() {
        assertTrue(isBalanced("y(ax[ {pm} uj h]g )hf"));
    }

    @Test
    public void testUnmixedBalancedString() {
        assertTrue(isBalanced("yazehf"));
    }

    @Test
    public void testUnbalancedOpeningOnly() {
        assertFalse(isBalanced("("));
    }

    @Test
    public void testUnbalancedOpeningOnlyEnd() {
        assertFalse(isBalanced("()("));
    }

    @Test
    public void testUnbalancedOpeningOnlyStart() {
        assertFalse(isBalanced("(()"));
    }

    @Test
    public void testUnbalancedClosingOnlyEnd() {
        assertFalse(isBalanced("()]"));
    }

    @Test
    public void testUnbalancedClosingOnlyStart() {
        assertFalse(isBalanced("]()"));
    }

    @Test
    public void testNestedUnbalancedOpeningOnly() {
        assertFalse(isBalanced("{({)}"));
    }

    @Test
    public void testNestedUnbalancedClosingOnly() {
        assertFalse(isBalanced("{(})}"));
    }

    @Test
    public void testUnbalancedDifferentClosing() {
        assertFalse(isBalanced("{((})}"));
    }

    @Test
    public void testUnbalancedMixedSymbols() {
        assertFalse(isBalanced("h{fsdf (fs}  d)s}"));
    }
}