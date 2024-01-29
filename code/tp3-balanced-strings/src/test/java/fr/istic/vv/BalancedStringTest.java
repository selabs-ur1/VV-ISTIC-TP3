package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalancedStringTest {

    @Test
    public void test1() {
        assertFalse(BalancedString.isBalanced(")zneznf"));
    }

    @Test
    public void test2() {
        assertFalse(BalancedString.isBalanced("]zneznf"));
    }

    @Test
    public void test3() {
        assertFalse(BalancedString.isBalanced("}zneznf"));
    }

    @Test
    public void test4() {
        assertFalse(BalancedString.isBalanced("zneznf("));
    }

    @Test
    public void test5() {
        assertFalse(BalancedString.isBalanced("zneznf["));
    }

    @Test
    public void test6() {
        assertFalse(BalancedString.isBalanced("zneznf{"));
    }

    @Test
    public void test7() {
        assertTrue(BalancedString.isBalanced(""));
    }

    @Test
    public void test8() {
        assertTrue(BalancedString.isBalanced("reherheheht"));
    }

    @Test
    public void test9() {
        assertTrue(BalancedString.isBalanced("([{}])"));
    }

    @Test
    public void test10() {
        assertTrue(BalancedString.isBalanced("ezfze(ezfez[{efz}sdf]sdg)"));
    }

}