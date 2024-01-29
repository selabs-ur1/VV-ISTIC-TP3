package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testIsBalancedWithBalancedString() {
        assertTrue(isBalanced("{[()]}"));
        assertTrue(isBalanced("()"));
        assertTrue(isBalanced("{}"));
        assertTrue(isBalanced("[]"));
    }

    @Test
    void testIsBalancedWithUnbalancedString() {
        assertFalse(isBalanced("{[()]())}"));
        assertFalse(isBalanced(")("));
        assertFalse(isBalanced("{[}"));
        assertFalse(isBalanced("[{)]"));
    }

    @Test
    void testIsBalancedWithEmptyString() {
        assertTrue(isBalanced(""));
    }

    @Test
    void testIsBalancedWithNestedSymbols() {
        assertTrue(isBalanced("{[(x  y)]}"));
    }

    @Test
    void testIsBalancedWithExtraOpenSymbols() {
        assertFalse(isBalanced("{[()]("));
    }

}
