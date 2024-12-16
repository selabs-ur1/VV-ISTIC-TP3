package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void shouldReturnFormattedStringWhenToStringGivenValidDate() {
        Date date = new Date(15, 6, 2023);
        assertEquals("15/06/2023", date.toString());
    }

    @Test
    void shouldReturnDayWhenGetDayGivenValidDate() {
        Date date = new Date(15, 6, 2023);
        assertEquals(15, date.getDay());
    }

    @Test
    void shouldReturnMonthWhenGetMonthGivenValidDate() {
        Date date = new Date(15, 6, 2023);
        assertEquals(6, date.getMonth());
    }

    @Test
    void shouldReturnYearWhenGetYearGivenValidDate() {
        Date date = new Date(15, 6, 2023);
        assertEquals(2023, date.getYear());
    }

    @Test
    void shouldNotThrowWhenConstructorGivenValidDateFor1900() {
        assertDoesNotThrow(() -> new Date(1, 1, 1900));
    }

    @Test
    void shouldNotThrowWhenConstructorGivenValidDateFor2100() {
        assertDoesNotThrow(() -> new Date(31, 12, 2100));
    }

    @Test
    void shouldThrowExceptionWhenConstructorGivenInvalidDayFor0() {
        assertThrows(IllegalArgumentException.class, () -> new Date(0, 1, 2000));
    }

    @Test
    void shouldThrowExceptionWhenConstructorGivenInvalidDayFor32() {
        assertThrows(IllegalArgumentException.class, () -> new Date(32, 1, 2000));
    }

    @Test
    void shouldThrowExceptionWhenConstructorGivenInvalidMonthFor0() {
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 0, 2000));
    }

    @Test
    void shouldThrowExceptionWhenConstructorGivenInvalidMonthFor13() {
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 13, 2000));
    }

    @Test
    void shouldReturnTrueWhenIsValidDateGivenValidDateFor2000() {
        assertTrue(Date.isValidDate(1, 1, 2000));
    }

    @Test
    void shouldReturnTrueWhenIsValidDateGivenValidLeapYearDate() {
        assertTrue(Date.isValidDate(29, 2, 2024));
    }

    @Test
    void shouldReturnTrueWhenIsValidDateGivenValidDateFor2023() {
        assertTrue(Date.isValidDate(28, 2, 2023));
    }

    @Test
    void shouldReturnTrueWhenIsValidDateGivenValidDateFor2000EndOfMonth() {
        assertTrue(Date.isValidDate(31, 1, 2000));
    }

    @Test
    void shouldReturnFalseWhenIsValidDateGivenInvalidDayInFebruaryNonLeapYear() {
        assertFalse(Date.isValidDate(30, 2, 2023));
    }

    @Test
    void shouldReturnFalseWhenIsValidDateGivenInvalidDayInFebruaryNonLeapYear2() {
        assertFalse(Date.isValidDate(29, 2, 2023));
    }

    @Test
    void shouldReturnFalseWhenIsValidDateGivenInvalidDayInJanuary() {
        assertFalse(Date.isValidDate(32, 1, 2000));
    }

    @Test
    void shouldReturnFalseWhenIsValidDateGivenInvalidDayLowerThanOne() {
        assertFalse(Date.isValidDate(0, 1, 2000));
    }

    @Test
    void shouldReturnFalseWhenIsValidDateGivenInvalidMonthLowerThanOne() {
        assertFalse(Date.isValidDate(10, 0, 2024));
    }

    @Test
    void shouldReturnTrueWhenIsValidDateGivenValidDate() {
        assertTrue(Date.isValidDate(15, 6, 2024));
    }

    @Test
    void shouldReturnFalseWhenIsValidDateGivenInvalidDayGreaterThanDaysInMonth() {
        assertFalse(Date.isValidDate(32, 6, 2024));
    }

    @Test
    void shouldReturnTrueWhenIsLeapYearGivenDivisibleBy4Not100For2024() {
        assertTrue(Date.isLeapYear(2024));
    }

    @Test
    void shouldReturnTrueWhenIsLeapYearGivenDivisibleBy4Not100For2004() {
        assertTrue(Date.isLeapYear(2004));
    }

    @Test
    void shouldReturnTrueWhenIsLeapYearGivenDivisibleBy400For2000() {
        assertTrue(Date.isLeapYear(2000));
    }

    @Test
    void shouldReturnTrueWhenIsLeapYearGivenDivisibleBy400For1600() {
        assertTrue(Date.isLeapYear(1600));
    }

    @Test
    void shouldReturnFalseWhenIsLeapYearGivenDivisibleBy100Not400For1900() {
        assertFalse(Date.isLeapYear(1900));
    }

    @Test
    void shouldReturnFalseWhenIsLeapYearGivenDivisibleBy100Not400For2100() {
        assertFalse(Date.isLeapYear(2100));
    }

    @Test
    void shouldReturnFalseWhenIsLeapYearGivenNotDivisibleBy4For2023() {
        assertFalse(Date.isLeapYear(2023));
    }

    @Test
    void shouldReturnFalseWhenIsLeapYearGivenNotDivisibleBy4For2019() {
        assertFalse(Date.isLeapYear(2019));
    }

    @Test
    void shouldReturnNextDateWhenNextDateGivenNormalDay() {
        Date date = new Date(15, 6, 2023);
        assertEquals(new Date(16, 6, 2023).toString(), date.nextDate().toString());
    }

    @Test
    void shouldReturnNextDateWhenNextDateGivenEndOfMonth() {
        Date date = new Date(31, 1, 2023);
        assertEquals(new Date(1, 2, 2023).toString(), date.nextDate().toString());
    }

    @Test
    void shouldReturnNextDateWhenNextDateGivenEndOfFebruaryLeapYear() {
        Date date = new Date(29, 2, 2024);
        assertEquals(new Date(1, 3, 2024).toString(), date.nextDate().toString());
    }

    @Test
    void shouldReturnNextDateWhenNextDateGivenEndOfFebruaryNonLeapYear() {
        Date date = new Date(28, 2, 2023);
        assertEquals(new Date(1, 3, 2023).toString(), date.nextDate().toString());
    }

    @Test
    void shouldReturnNextDateWhenNextDateGivenEndOfYear() {
        Date date = new Date(31, 12, 2023);
        assertEquals(new Date(1, 1, 2024).toString(), date.nextDate().toString());
    }

    @Test
    void shouldReturnPreviousDateWhenPreviousDateGivenNormalDay() {
        Date date = new Date(15, 6, 2023);
        assertEquals(new Date(14, 6, 2023).toString(), date.previousDate().toString());
    }

    @Test
    void shouldReturnPreviousDateWhenPreviousDateGivenStartOfMonth() {
        Date date = new Date(1, 3, 2023);
        assertEquals(new Date(28, 2, 2023).toString(), date.previousDate().toString());
    }

    @Test
    void shouldReturnPreviousDateWhenPreviousDateGivenStartOfMarchLeapYear() {
        Date date = new Date(1, 3, 2024);
        assertEquals(new Date(29, 2, 2024).toString(), date.previousDate().toString());
    }

    @Test
    void shouldReturnPreviousDateWhenPreviousDateGivenStartOfYear() {
        Date date = new Date(1, 1, 2023);
        assertEquals(new Date(31, 12, 2022).toString(), date.previousDate().toString());
    }

    @Test
    void shouldReturnZeroWhenCompareToGivenIdenticalDates() {
        Date date1 = new Date(15, 6, 2023);
        Date date2 = new Date(15, 6, 2023);
        assertEquals(0, date1.compareTo(date2));
    }

    @Test
    void shouldReturnNegativeWhenCompareToGivenEarlierDate() {
        Date date1 = new Date(15, 6, 2023);
        Date date2 = new Date(16, 6, 2023);
        assertTrue(date1.compareTo(date2) < 0);
    }

    @Test
    void shouldThrowExceptionWhenCompareToGivenNull() {
        Date date1 = new Date(1, 1, 2000);
        Date date2 = null;
        assertThrows(IllegalArgumentException.class, () -> date1.compareTo(date2));
    }

    @Test
    void shouldReturnNegativeWhenCompareToGivenYearOfThisDateIsLessThanOtherDate() {
        Date date1 = new Date(1, 1, 2000);
        Date date2 = new Date(1, 1, 2024);
        assertTrue(date1.compareTo(date2) < 0);
    }

    @Test
    void shouldReturnNegativeWhenCompareToGivenMonthOfThisDateIsLessThanOtherDate() {
        Date date1 = new Date(1, 1, 2000);
        Date date2 = new Date(1, 2, 2000);
        assertTrue(date1.compareTo(date2) < 0);
    }

    @Test
    void shouldReturnNegativeWhenCompareToGivenDayOfThisDateIsLessThanOtherDate() {
        Date date1 = new Date(1, 1, 2000);
        Date date2 = new Date(2, 1, 2000);
        assertTrue(date1.compareTo(date2) < 0);
    }

    @Test
    void shouldReturnPositiveWhenCompareToGivenYearOfThisDateIsGreaterThanOtherDate() {
        Date date1 = new Date(1, 1, 2024);
        Date date2 = new Date(1, 1, 2000);
        assertTrue(date1.compareTo(date2) > 0);
    }

    @Test
    void shouldReturnPositiveWhenCompareToGivenMonthOfThisDateIsGreaterThanOtherDate() {
        Date date1 = new Date(1, 2, 2000);
        Date date2 = new Date(1, 1, 2000);
        assertTrue(date1.compareTo(date2) > 0);
    }

    @Test
    void shouldReturnPositiveWhenCompareToGivenDayOfThisDateIsGreaterThanOtherDate() {
        Date date1 = new Date(2, 1, 2000);
        Date date2 = new Date(1, 1, 2000);
        assertTrue(date1.compareTo(date2) > 0);
    }

    @Test
    void shouldReturn0WhenCompareToGivenSameDate() {
        Date date1 = new Date(1, 1, 2000);
        Date date2 = new Date(1, 1, 2000);
        assertEquals(0, date1.compareTo(date2));
    }
}