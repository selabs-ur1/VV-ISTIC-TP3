package fr.istic.vv;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DateTest {

    @Test
    void testDateConstructorValidInputs() {
        // Valid inputs, expect no exceptions
        Date date = new Date(15, 6, 2022);
        assertNotNull(date);
        assertEquals(15, date.getDay());
        assertEquals(6, date.getMonth());
        assertEquals(2022, date.getYear());
    }

    @Test
    void testDateConstructorInvalidInputs() {
        // Invalid day, expect IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new Date(32, 6, 2022));

        // Invalid month, expect IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new Date(15, 13, 2022));

        // Invalid year, expect IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new Date(15, 6, -2022));
    }

    @Test
    void testIsValidDate() {
        // Valid date, expect true
        assertTrue(Date.isValidDate(15, 6, 2022));

        // Invalid day, expect false
        assertFalse(Date.isValidDate(32, 6, 2022));

        // Invalid month, expect false
        assertFalse(Date.isValidDate(15, 13, 2022));

        // Invalid year, expect false
        assertFalse(Date.isValidDate(15, 6, -2022));
    }

    @Test
    void testIsLeapYear() {
        // Leap year, expect true
        assertTrue(Date.isLeapYear(2020));

        // Non-leap year, expect false
        assertFalse(Date.isLeapYear(2022));
    }

    @Test
    void testNextDate() {
        // Normal next day, expect a new Date object representing the next day
        Date date = new Date(30, 6, 2022);
        Date nextDate = date.nextDate();
        assertEquals(1, nextDate.getDay());
        assertEquals(7, nextDate.getMonth());
        assertEquals(2022, nextDate.getYear());

        // End of the month, expect a new Date object representing the next month
        date = new Date(31, 12, 2022);
        nextDate = date.nextDate();
        assertEquals(1, nextDate.getDay());
        assertEquals(1, nextDate.getMonth());
        assertEquals(2023, nextDate.getYear());

        // End of the year, expect a new Date object representing the next year
        date = new Date(31, 12, 2022);
        nextDate = date.nextDate();
        assertEquals(1, nextDate.getDay());
        assertEquals(1, nextDate.getMonth());
        assertEquals(2023, nextDate.getYear());
    }

    @Test
    void testPreviousDate() {
        // Normal previous day, expect a new Date object representing the previous day
        Date date = new Date(2, 7, 2022);
        Date prevDate = date.previousDate();
        assertEquals(1, prevDate.getDay());
        assertEquals(7, prevDate.getMonth());
        assertEquals(2022, prevDate.getYear());

        // Start of the month, expect a new Date object representing the previous month
        date = new Date(1, 1, 2023);
        prevDate = date.previousDate();
        assertEquals(31, prevDate.getDay());
        assertEquals(12, prevDate.getMonth());
        assertEquals(2022, prevDate.getYear());

        // Start of the year, expect a new Date object representing the previous year
        date = new Date(1, 1, 2023);
        prevDate = date.previousDate();
        assertEquals(31, prevDate.getDay());
        assertEquals(12, prevDate.getMonth());
        assertEquals(2022, prevDate.getYear());
    }

    @Test
    void testCompareTo() {
        // Date is posterior to other
        Date date1 = new Date(15, 6, 2022);
        Date date2 = new Date(10, 6, 2022);
        assertTrue(date1.compareTo(date2) > 0);

        // Date is anterior to other
        date1 = new Date(15, 6, 2022);
        date2 = new Date(20, 6, 2022);
        assertTrue(date1.compareTo(date2) < 0);

        // Date and other represent the same date
        date1 = new Date(15, 6, 2022);
        date2 = new Date(15, 6, 2022);
        assertEquals(0, date1.compareTo(date2));

        final Date date1Copy = date1;
        // 'other' is null, expect NullPointerException
        assertThrows(NullPointerException.class, () -> date1Copy.compareTo(null));
    }
}