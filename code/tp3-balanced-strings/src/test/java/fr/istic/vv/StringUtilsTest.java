package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {

    /**
     * tests isBalanced against empty string
     */
    @Test
    void testIsBalancedEmptyString() {
        String input = "";
        assertTrue(StringUtils.isBalanced(input));
    }

    /**
     * tests isBalanced against null
     */
    @Test
    void testIsBalancedNullString() {
        String input = null;
        assertTrue(StringUtils.isBalanced(input));
    }

    /**
     * tests isBalanced with valid simple string
     */
    @Test
    void testIsBalancedNominal() {
        String input = "{{}()[()]}";
        assertTrue(StringUtils.isBalanced(input));
    }

    /**
     * tests isBalanced with valid simple string with other characters
     */
    @Test
    void testIsBalancedNominalWithNoise() {
        String input = "{a{.}/(0)[(cc)^]*}";
        assertTrue(StringUtils.isBalanced(input));
    }

    /**
     * tests isBalanced with missing }
     */
    @Test
    void testIsBalancedMissingClosingCurlyBracket() {
        String input = "{a{./(0)[(cc)^]*}";
        assertFalse(StringUtils.isBalanced(input));
    }

    /**
     * tests isBalanced with missing ]
     */
    @Test
    void testIsBalancedMissingClosingSquareBracket() {
        String input = "{a{.}/(0)[(cc)^*}";
        assertFalse(StringUtils.isBalanced(input));
    }

    /**
     * tests isBalanced with missing )
     */
    @Test
    void testIsBalancedMissingClosingParenthesis() {
        String input = "{a{.}/(0[(cc)^]*}";
        assertFalse(StringUtils.isBalanced(input));
    }

    /**
     * tests isBalanced with missing {
     */
    @Test
    void testIsBalancedMissingOpeningCurlyBracket(){
        String input = "{a.}/(0)[(cc)^]*}";
        assertFalse(StringUtils.isBalanced(input));
    }

    /**
     * tests isBalanced with missing [
     */
    @Test
    void testIsBalancedMissingOpeningSquareBracket(){
        String input = "{a{.}/(0)(cc)^]*}";
        assertFalse(StringUtils.isBalanced(input));
    }

    /**
     * tests isBalanced with missing (
     */
    @Test
    void testIsBalancedMissingOpeningParenthesis(){
        String input = "{a{.}/0)[(cc)^]*}";
        assertFalse(StringUtils.isBalanced(input));
    }

    /**
     * tests isBalanced with overlapping brackets
     */
    @Test
    void testIsBalancedOverlapping(){
        String input = "{(})";
        assertFalse(StringUtils.isBalanced(input));
    }
}
