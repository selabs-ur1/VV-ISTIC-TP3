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

1. Le plan de test pour la classe Date :

### Le constructeur Date(int day, int month, int year)

Date valide
Blocs: Jour, mois et année valides

Date invalide
Blocs: Jour, mois et année invalides

### isValidDate(int day, int month, int year)

Date valide
Blocs: Combinaisons valides de jour, mois et année

Date invalide
Blocs: Combinaisons invalides de jour, mois et année

### isLeapYear(int year)

Année bissextile
Blocs: Année divisible par 4 mais pas par 100, ou divisible par 400

Année non bissextile
Blocs: Année non divisible par 4, ou divisible par 100 mais pas par 400

### nextDate()

Date régulière
Blocs: Date n'est pas à la fin du mois/année

Fin de mois
Blocs: Date à la fin du mois

Fin d'année
Blocs: Date à la fin de l'année

### previousDate()

Date régulière
Blocs: Date n'est pas au début du mois/année

Début de mois
Blocs: Date au début du mois

Début d'année
Blocs: Date au début de l'année

### compareTo(Date other)

Même date
Blocs: Dates sont les mêmes

Dates différentes
Blocs: Une date est avant l'autre, une date est après l'autre

2. Le statement coverage des tests est de 100%. Les tests couvrent tous les blocs de code.

3. La méthode `isLeapYear` utilise deux opérateurs booléens. On peut décomposer le prédiacte en trois sous-predicats: 
- année divisible par 4
- année non divisible par 100
- année divisible par 400.

Pour satisfaire le Base Choice Coverage, on doit tester les combinaisons de valeurs qui satisfont et ne satisfont pas chaque sous-predicat. 

1. Année divisible par 4 mais pas par 100
2. Année divisible par 4 et par 100 mais pas par 400
3. Année divisible par 4, par 100 et par 400
4. Année non divisible par 4

```java
@Test
    void testIsLeapYear() {
        // Cas 1: Année divisible par 4 mais pas par 100
        assertTrue(Date.isLeapYear(2020)); // 2020 est une année bissextile

        // Cas 2: Année divisible par 4 et par 100 mais pas par 400
        assertTrue(Date.isLeapYear(2000)); // 2000 est une année bissextile

        // Cas 3: Année divisible par 4, par 100 et par 400
        assertFalse(Date.isLeapYear(1900)); // 1900 n'est pas une année bissextile

        // Cas 4: Année non divisible par 4
        assertFalse(Date.isLeapYear(2019)); // 2019 n'est pas une année bissextile
    }
```

4. Résultat de PIT :
```
================================================================================
- Statistics
================================================================================
>> Line Coverage: 50/68 (74%)
>> Generated 86 mutations Killed 63 (73%)
>> Mutations with no coverage 16. Test strength 90%
>> Ran 88 tests (1.02 tests per mutation)
```

Après l'amélioration de la méthode nextDate() et previousDate() : 

```
================================================================================
- Statistics
================================================================================
>> Line Coverage: 56/69 (81%)
>> Generated 89 mutations Killed 72 (81%)
>> Mutations with no coverage 12. Test strength 94%
>> Ran 96 tests (1.08 tests per mutation)
```