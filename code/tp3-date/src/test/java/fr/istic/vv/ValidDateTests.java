package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.Date.isValidDate;
import static org.junit.jupiter.api.Assertions.*;

class ValidDateTests {

    //OUT OF RANGE DATE

    @Test
    public void testInvalidMonthPositive(){
        assertFalse(isValidDate(3,45,9898));
    }

    @Test
    public void testInvalidMonthNegative(){
        assertFalse(isValidDate(3,-12,9898));
    }

    @Test
    public void testInvalidDayPositive(){
        assertFalse(isValidDate(65,45,9898));
    }

    @Test
    public void testInvalidDayNegative(){
        assertFalse(isValidDate(-12,45,9898));
    }

    @Test
    public void testValidDayOfMonth(){
        assertTrue(isValidDate(31,07,9898));
    }

    @Test
    public void testInvalidDayOfMonth(){
        assertFalse(isValidDate(31,06,9898));
    }

    //LEAP YEAR TESTS

    @Test
    public void validLeapYear29FebPositive(){
        assertTrue(isValidDate(29,2,2024));
    }

    @Test
    public void validLeapYear29FebNegative(){
        assertTrue(isValidDate(29,2,-2024));
    }

    @Test
    public void invalidLeapYear29FebPositive(){
        assertFalse(isValidDate(29,2,2023));
    }

    @Test
    public void invalidLeapYear29FebNegative(){
        assertFalse(isValidDate(29,2,-2023));
    }

}