package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    int year;
    int month;
    int day;

    @BeforeEach
    void init(){
        day = 13;
        month = 12;
        year = 1993;
    }

    @Test
    public void testIsValidate(){
        assertTrue(Date.isValidDate(day,month,year));
    }

    @Test
    public void testIsNotValidate(){
        assertFalse(Date.isValidDate(1993,13,-12) );
    }

    @Test
    public void testIsLeapYear(){
        assertTrue(Date.isLeapYear(2020));
    }

    @Test
    public void testIsNotLeapYear(){
        assertFalse(Date.isLeapYear(1993));
    }

    //-+=+--+=+--+=+-
    // PREVIOUS/NEXT
    //-+=+--+=+--+=+-
    @Test
    public void testNextDateValidate(){
        Date date = new Date(day,month,year);
        Date next = date.nextDate();
        assertEquals(next.getDay(),day+1);
        assertEquals(next.getMonth(),month);
        assertEquals(next.getYear(),year);
    }

    @Test
    public void testNextMonth(){
        Date date = new Date(30,11,year);
        Date next = date.nextDate();
        assertEquals(next.getDay(),1);
        assertEquals(next.getMonth(),month);
        assertEquals(next.getYear(),year);
    }

    @Test
    public void testNextYear(){
        Date date = new Date(31,month,year);
        Date next = date.nextDate();
        assertEquals(next.getDay(),1);
        assertEquals(next.getMonth(),1);
        assertEquals(next.getYear(),year+1);
    }

    @Test
    public void testPreviousDateValidate(){
        Date date = new Date(day,month,year);
        Date previous = date.previousDate();
        assertEquals(previous.getDay(),day-1);
        assertEquals(previous.getMonth(),month);
        assertEquals(previous.getYear(),year);
    }

    @Test
    public void testPreviousMonth(){
        Date date = new Date(1,month,year);
        Date previous = date.previousDate();
        assertEquals(previous.getDay(),30);
        assertEquals(previous.getMonth(),month-1);
        assertEquals(previous.getYear(),year);
    }

    @Test
    public void testPreviousYear(){
        Date date = new Date(1,1,year);
        Date previous = date.previousDate();
        assertEquals(previous.getDay(),31);
        assertEquals(previous.getMonth(),month);
        assertEquals(previous.getYear(),year-1);
    }

    //-+=+--+=+--+=+-
    // COMPARE TESTS
    //-+=+--+=+--+=+-
    @Test
    public void testCompareToSup(){
        Date date = new Date(day,month,year);
        Date date2 = new Date(day+1,month,year);
        assertEquals(date.compareTo(date2), -1);
    }

    @Test
    public void testCompareToInf(){
        Date date = new Date(day,month,year);
        Date date2 = new Date(day-1,month,year);
        assertEquals(date.compareTo(date2), +1);
    }

    @Test
    public void testCompareSame(){
        Date date = new Date(day,month,year);
        Date date2 = new Date(day,month,year);
        assertEquals(date.compareTo(date2), 0);
    }

    @Test
    public void testCompareNull(){
        Date date = new Date(day,month,year);
        Date date2 = null;
        assertThrows(NullPointerException.class, () -> {date.compareTo(date2);});
    }

    //-+=+--+=+--+=+-
    // EXTREME TESTS
    //-+=+--+=+--+=+-
    @Test
    public void testExtremeLowDay(){
        assertFalse(Date.isValidDate(0,month,year) );
    }

    @Test
    public void testExtremeHighDay(){
        assertFalse(Date.isValidDate(32,month,year) );
    }

    @Test
    public void testExtremeFebuaryNoLeap(){
        assertFalse(Date.isValidDate(29,2,1993) );
    }

    @Test
    public void testExtremeFebuaryLeap(){
        assertFalse(Date.isValidDate(30,2,1993) );
    }

    @Test
    public void testExtremeLowMonth(){
        assertFalse(Date.isValidDate(day,0,year) );
    }

    @Test
    public void testExtremeHighMonth(){
        assertFalse(Date.isValidDate(day,13,year) );
    }

    @Test
    public void testExtremeLowYear(){
        assertFalse(Date.isValidDate(day,month,-2147483647) );
    }

    @Test
    public void testExtremeHighYear(){
        assertFalse(Date.isValidDate(day,month,2147483647) );
    }

}