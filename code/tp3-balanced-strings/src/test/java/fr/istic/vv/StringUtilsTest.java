package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;


public class StringUtilsTest {

    @Test
    public void testEmptyString(){
        assert (StringUtils.isBalanced(""));
    }

    @Test
    public void testValidString(){
        assert (StringUtils.isBalanced("{[()]}"));
        assert (StringUtils.isBalanced("[({})]"));
        assert (StringUtils.isBalanced("({[]})"));
    }

    @Test
    public void testUnevenOpeningAndClosingCharactersString(){
        assertFalse (StringUtils.isBalanced("{()]"));
        assertFalse (StringUtils.isBalanced("([]}"));
        assertFalse (StringUtils.isBalanced("[{})"));
    }

    @Test
    public void testOddString(){
        assertFalse (StringUtils.isBalanced("{{}}}"));
        assertFalse (StringUtils.isBalanced("(()))"));
        assertFalse (StringUtils.isBalanced("[[]]]"));
        assertFalse (StringUtils.isBalanced("{()"));
        assertFalse (StringUtils.isBalanced("{[]"));
        assertFalse (StringUtils.isBalanced("[{}"));
        assertFalse (StringUtils.isBalanced("[()"));
        assertFalse (StringUtils.isBalanced("({}"));
        assertFalse (StringUtils.isBalanced("([]"));
    }
}