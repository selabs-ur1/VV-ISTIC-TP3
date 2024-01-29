package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testIsBalancedEmpty() {
        assertTrue(StringUtils.isBalanced(""));
    }

    @Test
    void testIsBalancedOddNumberOfChar() {
        assertFalse(StringUtils.isBalanced("{}}"));
    }

    @Test
    void testIsBalancedNotAllowedChar() {
        assertFalse(StringUtils.isBalanced("a"));
    }

    @Test
    void testIsBalancedFirstCharClosing() {
        assertFalse(StringUtils.isBalanced("][[]"));
    }

    @Test
    void testIsBalancedLastCharOpening() {
        assertFalse(StringUtils.isBalanced("())("));
    }

    @Test
    void testIsBalancedFirstCharClosingAndLastCharOpening() {
        assertFalse(StringUtils.isBalanced(")("));
    }

    @Test
    void testIsBalancedValid1() {
        assertTrue(StringUtils.isBalanced("()"));
    }

    @Test
    void testIsBalancedValid2() {
        assertTrue(StringUtils.isBalanced("[{}({})]"));
    }

    @Test
    void testIsBalancedValid3() {
        assertTrue(StringUtils.isBalanced("[{}]()"));
    }

    @Test
    void testIsBalancedNotValid() {
        assertFalse(StringUtils.isBalanced("[{]}()"));
    }

    @Test
    void testBalancingChar1() {
        assertEquals(StringUtils.getBalancingChar('('),')');
    }

    @Test
    void testBalancingChar2() {
        assertEquals(StringUtils.getBalancingChar(')'),'(');
    }

    @Test
    void testBalancingChar3() {
        assertEquals(StringUtils.getBalancingChar('['),']');
    }

    @Test
    void testBalancingChar4() {
        assertEquals(StringUtils.getBalancingChar(']'),'[');
    }

    @Test
    void testBalancingChar5() {
        assertEquals(StringUtils.getBalancingChar('{'),'}');
    }

    @Test
    void testBalancingChar6() {
        assertEquals(StringUtils.getBalancingChar('}'),'{');
    }


    @Test
    void testBalancingChar7() {
        assertThrows(IllegalArgumentException.class, () -> StringUtils.getBalancingChar('a'));
    }
}
