package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PreviousDateTests {

    //DAY+1 BASIC CASE

    @Test
    public void previousDayInMonthPositive(){
        Date date = (new Date(5,3,65465)).previousDate();
        assertEquals(0, date.compareTo(new Date(4,3,65465)));
    }

    @Test
    public void previousDayInMonthNegative(){
        Date date = new Date(5,3,-65465).previousDate();
        assertEquals(0, date.compareTo(new Date(4,3,-65465)));
    }

    //MONTH+1 31DAYS

    @Test
    public void previousMonth1To31(){
        Date date = new Date(1,4,65465).previousDate();
        assertEquals(0, date.compareTo(new Date(31,3,65465)));
    }

    //30DAYS

    @Test
    public void previousMonth1To30(){
        Date date = new Date(1,5,65465).previousDate();
        assertEquals(0, date.compareTo(new Date(30,4,65465)));
    }

    //NOT LEAP YEAR FEB

    @Test
    public void previousMonthNotLeapFebruary(){
        Date date = new Date(1,3,65465).previousDate();
        assertEquals(0, date.compareTo(new Date(28,2,65465)));
    }

    //LEAP YEAR FEB

    @Test
    public void previousMonthLeapFebruary(){
        Date date = new Date(1,3,2024).previousDate();
        assertEquals(0, date.compareTo(new Date(29,2,2024)));
    }

    //NEXTYEAR

    @Test
    public void previousYear(){
        Date date = new Date(1,1,2024).previousDate();
        assertEquals(0, date.compareTo(new Date(31,12,2023)));
    }

}