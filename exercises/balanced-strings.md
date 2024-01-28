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

1) The partitions identified are balanced string, unbalanced string and empty string leading to this set of input :

|                  | Balanced | Unbalanced |
|------------------|----------|------------|
| Raw Input        |          |      )     |
| With Depth       |   (())   |     ([)    |
| With other Chars |     a    |     a(     |
| With Parenthesis |    a()   |     a)     |
| With Brackets    |    a[]   |     a[     |
| With Braces      |    a{}   |     a{     |

We didn't make all combination of this partitioning in our tests due to his size. 

2) The path coverage is 100%, each line of code is executed at least one time by the test set. Nothing done in this step.

3) By using the active clause coverage on the following code :
```java
            if(c == '{') {
                groupingSymbolStack.push('}');
            } else if(c == '[') {
                groupingSymbolStack.push(']');      // A is "{([".contains(String.valueOf(c))
            } else if(c == '(') {
                groupingSymbolStack.push(')');
            } else if (")]}".contains(String.valueOf(c))) {  // B is ")]}".contains(String.valueOf(c))
                if(groupingSymbolStack.isEmpty()) {     p(x) with p() = isEmpty() and x = groupingSymbolStack 
                    return false;
                }
                else {
                    char last = groupingSymbolStack.pop();
                    if (c != last) {    // C is c != last
                        return false;
                    }
                }
            }
```

Here we only look one iteration of the for loop to take an example. 

We used Combinatorial Coverage to have this : 

|   A   |   B   |   C   |  p(x) | A v (B ^ !(p(x) v C)) |
|:-----:|:-----:|:-----:|:-----:|:---------------------:|
|  true |  true |  true |  true |          true         |
|  true |  true |  true | false |          true         |
|  true |  true | false |  true |          true         |
|  true |  true | false | false |          true         |
|  true | false |  true |       |          true         |
|  true | false | false |       |          true         |
| false |  true |  true |  true |         false         |
| false |  true |  true | false |         false         |
| false |  true | false |  true |         false         |
| false |  true | false | false |          true         |
| false | false |  true |       |         false         |
| false | false | false |       |         false         |

4)  	Line coverage      : 94% 17/18 

        Mutation Coverage  : 100% 

The score is high enough, no need to add tests (last line to cover is only l.5 'public class StringUtils')