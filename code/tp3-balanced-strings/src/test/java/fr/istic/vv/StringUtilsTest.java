package fr.istic.vv;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class StringUtilsTest {

    @Test
    public void testBalancedString() {
        assertTrue(StringUtils.isBalanced("{[()]}"));
        assertTrue(StringUtils.isBalanced("({})"));
    }

    @Test
    public void testUnbalancedString() {
        assertFalse(StringUtils.isBalanced("{[(])}"));
        assertFalse(StringUtils.isBalanced("()("));
    }

    @Test
    public void testEmptyString() {
        assertTrue(StringUtils.isBalanced(""));
    }

    @Test
    public void testStringWithoutBrackets() {
        assertTrue(StringUtils.isBalanced("abc"));
    }
}
