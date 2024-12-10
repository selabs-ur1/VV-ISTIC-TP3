# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed
symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is
considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str){
        ...
        }
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition
   blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to
   increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so
   far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic
   coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new
   test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer

1. Inputs de cas de test :
   - "" : Vide, cas particulier à tester => true
   - "()" : Cas simple => true
   - "()[]" : Cas simple avec deux types de symbole => true
   - "()[]{}" : Cas simple avec trois types de symbole => true
   - "([])" : Cas avec 1 imbrication => true
   - "([{}])" : Cas avec 2 imbrications => true
   - "y(ax[ {pm} uj h]g )hf" : Cas avec 2 imbrications et des caractère en plus => true
   - "yazehf" : Cas uniquement des caractères ni ouvrant ni fermant => true
   - "(" : Cas déséquilibré ouvrant simple => false
   - "}" : Cas déséquilibré fermant simple => false
   - "()[" : Cas déséquilibré ouvrant en fin de chaîne => false
   - "[()" : Cas déséquilibré ouvrant en début de chaîne => false
   - "}()" : Cas déséquilibré fermant en début de chaîne => false
   - "()}" : Cas déséquilibré fermant en fin de chaîne => false
   - "{({)}" : Cas déséquilibré ouvrant dans 2 imbrication => false
   - "{(})}" : Cas déséquilibré fermant dans 2 imbrication => false
   - "{(]}" : Cas déséquilibré ouvrant et fermant pas de la meme famille => false
   - "h{fsdf (fs}  d)s}" : Cas déséquilibré fermant dans 2 imbrication => false

2. En vérifiant avec des print, on a constaté que toute notre fonction était parcouru par les cas de test.
3. Les tests satisfont le Base Choice coverage.
4. Le résultat de PIT est le suivant :
``` 
Generated 31 mutations Killed 20 (65%)
>> Ran 140 tests (4.52 tests per mutation)
```

Parmi les 31 mutants générés, 10 n'ont pas été tué avec cette règles : 

```
> org.pitest.mutationtest.engine.gregor.mutators.VoidMethodCallMutator
>> Generated 10 Killed 0 (0%)
> KILLED 0 SURVIVED 10 TIMED_OUT 0 NON_VIABLE 0
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0
> NO_COVERAGE 0
```

Ces mutants ne sont pas pertinant car il n'y a aucune méthode void utilisés dans la classe StringUtils.

Un autre mutant n'a pas été tué avec cette règle : 

```
> org.pitest.mutationtest.engine.gregor.mutators.MathMutator
>> Generated 2 Killed 1 (50%)
> KILLED 1 SURVIVED 1 TIMED_OUT 0 NON_VIABLE 0
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0
> NO_COVERAGE 0
```

Etant donné qu'il n'y a pas d'opération arithmétique dans la fonction isBalanced, ce mutant n'est pas pertinant.


