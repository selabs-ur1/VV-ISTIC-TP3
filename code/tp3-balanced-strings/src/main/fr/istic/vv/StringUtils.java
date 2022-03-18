package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    public static boolean isBalanced(String str) {
        if (str == null) throw new IllegalArgumentException();

        Stack<Character> pile =new Stack();

        char[] chars = str.toCharArray();

        for (char c : chars) {
            switch(c) {
                case '{':
                    pile.push(c);
                    break;
                case '}':
                    if (pile.isEmpty() || pile.pop() != '{') return false;
                    break;
                case '(':
                    pile.push(c);
                    break;
                case ')':
                    if (pile.isEmpty() || pile.pop() != '(') return false;
                    break;
                case '[':
                    pile.push(c);
                    break;
                case ']':
                    if (pile.isEmpty() || pile.pop() != '[') return false;
                    break;
                default:
                    break;
            }
        }

        return pile.isEmpty();
    }

}
