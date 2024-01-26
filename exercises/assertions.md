# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

- assertTrue(3 * .4 == 1.2) echoue car multiplier un entier avec un float peut créer des erreurs d’arrondis.
- il faut utiliser assertEquals(expected, actual, delta), avec delta qui est la précision de l'égalité.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

- Assert Equals va être utilisé pour comparer des résultats tandis que assert Same va être utilisé pour comparer des références
On peut comparer 2 String identiques qu’on initialise dans 2 variables. Ils auront la même valeur donc AssertEquals va être       vrai mais 2 références différentes donc AssertSame sera faux.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

- C’est utilisé pour spécifier qu’une situation inattendue se produit. On peut par exemple tester une condition qu’on ne veut volontairement pas voir se vérifier et utiliser fail si tel est le cas.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

- assertThrows rend le code plus explicite. Il est aussi plus précis et complet, on peut voir le détail de l’exception.
