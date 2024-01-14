package fr.istic.vv;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StringUtils {

    private static final Map<Character, Character> bracketPairs = new HashMap<>();
    static {
        bracketPairs.put(']', '[');
        bracketPairs.put('}', '{');
        bracketPairs.put(')', '(');
    }

    public StringUtils() {
    }

    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (bracketPairs.containsValue(currentChar)) {
                stack.push(currentChar);
            } else if (bracketPairs.containsKey(currentChar)) {
                if (stack.isEmpty() || stack.pop() != bracketPairs.get(currentChar)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}


