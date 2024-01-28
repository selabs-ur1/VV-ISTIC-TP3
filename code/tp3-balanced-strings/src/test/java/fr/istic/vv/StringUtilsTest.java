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
    void testBalancedString() {
        assertTrue(isBalanced("{[][]}({})"));
        assertTrue(isBalanced("{[()]}"));
    }

    @Test
    void testUnbalancedString() {
        assertFalse(isBalanced("][, ([)], {, {(}{}"));
        assertFalse(isBalanced("({)}"));
    }

    @Test
    void testMixedStrings() {
        assertTrue(isBalanced("{[()]}"));
        assertFalse(isBalanced("][, ([)], {, {(}{}"));
    }
}