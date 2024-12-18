package fr.istic.vv;

import java.util.Arrays;

public class StringUtils {

    private StringUtils() {
    }

    /**
     * A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.
     *
     * For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.
     *
     * @param str the string to test
     * @return `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.
     */
    public static boolean isBalanced(String str) {
        int i = 0;
        int j = 0;
        char[] pile = new char[str.length()];

        while (i < str.length()) {
            char curr = str.charAt(i);

            if (curr == '{') {
                pile[j++] = '}';
            } else if (curr == '(') {
                pile[j++] = ')';
            } else if (curr == '[') {
                pile[j++] = ']';
            } else {
                j--;
                if (j<0 || pile[j] != curr) {
                    return false;
                }
            }
            i++;
        }
        return j == 0;
    }

}
