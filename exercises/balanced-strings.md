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
Les impairs par définition ne peuvent pas être équilibrés. Les pairs par contre peuvent l'être ou ne pas l'être, il faut tester plus finement. Par exemple un pair du style "{}(}" ne retourne pas vrai. Il faut obligatoirement le même nombre de symboles ouvrants et fermants, et de manière plus précise, le même nombre pour chaque type.

2. Après évaluation, nous avons atteint un pourcentage de couverture de 78.26%, que nous avions précédemment amélioré en modifiant nos cas de test. Ensuite nous avons relancé le coverage, pour obtenir le pourcentage mentionné précédemment. Afin d'améliorer le pourcentage à 81.16%, nous avons ajouté un test qui vérifie que la fonction renvoie "true" quand un String vide lui ait donné en paramètre.
Nous avons considéré que ce pourcentage était satisfaisant. Pour établir la couverture des tests, nous avons utilisé un plugin de test Java inclus dans le pack de développement Java sur VSCode.

3. En considérant que nous n'utilisons que trois booléens dans prédicats, et en comparant avec les actions effectuées par nos tests, nous pouvons affirmer que nos cas de tests satisfont le "Base Choice Coverage". Nos cas de test testent toutes les combinaisons possibles de nos booléens. 

4. Notre score de mutation évalué par PIT est de 77%. Il reste un mutant vivant, qui change un incrément de 1 en -1. Les autres sont des cas non traités de retours de booléens mis à vrai au lieu de faux. Nous avons estimé que cela était un score très acceptable.
