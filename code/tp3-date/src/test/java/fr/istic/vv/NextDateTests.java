package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static fr.istic.vv.Date.isLeapYear;
import static org.junit.jupiter.api.Assertions.*;

class NextDateTests {

    //DAY+1 BASIC CASE

    @Test
    public void nextDayInMonthPositive(){
        Date date = (new Date(5,3,65465)).nextDate();
        assertEquals(0, date.compareTo(new Date(6,3,65465)));
    }

    @Test
    public void nextDayInMonthNegative(){
        Date date = new Date(5,3,-65465).nextDate();
        assertEquals(0, date.compareTo(new Date(6,3,-65465)));
    }

    //MONTH+1 31DAYS

    @Test
    public void nextMonth31(){
        Date date = new Date(31,3,65465).nextDate();
        assertEquals(0, date.compareTo(new Date(1,4,65465)));
    }

    //30DAYS

    @Test
    public void nextMonth30(){
        Date date = new Date(30,4,65465).nextDate();
        assertEquals(0, date.compareTo(new Date(1,5,65465)));
    }

    @Test
    public void notNextMonth30(){
        Date date = new Date(30,3,65465).nextDate();
        assertEquals(0, date.compareTo(new Date(31,3,65465)));
    }

    //NOT LEAP YEAR FEB

    @Test
    public void nextMonthNotLeapFebruary(){
        Date date = new Date(28,2,65465).nextDate();
        assertEquals(0, date.compareTo(new Date(1,3,65465)));
    }

    //LEAP YEAR FEB

    @Test
    public void notNextMonthLeapFebruary(){
        Date date = new Date(28,2,2024).nextDate();
        assertEquals(0, date.compareTo(new Date(29,2,2024)));
    }

    @Test
    public void nextMonthLeapFebruary(){
        Date date = new Date(29,2,2024).nextDate();
        assertEquals(0, date.compareTo(new Date(1,3,2024)));
    }

    //NEXTYEAR

    @Test
    public void nextYear(){
        Date date = new Date(31,12,2024).nextDate();
        assertEquals(0, date.compareTo(new Date(1,1,2025)));
    }

    @Test
    public void nextYearNegToZero(){
        Date date = new Date(31,12,-1).nextDate();
        assertEquals(0, date.compareTo(new Date(1,1,0)));
    }

}