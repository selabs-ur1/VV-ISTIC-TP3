package fr.istic.vv;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Date
 */
public class DateTest {

    @BeforeAll
    public static void setUp() {
    }

    @AfterAll
    public static void tearDown() {
    }

    // Test Date constructor NOK : bad days
    @Test
    public void testDateNOKUncorrectDays() {
        // Bad argument days
        assertThrows(IllegalArgumentException.class, () -> new Date(-1, 12, 2012));
        assertThrows(IllegalArgumentException.class, () -> new Date(32, 12, 2012));
    }

    // Test Date constructor NOK : bad Months
    @Test
    public void testDateNOKUncorrectMonths() {
        // Bad argument months
        assertThrows(IllegalArgumentException.class, () -> new Date(12, -1, 2012));
        assertThrows(IllegalArgumentException.class, () -> new Date(12, 13, 2012));
    }

    // Test Date constructor NOK : bad date for leap year
    @Test
    public void testDateNOKUncorrectDateForLeapYear() {
        // Bad argument day for february with leap year
        assertThrows(IllegalArgumentException.class, () -> new Date(29, 2, 2019));
    }

    // Test isValidDate OK : 3 exemples
    @Test
    public void testIsValidDateOk() {
        // Normal dates
        Date date1 = new Date(23, 10, 1999);
        Date date2 = new Date(15, 1, 1998);
        // Date with leap year
        Date date3 = new Date(29, 2, 2020);
        assertTrue(date1.isValidDate(date1.getDay(), date1.getMonth(), date1.getYear()));
        assertTrue(date2.isValidDate(date2.getDay(), date2.getMonth(), date2.getYear()));
        assertTrue(date3.isValidDate(date3.getDay(), date3.getMonth(), date3.getYear()));
    }

    // Test isValidDate NOK : bad months
    @Test
    public void testIsValidDateNOkBadMonths() {
        // bad months
        assertFalse(new Date(12, 1, 2012).isValidDate(12, -1, 2012));
        assertFalse(new Date(12, 11, 2012).isValidDate(12, 13, 2012));
    }

    // Test Date constructor NOK : bad days
    @Test
    public void testIsValidDateNOkBadDays() {
        // Bad argument days
        assertFalse(new Date(1, 10, 2012).isValidDate(-1, 10, 2012));
        assertFalse(new Date(1, 10, 2012).isValidDate(32, 10, 2012));
    }

    // Test isLeapYear OK
    @Test
    public void testIsLeapYearOK() {
        assertTrue(new Date(20, 12, 2020).isLeapYear(2020));
    }

    // Test isLeapYear NOK : example 2019
    @Test
    public void testIsLeapYearNOK2019() {
        assertFalse(new Date(20, 12, 2020).isLeapYear(2019));
    }

    // Test isLeapYear OK : example 400
    @Test
    public void testIsLeapYearOK400() {
        assertTrue(new Date(20, 12, 2020).isLeapYear(400));
    }

    // Test isLeapYear NOK : example 100
    @Test
    public void testIsLeapYearNOK100() {
        assertFalse(new Date(20, 12, 2020).isLeapYear(100));
    }

    // Test next date OK: example simple
    @Test
    public void testNextDateOKSimpleExample() {
        Date d1 = new Date(15, 2, 2020);
        d1.nextDate();
        assertEquals(16, d1.getDay());
        assertEquals(2, d1.getMonth());
        assertEquals(2020, d1.getYear());
    }

    // Test next date OK: example end of month
    @Test
    public void testNextDateOKEndOfMonth() {
        Date d1 = new Date(31, 1, 2020);
        d1.nextDate();
        assertEquals(1, d1.getDay());
        assertEquals(2, d1.getMonth());
        assertEquals(2020, d1.getYear());
    }

    // Test next date OK: example end of year
    @Test
    public void testNextDateOKEndOfYear() {
        Date d1 = new Date(31, 12, 2020);
        d1.nextDate();
        assertEquals(1, d1.getDay());
        assertEquals(1, d1.getMonth());
        assertEquals(2021, d1.getYear());
    }

    // Test next date OK: with leap year
    @Test
    public void testNextDateOKLeapYear() {
        Date d1 = new Date(28, 2, 2020);
        d1.nextDate();
        assertEquals(29, d1.getDay());
        assertEquals(2, d1.getMonth());
        assertEquals(2020, d1.getYear());
    }

    // Test next date OK: example not leap year
    @Test
    public void testNextDateOKNotLeapYeap() {
        Date d1 = new Date(28, 2, 2019);
        d1.nextDate();
        assertEquals(1, d1.getDay());
        assertEquals(3, d1.getMonth());
        assertEquals(2019, d1.getYear());
    }


    // Test previous date OK: example simple
    @Test
    public void testPreviousDateOKSimpleExample() {
        Date d1 = new Date(15, 2, 2020);
        d1.previousDate();
        assertEquals(14, d1.getDay());
        assertEquals(2, d1.getMonth());
        assertEquals(2020, d1.getYear());
    }

    // Test previous date OK: example beginning of month
    @Test
    public void testPreviousDateOKBeginningOfMonth() {
        Date d1 = new Date(1, 2, 2020);
        d1.previousDate();
        assertEquals(31, d1.getDay());
        assertEquals(1, d1.getMonth());
        assertEquals(2020, d1.getYear());
    }

    // Test previous date OK: example beginning of year
    @Test
    public void testPreviousDateOKBeginningOfYear() {
        Date d1 = new Date(1, 1, 2020);
        d1.previousDate();
        assertEquals(31, d1.getDay());
        assertEquals(12, d1.getMonth());
        assertEquals(2019, d1.getYear());
    }

    // Test previous date OK: with leap year
    @Test
    public void testPreviousDateOKLeapYear() {
        Date d1 = new Date(1, 3, 2020);
        d1.previousDate();
        assertEquals(29, d1.getDay());
        assertEquals(2, d1.getMonth());
        assertEquals(2020, d1.getYear());
    }

    // Test previous date OK: example not leap year
    @Test
    public void testPreviousDateOKNotLeapYeap() {
        Date d1 = new Date(1, 3, 2019);
        d1.previousDate();
        assertEquals(28, d1.getDay());
        assertEquals(2, d1.getMonth());
        assertEquals(2019, d1.getYear());
    }

    // Test compare dates OK: posterior date day
    @Test
    public void testCompareDateOKPosteriorDateDay() {
        Date d1 = new Date(2, 3, 2019);
        Date d2 = new Date(1, 3, 2019);
        assertEquals(1, d1.compareTo(d2));
    }

    // Test compare dates OK: posterior date month
    @Test
    public void testCompareDateOKPosteriorDateMonth() {
        Date d1 = new Date(1, 4, 2019);
        Date d2 = new Date(2, 3, 2019);
        assertEquals(1, d1.compareTo(d2));
    }

    // Test compare dates OK: posterior date year
    @Test
    public void testCompareDateOKPosteriorDateYear() {
        Date d1 = new Date(1, 4, 2022);
        Date d2 = new Date(2, 3, 2019);
        assertEquals(1, d1.compareTo(d2));
    }

    // Test compare dates OK: equals date
    @Test
    public void testCompareDateOKEqualsDate() {
        Date d1 = new Date(2, 3, 2019);
        Date d2 = new Date(2, 3, 2019);
        assertEquals(0, d1.compareTo(d2));
    }


}
