package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testConstructorThrow(){
        assertThrows(IllegalArgumentException.class, ()->new Date(0, 0, 0));
    }

    @Test
    void testIsValidDateNominal1() {
        assertTrue(Date.isValidDate(1,1,1970));
    }

    @Test
    void testIsValidDateNominal2() {
        assertTrue(Date.isValidDate(31,12,1970));
    }

    @Test
    void testIsValidDateBissextile2902() {
        assertTrue(Date.isValidDate(29,2,2020));
    }

    @Test
    void testIsValidDateNotBissextile2902() {
        assertFalse(Date.isValidDate(29,2,2022));
    }

    @Test
    void testIsValidDateNominal3() {
        assertTrue(Date.isValidDate(28,2,2022));
    }

    @Test
    void testIsValidDateNominal4() {
        assertTrue(Date.isValidDate(30,6,2022));
    }

    @Test
    void testIsValidDate3106() {
        assertFalse(Date.isValidDate(31,6,2022));
    }

    @Test
    void testIsValidDateNominal5() {
        assertTrue(Date.isValidDate(31,7,2022));
    }

    @Test
    void testIsValidDateday32() {
        assertFalse(Date.isValidDate(32,7,2022));
    }

    @Test
    void testIsValidDateNominal6() {
        assertTrue(Date.isValidDate(3,9,2022));
    }

    @Test
    void testIsValidDatemonth13() {
        assertFalse(Date.isValidDate(3,13,2022));
    }

    @Test
    void testIsValidDateDay0() {
        assertFalse(Date.isValidDate(0,1,2022));
    }

    @Test
    void testIsValidDateMonth0() {
        assertFalse(Date.isValidDate(3,0,2022));
    }

    @Test
    void testIsValidDateYearNegative() {
        assertFalse(Date.isValidDate(3,13,-2022));
    }

    @Test
    void testIsValidDateDayNegative() {
        assertFalse(Date.isValidDate(-3,1,2022));
    }

    @Test
    void testIsValidDateMonthNegative() {
        assertFalse(Date.isValidDate(3,-1,2022));
    }

    @Test
    void testIsLeapYear2020() {
        assertTrue(Date.isLeapYear(2020));
    }

    @Test
    void testIsLeapYear2022() {
        assertFalse(Date.isLeapYear(2022));
    }

    @Test
    void testIsLeapYear2000() {
        assertFalse(Date.isLeapYear(2000));
    }

    @Test
    void testIsLeapYear2100() {
        assertFalse(Date.isLeapYear(2100));
    }

    @Test
    void testNextDateSameMonth31days() {
        Date currentDate = new Date(1,1,2022);
        Date expected = new Date(2,1,2022);
        assertEquals(expected, currentDate.nextDate());
    }
    @Test
    void testNextDateSameMonth31daysLastDay() {
        Date currentDate = new Date(30,1,2022);
        Date expected = new Date(31,1,2022);
        assertEquals(expected, currentDate.nextDate());
    }

    @Test
    void testNextDateSameMonth30days() {
        Date currentDate = new Date(1,4,2022);
        Date expected = new Date(2,4,2022);
        assertEquals(expected, currentDate.nextDate());
    }

    @Test
    void testNextDateNominalNextMonth31days() {
        Date currentDate = new Date(31,1,2022);
        Date expected = new Date(1,2,2022);
        assertEquals(expected, currentDate.nextDate());
    }

    @Test
    void testNextDateNominalNextMonth30days() {
        Date currentDate = new Date(30,4,2022);
        Date expected = new Date(1,5,2022);
        assertEquals(expected, currentDate.nextDate());
    }

    @Test
    void testNextDateNominalNextYear() {
        Date currentDate = new Date(31,12,2022);
        Date expected = new Date(1,1,2023);
        assertEquals(expected, currentDate.nextDate());
    }

    @Test
    void testNextDateNominalNextMonthBissextile() {
        Date currentDate = new Date(28,2,2020);
        Date expected = new Date(29,2,2020);
        assertEquals(expected, currentDate.nextDate());
    }

    @Test
    void testNextDateNominalNextMonthNotBissextile() {
        Date currentDate = new Date(28,2,2022);
        Date expected = new Date(1,3,2022);
        assertEquals(expected, currentDate.nextDate());
    }

    @Test
    void testPreviousDateNominal() {
        Date currentDate = new Date(31,7,2022);
        Date expected = new Date(30,7,2022);
        assertEquals(expected, currentDate.previousDate());
    }

    @Test
    void testPreviousDatePreviousMonth30days() {
        Date currentDate = new Date(1,7,2022);
        Date expected = new Date(30, 6, 2022);
        assertEquals(expected, currentDate.previousDate());
    }

    @Test
    void testPreviousDatePreviousMonthAlso31days() {
        Date currentDate = new Date(1,8,2022);
        Date expected = new Date(31,7,2022);
        assertEquals(expected, currentDate.previousDate());
    }

    @Test
    void testPreviousDatePreviousMonth31days() {
        Date currentDate = new Date(1,9, 2022);
        Date expected = new Date(31, 8, 2022);
        assertEquals(expected, currentDate.previousDate());
    }

    @Test
    void testPreviousDatePreviousYear() {
        Date currentDate = new Date(1,1,2022);
        Date expected = new Date(31,12,2021);
        assertEquals(expected, currentDate.previousDate());
    }

    @Test
    void testPreviousDate1stMarchBissextile() {
        Date currentDate = new Date(1,3,2024);
        Date expected = new Date(29,2,2024);
        assertEquals(expected, currentDate.previousDate());
    }
    @Test
    void testPreviousDate1stMarchNonBissextile() {
        Date currentDate = new Date(1,3,2022);
        Date expected = new Date(28,2,2022);
        assertEquals(expected, currentDate.previousDate());
    }

    @Test
    void testCompareToBeforeDay() {
        Date d1 = new Date(3,9,2022);
        Date d2 = new Date(4,9,2022);
        assertTrue(d1.compareTo(d2) < 0);
    }
    
    @Test
    void testCompareToBeforeMonth() {
        Date d1 = new Date(3,9,2022);
        Date d2 = new Date(3,10,2022);
        assertTrue(d1.compareTo(d2) < 0);
    }
    
    @Test
    void testCompareToBeforeYear() {
        Date d1 = new Date(3,9,2022);
        Date d2 = new Date(3,9,2023);
        assertTrue(d1.compareTo(d2) < 0);
    }
    
    
    @Test
    void testCompareToEqual() {
        Date d1 = new Date(3,9,2022);
        Date d2 = new Date(3,9,2022);
        assertEquals(0, d1.compareTo(d2));
    }

    @Test
    void testCompareToAfterDay() {
        Date d1 = new Date(4,9,2022);
        Date d2 = new Date(3,9,2022);
        assertTrue(d1.compareTo(d2) > 0);
    }

    @Test
    void testCompareToAfterMonth() {
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
