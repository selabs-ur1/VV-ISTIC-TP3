package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {
    }

    public static boolean isBalanced(String str) {
        boolean isBalanced = true;

        Stack<Character> pile = new Stack();

        // convert string to `char[]` array
        char[] chars = str.toCharArray();

        // iterate over `char[]` array using enhanced for-loop
        for (char ch: chars) {
            if (!isBalanced){
                break;
            }
            switch(ch) {
                case '{':
                    pile.push('{'); break;

                case '[':
                    pile.push('['); break;

                case '(':
                    pile.push('('); break;

                case '}':
                    if(pile.isEmpty() || !pile.pop().equals('{'))
                        isBalanced = false;
                    break;

                case ']':
                    if(pile.isEmpty() || !pile.pop().equals('['))
                        isBalanced = false;
                    break;

                case ')':
                    if(pile.isEmpty() || !pile.pop().equals('('))
                        isBalanced = false;
                    break;
            }
        }
        if (!pile.isEmpty()){
            isBalanced = false;
        }
        return isBalanced;
    }

}
