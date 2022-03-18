# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

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
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written so far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer

1.
    - {]]({))]]]}
    - {([])}
    - {[][]}({})
    - single opening sign
    - single closing sign
    - empty string
    - string with no signs
    - open then closing matching sings
    - (pouet[cacah{chipolata}uette])

2. 
    J'avais juste un else return false de non exécuté, j'ai rajouter des tests contenant une brancket seule a l'intérieur d'une autre bracket valide pour passer dessus.

3. Rien a ajouter

4. 
    Statistics
    - Line Coverage: 36/37 (97%)
    - Generated 59 mutations Killed 42 (71%)
    - Mutations with no coverage 3. Test strength 75%
    - Ran 130 tests (2.2 tests per mutation)

    J'ai remplacé le `return true` de isBalanced par :
    ```java
    return !str.contains("(") && !str.contains("[") && !str.contains("{") &&
                !str.contains(")") && !str.contains("]") && !str.contains("}");
    ```
    Les statistiques sont passées a:
    - Line Coverage: 37/38 (97%)
    - Generated 65 mutations Killed 48 (74%)
    - Mutations with no coverage 3. Test strength 77%
    - Ran 198 tests (3.05 tests per mutation)

    La plupart des mutants survivants sont des faux positifs, une partie est impossible a tester car les erreurs mentionnées sont forcément catchées avant et le mutant en question est impossible a reproduire avec un test.