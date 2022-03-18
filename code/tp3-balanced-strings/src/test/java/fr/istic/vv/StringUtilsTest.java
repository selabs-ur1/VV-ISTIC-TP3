package fr.istic.vv;

import static fr.istic.vv.StringUtils.isBalanced;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Random;

class StringUtilsTest {

    @Nested
    class StringUtilsTestFalse {
        @Test
        public void testBalancedString() {
            Assertions.assertFalse(isBalanced("azer{[]}][',oùiejr]}]}]"));
            Assertions.assertFalse(isBalanced("[azer{[]}][',oùiejr{]}"));
            Assertions.assertFalse(isBalanced("[{([{]}])([{})]"));
            Assertions.assertFalse(isBalanced("zefzef{"));
            Assertions.assertFalse(isBalanced("())"));
            Assertions.assertFalse(isBalanced("[]{"));
            Assertions.assertFalse(isBalanced(null));
        }

    }

    @Nested
    class StringUtilsTestTrue {
        @Test
        public void testBalancedStringParentheses() {
            Random r = new Random();
            int random = r.nextInt(256);
            String s = "()".repeat(random);
            String open = "(".repeat(random);
            String close = ")".repeat(random);

            //tests
            Assertions.assertTrue(isBalanced(""));
            Assertions.assertTrue(isBalanced("()"));
            Assertions.assertTrue(isBalanced("()".concat(s)));
            Assertions.assertTrue(isBalanced("("+ s +")"));
            Assertions.assertTrue(isBalanced("("+ open + close +")"));
            Assertions.assertTrue(isBalanced("("+ s +")" + s ));
        }

        @Test
        public void testBalancedStringBrackets() {
            Random r = new Random();
            int random = r.nextInt(256);
            String s = "{}".repeat(random);
            String open = "{".repeat(random);
            String close = "}".repeat(random);

            //tests
            Assertions.assertTrue(isBalanced(""));
            Assertions.assertTrue(isBalanced("{}"));
            Assertions.assertTrue(isBalanced("{}".concat(s)));
            Assertions.assertTrue(isBalanced("{"+ s +"}"));
            Assertions.assertTrue(isBalanced("{"+ open + close +"}"));
            Assertions.assertTrue(isBalanced("{"+ s +"}" + s ));
        }

        @Test
        public void testBalancedStringCrochet() {
            Random r = new Random();
            int random = r.nextInt(256);
            String s = "[]".repeat(random);
            String open = "[".repeat(random);
            String close = "]".repeat(random);

            //tests
            Assertions.assertTrue(isBalanced(""));
            Assertions.assertTrue(isBalanced("[]"));
            Assertions.assertTrue(isBalanced("[]".concat(s)));
            Assertions.assertTrue(isBalanced("["+ s +"]"));
            Assertions.assertTrue(isBalanced("["+ open + close +"]"));
            Assertions.assertTrue(isBalanced("["+ s +"]" + s ));
        }

        @Test
        public void testBalancedStringThirdSymbol() {
            Random r = new Random();
            int random = r.nextInt(256);
            String openCro = "[";
            String closeCro = "]";

            String openBra = "{";
            String closeBra = "}";

            String openPar = "(";
            String closePar = ")";

            //tests
            Assertions.assertTrue(isBalanced(""));
            Assertions.assertTrue(isBalanced("{}"));
            Assertions.assertTrue(isBalanced("[]"));
            Assertions.assertTrue(isBalanced("()"));
            Assertions.assertTrue(isBalanced("(){}[]"));
            Assertions.assertTrue(isBalanced("({[]})"));
        }
    }
}