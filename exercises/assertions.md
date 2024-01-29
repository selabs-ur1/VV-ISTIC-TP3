# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1)
Il s'agit d'un flottant donc afin de correctemment vérifier l'égalité, il faut tester une fourchette car 1.2 peut être égale à 1.2000000003.

2)
assertEquals permet de vérifier les données membres des objets
assertSame permet de vérifier l'égalité des références

3)
'fail' est utile pour récupérer une exception particuliere.

Il est possible d'utiliser 'fail' en conjonction avec un bloc try-catch pour indiquer explicitement qu'une excepetion attendue n'a pas été levée alors qu'elle aurait dû l'être.
```
@Test
public void testExceptionHandling() {
    try {
        // Code that should throw a specific exception
        throw new CustomException("Expected exception");
    } catch (CustomException expected) {
        // Exception caught, test passes
    } catch (Exception unexpected) {
        fail("Unexpected exception: " + unexpected.getMessage());
    }
}
```

4)
De notre point de vue, ce changement permet de la clarté ainsi que de la simplicité. De plus, cette nouvelle approche support les lambdas.


