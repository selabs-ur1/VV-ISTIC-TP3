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

```java
public static boolean isBalanced(String str) {
    while (str.contains("()") || str.contains("[]") || str.contains("{}") || str.matches("(.*)\\b(.*)")){
            str = str.replaceAll("\\(\\)", "")
                    .replaceAll("\\[\\]", "")
                    .replaceAll("\\{\\}", "")
                    .replaceAll("[^[\\(\\)][\\[\\]][\\{\\}]]", "");
        }
        return (str.length() == 0);
}
```
##### 1. Utilisation de l'input space partitioning
Dans le cas de l'utilisation de l'`input space partitioning`, la granularité choisie porte sur la méthode. La méthode lit des chaînes de caractères pouvant contenir différentes parenthèses. Le but est de s'assurer que celles-ci, une fois ouvertes, sont correctement fermées. Ainsi deux cas de figures se posent : soit la chaîne est dite équilibrée si chaque symbole ouvert {\[\( a un symbole fermé correspondant )]}, soit elle ne l'est pas. A noter qu'une chaîne vide est considérée comme équilibrée. Ainsi nous avons repéré comme blocs, les différentes compositions possibles des strings, à savoir vide, avec seulement des parenthèses, et contenant des parenthèses.

**Exemple :**

| String                 | Correcte | Fausse |
|------------------------| -------- | -------- |
| vide                   | ""     | impossible     |
| alphabet               | "test"     | impossible     |
| parenthèses            | "{[][]}({})"     | "{(}){}"     |
| parenthèses + alphabet | "(t[]e{s}t)"     | "{(}ar)tr{}"     |

A la lecture de ces exemples possibles, deux tests ont été réalisés. Le premier test correspond aux différentes configurations où la chaîne de caractères est correcte. Le deuxième concerne des valeurs fausses. 

##### 2. Evaluation de la couverture des instructions des cas de test
Lorsque nous portons notre attention au taux de couvrance, celui-ci atteints les 100% de ligne de code. 
Ainsi les tests, tels qu'ils ont été pensés avant, tiennent aussi compte de la nécessité de passer par les différents chemins possibles du while. Ainsi, plusieurs cas de figure ont été posés tel que la possibilité d'avoir trois types de parenthèses possibles : `(), {}, []`. Chacun de ces couples de parenthèses supposent un traitement spécifique avec l'utilisation du replaceAll. Enfin, un dernier cas de figure se pose dans le cas où la chaîne de caractères contient d'autres éléments que des parenthèses. Ce dernier point est traité par le dernier prédicat qui est une regex.
Ainsi, les tests réalisés plus haut tiennent compte de ces différentes possibilités afin de couvrir le plus de cas possible en un minimum de tests. 

Cependant, il peut être intéressant de rajouter un test pour vérifier la solidité du code en venant tester le cas où la suite de parenthèses est erronée, mais où chaque parenthèse ouverte à tout de même sa parenthèse fermée : `"{(}){}"`

##### 3. Prédicat utilisant plus de deux opérateurs booléens
Comme expliqué précédemment la fonction `ìsBalanced` débute sur une boucle while venant vérifier quatre prédicats possibles. Ainsi, les tests tels que réfléchit précédemment ont été prévu afin qu'ils passent au moins par tous les chemins en un test : `“(t[]e{s}t)”`, idem concernant les tests voués à échouer.

##### 4. Utilisation de PIT pour évaluer la suite de test
Après utilisation de la commande `pitest:mutationCoverage`, nous obtenons les informations suivantes : 
1 mutant a été créé portant sur la modification du retour de valeur du booléen. 
5 mutants ont été généré et portaient sur l'inversion des conditions (ex : `==` devient `!=`), 1 est tué, les 4 autres sont "tués" car ils ont atteint un time_out, c'est-à-dire que la génération de ce mutant a créé une boucle infinie dans le code qui a été tué par arrêté par un "time_out". 

