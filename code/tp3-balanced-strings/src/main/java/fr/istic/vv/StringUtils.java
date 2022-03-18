package fr.istic.vv;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private StringUtils() {}

    /**
     * Checks if the string has matching opening and closing brackets
     * @param str input string to test
     * @return true if all {, [ or ( symbols have their matching }, ], ) symbols (and not overlapping)
     */
    public static boolean isBalanced(String str) {
        if(str==null){
            return true;
        }
        //1) remove all but ( { [ ] } ) symbols
        String onlySymbols = str.replaceAll("([^\\{\\[\\(\\)\\]\\}])", "");

        String noMatchingClosed = onlySymbols;

        Pattern p = Pattern.compile("(\\(\\))|(\\[\\])|(\\{\\})");
        Matcher m = p.matcher(onlySymbols);
        //2) if the string has [] or {} or ()
        while(m.find()){
            //remove those self closing symbols
            noMatchingClosed = m.replaceAll("");
            //and try again
            m = p.matcher(noMatchingClosed);
        }

        //then if the string is empty, it means all symbols have a closing and the string is balanced
        return noMatchingClosed.isEmpty();
    }

}
