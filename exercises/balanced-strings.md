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

1) Dans un premier temps, nous pouvons établir plusieurs caractéristiques :
- une chaine de caractères vide doit être acceptée,
- une chaine de caractères null n'est pas acceptée, 
- une chaine de caractères qui contient un caractère fermant pas déjà ouvert n'est pas acceptée,
- un caractère ouvrant doit forcément avoir son caractère fermant dans la chaîne,
- une chaine doit avoir un nombre pair de caractère,
- un bloc de caractères doit forcément être pair,
- une chaine qui contient d'autres caractères n'est pas acceptée.

2) **Tests chaine null** 
- **testIsBalancedNOKIllegalArgument()** :  lance une exception de type IllegalArgumentException 

**Tests chaine vide**
- **testIsBalancedOKEmptyString()** : teste la chaine vide --> true

**Tests chaine de longueur impair**
- **testIsBalancedNOKOddLengthString()** : teste les chaines impaires --> false

**Tests chaine avec un accolade non fermée**
- **testIsBalancedNOKBracketNotClosed()** : teste une accolade non fermée --> false

**Tests caractères non autorisés**
- **testIsBalancedNOKUnallowedCharacters()** : teste avec une chaine de caractères contenant des caractères non autorisés

**Tests chaines correctes**
- **testIsBalancedOKMultipleTests()** : teste plusieurs chaines correctes avec un block simple
- **testIsBalancedOKMultipleBlockAndTests()** : teste plusieurs chaines correctes avec plusieurs blocks

A cette étape, nous avons une couverture de code de 100%. 

3) Nous n'écrivons pas dans nos tests des assertions avec plusieurs opérateurs booléens. Selon nous,
chaque assertion doit être indépendante des autres pour éviter tout problème et faciliter la relecture 
par d'autres développeurs.


4) Nous avons exécuté la commande suivante afin de déterminer les mutants 



    mvn test-compile org.pitest:pitest-maven:mutationCoverage


Nous avons un pourcentage de 79 pour la couverture de mutation. Les if dans chaque use case crée 3 mutants, ce qui est beaucoup.
Nous avons essayé de modifier ces lignes de code mais chaque changement provoquait une diminution du pourcentage.
De plus, nous avons ajouté des tests mais aucun n'a pu amélioré le pourcentage.
