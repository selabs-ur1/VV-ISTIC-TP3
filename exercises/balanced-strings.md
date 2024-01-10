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

1. I partinioned the possible inputs in the following blocks, assuming that the string only contains grouping symbols:

| Charactiristics                                                      | Blocks            |                  |
|----------------------------------------------------------------------|-------------------|------------------|
| null                                                                 | exception                            |
| empty                                                                | balanced                             |
| equal number of opening and closing grouping symbols of each kind    | yes -> undecided  | no -> unbalanced |
| string starts with closing grouping symbol                           | yes -> unbalanced | no -> undecided  |
| string ends with opening grouping symbol                             | yes -> unbalanced | no -> undecided  |
| string contains a balanced string inside each pair of grouping symbol| yes -> balanced   | no -> unbalanced |
| string is a sequence of balanced strings (0 or more)                 | yes -> balanced   | no -> unbalanced |
| string is "()" or "[]" or "{}"                                       | yes -> balanced   | no -> unbalanced |

I combined the characteristics when they are undecided to obtain inputs covering all cases. Here are the inputs:
```java
static String NULL = null;
static String EMPTY = "";
static String UNEQUAL = "(([{}])";
static String EQUAL_BALANCED = "([{}])"; // covers EQUAL_CONTAINS_BALANCED, EQUAL_STARTS_WITH_OPENING, EQUAL_ENDS_WITH_CLOSING
static String EQUAL_UNBALANCED = "([{}(]))"; // covers EQUAL_CONTAINS_UNBALANCED
static String EQUAL_STARTS_WITH_CLOSING = ")({[]}"; // covers SEQUENCE_STARTS_WITH_CLOSING
static String EQUAL_ENDS_WITH_OPENING = "[]{})("; // covers SEQUENCE_ENDS_WITH_OPENING
static String SEQUENCE_BALANCED = "([{}])({[]})"; // covers SEQUENCE_CONTAINS_BALANCED, SEQUENCE_STARTS_WITH_OPENING, SEQUENCE_ENDS_WITH_CLOSING
static String SEQUENCE_UNBALANCED = "()(][)"; // covers SEQUENCE_CONTAINS_UNBALANCED
```

2. The coverage was 100% after the tests with the inputs above.

3. This does not apply to our code as the only predicate with more than two booleans only has exclusive cases. If the first boolean is true, it means the other two are false and respectively.

4. Here is the result of the first run of PIT on our project:

```
================================================================================
- Mutators
================================================================================
> org.pitest.mutationtest.engine.gregor.mutators.returns.BooleanTrueReturnValsMutator
>> Generated 2 Killed 2 (100%)
> KILLED 2 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.returns.BooleanFalseReturnValsMutator
>> Generated 4 Killed 4 (100%)
> KILLED 4 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.NegateConditionalsMutator
>> Generated 12 Killed 12 (100%)
> KILLED 12 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
================================================================================
- Timings
================================================================================
> pre-scan for mutations : < 1 second
> scan classpath : < 1 second
> coverage and dependency analysis : < 1 second
> build mutation tests : < 1 second
> run mutation analysis : 1 seconds
--------------------------------------------------------------------------------
> Total  : 2 seconds
--------------------------------------------------------------------------------
================================================================================
- Statistics
================================================================================
>> Line Coverage (for mutated classes only): 16/17 (94%)
>> Generated 18 mutations Killed 18 (100%)
>> Mutations with no coverage 0. Test strength 100%
>> Ran 52 tests (2.89 tests per mutation)
```

The only line not covered is the StringUtils constructor. It cannot be covered by PIT because there is no corresponding mutation operator.


