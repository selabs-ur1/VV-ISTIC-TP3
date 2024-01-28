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

### 1. Input Space : 

| Partition                   | Description                                                                                          |
|-----------------------------|------------------------------------------------------------------------------------------------------|
| Objet null                  | La chaine n'est pas initialisé donc retourne une erreur                                              |
| Chaîne vide                 | Aucun caractère dans la chaîne                                                                       |
| Caractères non groupants    | Chaîne ne contenant que des caractères autres que les symboles de groupe                             |
| Ouvertures uniquement       | Chaîne contenant uniquement des ouvertures de groupes sans fermeture                                 |
| Fermetures uniquement       | Chaîne contenant uniquement des fermetures de groupes sans ouverture                                 |
| Équilibre correct/incorrect | Chaîne contenant un mélange d'ouvertures et de fermetures avec des équilibres corrects et incorrects |

J'ai choisi de retourner une erreur si la chaine n'a pas été initialisé.

### 2. Test Coverage :

J'ai testé le coverage avec intellij et j'obtiens les résultats suivants :

| Class     | Method         | Line         |
|-----------|----------------|--------------|
| 100% (1/1)| 100% (1/1)     | 100% (15/15) |

### 3. Base Choice Coverage 

Lors de la prise en compte des cas test et leur écriture j'ai pris en compte le *Base Choice Coverage* pour les écrire.

### 4. PIT:

PIT me retourne le résultat suivant : 

| Name         | Number of Classes | Line Coverage | Mutation Coverage |
|--------------|-------------------|---------------|-------------------|
| fr.istic.vv  | 1                 | 94% (15/16)   | 100% (20/20)      |
