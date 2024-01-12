package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    @Test
    void testStringIsNull() {
        assertFalse(isBalanced(null));
    }

    @Test
    void testStringIsEmpty() {
        assertTrue(isBalanced(""));
    }

    @Test
    void testStringAccolades() {
        assertFalse(isBalanced("{"));
    }

    @Test
    void testStringCrochets() {
        assertFalse(isBalanced("["));
    }

    @Test
    void testStringIsPairWithNested() {
        assertTrue(isBalanced("([])"));
    }

    @Test
    void testStringWithNested1() {
        assertFalse(isBalanced("([)"));
    }

    @Test
    void testStringWithNested2() {
        assertFalse(isBalanced("((]"));
    }

    @Test
    void testStringWithNested3() {
        assertFalse(isBalanced("([}"));
    }

    @Test
    void testStringWithNested4() {
        assertFalse(isBalanced("azerty"));
    }

    @Test
    void testStringIsPair() {
        assertTrue(isBalanced("()[]"));
    }

    @Test
    void testStringIsPairWithNestedAndUnion() {
        assertTrue(isBalanced("()" + "[{}]"));
    }

    @Test
    void testStringContainsOtherChar() {
        assertFalse(isBalanced("mot"));
    }
}