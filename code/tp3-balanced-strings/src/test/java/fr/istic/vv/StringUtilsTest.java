package fr.istic.vv;

import org.junit.jupiter.api.*;
import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @DisplayName("isBalancedTest - Tests génériques")
    @Test
    void isBalancedTest() {
        assertFalse(StringUtils.isBalanced("[)"));
        assertFalse(StringUtils.isBalanced("[}"));
        assertFalse(StringUtils.isBalanced("(]"));
        assertTrue(StringUtils.isBalanced("[{()}]"));
    }

    @DisplayName("isBalancedWithEmptyString - Test sur chaîne vide")
    @Test
    void isBalancedWithEmptyString() {
        assertTrue(StringUtils.isBalanced(""));
    }

    @DisplayName("isBalancedWithShortInput - Tests sur chaîne de petites tailles")
    @Test
    void isBalancedWithShortInput() {
        assertTrue(StringUtils.isBalanced("[]"));
        assertTrue(StringUtils.isBalanced("()"));
        assertTrue(StringUtils.isBalanced("{}"));
        assertFalse(StringUtils.isBalanced("[)"));
        assertFalse(StringUtils.isBalanced("(]"));
        assertFalse(StringUtils.isBalanced("[}"));
        assertFalse(StringUtils.isBalanced("["));
    }

    @DisplayName("isBalancedWithMediumInput - Tests sur chaînes de tailles moyennes")
    @Test
    void isBalancedWithMediumInput() {
        assertTrue(StringUtils.isBalanced("[()]"));
        assertTrue(StringUtils.isBalanced("{[]}"));
        assertTrue(StringUtils.isBalanced("([{}])"));
        assertFalse(StringUtils.isBalanced("{([)])}"));
        assertFalse(StringUtils.isBalanced("([[]))"));
        assertFalse(StringUtils.isBalanced("[[[]]}"));
        assertFalse(StringUtils.isBalanced("((()))]"));
    }

    @DisplayName("isBalancedWithLongInput - Tests sur chaînes de grandes tailles")
    @Test
    void isBalancedWithLongInput() {
        assertTrue(StringUtils.isBalanced("((((([])))))"));
        assertTrue(StringUtils.isBalanced("[{({[]})}]"));
        assertTrue(StringUtils.isBalanced("[[[[[[({{}})]]]]]]"));
        assertTrue(StringUtils.isBalanced("(".repeat(10^7) + ")".repeat(10^7)));
        assertFalse(StringUtils.isBalanced("{([[[[[)}]]]]]"));
        assertFalse(StringUtils.isBalanced("((([(((())))))))"));
        assertFalse(StringUtils.isBalanced("(((((((((([[[[{{}}]]]])))))))))}"));
        assertFalse(StringUtils.isBalanced("(".repeat(10^7) + ")".repeat(10^7) + "]"));
    }

    @DisplayName("isBalancedWithExtraCharacters - Tests sur chaînes contenant des lettres")
    @Test
    void isBalancedWithExtraCharacters() {
        assertTrue(StringUtils.isBalanced("(zouzou)"));
        assertTrue(StringUtils.isBalanced("(t[]{rrr})"));
        assertTrue(StringUtils.isBalanced("lk()"));
        assertTrue(StringUtils.isBalanced("jderange pas de ouf"));
        assertFalse(StringUtils.isBalanced("[rrt)"));
        assertFalse(StringUtils.isBalanced("([foufou)"));
        assertFalse(StringUtils.isBalanced("[]cv(pas}"));
    }

    @DisplayName("isBalancedWithSpecialCharacters - Tests sur chaînes contenant des caractères spéciaux")
    @Test
    void isBalancedWithSpecialCharacters() {
        assertTrue(StringUtils.isBalanced("(!!!)"));
        assertTrue(StringUtils.isBalanced("(?[]{&&&})"));
        assertTrue(StringUtils.isBalanced("@@()"));
        assertTrue(StringUtils.isBalanced("@$$$!;"));
        assertFalse(StringUtils.isBalanced("[:.~~)"));
        assertFalse(StringUtils.isBalanced("([))ççç99)"));
        assertFalse(StringUtils.isBalanced("[];,(%}"));
    }

    @DisplayName("isBalancedWithSpacesAndFormatting - Tests sur chaînes contenant des espaces et sauts à la ligne")
    @Test
    void isBalancedWithSpacesAndFormatting() {
        assertTrue(StringUtils.isBalanced(" [{()}] "));
        assertTrue(StringUtils.isBalanced("[ { ( ) } ]"));
        assertTrue(StringUtils.isBalanced("\t[{()}]\n"));
        assertFalse(StringUtils.isBalanced("[ )"));
        assertFalse(StringUtils.isBalanced("([{ ) } }"));
        assertFalse(StringUtils.isBalanced("\t[{()}]\n)"));
    }

    @DisplayName("isBalancedWithNestedParentheses - Tests Base Choice Coverage afin de tester toutes les conditions")
    @Test
    void isBalancedWithNestedParentheses() {
        // True-True
        assertTrue(StringUtils.isBalanced("{{[()]}}"));
        assertTrue(StringUtils.isBalanced("({[()]})"));
        assertTrue(StringUtils.isBalanced("[{([{}])}]"));

        // True-False
        assertFalse(StringUtils.isBalanced("{[()]}}"));
        assertFalse(StringUtils.isBalanced("({[()]})}"));
        assertFalse(StringUtils.isBalanced("[{([{}])}]}}"));

        // False-True
        assertTrue(StringUtils.isBalanced("{[()]}"));
        assertTrue(StringUtils.isBalanced("({[()]})"));
        assertTrue(StringUtils.isBalanced("[{([{}])}]"));

        // False-False
        assertTrue(StringUtils.isBalanced(""));
        assertTrue(StringUtils.isBalanced("[]"));
        assertTrue(StringUtils.isBalanced("{}"));
    }
}
