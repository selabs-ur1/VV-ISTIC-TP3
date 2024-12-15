package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {
    }

    public static boolean isBalanced(String str) {
        // contains only (,[ and {
        Stack<Character> symbolStack = new Stack<>();

        // Empty String
        if (str.isEmpty()) {
            return true;
        }

        for (char ch : str.toCharArray()) {
            if (ch == '{' || ch == '[' || ch == '(') {
                symbolStack.push(ch);
            } else if (ch == '}' || ch == ']' || ch == ')') {
                // No opening symbol
                if (symbolStack.isEmpty()) {
                    return false;
                }
                char top = symbolStack.pop();
                if ((ch == '}' && top != '{') ||
                        (ch == ']' && top != '[') ||
                        (ch == ')' && top != '(')) {
                    // Mismatched symbols
                    return false;
                }
            }

        }

        // Stack should be empty at the end
        // if false, then there are unmatched opening symbols
        return symbolStack.isEmpty();
    }
}

