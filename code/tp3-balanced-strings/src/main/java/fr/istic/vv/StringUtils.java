package fr.istic.vv;

import java.util.Stack;

/**
 * Classe StringUtils
 */
public class StringUtils {
    private Stack<Character> stack = new Stack<Character>();

    /**
     * Method isBalanced : check if the string in parameter is balanced
     * @param str : string
     * @return true if is balanced else false
     */
    public boolean isBalanced(String str) {
        while(!stack.isEmpty()) stack.pop();
        if (str == null) throw new IllegalArgumentException();
        if (str.length() == 0) return true;
        if (str.length() % 2 == 1) return false;
        // Walk the string
        for (char character : str.toCharArray()) {
            switch (character) {
                // These characters have to be stacked
                case '{':
                case '[':
                case '(':
                    stack.push(character);
                    break;
                    // pop all the other elements and check
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop()!= '[') return false;
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop()!= '(') return false;
                    break;
                default:
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
