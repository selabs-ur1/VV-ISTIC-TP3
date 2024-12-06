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
  - 
## Answer
1. Cas à tester pour isValidDate
- Date(int day, int month, int year)
    - 0, 0, 0 (Invalide - date nulle)
    - 1, 1, 1 (Valide)
    - 21,12,2021 (Valide)
    - 29,2,2024 (Valide - année bissextile)
    - 29,2,2021 (Invalide - année non bissextile)
    - 31,4,2021 (Invalide - mois invalide)
    - 32,12,2021 (Invalide - jour invalide)
    - 31,13,2021 (Invalide - mois invalide)
    - -1,-1,-1 (Invalide - date négative)
  
- Cas à tester pour isLeapYear
    - 0 (valide - bissextile)
    - 1 (Non bissextile)
    - 4 (Bissextile)
    - 1700 (Non bissextile)
    - 2000 (Bissextile)
    - 2021 (Non bissextile)
    - 2024 (Bissextile)
  
- Cas à tester pour nextDate
    - 1,1,1 (2,1,1 - jour suivant)
    - 31,12,2021 (1,1,2022 - mois suivant)
    - 28,2,2021 (1,3,2021- année non bissextile, mois suivant)
    - 29,2,2024 (1,3,2024 - année bissextile, mois suivant)
    - 31,12,1999 (1,1,2000 - année suivante)
    - 32,13,2022 (invalid - jour et mois invalides)

- Cas à tester pour previousDate
    - 20,12,2024 (19,12,2024 - jour précédent)
    - 1,1,2022 (31,12,2021 - année précédente)
    - 1,3,2021 (28,2,2021 - année non bissextile, mois précédent)
    - 1,3,2024 (29,2,2024 - année bissextile, mois précédent)
    - 32,13,2022 (invalid - jour et mois invalides)

- Cas à tester pour compareTo
    - 1,1,1, 1,1,1 (0 - date égale)
    - 1,1,1, 1,1,2 (-1 - année inférieure)
    - 1,1,2, 1,1,1 (1 - année supérieure)
    - 1,1,1, 1,2,1 (-1 - mois inférieur)
    - 1,2,1, 1,1,1 (1 - mois supérieur)
    - 1,1,1, 2,1,1 (-1 - jour inférieur)
    - 2,1,1, 1,1,1 (1 - jour supérieur)
    - 1,1,1, null (NullPointerException)
    - 20,12,2024 31,4,2024( Exception - other date invalide)
2 + 3.
Nous avons essayé de donner à chaque branchement if/else une valeur de test qui le couvre, 
pour chaque méthode. Nous avons également ajouté des cas limites comme les dates nulles, 
les dates négatives, les dates invalides, etc.

4. mvn test-compile org.pitest:pitest-maven:mutationCoverage,
