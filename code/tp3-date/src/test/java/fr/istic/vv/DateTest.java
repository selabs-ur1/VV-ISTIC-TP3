package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testIsValidDateLeapYear() {
        assertTrue(Date.isValidDate(29, 2, 2020)); // Leap year
    }

    @Test
    void testIsValidDateEndOfYear() {
        assertTrue(Date.isValidDate(31, 12, 2021)); // End of year
    }

    @Test
    void testIsValidDateBeginningOfYear() {
        assertTrue(Date.isValidDate(1, 1, 2021)); // Beginning of year
    }

    @Test
    void testIsValidDateInvalidDayInFebruary() {
        assertFalse(Date.isValidDate(30, 2, 2021)); // Invalid day in February
    }

    @Test
    void testIsValidDateInvalidDayInApril() {
        assertFalse(Date.isValidDate(31, 4, 2021)); // Invalid day in April
    }

    @Test
    void testIsValidDateInvalidDayInJanuary() {
        assertFalse(Date.isValidDate(32, 1, 2021)); // Invalid day in January
    }

    @Test
    void testIsValidDateInvalidMonth() {
        assertFalse(Date.isValidDate(15, 13, 2021)); // Invalid month
    }

    @Test
    void testIsValidDateInvalidNegativeDay() {
        assertFalse(Date.isValidDate(0, 1, 2021)); // Invalid negative day
    }

    @Test
    void testIsValidDateInvalidNegativeMonth() {
        assertFalse(Date.isValidDate(1, 0, 2021)); // Invalid negative month
    }

    @Test
    void testIsValidDateInvalidNegativeYear() {
        assertFalse(Date.isValidDate(1, 1, 0)); // Invalid negative year
    }

    @Test
    void isLeapYear2024() {
        assertTrue(Date.isLeapYear(2024));
    }

    @Test
    void isLeapYear2001() {
        assertFalse(Date.isLeapYear(2001));
    }

    @Test
    void isLeapYear2004() {
        assertTrue(Date.isLeapYear(2004));
    }

    @Test
    void isLeapYear2100() {
        assertFalse(Date.isLeapYear(2100));
    }

    @Test
    void isLeapYear2400() {
        assertTrue(Date.isLeapYear(2400));
    }

    @Test
    void testConstructorValidLeapYearDate() {
        assertDoesNotThrow(() -> new Date(29, 2, 2020)); // Valid leap year date
    }

    @Test
    void testConstructorValidEndOfYearDate() {
        assertDoesNotThrow(() -> new Date(31, 12, 2021)); // Valid end of year date
    }

    @Test
    void testConstructorInvalidDayInFebruary() {
        assertThrows(IllegalArgumentException.class, () -> new Date(30, 2, 2021)); // Invalid day in February
    }

    @Test
    void testConstructorInvalidDayInApril() {
        assertThrows(IllegalArgumentException.class, () -> new Date(31, 4, 2021)); // Invalid day in April
    }

    @Test
    void testNextDateEndOfYear() {
        Date date = new Date(31, 12, 2021);
        Date nextDate = date.nextDate();
        assertEquals(new Date(1, 1, 2022), nextDate); // Next day is new year
    }

    @Test
    void testNextDateLeapYearFebruary() {
        Date date = new Date(28, 2, 2020);
        Date nextDate = date.nextDate();
        assertEquals(new Date(29, 2, 2020), nextDate); // Next day in leap year February
    }

    @Test
    void testPreviousDateBeginningOfYear() {
        Date date = new Date(1, 1, 2022);
        Date previousDate = date.previousDate();
        assertEquals(new Date(31, 12, 2021), previousDate); // Previous day is end of year
    }

    @Test
    void testPreviousDateLeapYearMarch() {
        Date date = new Date(1, 3, 2020);
        Date previousDate = date.previousDate();
        assertEquals(new Date(29, 2, 2020), previousDate); // Previous day in leap year February
    }

    @Test
    void testCompareToAnterior() {
        Date date1 = new Date(1, 1, 2021);
        Date date2 = new Date(31, 12, 2021);
        assertTrue(date1.compareTo(date2) < 0); // date1 is anterior to date2
    }

    @Test
    void testCompareToPosterior() {
        Date date1 = new Date(1, 1, 2021);
        Date date2 = new Date(31, 12, 2021);
        assertTrue(date2.compareTo(date1) > 0); // date2 is posterior to date1
    }

    @Test
    void testCompareToSameDate() {
        Date date1 = new Date(1, 1, 2021);
        assertEquals(0, date1.compareTo(new Date(1, 1, 2021))); // Same date
    }

    @Test
    void testCompareToNull() {
        Date date = new Date(1, 1, 2021);
        assertThrows(NullPointerException.class, () -> date.compareTo(null)); // Null comparison
    }

    @Test
    void testCompareToDifferentYear() {
        Date date1 = new Date(1, 1, 2021);
        Date date2 = new Date(1, 1, 2022);
        assertTrue(date1.compareTo(date2) < 0); // date1 is in a different year
    }

    @Test
    void testCompareToDifferentMonth() {
        Date date1 = new Date(1, 1, 2021);
        Date date2 = new Date(1, 2, 2021);
        assertTrue(date1.compareTo(date2) < 0); // date1 is in a different month
    }
}