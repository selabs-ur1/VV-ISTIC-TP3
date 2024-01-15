package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        if (str == null || str.isEmpty()) {
            return true; // Empty string - balanced
        }

        Stack<Character> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (ch == '{' || ch == '[' || ch == '(') {
                stack.push(ch);
            } else if (ch == '}' || ch == ']' || ch == ')') {
                if (stack.isEmpty()) {
                    return false; // Closing symbol with no matching opening symbol
                }
                char opening = stack.pop(); //  Removes and returns the element at the top of the stack
                if ((ch == '}' && opening != '{') ||
                        (ch == ']' && opening != '[') ||
                        (ch == ')' && opening != '(')) {
                    return false; // Opening and closing symbols are not equal
                }
            }
        }

        return stack.isEmpty(); // All opening symbols match closing symbols
    }
}

/*
    Example:
    String "{[()]}"
    1. { is an opening symbol, pushed onto the stack.
    2. [ is an opening symbol, pushed onto the stack.
    3. ( is an opening symbol, pushed onto the stack.
    4. ) is a closing symbol:
        Matches with the opening symbol on top of the stack ((). Continues.
    5. ] is a closing symbol:
        Matches with the opening symbol on top of the stack ([).
    6. } is a closing symbol:
        Matches with the opening symbol on top of the stack ({).
 */