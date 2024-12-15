package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    public void testStringVide(){
        String t ="";
        assertTrue(isBalanced(t));
    }

    @Test
    public void testImpair() {
        String t = "[](";
        assertFalse(isBalanced(t));
    }

    @Test
    public void testMemeNombreOuvrantsFermants() {
        String t = "[]{}";
        int open = 0, closed = 0;
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == '{' || t.charAt(i) == '[' || t.charAt(i) == '(') {
                open++;
            } else {
                closed++;
            }
        }
        assertTrue(open == closed && isBalanced(t));
    }

    @Test
    public void testMemeNombrePaires() {
        String t = "[]()";
        int openP = 0, closedP = 0, openC = 0, closedC = 0, openA = 0, closedA = 0;

        for (int i = 0; i < t.length(); i++) {
            switch (t.charAt(i)) {
                case '}':
                    closedA++;
                    break;
                case ']':
                    closedC++;
                    break;
                case ')':
                    closedP++;
                    break;
                case '(':
                    openP++;
                    break;
                case '{':
                    openA++;
                    break;
                case '[':
                    openC++;
                    break;
                default:
                    break;
            }
        }
        assertTrue(openA == closedA && openC == closedC && openP == closedP && isBalanced(t));
    }

}