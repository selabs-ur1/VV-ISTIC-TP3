package fr.istic.vv;

import java.util.Objects;
import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    /**
     Check if the closing parenthesis match with the last char
     */
    private static boolean isMatching(char previousChar, char currentChar){
        boolean matchingParenthesis = previousChar == '(' && currentChar==')';
        boolean matchingBrackets = previousChar == '[' && currentChar==']';
        boolean matchingBraces = previousChar == '{' && currentChar=='}';

        return matchingParenthesis || matchingBrackets || matchingBraces;
    }

    /**
     Check if char is an opening bracket/parenthesis
     */
    private static boolean isOpeningBracket(char c){

        return c=='(' || c=='[' || c=='{';
    }

    public static boolean isBalanced(String str) {
        if(Objects.isNull(str)) return false;

        //Create LIFO
        Stack<Character> stack = new Stack<>();

        //for each char
        for(char c : str.toCharArray()){

            if(stack.isEmpty()){
                stack.push(c);
            }
            else if(isOpeningBracket(c)) {
                stack.push(c);
            }
            else if(isMatching(stack.lastElement(),c)) {
                stack.pop();
            }
            else {
                return false;
            }

        }

        return stack.isEmpty();
    }

}
