package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    public void testIsValidDateOK(){
        assertTrue(Date.isValidDate(1, 1, 2022));
        assertTrue(Date.isValidDate(31, 12, 2022));
        assertTrue(Date.isValidDate(29,2,2024));
    }

    @Test
    public void testIsValidDateNOKLeapYear(){
        assertFalse(Date.isValidDate(29,2,2022));
    }

    @Test
    public void testIsValidDateNOKInvalidMonth() {
        assertFalse(Date.isValidDate(5,13,2022));
        assertFalse(Date.isValidDate(5,-1,2022));
    }

    @Test
    public void testIsValidDateNOKInvalidDay() {
        assertFalse(Date.isValidDate(32,12,2022));
        assertFalse(Date.isValidDate(31,11,2022));
        assertFalse(Date.isValidDate(-1,2,2022));
    }

    @Test
    public void testIsLeapYear(){
        assertTrue(Date.isLeapYear(2024));
        assertFalse(Date.isLeapYear(2022));
    }

    @Test
    public void testNextDateOK(){
        Date d = new Date(5,5,2022);
        Date nd = d.nextDate();
        assertEquals( 6, nd.getDay());
        assertEquals(5,nd.getMonth());
        assertEquals(2022, nd.getYear());
    }

    @Test
    public void testNextDateOKNewMonth(){
        Date d = new Date(30,11,2022);
        Date nd = d.nextDate();
        assertEquals( 1, nd.getDay());
        assertEquals(12,nd.getMonth());
        assertEquals(2022, nd.getYear());
    }

    @Test
    public void testNextDateOKNewYear(){
        Date d = new Date(31,12,2022);
        Date nd = d.nextDate();
        assertEquals( 1, nd.getDay());
        assertEquals(1,nd.getMonth());
        assertEquals(2023, nd.getYear());
    }

    @Test
    public void testNextDateOK29Feb(){
        Date d = new Date(28,2,2024);
        Date nd = d.nextDate();
        assertEquals( 29, nd.getDay());
        assertEquals(2,nd.getMonth());
        assertEquals(2024, nd.getYear());
    }

    @Test
    public void testPreviousDateOK(){
        Date d = new Date(5,5,2022);
        Date pd = d.previousDate();
        assertEquals( 4, pd.getDay());
        assertEquals(5,pd.getMonth());
        assertEquals(2022, pd.getYear());
    }

    @Test
    public void testPreviousDateOKChangeMonth(){
        Date d = new Date(1,5,2022);
        Date pd = d.previousDate();
        assertEquals( 30, pd.getDay());
        assertEquals(4,pd.getMonth());
        assertEquals(2022, pd.getYear());
    }

    @Test
    public void testPreviousDateOKChangeYear(){
        Date d = new Date(1,1,2022);
        Date pd = d.previousDate();
        assertEquals( 31, pd.getDay());
        assertEquals(12,pd.getMonth());
        assertEquals(2021, pd.getYear());
    }

    @Test
    public void testPreviousDateOK29Feb(){
        Date d = new Date(1,3,2024);
        Date pd = d.previousDate();
        assertEquals( 29, pd.getDay());
        assertEquals(2,pd.getMonth());
        assertEquals(2024, pd.getYear());
    }

    @Test void testCompareToEquals(){
        Date d1 = new Date( 1,1,2022);
        Date d2 = new Date(1,1,2022);
        assertEquals(0, d1.compareTo(d2));
    }

    @Test void testCompareToDifferentYear(){
        Date d1 = new Date( 1,1,2022);
        Date d2 = new Date(1,1,2021);
        assertEquals(1, d1.compareTo(d2));
        assertEquals(-1, d2.compareTo(d1));
    }

    @Test void testCompareToDifferentMonths(){
        Date d1 = new Date( 1,2,2022);
        Date d2 = new Date(1,1,2022);
        assertEquals(1, d1.compareTo(d2));
        assertEquals(-1, d2.compareTo(d1));
    }

    @Test void testCompareToDifferentDays(){
        Date d1 = new Date( 2,1,2022);
        Date d2 = new Date(1,1,2022);
        assertEquals(1, d1.compareTo(d2));
        assertEquals(-1, d2.compareTo(d1));
    }

}