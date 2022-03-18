# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer
1.  L'assertion est toujours fausse car elle compare des nombres flotants a virgule, le 1.2 est arondis, alors que le `3*.4` donne un résultat de `1.2000000000000002`
    Pour avoir un test passable il faut utiliser un assert equals en donnant un delta autour de la valeur attendue:
    ```java
    assertEquals(3 * .4, 1.2, 0.000000000000001);
    ```

2.  assertEqualts utilise la méthode `equals()` pour comparer des objets, il compare leur identitée, alors que assertSame utilise l'opérateur `==` pour les comparer, il fait une comparaison de valeurs. 
    Exemple de fonctionnement identique, dans cet exemple le equal et le same produissent tout les deux une assertion fausse:
    ```java
    Object obj1 = new Object();
    assertEquals(obj1, obj1);
    assertSame(obj1, obj1);
    ```
    Exemple de fonctionnement différent:
    ```java
    Integer obj1 = new Integer(1);
    Integer obj2 = new Integer(1);
    assertEquals(obj1, obj2);
    assertSame(obj1, obj2);
    ```

3.  Un autre usage de fail peut être dans des tests non écrits mais ou les méthodes existent, pour ne pas oublier de les écrire plus tard.
    On peut aussi s'en servir dans un test pour inspecter la stacktrace a la recherche d'un code d'erreur si une exception n'est pas levée toute seule
    ```java
    try{
        // do stuff...
        fail("Exception not thrown");
    }catch(Exception e){
        assertTrue(e.hasSomeFlag());
    }
    ```

4.  L'intérêt de l'assertthrow est qu'il permet de continuer a exécuter du code a la suite de la levée de l'exception, par exemple pour mettre d'autre asserts vérifiant qu'un code dans l'exception est le bon ou de vérifier que l'exception a bien l'origine a laquelle on s'attend et n'est pas un effet de bord d'un autre bug qui produirait la même exception.