package fr.istic.vv;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.istic.vv.*;

class DateTest {

    @Test
    void testIsValidDate() {
        assertTrue(Date.isValidDate(1, 1, 2000));
        assertFalse(Date.isValidDate(31, 4, 2023));
        assertTrue(Date.isValidDate(29, 2, 2024));
        assertFalse(Date.isValidDate(29, 2, 2023));


        // Test cases for month < 1
        assertFalse(Date.isValidDate(1, 0, 2022));
        assertTrue(Date.isValidDate(1, 1, 2022));

        // Test cases for month > 12
        assertFalse(Date.isValidDate(1, 13, 2022));
        assertTrue(Date.isValidDate(1, 12, 2022));

        // Test cases for day < 1
        assertFalse(Date.isValidDate(0, 1, 2022));
        assertTrue(Date.isValidDate(1, 1, 2022));

        // Test cases for month == 4 || month == 6 || month == 9 || month == 11
        assertTrue(Date.isValidDate(30, 4, 2022));
        assertFalse(Date.isValidDate(31, 4, 2022));
        assertTrue(Date.isValidDate(30, 6, 2022));
        assertFalse(Date.isValidDate(31, 6, 2022));
        assertTrue(Date.isValidDate(30, 9, 2022));
        assertFalse(Date.isValidDate(31, 9, 2022));
        assertTrue(Date.isValidDate(30, 11, 2022));
        assertFalse(Date.isValidDate(31, 11, 2022));

        // Test cases for month == 2 with a leap year and non-leap year
        assertTrue(Date.isValidDate(29, 2, 2024));
        assertFalse(Date.isValidDate(29, 2, 2023));
    }

    @Test
    void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2000));  // Leap year
        assertFalse(Date.isLeapYear(2022)); // Non-leap year
    }

    @Test
    void testNextDate() {
        Date currentDate = new Date(1, 1, 2022);
        Date nextDate = currentDate.nextDate();
        assertEquals(new Date(2, 1, 2022), nextDate); // Next day

        currentDate = new Date(31, 12, 2022);
        nextDate = currentDate.nextDate();
        assertEquals(new Date(1, 1, 2023), nextDate); // Next year

        currentDate = new Date(28, 2, 2024);
        nextDate = currentDate.nextDate();
        assertEquals(new Date(29, 2, 2024), nextDate); // Leap year transition
    }

    @Test
    void testPreviousDate() {
        Date currentDate = new Date(2, 1, 2022);
        Date prevDate = currentDate.previousDate();
        assertEquals(new Date(1, 1, 2022), prevDate); // Previous day

        currentDate = new Date(1, 1, 2022);
        prevDate = currentDate.previousDate();
        assertEquals(new Date(31, 12, 2021), prevDate); // Previous year

        currentDate = new Date(29, 2, 2024);
        prevDate = currentDate.previousDate();
        assertEquals(new Date(28, 2, 2024), prevDate); // Leap year transition
    }

    @Test
    void testCompareTo() {
        Date date1 = new Date(1, 1, 2022);
        Date date2 = new Date(2, 1, 2022);
        assertTrue(date1.compareTo(date2) < 0); // Comparison based on day

        date1 = new Date(1, 1, 2022);
        date2 = new Date(1, 2, 2022);
        assertTrue(date1.compareTo(date2) < 0); // Comparison based on month

        date1 = new Date(1, 1, 2022);
        date2 = new Date(1, 1, 2023);
        assertTrue(date1.compareTo(date2) < 0); // Comparison based on year

        // Test NullPointerException
        Date finalDate = date1;
        assertThrows(NullPointerException.class, () -> finalDate.compareTo(null));
    }
}
