package fr.istic.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    /** Test on isBalanced function.
     *  Init : String is empty.
     *  Expected : isBalanced return true.*/
    @Test
    @DisplayName("Test corrects strings on isBalanced function")
    public void testIsBalancedCorrectStringsEmpty() {
        assertTrue(StringUtils.isBalanced(""));
    }

    /** Test on isBalanced function.
     *  Init : String with only various brackets.
     *  Expected : isBalanced return true.*/
    @Test
    @DisplayName("Test corrects strings on isBalanced function")
    public void testIsBalancedCorrectStringsBracket() {
        assertTrue(StringUtils.isBalanced("{[][]}({})"));
    }

    /** Test on isBalanced function.
     *  Init : String with only alphabetic values.
     *  Expected : isBalanced return true.*/
    @Test
    @DisplayName("Test corrects strings with alphabetic value on isBalanced function")
    public void testIsBalancedCorrectStringsAlphabetic() {
        assertTrue(StringUtils.isBalanced("test"));
    }

    /** Test on isBalanced function.
     *  Init : String with only alphabetic values.
     *  Expected : isBalanced return true.*/
    @Test
    @DisplayName("Test corrects strings with alphabetic value on isBalanced function")
    public void testIsBalancedCorrectStringsMixBracketAlphabetic() {
        assertTrue(StringUtils.isBalanced("(t[]e{s}t)"));
    }

    /** Test on isBalanced function.
     *  Init : String with only alphabetic values.
     *  Expected : isBalanced return true.*/
    @Test
    @DisplayName("Test corrects strings with () bracket value on isBalanced function")
    public void testIsBalancedCorrectStringsOnlyBracket1() {
        assertTrue(StringUtils.isBalanced("()"));
    }

    /** Test on isBalanced function.
     *  Init : String with only alphabetic values.
     *  Expected : isBalanced return true.*/
    @Test
    @DisplayName("Test corrects strings with {} bracket value on isBalanced function")
    public void testIsBalancedCorrectStringsOnlyBracket2() {
        assertTrue(StringUtils.isBalanced("{}"));
    }

    /** Test on isBalanced function.
     *  Init : String with only alphabetic values.
     *  Expected : isBalanced return true.*/
    @Test
    @DisplayName("Test corrects strings with [] bracket value on isBalanced function")
    public void testIsBalancedCorrectStringsOnlyBracket3() {
        assertTrue(StringUtils.isBalanced("[]"));
    }

    /** Test on isBalanced functions.
     *  Init : Wrong string with brackets which are not double and balanced.
     *  Expected : isBalanced return false.*/
    @Test
    @DisplayName("Test wrong string on isBalanced function with brackets which are not in pair and balanced")
    public void testIsBalancedWrongStringsNotPairBracket() {
        assertFalse(StringUtils.isBalanced("{(}{}"));
    }

    /** Test on isBalanced functions.
     *  Init : one wrong string with alphabetic value and mix brackets.
     *  Expected : isBalanced return false.*/
    @Test
    @DisplayName("Test wrongs strings on isBalanced function with alphabetic value and mix brackets")
    public void testIsBalancedWrongStringsBracketAlphabetic() {
        assertFalse(StringUtils.isBalanced("{(}ar)tr{}"));
        assertFalse(StringUtils.isBalanced("{(}){}"));
    }

    /** Test on isBalanced functions.
     *  Init : Wrong string with brackets which are not double and balanced.
     *  Expected : isBalanced return false.*/
    @Test
    @DisplayName("Test wrong string on isBalanced function with brackets which are in pair and balanced")
    public void testIsBalancedWrongStringsPairBracket() {
        assertFalse(StringUtils.isBalanced("{(}{}"));
    }

    @Test
    public void testIsBalancedException() throws NullPointerException {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            StringUtils.isBalanced(null);
        });
    }
}