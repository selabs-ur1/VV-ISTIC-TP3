package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

@Test
    void Test1() {
        assertTrue(StringUtils.isBalanced("()"));
        assertTrue(StringUtils.isBalanced("[]"));
        assertTrue(StringUtils.isBalanced("{}"));

        assertTrue(StringUtils.isBalanced("([{}])"));
        assertTrue(StringUtils.isBalanced("({})"));

        assertTrue(StringUtils.isBalanced("()[]{}"));

        assertFalse(StringUtils.isBalanced("([)]"));
        assertFalse(StringUtils.isBalanced("{})"));

        // added for Q2
        assertTrue(StringUtils.isBalanced("(test[work])[other]{and(another[one])}"));

        // after PIT check

        assertFalse(StringUtils.isBalanced("{}([)]"));
        assertFalse(StringUtils.isBalanced("(test])[other]{and({another[one])}"));

    }
}