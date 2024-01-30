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

Code de la méthode isBalanced(String str) :

    public static boolean isBalanced(String str) {
    
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (ch == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else if (ch == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else {
                return false; // Unmatched closing bracket or other characters
            }
        }
        return stack.isEmpty(); // If stack is empty, all brackets were balanced
    }

Partitionnement de l'Espace d'Entrée :

Présence de Symboles :
Partition 1 : Chaîne de caractères sans symboles (Chaîne vide).
Partition 2 : Chaîne de caractères avec des symboles équilibrés.
Partition 3 : Chaîne de caractères avec des symboles déséquilibrés.

Types de Symboles :
Partition 4 : Chaîne de caractères avec uniquement des accolades {}.
Partition 5 : Chaîne de caractères avec uniquement des crochets [].
Partition 6 : Chaîne de caractères avec uniquement des parenthèses ().

Combinaison de Symboles :
Partition 7 : Chaîne de caractères avec une combinaison de symboles équilibrés.
Partition 8 : Chaîne de caractères avec une combinaison de symboles déséquilibrés.

Évaluation de la Couverture des Instructions :

java

@Test
public void testIsBalanced() {

// Partition 1: Chaîne vide
assertTrue(isBalanced(""));

// Partition 2: Chaîne avec des symboles équilibrés
assertTrue(isBalanced("{[]()}"));

// Partition 3: Chaîne avec des symboles déséquilibrés
assertFalse(isBalanced("([)]"));

// Partition 4: Chaîne avec seulement des accolades
assertTrue(isBalanced("{}"));

// Partition 5: Chaîne avec seulement des crochets
assertTrue(isBalanced("[]"));

// Partition 6: Chaîne avec seulement des parenthèses
assertTrue(isBalanced("()"));

// Partition 7: Chaîne avec une combinaison de symboles équilibrés
assertTrue(isBalanced("{[()]}"));

// Partition 8: Chaîne avec une combinaison de symboles déséquilibrés
assertFalse(isBalanced("[{()]"));

}

Évaluation de la Couverture Logique (Couverture des Choix de Base) :

Le code de la fonction isBalanced est relativement simple, avec des conditions simples et peu de prédicats complexes. Cependant, nous devons nous assurer que chaque branche du code est couverte. Étant donné qu'il n'y a pas de prédicats complexes, la couverture de base devrait déjà être atteinte avec les tests fournis dans la réponse précédente.

Test de Mutation PIT :

Exécution de PIT (test de mutation) sur les suites de tests existantes.

