package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.Date.isLeapYear;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsLeapYearTests {

    @Test
    public void positiveLeapYear(){
        assertTrue(isLeapYear(2024));
    }

    @Test
    public void negativeLeapYear(){
        assertTrue(isLeapYear(-2024));
    }

    @Test
    public void positiveNotLeapYear(){
        assertFalse(isLeapYear(2014));
    }

    @Test
    public void negativeNotLeapYear(){
        assertFalse(isLeapYear(-2014));
    }

    @Test
    public void yearZero(){
        assertTrue(isLeapYear(0));
    }

}