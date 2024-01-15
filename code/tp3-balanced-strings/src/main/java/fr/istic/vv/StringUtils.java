package fr.istic.vv;

public class StringUtils {
    private StringUtils() {}

    public static boolean isBalanced(String str) {
        int len = str.length();
        if (len % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);

            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            }
            else if (c == '}' || c == ']' || c == ')') {
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), c)) {
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
}
