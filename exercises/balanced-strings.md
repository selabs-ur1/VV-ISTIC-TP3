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

 1: Il existe plusieurs cas de figure, 
 -équilibré : le cas où le le string est vide,  2 symbols corrects, une liste de symboles correctes
 -dééquilibré : le cas avec un seul sympbol,  2 symbols inversés, 2 symbols inversés,  symboles imbriqués incorectes.

 2: j'ai fait des tests qui parcourent chaque cas de figure de la question 1 avec chacun 2-3 versions différentes. Le code comporte 3 if donc les tests font en sortent de passer forcemment dans chacune des conditions.

 3: Les if servent à savoir si la pile est vide ou que la pile ne contient pas le bon symbole d'ouverture, les tests parcourent donc chacun des if où nous avons des symbols qui ne se suivent pas.

 4: J'ai en testant avec PIT, 90% de Line coverage pour la class StringUtils. Les 10% restant sont parce que je ne test pas la class StringUtils, elle n'est pas static donc je ne peux pas la tester ? 
