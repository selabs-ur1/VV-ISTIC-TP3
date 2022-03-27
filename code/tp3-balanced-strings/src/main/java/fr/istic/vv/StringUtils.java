package fr.istic.vv;

import java.util.ArrayList;
import java.util.List;


public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {

        char[] chars = str.toCharArray();
        List<Character> openSymbols = new ArrayList<>();

        if (str.length() == 0) {
            return true;
        } else if (str.length()%2 != 0) {
                return false;
            }


        for (int i = 0; i < chars.length; i++) {
            char chari = chars[i];

            if (!openSymbols.isEmpty()) {
                char lastOpenSymbol = openSymbols.get(openSymbols.size() - 1);

                switch (chari) {
                    case '{':
                    case '[':
                    case '(':
                        openSymbols.add(chari);
                        break;
                    case ')':
                        if (lastOpenSymbol == '(') {
                            openSymbols.remove(openSymbols.size() - 1);
                        } else {
                            return false;
                        }
                        ;
                        break;
                    case ']':
                        if (lastOpenSymbol == '[') {
                            openSymbols.remove(openSymbols.size() - 1);
                        } else {
                            return false;
                        }
                        ;
                        break;
                    case '}':
                        if (lastOpenSymbol == '{') {
                            openSymbols.remove(openSymbols.size() - 1);
                        } else {
                           return false;
                        }
                        ;
                        break;
                }

            } else {
                if (chari == ')' || chari == ']' || chari == '}') {
                  //  System.out.println("open symbol list is empty");
                    return false;
                } else {
                    openSymbols.add(chari);
                }
            }

        }

        return openSymbols.isEmpty();

    }

}
