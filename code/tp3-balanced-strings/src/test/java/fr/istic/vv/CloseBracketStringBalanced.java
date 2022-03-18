package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CloseBracketStringBalanced {
    @Test
    public void closingBracketStart(){
        assertFalse(isBalanced("]()"));
    }
    @Test
    public void closingBracketStartandOpenAfter(){
        assertFalse(isBalanced("]["));
    }
}
