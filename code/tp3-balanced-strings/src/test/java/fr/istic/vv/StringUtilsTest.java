package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
	/*
	 * Test de isBalanced pour une chaîne vide Une chaîne vide est equilibrée 
	 * input -> une chaine vide 
	 * Expected : isBalanced(input) -> true
	 */
	@Test
	public void emptyStringTest() {
		String input = "";
		boolean result = BalancedString.isBalanced(input);
		assertTrue(result);
	}

	/*
	 * Test de isBalanced pour une chaîne ne comportant pas de charactère ouvrant ni
	 * fermant Une telle chaîne est equilibrée 
	 * input -> une chaîne ne comportant pas de charactère ouvrant ni fermant Resultat attendu pour 
	 * Expected : isBalanced(input) -> true
	 */
	@Test
	public void noSymbolTest() {
		String input = "abcd";
		boolean result = BalancedString.isBalanced(input);
		assertTrue(result);
	}

	/*
	 * Test de isBalanced pour une chaîne non equilibrée 
	 * input -> Une chaîne non equilibrée ne comportant que des ouvrants et des fermants R
	 * Expected : isBalanced(input) -> false
	 */
	@Test
	public void unbalancedTest1() {
		String input = "[[()]}";
		boolean result = BalancedString.isBalanced(input);
		assertFalse(result);
	}

	/*
	 * Test de isBalanced pour une chaîne non equilibrée 
	 * input -> Une chaîne non equilibrée ne comportant pas que des ouvrants et des fermants 
	 * Expected : isBalanced(input) -> false
	 */
	@Test
	public void unbalancedTest2() {
		String input = "b[aa[(x)]z}";
		boolean result = BalancedString.isBalanced(input);
		assertFalse(result);
	}

	/*
	 * Test de isBalanced pour une chaîne equilibrée 
	 * input -> Une chaîne equilibrée ne comportant que des ouvrants et des fermants 
	 * Expected : isBalanced(input) -> true
	 */
	@Test
	public void balancedTest1() {
		String input = "[()]{}";
		boolean result = BalancedString.isBalanced(input);
		assertTrue(result);
	}

	/*
	 * Test de isBalanced pour une chaîne equilibrée 
	 * input -> Une chaîne equilibrée ne comportant pas que des ouvrants et des fermants 
	 * Expected : isBalanced(input) -> true
	 */
	@Test
	public void balancedTest2() {
		String input = "xxxxxx[m(a)qsdqsdqs]sqd{xad}qsdqds";
		boolean result = BalancedString.isBalanced(input);
		assertTrue(result);
	}
	
	/*
	 * Test de isBalanced pour une chaîne non equilibrée
	 * input -> Une chaîne non equilibrée du fait qu'a la fin un ouvrant ne possede pas de fermant
	 * Le but est de s'assurer de l'utilité de la deniere instruction "return pile.isEmpty()"
	 * Expected : isBalanced(input) -> false
	 */
	@Test
	public void unbalanced3() {
		String input = "xxxxxx[m(a)qsdqsdqs]sqd{xad}qsdqds(";
		boolean result = BalancedString.isBalanced(input);
		assertFalse(result);
	}

}
