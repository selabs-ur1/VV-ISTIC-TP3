# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. L'assertion échoue car 3 * .4 produit 1.20000000002 et que c'est donc pas equals à 1.2 malgrés l'aproxiamation.Pour que ça fonctionne :

```java
@Test
public void testValue(){
    float floatA = 1.20000000002;
    float floatB = 3*.4;
    double tolerance = 0.00001;

    assertEquals(floatA, floatB, tolerance);
}


```

2. AssertEquals va faire un .equals() ce qui va comparer les contenus des objets, tant dis que AssertSame va comparer les références. Example :

```java
@Test
public void testString(){
    String stringA = "truc";
    String stringB = "truc";

    assertEquals(stringA, stringB); // TRUE
    assertSame(stringA, stringB);// FALSE
}

@Test
public void testString2(){
    String stringA = "truc";
    String stringB = stringA;

    assertEquals(stringA, stringB); // TRUE
    assertSame(stringA, stringB);// TRUE
}

```

3. Fail sert pour signaler qu'une condition n'est pas rempli dans les tests unitaire. Ou pour signaler qu'une erreur n'a pas été provoqué mais attendu 

```java
        @Test
void testExpectedException() {
    try {
        int result = 1 / 0;
        fail("ArithmeticException était attendue, mais aucune exception n'a été levée.");
    } catch (ArithmeticException e) {
        // Exception attendue, test réussi
    }
}
    ```

4.  Cela permet de pouvoir tester si les exceptions sont bien déclenchées.  