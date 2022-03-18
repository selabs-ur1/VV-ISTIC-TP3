# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. L'assertion est fausse car on compare des flottants. Il y a une approximation dan sla representation des flottants et donc le résultat de 3 * .4 n'est pas exactement égal à 1.2
Pour éviter cela il faut ajouter un delta à l'assertion :
````java
assertEquals(3 * .4, 1.2, 0.0002); // true
````

2. AssertEquals compare la valeur de deux objets (.equals()). AssertSame compare la référence de deux objets ('==').
````java
String s1 = "test" ;
String s2 = "test" ;
assertSame(s1,s2) ; // true
assertEquals(s1,s2) ; // true

String s1 = new String("test") ;
String s2 = new String("test") ;
assertSame(s1,s2) ; // false car il s'agit de deux objet différents
assertEquals(s1,s2) ; //true car les deux objets ont la même valeur
````

3.On peut utiliser fail comme une condition d'arrêt d'un test.
````java
@Test
public void testingCondition() {
    int result = randomInteger();
    if(result > Integer.MAX_VALUE) {
        fail("Result cannot exceed integer max value");
    }
    // more testing code
}
````

4. Avec assertThrows, il est possible de continuer le test après l'exception pour ajouter d'autres assertions.