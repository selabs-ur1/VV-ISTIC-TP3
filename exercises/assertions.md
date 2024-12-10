# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer
1. On multiplie ici un entier (3) avec un flottant (.4), le résultat n'est pas nécessairement un flottant.
Pour corriger ce souci :

```java
assert(3f * .4 == 1.2)
```

2. 
* `assertEquals` : appelle la méthode equals définie dans l'objet, le résultat dépendra de l'implémentation réalisée dans cette méthode.
* `assertSame` : vérifie que les références des objets sont identiques.

3. Les autres cas d'utilisation sont :
* Lorsque le code ne doit pas être exécuté car on est censé avoir retourné :
```java
@Test
public void returnBefore() {
    int value = randomInteger();
    if(value != 0){
        return;
    }
    fail("Should have returned before");
}
```

* Lorsque le résultat obtenu n'est pas celui attendu :
```java
@Test
public void testingCondition() {
    int result = randomInteger();
    if(result > Integer.MAX_VALUE) {
        fail("Result cannot exceed integer max value");
    }
    // more testing code
}
```

* Lorsque le test n'est pas encore implémenté :
```java
@Test
public void incompleteTest() {
    fail("Not yet implemented");
}
```

4. `assertThrows` permet d'avoir un contrôle plus fin sur le moment où l'exception est levée puisque que le assert est sur une méthode, là ou le bloc try/catch capte toutes les exceptions levées. Le second avantage est de bien spécifier le type d'exception attendu dans le `assertThrows`, ce qui ajoute encore plus de précision dans le résultat attendu.