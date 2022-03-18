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

#### 1
#####  ```isValidDate(...)``` common with ```nextDate(...)``` & ```previousDate(...)``` 
| Characteristics           | b1 | b2 | b3 | b4 | b5 |
| ------------------------- | ----- | ----- | ----- |----- | -
| Day    | <=0 | >= 1 & <= max month | > max month    |
| Month  | <=0 | 31 days month = {1,3,5,7,8,10,12}     | 30 days month =  {4,6,9,11}| >12 | 2
| Year   |  Leap year   |  Not leap year

#####  ```isLeapYear(...)```
| Characteristics           | b1 | b2 | b3 |
| ------------------------- | ----- | ----- | ----- | 
| Year   |  Leap year   |  Not leap year

#####  ```compareTo(...)```

| Characteristics           | b1 | b2 | b3 | b4 |
| ------------------------- | ----- | ----- | ----- |----- | 
| Day    | <=0 | >= 1 & <= max month | > max month    |
| Month  | <=0 | 31day month = {1,3,5,7,8,10,12}     | 30day month =  {4,6,9,11}| >12 |
| Year   |  Leap year   |  Not leap year
| Day of other    | <=0 | >= 1 & < max month | > max month    |
| Month of other  | <=0  | 31day month = {1,3,5,7,8,10,12}     | 30day month =  {4,6,9,11}| >12 |
| Year of other  |  Leap year   |  Not leap year


### 2

| Characteristics           | b1 | b2 | b3 | b4 | b5 |
| ------------------------- | ----- | ----- | ----- |----- | -
| q1    | <=0 | >= 1 & <= max month | > max month    |
| q2  | <=0 | 31day month = {1,3,5,7,8,10,12}     | 30day month =  {4,6,9,11}| >12 | 2
| q3   |  Leap year   |  Not leap year

| Input:  day month year        | Blocks | Description | Assert |
| ------------------------- | ----- | -| -|
| 0 1 0    | q1b1, q2b2, q3b1 | Day is zero | false
| 1 0 -1  | q1b2, q2b1, q3b2 | Month is zero | false
| 1 13 2022   | q1b2, q2b4, q3b2 | Month is > 12 | false
| 29 2 2024   |q1b2, q2b5, q3b1 | Valid leap year | true
| 29 2 2022   | q1b2, q2b5, q3b2| Not valid yeap lear | false
| 30 4 2022   |q1b2, q2b3, q3b2 | Valid 30 days month | true
| 31 12 2022   | q1b2, q2b2, q3b2 | Valid 31 days month | true
| 31 4 2022   | q1b3, q2b3,q3b2 | Not valid 30 days month | false
| 32 12 2022   | q1b3, q2b2,q3b2 | Not valid 31 days month | false



L'objectif de ces inputs est d'avoir une combinaison de blocks et characteristics qui vérifie chaques conditions de chaques méthodes. 

### 3 
Ici on a ajouté les tests correspondant aux assertions inverse de chaque condition utilisée dans le code par exemple pour une condition >= 0, on ajouté le test d'une valeur <0. 
Plusieurs des prédicats dans nos méthodes utilise plus de deux opérateurs booléens, on a donc appliqué le Base Choice Coverage pour imaginer différents cas de tests.

```java=
Prédicat : (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)
@Test
void testIsLeapYearNotValid(){
    assertFalse(isLeapYear(2022));
}
@Test
void testIsLeapYearValid(){
    assertTrue(isLeapYear(2024));
}
@Test
void testIsLeapYearValid2(){
    assertTrue(isLeapYear(400));
}
@Test
void testIsLeapYearNotValid2(){
    assertFalse(isLeapYear(100));
}
```
### 4 

Au premier lancement de PIT, nous avions 71% de mutations éliminées, la plupart étant reliée à la méthode compareTo que nous n'avions pas vérifié et quelques autres conditions dans des prédicats par exemple : pour le ```equals()```, nous ne vérifions pas si la fonction retournait bien ```false``` si l'object comparé n'était pas une classe ```Date```. Nous avons donc étudiés le rapport créé par PIT pour compléter nos tests et atteindre 91%. 

![](https://i.imgur.com/kMsPoPz.png)
