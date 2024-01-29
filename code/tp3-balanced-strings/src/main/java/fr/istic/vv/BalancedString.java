package fr.istic.vv;

import java.util.Stack;

public class BalancedString {

    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.add(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.lastElement() != '(') {
                    return false;
                }
                stack.pop();
            } else if (c == ']') {
                if (stack.isEmpty() || stack.lastElement() != '[') {
                    return false;
                }
                stack.pop();
            } else if (c == '}') {
                if (stack.isEmpty() || stack.lastElement() != '{') {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

}