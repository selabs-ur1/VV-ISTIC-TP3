 package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testIsNullParam() {
        assertThrows(NullPointerException.class, () -> isBalanced(null));
    }

    @Test
    void testIsBalanced() {
        assertTrue(isBalanced(""));
    }
    @Test
    void testIsBalancedSimple() {
        assertTrue(isBalanced("()"));
    }
    @Test
    void testIsBalancedSimple2() {
        assertTrue(isBalanced("aaa()"));
    }
    @Test
    void testIsBalancedSimple3() {
        assertTrue(isBalanced("(aaaa)"));
    }
    @Test
    void testIsBalancedSimple4() {
        assertTrue(isBalanced("()aaa"));
    }
    @Test
    void testIsBalancedSimple5() {
        assertTrue(isBalanced("aaa(aaa)aaa"));
    }

    @Test
    void testIsBalancedSimple6() {
        assertTrue(isBalanced("{[]}"));
    }

    @Test
    void testIsBalancedSimple7() {
        assertTrue(isBalanced("[{()}]"));
    }

    @Test
    void testIsBalancedText() {
        assertTrue(isBalanced("AHAHAH"));
    }

    @Test
    void testIsNotBalanced() {
        assertFalse(isBalanced("("));
    }

    @Test
    void testIsNotBalanced2() {
        assertFalse(isBalanced("(()())))"));
    }

    @Test
    void testIsNotBalanced3() {
        assertFalse(isBalanced("(())())"));
    }

    @Test
    void testIsNotBalanced4() {
        assertFalse(isBalanced("["));
    }

    @Test
    void testIsNotBalanced5() {
        assertFalse(isBalanced("{}]"));
    }

    @Test
    void testIsNotBalanced6() {
        assertFalse(isBalanced("{[}]"));
    }

    @Test
    void testIsNotBalanced7() {
        assertFalse(isBalanced("([)]"));
    }

    @Test
    void testIsNotBalanced8() {
        assertFalse(isBalanced("[(])"));
    }

}
