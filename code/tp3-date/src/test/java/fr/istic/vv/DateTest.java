package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testIsValidDate() {
        assertTrue(Date.isValidDate(1, 1, 1));
        assertTrue(Date.isValidDate(31, 12, 2024));
        assertTrue(Date.isValidDate(29, 2, 2024));

        assertFalse(Date.isValidDate(0, 0, 0));
        assertFalse(Date.isValidDate(29, 2, 2023));
        assertFalse(Date.isValidDate(31, 4, 2024));
        assertFalse(Date.isValidDate(1, 13, 2024));
    }

    @Test
    void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2024));
        assertTrue(Date.isLeapYear(2000));

        assertFalse(Date.isLeapYear(2023));
        assertFalse(Date.isLeapYear(1900));
    }

    @Test
    void testDaysInMonth() {
        assertEquals(31, Date.daysInMonth(1, 2024));
        assertEquals(29, Date.daysInMonth(2, 2024));

        assertEquals(28, Date.daysInMonth(2, 2023));

        assertEquals(30, Date.daysInMonth(4, 2024));
        assertEquals(30, Date.daysInMonth(6, 2024));
        assertEquals(30, Date.daysInMonth(9, 2024));
        assertEquals(30, Date.daysInMonth(11, 2024));

        assertEquals(0, Date.daysInMonth(0, 2024));
        assertEquals(0, Date.daysInMonth(13, 2024));
    }

    @Test
    void testNextDate() {
        // Cas de test: date, expectedNextDate
        Object[][] testCases = {
            {new Date(1, 1, 2024), new Date(2, 1, 2024)},
            {new Date(31, 12, 2024), new Date(1, 1, 2025)},
            {new Date(29, 2, 2024), new Date(1, 3, 2024)},
            {new Date(28, 2, 2024), new Date(29, 2, 2024)},
            {new Date(28, 2, 2023), new Date(1, 3, 2023)},
            {new Date(30, 12, 2024), new Date(31, 12, 2024)},
            {new Date(30, 4, 2024), new Date(1, 5, 2024)},
            {new Date(30, 6, 2024), new Date(1, 7, 2024)},
            {new Date(30, 9, 2024), new Date(1, 10, 2024)},
            {new Date(30, 11, 2024), new Date(1, 12, 2024)},
            {new Date(15, 4, 2024), new Date(16, 4, 2024)},
            {new Date(15, 6, 2024), new Date(16, 6, 2024)},
            {new Date(15, 9, 2024), new Date(16, 9, 2024)},
            {new Date(15, 11, 2024), new Date(16, 11, 2024)},
            {new Date(31, 10, 2024), new Date(1, 11, 2024)},
            {new Date(1, 2, 2024), new Date(2, 2, 2024)},
        };

        for (Object[] testCase : testCases) {
            Date date = (Date) testCase[0];
            Date expectedNextDate = (Date) testCase[1];
            assertEquals(expectedNextDate.toString(), date.nextDate().toString());
        }
    }

    @Test
    void testPreviousDate() {
        // Cas de test: date, expectedPreviousDate
        Object[][] testCases = {
            {new Date(1, 1, 2024), new Date(31, 12, 2023)},
            {new Date(1, 3, 2024), new Date(29, 2, 2024)},
            {new Date(1, 3, 2023), new Date(28, 2, 2023)},
            {new Date(1, 5, 2024), new Date(30, 4, 2024)},
            {new Date(1, 7, 2024), new Date(30, 6, 2024)},
            {new Date(1, 10, 2024), new Date(30, 9, 2024)},
            {new Date(1, 12, 2024), new Date(30, 11, 2024)},
            {new Date(2, 1, 2024), new Date(1, 1, 2024)},
            {new Date(1, 11, 2024), new Date(31, 10, 2024)},
        };

        for (Object[] testCase : testCases) {
            Date date = (Date) testCase[0];
            Date expectedPreviousDate = (Date) testCase[1];
            assertEquals(expectedPreviousDate.toString(), date.previousDate().toString());
        }
    }

    @Test
    void testCompareTo() {
        Date date1 = new Date(1, 1, 2024);
        Date date2 = new Date(1, 1, 2024);
        assertEquals(0, date1.compareTo(date2));

        date1 = new Date(1, 1, 2024);
        date2 = new Date(2, 1, 2024);
        assertEquals(-1, date1.compareTo(date2));

        date1 = new Date(1, 1, 2024);
        date2 = new Date(31, 12, 2023);
        assertEquals(1, date1.compareTo(date2));

        date1 = new Date(1, 1, 2024);
        date2 = new Date(1, 1, 2023);
        assertEquals(1, date1.compareTo(date2));

        date1 = new Date(1, 1, 2024);
        date2 = new Date(1, 1, 2025);
        assertEquals(-1, date1.compareTo(date2));

        date1 = new Date(1, 1, 2024);
        date2 = new Date(1, 2, 2024);
        assertEquals(-1, date1.compareTo(date2));

        assertThrows(NullPointerException.class, () -> {
            Date date = new Date(1, 1, 2024);
            date.compareTo(null);
        });
    }

    @Test
    void testCannotInstantiateInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> new Date(0, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Date(29, 2, 2023));
        assertThrows(IllegalArgumentException.class, () -> new Date(31, 4, 2024));
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 13, 2024));
    }

    @Test
    void testToString() {
        Date date = new Date(1, 1, 2024);
        assertEquals("01/01/2024", date.toString());
    }
}