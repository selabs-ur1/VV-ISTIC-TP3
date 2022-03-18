package fr.istic.vv;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        if(str == null || str.length() == 0) return true;
        Character c = str.charAt(0);
        Integer close = -1;
        switch (c) {
            case '(':
                close = str.indexOf(')');
                break;
            case '[':
                close = str.indexOf(']');
            break;
            case '{':
                close = str.indexOf('}');
                break;
        
            case ')':
            case ']' :
            case '}' :
            return false;
            default:
            return isBalanced(str.substring(1));
        }
                if( close == -1) return false;
                if(close <= 1) return isBalanced(str.substring(close+1));
                return isBalanced(str.substring(1, close)) && isBalanced(str.substring(close+1));
    }

}
