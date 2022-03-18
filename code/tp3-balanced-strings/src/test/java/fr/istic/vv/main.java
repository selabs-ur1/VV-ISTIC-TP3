package fr.istic.vv;

import java.util.Scanner;

public class main {

    public static void main(String[] args) throws StringUtils.WrongInitialSetException, StringUtils.SymbolNotInSetException, StringUtils.EmptyInitialSetException {
        StringUtils tool  = new StringUtils();

        Scanner scanner= new Scanner(System.in);
        System.out.print("Write the initial set of input with this format \"AB CD\"\n" +
                "Where A and C are opening symbols and B and C the closing ones, then type Enter. For instance:\n" +
                "\"() {} []\"\n>");

        String initString = scanner.nextLine();

        String[] map = initString.split(" ");
        tool.initFromStrings(map);

        System.out.print("Write the string you want to know if balanced.\n" +
                "WARNING: blank spaces are considered as a real entry !\n>");

        while (!initString.isBlank()){
            initString = scanner.nextLine();

            if (tool.isBalanced(initString)) {
                System.out.print("The string \"" + initString + "\" is balanced.\n>");
            } else {
                System.out.print("The string \"" + initString + "\" is not balanced.\n>");
            }
        }

    }
}
