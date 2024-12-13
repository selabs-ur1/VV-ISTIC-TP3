package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {
    }

    public static boolean isBalanced(String str) {
        if (str == null || str.isEmpty()) {
            return true; // An empty string or null is balanced
        }

        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c); // Push opening symbols onto the stack
            } else if (c == '}' || c == ']' || c == ')') {
                if (stack.isEmpty()) {
                    return false; // No matching opening symbol
                }
                char top = stack.pop();
                if (!isMatchingPair(top, c)) {
                    return false; // Mismatched symbols
                }
            }
        }

        return stack.isEmpty(); // True if all symbols are matched
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '{' && close == '}') ||
                (open == '[' && close == ']') ||
                (open == '(' && close == ')');
    }
}
