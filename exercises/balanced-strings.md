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

L'intégralité de mon code peut être trouver dans le fichier tp3-balanced-strings.

1. Pour l'input Space Partitioning nous devons diviser l'espace des entrées en blocs de partition pour couvrir les cas significatifs tel que
- Longueur de la chaîne (vide, avec un ou plusieurs caracteres)
- Composition des caracteres 
- L'équilibrage des symboles (les deux cas, équilibrés et non équilibrés )
- Le test de structure imbriqué

2. Pour avoir un maximum de coverage, j'ai ajouter tout les cas significatifs dont j'ai parler au dessus dans mon fichier de test à retrouver dans tp3-balanced-strings/test
Si il me manque des cas de tests dans mon fichier et que mon coverage est insuffisant, je pourrais utiliser JaCoCo pour trouver les cas non couvert par mes tests et ainsi les ajouter

3. Un exemple de prédicat que j'ai dans mon code est le suivant :
   if (stack.isEmpty() || !isMatchingPair(stack.pop(), c)) { ... }

je dois donc faire les tests suivant nécéssaire pour s'assurer de la satisfaction du BCC de ces predicats :
Tests nécessaires :

    stack.isEmpty() : true et false
    !isMatchingPair(...) : true et false

Cas supplémentaires pour BCC :

    stack.isEmpty() == true et isMatchingPair == false (exemple : ")")
    stack.isEmpty() == false et isMatchingPair == false (exemple : "{]")

4. PIT : malheuresement je n'arrive pas à faire fonctionner PIT due à des UNKNOW_ERROR





