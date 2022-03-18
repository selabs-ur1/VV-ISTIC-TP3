package fr.istic.vv;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        //replace all character excepting {([])}
        if(str == null) {
            return false;
        }
        str = str.replaceAll("[^{\\[()\\]}]", "");

        //needed for the loop
        //all character excepting {([])} will be replaced
        String strEnd = "a";
        while(str != strEnd) {
            if( strEnd != "a") {
                str = strEnd;
            }
            strEnd = str.replaceAll("\\(\\)", "").replaceAll("\\[\\]", "").replaceAll("\\{\\}", "");
        }
        str = strEnd;

        return str.length() == 0;
    }

}
