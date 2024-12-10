# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. Cette assertion échoue à cause de la manière dont les nombres à virgule flottante (float ou double) sont représentés. Ces types de données sont stockés en interne sous forme binaire, ce qui peut entraîner des erreurs d'arrondi. 
Par exemple, le produit de 3 * 0.4 peut donner une valeur très proche de 1.2, mais pas exactement égale à 1.2.
Pour ce type de vérification, il est préférable de comparer les nombres à virgule flottante avec une tolérance

2.assertEquals: Vérifie si deux objets sont égaux en utilisant leur méthode equals().
  assertSame: Vérifie si deux références pointent vers le même objet en mémoire.

Scenario ou ils produisent le même résultat :
@Test
public void testEqualsAndSameIdenticalObjects() {
String a = "test";
String b = a; // Les deux pointent vers le même objet
assertEquals(a, b); // Réussit
assertSame(a, b);   // Réussit
}

Scenario ou ils produisent un résultat différent :
@Test
public void testEqualsAndSameDifferentObjects() {
String a = new String("test");
String b = new String("test"); // Deux objets différents avec la même valeur
assertEquals(a, b); // Réussit, car "test".equals("test")
assertSame(a, b);   // Échoue, car a et b sont des objets distincts
}


3. Fail peut être utiliser dans les deux cas suivants :
 - Détecter des conditions inattendues dans les tests, comme par exemple si un test dépend de conditions ou d'entrées spécifiques, on peut utiliser fail pour signaler une erreur si ces conditions ne sont pas remplies.
 - Indiquer un cas pas encore implémenté

Exemple du premier cas :
@Test
public void testWithUnexpectedCondition() {
int value = 5;
if (value < 0) {
fail("Le test ne supporte pas de valeur négative");
}
assertEquals(25, value * value);
}


Exemple du second cas :
@Test
public void testFeatureNotYetImplemented() {
fail("Ce cas de test n'est pas encore implémenté.");
}

4. AssertThrows présente les avantages suivants :
- La précision des cas de tests est plus poussé avec assertThrows, en effet on peut inspecter l’exception retournée pour vérifier des détails supplémentaires, comme un message ou des attributs.
- En utilisant assertThrows, il est possible de gérer plusieurs exceptions dans un même test ou de combiner cette assertion avec d'autres.
