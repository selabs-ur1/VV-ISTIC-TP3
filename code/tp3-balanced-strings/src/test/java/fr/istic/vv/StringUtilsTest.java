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
    void testSimpleBalancedCases() {
        assertTrue(isBalanced("{}"));
        assertTrue(isBalanced("[]"));
        assertTrue(isBalanced("()"));
    }

    @Test
    void testComplexBalancedCases() {
        assertTrue(isBalanced("{{}}"));
        assertTrue(isBalanced("[[]]"));
        assertTrue(isBalanced("()()"));
    }

    @Test
    void testUnbalancedCases() {
        assertFalse(isBalanced("{[)]}"));
        assertFalse(isBalanced("[{]}"));
        assertFalse(isBalanced("[["));
        assertFalse(isBalanced(")]"));
    }

    @Test
    void testNestedCases() {
        assertTrue(isBalanced("{[()]}"));
        assertFalse(isBalanced("{[()}]"));
    }
}
