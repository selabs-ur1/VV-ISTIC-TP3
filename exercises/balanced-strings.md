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

1. Input Space Partitioning

    Input domain: String of `{}[]()` symbols
    Partitioning characteristic: Presence of Balanced Symbols
    - Block 1: Strings with balanced symbols 
    - Block 2: Strings without balanced symbols
   
    Test cases:
    - `{[][]}({})` : classic false
    - `{(}{}` : classic false
    - ` ` : empty string

2. Evaluate the statement coverage
   
   - I add 3 junit tests for initials tests cases.
   - I add 2 junit tests for all switch cases.

3. Predicate that uses more than two boolean operators.
   
   My code isn't in that case.

4. Use PIT to evaluate the test suite

   Mutation result after 1st PIT execution:
   - Line Coverage : 85%	
   - Mutation Coverage : 82%
   ```
   22: negated conditional → KILLED
       negated conditional → KILLED
   23: replaced boolean return with true for fr/istic/vv/StringUtils::isBalanced → KILLED
   26: negated conditional → KILLED
       negated conditional → KILLED
   27: replaced boolean return with true for fr/istic/vv/StringUtils::isBalanced → NO_COVERAGE
   30: negated conditional → KILLED
       negated conditional → KILLED
   31: replaced boolean return with true for fr/istic/vv/StringUtils::isBalanced → KILLED
   37: replaced boolean return with false for fr/istic/vv/StringUtils::isBalanced → KILLED
       replaced boolean return with true for fr/istic/vv/StringUtils::isBalanced → SURVIVED
   ```
   
   I have 1 line non covered and 1 mutant that survive. I add this following test:
   - non covered for true : `assertFalse(StringUtils.isBalanced("[)"));`
   - mutation for false: `assertFalse(StringUtils.isBalanced("()["));`

   Mutation result after 2nd PIT execution:
   - Line Coverage : 92% (Constructor isn't coverage)
   - Mutation Coverage : 100%
