package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            if (isOpenSymbol(c)) {
                stack.push(c);
            } else if (isCloseSymbol(c)) {
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isOpenSymbol(char c) {
        return c == '{' || c == '[' || c == '(';
    }

    private static boolean isCloseSymbol(char c) {
        return c == '}' || c == ']' || c == ')';
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '{' && close == '}') ||
                (open == '[' && close == ']') ||
                (open == '(' && close == ')');
    }

}
