package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testIsValidDateValid() {
        assertTrue(Date.isValidDate(10, 1, 2008));
    }

    @Test
    void testIsValidDateInvalidMonth() {
        assertFalse(Date.isValidDate(3, 13, 2024));
    }

    @Test
    void testIsValidDateInvalidDay() {
        assertFalse(Date.isValidDate(32, 1, 2022));
    }

    @Test
    void testIsLeapYearLeapYear() {
        assertTrue(Date.isLeapYear(2024));
    }

    @Test
    void testIsLeapYearNotLeapYear() {
        assertFalse(Date.isLeapYear(2023));
    }

    @Test
    void testNextDate() {
        Date date = new Date(24, 1, 2024);
        Date nextDate = date.nextDate();
        assertEquals(new Date(25, 1, 2024).toString(), nextDate.toString());
    }

    @Test
    void testPreviousDate() {
        Date date = new Date(24, 1, 2024);
        Date previousDate = date.previousDate();
        assertEquals(new Date(23, 1, 2024).toString(), previousDate.toString());
    }

    @Test
    void testNextDateEndOfMonth() {
        Date date = new Date(31, 1, 2022);
        Date nextDate = date.nextDate();
        assertEquals(new Date(1, 2, 2022).toString(), nextDate.toString());
    }

    @Test
    void testNextDateEndOfYear() {
        Date date = new Date(31, 12, 2022);
        Date nextDate = date.nextDate();
        assertEquals(new Date(1, 1, 2023).toString(), nextDate.toString());
    }

    @Test
    void testNextDateLeapYearEndOfMonth() {
        Date date = new Date(28, 2, 2024);
        Date nextDate = date.nextDate();
        assertEquals(new Date(29, 2, 2024).toString(), nextDate.toString());
    }

   /* @Test
    void testNextDateInvalidNextDay() {
        Date date = new Date(30, 4, 2024);
        assertThrows(IllegalStateException.class, date::nextDate);
    }*/

    @Test
    void testPreviousDateBeginningOfMonth() {
        Date date = new Date(1, 1, 2022);
        Date previousDate = date.previousDate();
        assertEquals(new Date(31, 12, 2021).toString(), previousDate.toString());
    }

    @Test
    void testPreviousDateBeginningOfYear() {
        Date date = new Date(1, 1, 2022);
        Date previousDate = date.previousDate();
        assertEquals(new Date(31, 12, 2021).toString(), previousDate.toString());
    }

    @Test
    void testPreviousDateInvalidPreviousDay() {
        Date date = new Date(1, 1, 0);
        assertThrows(IllegalStateException.class, date::previousDate);
    }

    @Test
    void testCompareToEqualDates() {
        Date date1 = new Date(24, 1, 2024);
        Date date2 = new Date(24, 1, 2024);
        assertEquals(0, date1.compareTo(date2));
    }

    @Test
    void testCompareToLaterDate() {
        Date date1 = new Date(24, 1, 2024);
        Date date2 = new Date(25, 1, 2024);
        assertTrue(date2.compareTo(date1) > 0);
    }

    @Test
    void testCompareToNull() {
        Date date1 = new Date(24, 1, 2024);
        assertThrows(NullPointerException.class, () -> date1.compareTo(null));
    }
}
