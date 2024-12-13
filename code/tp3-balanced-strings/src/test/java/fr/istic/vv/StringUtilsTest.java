package fr.istic.vv;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @Test 
    void testAvecCharactereSpecial(){
        String testString = "([a{}b]c)";
        boolean result = StringUtils.isBalanced(testString);
        assertTrue(result);
    }

    @Test
    void testSansCharactereSpecial(){
        String testString = "abc";
        boolean result = StringUtils.isBalanced(testString);
        assertTrue(result);
    }

    @Test
    void testMemeNombreCharactereSpecial(){
        String testString = "(())";
        boolean result = StringUtils.isBalanced(testString);
        assertTrue(result);
    }

    @Test
    void testNombreDiffCharactereSpecial(){
        String testString = "(()";
        boolean result = StringUtils.isBalanced(testString);
        assertFalse(result);
    }

    @Test
    void testBienOrdonne(){
        String testString = "({})";
        boolean result = StringUtils.isBalanced(testString);
        assertTrue(result);
    }

    @Test
    void testMalOrdonne(){
        String testString = "({)}";
        boolean result = StringUtils.isBalanced(testString);
        assertFalse(result);
    }

    @Test
    void testBienOuvertFerme(){
        String testString = "({[]})[]";
        boolean result = StringUtils.isBalanced(testString);
        assertTrue(result);
    }

    @Test
    void testMalOuvertFerme(){
        String testString = "])(}{[";
        boolean result = StringUtils.isBalanced(testString);
        assertFalse(result);
    }

    @Test
    void testBarcketStackNotEmptyError(){
        String testString = "(a])(}{[";
        boolean result = StringUtils.isBalanced(testString);
        assertFalse(result);
    }

    @Test
    void testParenthesisStackEmptyError(){
        String testString = ")";
        boolean result = StringUtils.isBalanced(testString);
        assertFalse(result);
    }

    @Test 
    void testAccoladeFermanteErrorStackEmpty(){
        String testString = "totot}";
        boolean result = StringUtils.isBalanced(testString);
        assertFalse(result);   
    }
    @Test 
    void testAccoladeFermanteError(){
        String testString = "[totot}";
        boolean result = StringUtils.isBalanced(testString);
        assertFalse(result);   
    }


}