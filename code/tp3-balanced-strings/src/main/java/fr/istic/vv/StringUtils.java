package fr.istic.vv;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str){
        while (str.contains("()") || str.contains("[]") || str.contains("{}") || str.matches("(.*)\\b(.*)")){
            str = str.replaceAll("\\(\\)", "")
                    .replaceAll("\\[\\]", "")
                    .replaceAll("\\{\\}", "")
                    .replaceAll("[^[\\(\\)][\\[\\]][\\{\\}]]", "");
        }
        return (str.length() == 0);
    }
}
