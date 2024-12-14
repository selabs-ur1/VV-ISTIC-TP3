package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;


public class StringUtilsTest {

    @Test
    public void testEmptyString() {
        assertTrue(StringUtils.isBalanced(""));
    }

    @Test
    public void testNoSymbols() {
        assertTrue(StringUtils.isBalanced("abc"));
    }

    @Test
    public void testSingleGroupingSymbol() {
        assertFalse(StringUtils.isBalanced("{"));
        assertFalse(StringUtils.isBalanced(")"));
        assertFalse(StringUtils.isBalanced("["));
    }

    @Test
    public void testSimpleBalanced() {
        assertTrue(StringUtils.isBalanced("{}"));
        assertTrue(StringUtils.isBalanced("[]"));
        assertTrue(StringUtils.isBalanced("()"));
    }

    @Test
    public void testNestedBalanced() {
        assertTrue(StringUtils.isBalanced("{[()]}"));
        assertTrue(StringUtils.isBalanced("{{[]}}([])"));
    }

    @Test
    public void testConsecutiveBalanced() {
        assertTrue(StringUtils.isBalanced("{}[]"));
        assertTrue(StringUtils.isBalanced("()(){}"));
    }

    @Test
    public void testUnbalancedIncorrectOrder() {
        assertFalse(StringUtils.isBalanced("{]"));
        assertFalse(StringUtils.isBalanced("[)"));
        assertFalse(StringUtils.isBalanced("}{"));
    }

    @Test
    public void testUnbalancedMissingPairs() {
        assertFalse(StringUtils.isBalanced("{["));
        assertFalse(StringUtils.isBalanced("}"));
        assertFalse(StringUtils.isBalanced("["));
    }
}