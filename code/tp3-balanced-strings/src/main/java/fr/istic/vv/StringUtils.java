package fr.istic.vv;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    private StringUtils() {}
    
    private static Character getOpposite(Character c) {
		switch(c) {
		case ')': return '(';
		case '}': return '{';
		case ']': return '[';
		}
		return null;
    }
    
    private static boolean isBalancedAux(String str, List<Character> currents) {
    	if(str.length()==0) return true;
    	Character c = str.charAt(0);
    	if(c == '(' || c == '{' || c == '[') {
    		currents.add(c);
    		return isBalancedAux(str.substring(1), currents);
    	}
    	if(c == ')' || c == '}' || c == ']') {
    		if(currents.isEmpty() || !currents.remove(currents.size()-1).equals(getOpposite(c))) return false;
    		return isBalancedAux(str.substring(1), currents);
    	}
    	return isBalancedAux(str.substring(1), currents);
    }

    public static boolean isBalanced(String str) {
        if(str == null) return true;
    	List<Character> l = new ArrayList<Character>();
        return isBalancedAux(str, l) && l.isEmpty();
    }

}
