package fr.istic.vv;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

   public static boolean isBalanced(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }

        Stack<Character> symbolStack = new Stack<>();

        for (char ch : str.toCharArray()) {

            switch (ch){
                case '{':
                case '(':
                case '[':
                    symbolStack.push(ch);
                    break;
                case '}':
                    if (symbolStack.isEmpty() || symbolStack.pop() != '{'){
                        return false;
                    }
                    break;
                case ')':
                    if (symbolStack.isEmpty() || symbolStack.pop() != '('){
                        return false;
                    }
                    break;
                case ']':
                    if (symbolStack.isEmpty() || symbolStack.pop() != '['){
                        return false;
                    }
                default:
                    break;
            }
        }
        return symbolStack.isEmpty();
    }
}
