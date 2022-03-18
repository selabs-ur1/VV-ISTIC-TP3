package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NoExpectedCharactersStringBalanced {
    @Test
    public void charactersString(){
        assertFalse(isBalanced("sdlkfj"));
    }

    @Test
    public void bracketAndCharacterString(){
        assertFalse(isBalanced("[skjdfhs]"));
    }
}
