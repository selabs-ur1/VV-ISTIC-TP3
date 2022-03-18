package fr.istic.vv;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class StringUtils {

    private Map<Character,Character> characterMap;

    public StringUtils() {

        this.characterMap= new HashMap<Character,Character>();
    }

    public void setCharacterMap(Map<Character, Character> characterMap) {
        this.characterMap = characterMap;
    }

    public void  initFromStrings(String[] array) throws WrongInitialSetException, EmptyInitialSetException {
        if (array.length <= 0) throw new EmptyInitialSetException();
        for( String entry : array){
            if(entry.length() != 2) throw new WrongInitialSetException();
            this.characterMap.put(entry.toCharArray()[0],entry.toCharArray()[1]);
        }
    }

    public boolean isOpeningSymbol(char c){
        return this.characterMap.containsKey(c);
    }

    public boolean isClosingSymbol(char c){
        return this.characterMap.containsValue(c);
    }

    public boolean isCorrespondingSymbol(char open,char close){
        return (characterMap.containsKey(open) &&characterMap.get(open).equals(close) );
    }

    public boolean isBalanced(String str) throws EmptyInitialSetException, SymbolNotInSetException {
        if (this.characterMap.isEmpty()) throw new EmptyInitialSetException();
        LinkedList<Character> queue = new LinkedList<Character>();
        for (Character c : str.toCharArray()){
            if(isOpeningSymbol(c)){
                queue.addLast(c);
            }else if(isClosingSymbol(c)
                    && !queue.isEmpty()
                    && isCorrespondingSymbol(queue.getLast(),c)){
                queue.removeLast();
            }else if (!isOpeningSymbol(c) && !isClosingSymbol(c)) throw new SymbolNotInSetException(c);
        }
        return queue.isEmpty();
    }

    public class EmptyInitialSetException extends Exception {
        @Override
        public String getMessage() {
            return "Initial HashMap of opening and closing symbol is empty.";
        }
    }

    @RequiredArgsConstructor
    public class SymbolNotInSetException extends Exception {
        private final char c;
        @Override
        public String getMessage() {
            return "Given symbol '"+c+"' is not in symbol table.";
        }
    }

    public class WrongInitialSetException extends Exception {
        @Override
        public String getMessage() {
            return "Given initial set is not properly formatted - cannot map symbols.";
        }
    }

}
