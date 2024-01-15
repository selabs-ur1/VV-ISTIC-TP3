package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testValidDate() {
        assertDoesNotThrow(() -> new Date(1, 1, 2024));
    }

    @Test
    void testInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> new Date(30, 2, 2024));
    }

    ///

    @Test
    void testIsValidDate() {
        // Valid date
        assertTrue(Date.isValidDate(1, 1, 2024));

        // Invalid year
        assertFalse(Date.isValidDate(1, 1, -2024));

        // Invalid month (< 1)
        assertFalse(Date.isValidDate(1, 0, 2024));

        // Invalid month (> 12)
        assertFalse(Date.isValidDate(1, 13, 2024));

        // Invalid day (< 1)
        assertFalse(Date.isValidDate(0, 1, 2024));

        // Invalid day (> maxDays in month)
        assertFalse(Date.isValidDate(32, 1, 2024));

        // February in a non-leap year with day > 28
        assertFalse(Date.isValidDate(29, 2, 2023));

        // February in a leap year with day > 29
        assertFalse(Date.isValidDate(30, 2, 2024));
    }

    @Test
    void testIsInvalidDate() {
        assertFalse(Date.isValidDate(30, 2, 2024));
    }

    ///

    @Test
    void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2020));
    }

    @Test
    void testIsNotLeapYear() {
        assertFalse(Date.isLeapYear(2021));
    }

    ///

    @Test
    void testNextDate() {
        Date currentDate = new Date(31, 1, 2024);
        Date nextDate = currentDate.nextDate();
        assertEquals(new Date(1, 2, 2024), nextDate);
    }

    @Test
    void testPreviousDate() {
        Date currentDate = new Date(1, 2, 2024);
        Date previousDate = currentDate.previousDate();
        assertEquals(new Date(31, 1, 2024), previousDate);
    }

    ///

    @Test
    void testCompareToEqualDates() {
        Date date1 = new Date(1, 1, 2024);
        Date date2 = new Date(1, 1, 2024);
        assertEquals(0, date1.compareTo(date2));
    }

    @Test
    void testCompareToPosteriorDate() {
        Date date1 = new Date(2, 1, 2024);
        Date date2 = new Date(1, 1, 2024);
        assertTrue(date1.compareTo(date2) > 0);
    }

    @Test
    void testCompareToAnteriorDate() {
        Date date1 = new Date(1, 1, 2024);
        Date date2 = new Date(2, 1, 2024);
        assertTrue(date1.compareTo(date2) < 0);
    }

    @Test
    void testCompareToNull() {
        Date date1 = new Date(1, 1, 2024);
        assertThrows(NullPointerException.class, () -> date1.compareTo(null));
    }

    ///
    // after coverage evaluation
    ///

    @Test
    void testToString() {
        Date date = new Date(1, 2, 2022);
        assertEquals("2022-02-01", date.toString());
    }

    ///
    // after PIT evaluation
    ///

    @Test
    void testNextDateWithMaxDays() {
        assertEquals(new Date(1, 2, 2024), new Date(31, 1, 2024).nextDate());
        assertEquals(new Date(1, 3, 2024), new Date(29, 2, 2024).nextDate());
        assertEquals(new Date(1, 4, 2024), new Date(31, 3, 2024).nextDate());
    }

    @Test
    void testBooleanTrueReturn() {
        assertTrue(new Date(1, 1, 2024).equals(new Date(1, 1, 2024)));
    }

    @Test
    void testBooleanFalseReturn() {
        assertFalse(new Date(1, 1, 2024).equals(new Date(1, 2, 2024)));
    }

    @Test
    void testPrimitiveReturns() {
        Date date1 = new Date(1, 1, 2024);
        Date date2 = new Date(2, 1, 2024);
        assertTrue(date1.compareTo(date2) < 0);
    }

}