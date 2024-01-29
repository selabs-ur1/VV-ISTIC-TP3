package fr.istic.vv;

import java.util.List;
import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    private final static List<Character> openSymb = List.of('(', '{', '[');
    private final static List<Character> closeSymb = List.of(')', '}', ']');

    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack();

        for (char current : str.toCharArray()) {
            if (openSymb.contains(current)) {
                stack.push(current);
            } else if (!stack.empty() && closeSymb.contains(current) && (stack.peek().equals(getBalancingChar(current)))) {
                stack.pop();
            } else return false;
        }

        return stack.empty();
    }

    public static char getBalancingChar(char c) {
        switch (c) {
            case '(':
                return ')';
            case ')':
                return '(';
            case '{':
                return '}';
            case '}':
                return '{';
            case '[':
                return ']';
            case ']':
                return '[';
            default:
                throw new IllegalArgumentException();
        }
    }

}
