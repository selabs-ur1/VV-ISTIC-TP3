package fr.istic.vv;


import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmptyStringBalanced {
    @Test
    public void emptyString(){
        assertTrue(isBalanced(""));
    }
}
