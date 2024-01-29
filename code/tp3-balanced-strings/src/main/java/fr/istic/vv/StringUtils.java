package fr.istic.vv;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class StringUtils {

    private static final List<Character> OPENING_SYMBOL = List.of('{', '(', '[');
    private static final List<Character> CLOSING_SYMBOL = List.of('}', ')', ']');

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        if (str == null) throw new IllegalArgumentException("String is null");

        //a LIFO stack
        Deque<Character> stack = new ArrayDeque<>();

        for(char c : str.toCharArray()){
            if(OPENING_SYMBOL.contains(c)){
                stack.push(c);
            } else if (CLOSING_SYMBOL.contains(c)){
                if(!stack.isEmpty() && isMatching(stack.peek(), c)){
                    stack.pop();
                } else {
                    // cas où ")", "{})"
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isMatching(char openingSymbol, char closingSymbol) {
        return (openingSymbol == '{' && closingSymbol == '}') || (openingSymbol == '(' && closingSymbol == ')') || (openingSymbol == '[' && closingSymbol == ']');
    }
}
