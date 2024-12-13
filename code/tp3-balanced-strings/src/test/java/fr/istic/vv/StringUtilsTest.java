package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> isBalanced(null));
    }

    @Test
    void testEmptyString() {
        assertTrue(isBalanced(""));
    }

    @Test
    void testSingleBracket() {
        assertFalse(isBalanced("("));
        assertFalse(isBalanced(")"));
        assertFalse(isBalanced("{"));
        assertFalse(isBalanced("}"));
        assertFalse(isBalanced("["));
        assertFalse(isBalanced("]"));
    }

    @Test
    void testBalancedBrackets() {
        assertTrue(isBalanced("()"));
        assertTrue(isBalanced("{}"));
        assertTrue(isBalanced("[]"));
        assertTrue(isBalanced("({[]})"));
        assertTrue(isBalanced("({[()]})"));
    }

    @Test
    void testUnbalancedBrackets() {
        assertFalse(isBalanced("(]"));
        assertFalse(isBalanced("({[})]"));
        assertFalse(isBalanced("({[)]}"));
        assertFalse(isBalanced("({[}])"));
    }

    @Test
    void testNonBracketCharacters() {
        assertTrue(isBalanced("abc"));
        assertTrue(isBalanced("123"));
        assertTrue(isBalanced("abc123"));
    }

    @Test
    void testMixedCharacters() {
        assertTrue(isBalanced("(a[b{c}d]e)"));
        assertFalse(isBalanced("(a[b{c}d]e"));
        assertFalse(isBalanced("(a[b{c}d]e))"));
    }

    @Test
    void testMultipleSameTypeBrackets() {
        assertTrue(isBalanced("((()))"));
        assertTrue(isBalanced("{{}}"));
        assertTrue(isBalanced("[[[]]]"));
    }

    @Test
    void testNestedBrackets() {
        assertTrue(isBalanced("{[()]}"));
        assertTrue(isBalanced("[{()}]"));
        assertFalse(isBalanced("{[(])}"));
    }

    @Test
    void testWithSpaces() {
        assertTrue(isBalanced(" ( ) "));
        assertTrue(isBalanced(" { } "));
        assertTrue(isBalanced(" [ ] "));
        assertTrue(isBalanced(" ( { [ ] } ) "));
        assertFalse(isBalanced(" ( { [ ] } "));
        assertFalse(isBalanced(" ( { [ ] } ) ) "));
    }
}