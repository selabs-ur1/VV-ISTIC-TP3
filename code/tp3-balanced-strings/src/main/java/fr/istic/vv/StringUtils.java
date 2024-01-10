package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) throws IllegalArgumentException {
        Stack<Character> stack = new Stack<>();
        if (str == null) {
            throw new IllegalArgumentException("String cannot be null");
        }

        for (char c : str.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (!stack.empty() && matchesOpening(stack.peek(), c)) {
                stack.pop();
            }
        }

        return stack.empty();
    }

    public static boolean matchesOpening(char opening, char closing) {
        if (opening == '(' && closing == ')') {
            return true;
        } else if (opening == '[' && closing == ']') {
            return true;
        } else return opening == '{' && closing == '}';
    }
}