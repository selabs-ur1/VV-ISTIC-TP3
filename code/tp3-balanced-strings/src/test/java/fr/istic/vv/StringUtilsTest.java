package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
  
  @Test
	void test() {
    assertTrue(StringUtils.isBalanced(null));
    assertTrue(StringUtils.isBalanced(""));
    assertTrue(StringUtils.isBalanced("()"));
    assertTrue(StringUtils.isBalanced("{}"));
    assertTrue(StringUtils.isBalanced("[]"));
    assertTrue(StringUtils.isBalanced("({[]})"));
    
    assertFalse(StringUtils.isBalanced("("));
    assertFalse(StringUtils.isBalanced("{"));
    assertFalse(StringUtils.isBalanced("["));
    
    assertTrue(StringUtils.isBalanced("a(a{a[a]a}a)a"));
    
    assertFalse(StringUtils.isBalanced("]["));
		assertFalse(StringUtils.isBalanced("(}"));
  }

}
