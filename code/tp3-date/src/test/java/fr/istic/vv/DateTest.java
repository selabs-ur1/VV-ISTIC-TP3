package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

    @Test
    public void testIsValidDate() {
        assertTrue(Date.isValidDate(29, 2, 2020)); // Leap year
        assertFalse(Date.isValidDate(29, 2, 2021)); // Non-leap year
        assertTrue(Date.isValidDate(31, 12, 2023)); // Valid end-of-year date
        assertFalse(Date.isValidDate(32, 1, 2023)); // Invali day
    }

    @Test
    public void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2020)); // Divisible by 4, not 100
        assertFalse(Date.isLeapYear(1900)); // Divisible by 100, not 400
        assertTrue(Date.isLeapYear(2000)); // Divisible by 400
        assertFalse(Date.isLeapYear(2023)); // Not divisible by 4
    }

    @Test
    public void testNextDate() {
        Date date = new Date(31, 12, 2023);
        Date next = date.nextDate();
        assertEquals(new Date(1, 1, 2024), next);

        Date leapYearDate = new Date(28, 2, 2020);
        Date nextLeapDay = leapYearDate.nextDate();
        assertEquals(new Date(29, 2, 2020), nextLeapDay);
    }

    @Test
    public void testPreviousDate() {
        Date date = new Date(1, 1, 2024);
        Date previous = date.previousDate();
        assertEquals(new Date(31, 12, 2023), previous);
    }

    @Test
    public void testCompareTo() {
        Date date1 = new Date(1, 1, 2023);
        Date date2 = new Date(31, 12, 2023);
        assertTrue(date1.compareTo(date2) < 0);
        assertEquals(0, date1.compareTo(new Date(1, 1, 2023)));
        assertTrue(date2.compareTo(date1) > 0);
    }
}
