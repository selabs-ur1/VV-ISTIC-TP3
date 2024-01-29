package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testValidDateConstructor() {
        assertDoesNotThrow(() -> new Date(1, 1, 2022));
        assertDoesNotThrow(() -> new Date(29, 2, 2024));
        assertDoesNotThrow(() -> new Date(31, 12, 2023));
    }

    @Test
    void testInvalidDateConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Date(0, 1, 2022));
        assertThrows(IllegalArgumentException.class, () -> new Date(32, 12, 2023));
        assertThrows(IllegalArgumentException.class, () -> new Date(29, 2, 2023)); // Not a leap year
    }

    @Test
    void testIsValidDate() {
        assertTrue(Date.isValidDate(1, 1, 2022));
        assertTrue(Date.isValidDate(29, 2, 2024));
        assertTrue(Date.isValidDate(31, 12, 2023));

        assertFalse(Date.isValidDate(0, 1, 2022));
        assertFalse(Date.isValidDate(32, 12, 2023));
        assertFalse(Date.isValidDate(29, 2, 2023)); // Not a leap year
    }

    @Test
    void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2020));
        assertTrue(Date.isLeapYear(2000));
        assertFalse(Date.isLeapYear(2022));
        assertFalse(Date.isLeapYear(2100));
    }

    @Test
    void testNextDate() {
        Date date = new Date(1, 1, 2022);
        assertEquals(new Date(2, 1, 2022), date.nextDate());

        date = new Date(31, 12, 2023);
        assertEquals(new Date(1, 1, 2024), date.nextDate());

        date = new Date(28, 2, 2024);
        assertEquals(new Date(01, 03, 2024), date.nextDate());

        date = new Date(29, 2, 2020); // Leap year
        assertEquals(new Date(1, 3, 2020), date.nextDate());
    }

    @Test
    void testPreviousDate() {
        Date date = new Date(2, 1, 2022);
        assertEquals(new Date(1, 1, 2022), date.previousDate());

        date = new Date(1, 1, 2024);
        assertEquals(new Date(31, 12, 2023), date.previousDate());

        date = new Date(29, 2, 2024);
        assertEquals(new Date(28, 2, 2024), date.previousDate());

        date = new Date(1, 3, 2020); // Leap year
        assertEquals(new Date(29, 2, 2020), date.previousDate());
    }

    @Test
    void testCompareTo() {
        Date date1 = new Date(1, 1, 2022);
        Date date2 = new Date(1, 1, 2022);
        Date date3 = new Date(2, 1, 2022);

        assertEquals(0, date1.compareTo(date2));
        assertTrue(date1.compareTo(date3) < 0);
        assertTrue(date3.compareTo(date1) > 0);

        assertThrows(NullPointerException.class, () -> date1.compareTo(null));
    }

    // Aditionnal test 2

    @Test
    void testConstructorEdgeCases() {
        assertDoesNotThrow(() -> new Date(1, 1, 1));
        assertDoesNotThrow(() -> new Date(31, 12, 9999));
    }

    @Test
    void testStaticMethodsEdgeCases() {
        assertTrue(Date.isValidDate(29, 2, 2020)); // Leap year
        assertFalse(Date.isLeapYear(1900)); // Not a leap year
    }

    @Test
    void testNextDateEdgeCase() {
        Date date = new Date(31, 12, 9999);
        assertEquals(new Date(1, 1, 10000), date.nextDate());
    }

    @Test
    void testPreviousDateEdgeCase() {
        Date date = new Date(1, 1, 1);
        assertEquals(new Date(31, 12, 9999), date.previousDate());
    }

    

    
}