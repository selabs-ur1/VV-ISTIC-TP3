package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    @Test
    void testConstructor_validDate() {
        Date date = new Date(1, 1, 2022);
        assertNotNull(date);
    }

    @Test
    void testConstructor_invalidDate() {
        assertThrows(IllegalArgumentException.class, () -> new Date(32, 1, 2022));
        assertThrows(IllegalArgumentException.class, () -> new Date(31, 13, 2022));
        assertThrows(IllegalArgumentException.class, () -> new Date(29, 2, 2021));
    }

    @Test
    void testIsValidDate_validDate() {
        assertTrue(Date.isValidDate(1, 1, 2022));
        assertTrue(Date.isValidDate(29, 2, 2020)); // Leap year
    }

    @Test
    void testIsValidDate_invalidDate() {
        assertFalse(Date.isValidDate(32, 1, 2022));
        assertFalse(Date.isValidDate(31, 13, 2022));
        assertFalse(Date.isValidDate(29, 2, 2021)); // Not a leap year
    }

    @Test
    void testIsLeapYear_leapYear() {
        assertTrue(Date.isLeapYear(2020));
        assertTrue(Date.isLeapYear(2000));
    }

    @Test
    void testIsLeapYear_nonLeapYear() {
        assertFalse(Date.isLeapYear(2022));
        assertFalse(Date.isLeapYear(1900));
    }

    @Test
    void testNextDate_withinMonth() {
        Date date = new Date(30, 6, 2022);
        Date nextDate = date.nextDate();
        assertEquals(new Date(1, 7, 2022), nextDate);
    }

    @Test
    void testNextDate_crossingMonth() {
        Date date1 = new Date(31, 12, 2022);
        Date date2 = new Date(30, 6, 2022);
        assertEquals(new Date(1, 1, 2023), date1.nextDate());
        assertEquals(new Date(1, 7, 2022), date2.nextDate());
    }

    @Test
    void testPreviousDate_withinMonth() {
        Date date = new Date(2, 6, 2022);
        Date prevDate = date.previousDate();
        assertEquals(new Date(1, 6, 2022), prevDate);
    }

    @Test
    void testPreviousDate_crossingMonth() {
        Date date1 = new Date(1, 1, 2022);
        Date date2 = new Date(2, 6, 2022);
        assertEquals(new Date(31, 12, 2021), date1.previousDate());
        assertEquals(new Date(1, 6, 2022), date2.previousDate());
    }

    @Test
    void testCompareTo_sameDate() {
        Date date1 = new Date(1, 1, 2022);
        Date date2 = new Date(1, 1, 2022);
        assertEquals(0, date1.compareTo(date2));
    }

    @Test
    void testCompareTo_posteriorDate() {
        Date date1 = new Date(2, 1, 2022);
        Date date2 = new Date(1, 1, 2022);
        assertTrue(date1.compareTo(date2) > 0);
    }

    @Test
    void testCompareTo_anteriorDate() {
        Date date1 = new Date(1, 1, 2022);
        Date date2 = new Date(2, 1, 2022);
        assertTrue(date1.compareTo(date2) < 0);
    }
}
