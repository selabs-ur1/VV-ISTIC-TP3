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

Code: 

public class Date implements Comparable<Date> {
    private final int day;
    private final int month;
    private final int year;

    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }

        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12 || day < 1) {
            return false;
        }

        int maxDays = getMaxDaysInMonth(month, year);
        return day <= maxDays;
    }

    private static int getMaxDaysInMonth(int month, int year) {
        if (month == 2 && isLeapYear(year)) {
            return 29;
        }

        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return daysInMonth[month];
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public Date nextDate() {
        int nextDay = day + 1;
        int nextMonth = month;
        int nextYear = year;

        if (nextDay > getMaxDaysInMonth(nextMonth, nextYear)) {
            nextDay = 1;
            nextMonth++;
            if (nextMonth > 12) {
                nextMonth = 1;
                nextYear++;
            }
        }

        return new Date(nextDay, nextMonth, nextYear);
    }

    public Date previousDate() {
        int prevDay = day - 1;
        int prevMonth = month;
        int prevYear = year;

        if (prevDay < 1) {
            prevMonth--;
            if (prevMonth < 1) {
                prevMonth = 12;
                prevYear--;
            }
            prevDay = getMaxDaysInMonth(prevMonth, prevYear);
        }

        return new Date(prevDay, prevMonth, prevYear);
    }

    @Override
    public int compareTo(Date other) {
        Objects.requireNonNull(other, "Cannot compare to null");

        if (this.year != other.year) {
            return this.year - other.year;
        }
        if (this.month != other.month) {
            return this.month - other.month;
        }
        return this.day - other.day;
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }
}

public class DateTest {

    @Test
    public void testValidDate() {
        assertTrue(Date.isValidDate(1, 1, 2023));
        assertFalse(Date.isValidDate(29, 2, 2023)); // Not a leap year
        assertTrue(Date.isValidDate(29, 2, 2024)); // Leap year
        assertFalse(Date.isValidDate(31, 4, 2023)); // Invalid month
        assertTrue(Date.isValidDate(31, 12, -1)); // Invalid year
        assertFalse(Date.isValidDate(0, 4, 2023)); // Invalid day
        assertFalse(Date.isValidDate(1, 0, 2023)); // Invalid month
        assertFalse(Date.isValidDate(1, 13, 2023)); // Invalid month
    }

    @Test
    public void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2020));
        assertFalse(Date.isLeapYear(1900));
        assertTrue(Date.isLeapYear(2000));
        assertFalse(Date.isLeapYear(2023));
    }

    @Test
    public void testNextDate() {
        Date date = new Date(31, 1, 2023);
        assertEquals(new Date(1, 2, 2023).toString(), date.nextDate().toString());

        date = new Date(28, 2, 2023);
        assertEquals(new Date(1, 3, 2023).toString(), date.nextDate().toString());

        date = new Date(31, 12, 2023);
        assertEquals(new Date(1, 1, 2024).toString(), date.nextDate().toString());
    }

    @Test
    public void testPreviousDate() {
        Date date = new Date(1, 2, 2023);
        assertEquals(new Date(31, 1, 2023).toString(), date.previousDate().toString());

        date = new Date(1, 3, 2023);
        assertEquals(new Date(28, 2, 2023).toString(), date.previousDate().toString());

        date = new Date(1, 1, 2024);
        assertEquals(new Date(31, 12, 2023).toString(), date.previousDate().toString());
    }

    @Test
    public void testCompareTo() {
        Date date1 = new Date(1, 1, 2023);
        Date date2 = new Date(1, 1, 2023);
        assertEquals(0, date1.compareTo(date2));

        date1 = new Date(1, 1, 2023);
        date2 = new Date(2, 1, 2023);
        assertTrue(date1.compareTo(date2) < 0);

        date1 = new Date(1, 1, 2023);
        date2 = new Date(1, 2, 2023);
        assertTrue(date1.compareTo(date2) < 0);

        date1 = new Date(1, 1, 2023);
        date2 = new Date(1, 1, 2022);
        assertTrue(date1.compareTo(date2) > 0);
    }

1) Plusieurs blocks sont identifiés :
Les jours doivent avoir une valueur >= 1 et <= max(mois, année),
Les mois doivent avoir une valeur >=1 et <=12,
Les années n'ont pas vraiment de limite supérieure ou inférieure.

2) Avec le code fournis précedement, j'ai 100% de coverage. J'utilise l'outil fourni par IntelliJ pour l'évaluer.

3) J'ai déjà 100% de coverage je ne sais pas quoi ajouter ici.

4) En utilisant PIT j'ai un score de 98% de line coverage et 88% de mutation coverage.
Pour améliorer cela j'ai par exemple ajouté des cas de test pour la ligne "if (month < 1 || month > 12 || day < 1)"
car PIT m'indiquait de des mutations "survivaient".

