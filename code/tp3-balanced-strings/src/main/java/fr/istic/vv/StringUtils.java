package fr.istic.vv;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class StringUtils {

    private StringUtils() {
    }

    private enum BracketType {
        ROUND('(', ')'),
        CURLY('{', '}'),
        SQUARE('[', ']');

        private final char openValue;
        private final char closeValue;

        BracketType(char value, char close) {
            this.openValue = value;
            this.closeValue = close;
        }

        public char getOpenValue() {
            return openValue;
        }

        public char getCloseValue() {
            return closeValue;
        }
    }

    public static boolean isBalanced(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }

        if (str.isEmpty()) {
            return true;
        }

        Deque<BracketType> stack = new ArrayDeque<>();

        for (char c : str.toCharArray()) {
            BracketType bracketType = getBracketType(c);
            if (bracketType == null) {
                continue;
            }

            if (bracketType.getOpenValue() == c) {
                stack.push(bracketType);
            } else if (bracketType.getCloseValue() == c) {
                if (stack.isEmpty() || !isMatchingBracket(stack.pop(), bracketType)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static BracketType getBracketType(char c) {
        for (BracketType bracketType : BracketType.values()) {
            if (bracketType.getOpenValue() == c || bracketType.getCloseValue() == c) {
                return bracketType;
            }
        }
        return null;
    }

    private static boolean isMatchingBracket(BracketType openingBracket, BracketType closingBracket) {
        Map<BracketType, BracketType> matchingBrackets = Map.of(
                BracketType.ROUND, BracketType.ROUND,
                BracketType.CURLY, BracketType.CURLY,
                BracketType.SQUARE, BracketType.SQUARE
        );

        return matchingBrackets.get(openingBracket) == closingBracket;
    }
}