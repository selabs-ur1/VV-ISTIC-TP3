# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. Échec de l'Assertion :
   L'assertion assertTrue(3 * .4 == 1.2) échoue en raison des erreurs de précision des nombres à virgule flottante. Il faut utiliser une valeur epsilon pour les comparaisons de nombres à virgule flottante.

2. AssertEquals vérifie si deux objets donnés sont égaux. 
AssertSame vérifie si deux objets sont identiques et s'ils peuvent ne pas être égaux mais il s'agit du même objet.

3. Autres Utilisations de fail dans JUnit :
   Échecs Conditionnels : Il faut utiliser fail pour signaler des conditions inattendues, améliorant la lisibilité du code.
```
import java.lang.Math;

public class SquareRootCalculator {

    public static double calculateSquareRoot(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Input should be non-negative numer");
        } else {
            return Math.sqrt(x);
        }
    }

    public static void main(String[] args) {
        double result1 = calculateSquareRoot(25);
        System.out.println(result1);

        try {
            double result2 = calculateSquareRoot(-10);
            System.out.println(result2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

```

4. Il s’agit de tester les limites des programmes.
Il peut couvrir une mauvaise utilisation du programme et vérifier que la mauvaise utilisation du programme génère une exception. 
Il peut être utilisé pour NullPointerException par exemple.
