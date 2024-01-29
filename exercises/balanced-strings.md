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

#### 1) Input Space Partitioning


| Characteristics                     | Result | Blocks                           |
|-------------------------------------|--------------------------|-----------------------------------------------------------------------------|
| Empty                               | True                     | Empty input string                                                          |
| Null                                | False                    | Null input string                                                           |
| {                                   | False                    | Unmatched opening symbols                                                   |
| }                                   | False                    | Unmatched closing symbols                                                   |
| {}                                  | True                     | Single pairs of symbols                                                     |
| }{                                 | False                    | Mixed symbols without respecting the order                                   |
| {}()                               | True                     | Mixed symbols with the correct order                                         |
| {}][                               | False                    | Mixed symbols with an incorrect order                                        |
| {()}                               | True                     | Nested pairs of symbols                                                     |
| {)(                                | False                    | Mixed symbols with an incorrect order                                        |
| }()                                | False                    | Unmatched closing symbols                                                   |
| {][                                | False                    | Mixed symbols without respecting the order                                   |

2)

After conducting our unit tests, we have a coverage of 100% for the cases described in 1).

3)

Let's evaluate the Base Choice Coverage for each predicate:

1. `str == null`: Already covered by the test case `assertThrows(IllegalArgumentException.class, () -> StringUtils.isBalanced(null));`
    
2. `OPENING_SYMBOL.contains(c)`: Covered by test cases such as `assertTrue(StringUtils.isBalanced("{[("));` and `assertFalse(StringUtils.isBalanced("abc"));`
    
3. `CLOSING_SYMBOL.contains(c)`: Covered by test cases like `assertTrue(StringUtils.isBalanced(")}]"));` and `assertFalse(StringUtils.isBalanced("abc"));`
    
4. `!stack.isEmpty()`: Covered by cases where closing symbols are encountered after opening symbols, such as `assertTrue(StringUtils.isBalanced("{[()]}"));` and `assertFalse(StringUtils.isBalanced(")}]"));`
    
5. `isMatching(stack.peek(), c)`: This is covered by test cases like `assertTrue(StringUtils.isBalanced("{[()]}"));` and `assertFalse(StringUtils.isBalanced("{)}"));`
    

Based on this analysis, it appears that the existing test cases already provide coverage for all possible combinations of truth values for the identified predicates.

4)

```
/================================================================================
- Mutators
================================================================================
> org.pitest.mutationtest.engine.gregor.mutators.BooleanTrueReturnValsMutator
>> Generated 3 Killed 2 (67%)
> KILLED 2 SURVIVED 1 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.VoidMethodCallMutator
>> Generated 1 Killed 1 (100%)
> KILLED 1 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.BooleanFalseReturnValsMutator
>> Generated 1 Killed 1 (100%)
> KILLED 1 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.NegateConditionalsMutator
>> Generated 11 Killed 11 (100%)
> KILLED 11 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
================================================================================
- Timings
================================================================================
> scan classpath : < 1 second
> coverage and dependency analysis : 1 seconds
> build mutation tests : < 1 second
> run mutation analysis : 1 seconds
--------------------------------------------------------------------------------
> Total  : 3 seconds
--------------------------------------------------------------------------------
================================================================================
- Statistics
================================================================================
>> Generated 16 mutations Killed 15 (94%)
>> Ran 33 tests (2.06 tests per mutation)
```

The mutation score is 94%, which is quite good.
