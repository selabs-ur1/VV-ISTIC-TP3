package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            if (ch == '{' || ch == '[' || ch == '(') {
                stack.push(ch);
            } else if (!stack.isEmpty()) {
                Character top = stack.peek();
                if (ch == '}' && top == '{') {
                    stack.pop();
                } else if (ch == ']' && top == '[') {
                    stack.pop();
                } else if (ch == ')' && top == '(') {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }
}
