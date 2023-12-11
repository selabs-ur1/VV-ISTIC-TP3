# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1)
Flottant donc faut regarder une fouchette genre 1.2 cest 1.2000000003
? Sortir le calcul de l'oracle ?

2)
assertEquals = vérifie les datas des objets
assertSame = vérifie la référence

3)
'fail' est utile pour récupérer une exception particuliere.
?    Use fail in conjunction with a try-catch block to explicitly indicate that an expected exception was not thrown when it should have been. ?
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
- c'est plus clair, simple
- Support les lambdas


