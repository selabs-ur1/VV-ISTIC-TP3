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

1. Le test ne passe pas car les flottants utilisés peuvent contenir des arrondis. Par défaut, 1,20001 n'est pas égal a
   1,2. Pour contrer ce problème d'arrondi on peut préciser une précision
   avec : `Assert.assertEquals(expected, actual, delta)`, ce qui donne dans notre
   exemple : `Assert.assertEquals(3 * 0.4, 1.2, 0.001)`.

2. D'après la documentation, assert same vérifie que les deux objets sont identiques et assert equals vérifie que les
   valeurs sont égales. Dans la pratique l'adresse mémoire est utilisée dans le premier et la méthode equals est appelée
   dans le second.<br>
    ```java
   Integer int1 = new Integer(5);
    Integer int2 = new Integer(2);
    Integer int3 = new Integer(3);
    Assert.assertSame(int1, int2 + int3);
    Assert.assertEqual(int1, int2 + int3);
   ```
   Dans cet exemple, le assert equals sera vrai car la valeur est la même mais ce ne sont pas les mêmes objets donc le
   assertSame sera faux.

3. Il est possible d'utiliser les fail() pour passer des messages customiés. 'exemple extrait de la doc Junit'

```java
   public void testBad(){
        try{
            doSomething();
            fail("should have thrown an exception");
        }catch(Exception e){
            throws(e);
        }
    }
```

5. Cette modification dans la syntaxe rend les tests plus permissifs, il est ainsi possible de tester les retours de
   plusieurs manière possible puis de tester que l'exception a bien été levée.