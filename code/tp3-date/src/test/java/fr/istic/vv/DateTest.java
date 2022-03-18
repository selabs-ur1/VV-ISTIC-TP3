package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.Date.isLeapYear;
import static fr.istic.vv.Date.isValidDate;
import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    /*  All our dates

    Date DayIsZero = new Date(0,1,0);
    Date MonthIsZero = new Date(1,0,-1);
    Date MonthIsMoreThanTwelve = new Date(1,13,2022);
    Date ValidLeapYear = new Date(29,2,2024);
    Date NotValidLeapYear = new Date(29,2,2022);
    Date ValidThirtyDayMonth = new Date(30,4,2022);
    Date ValidThirtyOneDayMonth = new Date(31,12,2022);
    Date NotValidThirtyDayMonth = new Date(31,4,2022);
    Date NotValidThirtyOneDayMonth = new Date(32,12,2022);
    */
    @Test
    void testIsValidDateDayIsZero(){
        assertFalse(isValidDate(0,1,0));
    }
    @Test
    void testIsValidDateMonthIsZero(){
        assertFalse(isValidDate(1,0,-1));
    }
    @Test
    void testIsValidDateMonthIsMoreThanTwelve(){
        assertFalse(isValidDate(1,13,2022));
    }
    @Test
    void testIsValidDateValidLeapYear(){
        assertTrue(isValidDate(29,2,2024));
    }
    @Test
    void testIsValidDateNotValidLeapYear(){
        assertFalse(isValidDate(29,2,2022));
    }
    @Test
    void testIsValidDateValidThirtyDayMonth(){
        assertTrue(isValidDate(30,4,2022));
    }
    @Test
    void testIsValidDateValidThirtyOneDayMonth(){
        assertTrue(isValidDate(31,12,2022));
    }
    @Test
    void testIsValidDateNotValidThirtyDayMonth(){
        assertFalse(isValidDate(31,4,2022));
    }
    @Test
    void testIsValidDateNotValidThirtyOneDayMonth(){
        assertFalse(isValidDate(32,12,2022));
    }
    @Test
    void testIsLeapYearNotValid(){
        assertFalse(isLeapYear(2022));
    }
    @Test
    void testIsLeapYearValid(){
        assertTrue(isLeapYear(2024));
    }
    @Test
    void testIsLeapYearValid2(){
        assertTrue(isLeapYear(400));
    }
    @Test
    void testIsLeapYearNotValid2(){
        assertFalse(isLeapYear(100));
    }

    @Test
    void testNextDateNullMonthOverTwelve(){
        Date date = new Date(1,13,0);
        assertNull(date.nextDate());
    }
    @Test
    void testNextDateNullMonthUnderOne(){
        Date date = new Date(1,0,0);
        assertNull(date.nextDate());
    }
    @Test
    void testNextDateNullDayUnderOne(){
        Date date = new Date(0,1,0);
        assertNull(date.nextDate());
    }
    @Test
    void testNextDateNullDayOverMaxDaysOfMonth(){
        Date date = new Date(31,4,0);
        assertNull(date.nextDate());
    }
    @Test
    void testNextDateNullDayOverMaxDaysOfMonth2(){
        Date date = new Date(32,12,0);
        assertNull(date.nextDate());
    }
    @Test
    void testNextDateNextDay(){
        Date date = new Date(1,1,0);
        Date nextDay = new Date(2,1,0);
        assertEquals(nextDay, date.nextDate());
    }
    @Test
    void testNextDateNextDayNextMonth(){
        Date date = new Date(31,1,0);
        Date nextDay = new Date(1,2,0);
        assertEquals(nextDay, date.nextDate());
    }
    @Test
    void testNextDateNextDayNextMonthNextYear(){
        Date date = new Date(31,12,0);
        Date nextDay = new Date(1,1,1);
        assertEquals(nextDay, date.nextDate());
    }
    @Test
    void testCompareToNullPointerException(){
        Date date = new Date(31,12,0);
        Exception e = assertThrows(NullPointerException.class, () -> {
            date.compareTo(null);
        });
        assertEquals("input value is null", e.getMessage());
    }

    @Test
    void testPreviousDateNullMonthOverTwelve(){
        Date date = new Date(1,13,0);
        assertNull(date.nextDate());
    }
    @Test
    void testPreviousDateNullMonthUnderOne(){
        Date date = new Date(1,0,0);
        assertNull(date.nextDate());
    }
    @Test
    void testtestPreviousDateNullDayOverMaxDaysOfMonthDateNullDayUnderOne(){
        Date date = new Date(0,1,0);
        assertNull(date.nextDate());
    }
    @Test
    void testPreviousDateNullDayOverMaxDaysOfMonth(){
        Date date = new Date(31,4,0);
        assertNull(date.nextDate());
    }
    @Test
    void testPreviousDateNullDayOverMaxDaysOfMonth2(){
        Date date = new Date(32,12,0);
        assertNull(date.nextDate());
    }
    @Test
    void testPreviousDatePreviousDay(){
        Date date = new Date(2,1,0);
        Date previousDay = new Date(1,1,0);
        assertEquals(previousDay, date.previousDate());
    }
    @Test
    void testPreviousDatePreviousDayPreviousMonth(){
        Date date = new Date(1,2,0);
        Date previousDay = new Date(31,1,0);
        assertEquals(previousDay, date.previousDate());
    }
    @Test
    void testPreviousDatePreviousDayPreviousMonthPreviousYear(){
        Date date = new Date(1,1,0);
        Date previousDay = new Date(31,12,-1);
        assertEquals(previousDay, date.previousDate());
    }

    @Test
    void testCompareToIsZero(){
        Date date = new Date(1,1,1);
        Date date2 = new Date(2,1,1);
        assertTrue(date.compareTo(date) == 0);
    }
    @Test
    void testComparePositive(){
        Date date = new Date(1,1,1);
        Date date2 = new Date(2,1,1);
        assertTrue(date2.compareTo(date) > 0);
    }
    @Test
    void testCompareNegative(){
        Date date = new Date(1,1,1);
        Date date2 = new Date(2,1,1);
        assertTrue(date.compareTo(date2) < 0);
    }

    @Test
    void testGetDay(){
        Date date = new Date(1,1,1);
        assertEquals(1,date.getDay());
    }
    @Test
    void testGetMonth(){
        Date date = new Date(1,1,1);
        assertEquals(1,date.getMonth());
    }
    @Test
    void testGetYear(){
        Date date = new Date(1,1,1);
        assertEquals(1,date.getYear());
    }
    @Test
    void testEqualsFalse(){
        Date date = new Date(1,1,1);
        Date date2 = new Date(2,1,1);
        assertNotEquals(date,date2);
    }
    @Test
    void testEqualsFalse2(){
        Date date = new Date(1,1,1);
        String s = "String";
        assertNotEquals(date,s);
    }

}