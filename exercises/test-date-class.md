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

### 1. Input Space Partitioning
- Characteristics and blocks identified for isValidDate() 

|Characteristics  |  Blocks  |   | | | | |  
|---|---|---|---|---|---|---|
| Value of year  | &lt; 0 |  0 | valid leap year | valid common year | | | 
| Value of month | &lt; 0 |  0 | {1, 3, 5, 7, 8, 10, 12} | {4, 6, 9, 11} | 2 | &gt; 12 | 
| Value of day   | &lt; 0 |  0 | &ge; 1 and &le; maxDay(month, year)| &gt; maxDay(month, year) | | | 

- Characteristics and blocks identified for isLeapYear(int year)

|Characteristics  |  Blocks  |   | 
|---|---|---|
| Value of year is multiple of 4    | True | False |  
| Value of year is multiple of 100  | True | False |  
| Value of year is multiple of 400  | True | False |  


- Characteristics and blocks identified for nextDate()

|Characteristics  |  Blocks  |   | | |
|---|---|---|---|---|
| Value of year  | valid leap year | valid common year | | |
| Value of month | {1, 3, 5, 7, 8, 10} | {4, 6, 9, 11}|2|12|
| Value of day | &lt; maxDay(month, year) |maxDay(month, year)| ||

- Characteristics and blocks identified for previousDate()

|Characteristics  |  Blocks  |   | | |
|---|---|---|---|---|
| Value of year  | valid leap year | valid common year | | |
| Value of month | {3, 5, 7, 8, 10, 12} | {4, 6, 9, 11}|2|1|
| Value of day | 1|&gt; 1 and &le; maxDay(month, year) || |

- Characteristics and blocks identified for compareTo(Date other)

|Characteristics  |  Blocks  |   | | ||
|---|---|---|---|---|---|
| Value of year of other  | valid leap year | valid common year | | |
| Value of month of other | {3, 5, 7, 8, 10} | {4, 6, 9, 11}|2|1|12|
| Value of day | 1|&gt; 1 and &lt; maxDay(month, year) |maxDay(month, year)| |

- Characteristics and blocks identified for maxDay(int month, int year)

|Characteristics  |  Blocks  |   | | 
|---|---|---|---|
| Value of year | valid leap year | valid common year | |
| Value of month | {1, 3, 5, 7, 8, 10, 12} | {4, 6, 9, 11}|2|

Les caractéristiques communes à plus d'une méthode :
La caractéristique "value of year" est commune à isValidDate(), isLeapYear(int year), nextDate(), previousDate(), compareTo(Date other), maxDay(int month, int year).
La caractéristique "value of month" est commune à isValidDate(), nextDate(), previousDate(), compareTo(Date other), maxDay(int month, int year).
La caractéristique "value of day" est commune à isValidDate(), nextDate(), previousDate(), compareTo(Date other).

### 2. Statement coverage
Dans un premier temps, j'ai créé 6 cas de test pour tester la méthode isValidDate() avec la coverage criteria "Each Choice Coverage (ECC)". 
Suite à l'exécution avec Coverage, j'ai ajouté le cas de test test29DaysFebruaryLeapYear() pour augmenter la coverage.
En plus, j'ai trouvé une erreur dans la méthode maxDay() via un cas de test testMar1LeapYear() qui a le but de tester la méthode previousDate(). 
L'erreur était qu'il manquait un else dans le deuxième if statement. 
A la fin, j'ai ajouté des cas de test pour tester le lancement des exceptions afin d'augmenter la coverage à 100%

### 3. Logic coverage
J'ai un prédicat dans la méthode isLeapYear() qui utilise plus de deux opérateurs booléens : 
```
return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
```
Les cas de tests pour tester la méthode ne satisfait pas *Base Choice Coverage*.
Le test avec la valeur de 2021 a pris le block year % 4 != 0, le block year % 100 != 0 et le block year % 400 != 0.
Le test avec la valeur de 2000 a pris le block year % 4 == 0, le block year % 100 == 0 et le block year % 400 == 0.
Mais les autres inputs étant formés par changer un seul block par partition et garder les autres ne sont pas testés, 
par exemple, le block year % 4 == 0, le block year % 100 != 0 et le block year % 400 != 0.

Le critère de coverage *Modified Condition/Decision Coverage (MC/DC)* est utilisé pour évaluer la logic coverage. 
J'ai ajouté 2 nouveaux cas de test augmenter la coverage.
```
    @Test
    @DisplayName("test isLeapYear 2")
    public void testIsLeapYear2() {
        assertFalse(Date.isLeapYear(1900), "Leap years should not be multiple of 4 and 100");
    }

    @Test
    @DisplayName("test isLeapYear 3")
    public void testIsLeapYear3() {
        assertTrue(Date.isLeapYear(2020), "Leap years could be multiple of 4, not 100 and 400");
    }

```


### 4. PIT
Mutation score :
>> Generated 78 mutations Killed 60 (77%)
>> Ran 312 tests (4 tests per mutation)

Une partie des Live mutants :
1. if (year > MAX_VALID_YR || : changed conditional boundary → SURVIVED
2. if (month < 1 || month > 12) { : changed conditional boundary → SURVIVED
3. if (day < 1 || day > 31) { : changed conditional boundary → SURVIVED
4. return (day <= 29); : replaced boolean return with true for fr/istic/vv/Date::isValidDate → SURVIVED
5. return (day <= 28); : changed conditional boundary → SURVIVED
6. if (month == 4 || month == 6 || month == 9 || month == 11) { : negated conditional → SURVIVED
7. return (day <= 30); : 
   1. replaced boolean return with true for fr/istic/vv/Date::isValidDate → SURVIVED 
   2. changed conditional boundary → SURVIVED 
   3. negated conditional → SURVIVED
8. nextDay = this.day + 1; :  Replaced integer addition with subtraction → SURVIVED

Suite à l'ajout des nouveaux cas test, le mutation score est augmenté à 90%.
>> Generated 78 mutations Killed 70 (90%)
>> Ran 318 tests (4.08 tests per mutation)
