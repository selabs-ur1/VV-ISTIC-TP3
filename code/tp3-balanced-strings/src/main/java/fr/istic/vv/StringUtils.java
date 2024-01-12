package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        if (str == null) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
            } else if (!stack.isEmpty()) {
                char top = stack.pop();

                if (c == '}' && top != '{') {
                    return false;
                } else if (c == ')' && top != '(') {
                    return false;
                } else if (c == ']' && top != '[') {
                    return false;
                }
            } else {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
