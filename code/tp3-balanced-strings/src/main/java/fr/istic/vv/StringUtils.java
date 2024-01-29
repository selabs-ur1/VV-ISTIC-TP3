package fr.istic.vv;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {

        List<Character> openSymbols = Arrays.asList('(', '[', '{');
        List<Character> closeSymbols = Arrays.asList(')', ']', '}');
        Stack<Character> opened = new Stack<>();

        for(int i=0; i<str.length(); i++) {
            char character = str.charAt(i);
            
            if(openSymbols.contains(character)) {
                // add character to opened stack
                opened.add(character);
            }

            else if(closeSymbols.contains(character)) {
                if(opened.isEmpty()){
                    return false;
                }

                // remove character to opened stack
                char lastOpened = opened.pop();
                if(openSymbols.indexOf(lastOpened) != closeSymbols.indexOf(character)){
                    return false;
                }
            }
        }

        return opened.isEmpty();
    }

}
