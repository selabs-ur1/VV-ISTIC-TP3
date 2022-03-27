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


### 1. input space partitioning
- Characteristics and blocks identified using interface based modeling:
 
  | Characteristics  |  Blocks  |   |   
  |---|---|---|
  | str is empty  |True   |  False | 
 
- Characteristics and blocks identified using functionality based modeling:

  | Characteristics  |  Blocks  |   |    |   
  |---|---|---|---|
  |Length of str is pair   |True|False |   |   
  |Number of open symbols in str equals number of closed symbols |  True |  False |   |   |
  |The first character is open symbol | True|False |   |   
  |Number of types of symbols | 0 | 1 | &gt;1 | 
  |Each open symbol has a matching closed symbol | True|False |   |
  |Each closed symbol has a matching open symbol | True|False |   |
  |The substring before pairs of symbols is balanced | True|False |   |   
  |The substring after pairs of symbols is balanced | True|False |   |   
  |The substring between pairs of symbols is balanced | True|False |   |   
  
### 2. statement coverage
J'ai créé une suite de test dans la classe "StringUtilsTest" avec les inputs définis dans l'étape 1 input space partitioning.
Ensuite j'ai run la suite de test avec Coverage où le résultat était 88%. Donc, j'ai ajouté des nouveaux cas de test
pour augmenter la coverage à 100%.

### 3. logic coverage
J'ai un seul prédicat qui utilise plus de 2 opétateurs booléens : if (chari == ')' || chari == ']' || chari == '}').
Pour vérifier la "Base Choice Coverage", il faut choisir un block pour chaque partition, ici, par exemple, les blocks choisis
peuvent être chari == ')', chari == ']' et chari == '}'. Ensuite, il faut créer un test avec ces 3 blocks. 
En revenche, ces 3 blocks sont incompatibles, un test ne peut pas satisfaire ces 3 blocks en même temps. Dans le cas où il est possible,
il faut ensuite changer la valeur d'un seul block et garder les autres chaque fois pour tester chaque block.

### 4. PIT
J'ai utilisé la commande mvn clean test pour utiliser PIT afin de faire le mutation testing.
Le résultat : Le mutation score est 100%, et il n'y a pas de live mutants.
>> Generated 25 mutations Killed 25 (100%)
>> Ran 45 tests (1.8 tests per mutation)
