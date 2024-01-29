package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testConstructorBadDay() {
        assertThrows(IllegalArgumentException.class, () -> new Date(30, 2, 2000));
    }

    @Test
    void testConstructorBadMonth() {
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 13, 2000));
    }

    @Test
    void testConstructorBadYear() {
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 1, 0));
    }

    @Test
    void testNextDate() {
        Date date = new Date(1, 1, 2000);
        Date nextDate = date.nextDate();
        assertEquals(1, nextDate.compareTo(date));
    }

    @Test
    void testPreviousDate() {
        Date date = new Date(1, 1, 2000);
        Date previousDate = date.previousDate();
        assertEquals(-1, previousDate.compareTo(date));
    }

    @Test
    void testCompareNextDate() {
        Date date = new Date(1, 1, 2000);
        Date nextDate = new Date(2, 1, 2000);
        assertTrue(nextDate.compareTo(date) > 0);
    }

    @Test
    void testComparePreviousDate() {
        Date date = new Date(1, 1, 2000);
        Date previousDate = new Date(31, 12, 1999);
        assertTrue(previousDate.compareTo(date) < 0);
    }

    @Test
    void testCompareSameDate() {
        Date date = new Date(1, 1, 2000);
        Date sameDate = new Date(1, 1, 2000);
        assertEquals(0, sameDate.compareTo(date));
    }

    @Test
    void testCompareToNullValue() {
        Date date = new Date(1, 1, 2000);
        assertThrows(NullPointerException.class, () -> date.compareTo(null));
    }

    @Test
    void testIsValidDate() {
        assertTrue(Date.isValidDate(1, 1, 2000));
    }

    @Test
    void testIsNotValidDateDay() {
        assertFalse(Date.isValidDate(0, 1, 2000));
    }

    @Test
    void testIsNotValidDateMonth() {
        assertFalse(Date.isValidDate(1, 0, 2000));
    }

    @Test
    void testIsNotValidDateYear() {
        assertFalse(Date.isValidDate(1, 1, 0));
    }

    @Test
    void isLeapYear() {
        assertTrue(Date.isLeapYear(2000));
    }

    @Test
    void isNotLeapYear() {
        assertFalse(Date.isLeapYear(2001));
    }
}