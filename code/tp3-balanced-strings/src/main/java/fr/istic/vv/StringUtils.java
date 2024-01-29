package fr.istic.vv;

import java.util.*;

public class StringUtils {

//    private StringUtils() {}

    public static boolean isBalanced(String str) {
        Deque<Character> stack = new ArrayDeque<>();

        for (Character c: str.toCharArray()) {
            switch (c) {
                case '{', '(', '[' -> stack.push(c);
                case '}' -> {
                    if (!stack.isEmpty() && !stack.peek().equals('{')) {
                        return false;
                    } else if (stack.isEmpty()) {
                        return false;
                    }
                    stack.pop();
                }
                case ')' -> {
                    if (!stack.isEmpty() && !stack.peek().equals('(')) {
                        return false;
                    } else if (stack.isEmpty()) {
                        return false;
                    }
                    stack.pop();
                }
                case ']' -> {
                    if (!stack.isEmpty() && !stack.peek().equals('[')) {
                        return false;
                    } else if (stack.isEmpty()) {
                        return false;
                    }
                    stack.pop();
                }
            }
        }

        return stack.isEmpty();
    }

}
