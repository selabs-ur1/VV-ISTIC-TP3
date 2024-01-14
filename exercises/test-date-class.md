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
1/-
Caractéristiques et blocs identifiés pour chaque méthode :

Date(int day, int month, int year) (Constructeur) :

Caractéristiques :
Validité de la date.
Année négative, mois hors de la plage, jour hors de la plage.
isValidDate(int day, int month, int year) :

Caractéristiques :
Année négative.
Mois hors de la plage.
Jour hors de la plage.
Validité normale (cas valide).
isLeapYear(int year) :

Caractéristiques :
Année bissextile.
Année non bissextile.
nextDate() :

Caractéristiques :
Jour < 31.
Mois < 12.
Jour = 31, Mois = 12.
previousDate() :

Caractéristiques :
Jour > 1.
Mois > 1.
Jour = 1, Mois = 1.
Jour = 1, Mois = 3, Année bissextile.
Jour = 1, Mois = 3, Année non bissextile.
compareTo(Date other) :

Caractéristiques :
Dates égales.
Date actuelle < Date autre.
Date actuelle > Date autre.

2/- Évaluation de la couverture de l'instruction :

Les tests actuels couvrent la plupart des instructions.

3/-
Évaluation de la couverture de la décision (Base Choice Coverage) :

Aucun prédicat avec plus de deux opérateurs booléens n'est présent dans le code.
la Base Choice Coverage est déjà satisfaite.