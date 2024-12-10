package fr.istic.vv;

public class StringUtils {

    private StringUtils() {
    }

    public static boolean isBalanced(String str) {
        if (!str.isEmpty()) {
            char firstCharacter = str.charAt(0);
            System.out.println("String is not empty");
            if (firstCharacter == '{' || firstCharacter == '(' || firstCharacter == '[') {
                System.out.println("Opening found");
                int indexOfClose;
                switch (firstCharacter) {
                    case '{':
                        System.out.println("Opening : {");
                        indexOfClose = str.indexOf('}');
                        break;
                    case '(':
                        System.out.println("Opening : (");
                        indexOfClose = str.indexOf(')');
                        break;
                    case '[':
                        System.out.println("Opening : [");
                        indexOfClose = str.indexOf(']');
                        break;
                    default:
                        indexOfClose = -1;
                        break;
                }
                if (indexOfClose == -1) {
                    System.out.println("No closing");
                    return false;
                } else {
                    if (indexOfClose == str.length()-1) {
                        System.out.println("Closing is the last character");
                        return isBalanced(str.substring(1, indexOfClose));
                    } else {
                        System.out.println("Closing is not the last character");
                        return isBalanced(str.substring(1, indexOfClose)) && isBalanced(str.substring(indexOfClose + 1));
                    }
                }
            } else if(firstCharacter == ')' || firstCharacter == ']' || firstCharacter == '}') {
                System.out.println("Closing without opening");
                return false;
            } else {
                System.out.println("Not a closing and not an opening");
                return isBalanced(str.substring(1));
            }

        } else {
            return true;
        }
    }
}
