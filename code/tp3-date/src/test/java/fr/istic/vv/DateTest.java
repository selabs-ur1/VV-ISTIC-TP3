package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class DateTest {
    
    @Test
    public void testToStringTest() throws IOException {
        Date date = new Date(15, 3, 2011);
        String expected = "Date [day=15, month=3, year=2011]";

        assertEquals(expected, date.toString());
    }

    @Test 
    public void testEqualsNull() throws IOException {
        Date date = new Date(15, 7, 1987);
        Object obj = null;

        assertFalse(date.equals(obj));
    }

    @Test 
    public void testEqualsType() throws IOException {
        Date date = new Date(15, 7, 1987);
        int obj = 1;

        assertFalse(date.equals(obj));
    }

    @Test 
    public void testEqualsDifferentDay() throws IOException {
        Date date = new Date(8, 8, 1908);
        Date obj = new Date(5, 8, 1908);

        assertFalse(date.equals(obj));
    }

    @Test 
    public void testEqualsDifferentMonth() throws IOException {
        Date date = new Date(19, 8, 2006);
        Date obj = new Date(19, 1, 2006);

        assertFalse(date.equals(obj));
    }

    @Test 
    public void testEqualsDifferentYear() throws IOException {
        Date date = new Date(15, 3, 2006);
        Date obj = new Date(15, 3, 2011);

        assertFalse(date.equals(obj));
    }

    @Test 
    public void testEqualsSameDate() throws IOException {
        Date date = new Date(8, 8, 1908);
        Date obj = new Date(8, 8, 1908);

        assertTrue(date.equals(obj));
    }

    @Test
    public void testInvalidDate() {
        assertThrows(IOException.class, () -> { new Date(-4, 2, 5); });
    }

    @Test
    public void testValidDate() {
        assertDoesNotThrow( () -> { new Date(1, 2, 2013); });
    }

    @Test
    public void testIsValidDateDayZero() {
        int day = 0;
        int month = 1;
        int year = 2024;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test
    public void testIsValidDateNegativeMonth() {
        int day = 1;
        int month = -2;
        int year = 2024;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test
    public void testIsValidDateMonthZero() {
        int day = 2;
        int month = 0;
        int year = 2024;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test
    public void testIsValidDateMonthTooHigh() {
        int day = 2;
        int month = 13;
        int year = 2024;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooSmallInMonth1() {
        int day = -4;
        int month = 1;
        int year = 2024;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooHighInMonth1() {
        int day = 32;
        int month = 1;
        int year = 2024;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayInMonth1() {
        int day = 31;
        int month = 1;
        int year = 2024;

        assertTrue(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooSmallInMonth2() {
        int day = -4;
        int month = 2;
        int year = 2024;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooHighInMonth2() {
        int day = 32;
        int month = 2;
        int year = 2024;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayInMonth2() {
        int day = 17;
        int month = 2;
        int year = 2024;

        assertTrue(Date.isValidDate(day, month, year));
    }

    @Test
    public void testIsValidDateDay29InMonth2AndLeapYear() {
        int day = 29;
        int month = 2;
        int year = 2024;

        assertTrue(Date.isValidDate(day, month, year));
    }

    @Test
    public void testIsValidDateDay29InMonth2AndNotLeapYear() {
        int day = 29;
        int month = 2;
        int year = 2023;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooSmallInMonth3() {
        int day = -2;
        int month = 3;
        int year = 6;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooHighInMonth3() {
        int day = 32;
        int month = 3;
        int year = 12;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayInMonth3() {
        int day = 19;
        int month = 3;
        int year = -456;

        assertTrue(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooSmallInMonth4() {
        int day = -1;
        int month = 4;
        int year = 978;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooHighInMonth4() {
        int day = 33;
        int month = 4;
        int year = 131;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayInMonth4() {
        int day = 5;
        int month = 4;
        int year = -4568;

        assertTrue(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooSmallInMonth5() {
        int day = -1;
        int month = 5;
        int year = 456;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooHighInMonth5() {
        int day = 45;
        int month = 5;
        int year = 123;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayInMonth5() {
        int day = 8;
        int month = 5;
        int year = 587;

        assertTrue(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooSmallInMonth6() {
        int day = -21;
        int month = 6;
        int year = 7856;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooHighInMonth6() {
        int day = 31;
        int month = 6;
        int year = 789;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayInMonth6() {
        int day = 7;
        int month = 6;
        int year = 25024;

        assertTrue(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooSmallInMonth7() {
        int day = -7;
        int month = 7;
        int year = 6788;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooHighInMonth7() {
        int day = 90;
        int month = 7;
        int year = 7897;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayInMonth7() {
        int day = 19;
        int month = 7;
        int year = -1648648;

        assertTrue(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooSmallInMonth8() {
        int day = -456;
        int month = 8;
        int year = 65468;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooHighInMonth8() {
        int day = 32;
        int month = 8;
        int year = 456;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayInMonth8() {
        int day = 12;
        int month = 8;
        int year = -64864;

        assertTrue(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooSmallInMonth9() {
        int day = -5;
        int month = 9;
        int year = 27897824;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooHighInMonth9() {
        int day = 54;
        int month = 9;
        int year = 7897;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayInMonth9() {
        int day = 16;
        int month = 9;
        int year = 0;

        assertTrue(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooSmallInMonth10() {
        int day = 0;
        int month = 10;
        int year = 456;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooHighInMonth10() {
        int day = 32;
        int month = 10;
        int year = 4568;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayInMonth10() {
        int day = 31;
        int month = 10;
        int year = 78;

        assertTrue(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooSmallInMonth11() {
        int day = -2;
        int month = 11;
        int year = 654;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooHighInMonth11() {
        int day = 32;
        int month = 11;
        int year = 0;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayInMonth11() {
        int day = 8;
        int month = 11;
        int year = -42;

        assertTrue(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooSmallInMonth12() {
        int day = -9;
        int month = 12;
        int year = 455;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayTooHighInMonth12() {
        int day = 45;
        int month = 12;
        int year = 437;

        assertFalse(Date.isValidDate(day, month, year));
    }

    @Test 
    public void testIsValidDateDayInMonth12() {
        int day = 1;
        int month = 12;
        int year = 1058;

        assertTrue(Date.isValidDate(day, month, year));
    }

    @Test
    public void testIsLeapYearDayDivisibleBy4(){
        int year = 16;

        assertTrue(Date.isLeapYear(year));
    }

    @Test
    public void testIsLeapYearDayDivisibleBy100AndBy400(){
        int year = 1600;

        assertTrue(Date.isLeapYear(year));
    }

    @Test
    public void testIsLeapYearDayDivisibleBy100AndNotBy400(){
        int year = 1100;

        assertFalse(Date.isLeapYear(year));
    }

    @Test
    public void testNextDateInMonth() throws IOException {
        Date date = new Date(7, 11, 2024);
        Date expected = new Date(8, 11, 2024);

        assertEquals(expected, date.nextDate());
    }

    @Test
    public void testNextDateNewMonth() throws IOException {
        Date date = new Date(31, 8, 2024);
        Date expected = new Date(1, 9, 2024);

        assertEquals(expected, date.nextDate());
    }

    @Test
    public void testNextDateNewYear() throws IOException {
        Date date = new Date(31, 12, 2023);
        Date expected = new Date(1, 1, 2024);

        assertEquals(expected, date.nextDate());
    }

    @Test
    public void testPreviousDateInMonth() throws IOException {
        Date date = new Date(13, 2, 2023);
        Date expected = new Date(12, 2, 2023);

        assertEquals(expected, date.previousDate());
    }

    @Test
    public void testPreviousDateNewMonth31() throws IOException {
        Date date = new Date(1, 9, 2024);
        Date expected = new Date(31, 8, 2024);

        assertEquals(expected, date.previousDate());
    }

    @Test
    public void testPreviousDateNewMonth30() throws IOException {
        Date date = new Date(1, 7, 2024);
        Date expected = new Date(30, 6, 2024);

        assertEquals(expected, date.previousDate());
    }

    @Test
    public void testPreviousDateNewMonth2AndLeapYear() throws IOException {
        Date date = new Date(1, 3, 2024);
        Date expected = new Date(29, 2, 2024);

        assertEquals(expected, date.previousDate());
    }

    @Test
    public void testPreviousDateNewMonth2AndNotLeapYear() throws IOException {
        Date date = new Date(1, 3, 2007);
        Date expected = new Date(28, 2, 2007);

        assertEquals(expected, date.previousDate());
    }

    @Test
    public void testPreviousDateNewYear() throws IOException {
        Date date = new Date(1, 1, 2022);
        Date expected = new Date(31, 12, 2021);

        assertEquals(expected, date.previousDate());
    }

    @Test
    public void testCompareToEquals() throws IOException {
        Date date = new Date(15, 7, 2015);
        Date other = new Date(15, 7, 2015);

        int expected = 0;

        assertEquals(expected, date.compareTo(other));
    }

    @Test
    public void testCompareToDayBefore() throws IOException {
        Date date = new Date(12, 2, 2026);
        Date other = new Date(15, 2, 2026);

        int expected = -1;

        assertEquals(expected, date.compareTo(other));
    }

    @Test
    public void testCompareToDayAfter() throws IOException {
        Date date = new Date(31, 8, 2026);
        Date other = new Date(15, 8, 2026);

        int expected = 1;

        assertEquals(expected, date.compareTo(other));
    }

    @Test
    public void testCompareToMonthBefore() throws IOException {
        Date date = new Date(30, 3, 2026);
        Date other = new Date(15, 5, 2026);

        int expected = -1;

        assertEquals(expected, date.compareTo(other));
    }

    @Test
    public void testCompareToMonthAfter() throws IOException {
        Date date = new Date(1, 12, 2026);
        Date other = new Date(15, 8, 2026);

        int expected = 1;

        assertEquals(expected, date.compareTo(other));
    }

    @Test
    public void testCompareToYearBefore() throws IOException {
        Date date = new Date(11, 4, 2023);
        Date other = new Date(15, 5, 2026);

        int expected = -1;

        assertEquals(expected, date.compareTo(other));
    }

    @Test
    public void testCompareToYearAfter() throws IOException {
        Date date = new Date(31, 10, 2026);
        Date other = new Date(15, 8, 2021);

        int expected = 1;

        assertEquals(expected, date.compareTo(other));
    }
        


}