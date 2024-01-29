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

Authors: Dufeil Jaufret & Gentile Brian

1. We can split the test cases in multiple partitions:

    - balanced strings: strings with balanced symbols

    - unbalanced strings: strings with unbalanced symbols 

    - mix of both: strings with balanced and unbalanced symbols

    - strings with only one type of symbols: simplify tests and test the most basic test

2. Specific cases not tested, coverage = 92%. Needed to add 2 more tests. We looked at the coverage to see where the program didn't go and figured it was because of some conditions so we added tests which go through those conditions.

3. 6 tests added. Since it was || conditions, we did a test for each condition inside the if.

4. 93% mutation score with 1 live mutants. We had to add another test to check the last condition of the function. We re-ran PIT and we had 100% mutation score and all mutants were killed.