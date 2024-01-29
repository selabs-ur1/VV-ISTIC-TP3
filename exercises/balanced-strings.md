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
Here's are the test cases :
 - Empty string
 - string without bracket
 - single open bracket (for each type of brackets)
 - single close bracket (for each type of brackets)
 - balanced couple bracket (for each type of brackets)
 - unbalanced couple bracket (for each type of brackets)
 - missing closing bracket (for each type of brackets)
 - mssing open bracket (for each type of brackets)
 - balanced string with only one type of bracket (for each type of brackets)
 - wrong couple of bracket
 - complex unbalanced string
 - complex balanced string

2.
The statement coverage is 100%.
No new tests case have been added, coverage report can be generate with the command `mvn jacoco:prepare-agent test install jacoco:report` from the directory `/code/tp3-balanced-strings`.
The coverage report is available [here](http://127.0.0.1:3000/code/tp3-balanced-strings/target/site/jacoco/index.html).

3.
There isn't any predicate that uses more than two boolean operators in the code.

4.
PIT report can be generated with the command `mvn test-compile org.pitest:pitest-maven:mutationCoverage` from the directory `/code/tp3-balanced-strings`.
The is PIT report is available [here](http://127.0.0.1:3000/code/tp3-balanced-strings/target/pit-reports/202401141413/index.html).
The mutation score is 100%, 11 mutants were created, and the all have been killed.
A more detailed report is available [here](http://127.0.0.1:3000/code/tp3-balanced-strings/target/pit-reports/202401141413/fr.istic.vv/StringUtils.java.html), it show the life cycle of the mutants.
No new tests case have been added.
