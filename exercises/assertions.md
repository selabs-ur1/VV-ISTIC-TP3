# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. Cette assertion échoue à cause du problème de la virgule flottante, les flottants sont mal représentés en binaire, d'où l'imprécision. Pour palier à celà il faut instaurer une tolérance aux fautes pour accepter l'imprécision.

2. `asserEquals` vérifie une égalité entre 2 éléments, tandis que `assertSame` vérifie qu'il s'agit de la même instance.

```
String a = "test";
String b = a;

assertEquals(a, b);
assertSame(a, b);   
```
Ici les deux tests passent car a et b sont égaux et pointent la même instance.

```
String a = new String("test");
String b = new String("test");

assertEquals(a, b);
assertSame(a, b); 
```
Ici assertSame ne passe pas car a et b ne pointent pas vers la même instance même s'ils représentent la même chaîne de caractères.

3. On peut s'en servir pour afficher un message en cas d'erreur sur une conditionnelle par exemple :

```
@Test
void testCondition() {
    int result = someMethod();
    int expected = 15;
    if (result != expected) {
        fail("Le résultat attendu est " + expected + ", mais on obtient : " + result);
    }
}
```

4. Cela permet de concevoir facilement un test lorsqu'on veut tester qu'une exception bien précise soit levée, c'est plus simple à déclarer et facile à lire. Aussi on peut spécifier réellement quelle exception on attend.