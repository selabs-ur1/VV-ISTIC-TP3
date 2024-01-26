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

## Answer
Code :

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            if (isOpenSymbol(ch)) {
                stack.push(ch);
            } else if (isCloseSymbol(ch)) {
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean isOpenSymbol(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }

    private static boolean isCloseSymbol(char ch) {
        return ch == ')' || ch == '}' || ch == ']';
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }
}

class StringUtilsTest {

    @Test
    public void testIsBalancedForBalancedStrings() {
        assertTrue(StringUtils.isBalanced("{([])}"));
    }

    @Test
    public void testIsBalancedForUnbalancedStrings() {
        assertFalse(StringUtils.isBalanced("{[)}"));
    }

    @Test
    public void testIsBalancedForEmptyString() {
        assertTrue(StringUtils.isBalanced(""));
    }

    @Test
    public void testIsBalancedForStringWithoutGroupingSymbols() {
        assertTrue(StringUtils.isBalanced("abc"));
    }

    @Test
    public void testIsBalancedForStringWithSpaces() {
        assertTrue(StringUtils.isBalanced(" { [ ] } "));
    }

    @Test
    public void testIsBalancedForStringWithOtherCharacters() {
        assertTrue(StringUtils.isBalanced("a(b)c[d]e{f}"));
    }
}

1) Nous avons identifé 5 blocks de partition : 
- Chaine équilibrée : "{()}"
- Chaine non équilibrée : "{(})"
- Chaine vide : ""
- Chaine sans symbole : "abc"
- Chaine avec des espaces : "( { } )"
- Chaine chaine valide avec des caractères : "(a{opr})"
  
3) On a ici un coverage de 100%, on a essayé de tester tout les cas possibles en faisant un test pour chaque block de partition relevé précedement.

3) Notre code ne prennant qu'un paramètre String, il n'est pas vraiment utile d'utiliser le template Base Choice Coverage.

4) PIT me dit que la mutation de la ligne "return stack.isEmpty();" survie dans il passe la valeur de retour à vrai. Si j'ai bien compris, cela signifie qu'il n'y a aucun cas ou cette ligne peut valoir faux.
En corrigeant cette ligne on obtient un coverage de 100%. Seule une ligne n'est pas testée, celle du constructeur.


