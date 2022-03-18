# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. Le calcul avec un float ou un double ne peut pas être enregistré de manière précise en mémoire ainsi le resultat d'un calcul impliquant un float ou un double ne sera jamais aussi précis qu'une valeur telle que 1.2. Pour réaliser ce genre de vérification il conviendrait d'utiliser le type BigDecimal qui permet de maintenir un bon degré de précision dans les calculs.
2.  assertEquals compare les deux objets et vérifie que ces deux objets sont identiques alors que assertSame vérifie que les deux objets pointe vers le même objet en mémoire. 

```java=
@Test
    void testEquals() throws InterruptedException {

        List<String> la = new ArrayList<>();
        List<String> lb = new ArrayList<>();
        assertEquals(la,lb);
    }
    @Test
    void testSame() throws InterruptedException {

        List<String> la = new ArrayList<>();
        List<String> lb = new ArrayList<>();
        assertSame(la,lb);
    }
```
Ici testSame renvoie faux et testEquals renvoie vrai, en effet les deux objet sont identiques mais ne pointe pas vers la même adresse.

```java=
@Test
    void testEquals() throws InterruptedException {

        String a = "a";
        String b = "a";
        assertEquals(a,b);
    }
    @Test
    void testSame() throws InterruptedException {

        String a = "a";
        String b = "a";
        assertSame(a,b);
    }
```
Ici le comportement des deux test est identique ils passent tous les deux.

3. l'annotation fail permet de marquer du code que l'on souhaite voir échouer sous certaines conditions pour vérifier notre programme. On peut aussi utiliser cette annotation dans les cas suivants : 
* Pour marquer un test incomplet : 
```java=
@Test
public void nonFinishedTest() {
    fail("Not implemented for now");
}
```
On pourra ainsi identifier les test non implémentés rapidement
* Pour marquer une exception attendue : 
```java=
public void shouldBeException() {
    try {
        methodThrowsException();
        fail("Expected exception not thrown");
    } catch (Exception e) {
        assertNotNull(e);
    }
}
```
On aura un fail dans le cas d'une exception différente de celle attendue
* Pour marquer une exception non attendue : 
```java=
public void unexpectedException() {
    try {
        safeMethod();
        // more testing code
    } catch (Exception e) {
        fail("Unexpected exception was thrown");
    }
}
```
On ne veut pas d'exception dans ce test il va donc fail dans le cas d'une exception
* Pour marquer un condition de test dépassant la valeur attendue : 
```java=
public void testingCondition() {
    int result = randomInteger();
    if(result > Integer.MAX_VALUE) {
        fail("Result cannot exceed integer max value");
    }
    // rest of testing code
}
```
On veut vérifier que la valeur reste dans un champ défini on envoie donc un fail dans le cas ou elle dépasse la borne supérieure
* Pour marquer une boucle qui ne retournerais pas au moment désiré : 
```java=
public void returnedBefore() {
    int value = randomInteger();
    for (int i = 0; i < 5; i++) {
        // returns when (value + i) is an even number
        if ((i + value) % 2 == 0) {
            return;
        }
    }
    fail("Should have returned before");
}
```
Si la boucle ne retourne pas avant la valeur désirée on envoie un fail

4. Les avantages de la méthode ```assertThrows``` 
assertThrows permet de spécifier une exception reliée à une classe d'exception et  non pas n'importe quelle exception, on peut donc ainsi vérifier que l'exception renvoyée par le test est bien l'exception que l'on voulait obtenir.
Elle peut être utiliser sur un bloc de code ou une lambda expression.
