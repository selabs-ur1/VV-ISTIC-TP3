package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {
    }

    public static boolean isBalanced(String str) {

        if (str == null || str.length() == 0) {
            return true;
        }

        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else if (c == '}' || c == ']' || c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if (c == '}' && top != '{' || c == ']' && top != '[' || c == ')' && top != '(') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

}
