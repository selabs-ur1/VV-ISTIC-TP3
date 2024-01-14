package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testValidDate_ValidDate() {
        assertTrue(Date.isValidDate(1, 1, 2022));
    }

    @Test
    void testValidDate_InvalidDate() {
        assertFalse(Date.isValidDate(32, 1, 2022));
    }

    @Test
    void testIsLeapYear_LeapYear() {
        assertTrue(Date.isLeapYear(2020));
    }

    @Test
    void testIsLeapYear_NotLeapYear() {
        assertFalse(Date.isLeapYear(2021));
    }

    @Test
    void testNextDate_SimpleCase() {
        Date currentDate = new Date(31, 12, 2022);
        Date nextDate = currentDate.nextDate();
        assertEquals(new Date(1, 1, 2023), nextDate);
    }

    @Test
    void testPreviousDate_SimpleCase() {
        Date currentDate = new Date(1, 1, 2023);
        Date previousDate = currentDate.previousDate();
        assertEquals(new Date(31, 12, 2022), previousDate);
    }

    @Test
    void testCompareTo_EqualDates() {
        Date date1 = new Date(1, 1, 2022);
        Date date2 = new Date(1, 1, 2022);
        assertEquals(0, date1.compareTo(date2));
    }

    @Test
    void testCompareTo_LaterDate() {
        Date date1 = new Date(1, 1, 2022);
        Date date2 = new Date(1, 2, 2022);
        assertTrue(date2.compareTo(date1) > 0);
    }

    @Test
    void testCompareTo_EarlierDate() {
        Date date1 = new Date(1, 1, 2022);
        Date date2 = new Date(1, 2, 2022);
        assertTrue(date1.compareTo(date2) < 0);
    }
}
