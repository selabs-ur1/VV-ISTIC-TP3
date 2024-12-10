package fr.istic.vv;

import java.util.ArrayDeque;
import java.util.Deque;

public class StringUtils {

    private StringUtils() {
    }

    public static boolean isBalanced(String str) {
        if (str == null || str.isEmpty()) return true; // Empty string is considered balanced

        Deque<Character> stack = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            // Push opening symbols onto the stack
            if (isOpeningSymbol(c)) {
                stack.push(c);
            }
            // Handle closing symbols
            else if (isClosingSymbol(c)) {
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), c)) {
                    return false;
                }
            }
        }
        // Expression is balanced if stack is empty at the end
        return stack.isEmpty();
    }

    private static boolean isOpeningSymbol(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private static boolean isClosingSymbol(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }
}
