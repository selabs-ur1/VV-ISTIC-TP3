# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer

1.    Partitionnement de l'Espace d'Entrée :

Caractéristiques et Blocs de Partition : Date(int jour, int mois, int année)

Date valide (commune avec la méthode isValidDate)
Date invalide (commune avec la méthode isValidDate)

Caractéristiques et Blocs de Partition : isValidDate(int jour, int mois, int année)

Date valide

Partition 1 : Date valide normale
Partition 2 : Date bissextile (commune avec la méthode isLeapYear)

Date invalide

Partition 3 : Jour invalide
Partition 4 : Mois invalide
Partition 5 : Année invalide

Caractéristiques et Blocs de Partition : isLeapYear(int année)

Année bissextile
Année non bissextile

Caractéristiques et Blocs de Partition : nextDate() et previousDate()

Date normale
Cas limites (début ou fin d'un mois, année)

Caractéristiques et Blocs de Partition : compareTo(Date autre)

La date est postérieure à une autre
La date est antérieure à une autre
La date est identique à une autre
L'autre est nulle

2.    Code dans la classe de tests.

3.    Évaluation de la Couverture Logique (Couverture des Choix de Base):

Le code des fonctions est relativement simple, avec des conditions simples et peu de prédicats complexes. Cependant, nous devons nous assurer que chaque branche du code est couverte. Étant donné qu'il n'y a pas de prédicats complexes, la couverture de base devrait déjà être atteinte avec les tests fournis dans la réponse précédente.

4.    Test de Mutation PIT :

Exécution de PIT (test de mutation) sur les suites de tests existantes.

