# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. Cette assertion échoue à cause de la façon dont les nombres à virgule flottante sont représentés en informatique. La multiplication 3 * .4 donne un résultat très proche de 1.2, mais pas exactement égal à cause des petites erreurs d'arrondi dues à la représentation binaire des nombres flottants. Certains nombres décimaux ne peuvent pas être représentés exactement en binaire.

2.  Différence entre assertEquals et AssertSame
- assertEquals vérifie si deux valeurs sont égales en se basant sur leur méthode equals().
 - assertSame vérifie si deux objets sont la même instance (c’est-à-dire qu’ils pointent vers le même emplacement mémoire).

 3. La méthode fail peut être utilisée comme :

    - Un indicateur pour des tests non encore terminés.
    - Une garantie qu'un chemin de code spécifique n'est pas exécuté, sauf si une condition attendue est respectée.

 Voici des exemples : 
  1. Indicateur pour un test incomplet :

```@Test
public void testFeatureX() {
    // Test non encore implémenté
    fail("Test non implémenté");
}
 ```

2. Vérification qu’un chemin de code ne doit pas être atteint :

```@Test
public void testDivisionParZero() {
    try {
        int resultat = 10 / 0;
        fail("ArithmeticException attendue mais non levée");
    } catch (ArithmeticException e) {
        // Exception capturée, le test passe
    }
}
```

4.  - JUnit 4 : Les exceptions étaient vérifiées avec l'annotation @Test(expected = Exception.class).
    - JUnit 5 : assertThrows offre une méthode explicite et plus lisible pour tester les exceptions.

Avantages de assertThrows :

- Contrôle précis : Vous pouvez écrire des assertions sur l'objet exception lui-même, par exemple vérifier le message ou des propriétés spécifiques.

- Meilleure lisibilité : Le test montre clairement où l'exception est attendue, ce qui le rend plus compréhensible par rapport à l'utilisation de @Test(expected).

- Tests localisés : Avec @Test(expected), toute exception levée dans la méthode pouvait satisfaire le test. Avec assertThrows, vous pouvez vous assurer que l'exception est levée à un endroit spécifique du code.

- Évite les faux positifs : Avec @Test(expected), si une exception était levée ailleurs dans la méthode, le test pouvait passer par erreur. assertThrows limite cette possibilité.