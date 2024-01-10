# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer
1 L'asertion échoue car la multiplication de 3 par 0.4 donne 1.2000000000000002 et non 1.2. Pour vérifier que deux nombres sont égaux, il faut utiliser la méthode `assertEquals` qui prend en paramètre deux nombres et une marge d'erreur.
Dans notre cas, il faut donc utiliser `assertEquals(3 * .4, 1.2, 0.0000000000000001)`.

2 `assertEquals` compare deux objets en vérifiant qu'ils sont égaux. `assertSame` compare deux objets en vérifiant qu'ils sont identiques. Dans le cas d'un objet, les deux méthodes produisent le même résultat. Dans le cas d'un primitif, `assertSame` ne fonctionne pas car il compare les références et non les valeurs.

3 `fail` peut être utilisé pour vérifier qu'une méthode n'est pas appelée. Par exemple, si on veut vérifier qu'une méthode n'est pas appelée, on peut utiliser `fail` dans le `catch` de la méthode. Si la méthode est appelée, le test échouera.

4 L'avantage de la nouvelle méthode est qu'elle permet de vérifier qu'une exception est bien levée dans une méthode. On peut donc vérifier qu'une méthode lève bien une exception sans avoir à utiliser un `try/catch` dans le test.