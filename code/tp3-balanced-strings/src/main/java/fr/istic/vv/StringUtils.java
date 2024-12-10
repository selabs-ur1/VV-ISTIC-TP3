package fr.istic.vv;
import java.util.Stack;

public class StringUtils {

    private StringUtils() {}
    
    private static boolean isMatchingPair(char character1, char character2) {
        if (character1 == '(' && character2 == ')') {
            return true;
        } else if (character1 == '{' && character2 == '}') {
            return true;
        } else return character1 == '[' && character2 == ']';
    }

    private static boolean isOpenBracket(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private static boolean isCloseBracket(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    public static boolean isBalanced(String str) {
        Stack<Character> openBrackets = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(isOpenBracket(c)) {
                openBrackets.push(c);
            } else if(isCloseBracket(c)) {
                if(openBrackets.isEmpty() || !isMatchingPair(openBrackets.pop(), c)) {
                    return false;
                }
            }
        }
        return openBrackets.isEmpty();
    }

}
