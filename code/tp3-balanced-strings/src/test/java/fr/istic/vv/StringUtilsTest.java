package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {



        /**********************/
        /* SPACE PARTITIONING */
        /**********************/

        @Test
        public void testEmptyString(){
            String testString = "";
            assertTrue(isBalanced(testString));
        }

        @Test void testNoBracketString(){
            String testString = "yuairzervpb^ùarzvazerhjiv erhipMFD QGFDSF Gfdsg gser 102486 4789 ar48a9r";
            assertTrue(isBalanced(testString));
        }

        @Test
        public void testSingleOpenParenthese(){
            String testString = "(";
            assertFalse(isBalanced(testString));
        }

        @Test
        public void testSingleOpenBracket(){
            String testString = "[";
            assertFalse(isBalanced(testString));
        }


        @Test
        public void testSingleOpenBrace(){
            String testString = "{";
            assertFalse(isBalanced(testString));
        }

        @Test
        public void testSingleCloseParenthese(){
            String testString = ")";
            assertFalse(isBalanced(testString));
        }

        @Test
        public void testSingleCloseBracket(){
            String testString = "]";
            assertFalse(isBalanced(testString));
        }


        @Test
        public void testSingleCloseBrace(){
            String testString = "}";
            assertFalse(isBalanced(testString));
        }

        @Test
        public void testBalancedCoupleParenthese(){
            String testString = "()";
            assertTrue(isBalanced(testString));
        }

        @Test
        public void testBalancedCoupleBrackets(){
            String testString = "[]";
            assertTrue(isBalanced(testString));
        }

        @Test
        public void testBalancedCoupleBraces(){
            String testString = "{}";
            assertTrue(isBalanced(testString));
        }

        @Test
        public void testUnbalancedCoupleParenthese(){
            String testString = ")(";
            assertFalse(isBalanced(testString));
        }

        @Test
        public void testUnbalancedCoupleBrackets(){
            String testString = "][";
            assertFalse(isBalanced(testString));
        }

        @Test
        public void testUnbalancedCoupleBraces(){
            String testString = "}{";
            assertFalse(isBalanced(testString));
        }


        @Test
        public void testUnBalancedOpenParethese(){
            String testString = "(()";
            assertFalse(isBalanced(testString));
        }

        @Test
        public void testUnBalancedOpenBracket(){
            String testString = "[[]";
            assertFalse(isBalanced(testString));
        }

        @Test
        public void testUnBalancedOpenBrace(){
            String testString = "{{}";
            assertFalse(isBalanced(testString));
        }

        @Test
        public void testUnBalancedCloseParethese(){
            String testString = "())";
            assertFalse(isBalanced(testString));
        }

        @Test
        public void testUnBalancedCloseBracket(){
            String testString = "[]]";
            assertFalse(isBalanced(testString));
        }

        @Test
        public void testUnBalancedCloseBrace(){
            String testString = "{}}";
            assertFalse(isBalanced(testString));
        }

        @Test
        public void testBalancedParethese(){
            String testString = "(())";
            assertTrue(isBalanced(testString));
        }

        @Test
        public void testBalancedBracket(){
            String testString = "[[]]";
            assertTrue(isBalanced(testString));
        }

        @Test
        public void testBalancedBrace(){
            String testString = "{{}}";
            assertTrue(isBalanced(testString));
        }

        @Test
        public void testUnbalancedString1(){
            String testString = "{)";
            assertFalse(isBalanced(testString));
        }

        @Test
        public void testUnbalancedString2(){
            String testString = "{a(aaa){aaaa]aa}aa";
            assertFalse(isBalanced(testString));
        }

        @Test
        public void testBalancedString(){
            String testString = "{aaa{a(aa[aaaaa]a)aa[a]aa}aa(a({[aaa]a}a)aa)a}a";
            assertTrue(isBalanced(testString));
        }
    
}