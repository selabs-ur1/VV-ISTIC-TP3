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

1. 
Fonction :
```java
private static boolean isMatchingPair(char character1, char character2) {
        if (character1 == '(' && character2 == ')') {
            return true;
        } else if (character1 == '{' && character2 == '}') {
            return true;
        } else return character1 == '[' && character2 == ']';
    }

    private static boolean isOpenBracket(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private static boolean isCloseBracket(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    public static boolean isBalanced(String str) {
        Stack<Character> openBrackets = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(isOpenBracket(c)) {
                openBrackets.push(c);
            } else if(isCloseBracket(c)) {
                if(openBrackets.isEmpty() || !isMatchingPair(openBrackets.pop(), c)) {
                    return false;
                }
            }
        }
        return openBrackets.isEmpty();
    }
```
Chaîne vide : ""

Chaînes courtes :
"()" (parenthèses, équilibrée)
"(]" (parenthèses et crochets, déséquilibrée)
"[]" (crochets, équilibrée)
"[}" (crochets et accolades, déséquilibrée)
"{}" (accolades, équilibrée)
"{)" (accolades et parenthèses, déséquilibrée)

Chaînes moyennes :
"([])" (mélange, équilibrée)
"([)]" (mélange, déséquilibrée)
"{[()]}" (mélange, équilibrée)
"{[(])}" (mélange, déséquilibrée)
"a(b)c" (caractères non pertinents, équilibrée)
"a(b]c" (caractères non pertinents, déséquilibrée)

Chaînes longues :
"({[({[({[({[()]})]})]})]})" (mélange, équilibrée)
"({[({[({[({[()]})]})]})]})}" (mélange, déséquilibrée)
"a{b[c(d)e]f}g" (caractères non pertinents, équilibrée)
"a{b[c(d)e]f}g)" (caractères non pertinents, déséquilibrée)

2. Ligne de code pas couverte par les cas de tests : 
- `return openBrackets.isEmpty();`

Ajout de tests pour couvrir cette ligne de code :
```java
@Test
    void testSingleOpenParentheses() {
        assertFalse(isBalanced("("));
    }

@Test
    void testMediumNotEqualStack() {
        assertFalse(isBalanced("()]"));
    }
```

3. Aucun prédicat utilisant plus de deux opérateurs booléens.

4. Commande utilisé pour PIT : `mvn org.pitest:pitest-maven:mutationCoverage`	

Résultat de PIT :
```
================================================================================
- Statistics
================================================================================
>> Line Coverage: 16/17 (94%)
>> Generated 30 mutations Killed 30 (100%)
>> Mutations with no coverage 0. Test strength 100%
>> Ran 58 tests (1.93 tests per mutation)
```

Tous les mutants ont été tués.