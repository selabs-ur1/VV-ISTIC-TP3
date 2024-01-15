package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    @Test
    public void testIsBalanced() {
        assertTrue(isBalanced("{"));
        assertTrue(isBalanced("["));
        assertTrue(isBalanced("("));

        assertFalse(isBalanced("}"));
        assertFalse(isBalanced("]"));
        assertFalse(isBalanced(")"));

        assertTrue(isBalanced("{}"));
        assertTrue(isBalanced("[]"));
        assertTrue(isBalanced("()"));

        assertFalse(isBalanced("{]"));
        assertFalse(isBalanced("[)"));
        assertFalse(isBalanced("(}"));
        assertFalse(isBalanced("[}"));
        assertFalse(isBalanced("(]"));
        assertFalse(isBalanced("{)"));

        assertTrue(isBalanced("{abc}"));
        assertTrue(isBalanced("[def]"));
        assertTrue(isBalanced("(ghi)"));

        assertTrue(isBalanced(""));
    }
}
