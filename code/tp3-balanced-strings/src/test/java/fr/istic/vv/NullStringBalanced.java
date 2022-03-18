package fr.istic.vv;

import fr.istic.vv.StringUtils;
import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NullStringBalanced {
    @Test
    public void nullString(){
        assertFalse(isBalanced(null));
    }
}
