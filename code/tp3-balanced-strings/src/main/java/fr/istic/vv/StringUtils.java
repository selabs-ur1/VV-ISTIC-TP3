package fr.istic.vv;

public class StringUtils {

    public static boolean isBalanced(String str) {
        for (int i = 0; i < str.length(); i++){
            // Check there a no closing before opening
            if (str.charAt(i) == ')' || str.charAt(i) == ']' || str.charAt(i) == '}') return false;

            // First find an open bracket
            if (str.charAt(i) == '('){
                // Check current char is not the last one (no possible ending)
                if (i+1 > str.length()-1) return false;
                // Then look for the corresponding ending
                for (int j = i; j < str.length(); j++){
                    if (str.charAt(j) == ')') {
                        // Once end if found, extract the substring inside and test it recursively
                        String substring = str.substring(i+1,j);
                        if (isBalanced(substring)){
                            // If substring is valid cut it with the current brackets out of the main string and go back 1 on i
                            str = new StringBuilder(str).replace(i,j+1,"").toString();
                            i--;
                            break;
                        } else return false;
                    } else if (j == str.length()-1) return false;
                }
            }

            // First find an open bracket
            else if (str.charAt(i) == '['){
                // Check current char is not the last one (no possible ending)
                if (i+1 > str.length()-1) return false;
                // Then look for the corresponding ending
                for (int j = i; j < str.length(); j++){
                    if (str.charAt(j) == ']') {
                        // Once end if found, extract the substring inside and test it recursively
                        String substring = str.substring(i+1,j);
                        if (isBalanced(substring)){
                            // If substring is valid cut it with the current brackets out of the main string and go back 1 on i
                            str = new StringBuilder(str).replace(i,j+1,"").toString();
                            i--;
                            break;
                        } else return false;
                    } else if (j == str.length()-1) return false;
                }
            }

            // First find an open bracket
            else if (str.charAt(i) == '{'){
                // Check current char is not the last one (no possible ending)
                if (i+1 > str.length()-1) return false;
                // Then look for the corresponding ending
                for (int j = i; j < str.length(); j++){
                    if (str.charAt(j) == '}') {
                        // Once end if found, extract the substring inside and test it recursively
                        String substring = str.substring(i+1,j);
                        if (isBalanced(substring)){
                            // If substring is valid cut it with the current brackets out of the main string and go back 1 on i
                            str = new StringBuilder(str).replace(i,j+1,"").toString();
                            i--;
                            break;
                        } else return false;
                    } else if (j == str.length()-1) return false;
                }
            }
        }
        return !str.contains("(") && !str.contains("[") && !str.contains("{") &&
                !str.contains(")") && !str.contains("]") && !str.contains("}");
    }
}
