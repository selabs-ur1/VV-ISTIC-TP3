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

1. Input Space Partitioning design

- `isValidDate` and `isLeapYear` Method:

  Characteristics:

  - Year Range:

    - Block 1: Year is less than 0 (Invalid)
    
    - Block 2: Year is 1 or greater (Valid)

  - Month Range:

    - Block 1: Month is less than 1 (Invalid)

    - Block 2: Month is 1-12 (Valid)

    - Block 3: Month is greater than 12 (Invalid)
  
  - Day Range:

    - Block 1: Day is less than 1 (Invalid)

    - Block 2: Day is between 1 and number of days in associate month (Valid)

    - Block 3: Month is greater than number of days in associate month (Invalid)

  - Leap Year:

    - Block 1: Year is a leap year (Valid)

    - Block 2: Year is not a leap year (Valid)
  
  Tests:
  ```java
  assertFalse(Date.isValidDate(1, 1, -1), "Year less than 1 should be invalid");
  assertTrue(Date.isValidDate(1, 1, 2022), "Year 1 or greater should be valid");

  assertFalse(Date.isValidDate(1, 0, 2022), "Month less than 1 should be invalid");
  assertTrue(Date.isValidDate(1, 1, 2022), "Month between 1 and 12 should be valid");
  assertFalse(Date.isValidDate(1, 13, 2022), "Month greater than 12 should be invalid");

  assertFalse(Date.isValidDate(0, 1, 2020), "Day less than 1 should be invalid");
  assertTrue(Date.isValidDate(1, 1, 2022), "Day within the range should be valid");
  assertFalse(Date.isValidDate(32, 1, 2022), "Day greater than days in associated month should be invalid");
  assertFalse(Date.isValidDate(30, 2, 2022), "Day greater than days in associated month should be invalid");
  ```
  ```java
  assertTrue(Date.isLeapYear(2020), "2020 should be a leap year");
  assertFalse(Date.isLeapYear(2022), "2022 should not be a leap year");
  ```


- `nextDate` and `previousDate` Methods:

  Characteristics:

  - Month and Year Transition:

    - Block 1: Normal transition (within the same month and year)

    - Block 2: Transition to the next/previous month

    - Block 3: Transition to the next/previous year

  Tests:
  ```java
  Date date1 = new Date(1, 1, 2022);
  assertEquals("2022-01-02", date1.nextDate().toString(), "Next date within the same year/month test failed");

  Date date2 = new Date(31, 1, 2023);
  assertEquals("2023-02-01", date2.nextDate().toString(), "Next date transitioning to the next month test failed");

  Date date3 = new Date(31, 12, 2022);
  assertEquals("2023-01-01", date3.nextDate().toString(), "Next date transitioning to the next year test failed");
  ```
  ```java
  Date date1 = new Date(2, 1, 2023);
  assertEquals("2023-01-01", date1.previousDate().toString(), "Previous date within the same year/month test failed");

  Date date2 = new Date(1, 2, 2023);
  assertEquals("2023-01-31", date2.previousDate().toString(), "Previous date transitioning to the previous month test failed");

  Date date3 = new Date(1, 1, 2023);
  assertEquals("2022-12-31", date3.previousDate().toString(), "Previous date transitioning to the previous year test failed");
  ```

- `compareTo` Methods :

  Characteristics:

  - Date:

    - Block 1: Null

    - Block 2: The other date is anterior

    - Block 3: The other date is posterior

    - Block 4: The other date is the same

  Tests:
  ```java
  Date date1 = new Date(1, 1, 2022);
  Date date2 = new Date(1, 1, 2023);
  assertTrue(date1.compareTo(date2) < 0, "CompareTo test - The other date is posterior failed");

  Date date3 = new Date(1, 1, 2022);
  assertEquals(0, date1.compareTo(date3), "CompareTo test - The other date is the same failed");

  assertThrows(NullPointerException.class, () -> date1.compareTo(null), "CompareTo test - Null date failed");
  ```

2. Statement coverage of the test cases.

    I add all previous tests

4. Use PIT to evaluate the test suite

    Mutation result after 1st PIT execution:
   - Line Coverage : 91%
   - Mutation Coverage : 77%

I have 2 line non covered and 9 mutants that survive. I add this following test:
- non covered : I add assertFalse to `compareTo()`. 
- mutation : I add negative assertion to a lot of `negated conditional`. I add other test that I forget.

Mutation result after 2nd PIT execution:
- Line Coverage : 96%
- Mutation Coverage : 88%

  