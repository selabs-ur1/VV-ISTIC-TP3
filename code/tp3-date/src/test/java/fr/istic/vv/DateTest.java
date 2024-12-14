package fr.istic.vv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class DateTest {

    @Test
    void testIsValidDateLastDayOfYear() {
        assertTrue(Date.isValidDate(31, 12, 2024));
    }

    @Test
    void testIsValidDateFirtDayOfYear() {
        assertTrue(Date.isValidDate(1, 12, 2024));
    }

    @Test
    void isValidDay() {
        assertTrue(Date.isValidDate(15, 6, 2024));
    }

    @Test
    void isValidDate29FebruaryBisextile() {
        assertTrue(Date.isValidDate(29, 2, 2024));
    }

    @Test
    void isValidDateBadDayTooHigh() {
        assertFalse(Date.isValidDate(31, 11, 2024));
    }

    @Test
    void isValidDateBadDayTooSmall() {
        assertFalse(Date.isValidDate(0, 11, 2024));
    }

    @Test
    void isValidDateBadMonthTooHigh() {
        assertFalse(Date.isValidDate(15, 13, 2024));
    }

    @Test
    void isValidDateBadMonthTooSmall() {
        assertFalse(Date.isValidDate(15, 0, 2024));
    }

    @Test
    void isValidDate29FebruaryNotBisextile() {
        assertFalse(Date.isValidDate(29, 2, 2023));
    }

    @Test
    void isValidDateYear1() {
        assertTrue(Date.isValidDate(2, 2, 1));
    }

    @Test
    void isValidDateYear0() {
        assertFalse(Date.isValidDate(29, 2, 0));
    }

    @Test
    void isValidDateMonth1() {
        assertTrue(Date.isValidDate(29, 1, 23));
    }

    @Test
    void testIsLeapYearBisextile() {
        assertTrue(Date.isLeapYear(2024));
    }

    @Test
    void testIsLeapYearNotBisextile() {
        assertFalse(Date.isLeapYear(2023));
    }

    @Test
    void testIsLeapYearNotBisextileExcetion() {
        assertFalse(Date.isLeapYear(1900));
    }

    @Test
    void testIsLeapYearBisextileException() {
        assertTrue(Date.isLeapYear(2000));
    }

    @Test
    void testNextDateRegular() {
        Date date = new Date(15, 6, 2024);
        Date next = date.nextDate();
        assertEquals(new Date(16, 6, 2024), next);
    }

    @Test
    void testNextEndMonth() {
        Date date = new Date(31, 1, 2024);
        Date next = date.nextDate();
        assertEquals(new Date(1, 2, 2024), next);
    }

    @Test
    void testNextEndYear() {
        Date date = new Date(31, 12, 2024);
        Date next = date.nextDate();
        assertEquals(new Date(1, 1, 2025), next);
    }

    @Test
    void testPreviousDateRegular() {
        Date date = new Date(15, 6, 2024);
        Date next = date.previousDate();
        assertEquals(new Date(14, 6, 2024), next);
    }

    @Test
    void testPreviousDateStartMonth() {
        Date date = new Date(1, 2, 2024);
        Date next = date.previousDate();
        assertEquals(new Date(31, 1, 2024), next);
    }

    @Test
    void testPreviousDateStartYear() {
        Date date = new Date(1, 1, 2025);
        Date next = date.previousDate();
        assertEquals(new Date(31, 12, 2024), next);
    }

    @Test
    void testCompareToEquals() {
        Date date1 = new Date(1, 1, 2024);
        Date date2 = new Date(1, 1, 2024);
        assertEquals(0, date1.compareTo(date2));
    }

    @Test
    void testCompareToEarlier() {
        Date date1 = new Date(1, 1, 2024);
        Date date2 = new Date(2, 1, 2024);
        assertTrue(date1.compareTo(date2) < 0);
    }

    @Test
    void testCompareToLater() {
        Date date1 = new Date(2, 1, 2024);
        Date date2 = new Date(1, 1, 2024);
        assertTrue(date1.compareTo(date2) > 0);
    }

    @Test
    void testCompareToNull() {
        Date date = new Date(1, 1, 2024);
        Exception exception = assertThrows(NullPointerException.class, () -> date.compareTo(null));
        assertNotNull(exception);
    }
}
