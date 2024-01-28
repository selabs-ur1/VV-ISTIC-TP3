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

1) 
|           | 31 days month | 30 days month | February   |
|-----------|---------------|---------------|------------|
| Last day  | 31/01/2023    | 30/04/2023    | 28/02/2023 |
| Leap year | 01/01/2024    | 01/04/2024    | 01/02/2024 |

| Characteristics |                | Blocks |    |                               |                     |    |       |
|-----------------|----------------|--------|----|-------------------------------|---------------------|----|-------|
|                 |                |   b1   | b2 | b3                            | b4                  | b5 | b6    |
|        q1       | Value of year  | < 0    | 0  | valid leap year               | valid common year   |    |       |
|        q2       | Value of month | < 0    | 0  | { 1, 3, 5, 7, 8, 10, 12}      | { 4, 6, 9, 11 }     | 2  | \> 12 |
|        q3       | Value of day   | < 0    | 0  | \>= 1 and <= max(month, year) | \> max(month, year) |    |       |

Common characteristics : 

- q1 is common to isValidDate and isLeapYear
- q2 & q3 are only in isValidDate

2) By running the coverage we have added test for previousDate on a month when result should be 30/xx/xxxx

```java
    @Test
    public void testPreviousDateToA30DaysMonth() {
        Date date1 = new Date(1,5,2023);
        Date date2 = new Date(30,4,2023);
        assert(date2.compareTo(date1.previousDate()) == 0);
    }
```

With this test we reach 100% coverage with IntelliJ built-in coverage tool.

3) All my test respects Best Choice Coverage, no tests added

4) Pitest report show me 40% coverage and on mutation. By checking 
all mutation not covered or surviving, we have added :
```java
@Test
public void testNextDateWithIntegerAddition() {
        Date date = new Date(30, 12, 2023);
        Date nextDate = date.nextDate();
        assertEquals(31, nextDate.getDay());
        }

@Test
public void testNextDateWithIncrementChange() {
        Date date = new Date(31, 12, 2023);
        Date nextDate = date.nextDate();
        assertEquals(1, nextDate.getDay());
        assertEquals(1, nextDate.getMonth());
        assertEquals(2024, nextDate.getYear());
        }

@Test
public void testNextDateNotNull() {
        Date date = new Date(30, 12, 2023);
        assertNotNull(date.nextDate());
        }

@Test
public void testPreviousDateWithIntegerSubtraction() {
        Date date = new Date(2, 1, 2023);
        Date prevDate = date.previousDate();
        assertEquals(1, prevDate.getDay());
        }

@Test
public void testPreviousDateWithIncrementChange() {
        Date date = new Date(1, 1, 2023);
        Date prevDate = date.previousDate();
        assertEquals(31, prevDate.getDay());
        assertEquals(12, prevDate.getMonth());
        assertEquals(2022, prevDate.getYear());
        }

@Test
public void testPreviousDateNotNull() {
        Date date = new Date(2, 1, 2023);
        assertNotNull(date.previousDate());
        }

@Test
public void testCompareToWithZeroReturnValue() {
        Date date1 = new Date(1, 1, 2023);
        Date date2 = new Date(1, 1, 2023);
        assertEquals(0, date1.compareTo(date2));
        }

@Test
public void testCompareToMutation() {
        Date date1 = new Date(1, 1, 2023);
        Date date2 = new Date(2, 2, 2024);
        assertTrue(date1.compareTo(date2) != 0);
        }

@Test
public void testPreviousDateNegatedToA31DaysMonth() {
        Date date1 = new Date(1,5,2023);
        Date date2 = new Date(1,4,2023);
        assert(date2.compareTo(date1.previousDate()) < 0);
        } 
```

Finally, we had 85% line coverage and 96% Mutation coverage, but 
the last mutation surviving is false positive.