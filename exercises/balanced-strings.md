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

1. The method expects a string as a parameter. We identified those different characteristics and partitioning blocks:

| characteristics                                                    | blocks                                                 |                                                        |                                                                |                                                                |
|--------------------------------------------------------------------|--------------------------------------------------------|--------------------------------------------------------|----------------------------------------------------------------|----------------------------------------------------------------|
| length of the string                                               | 0                                                      | \>0                                                     | \>0                                                             | \>0                                                             |
| number of the same characters (opening or closing) in a same block | even                                                   | even                                                   | even                                                           | odd                                                            |
| number of the opening characters in a same block                   | same as the number of corresponding closing characters | same as the number of corresponding closing characters | not the same as the number of corresponding closing characters | not the same as the number of corresponding closing characters |
| number of closing characters in a same block                       | same as the number of corresponding opening characters | same as the number of corresponding opening characters | not the same as the number of corresponding opening characters | not the same as the number of corresponding opening characters |

2. We wrote tests to cover each of those blocks. We have four tests : one for each block.
3. None of our tests use double boolean assertions. However to ensure the line coverage of our tests, each test uses multiple assertions to test the same thing for the different characters.
4. We used PIT to evaluate our test suite. we have a score of 18/21 line coverage and 6/10 mutant killed. This comes from the fact that we use a switch statement to ensure that the characters are closed in the right order. However, we currently have no solution to kill those mutants.