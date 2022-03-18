package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    StringUtils stringUtils;

    @BeforeEach
    void init(){

        stringUtils= new StringUtils();
        HashMap<Character,Character> set = new HashMap<Character,Character>();
        set.put('(',')');
        set.put('{','}');
        set.put('[',']');
        stringUtils.setCharacterMap(set);
    }

    @Test
    void isBalancedTest_0() throws StringUtils.EmptyInitialSetException, StringUtils.SymbolNotInSetException {
        assertTrue(stringUtils.isBalanced("{[][]}({})"));
    }

    @Test
    void isBalancedTest_1() throws StringUtils.EmptyInitialSetException, StringUtils.SymbolNotInSetException {
        assertFalse(stringUtils.isBalanced("{"));
    }

    @Test
    void isBalancedTest_2() throws StringUtils.EmptyInitialSetException, StringUtils.SymbolNotInSetException {
        assertFalse(stringUtils.isBalanced("{([{)]"));
    }

    @Test
    void isBalancedTest_3() throws StringUtils.EmptyInitialSetException, StringUtils.SymbolNotInSetException {
        assertFalse(stringUtils.isBalanced("{(}{}"));
    }

    @Test
    void isBalancedTest_4() throws StringUtils.EmptyInitialSetException, StringUtils.SymbolNotInSetException {
        assertFalse(stringUtils.isBalanced("]["));
    }

    @Test
    void isBalancedExceptionTest_1(){
        stringUtils.setCharacterMap(new HashMap<>());
        assertThrowsExactly(StringUtils.EmptyInitialSetException.class,()->stringUtils.isBalanced("{[][]}({})"));
    }

    @Test
    void isBalancedExceptionTest_2(){
        assertThrowsExactly(StringUtils.SymbolNotInSetException.class,()->stringUtils.isBalanced("{[ABBA][XX]}({})"));
    }

    @Test
    void isBalancedExceptionTest_3() throws StringUtils.WrongInitialSetException, StringUtils.EmptyInitialSetException {
        assertThrowsExactly(StringUtils.WrongInitialSetException.class,()->stringUtils.initFromStrings("() {} [] AB XYZ".split(" ")));
    }

    @Test
    void pitCompletionTest_1(){
        assertTrue(stringUtils.isCorrespondingSymbol('(',')'));
        assertFalse(stringUtils.isCorrespondingSymbol('[','}'));
        assertFalse(stringUtils.isCorrespondingSymbol('a','}'));
        assertFalse(stringUtils.isCorrespondingSymbol('[','b'));
    }

    @Test
    void pitCompletionTest_2() throws StringUtils.SymbolNotInSetException, StringUtils.EmptyInitialSetException {
        assertFalse(stringUtils.isBalanced("[}}"));
    }

}