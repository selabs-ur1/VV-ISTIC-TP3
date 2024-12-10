# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. L'assert fail dû à un problème de floating point. En effet 3 * .4 n'est pas égal à 1.2 en floating point. Pour vérifier l'égalité de deux nombres flottants, il faut utiliser la méthode `assertEquals` avec un troisième paramètre qui est la précision de la comparaison.
Par exemple : `assertEquals(1.2, 3 * .4, 0.0001);`

2. `assertEquals` compare les valeurs des objets alors que `assertSame` compare les références des objets. Par exemple, si on a deux objets `a` et `b` qui ont la même valeur, `assertEquals(a, b)` retournera `true` alors que `assertSame(a, b)` retournera `false` car `a` et `b` ne sont pas la même instance.

3. `fail` peut être utilisé pour marquer des tests qui ne sont pas encore implémentés. Par exemple, si on a un test qui n'est pas encore implémenté, on peut le marquer avec `fail` pour ne pas oublier de l'implémenter plus tard. Par exemple : 
```java

@Test
public void testSomething() {
    fail("Not yet implemented");
}
```

1. L'avantage de `assertThrows` est que l'exception est attendue dans le bloc de test. Cela permet de rendre le code plus lisible et de séparer les tests qui vérifient les exceptions attendues des tests qui vérifient les résultats attendus. Cela permet aussi de vérifier que l'exception est bien lancée et de vérifier le message de l'exception.