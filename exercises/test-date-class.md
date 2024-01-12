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
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer

1. 
- `isValidDate` (du cours) :

| Characteristics |                | Blocks |    |                              |                    |    |      |
|-----------------|----------------|--------|----|------------------------------|--------------------|----|------|
|                 |                | b1     | b2 | b3                           | b4                 | b5 | b6   |
| q1              | Value of year  | < 0    | 0  | valid leap year              | valid common year  |    |      |
| q2              | Value of month | < 0    | 0  | { 1, 3, 5, 7, 8, 10, 12}     | { 4, 6, 9, 11 }    | 2  | > 12 |
| q3              | Value of day   | < 0    | 0  | >= 1 and <= max(month, year) | > max(month, year) |    |      |

Cas de base q1b1, q2b3, q3b3

Ensemble initial d’entrées

| q1 | q2 | q3 | date     |
|----|----|----|----------|
| b2 | b3 | b3 | 0/1/1    |
| b1 | b1 | b3 | 2000/1/1 |
| b1 | b3 | b1 | 2000/1/1 |
| b1 | b3 | b3 | 2000/1/1 |

- `isLeapYear` :

| Characteristics                   | Block | Block |
|-----------------------------------|-------|-------|
| q1 : year%400 == 0                | True  | False |
| q2 : year%4 == 0 && year%100 != 0 | True  | False |

Cas de base q1 = False, q2 = False

Ensemble initial d’entrées

| q1    | q2    | year |
|-------|-------|------|
| true  | -     | 400  |
| false | true  | 4    |
| false | false | 2    |

- `nextDate` :

| Characteristics                                     | Block | Block |
|-----------------------------------------------------|-------|-------|
| q1 : 0 < month < 13 && 0 < day < monthLength(month) | True  | False |
| q2 : 0 < month < 12 && day == monthLength(month)    | True  | False |
| q3 : month == 12 && day == monthLength(month)       | True  | False |

Cas de base q1 = False, q2 = False, q3 = False

Ensemble initial d’entrées

| q1    | q2    | q3    | date       | COMMENTAIRE                           |
|-------|-------|-------|------------|---------------------------------------|
| true  | false | false | 2023/11/21 |                                       |
| false | true  | false | 2023/11/30 |                                       |
| false | false | true  | 2023/12/31 |                                       |
| false | false | false | 2023/0/0   | Impossible de créer une date invalide |

- `previousDate` :

| Characteristics                                      | Block | Block |
|------------------------------------------------------|-------|-------|
| q1 : 0 < month < 13 && 1 < day =< monthLength(month) | True  | False |
| q2 : 1 < month < 13 && 1 < day == 1                  | True  | False |
| q3 : month == 1 && day == 1                          | True  | False |

Cas de base q1 = False, q2 = False, q3 = False

Ensemble initial d’entrées

| q1    | q2    | q3    | date       | COMMENTAIRE                           |
|-------|-------|-------|------------|---------------------------------------|
| true  | false | false | 2023/11/21 |                                       |
| false | true  | false | 2023/2/1   |                                       |
| false | false | true  | 2023/1/1   |                                       |
| false | false | false | 2023/0/0   | Impossible de créer une date invalide |

- `compareTo` :
- 
| Characteristics |                   | Blocks          |                 |                  |
|-----------------|-------------------|-----------------|-----------------|------------------|
|                 |                   | b1              | b2              | b3               |
| q1              | Compare of years  | year1 < year2   | year1 > year2   | year1 == year2   |
| q2              | Compare of months | month1 < month2 | month1 > month2 | month1 == month2 |
| q3              | Compare of days   | day1 < day2     | day1 > day2     | day1 == day2     |

Cas de base q1b3, q2b1, q2b3

Ensemble initial d’entrées

| q1 | q2 | q3 | dates                              |
|----|----|----|------------------------------------|
| b2 | b1 | b3 | date1 = 2001/1/1, date2 = 2000/2/1 |
| b3 | b3 | b3 | date1 = 2000/1/1, date2 = 2000/1/1 |
| b3 | b1 | b1 | date1 = 2000/1/1, date2 = 2000/2/2 |
| b3 | b1 | b3 | date1 = 2000/1/1, date2 = 2000/2/1 |

- Il n'y a visiblement pas de caractéristiques communes

2.
- Code pas totalement couverte (hormis equals et toString) :
  - Constructeur
  - isValidDate
  - monthLength
  - compareTo  
==> Ajout de cas de test pour tout couvrir

3.
- Uniquement le prédicat de isLeapYear est a vérifier et l'est déjà avec les tests actuels, les autres prédicats sont couverts (soit un opérateur, soit conjonctifs).

4.
- Score de mutation : 80%
- Mutants en vie :
  - isValidDate :
    - `if (0 < month && month < 13)`
      - changed conditional boundary → SURVIVED
      - changed conditional boundary → SURVIVED\
      ==> Ajouté des tests sur tous les mois valides + 0 et 13 impossibles ne suffit pas
      ==> Ajouté des tests sur tous les mois valides et invalides en aléatoire ne suffit pas
    - `if (0 < day && day < monthLength + 1)`
      - changed conditional boundary → SURVIVED
      - changed conditional boundary → SURVIVED
  - monthLength (méthodes privées notamment utilisée dans isValidDate):
    - `if (isLeapYear(year))`
      - negated conditional → SURVIVED\
      ==> ajouté un tests `nextDate` sur `28/02/0000` suffit
  - isLeapYear
    - `return year % 400 == 0 || ( year % 4 == 0 && year % 100 != 0 );`
      - Replaced integer modulus with multiplication → SURVIVED\
      ==> Seul cas où `year % 400 == 0` == `year * 400 == 0` et `year % 4 == 0` == `year * 4 == 0` est quand year = 0\
      ==> Cas où `year % 100 != 0` == `year * 100 != 0` est quand year n'est pas un multiple de 100