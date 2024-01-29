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

1. caractéristiques des tests effectués
- Méthode `isValidDate()` :
    * Test de le méthode avec des dates valides avec le premier jour de l'année, le dernier jour de l'année, des années positives et négatives et un 29 février d'une année bissextile.
    * Test de la méthode avec des dates non valides telles qu'un 32 janvier, un 29 février d'une année non bissextile ainsi qu'une date avec pour jour et mois un 0.

- Méthode `isLeapYear()` :
    * Test avec une année bissextile et une année non-bissextile.

- Méthode `nextYear()` :
    * Test avec une date à la fin de l'année.
    * Test avec un 28 février d'une année non-bissextile.

- Méthode `previousYear()` :
    * Test avec une date au début de l'année.
    * Test avec un 1 mars d'une année bissextile.

- Méthode `compareTo()` :
    * Test dates supérieures, égales et inférieures avec des différences pendant sur l'année, le mois ou le jour. 

2. Résultat des test de couverture avec les tests initiaux :

![résultat test coverage](<images/coverage date.png>)

Avec une couverture à 100% avec les tests initiaux, il n'y a pas besoin d'ajouter de nouveaux tests.

3. Ajouts de tests pour satisfaire le Base Choice Coverage pour les méthodes `isValidDate()` et `isLeapYear()` :

```java
@Test
void testBCCIsValidDate() {
    assertFalse(Date.isValidDate(-1, 1, 1));
    assertFalse(Date.isValidDate(1, -1, 1));
    assertFalse(Date.isValidDate(1, 13, 1));
}

@Test
void testBCCLeapYear() {
    assertTrue(Date.isLeapYear(2020));
    assertFalse(Date.isLeapYear(2100));
    assertTrue(Date.isLeapYear(2000));
    assertTrue(Date.isLeapYear(2024));
    assertFalse(Date.isLeapYear(2021));
}
```

4. Résultats des tests de mutation Pitest avec les tests initiaux :

![](<images/pitest date.png>)

Les mutants survivants : 

![](<images/pitest date survivants.png>)

Expliquation des mutants :
- Changed conditional boundary : Pitest remplace les `>` par des `>=` et les `<` par des `<=`. Pour qu'un test puisse tuer un mutant dans ce cas, il faut donc un test qui qui tombe sur la valeur qui correspond au `=0`.
- Replaced integer substraction with addition : Pitest change le `-` en `+` dans la méthode `compareTo()`. Pour tuer ce mutant, il faut faire un test dans lequel le mois de la date que l'on compare soit supérieur au mois de la date initiale.

Pour obtenir un meilleur résultat aux tests de mutation, les tests suivants ont été ajoutés : 
```java 
@Test
void testPitNextDate() {
    Date date = new Date(29, 11, 2021);
    Date nextDate = date.nextDate();
    assertEquals(30, nextDate.getDay());
    assertEquals(11, nextDate.getMonth());
    assertEquals(2021, nextDate.getYear());

    nextDate = nextDate.nextDate();
    assertEquals(1, nextDate.getDay());
    assertEquals(12, nextDate.getMonth());
    assertEquals(2021, nextDate.getYear());
}

@Test
void testPitPreviousDate() {
    Date date = new Date(2, 2, 2021);
    Date previousDate = date.previousDate();
    assertEquals(1, previousDate.getDay());
    assertEquals(2, previousDate.getMonth());
    assertEquals(2021, previousDate.getYear());

    previousDate = previousDate.previousDate();
    assertEquals(31, previousDate.getDay());
    assertEquals(1, previousDate.getMonth());
    assertEquals(2021, previousDate.getYear());
}

@Test
void testPitCompareTo() {
    Date date = new Date(1, 6, 2021);
    assertTrue(date.compareTo(new Date(1, 7, 2021)) < 0);
}
```


Résultats des tests de mutation Pitest après améliorations : 

![](<images/pitest date amélioration.png>)