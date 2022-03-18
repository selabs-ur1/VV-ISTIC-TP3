package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    //empty string
    @Test
    public void testEmptyString(){
        assertTrue(isBalanced(""), "empty string is balanced");
    }

    //UNIQUE CHAR

    //unique opening
    @Test
    public void testOpenOneParenthesis(){
        assertFalse(isBalanced("("), "string contains odd number of char therefore can not be balanced");
    }
    @Test
    public void testOpenOneBracket(){
        assertFalse(isBalanced("["), "string contains odd number of char therefore can not be balanced");
    }
    @Test
    public void testOpenOneCurlyBracket(){
        assertFalse(isBalanced("{"), "string contains odd number of char therefore can not be balanced");
    }

    //unique closing
    @Test
    public void testCloseOneParenthesis(){
        assertFalse(isBalanced(")"), "string contains odd number of char therefore can not be balanced");
    }
    @Test
    public void testCloseOneBracket(){
        assertFalse(isBalanced("]"), "string contains odd number of char therefore can not be balanced");
    }
    @Test
    public void testCloseOneCurlyBracket(){
        assertFalse(isBalanced("}"), "string contains odd number of char therefore can not be balanced");
    }

    //SIMPLE Balanced
    @Test
    public void testParenthesis(){
        assertTrue(isBalanced("()"), "string contains an even number of char therefore can be balanced. str has the right opening and closing.");
    }
    @Test
    public void testBracket(){
        assertTrue(isBalanced("[]"), "string contains an even number of char therefore can be balanced. str has the right opening and closing.");
    }
    @Test
    public void testCurlyBracket(){
        assertTrue(isBalanced("{}"), "string contains an even number of char therefore can be balanced. str has the right opening and closing.");
    }

    //COMPLEXED Balanced (imbricated)
    //same char in str
    //return true
    @Test
    public void testComplexParenthesis(){
        assertTrue(isBalanced("()(())(())"));
    }
    @Test
    public void testComplexBracket(){
        assertTrue(isBalanced("[[[][]][][[]]]"));
    }
    @Test
    public void testComplexCurlyBracket(){
        assertTrue(isBalanced("{}{{}}"));
    }{}
    //return falsse
    @Test
    public void tesComplexParenthesisNotClosed(){
        assertFalse(isBalanced("()((()())"));
    }
    @Test
    public void testMultipleParenthesisNotOpened(){
        assertFalse(isBalanced("()()())((()))"));
    }
    @Test
    public void testComplexBracketOneNotClosed(){
        assertFalse(isBalanced("[[][[]]"));
    }
    @Test
    public void testComplexBracketOneNotOpened(){
        assertFalse(isBalanced("[]]][]"));
    }
    @Test
    public void testComplexCurlyBracketOneNotClosed(){
        assertFalse(isBalanced("{{{}}{}"));
    }
    @Test
    public void testComplexPCurlyBracketOneNotOpened(){
        assertFalse(isBalanced("{{}{}}}}"));
    }

    //mixed char
    //return true
    @Test
    public void testComplexMixedTrue(){
        assertTrue(isBalanced("{{({})}}({})[]"));
    }
    //return false
    @Test
    public void testComplexMixedFalse(){
        assertFalse(isBalanced("(([)]"));
    }

}

