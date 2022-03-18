package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    @Test
    void testUnbalancedAllSigns(){
        assertFalse(isBalanced("{]]({))]]]}"));
    }

    @Test
    void testBalancedAllSigns(){
        assertTrue(isBalanced("{([])}"));
        assertTrue(isBalanced("{[][]}({})"));
    }

    @Test
    void testUnbalancedOpenPrenthesisOpening(){
        assertFalse(isBalanced("("));
    }

    @Test
    void testUnbalancedOpenSquareBracketOpening(){
        assertFalse(isBalanced("["));
    }

    @Test
    void testUnbalancedOpenCurlyBracketOpening(){
        assertFalse(isBalanced("{"));
    }

    @Test
    void testUnbalancedOpenPrenthesisEnding(){
        assertFalse(isBalanced(")"));
    }

    @Test
    void testUnbalancedOpenSquareBracketEnding(){
        assertFalse(isBalanced("]"));
    }

    @Test
    void testUnbalancedOpenCurlyBracketEnding(){
        assertFalse(isBalanced("}"));
    }

    @Test
    void testNoSign(){
        assertTrue(isBalanced("pouet"));
    }

    @Test
    void testEmptyString(){
        assertTrue(isBalanced(""));
    }

    @Test
    void testSquareBracket(){
        assertTrue(isBalanced("[]"));
    }

    @Test
    void testParenthesis(){
        assertTrue(isBalanced("()"));
    }

    @Test
    void testCurlyBracket(){
        assertTrue(isBalanced("{}"));
    }

    @Test
    void testWithText(){
        assertTrue(isBalanced("(pouet[cacah{chipolata}uette])"));
    }

    @Test
    void testSquareBracketInvalidContent(){
        assertFalse(isBalanced("[{]"));
    }

    @Test
    void testParenthesisInvalidContent(){
        assertFalse(isBalanced("([)"));
    }

    @Test
    void testCurlyBracketInvalidContent(){
        assertFalse(isBalanced("{[}"));
    }

    @Test
    void testNullString(){assertThrows(NullPointerException.class, () -> {isBalanced(null);});}
}