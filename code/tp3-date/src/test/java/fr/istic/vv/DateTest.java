package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testDateInvalidConstructor(){
        assertThrows(IllegalArgumentException.class, () -> new Date(0, 1, 2000));
    }

    @Test
    void testisValidDate1(){
        assertTrue(Date.isValidDate(1, 1, -1));
    }

    @Test
    void testisValidDate2(){
        assertFalse(Date.isValidDate(-1, -1, 0));
    }

    @Test
    void testisValidDate3(){
        assertFalse(Date.isValidDate(0, 4, 2020));
    }

    @Test
    void testisValidDate4(){
        assertFalse(Date.isValidDate(-2, 0, 2019));
    }

    @Test
    void testisValidDate5(){
        assertFalse(Date.isValidDate(29, 2, 2019));
    }

    @Test
    void testisValidDate6(){
        assertFalse(Date.isValidDate(0, 13, 2018));
    }

    @Test
    void testisLeapYear1(){
        assertFalse(Date.isLeapYear(13));
    }

    @Test
    void testisLeapYear2(){
        assertTrue(Date.isLeapYear(0));
    }

    @Test
    void testisLeapYear3(){
        assertFalse(Date.isLeapYear(1900));
    }

    @Test
    void testisLeapYear4(){
        assertTrue(Date.isLeapYear(4));
    }

    @Test
    void testisLeapYear5(){
        assertTrue(Date.isLeapYear(2000));
    }

    @Test
    void testNextDate1(){
        Date d = new Date(1, 6, 2000);
        assertEquals(d.nextDate(), new Date(2, 6, 2000));
    }

    @Test
    void testNextDate2(){
        Date d = new Date(1, 12, 2001);
        assertEquals(d.nextDate(), new Date(2, 12, 2001));
    }

    @Test
    void testNextDate3(){
        Date d = new Date(1, 1, 2000);
        assertEquals(d.nextDate(), new Date(2, 1, 2000));
    }

    @Test
    void testNextDate4(){
        Date d = new Date(31, 1, 2001);
        assertEquals(d.nextDate(), new Date(1, 2, 2001));
    }

    @Test
    void testNextDate5(){
        Date d = new Date(30, 4, 2000);
        assertEquals(d.nextDate(), new Date(1, 5, 2000));
    }

    @Test
    void testNextDate6(){
        Date d = new Date(31, 12, 2001);
        assertEquals(d.nextDate(), new Date(1, 1, 2002));
    }

    @Test
    void testNextDate7(){
        Date d = new Date(28, 2, 2000);
        assertEquals(d.nextDate(), new Date(29, 2, 2000));
    }

    @Test
    void testNextDate8(){
        Date d = new Date(28, 2, 2001);
        assertEquals(d.nextDate(), new Date(1, 3, 2001));
    }

    @Test
    void testNextDate9(){
        Date d = new Date(1, 3, 2000);
        assertEquals(d.nextDate(), new Date(2, 3, 2000));
    }

    @Test
    void testNextDate10(){
        Date d = new Date(1, 3, 2001);
        assertEquals(d.nextDate(), new Date(2, 3, 2001));
    }

    @Test
    void testNextDate11(){
        Date d = new Date(20, 2, 2000);
        assertEquals(d.nextDate(), new Date(21, 2, 2000));
    }

    @Test
    void testNextDate12(){
        Date d = new Date(20, 12, 2001);
        assertEquals(d.nextDate(), new Date(21, 12, 2001));
    }

    @Test
    void testPreviousDate1(){
        Date d = new Date(1, 6, 2000);
        assertEquals(d.previousDate(), new Date(31, 5, 2000));
    }

    @Test
    void testPreviousDate2(){
        Date d = new Date(1, 12, 2001);
        assertEquals(d.previousDate(), new Date(30, 11, 2001));
    }

    @Test
    void testPreviousDate3(){
        Date d = new Date(1, 1, 2000);
        assertEquals(d.previousDate(), new Date(31, 12, 1999));
    }

    @Test
    void testPreviousDate4(){
        Date d = new Date(31, 1, 2001);
        assertEquals(d.previousDate(), new Date(30, 1, 2001));
    }

    @Test
    void testPreviousDate5(){
        Date d = new Date(30, 4, 2000);
        assertEquals(d.previousDate(), new Date(29, 4, 2000));
    }

    @Test
    void testPreviousDate6(){
        Date d = new Date(31, 12, 2001);
        assertEquals(d.previousDate(), new Date(30, 12, 2001));
    }

    @Test
    void testPreviousDate7(){
        Date d = new Date(28, 2, 2000);
        assertEquals(d.previousDate(), new Date(27, 2, 2000));
    }

    @Test
    void testPreviousDate8(){
        Date d = new Date(28, 2, 2001);
        assertEquals(d.previousDate(), new Date(27, 2, 2001));
    }

    @Test
    void testPreviousDate9(){
        Date d = new Date(1, 3, 2000);
        assertEquals(d.previousDate(), new Date(29, 2, 2000));
    }

    @Test
    void testPreviousDate10(){
        Date d = new Date(1, 3, 2001);
        assertEquals(d.previousDate(), new Date(28, 2, 2001));
    }

    @Test
    void testPreviousDate11(){
        Date d = new Date(20, 2, 2000);
        assertEquals(d.previousDate(), new Date(19, 2, 2000));
    }

    @Test
    void testPreviousDate12(){
        Date d = new Date(20, 12, 2001);
        assertEquals(d.previousDate(), new Date(19, 12, 2001));
    }

    @Test
    void testCompareDate1(){
        Date d = new Date(20, 12, 2001);
        Date d1 = new Date(19, 12, 2001);
        assertTrue(d.compareTo(d1) > 0);
    }

    @Test
    void testCompareDate2(){
        Date d = new Date(20, 12, 2001);
        Date d1 = new Date(20, 11, 2001);
        assertTrue(d.compareTo(d1) > 0);
    }

    @Test
    void testCompareDate3(){
        Date d = new Date(20, 12, 2001);
        Date d1 = new Date(20, 12, 2000);
        assertTrue(d.compareTo(d1) > 0);
    }

    @Test
    void testCompareDate4(){
        Date d = new Date(20, 12, 2001);
        Date d1 = new Date(19, 12, 2001);
        assertTrue(d1.compareTo(d) < 0);
    }

    @Test
    void testCompareDate5(){
        Date d = new Date(20, 12, 2001);
        Date d1 = new Date(20, 11, 2001);
        assertTrue(d1.compareTo(d) < 0);
    }

    @Test
    void testCompareDate6(){
        Date d = new Date(20, 12, 2001);
        Date d1 = new Date(20, 12, 2000);
        assertTrue(d1.compareTo(d) < 0);
    }

    @Test
    void testCompareDate7(){
        Date d = new Date(20, 12, 2001);
        assertTrue(d.compareTo(d) == 0);
    }
}
