package fr.istic.vv;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class StringUtils {

    private static final Map<Character, Character> expectedOpener = new HashMap<Character, Character>() {
		{
			put('}', '{');
			put(')', '(');
			put(']', '[');
		}
	};

	private static final Set<Character> closers = new HashSet<Character>() {
		{
			add('}');
			add(')');
			add(']');
		}
	};

	private static final Set<Character> openers = new HashSet<Character>() {
		{
			add('{');
			add('(');
			add('[');
		}
	};

	/*
	 * Utilisation dune pile
	 * Si on rencontre un ouvrant, on l'empile
	 * Si on rencontre un fermant, on verifie quil correspond a louvrant en haut de pile, si non return false
	 * Si on rencontre un fermant et que pile vide, return false (il ny avait pas douvrant correspondant)
	 * Fin, si pile vide, return true, sinon false
	 */
	public static boolean isBalanced(String str) {
		
		//Chaîne vide est equilibrée, histoire d'eviter de creer une pile pour rien
		if(str.isEmpty())
			return true;
		
		Stack<Character> pile = new Stack<Character>();
		
		for(int i=0 ; i<str.length() ; i++) {
			
			if(openers.contains(str.charAt(i)))
				pile.push(str.charAt(i));
			
			else if(closers.contains(str.charAt(i)))
			{
				if(pile.isEmpty() || (pile.pop() != expectedOpener.get(str.charAt(i))))
					return false;
			}
		}
		
		return pile.isEmpty();
	}

}
