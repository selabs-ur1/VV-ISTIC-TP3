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

Authors: Dufeil Jaufret & Gentile Brian

1.
Method: Date(int day, int month, int year)
Characteristics:

Valid day: 1 to 31
Valid month: 1 to 12
Valid year: A valid range (e.g., from 0 to some future year)


 
Method: isValidDate(int day, int month, int year)
Characteristics:

Valid day: 1 to 31
Valid month: 1 to 12
Valid year: A valid range (e.g., from 0 to some future year)


 
Method: isLeapYear(int year)
Characteristics:

Leap year: A year divisible by 4 but not by 100 unless it's also divisible by 400


 
Method: nextDate()
This method generates the next date, so the characteristics for valid days, months, and years will be the same as identified in the Date constructor and isValidDate method.


 
Method: previousDate()
This method generates the previous date, so the characteristics for valid days, months, and years will be the same as identified in the Date constructor and isValidDate method.


 
Method: compareTo(Date other)
Characteristics:

Null comparison: Test for handling NullPointerException.
Comparison with another date: Test for greater than, less than, and equal scenarios.

2.
I got 75% coverage, so to improve this result, I made new tests to cover the code not tested.

3.
  I got this predicat so I tested all the possibilities.

if (month == 4  month == 6  month == 9  month == 11) {


4.
I tested the PIT and I got this report for the IsLeapYear function : 

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0)  (year % 400 == 0);
    }

replaced boolean return with true for fr/istic/vv/Date::isLeapYear → KILLED
Replaced integer modulus with multiplication → KILLED
Replaced integer modulus with multiplication → SURVIVED
Replaced integer modulus with multiplication → KILLED
negated conditional → KILLED
negated conditional → KILLED
negated conditional → KILLED

I tried to make the opposite of the function to be sure that the survived test isn't an error.

    public static boolean notIsLeapYear(int year) {
        return (year * 4 != 0 || year * 100 == 0) && (year * 400 != 0);
    }

I made the same tests than on the real one and all test passed so this is just an exception for the cases I used for my tests.