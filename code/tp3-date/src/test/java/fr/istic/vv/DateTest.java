package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    @Test
    public void testIsValidDate1(){
        assertFalse(Date.isValidDate(0, 0, 2020));
    }
    @Test
    public void testIsValidDate2(){
        assertTrue(Date.isValidDate(1, 1, 1));
    }
    @Test
    public void testIsValidDate3(){
        assertTrue(Date.isValidDate(21, 12, 2021));
    }
    @Test
    public void testIsValidDate4(){
        assertTrue(Date.isValidDate(29, 2, 2024));
    }
    @Test
    public void testIsValidDate5(){
        assertFalse(Date.isValidDate(29, 2, 2021));
    }
    @Test
    public void testIsValidDate6(){
        assertFalse(Date.isValidDate(31, 4, 2021));
    }
    @Test
    public void testIsValidDate7(){
        assertFalse(Date.isValidDate(32, 12, 2021));
    }
    @Test
    public void testIsValideDate8(){
        assertFalse(Date.isValidDate(31,13,2021));
    }
    @Test
    public void testIsValideDate9(){
        assertFalse(Date.isValidDate(-1,-1,-1));
    }
    @Test
    public void testIsValideDate10(){
        assertFalse(Date.isValidDate(25,13,2021));
    }
    @Test
    public void testIsValideDate11(){
        assertFalse(Date.isValidDate(31,4,2021));
    }
    @Test
    public void testIsValideDate12(){
        assertFalse(Date.isValidDate(1,0,1));
    }
    @Test
    public void testIsLeapYear1(){
        assertTrue(Date.isLeapYear(0));
    }
    @Test
    public void testIsLeapYear2(){
        assertFalse(Date.isLeapYear(1));
    }
    @Test
    public void testIsLeapYear3(){
        assertTrue(Date.isLeapYear(4));
    }
    @Test
    public void testIsLeapYear4(){
        assertFalse(Date.isLeapYear(2021));
    }
    @Test
    public void testIsLeapYear5(){
        assertTrue(Date.isLeapYear(2024));
    }
    @Test
    public void testIsLeapYear6(){
        assertFalse(Date.isLeapYear(1700));
    }
    @Test
    public void testIsLeapYear7(){
        assertTrue(Date.isLeapYear(2000));
    }
    @Test
    public void testNextDate1(){
        Date date = new Date(1,1,1);
        Date nextDate = date.nextDate();
        Date expected = new Date(2,1,1);
        assertEquals(expected.day, nextDate.day);
        assertEquals(expected.month, nextDate.month);
        assertEquals(expected.year, nextDate.year);
    }
    @Test
    public void testNextDate2(){
        Date date = new Date(31,12,2021);
        Date nextDate = date.nextDate();
        Date expected = new Date(1,1,2022);
        assertEquals(expected.day, nextDate.day);
        assertEquals(expected.month, nextDate.month);
        assertEquals(expected.year, nextDate.year);
    }
    @Test
    public void testNextDate3(){
        Date date = new Date(28,2,2024);
        Date nextDate = date.nextDate();
        Date expected = new Date(29,2,2024);
        assertEquals(expected.day, nextDate.day);
        assertEquals(expected.month, nextDate.month);
        assertEquals(expected.year, nextDate.year);
    }
    @Test
    public void testNextDate4(){
        Date date = new Date(28,2,2021);
        Date nextDate = date.nextDate();
        Date expected = new Date(1,3,2021);
        assertEquals(expected.day, nextDate.day);
        assertEquals(expected.month, nextDate.month);
        assertEquals(expected.year, nextDate.year);
    }
    @Test
    public void testNextDate5(){
        Date date = new Date(31,12,1999);
        Date nextDate = date.nextDate();
        Date expected = new Date(1,1,2000);
        assertEquals(expected.day, nextDate.day);
        assertEquals(expected.month, nextDate.month);
        assertEquals(expected.year, nextDate.year);
    }
    @Test
    public void testNextDate6(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Date date = new Date(32,13,2022);
            date.nextDate();
        });
        assertEquals("Invalid date", exception.getMessage());
    }
    @Test
    public void testNextDate7(){
        Date date = new Date(31,10,2021);
        Date nextDate = date.nextDate();
        Date expected = new Date(1,11,2021);
        assertEquals(expected.day, nextDate.day);
        assertEquals(expected.month, nextDate.month);
        assertEquals(expected.year, nextDate.year);
    }
    @Test
    public void testNextDate8(){
        Date date = new Date(30,4,2021);
        Date nextDate = date.nextDate();
        Date expected = new Date(1,5,2021);
        assertEquals(expected.day, nextDate.day);
        assertEquals(expected.month, nextDate.month);
        assertEquals(expected.year, nextDate.year);
    }
    @Test
    public void testPreviousDate1(){
        Date date = new Date(20,12,2024);
        Date previousDate = date.previousDate();
        Date expected = new Date(19,12,2024);
        assertEquals(expected.day, previousDate.day);
        assertEquals(expected.month, previousDate.month);
        assertEquals(expected.year, previousDate.year);
    }
    @Test
    public void testPreviousDate2(){
        Date date = new Date(1,1,2022);
        Date previousDate = date.previousDate();
        Date expected = new Date(31,12,2021);
        assertEquals(expected.day, previousDate.day);
        assertEquals(expected.month, previousDate.month);
        assertEquals(expected.year, previousDate.year);
    }
    @Test
    public void testPreviousDate3(){
        Date date = new Date(1,3,2021);
        Date previousDate = date.previousDate();
        Date expected = new Date(28,2,2021);
        assertEquals(expected.day, previousDate.day);
        assertEquals(expected.month, previousDate.month);
        assertEquals(expected.year, previousDate.year);
    }
    @Test
    public void testPreviousDate4(){
        Date date = new Date(1,3,2024);
        Date previousDate = date.previousDate();
        Date expected = new Date(29,2,2024);
        assertEquals(expected.day, previousDate.day);
        assertEquals(expected.month, previousDate.month);
        assertEquals(expected.year, previousDate.year);
    }
    @Test
    public void testPreviousDate5(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Date date = new Date(32,12,2022);
            date.previousDate();
        });
        assertEquals("Invalid date", exception.getMessage());
    }
    @Test
    public void testPreviousDate6(){
        Date date = new Date(1,7,2021);
        Date previousDate = date.previousDate();
        Date expected = new Date(30,6,2021);
        assertEquals(expected.day, previousDate.day);
        assertEquals(expected.month, previousDate.month);
        assertEquals(expected.year, previousDate.year);
    }
    @Test
    public void testPreviousDate7(){
        Date date = new Date(1,2,2021);
        Date previousDate = date.previousDate();
        Date expected = new Date(31,1,2021);
        assertEquals(expected.day, previousDate.day);
        assertEquals(expected.month, previousDate.month);
        assertEquals(expected.year, previousDate.year);
    }
    @Test
    public void testCompareTo1(){
        Date date1 = new Date(1,1,1);
        Date date2 = new Date(1,1,1);
        assertEquals(0, date1.compareTo(date2));
    }
    @Test
    public void testCompareTo2(){
        Date date1 = new Date(1,1,1);
        Date date2 = new Date(1,1,2);
        assertEquals(-1, date1.compareTo(date2));
    }
    @Test
    public void testCompareTo3(){
        Date date1 = new Date(1,1,2);
        Date date2 = new Date(1,1,1);
        assertEquals(1, date1.compareTo(date2));
    }
    @Test
    public void testCompareTo4(){
        Date date1 = new Date(1,1,1);
        Date date2 = new Date(1,2,1);
        assertEquals(-1, date1.compareTo(date2));
    }
    @Test
    public void testCompareTo5(){
        Date date1 = new Date(1,2,1);
        Date date2 = new Date(1,1,1);
        assertEquals(1, date1.compareTo(date2));
    }
    @Test
    public void testCompareTo6(){
        Date date1 = new Date(1,1,1);
        Date date2 = new Date(2,1,1);
        assertEquals(-1, date1.compareTo(date2));
    }
    @Test
    public void testCompareTo7(){
        Date date1 = new Date(2,1,1);
        Date date2 = new Date(1,1,1);
        assertEquals(1, date1.compareTo(date2));
    }
    @Test
    public void testCompareTo8(){
        Date date1 = new Date(1,1,1);
        Date date2 = null;
        Exception exception = assertThrows(NullPointerException.class, () -> {
            date1.compareTo(date2);
        });
        assertEquals("The date is null", exception.getMessage());
    }
    @Test
    public void testCompareTo9(){
        Date date1 = new Date(20,12,2024);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Date date2 = new Date(31,4,2024);
            date1.compareTo(date2);
        });
        assertEquals("Invalid date", exception.getMessage());
    }
}