# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

#### 1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

assertTrue vérifie si une condition est vraie. Dans ce cas, l'assertion échoue à cause du nombre de chiffres après la virgule de ``1.2``.
``3* .4`` retourne ``1.2000000000000002`` et non ``1.2``.
Les types double et float sont sensibles aux nombres de chiffres après la virgule. Leur résultat ne donne pas des réponses arrondies.

Cette égalité pourra être vraie si on utilise l'assertion:
```Java 
assertEquals(double expected, double actual, double delta)
````

#### 2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

`assertEquals` compare les *valeurs* de 2 objets ou de 2 variables de type primitif. Il renvoie _true_ si ces derniers ont la même valeurIl utilise la méthode _equals()_ pour vérifier si les 2 objets sont égaux.
`assertSame` compare les *références* de 2 objets.Il retourne _true_ si les objets font référence à la même adresses mémoire.
Il utilise quant à lui , l'opérateur _==_ pour vérifier si les 2 objets sont égaux. 

*Show scenarios where they produce the same result and scenarios where they do not produce the same result.*

``` Java
public class SameOrEqualsTest {

	BigDecimal b1,b2,b3;

	@BeforeEach
	void setUp() {

		 b1 = new BigDecimal("1.0");
		 b2 = b1;
		 b3 =  new BigDecimal("1.0");
	}

	@Test
	@DisplayName(" scenarios where assertEquals and assertSame produce the same result")
	void testEqualsSameProduceSameResult() {
		assertEquals(b1,b2);  //success
		assertSame(b1, b2); //success
	}

	@Test
	@DisplayName(" test assertEquals _ scenario where assertEquals and assertSame do not produce the same result")
	void testEqualsSameProduceDifferentResults1() {
		assertEquals(b1,b3); //success
	}

	@Test
	@DisplayName("test assertSame _ scenarios where assertEquals and assertSame do not produce the same result")
	void testEqualsSameProduceDifferentResults2() {
		assertSame(b1, b3); //fail
	}
}

````
#### 3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

. *Attraper une exception qui est attendue*.

Dans l'exemple ci-dessous, l’appel de la méthode `fail` dans le bloc ``try {...}`` permet de faire échouer le test si l’appel à _Integer.parseInt_ n’a pas produit d’exception.

```Java
@Test
public void parseIntThrowsExceptionWhenNotANumber() throws Exception {
    try {
        Integer.parseInt("not a number");
        fail("NumberFormatException expected");
        } catch (NumberFormatException e) {
        }
}
```

. *Pour indiquer des tests qui sont incomplets*

On peut faire échouer un test lorsqu'il est incomplet ou pas encore implémenté. Si on n'utilisait pas ``fail``, le test aurait réussi, ce qui est faux.

```Java
@Test
public void incompleteTest() {
    fail("Not yet implemented");
}
```
. *Exception inattendue*

Echouer un test lorsqu'une exception ne devrait pas être levée.

```Java
@Test
public void unexpectedException() {
    try {
        safeMethod();
        // more testing code
    } catch (Exception e) {
        fail("Unexpected exception was thrown");
    }
}
````
. *Condition de test*

``fail`` peut être appelé lorsqu'un résultat ne répond pas à une condition souhaitée.

```Java
@Test
public void testingCondition() {
    int result = randomInteger();
    if(result > Integer.MAX_VALUE) {
        fail("Result cannot exceed integer max value");
    }
// more testing code
}
```

#### 4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

La méthode ``assertThrows()`` de Junit5 permet de contrôler de façon précise la logique d'assertion d'exception car on peut l'utiliser 
dans des parties spécifiques du code, ce qui n'est pas le cas pour l'annotation ``@Test`` de JUnit4.
Aussi, il est possible de faire des vérifications sur l'exception obtenue avec ``assertThrows()``