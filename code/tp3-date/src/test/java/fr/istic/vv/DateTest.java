package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void test(){
        assertTrue(Date.isValidDate(1, 1, 2022));
        assertTrue(Date.isValidDate(29, 2, 2024));
        assertFalse(Date.isValidDate(29, 2, 2023));
        assertTrue(Date.isValidDate(31, 1, 2022));
        assertFalse(Date.isValidDate(31, 4, 2022));

        assertTrue(Date.isLeapYear(2024));
        assertFalse(Date.isLeapYear(2023));
        assertFalse(Date.isLeapYear(2000));

        assertTrue(new Date(30, 1, 2022).nextDate().equals(new Date(31, 1, 2022)));
        assertTrue(new Date(31, 1, 2022).nextDate().equals(new Date(1, 2, 2022)));
        assertTrue(new Date(31, 12, 2022).nextDate().equals(new Date(1, 1, 2023)));

        assertTrue(new Date(1, 3, 2022).previousDate().equals(new Date(28, 2, 2022)));
        assertTrue(new Date(2, 3, 2022).previousDate().equals(new Date(1, 3, 2022)));
        assertTrue(new Date(1, 1, 2022).previousDate().equals(new Date(31, 12, 2021)));

        assertThrows(NullPointerException.class, () -> new Date(1, 1, 2022).compareTo(null));

        assertTrue(new Date(1, 2, 2022).compareTo(new Date(31, 1, 2022)) == 1);
        assertTrue(new Date(31, 11, 2022).compareTo(new Date(1, 1, 2023)) == -32);

        //add after PIT

        assertTrue(new Date(1, 1, 2022).compareTo(new Date(31, 12, 2021)) == 1);
        assertTrue(new Date(31, 3, 2022).compareTo(new Date(1, 4, 2022)) == -1);

    }
}