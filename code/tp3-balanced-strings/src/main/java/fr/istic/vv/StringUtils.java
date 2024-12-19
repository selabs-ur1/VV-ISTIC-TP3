package fr.istic.vv;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    private StringUtils() {
    }

    /**
     * A string containing grouping symbols {}[]() is said to be balanced if every
     * open symbol {[( has a matching closed symbol )]}
     * and the substrings before, after and between each pair of symbols is also
     * balanced. The empty string is considered as balanced.
     * For example: {[][]}({}) is balanced, while ][, ([)], {, {(}{} are not.
     */

    public static boolean isBalanced(String str) {
        List<Character> openingSymbols = new ArrayList<>();

        if (str.isEmpty()) { // cas où le string est vide
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '{' || str.charAt(i) == '[' || str.charAt(i) == '(') {
                openingSymbols.add(str.charAt(i));
            }
        }
        int indexInOpens = -1;
        for (int indexInStr = 0; indexInStr < str.length(); indexInStr++) {
            char popped;
            switch (str.charAt(indexInStr)) {
                case '}':
                    if (indexInOpens == -1) {
                        return false;
                    }
                    popped = openingSymbols.remove(indexInOpens);
                    indexInOpens--;
                    if (popped != '{')
                        return false;
                    break;

                case ']':
                if (indexInOpens == -1) {
                    return false;
                }
                    popped = openingSymbols.remove(indexInOpens);
                    indexInOpens--;
                    if (popped != '[')
                        return false;
                    break;

                case ')':
                if (indexInOpens == -1) {
                    return false;
                }
                    popped = openingSymbols.remove(indexInOpens);
                    if (popped != '(')
                        return false;
                    break;

                case '(':
                    indexInOpens++;
                    break;

                case '{':
                    indexInOpens++;
                    break;

                case '[':
                    indexInOpens++;
                    break;
                default:
                    break;
            }
        }
        if (openingSymbols.size() == 0)
            return true;
        return false;
        // garder liste avec tous les ouvrants, et des quon tombe sur un fermant,
        // verifier quil correspond au dernier ouvrant enregistré
    }

  /* public static void main(String[] args) { 
        //pour les tests à la main avant tout pour tester fonction avant d'écrire les test JUnit et les questions de l'exo
        System.out.println(isBalanced("")+" (T)"); // fonctionne
        System.out.println(isBalanced("[")+" (F)"); // fonctionne
        System.out.println(isBalanced("{}")+" (T)"); // fonctionne
        System.out.println(isBalanced("[]")+" (T)"); // fonctionne
        System.out.println(isBalanced("()")+" (T)"); // fonctionne
        System.out.println(isBalanced("{[]}")+" (T)"); // fonctionne

        System.out.println(isBalanced("][")+" (F)");
        System.out.println(isBalanced("([)]")+" (F)");
        System.out.println(isBalanced("{(}{}")+" (F)");
        System.out.println(isBalanced("{}{}}")+" (F)");

        System.out.println(isBalanced("{[(]}")+" (F)"); // fonctionne

        System.out.println(isBalanced("{}{}")+" (T)"); // fonctionne
        System.out.println(isBalanced("{}(}")+" (F)"); // fonctionne
        //if impair ca passe pas, si pair faut voir
    } */ 

}
