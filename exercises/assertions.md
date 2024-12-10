# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. L'assert `assertTrue(3 * .4 == 1.2)` fail car le résultat de `3 * .4` n'est pas exactement égal à `1.2` en raison de la représentation en virgule flottante des nombres. Pour vérifier si deux valeurs flottantes sont égales, il est recommandé d'utiliser une tolérance. Par exemple, `assertTrue(Math.abs(3 * .4 - 1.2) < 0.0001)`.

2. La différence entre `assertEquals` et `assertSame` est :
- `assertEquals` vérifie si les deux objets passés en paramètres sont égaux en utilisant la méthode `equals` de l'objet.
- `assertSame` vérifie si les deux objets passés en paramètres sont exactement les mêmes objets (c'est-à-dire s'ils ont la même référence).

3. `fail` peut être utilisé pour marquer du code qui ne devrait pas être exécuté pour une raison quelconque. Par exemple, si une méthode est appelée avec des paramètres incorrects, il est possible de vérifier ces paramètres avant d'exécuter le code principal. Si les paramètres sont incorrects, il est possible de lancer une exception ou d'appeler `fail` pour arrêter l'exécution du code.
Exemple :
```java
public void doSomething(int value) {
    if (value < 0) {
        fail("Value doit être positif");
    }
    // Reste de code
}
```

4. L'avantage de la nouvelle méthode `assertThrows` est qu'elle permet de vérifier si une exception est lancée par une méthode sans avoir à utiliser un bloc `try-catch`. Cela rend le code plus lisible et plus facile à comprendre. De plus, `assertThrows` permet de vérifier si une exception est lancée avec un message spécifique, ce qui n'était pas possible avec l'ancienne méthode.