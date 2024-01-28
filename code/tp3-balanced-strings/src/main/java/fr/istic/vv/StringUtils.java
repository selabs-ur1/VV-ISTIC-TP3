package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    public static boolean isBalanced(String str) {
        if(str.isEmpty()) {
            return true;
        }

        Stack<Character> groupingSymbolStack = new Stack<>();
        for(char c : str.toCharArray()) {
            if(c == '{') {
                groupingSymbolStack.push('}');
            } else if(c == '[') {
                groupingSymbolStack.push(']');
            } else if(c == '(') {
                groupingSymbolStack.push(')');
            } else if (")]}".contains(String.valueOf(c))) {
                if(groupingSymbolStack.isEmpty()) {
                    return false;
                }
                else {
                    char last = groupingSymbolStack.pop();
                    if (c != last) {
                        return false;
                    }
                }
            }
        }
        return groupingSymbolStack.isEmpty();
    }
}
