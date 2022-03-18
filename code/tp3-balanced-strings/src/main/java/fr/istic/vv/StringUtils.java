package fr.istic.vv;

public class StringUtils {

    private StringUtils() {
    }

    public static boolean isBalanced(String str) {
        // Suppression de tous les caractères n'étant pas des (){}[]
        str = str.replaceAll("[^[\\(\\)][\\[\\]][\\{\\}]]", "");

        // Suppression des couples atomiques
        while (str.contains("()") || str.contains("[]") || str.contains("{}")) {
            str = str.replaceAll("\\(\\)", "")
                    .replaceAll("\\[\\]", "")
                    .replaceAll("\\{\\}", "");
        }
        //Si le résultat est vide alors tous les couples ont étés enlevés et la string est valide
        return (str.length() == 0);
    }

}
