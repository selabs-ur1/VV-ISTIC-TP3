package fr.istic.vv;

import fr.istic.vv.StringUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testBalancedString() {
        assertTrue(StringUtils.isBalanced("{[][]}({})"));
    }

    @Test
    void testUnbalancedString1() {
        assertFalse(StringUtils.isBalanced("]["));
    }

    @Test
    void testUnbalancedString2() {
        assertFalse(StringUtils.isBalanced("([)]"));
    }

    @Test
    void testUnbalancedString3() {
        assertFalse(StringUtils.isBalanced("{"));
    }

    @Test
    void testUnbalancedString4() {
        assertFalse(StringUtils.isBalanced("{(}{})"));
    }

    @Test
    void testEmptyString() {
        assertTrue(StringUtils.isBalanced(""));
    }

    @Test
    void testOnlyOpenSymbols() {
        assertFalse(StringUtils.isBalanced("{[("));
    }

    @Test
    void testOnlyCloseSymbols() {
        assertFalse(StringUtils.isBalanced(")]}"));
    }
}
