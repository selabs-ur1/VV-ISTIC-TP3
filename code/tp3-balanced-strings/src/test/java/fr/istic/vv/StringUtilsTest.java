package fr.istic.vv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;

class StringUtilsTest {

    @Test
    public void testStringVide() {
        Assertions.assertTrue(isBalanced(""));
    }

    @Test
    public void testEnsembleValideDeBase() {
        Assertions.assertTrue(isBalanced("(){}[]"));
    }

    @Test
    public void testAvecLettres() {
        Assertions.assertTrue(isBalanced("(abcs[oqisnd]{})"));
    }

    @Test
    public void testSeulementLettres() {
        Assertions.assertTrue(isBalanced("ceci n est pas un test"));
    }

    @Test
    public void testAvecChiffres() {
        Assertions.assertTrue(isBalanced("(12{}[(){}])"));
    }

    @Test
    public void testNonFermantes() {
        Assertions.assertFalse(isBalanced("({})["));
    }

    @Test
    public void testNonOuvrantes() {
        Assertions.assertFalse(isBalanced("({}])"));
    }

    @Test
    public void testVide() {
        Assertions.assertTrue(isBalanced(""));
    }

    @Test
    public void testOuvrantesEtFermanteMaisInvalide() {
        Assertions.assertFalse(isBalanced("([{]})"));
    }

}