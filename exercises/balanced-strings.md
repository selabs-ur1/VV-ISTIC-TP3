# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}`
and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```bash
    public static boolean isBalanced(String str) {
        ...
    }
```

`isBalanced` returns `true` if  `str` is balanced according to the rules explained above.Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified. (Table 6)
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer

```bash
    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();
    
        for (char c : str.toCharArray()) {
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
            } else if (!stack.isEmpty()) {
                char top = stack.pop();
    
                if (c == '}' && top != '{') {
                    return false;
                } else if (c == ')' && top != '(') {
                    return false;
                } else if (c == ']' && top != '[') {
                    return false;
                }
            } else {
                return false;
            }
        }
    
        return stack.isEmpty();
    }
```

1.
| Characteristics                        | Block | Block |
|----------------------------------------|-------|-------|
| q1 : s == null                         | True  | False |
| q2 : s is empty                        | True  | False |
| q3 : s i pair                          | True  | False |
| q4 : s contains nested balanced string | True  | False |
| q5 : s is the union of balanced string | True  | False |

- Cas de base q1 = False, q2 = False, q3 = True, q4 = True, q5 = False


- Ensemble initial d’entrées

| q1    | q2    | q3    | q4    | q5    | s             | Explication                                   |
|-------|-------|-------|-------|-------|---------------|-----------------------------------------------|
| true  | -     | -     | -     | -     | null          | Quand q1 est true, on ne regarde pas le reste |
| false | true  | -     | -     | -     | ""            | Quand q2 est true, on ne regarde pas le reste |
| false | false | true  | true  | false | "([])"        |                                               |
| false | false | false | true  | false | "([)"         |                                               |
| false | false | true  | false | false | "()[]"        |                                               |
| false | false | true  | true  | true  | "()" + "[{}]" |                                               |

2.
- Nous avons écrit pour chaque entrée, définit dans le tableau ci-dessus, un cas de test. Puis, nous avons exécuté la classe de test en mode couverture. Taux de couverture : 82%.
- Nous avons rajouté des cas de test pour pousser la couverture à 100%.

3. 
- La couverture structurelle étant de 100% avec les tests écrits précédemment, la couverture des prédicats conjonctifs est complète.
- Dans le code, il reste un prédicat disjonctif à couvrir ``if (c == '{' || c == '(' || c == '[')``, donc au maximum 3 cas de test avec pour entrées : ``"{"``, ``"("`` (déjà testé avec les autres tests), ``"["``.

4.
- La couverture par mutation et l'efficacité des tests sont maximales. Uniquement une ligne n'est pas couverte qui est celle du constructeur privé vide.