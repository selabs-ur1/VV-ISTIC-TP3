package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testValidDate() {
        assertTrue(Date.isValidDate(1, 1, 2022));
        assertTrue(Date.isValidDate(31, 12, 2022));
        assertTrue(Date.isValidDate(29, 2, 2020)); // Leap year
        assertFalse(Date.isValidDate(29, 2, 2021)); // Not a leap year

        //test limit
        assertFalse(Date.isValidDate(0, 1, 2022));
        assertFalse(Date.isValidDate(1, 0, 2022));
        assertFalse(Date.isValidDate(1, 13, 2022));
        assertFalse(Date.isValidDate(1, 13, 0));

    }

    @Test
    void testInvalidDate() {
        //Day test
        assertFalse(Date.isValidDate(32, 1, 2022));
        assertFalse(Date.isValidDate(0, 1, 2022));
        assertFalse(Date.isValidDate(29, 2, 2021)); // Invalid day for February

        //Month test
        assertFalse(Date.isValidDate(1, 13, 2022));
        assertFalse(Date.isValidDate(1, 0, 2022));
        assertFalse(Date.isValidDate(1, -1, 2022));

        //Year test
        assertFalse(Date.isValidDate(1, -1, 0));
    }

    @Test
    void testLeapYear() {
        assertTrue(Date.isLeapYear(2020));
        assertFalse(Date.isLeapYear(2022));
        assertTrue(Date.isLeapYear(2000)); // Century leap year
        assertFalse(Date.isLeapYear(2100)); // Century non-leap year
    }

    @Test
    void testNextDate() {
        Date currentDate = new Date(31, 12, 2022);
        Date nextDate = currentDate.nextDate();
        assertEquals(1, nextDate.getDay());
        assertEquals(1, nextDate.getMonth());
        assertEquals(2023, nextDate.getYear());

        Date leapYearEnd = new Date(31, 12, 2020);
        Date leapYearNext = leapYearEnd.nextDate();
        assertEquals(1, leapYearNext.getDay());
        assertEquals(1, leapYearNext.getMonth());
        assertEquals(2021, leapYearNext.getYear());

        Date currentDate3 = new Date(30, 11, 2022);
        Date nextDate3 = currentDate3.nextDate();
        assertEquals(1, nextDate3.getDay());
        assertEquals(12, nextDate3.getMonth());
        assertEquals(2022, nextDate3.getYear());


    }

    @Test
    void testPreviousDate() {
        Date currentDate = new Date(1, 1, 2023);
        Date prevDate = currentDate.previousDate();
        assertEquals(31, prevDate.getDay());
        assertEquals(12, prevDate.getMonth());
        assertEquals(2022, prevDate.getYear());

        Date leapYearStart = new Date(1, 1, 2020);
        Date leapYearPrev = leapYearStart.previousDate();
        assertEquals(31, leapYearPrev.getDay());
        assertEquals(12, leapYearPrev.getMonth());
        assertEquals(2019, leapYearPrev.getYear());
    }

    @Test
    void testCompareTo() {
        Date date1 = new Date(1, 1, 2022);
        Date date2 = new Date(1, 1, 2023);
        Date date3 = new Date(1, 2, 2022);
        Date date4 = new Date(2, 1, 2022);


        assertTrue(date1.compareTo(date2) < 0);
        assertTrue(date2.compareTo(date1) > 0);
        assertTrue(date1.compareTo(date3) < 0);
        assertTrue(date3.compareTo(date1) > 0);
        assertTrue(date4.compareTo(date1) > 0);
        assertEquals(0, date1.compareTo(date1));

    }

    @Test
    void testInvalidDateConstructor() {
        // Test that the constructor throws an IllegalArgumentException for an invalid date
        assertThrows(IllegalArgumentException.class, () -> new Date(32, 1, 2022));
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 13, 2022));
        assertThrows(IllegalArgumentException.class, () -> new Date(29, 2, 2021));
    }
}
