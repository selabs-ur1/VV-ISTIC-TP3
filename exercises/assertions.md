# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. L'assertion assertTrue(3 * .4 == 1.2) échoue en raison de problèmes de précision arithmétique en virgule flottante. Les ordinateurs représentent les nombres à virgule flottante en binaire, ce qui peut parfois entraîner des erreurs de précision dans les calculs impliquant des nombres à virgule flottante.

Pour effectuer cette vérification avec des nombres à virgule flottante, on peut utiliser une approche qui tienne compte des erreurs de précision. On peux faire une comparaison de la différence absolue entre les deux nombres par rapport à une valeur seuil très faible au lieu de les comparer directement pour vérifier l'égalité.

```
double result = 3 * 0.4;
double expected = 1.2;
double threshold = 1e-10;

double difference = Math.abs(result - expected);
assertTrue("The difference exceeds the threshold", difference < threshold);
```

Autre possibilité est d'utiliser BigDecimale :

```
BigDecimal result = BigDecimal.valueOf(3).multiply(BigDecimal.valueOf(0.4));
BigDecimal expected = BigDecimal.valueOf(1.2);

assertTrue("Les nombres ne sont pas égaux", result.compareTo(expected) == 0);
```

2. assertEquals && assertSame

assertEquals :
  - Vérifie si deux objets sont égaux en termes de contenu ou de valeur.
  - Compare les valeurs elles-mêmes et ignore la référence mémoire.
  - Utilisé pour vérifier l'égalité de contenu, souvent pour les types primitifs (int, double, etc.) ou les objets. \

assertSame :
  - Vérifie si deux références pointent vers exactement le même objet en mémoire.
  - Compare les références mémoire elles-mêmes, exigeant que les objets soient situés au même emplacement mémoire.

Scénarios où ils produisent le même résultat :

- Pour les types primitifs comme les int, double, etc., lorsqu'ils contiennent la même valeur, assertEquals et assertSame renverront tous deux true.

Scénarios où ils produisent des résultats différents :
    
- Lorsque deux objets ont le même contenu ou la même valeur, mais sont situés à des emplacements mémoire différents, assertEquals renverra true tandis que assertSame renverra false.
- Si deux références pointent vers le même objet en mémoire, assertSame renverra true, mais assertEquals peut renvoyer false si les objets sont considérés comme différents en raison de la comparaison basée sur leur contenu.

3. 'fail' : 
- Implémentation de fonctionnalités inachevées : Lorsqu'une méthode ou une fonctionnalité est en cours de développement mais n'est pas encore entièrement mise en œuvre, vous pouvez utiliser fail pour indiquer que cette partie du code doit encore être complétée.
```
public int calculerQuelqueChose(int x, int y) {
    fail("Fonctionnalité non implémentée");
}
```

- Tests d'assertion dans des scénarios de développement piloté par les tests (TDD) : Lorsque vous écrivez des tests avant d'implémenter le code réel, vous pouvez utiliser fail pour marquer temporairement les tests non implémentés.
```
@Test
public void testMethodeNonImplementee() {
    fail("Cette méthode n'a pas encore été implémentée.");
}
```

4. JUnit : 
- En déplaçant la logique d'assertion spécifique à l'exception dans une méthode dédiée, assertThrows offre une approche plus explicite, rendant ainsi le code plus lisible et améliorant la clarté des tests.

- assertThrows offre des possibilités de validations plus précises et spécifiques des exceptions attendues.

- assertThrows assure une meilleure évolutivité des tests.

- Favorise la maintenabilité du code de test en évitant la duplication de code dans plusieurs tests.

- assertThrows est compatible avec l'utilisation de lambdas.
