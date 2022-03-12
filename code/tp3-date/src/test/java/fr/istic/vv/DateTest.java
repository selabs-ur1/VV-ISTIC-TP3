package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateTest {

    @Test
    void testConstructorThrow(){
        assertThrows(IllegalArgumentException.class, ()->new Date(0, 0, 0));
    }

    @Test
    void isValidDateNominal1() {
        assertTrue(Date.isValidDate(1,1,1970));
    }

    @Test
    void isValidDateNominal2() {
        assertTrue(Date.isValidDate(31,12,1970));
    }

    @Test
    void isValidDateBissextile2902() {
        assertTrue(Date.isValidDate(29,2,2020));
    }

    @Test
    void isValidDateNotBissextile2902() {
        assertFalse(Date.isValidDate(29,2,2022));
    }

    @Test
    void isValidDateNominal3() {
        assertTrue(Date.isValidDate(28,2,2022));
    }

    @Test
    void isValidDateNominal4() {
        assertTrue(Date.isValidDate(30,6,2022));
    }

    @Test
    void isValidDate3106() {
        assertFalse(Date.isValidDate(31,6,2022));
    }

    @Test
    void isValidDateNominal5() {
        assertTrue(Date.isValidDate(31,7,2022));
    }

    @Test
    void isValidDateday32() {
        assertFalse(Date.isValidDate(32,7,2022));
    }

    @Test
    void isValidDateNominal6() {
        assertTrue(Date.isValidDate(3,9,2022));
    }

    @Test
    void isValidDatemonth13() {
        assertFalse(Date.isValidDate(3,13,2022));
    }

    @Test
    void isValidDateDay0() {
        assertFalse(Date.isValidDate(0,1,2022));
    }

    @Test
    void isValidDateMonth0() {
        assertFalse(Date.isValidDate(3,0,2022));
    }

    @Test
    void isValidDateYearNegative() {
        assertFalse(Date.isValidDate(3,13,-2022));
    }

    @Test
    void isValidDateDayNegative() {
        assertFalse(Date.isValidDate(-3,1,2022));
    }

    @Test
    void isValidDateMonthNegative() {
        assertFalse(Date.isValidDate(3,-1,2022));
    }

    @Test
    void isLeapYear2020() {
        assertTrue(Date.isLeapYear(2020));
    }

    @Test
    void isLeapYear2022() {
        assertFalse(Date.isLeapYear(2022));
    }

    @Test
    void isLeapYear2000() {
        assertFalse(Date.isLeapYear(2000));
    }

    @Test
    void isLeapYear2100() {
        assertFalse(Date.isLeapYear(2100));
    }

    @Test
    void nextDateSameMonth31days() {
        Date currentDate = new Date(1,1,2022);
        Date expected = new Date(2,1,2022);
        assertEquals(expected, currentDate.nextDate());
    }
    @Test
    void nextDateSameMonth31daysLastDay() {
        Date currentDate = new Date(30,1,2022);
        Date expected = new Date(31,1,2022);
        assertEquals(expected, currentDate.nextDate());
    }

    @Test
    void nextDateSameMonth30days() {
        Date currentDate = new Date(1,4,2022);
        Date expected = new Date(2,4,2022);
        assertEquals(expected, currentDate.nextDate());
    }

    @Test
    void nextDateNominalNextMonth31days() {
        Date currentDate = new Date(31,1,2022);
        Date expected = new Date(1,2,2022);
        assertEquals(expected, currentDate.nextDate());
    }

    @Test
    void nextDateNominalNextMonth30days() {
        Date currentDate = new Date(30,4,2022);
        Date expected = new Date(1,5,2022);
        assertEquals(expected, currentDate.nextDate());
    }

    @Test
    void nextDateNominalNextYear() {
        Date currentDate = new Date(31,12,2022);
        Date expected = new Date(1,1,2023);
        assertEquals(expected, currentDate.nextDate());
    }

    @Test
    void nextDateNominalNextMonthBissextile() {
        Date currentDate = new Date(28,2,2020);
        Date expected = new Date(29,2,2020);
        assertEquals(expected, currentDate.nextDate());
    }

    @Test
    void nextDateNominalNextMonthNotBissextile() {
        Date currentDate = new Date(28,2,2022);
        Date expected = new Date(1,3,2022);
        assertEquals(expected, currentDate.nextDate());
    }

    @Test
    void previousDateNominal() {
        Date currentDate = new Date(31,7,2022);
        Date expected = new Date(30,7,2022);
        assertEquals(expected, currentDate.previousDate());
    }

    @Test
    void previousDatePreviousMonth30days() {
        Date currentDate = new Date(1,7,2022);
        Date expected = new Date(30, 6, 2022);
        assertEquals(expected, currentDate.previousDate());
    }

    @Test
    void previousDatePreviousMonthAlso31days() {
        Date currentDate = new Date(1,8,2022);
        Date expected = new Date(31,7,2022);
        assertEquals(expected, currentDate.previousDate());
    }

    @Test
    void previousDatePreviousMonth31days() {
        Date currentDate = new Date(1,9, 2022);
        Date expected = new Date(31, 8, 2022);
        assertEquals(expected, currentDate.previousDate());
    }

    @Test
    void previousDatePreviousYear() {
        Date currentDate = new Date(1,1,2022);
        Date expected = new Date(31,12,2021);
        assertEquals(expected, currentDate.previousDate());
    }

    @Test
    void compareToBeforeDay() {
        Date d1 = new Date(3,9,2022);
        Date d2 = new Date(4,9,2022);
        assertTrue(d1.compareTo(d2) < 0);
    }
    
    @Test
    void compareToBeforeMonth() {
        Date d1 = new Date(3,9,2022);
        Date d2 = new Date(3,10,2022);
        assertTrue(d1.compareTo(d2) < 0);
    }
    
    @Test
    void compareToBeforeYear() {
        Date d1 = new Date(3,9,2022);
        Date d2 = new Date(3,9,2023);
        assertTrue(d1.compareTo(d2) < 0);
    }
    
    
    @Test
    void compareToEqual() {
        Date d1 = new Date(3,9,2022);
        Date d2 = new Date(3,9,2022);
        assertEquals(0, d1.compareTo(d2));
    }

    @Test
    void compareToAfterDay() {
        Date d1 = new Date(4,9,2022);
        Date d2 = new Date(3,9,2022);
        assertTrue(d1.compareTo(d2) > 0);
    }

    @Test
    void compareToAfterMonth() {
        Date d1 = new Date(3,10,2022);
        Date d2 = new Date(3,9,2022);
        assertTrue(d1.compareTo(d2) > 0);
    }

    @Test
    void compareToAfterYear() {
        Date d1 = new Date(3,9,2023);
        Date d2 = new Date(3,9,2022);
        assertTrue(d1.compareTo(d2) > 0);
    }
}
