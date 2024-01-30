package fr.istic.vv;
import org.junit.jupiter.api.Test;
import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
  
    @Test
    public void testIsBalanced() {

        // Partition 1: Empty String
        assertTrue(isBalanced(""));

        // Partition 2: String with balanced symbols
        assertTrue(isBalanced("{[]()}"));

        // Partition 3: String with unbalanced symbols
        assertFalse(isBalanced("([)]"));

        // Partition 4: String with only curly braces
        assertTrue(isBalanced("{}"));

        // Partition 5: String with only square brackets
        assertTrue(isBalanced("[]"));

        // Partition 6: String with only parentheses
        assertTrue(isBalanced("()"));

        // Partition 7: String with a combination of balanced symbols
        assertTrue(isBalanced("{[()]}"));

        // Partition 8: String with a combination of unbalanced symbols
        assertFalse(isBalanced("[{()]"));
    }
}
