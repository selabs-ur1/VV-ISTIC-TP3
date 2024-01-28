package fr.istic.vv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    public void testEmptyString() {
        assertTrue(StringUtils.isBalanced(""));
    }

    @Test
    public void testNonGroupingCharacters() {
        assertTrue(StringUtils.isBalanced("abcde12345!@#"));
    }

    @Test
    public void testOnlyOpenings() {
        assertFalse(StringUtils.isBalanced("{[("));
    }

    @Test
    public void testOnlyClosings() {
        assertFalse(StringUtils.isBalanced(")]}"));
    }

    @Test void TestNullThrowError() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            StringUtils.isBalanced(null);
        });
    }

    @Test
    public void testMixedBalance1() {
        assertTrue(StringUtils.isBalanced("{[][]}({})"));
    }

    @Test
    public void testMixedBalance2() {
        assertFalse(StringUtils.isBalanced("][,"));
    }

    @Test
    public void testMixedBalance3() {
        assertFalse(StringUtils.isBalanced("([)],"));
    }

    @Test
    public void testMixedBalance4() {
        assertFalse(StringUtils.isBalanced("{"));
    }

    @Test
    public void testMixedBalance5() {
        assertFalse(StringUtils.isBalanced("{(}{)"));
    }
}