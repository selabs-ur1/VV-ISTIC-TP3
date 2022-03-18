package fr.istic.vv;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Class for class StringUtils
 */
public class StringUtilsTest {

    private static StringUtils stringUtils;

    // Initialize stringUtils
    @BeforeAll
    public static void setUp(){
        stringUtils = new StringUtils();
    }

    // Method after all others
    @AfterAll
    public static void tearDown(){}

    // Test isBalanced NOK: illegal argument parameter null
    @Test
    public void testIsBalancedNOKIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> stringUtils.isBalanced(null));
    }

    // Test isBalanced OK: empty string is allowed
    @Test
    public void testIsBalancedOKEmptyString() {
        assertTrue(stringUtils.isBalanced(""));
    }

    // Test isBalanced NOK: odd number of char
    @Test
    public void testIsBalancedNOKOddLengthString() {
        assertFalse(stringUtils.isBalanced("["));
        assertFalse(stringUtils.isBalanced("[({"));
        assertFalse(stringUtils.isBalanced("[({(("));
    }

    // Test isBalanced NOK: bracket not closed
    @Test
    public void testIsBalancedNOKBracketNotClosed() {
        assertFalse(stringUtils.isBalanced("[{{{"));
    }

    // Test isBalanced NOK: unallowed characters
    @Test
    public void testIsBalancedNOKUnallowedCharacters() {
        assertFalse(stringUtils.isBalanced("[{{{aa}}}]"));
    }

    // Test Multiple string : OK
    @Test
    public void testIsBalancedOKMultipleTests(){
        assertTrue(stringUtils.isBalanced("[]"));
        assertTrue(stringUtils.isBalanced("[({})]"));
        assertTrue(stringUtils.isBalanced("[({(())})]"));
    }

    // Test Multiple string with multiple Blocks : OK
    @Test
    public void testIsBalancedOKMultipleBlockAndTests(){
        assertTrue(stringUtils.isBalanced("[]{}"));
        assertTrue(stringUtils.isBalanced("[({})][{}]"));
        assertTrue(stringUtils.isBalanced("[({(())})][][(())]"));
    }
}
