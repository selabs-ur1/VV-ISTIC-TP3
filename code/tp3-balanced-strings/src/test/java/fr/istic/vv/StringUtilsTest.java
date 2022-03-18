package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

@Test 
public void testEmpty(){
    assertTrue(isBalanced(""));
}
@Test 
public void testOneOpenParenthesis(){
    assertFalse(isBalanced("("));
}
@Test 
public void testOneClosedPrenthesis(){
    assertFalse(isBalanced(")"));
}
@Test 
public void testOneOpenCurlyBracket(){
    assertFalse(isBalanced("{"));
}
@Test 
public void testOneClosedCurlyBracket(){
    assertFalse(isBalanced("}"));
}
@Test 
public void testOneOpenBracket(){
    assertFalse(isBalanced("["));
}
@Test 
public void testOneClosedBracket(){
    assertFalse(isBalanced("]"));
}
@Test
public void testParenthesis(){
    assertTrue(isBalanced("()"));
}
@Test
public void testBracket(){
    assertTrue(isBalanced("[]"));
}
@Test
public void testCurlyBracket(){
    assertTrue(isBalanced("{}"));
}
@Test
public void testMultipleParenthesis(){
    assertTrue(isBalanced("()()(())((()))"));
}
@Test
public void testMultipleBracket(){
    assertTrue(isBalanced("[[[]][]][][[]]"));
}
@Test
public void testMultipleCurlyBracket(){
    assertTrue(isBalanced("{{{}}}{}{{}}{}"));
}
@Test
public void testMultipleParenthesisOneNotClosed(){
    assertFalse(isBalanced("()()(()()((()))"));
}
@Test
public void testMultipleParenthesisOneNotOpened(){
    assertFalse(isBalanced("()()())((()))"));
}
@Test
public void testMultipleBracketOneNotClosed(){
    assertFalse(isBalanced("[[[]][][[]]"));
}
@Test
public void testMultipleBracketOneNotOpened(){
    assertFalse(isBalanced("[[]][[]][][[]]]"));
}
@Test
public void testMultipleCurlyBracketOneNotClosed(){
    assertFalse(isBalanced("{{{{}}}{}"));
}
@Test
public void testMultiplePCurlyBracketOneNotOpened(){
    assertFalse(isBalanced("{{}{}}{{}}}"));
}

@Test
public void testBalencedEmbraced1(){
    assertTrue(isBalanced("{{({})}}{}{{}}{}[]"));
}
//
@Test
public void testBalencedEmbraced2(){
    assertTrue(isBalanced("[()]"));
}
@Test
public void testBalencedEmbraced3(){
    assertTrue(isBalanced("([])"));
}
@Test
public void testBalencedEmbraced4(){
    assertTrue(isBalanced("[{}]"));
}
@Test
public void testBalencedEmbraced5(){
    assertTrue(isBalanced("{[]}"));
}
@Test
public void testBalencedEmbraced6(){
    assertTrue(isBalanced("[()]"));
}
@Test
public void testBalencedEmbraced7(){
    assertTrue(isBalanced("{()}"));
}
@Test
public void testBalencedEmbraced8(){
    assertTrue(isBalanced("({})"));
}
@Test
public void testBalencedEmbraced9(){
    assertTrue(isBalanced("[()]"));
}
//
@Test
public void testBalencedEmbraced10(){
    assertTrue(isBalanced("{([])}"));
}
@Test
public void testBalencedEmbraced11(){
    assertTrue(isBalanced("([{}])"));
}
@Test
public void testBalencedEmbraced12(){
    assertTrue(isBalanced("({[]})"));
}
@Test
public void testBalencedEmbraced13(){
    assertTrue(isBalanced("{[()]}"));
}
@Test
public void testBalencedEmbraced14(){
    assertTrue(isBalanced("[{()}]"));
}
@Test
public void testBalencedEmbraced15(){
    assertTrue(isBalanced("[({})]"));
}
@Test
public void testBalencedEmbraced16(){
    assertTrue(isBalanced("[({}){[()]}]"));
}
@Test
public void testBalencedEmbraced17(){
    assertTrue(isBalanced("[([{()}]{}){[()]}]"));
}
@Test
public void testBalencedEmbraced18(){
    assertTrue(isBalanced("[([{()}][()]{}({[]})){[()]}]"));
}

@Test
public void testBalencedEmbraced19(){
    assertTrue(isBalanced("[([{(){[()]}}][({([])}){()}]{}({{([])}[{([])}]})){[()][()]}{([])}]"));
}

@Test
public void testNotBalencedEmbraced(){
    assertFalse(isBalanced("([)]"));
}
@Test
public void testNotBalencedEmbraced2(){
    assertFalse(isBalanced("([(((]}}](()]]][[{"));
}@Test
public void testNotBalencedEmbraced3(){
    assertFalse(isBalanced("][[[#{[|Stop"));
}@Test
public void testNotBalencedEmbraced4(){
    assertFalse(isBalanced("hello"));
}@Test
public void testNotBalencedEmbraced5(){
    assertFalse(isBalanced(" "));
}@Test
public void testNotBalencedEmbraced6(){
    assertFalse(isBalanced("}[{))}}}(({["));
}
}