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

1.
Here's are the test cases :
 - toString
    - no specific date
 - equals
    - date and null
    - date and not date object
    - different dates
    - same date
 - Constructor
    - Invalid date creation
    - Valide date creation
 - isValidDate
    - day = 0
    - a negative month
    - month = 0
    - a month > 12
    - For each month : day too small / day too high / valid day
    - For month 2 : day 29 on a leap and on a non leap year
 - isLeapYear
    - year divisable by 4
    - year divisable by 100 and by 400
    - year divisable by 100 and not by 400
 - nextDate
    - staying in same month
    - new month
    - new year
 - previousDate
    - staying in same month
    - new month with 31 days
    - new month with 30 days
    - month 2 and leap year
    - month 2 and not leap year
    - new year
 - compareTo
    - date equals
    - same month & year but day before
    - same month & year but day after
    - same year but month before
    - same year but month after
    - year before
    - year after

2.
The statement coverage is 100%.
No new tests case have been added, coverage report can be generate with the command `mvn jacoco:prepare-agent test install jacoco:report` from the directory `/code/tp3-date`.
The coverage report is available [here](http://127.0.0.1:3000/code/tp3-date/target/site/jacoco/index.html).

3.
There isn't any predicate that uses more than two boolean operators in the code.

4.
PIT report can be generated with the command `mvn test-compile org.pitest:pitest-maven:mutationCoverage` from the directory `/code/tp3-date`.
The is PIT report is available [here](http://127.0.0.1:3000/code/tp3-date/target/pit-reports/202401141612/fr.istic.vv/index.html).
The mutation score is 100%, 75 mutants were created, and the all have been killed.
A more detailed report is available [here](http://127.0.0.1:3000/code/tp3-date/target/pit-reports/202401141612/fr.istic.vv/Date.java.html), it show the life cycle of the mutants.
No new tests case have been added.