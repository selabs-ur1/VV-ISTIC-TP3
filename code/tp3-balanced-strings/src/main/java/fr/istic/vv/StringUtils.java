package fr.istic.vv;

import java.io.IOException;
import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        int flag = 1, i = 0, l = str.length();
        Stack<Character> st = new Stack<Character>();
        for( i = 0; i < l; i++ ){
            char ch = str.charAt(i);
            if( ch == '(' || ch == '[' || ch == '{' ){
                st.push( ch );
            }else {
                if( st.empty() ){
                    flag = 0; break;
                } else {
                    char c = (char) st.pop();
                    if( (c == '(' && ch != ')') || (c == '[' && ch != ']') || (c == '{' && ch != '}') ){
                        flag = 0; break;
                    }
                }
            }
        }
        if( flag == 1 && st.empty() ) { return true; } 
        else  {return false; }
    }

    public static void main(String args[]) throws IOException {
    }
}
