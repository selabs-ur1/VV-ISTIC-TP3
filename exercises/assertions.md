# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

## EX 1

### 1.1

L'assertion `assertTrue(3 * .4 == 1.2)` fails car Les nombres à virgule flottante sont stockés en format binaire, ce qui peut entraîner des erreurs d'arrondi lors de la représentation de certaines valeurs décimales. pour resoudre cette assertion il faut utiliser une méthode qui permet une tolérance en termes de comparaison (delta). Comme :

```java
double result = 3 * .4;
double expected = 1.2;
double delta = 0.000001;
```

`assertTrue(Math.abs(result - expected) < delta);`

Donc pas possible de vérifier l'égalité entre deux floats, faut fixer un interval de différence maximale autorisée

`Assert.assertEquals(0.0012f, 0.0014f, 0.0002);`

### 1.2

La méthode assertEquals compare si deux objets ont la même valeur, c'est-à-dire s'ils sont égaux en termes de contenu ou de données. Elle vérifie si les valeurs des objets sont les mêmes, même si ces objets sont différents dans la mémoire.

la méthode assertSame vérifie si deux références pointent vers exactement le même objet en mémoire, c'est-à-dire s'ils partagent la même adresse mémoire.

```java
    String s1 = "Hello";
    String s2 = s1;
    assertEquals(s1, s2);// true car ils ont la meme valeur
    assertSame(s1, s2) // true aussi car ces les meme objets.

	String s1 = new String("Hello");
	String s2 = new String("Hello");
	assertEquals(s1, s2); // Égale car les valeurs des chaînes de caractères sont les mêmes
	assertSame(s1, s2); // false ce n'ai pas les même objets
```

Donc :

assertSame : vérifie que les deux valeurs passées en paramètre réfèrent au même objet

assertEquals : vérifie que les deux valeurs passées en param sont égales

```java
    String tmp = new String("detox");
    
    String tmpRef = tmp;
    
    assertSame(tmp, tmpRef); 	 	         // True
    
    assertSame(tmp, new String("detox"));  	 // False
    
    assertEquals(tmp, tmpRef);		         // True
    
    assertEquals(tmp, new String("detox"));  // True
```

### 1.3 FAIL

- Implémentations Incomplètes :

```java
    public int effectuerCalcul(int a, int b) {
        // À compléter ultérieurement
        fail("Méthode non implémentée pour le moment");
        // La logique réelle sera ajoutée ici
    }
```

- Tester des marqueurs ou du code enateignable : 

```java
    public void traiterDonnees(Donnees donnees) {
            if (donnees == null) {
                fail("Les données sont nulles, ne devrait pas arriver ici");
            } else {
                // Traiter les données
            }
        }
    }
```

Donc le .fail() implique

-> tests incomplets (à ne pas executer), exception à lancer, exception non attendue, tester condition, returning before

### 1.4
`assertThrows` permet une utilisation plus flexible, sur des blocs de code mieux délimités. L'objet qui renvoie l'exception est également renvoyé avec
cette méthode
