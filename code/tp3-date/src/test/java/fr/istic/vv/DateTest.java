package fr.istic.vv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static fr.istic.vv.Date.*;
import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    //### Test de ValidDate ###
    @Test
    public void testValidDate() {
        Assertions.assertTrue(isValidDate(1, 1, 2020));
    }

    @Test
    public void testValidDateNegative() {
        Assertions.assertFalse(isValidDate(10, 10, -50));
    }

    @Test
    public void testNotValidDateIncorrect() {
        Assertions.assertFalse(isValidDate(-1, 1, 2020));
    }

    @Test
    public void testNotValidDateIncorrectNegativeMonth() {
        Assertions.assertFalse(isValidDate(10, -8, 2020));
    }

    @Test
    public void testValidDateIncorrectDayMonth() {
        Assertions.assertFalse(isValidDate(31, 4, 2020));
    }

    @Test
    public void testValidDateIncorrectMonth() {
        Assertions.assertFalse(isValidDate(10, 800, 2020));
    }

    @Test
    public void testValidDateIncorrectDay() {
        Assertions.assertFalse(isValidDate(800, 800, 2020));
    }

    @Test
    public void testValidDate30Fev() {
        Assertions.assertFalse(isValidDate(30, 2, 2020));
    }

    @Test
    public void testValidDate31Jan() {
        Assertions.assertTrue(isValidDate(31, 1, 2020));
    }

    //### TEST DE LEAPYEAR ###
    @Test
    public void testLeapYear() {
        Assertions.assertTrue(isLeapYear(2020));
    }

    @Test
    public void testNotLeapYear() {
        Assertions.assertFalse(isLeapYear(2021));
    }

    @Test
    public void testLeapYearIncorrect() {
        Assertions.assertFalse(isLeapYear(-50));
    }

    @Test
    public void testLeapYearZero() {
        Assertions.assertTrue(isLeapYear(0));
    }

    // ### Test Equals ###
    @Test
    public void testEqualsEquals() {
        Date date1 = new Date(1, 1, 1);
        Date date2 = new Date(1, 1, 1);
        assertEquals(0, date1.compareTo(date2));
    }

    @Test
    public void testEqualsYearSupp() {
        Date date1 = new Date(10, 9, 2020);
        Date date2 = new Date(5, 2, 2021);
        assertEquals(-1, date1.compareTo(date2));
    }

    @Test
    public void testEqualsSameYearMonthSupp() {
        Date date1 = new Date(10, 2, 2020);
        Date date2 = new Date(5, 9, 2020);
        assertEquals(-1, date1.compareTo(date2));
    }

    @Test
    public void testEqualsSameYearSameMonthDaySupp() {
        Date date1 = new Date(5, 9, 2020);
        Date date2 = new Date(10, 9, 2020);
        assertEquals(-1, date1.compareTo(date2));
    }

    @Test
    public void testEqualsYearInf() {
        Date date1 = new Date(10, 9, 2025);
        Date date2 = new Date(5, 2, 2021);
        assertEquals(1, date1.compareTo(date2));
    }

    @Test
    public void testEqualsSameYearMonthInf() {
        Date date1 = new Date(10, 10, 2020);
        Date date2 = new Date(5, 9, 2020);
        assertEquals(1, date1.compareTo(date2));
    }

    @Test
    public void testEqualsSameYearSameMonthDayInf() {
        Date date1 = new Date(15, 9, 2020);
        Date date2 = new Date(10, 9, 2020);
        assertEquals(1, date1.compareTo(date2));
    }

    @Test
    public void testEqualNull() {
        Date date1 = new Date(15, 9, 2020);
        Date date2 = null;
        assertThrows(NullPointerException.class, () -> {
            date1.compareTo(date2);
        });

    }

    //### Test NextDay ###
    @Test
    public void testNextDayCasBase() {
        Date dateBase = new Date(5, 5, 2005);
        Date dateExcepted = new Date(6, 5, 2005);
        Assertions.assertEquals(0, dateExcepted.compareTo(dateBase.nextDate()));
    }

    @Test
    public void testNextDayEndMonthPair() {
        Date dateBase = new Date(30, 4, 2005);
        Date dateExcepted = new Date(1, 5, 2005);
        Assertions.assertEquals(0, dateExcepted.compareTo(dateBase.nextDate()));
    }

    @Test
    public void testNextDayEndMonthImpair() {
        Date dateBase = new Date(31, 5, 2005);
        Date dateExcepted = new Date(1, 6, 2005);
        Assertions.assertEquals(0, dateExcepted.compareTo(dateBase.nextDate()));
    }

    @Test
    public void testNextDayEndYear() {
        Date dateBase = new Date(31, 12, 2005);
        Date dateExcepted = new Date(1, 1, 2006);
        Assertions.assertEquals(0, dateExcepted.compareTo(dateBase.nextDate()));
    }

    //### Test PreviousDay ###
    @Test
    public void testPrevDayCasBase() {
        Date dateBase = new Date(5, 5, 2005);
        Date dateExcepted = new Date(4, 5, 2005);
        Assertions.assertEquals(0, dateExcepted.compareTo(dateBase.previousDate()));
    }

    @Test
    public void testPrevDayEndMonthPair() {
        Date dateBase = new Date(1, 4, 2005);
        Date dateExcepted = new Date(31, 3, 2005);
        Assertions.assertEquals(0, dateExcepted.compareTo(dateBase.previousDate()));
    }

    @Test
    public void testPrevDayEndMonthImpair() {
        Date dateBase = new Date(1, 5, 2005);
        Date dateExcepted = new Date(30, 4, 2005);
        Assertions.assertEquals(0, dateExcepted.compareTo(dateBase.previousDate()));
    }

    @Test
    public void testPrevDayEndYear() {
        Date dateBase = new Date(1, 1, 2005);
        Date dateExcepted = new Date(31, 12, 2004);
        Assertions.assertEquals(0, dateExcepted.compareTo(dateBase.previousDate()));
    }

    @Test
    public void testPrevDayFevLeapYear() {
        Date dateBase = new Date(1, 3, 2020);
        Date dateExcepted = new Date(29, 2, 2020);
        Assertions.assertEquals(0, dateExcepted.compareTo(dateBase.previousDate()));
    }

    @Test
    public void testPrevDayFevNotLeapYear() {
        Date dateBase = new Date(1, 3, 2005);
        Date dateExcepted = new Date(28, 2, 2005);
        Assertions.assertEquals(0, dateExcepted.compareTo(dateBase.previousDate()));
    }
}