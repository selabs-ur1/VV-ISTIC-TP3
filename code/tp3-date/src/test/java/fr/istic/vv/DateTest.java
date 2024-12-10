package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testConstructorValidDate() {
        assertDoesNotThrow(() -> new Date(1, 1, 2020));
        assertDoesNotThrow(() -> new Date(29, 2, 2020)); // leap year
        assertDoesNotThrow(() -> new Date(31, 12, 2020));
    }

    @Test
    void testConstructorInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> new Date(32, 1, 2020));
        assertThrows(IllegalArgumentException.class, () -> new Date(29, 2, 2019)); // not a leap year
        assertThrows(IllegalArgumentException.class, () -> new Date(31, 4, 2020));
    }

    @Test
    void testIsValidDate() {
        assertTrue(Date.isValidDate(1, 1, 2020));
        assertTrue(Date.isValidDate(29, 2, 2020)); // leap year
        assertTrue(Date.isValidDate(31, 12, 2020));

        assertFalse(Date.isValidDate(32, 1, 2020));
        assertFalse(Date.isValidDate(29, 2, 2019)); // not a leap year
        assertFalse(Date.isValidDate(31, 4, 2020));
    }

    @Test
    void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2020));
        assertTrue(Date.isLeapYear(2000));

        assertFalse(Date.isLeapYear(2019));
        assertFalse(Date.isLeapYear(1900));
    }

    @Test
    void testNextDate() {
        assertEquals(new Date(2, 1, 2020), new Date(1, 1, 2020).nextDate());
        assertEquals(new Date(1, 2, 2020), new Date(31, 1, 2020).nextDate());
        assertEquals(new Date(1, 1, 2021), new Date(31, 12, 2020).nextDate());
        assertEquals(new Date(1, 3, 2020), new Date(29, 2, 2020).nextDate());
        assertEquals(new Date(1, 3, 2019), new Date(28, 2, 2019).nextDate());
    }

    @Test
    void testPreviousDate() {
        assertEquals(new Date(31, 12, 2019), new Date(1, 1, 2020).previousDate());
        assertEquals(new Date(1, 1, 2020), new Date(2, 1, 2020).previousDate());
        assertEquals(new Date(31, 1, 2020), new Date(1, 2, 2020).previousDate());
        assertEquals(new Date(31, 12, 2020), new Date(1, 1, 2021).previousDate());
        assertEquals(new Date(28, 2, 2020), new Date(29, 2, 2020).previousDate());
    }

    @Test
    void testCompareTo() {
        assertEquals(0, new Date(1, 1, 2020).compareTo(new Date(1, 1, 2020)));
        assertTrue(new Date(1, 1, 2020).compareTo(new Date(2, 1, 2020)) < 0);
        assertTrue(new Date(2, 1, 2020).compareTo(new Date(1, 1, 2020)) > 0);
    }

    @Test
    void testCompareToNull() {
        assertThrows(NullPointerException.class, () -> new Date(1, 1, 2020).compareTo(null));
    }
}