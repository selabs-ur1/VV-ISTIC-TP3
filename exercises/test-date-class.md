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

Constructor :  		
DAY   :       <= 0 && 31 <     0 > && <= 31  	>= 1 and <= max(month, year)		> max(month, year)	
MONTH :	      <= 0 && 12 <     0 > && <= 12	{1, 3, 5, 7, 8, 10, 12}			{ 4, 6, 9, 11 }		2
YEAR  :       <= 0		isLeapYear	 !isLeapYear && 0 <	

isValidDate :
same as constructor

isLeapYear :
YEAR  :       <= 0 		Divisible by 400    Divisible by 4 but not 100   Divisible by 100 but not 400

nextDate :
no inputs (by virtue of the Constructor, manipulates a validDate)

previousDate :
no inputs (by virtue of the Constructor, manipulates a validDate)

compareTo :
DATE : isValidDate()   !isValidDate()

maxDays :
MONTH :         <= 0           > 12           1, 3, 5, 7, 8, 10, 12    4, 6, 9, 11        2
YEAR  :         <= 0           isLeapYear     !isLeapYear            0 < 

all getters:
no inputs

We can see that Day Month and Year are commons to a lot of methods

2.
I was missing line coverage for compareTo because my test only tested in the case where the days where different, i added a test for the months and a test for the years

3.
i added tests for isValidDate and isLeapYear to cover for those manually (i didn't add for maxDays which is a method i have made)
i made sure to go through every || or && branches to be able to increase my base coverage.

4. 
Mutation coverage of 94%
60/64 mutants killed








					




