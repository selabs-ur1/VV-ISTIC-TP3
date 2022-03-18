package fr.istic.vv;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    public void testNOKNullArgument(){
        assertThrows(IllegalArgumentException.class, () -> isBalanced(null));
    }

    @Test
    public void testOKEmptyString(){
        assertTrue(isBalanced(""));
    }

    @Test
    public void testOKBalancedString(){
        assertTrue(isBalanced("{[][]}({})"));
    }

    @Test
    public void testNOKClosingBeforeOpening(){
        assertFalse(isBalanced("]["));
        assertFalse(isBalanced("}"));
        assertFalse(isBalanced(")[]"));
    }

    @Test
    public void testNOKNoClosingSymbol(){
        assertFalse(isBalanced("([]"));
        assertFalse(isBalanced("{"));
        assertFalse(isBalanced("()["));
    }

    @Test
    public void testNOKSubstringNotBalanced(){
        assertFalse(isBalanced("([)]"));
    }

    @Test
    public void testOKBalancedStringWithNonGroupingSymbol(){
        assertTrue(isBalanced("{a(b[c]b)a}"));
        assertTrue(isBalanced("abc"));
    }

}