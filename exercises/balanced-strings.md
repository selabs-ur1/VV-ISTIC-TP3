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

1 -

|Charactersitics|Blocks|
|---------------|------|
|Value of string| null; Empty; no '(' '['...;length=1; starts with ),]or...;  		      starts with ( or [ or { and length>=2

We used our method signature to define the characteristics
We identified different cases of string values could be interresting => null value, Empty value has the border effects, case of a string with none of the wanted characters, case of a string length equal to 1 has it can't be balanced, string composed only of our characters and starting with a closing one, case of a string of a length > 2 and starting with an opening bracket

We wrote test cases for each Block in a test class which are include in our test suite.

2 - With IntelliJ Idea the coverage of statements was 91% and we then added cases for strings which contains characters. And it increased statement coverage to 100%


4 -
When we first runed the PIT analysis we got :

```
- Statistics
================================================================================
>> Generated 22 mutations Killed 22 (100%)
>> Ran 42 tests (1.91 tests per mutation)

```

All the mutations were killed, so all the tests passed with every mutation.

As all the mutation are killed the coverage is good.




