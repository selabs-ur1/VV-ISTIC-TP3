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

1. Methods: Date, isValidDate, previousDate, nextDate
They have the same *Input Space Partitioning* because they use the same attributes in the same way.

| characteristics | blocks |                                           |             |
| --------------- | ------ | ----------------------------------------- | ----------- |
| day             | <=0    | [1;MAX_DAY]                               | \>= MAX_DAY |
| month           | < 1    | [1;12]                                    | \> 12       |
| year            | < 0    | leap year                                 | other       |

Method isLeapYear only uses the year.

| characteristics | blocks |                                           |             |
| --------------- | ------ | ----------------------------------------- | ----------- |
| year            | < 0    | leap year                                 | other       |

Method compareTo (uses all attributes but in a different way than the other methods).

| characteristics     | blocks |           |      |
| ------------------- | ------ | --------- | ---- |
| day - other.day     | < 0    | \= 0      | \> 0 |
| month - other.month | < 0    | \= 0      | \> 0 |
| year - other.year   | < 0    | \= 0      | \> 0 |

2. At first try, we only reached a line coverage of 95%. We were missing the case of the first of January when we have to change the year during the previousDate method. We added the test:
```java
@Test
public void testPreviousDateYear() {
    assertEquals(new Date(DAY_MAX_31, MONTH_VALID_12, YEAR_VALID), DATE_PREVIOUS_01_01.previousDate());
}
```

3. This does not apply to our code.

4. Our initial run of PIT gave us the following resutls:

```
================================================================================
13:25:22 PIT >> INFO : Completed in 7 seconds
- Mutators
================================================================================
> org.pitest.mutationtest.engine.gregor.mutators.returns.PrimitiveReturnsMutator
>> Generated 3 Killed 3 (100%)
> KILLED 3 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.ConditionalsBoundaryMutator
>> Generated 12 Killed 6 (50%)
> KILLED 6 SURVIVED 6 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.IncrementsMutator
>> Generated 6 Killed 6 (100%)
> KILLED 6 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.returns.BooleanTrueReturnValsMutator
>> Generated 8 Killed 4 (50%)
> KILLED 4 SURVIVED 2 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 2 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.returns.NullReturnValsMutator
>> Generated 2 Killed 2 (100%)
> KILLED 2 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.MathMutator
>> Generated 11 Killed 9 (82%)
> KILLED 9 SURVIVED 1 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 1 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.returns.EmptyObjectReturnValsMutator
>> Generated 1 Killed 1 (100%)
> KILLED 1 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.NegateConditionalsMutator
>> Generated 33 Killed 32 (97%)
> KILLED 32 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 1 
--------------------------------------------------------------------------------
================================================================================
- Timings
================================================================================
> pre-scan for mutations : < 1 second
> scan classpath : < 1 second
> coverage and dependency analysis : 1 seconds
> build mutation tests : < 1 second
> run mutation analysis : 5 seconds
--------------------------------------------------------------------------------
> Total  : 7 seconds
--------------------------------------------------------------------------------
================================================================================
- Statistics
================================================================================
>> Line Coverage (for mutated classes only): 48/48 (100%)
>> Generated 76 mutations Killed 63 (83%)
>> Mutations with no coverage 4. Test strength 88%
>> Ran 194 tests (2.55 tests per mutation)
```



