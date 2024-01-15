# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.


Pour répondre aux question, je fournis le code + scénarios de test écrits d'une traite avant utilisation de PIT
La partitionnement des entrées est décrit dans la spécification des méthodes de test

## Answer

La classe BalancedString : 
```
public class BalancedString {

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
	 * Si on rencontre un fermant, on verifie qu'il correspond a l'ouvrant en haut de pile, si non return false
	 * Si on rencontre un fermant et que pile vide, return false (il n'y avait pas d'ouvrant correspondant)
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
```

Classe de test initiale : 
```
/*
 * Classe de test pour la classe BalancedString
 * Tester BalancedString revient a tester la seule operation qu'elle propose -> isBalanced(String input)
 */
class StringUtilsTest {
	/*
	 * Test de isBalanced pour une chaîne vide, une chaîne vide est equilibrée 
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
	 * Test de isBalanced pour une chaîne ne comportant pas de caractère ouvrant ni
	 * fermant, une telle chaîne est equilibrée 
	 * input -> une chaîne ne comportant pas de caractère ouvrant ni fermant
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
	 * input -> Une chaîne non equilibrée ne comportant que des ouvrants et des fermants 
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

}
```

Observations apres utilisation de PIT : 
Le code de BalancedString est couvert à 100%
En revanche 1 mutant parmi les 11 générés n'a pas été tué (score de 91%)
Commande de génération de mutants, exécutée à la racine du projet 
```
mvn clean test org.pitest:pitest-maven:mutationCoverage
```

Le mutant ayant survécu remplace dans la méthode isBalanced l'instruction "return pile.isEmpty();" par "return true;".
Cela nous amène donc à rajouter un scenario de test dans lequel la detection du chaîne non equilibrée se fait sur cette derniere instruction.

Nouvelle méthode de test : 
```
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
```

Re-éxecution de PIT, 11 mutants générés, 11 mutants tués.

