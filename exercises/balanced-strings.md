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

1. Input initial

| Input            | Output attendu  |
|------------------|-----------------|
| `""`             | `true`          |
| `"()"`           | `true`          |
| `"([])"`         | `true`          |
| `")("`           | `false`         |
| `"([)]"`         | `false`         |
| `"{{}}"`         | `true`          |
| `"{[()]}"`       | `true`          |
| `"((()"`         | `false`         |
| `"[{}]]"`        | `false`         |

Le premier est trivial, le reste permet de tester les cas où les symboles sont déséquilibrés de différentes façon.

2. Il manque certains cas de tests :

| Input            | Output attendu  |
|------------------|-----------------|
| `"a"`            | `true`          |
| `"("`            | `false`         |
| `"]"`            | `false`         |

Lorsqu'il n'y a que des caractères, le résultat est `true`. Les cas où il n'y a qu'un seul symbole ou un seul caractère sont manquants.

3. Base Choice Coverage
`if (round < 0 || square < 0 || curly < 0)`

La condition sont connectés par des OR, il suffit donc qu'un seul soit vrai pour satisfaire ce prédicat :
| Input            | Output attendu  |
|------------------|-----------------|
| `")"`            | `false`         |
| `"]"`            | `false`         |
| `"}"`            | `false`         |

Le code que j'avais était le suivant :

```java
    public static boolean isBalanced(String str) {
        int round = 0;
        int square = 0;
        int curly = 0;
        for (char c : str.toCharArray()) {
            switch (c) {
                case '(':
                    round++;
                    break;
                case ')':
                    round--;
                    break;
                case '[':
                    square++;
                    break;
                case ']':
                    square--;
                    break;
                case '{':
                    curly++;
                    break;
                case '}':
                    curly--;
                    break;
            }
            if (round < 0 || square < 0 || curly < 0) {
                return false;
            }
        }
        return round == 0 && square == 0 && curly == 0;
    }
```
Mais le test "([)]" ne passait pas, car en effet il n'y a pas l'idée de vérifier si les symboles sont bien fermés dans l'ordre. J'ai donc réécris le code avec une pile :

```java
    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }
```

Pour calculer le coverage, j'ai utilisé le plugin Jacoco. Voici ce que j'ai obtenu :

```
GROUP                       : tp3-balanced-strings
PACKAGE                     : fr.istic.vv
CLASS                       : StringUtils
INSTRUCTION_MISSED          : 0
INSTRUCTION_COVERED         : 65
BRANCH_MISSED               : 2
BRANCH_COVERED              : 17
LINE_MISSED                 : 0
LINE_COVERED                : 12
COMPLEXITY_MISSED           : 2
COMPLEXITY_COVERED          : 10
METHOD_MISSED               : 0
METHOD_COVERED              : 1
```

Dans l'interface, je peux voir que le code concerné par les branches manquantes est le suivant :

```java
24. if (stack.isEmpty() || stack.pop() != '[') {
29. if (stack.isEmpty() || stack.pop() != '{') {
```

J'ai donc ajouté les tests suivants :

```java
@Test
void testMixedTypesImproperlyNestedSquare() {
    assertFalse(StringUtils.isBalanced("[(])"));
}

@Test
void testMixedTypesImproperlyNestedCurly() {
    assertFalse(StringUtils.isBalanced("{(})"));
}
```

En effet, il y avait déjà le cas de test `"([)]"` qui vérifiait que pour les parenthèses, mais pas pour les crochets et les accolades.

4. Mutation testing

J'ai utilisé le plugin PIT pour évaluer le code. Voici les résultats :

Line coverage: 92%, Mutation score: 100%

PIT considère que la ligne du constructeur vide n'est pas couverte, mais le reste du code l'est.

D'après les différentes étapes, j'ai pu obtenir un code qui est bien testé.