package fr.istic.vv;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testEmptyString() {
        assertTrue(StringUtils.isBalanced(""));
    }

    @Test
    void testSingleTypeBalanced() {
        assertTrue(StringUtils.isBalanced("()"));
    }

    @Test
    void testMixedTypesProperlyNested() {
        assertTrue(StringUtils.isBalanced("([])"));
    }

    @Test
    void testSingleTypeImproperlyOrdered() {
        assertFalse(StringUtils.isBalanced(")("));
    }

    @Test
    void testMixedTypesImproperlyNestedRound() {
        assertFalse(StringUtils.isBalanced("([)]"));
    }

    @Test
    void testMixedTypesImproperlyNestedSquare() {
        assertFalse(StringUtils.isBalanced("[(])"));
    }

    @Test
    void testMixedTypesImproperlyNestedCurly() {
        assertFalse(StringUtils.isBalanced("{(})"));
    }

    @Test
    void testSingleTypeBalancedWithCurlyBraces() {
        assertTrue(StringUtils.isBalanced("{{}}"));
    }

    @Test
    void testMixedTypesProperlyNestedAllTypes() {
        assertTrue(StringUtils.isBalanced("{[()]}"));
    }

    @Test
    void testUnbalancedExtraOpeningBracket() {
        assertFalse(StringUtils.isBalanced("((()"));
    }

    @Test
    void testUnbalancedExtraClosingBracket() {
        assertFalse(StringUtils.isBalanced("[{}]]"));
    }

    @Test
    void testNonBracketCharacters() {
        assertTrue(StringUtils.isBalanced("a"));
    }

    @Test
    void testSingleUnmatchedOpeningRoundBracket() {
        assertFalse(StringUtils.isBalanced("("));
    }

    @Test
    void testSingleUnmatchedOpeningSquareBracket() {
        assertFalse(StringUtils.isBalanced("["));
    }

    @Test
    void testSingleUnmatchedOpeningCurlyBracket() {
        assertFalse(StringUtils.isBalanced("{"));
    }

    @Test
    void testSingleUnmatchedClosingBracket() {
        assertFalse(StringUtils.isBalanced("]"));
    }

    @Test
    void testBaseChoiceRoundImbalance() {
        assertFalse(StringUtils.isBalanced(")"));
    }

    @Test
    void testBaseChoiceCurlyImbalance() {
        assertFalse(StringUtils.isBalanced("}"));
    }
}