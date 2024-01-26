package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        Stack<Character> st = new Stack<Character>();

        for(char chr : str.toCharArray()) {
            switch(chr) {

                case '{':
                case '(':
                case '[':
                    st.push(chr);
                    break;

                case ']':
                    if(st.isEmpty() || st.pop() != '[')
                        return false;
                    break;
                case ')':
                    if(st.isEmpty() || st.pop() != '(')
                        return false;
                    break;
                case '}':
                    if(st.isEmpty() || st.pop() != '{')
                        return false;
                    break;
                default:
                    break;
            }
        }
        return st.isEmpty();
    }
}