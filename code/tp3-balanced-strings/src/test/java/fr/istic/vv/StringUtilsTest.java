package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    public void testIsBalancedPositive() {
        assertTrue(StringUtils.isBalanced("{[][]}({})"));
    }

    @Test
    public void testIsBalancedNegative() {
        assertFalse(StringUtils.isBalanced("{(}{}"));
        assertFalse(StringUtils.isBalanced("{[}{}"));
        assertFalse(StringUtils.isBalanced("[{]{}"));
        assertFalse(StringUtils.isBalanced("[)"));
        assertFalse(StringUtils.isBalanced("()["));
    }

    @Test
    public void testIsBalancedEmptyString() {
        assertTrue(StringUtils.isBalanced(""));
    }
}