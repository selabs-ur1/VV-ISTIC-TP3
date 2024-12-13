package fr.istic.vv;

public class StringUtils {

    private StringUtils() {
    }

    public static boolean isBalanced(String str) {
        boolean isBalanced;

        if (str.isEmpty()) {
            isBalanced = true;
        } else if (str.length() % 2 != 0) {
            isBalanced = false;
        } else {
            // Check first char
            char firstChar = str.charAt(0);
            switch (firstChar) {
                case '(':
                case '[':
                case '{':
                    // Search for index of the corresponding closing char
                    int closingCharIndex = str.indexOf(getClosingChar(firstChar));
                    if (closingCharIndex == -1) {
                        isBalanced = false;
                    } else {
                        // Check the substring between the first and closing char
                        isBalanced = isBalanced(str.substring(1, closingCharIndex));
                        if (isBalanced) {
                            // Check the rest of the string
                            isBalanced = isBalanced(str.substring(closingCharIndex + 1));
                        }
                    }
                    break;
                default:
                    isBalanced = false;
            }
        }

        return isBalanced;
    }

    private static char getClosingChar(char openingChar) {
        switch (openingChar) {
            case '(':
                return ')';
            case '[':
                return ']';
            case '{':
                return '}';
            default:
                throw new IllegalArgumentException("Invalid opening char: " + openingChar);
        }
    }
}
