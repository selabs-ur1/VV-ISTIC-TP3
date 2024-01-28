package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    @Test
    public void testConstructorBadDay() {
        assertThrows(IllegalArgumentException.class, () -> new Date(0, 1, 2022));
    }

    @Test
    public void testConstructorBadMonth() {
        assertThrows(IllegalArgumentException.class, () -> new Date(5, 0, 2022));
    }

    @Test
    public void testWithValidDate() {
        assertTrue(Date.isValidDate(2, 2, 2022));
    }

    @Test
    public void testWithInvalidDateBadMonth() {
        assertFalse(Date.isValidDate(2, 26, 2022));
    }

    @Test
    public void testInvalidDateBadDay() {
        assertFalse(Date.isValidDate(31, 2, 2022));
    }

    @Test
    public void testInvalidDateBadMonth() {
        assertFalse(Date.isValidDate(25, 0, 2022));
    }

    @Test
    public void testLeapYear() {
        assertTrue(Date.isLeapYear(2012));
    }

    @Test
    public void testLeapYearBadYear() {
        assertFalse(Date.isLeapYear(2022));
    }

    @Test
    public void testCompareTo() {
        Date date = new Date(5, 1, 2022);
        assertEquals(0, date.compareTo(new Date(5, 1, 2022)));
    }

    @Test
    public void testCompareTo1() {
        Date date = new Date(6, 1, 2022);
        assertEquals(1, date.compareTo(new Date(5, 1, 2022)));
    }

    @Test
    public void testCompareToM1() {
        Date date = new Date(6, 1, 2022);
        assertEquals(-1, date.compareTo(new Date(8, 1, 2022)));
    }
    @Test
    public void testCompareToNull() {
        Date date = new Date(6, 1, 2022);
        assertThrows(NullPointerException.class, () -> date.compareTo(null));
    }

    @Test
    public void testCompareToM11() {
        Date date = new Date(6, 1, 2022);
        assertEquals(-1, date.compareTo(new Date(6, 2, 2022)));
    }

    @Test
    public void testPreviousDate() {
        Date date1 = new Date(6, 1, 2022);
        Date date2 = new Date(5, 1, 2022);
        assertEquals(0, date1.previousDate().compareTo(date2));
    }

    @Test
    public void testPreviousDate2() {
        Date date1 = new Date(5, 1, 2022);
        Date date2 = new Date(5, 1, 2022);
        assertEquals(-1, date1.previousDate().compareTo(date2));
    }

    @Test
    public void testPreviousDate3() {
        Date date1 = new Date(7, 1, 2022);
        Date date2 = new Date(5, 1, 2022);
        assertEquals(1, date1.previousDate().compareTo(date2));
    }

    @Test
    public void testPreviousDateLeapYear() {
        Date date1 = new Date(1, 3, 2024);
        Date date2 = new Date(29, 2, 2024);
        assertEquals(0, date1.previousDate().compareTo(date2));
    }

    @Test
    public void testPreviousDateNonLeapYear() {
        Date date1 = new Date(1, 3, 2023);
        Date date2 = new Date(28, 2, 2023);
        assertEquals(0, date1.previousDate().compareTo(date2));
    }

    @Test
    public void testPreviousDateChangeYear() {
        Date date1 = new Date(1, 1, 2024);
        Date date2 = new Date(31, 12, 2023);
        assertEquals(0, date1.previousDate().compareTo(date2));
    }

    @Test
    public void testPreviousDateChangeMonth31() {
        Date date1 = new Date(1, 4, 2024);
        Date date2 = new Date(31, 3, 2024);
        assertEquals(0, date1.previousDate().compareTo(date2));
    }

    @Test
    public void testPreviousDateChangeMonth30() {
        Date date1 = new Date(1, 5, 2024);
        Date date2 = new Date(30, 4, 2024);
        assertEquals(0, date1.previousDate().compareTo(date2));
    }

    @Test
    public void testNextDate() {
        Date date1 = new Date(5, 1, 2022);
        Date date2 = new Date(6, 1, 2022);
        assertEquals(0, date1.nextDate().compareTo(date2));
    }

    @Test
    public void testNextDate2() {
        Date date1 = new Date(5, 1, 2022);
        Date date2 = new Date(7, 1, 2022);
        assertEquals(-1, date1.nextDate().compareTo(date2));
    }

    @Test
    public void testNextDate3() {
        Date date1 = new Date(5, 1, 2022);
        Date date2 = new Date(5, 1, 2022);
        assertEquals(1, date1.nextDate().compareTo(date2));
    }

    @Test
    public void testNextDateLeapYear() {
        Date date1 = new Date(28, 2, 2024);
        Date date2 = new Date(29, 2, 2024);
        assertEquals(0, date1.nextDate().compareTo(date2));
    }

    @Test
    public void testNextDateNonLeapYear() {
        Date date1 = new Date(28, 2, 2023);
        Date date2 = new Date(1, 3, 2023);
        assertEquals(0, date1.nextDate().compareTo(date2));
    }

    @Test
    public void testNextDateChangeYear() {
        Date date1 = new Date(31, 12, 2023);
        Date date2 = new Date(1, 1, 2024);
        assertEquals(0, date1.nextDate().compareTo(date2));
    }

    @Test
    public void testNextDateMonth31() {
        Date date1 = new Date(30, 3, 2024);
        Date date2 = new Date(31, 3, 2024);
        assertEquals(0, date1.nextDate().compareTo(date2));
    }

    @Test
    public void testNextDateChangeMonth30() {
        Date date1 = new Date(30, 4, 2024);
        Date date2 = new Date(1, 5, 2024);
        assertEquals(0, date1.nextDate().compareTo(date2));
    }

}
