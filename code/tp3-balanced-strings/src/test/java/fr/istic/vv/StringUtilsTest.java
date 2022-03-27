package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    public static void main(String[] args) {
        System.out.println(isBalanced("[)"));
    }

    @Test
    public void testIsBalanced1() {
        String str = "()()(";
        //assertEquals(0,str.length()%2);
        assertFalse(isBalanced(str), "Length of the string should be pair");
    }

    @Test
    public void testIsBalanced2() {
        assertFalse(isBalanced("{{}"), "Number of open symbols in str should equal number of closed symbols");
        assertFalse(isBalanced("{{{}"), "Number of open symbols in str should equal number of closed symbols");
    }

    @Test
    public void testIsBalanced3() {
        assertFalse(isBalanced("]["), "The first character should be open symbol");
    }

    @Test
    public void testIsBalanced4() {
        assertTrue(isBalanced(""), "The empty string should be considered as balanced");
    }

    @Test
    public void testIsBalanced5() {
        assertFalse(isBalanced("{}[}"), "Each open symbol should have a matching closed symbol");
    }

    @Test
    public void testIsBalanced6() {
        assertFalse(isBalanced("[)"), "Each closed symbol should have a matching open symbol");
        assertFalse(isBalanced("{}{]"), "Each closed symbol should have a matching open symbol");
        assertFalse(isBalanced("{}(}"), "Each closed symbol should have a matching open symbol");
    }

    @Test
    public void testIsBalanced7() {
        assertTrue(isBalanced("([]){}"), "The substring before pairs of symbols should be balanced");
    }

    @Test
    public void testIsBalanced8() {
        assertTrue(isBalanced("{}([])"), "The substring after pairs of symbols should be balanced");
    }

    @Test
    public void testIsBalanced9() {
        assertTrue(isBalanced("{()[]}"), "The substring after pairs of symbols should be balanced");
    }

    @Test
    public void testIsBalanced10() {
        assertTrue(isBalanced("()"), "The number of types of symbols can equal 1");
        assertTrue(isBalanced("()[]"), "The number of types of symbols can be > 1");
        assertTrue(isBalanced("()[]{}"), "The number of types of symbols can be > 1");
    }


    @Test
    public void testBaseChoice() {
        assertFalse(isBalanced(")["), "The first character should not be closed symbol");
        assertFalse(isBalanced("]["), "The first character should not be closed symbol");
        assertFalse(isBalanced("}["), "The first character should not be closed symbol");
    }

}
