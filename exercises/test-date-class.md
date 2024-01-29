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

`isValidDate()`
| Characteristics |       | Blocks |    |                             |                    |    |      |
| --------------- | ----- | ------ | -- | --------------------------- | ------------------ | -- | ---- |
|                 |       | b1     | b2 | b3                          | b4                 | b5 | b6   |
| q1              | day   | < 0    | 0  | >= 1 and < max(month, year) | > max(month, year) |    |      |
| q2              | month | < 0    | 0  | 1, 3, 5, 7, 8, 10, 12       | 4, 6, 9, 11        | 2  | > 12 |
| q3              | year  | < 0    | 0  | valid leap year             | valid common year  |    |      |

| Inputs                           | Blocks           |
| -------------------------------- | ---------------- |
| { day: 1, month: 1, year: -1}    | q1b1, q2b3, q3b3 |
| { day: -1, month: -1, year: 0}   | q1b2, q2b1, q3b1 |
| { day: 0, month: 4, year: 2020}  | q1b3, q2b4, q3b2 |
| { day: -2, month: 0, year: 2019} | q1b4, q2b2, q3b1 |
| { day: 29, month: 2, year: 2019} | q1b3, q2b5, q3b4 |
| { day: 0, month: 13, year: 2018} | q1b4, q2b6, q3b2 |

`isLeapYear()`

| Characteristics |       | Blocks       |                 |                  |                  |
| --------------- | ----- | ------------ | --------------- | ---------------- | ---------------- |
|                 |       | b1           | b2              | b3               | b4               |
| q1              | year  | prime number | divisible by 4  | divisible by 100 | divisible by 400 |

| Inputs | Blocks           |
| ------ | ---------------- |
| 13     | q1b1             |
| 0      | q1b2, q1b3, q1b4 |
| 1900   | q1b2, q1b3       |
| 4      | q1b2             |
| 2000   | q1b2, q1b3, q1b4 |

`nextDate() / previousDate()`

| Characteristics |       | Blocks          |                                 |                  |
| --------------- | ----- | --------------- | ------------------------------- | ---------------- |
|                 |       | b1              | b2                              | b3               |
| q1              | day   | 1               | >= 1 and <= max(month, year)    | max(month, year) |
| q2              | month | 1               | 2, 3, 4, 5, 6, 7, 8, 9, 10, 11  | 12               |
| q3              | year  | valid leap year | valid common year               |                  |

| Inputs                            | Blocks           |
| --------------------------------- | ---------------- |
| { day: 1, month: 6, year: 2000}   | q1b1, q2b2, q3b1 |
| { day: 1, month: 12, year: 2001}  | q1b1, q2b3, q3b2 |
| { day: 1, month: 1, year: 2000}   | q1b1, q2b1, q3b1 |
| { day: 31, month: 1, year: 2001}  | q1b3, q2b1, q3b2 |
| { day: 30, month: 4, year: 2000}  | q1b3, q2b2, q3b1 |
| { day: 31, month: 12, year: 2001} | q1b3, q2b3, q3b2 |
| { day: 28, month: 2, year: 2000}  | q1b3, q2b2, q3b1 |
| { day: 28, month: 2, year: 2001}  | q1b3, q2b2, q3b2 |
| { day: 1, month: 3, year: 2000}   | q1b1, q2b2, q3b1 |
| { day: 1, month: 3, year: 2001}   | q1b1, q2b2, q3b2 |
| { day: 20, month: 2, year: 2000}  | q1b2, q2b2, q3b1 |
| { day: 20, month: 12, year: 2001} | q1b2, q2b3, q3b2 |

PIT results:
98% Line Coverage
91% Mutation Coverage
