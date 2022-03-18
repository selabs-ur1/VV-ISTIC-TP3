package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenBracketStringBalanced {
    @Test
    public void openingBracketStartFalse(){
        assertFalse(isBalanced("[(]]"));
    }
    @Test
    public void closingBracketStartTrue(){
        assertTrue(isBalanced("[({[]})]"));
    }
}
