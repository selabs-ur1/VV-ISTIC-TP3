# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. Le assert ne fonctionne pas car la multiplication ne va pas forcement donnée le résultat exact. Cela est du à la manière dont fonctionne les flottant qui fait aparaitre des erreurs de précisions. Même si les résulats sont théoriquement identiques.
Pour pouvoir tout de me réaliser un assert sur des flottants il faut ajouter une valeur "epsilon" qui sera la précision à partir de laquelle le résultat sera acceptable. La syntaxe est la suivante en java :

    ```java
    assertEquals(a, b, epsilon)
    ```

2. AssertSame compare les références de deux objects. Il renvoie vrai seulement si l'adresse mémoire est la même.
AssertEquals compare le contenu de deux objets en utilisant la méthode equals() définis.
Scenario produisant le même résultat :

    ```java
    String s1 = "Hello";
    String s2 = s1; 
    assertEquals(s1, s2);   //retourne vrai 
    assertSame(s1, s2);     //retourne vrai 
    ```

Scenario produisant un résultat différent :

    ```java
    String s1 = new String("Hello");
    String s2 = new String("Hello"); 
    assertEquals(s1, s2);   //retourne vrai 
    assertSame(s1, s2);     //retourne faux
    ```

3. Voici deux exemples d'utilisation du fail au sein d'un code java :
    - On peut l'utiliser pour prévoir des cas de tests qu ine sont pas encore implémenté. Ca permet au moment de la conception de prévoire une campagne de tests sans pour autant savoir comment les écrire. Ainsi au moment de rédiger les tests, l'instruction fail permettra de trouver facilement des tests non implémentés mais à réaliser.
    ```java
    @Test
    testMonFail(){
        fail();
    }
    ```
    - Cela permet de vérifier une logique qui ne devrait pas arriver comme une valeur retournée alors qu'ell ene correspnd pas aux conditions voulues.
    ```java
     @Test
    testMonFail2(){
        int value = 5;
        computeValue(value);
        if(value<0){
            fail("value should not be below zero");
        }
    }
    ```

4. Utiliser AssertThrows permet d'être plus précis sur l'erreur remontée en analisant le message ce qui n'etait pas faisable facilement avec le @Test (utiliser ExpectedException). On a accès a une analyse plus fine de l'erreur.
C'est plus facile de débugger un cas de test si nécessaire car l'expressions est dans le corps de la fonction de test et pas dans l'annotation.
