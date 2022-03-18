# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. L'assertion échoue car les opérations avec des doubles rendent une valeur approché (ici environ 1.2000000002). Un moyen de contornement serait d'utiliser une variable ```final double THRESHOLD = .0001;``` et changer l'assert en : ```assertTrue(Math.abs(3*.4 - 1.2) < THRESHOLD);```

2. `assertEquals` utilise la méthode equals alors que `assertSame` utilise l'opérateur `==`. Il y aura donc une différence avec par exemple des objets String.
 
```
@Test
void test() {
  assertEquals("testEq","testEq");
  assertSame("testSame","testSame");
}
```
  
  Ici le résultat est le même. Pour avoir un résultat différents on utilise des objets : 
  
```
@Test
void test() {
  String s1 = new String("test"); 
  String s2 = new String("test"); 
  assertEquals(s1,s2);
  assertSame(s1,s2);
}
```
3. Une utilisation de fail() peut aussi être pour une méthode qui n'est pas encore implémenté.

4. Utiliser assertThrows les exceptions rend plus homogène cette fonctionnalité. Avec l'utilisation des lambda il est encore plus facile a écrire et comprendre le code. L'avantage principale est donc la lisibilité du code de test. 
