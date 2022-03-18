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

1. Pour designer le set d'entrée de donnée, il faut faire attention aux bornes des dates.
L'année doit forcément être positive ou nulle.
Les mois doivent être compris entre 1 et 12.
Les jours sont entre 1 et 28,29,30 ou 31 suivant le mois.
Il faut donc faire attention au mois et a l'année lors de l'incrémentation des jours
Pour prendre un set d'entrée utile, il faut prendre en compte tous les cas possibles.

2. Le coverage est de 100%

3. La seule fonction qui utilise des comparaisons booléennes en masse est le ValidDate. Toutes possibilités sont testées.
4. D'après l'outil PITtest nous obtenons **>>> Generated 64 mutations Killed 56 (88%)** ce qui est un bon score de mutation.
Les mutants vivants sont situés sur 
- BooleanTrueReturnValsMutator (75%)
- ConditionalsBoundaryMutator (62%)
- MathMutator (78%)
