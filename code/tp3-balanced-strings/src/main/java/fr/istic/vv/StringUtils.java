package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (ch == '{' || ch == '[' || ch == '(') {
                stack.push(ch);
            } else if (ch == '}' || ch == ']' || ch == ')') {
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


        private static boolean isMatchingPair(char open, char close) {
            return (open == '{' && close == '}') ||
                    (open == '[' && close == ']') ||
                    (open == '(' && close == ')');
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(isBalanced("{[][]}({})")); // true
        System.out.println(isBalanced("]["));         // false
        System.out.println(isBalanced("([)]"));        // false
        System.out.println(isBalanced("{"));           // false
        System.out.println(isBalanced("{(}{})"));   // false
    }
}
