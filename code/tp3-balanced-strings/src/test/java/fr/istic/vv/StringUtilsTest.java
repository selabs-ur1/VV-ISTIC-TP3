package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    //Test avec une string vide
    @Test
    public void testIsBalancedEmptyString() {
        assertTrue(StringUtils.isBalanced(""));
    }

    //Test avec une string contenant "("
    @Test
    public void testIsBalancedOneParenthesisOpen() {
        assertFalse(StringUtils.isBalanced("("));
    }

    //Test avec une string contenant "{}"
    @Test
    public void testIsBalancedParenthesisBalanced() {
        assertTrue(StringUtils.isBalanced("{}"));
    }

    //Test avec une string contenant "{(}"
    @Test
    public void testIsBalancedParenthesisNotBalanced() {
        assertFalse(StringUtils.isBalanced("{(}"));
    }

    //Test avec une string contenant "{(})"
    @Test
    public void testIsBalancedParenthesisCroise() {
        assertFalse(StringUtils.isBalanced("{(})"));
    }

    //Test avec une string contenant "{()}"
    @Test
    public void testIsBalancedParenthesisNotCroise() {
        assertTrue(StringUtils.isBalanced("{()}"));
    }

    //Test avec une string contenant "("
    @Test
    public void testIsBalancedParenthesisOpen() {
        assertFalse(StringUtils.isBalanced("("));
    }

    //Test avec une string contenant "{"
    @Test
    public void testIsBalancedCurlyOpen() {
        assertFalse(StringUtils.isBalanced("{"));
    }

    //Test avec une string contenant "["
    @Test
    public void testIsBalancedSquareOpen() {
        assertFalse(StringUtils.isBalanced("["));
    }

    //Test avec une string contenant ")"
    @Test
    public void testIsBalancedParenthesisClosed() {
        assertFalse(StringUtils.isBalanced(")"));
    }

    //Test avec une string contenant "}"
    @Test
    public void testIsBalancedCurlyClosed() {
        assertFalse(StringUtils.isBalanced("}"));
    }

    //Test avec une string contenant "]"
    @Test
    public void testIsBalancedSquareClosed() {
        assertFalse(StringUtils.isBalanced("]"));
    }


    //Test avec une string contenant "(}"
    @Test
    public void testIsBalancedParenthesisNormalOpenClurlyClosed() {
        assertFalse(StringUtils.isBalanced("(}"));
    }

    //Test avec une string contenant "{]"
    @Test
    public void testIsBalancedCurlyOpenClosedSquareClosed() {
        assertFalse(StringUtils.isBalanced("{]"));
    }

    //Test avec une string contenant "[)"
    @Test
    public void testIsBalancedSquareOpenNormalClosed() {
        assertFalse(StringUtils.isBalanced("[)"));
    }

    //Test avec une string contenant "(]"
    @Test
    public void testIsBalancedNormalOpenSquareClosed() {
        assertFalse(StringUtils.isBalanced("(]"));
    }

    //Test avec une string contenant "{)"
    @Test
    public void testIsBalancedCurlyOpenNormalClosed() {
        assertFalse(StringUtils.isBalanced("{)"));
    }

    //Test avec une string contenant "[}"
    @Test
    public void testIsBalancedSquareOpenCurlyClosed() {
        assertFalse(StringUtils.isBalanced("[}"));
    }

    //Test avec une string contenant "a]"
    @Test
    public void testIsBalancedRandomCharParenthesisRandomClosed() {
        assertFalse(StringUtils.isBalanced("a]"));
    }

    //Test avec une string contenant "[]"
    @Test
    public void testIsBalancedParenthesisSquareOpenClosed() {
        assertTrue(StringUtils.isBalanced("[]"));
    }
}