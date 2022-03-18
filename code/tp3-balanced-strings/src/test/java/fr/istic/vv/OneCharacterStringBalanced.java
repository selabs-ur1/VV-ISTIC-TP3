package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class OneCharacterStringBalanced {
    @Test
    public void bracketString(){
        assertFalse(isBalanced("["));
    }
    @Test
    public void characterString(){
        assertFalse(isBalanced("A"));
    }
}
