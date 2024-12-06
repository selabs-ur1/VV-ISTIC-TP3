# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be
   done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and
   scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected
   before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion
   method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. Cette assertion peut être fausse en raison de potentiel faute d'arrondi. Les fautes d'arrondi sont dûe à la façon
   dont les nombres à virgule sont représenté dans un ordinateur. En effet, les nombres étant représenté en binaire, ils
   peuvent avoir une valeur très proche d'une décimale sans en être exactement égal. Pour contourner ce problème, il est
   possible d'accepter une marge d'erreur dans la comparaison.

2. `assertEquals` vérifie que 2 objets possède la même valeur. Pour des types primitifs, cela revient à effectuer la
   comparaison `==` et pour des objets plus complexes, cela revient à utiliser la méthode `.equals()`.
   `assertSame` vérifie que 2 références pointes vers la même adresse mémoire. Cela revient à utiliser la
   comparaison `==` qui compare les références d'objet.

3. On peut aussi utiliser fail pour :

- Faire échouer un test lorsqu'une exception n'est pas attendu

```java
class Test {
    @Test
    public void testUnexpectedException() {
        try {
            doSomething();
        } catch (error e) {
            fail();
        }
    }
}
```

- Faire échouer un test lorsqu'une condition n'est pas attendu :

```java
class Test {
    @Test
    public void testUnexpectedCondition() {
        int random = randomInteger();
        if (random > Integer.MAX_VALUE) {
            fail();
        }
    }
}
```

- Faire échouer un test lorsque la fonction ne retourne pas de valeur au bon moment :

```java
class Test {
    @Test
    public void testUnexpectedReturn() {
        int i = randomInteger(2);
        switch (i) {
            case 1, 2:
                return;
        }
        fail();
    }
}
```

4. Avec la version @Test(expected) de JUnit 4, la précision de l'endroit ou est levée l'exception est moins précise. En
   effet, l'exception peut être levée n'importe où dans le cas de test comme dans l'exemple suivant ou l'exception est
   levée avant que la fonction qu'on veut tester soit exécutée.

```java
class Test {
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionJUnit4() {
        validateInput(null);  // Exception levée ici (attendue)
        performAction();      // Jamais exécuté, mais erreur masquée
    }
}
```

Un autre avantage de la version de JUnit 5 est qu'il est possible d'inspecter l'exception qui est levée. On peut par
exemple vérifier si le message d'erreur est correcte avec `exception.getMessage()`.

```java
class Test {
    @Test()
    public void testExceptionJUnit5() {
       IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validateInput(null));
       assertEquals("Input must not be null", exception.getMessage());  
    }
}
```
