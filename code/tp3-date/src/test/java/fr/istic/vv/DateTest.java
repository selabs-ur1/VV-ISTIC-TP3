package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testValidDateConstructor() {
        Date date = new Date(28, 2, 2024);
        assertNotNull(date);
    }

    @Test
    void testInvalidDateConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Date(32, 1, 2020));
        assertThrows(IllegalArgumentException.class, () -> new Date(30, 2, 2020));
    }

    @Test
    void testIsValidDate() {
        assertTrue(Date.isValidDate(28, 2, 2024));
        assertFalse(Date.isValidDate(29, 2, 2023));
        assertTrue(Date.isValidDate(31, 12, 2020));
        assertFalse(Date.isValidDate(32, 12, 2020));
        assertFalse(Date.isValidDate(0, 5, 2020));
        assertFalse(Date.isValidDate(15, 13, 2020));
    }

    //Added for base coverage
    @Test
    void testValidDate() {
        assertTrue(Date.isValidDate(28, 2, 2024));
        assertTrue(Date.isValidDate(31, 12, 2020));
        assertTrue(Date.isValidDate(29, 2, 2024));
    }

    @Test
    void testInvalidYear() {
        assertFalse(Date.isValidDate(28, 2, -1));
    }

    @Test
    void testInvalidMonth() {
        assertFalse(Date.isValidDate(28, 13, 2024));
        assertFalse(Date.isValidDate(28, 0, 2024));
    }

    @Test
    void testInvalidDay() {
        assertFalse(Date.isValidDate(0, 5, 2024));
        assertFalse(Date.isValidDate(32, 1, 2024));
        assertFalse(Date.isValidDate(30, 2, 2023));
    }

    @Test
    void testDayExceedsMaxDaysInMonth() {
        assertFalse(Date.isValidDate(32, 1, 2024));
        assertFalse(Date.isValidDate(30, 2, 2024));
    }

    @Test
    void testValidDateWithMaxDays() {
        assertTrue(Date.isValidDate(31, 1, 2024));
        assertTrue(Date.isValidDate(29, 2, 2024));
        assertTrue(Date.isValidDate(30, 4, 2024));
    }

    @Test
    void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2024));
        assertFalse(Date.isLeapYear(2023));
        assertTrue(Date.isLeapYear(2000));
        assertFalse(Date.isLeapYear(1900));
    }

    //Added for base coverage
    @Test
    void testIsLeapYearDivisibleBy4() {
        assertTrue(Date.isLeapYear(2024)); // Divisible by 4 and not divisible by 100
    }

    @Test
    void testIsLeapYearNotDivisibleBy4() {
        assertFalse(Date.isLeapYear(2023)); // Not divisible by 4
    }

    @Test
    void testIsLeapYearDivisibleBy4AndNotBy100() {
        assertTrue(Date.isLeapYear(2020)); // Divisible by 4 but not by 100
    }

    @Test
    void testIsLeapYearDivisibleBy100ButNotBy400() {
        assertFalse(Date.isLeapYear(1900)); // Divisible by 100 but not by 400
    }

    @Test
    void testIsLeapYearDivisibleBy100And400() {
        assertTrue(Date.isLeapYear(2000)); // Divisible by both 100 and 400
    }

    @Test
    void testMaxDays() {
        assertEquals(31, Date.maxDays(1, 2020));
        assertEquals(28, Date.maxDays(2, 2023));
        assertEquals(29, Date.maxDays(2, 2024));
        assertEquals(30, Date.maxDays(4, 2021));
        assertEquals(31, Date.maxDays(12, 2022));
        assertEquals(0, Date.maxDays(13, 2020));
    }

    @Test
    void testNextDate() {
        Date date = new Date(28, 2, 2024);
        Date nextDate = date.nextDate();
        assertEquals(29, nextDate.getDay());
        assertEquals(2, nextDate.getMonth());
        assertEquals(2024, nextDate.getYear());
    }

    @Test
    void testNextDate2() {
        Date date2 = new Date(31, 12, 2023);
        Date nextDate2 = date2.nextDate();
        assertEquals(1, nextDate2.getDay());
        assertEquals(1, nextDate2.getMonth());
        assertEquals(2024, nextDate2.getYear());
    }

    @Test
    void testNextDateMonthOverflow() {
        Date date = new Date(31, 12, 2024);
        Date nextDate = date.nextDate();

        assertEquals(1, nextDate.getDay());
        assertEquals(1, nextDate.getMonth());
        assertEquals(2025, nextDate.getYear());
    }

    @Test
    void testPreviousDate() {
        Date date = new Date(1, 3, 2024);
        Date previousDate = date.previousDate();
        assertEquals(29, previousDate.getDay());
        assertEquals(2, previousDate.getMonth());
        assertEquals(2024, previousDate.getYear());
    }

    @Test
    void testPreviousDate2() {
        Date date2 = new Date(1, 1, 2024);
        Date previousDate2 = date2.previousDate();
        assertEquals(31, previousDate2.getDay());
        assertEquals(12, previousDate2.getMonth());
        assertEquals(2023, previousDate2.getYear());
    }

    @Test
    void testPreviousDateSameMonth() {
        Date date = new Date(5, 5, 2024);
        Date previousDate = date.previousDate();

        assertEquals(4, previousDate.getDay());
        assertEquals(5, previousDate.getMonth());
        assertEquals(2024, previousDate.getYear());
    }

    @Test
    void testPreviousDateEndOfMonth() {
        Date date = new Date(1, 5, 2024);
        Date previousDate = date.previousDate();

        assertEquals(30, previousDate.getDay());
        assertEquals(4, previousDate.getMonth());
        assertEquals(2024, previousDate.getYear());
    }

    @Test
    void testPreviousDateJanuary() {
        Date date = new Date(1, 1, 2024);
        Date previousDate = date.previousDate();

        assertEquals(31, previousDate.getDay());
        assertEquals(12, previousDate.getMonth());
        assertEquals(2023, previousDate.getYear());
    }

    @Test
    void testPreviousDateEdge() {
        Date date = new Date(1, 3, 2024);
        Date previousDate = date.previousDate();

        assertEquals(29, previousDate.getDay());
        assertEquals(2, previousDate.getMonth());
        assertEquals(2024, previousDate.getYear());
    }

    @Test
    void testCompareToDifferentDay() {
        Date date1 = new Date(1, 1, 2020);
        Date date2 = new Date(2, 1, 2020);
        Date date3 = new Date(1, 1, 2020);

        assertTrue(date1.compareTo(date2) < 0);
        assertTrue(date2.compareTo(date1) > 0);
        assertEquals(0, date1.compareTo(date3));

        assertThrows(NullPointerException.class, () -> date1.compareTo(null));
    }

    @Test
    void testCompareToDifferentMonth() {
        Date date1 = new Date(1, 1, 2020);
        Date date2 = new Date(1, 5, 2020);
        Date date3 = new Date(1, 1, 2020);

        assertTrue(date1.compareTo(date2) < 0);
        assertTrue(date2.compareTo(date1) > 0);
        assertEquals(0, date1.compareTo(date3));

        assertThrows(NullPointerException.class, () -> date1.compareTo(null));
    }

    @Test
    void testCompareToDifferentYear() {
        Date date1 = new Date(1, 1, 2020);
        Date date2 = new Date(1, 1, 2021);
        Date date3 = new Date(1, 1, 2020);

        assertTrue(date1.compareTo(date2) < 0);
        assertTrue(date2.compareTo(date1) > 0);
        assertEquals(0, date1.compareTo(date3));

        assertThrows(NullPointerException.class, () -> date1.compareTo(null));
    }
}
