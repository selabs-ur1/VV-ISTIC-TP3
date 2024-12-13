package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void isValideDateNominalTest(){
        assertTrue(Date.isValidDate(10,12,2024));
        assertTrue(Date.isValidDate(12,02,2001));
        assertTrue(Date.isValidDate(12,2,-2001));
        assertTrue(Date.isValidDate(12,2,0));

    }

    @Test
    void isValideInvalideDateTest(){
        assertFalse(Date.isValidDate(10,-12,2024));
        assertFalse(Date.isValidDate(-12,2,2001));
        assertFalse(Date.isValidDate(0,2,2001));
        assertFalse(Date.isValidDate(0,0,0));
        assertFalse(Date.isValidDate(1,0,2001));

    }

    @Test
    void isValideIsLeapDateDateTest(){
        //Leap year and february
        assertTrue(Date.isValidDate(29,2,2016));
        assertTrue(Date.isValidDate(29,2,2020));
        assertTrue(Date.isValidDate(15,2,2020));

        //Leap year and february but invalide day
        assertFalse(Date.isValidDate(50,2,2020));
        //not Leap year and february
        assertFalse(Date.isValidDate(29,2,2015));
        assertFalse(Date.isValidDate(29,2,2001));
    }

    /**
     * Test des différentes exceptions possibles
     */
    @Test
    void DateThrowsTest(){
        assertThrows(IllegalArgumentException.class, ()->new Date(12,-1,55));
        assertThrows(IllegalArgumentException.class, ()->new Date(-12,2,55));
        assertThrows(IllegalArgumentException.class, ()->new Date(80,2,55));
        assertThrows(IllegalArgumentException.class, ()->new Date(12,89,55));
    }

    @Test
    void isLeapYearTrueTest() {
        assertTrue(Date.isLeapYear(2000));
        assertTrue(Date.isLeapYear(2016));
        assertTrue(Date.isLeapYear(2020));
    }

    @Test
    void isLeapYearFalseTest() {
        assertFalse(Date.isLeapYear(2011));
        assertFalse(Date.isLeapYear(2017));
        assertFalse(Date.isLeapYear(100));
    }

    @Test
    void nextDateNominalTest(){
        Date date1 = new Date(12,2,2001);
        Date nxDate = date1.nextDate();
        assertEquals(13,nxDate.getDay());
        assertEquals(2,nxDate.getMonth());
        assertEquals(2001,nxDate.getYear());
    }

    @Test
    void nextDateNewYearTest(){
        Date date1 = new Date(31,12,2001);
        Date nxDate = date1.nextDate();
        assertEquals(1,nxDate.getDay());
        assertEquals(1,nxDate.getMonth());
        assertEquals(2002,nxDate.getYear());
    }

    @Test
    void nextDateBissextileTest(){
        Date date2 = new Date(29,2,2000);
        Date nxDate2 = date2.nextDate();
        assertEquals(1,nxDate2.getDay());
        assertEquals(3,nxDate2.getMonth());
        assertEquals(2000,nxDate2.getYear());
    }

    @Test
    void nextDateBissextileNotFebruaryTest(){
        Date date2 = new Date(29,4,2000);
        Date nxDate2 = date2.nextDate();
        assertEquals(30,nxDate2.getDay());
        assertEquals(4,nxDate2.getMonth());
        assertEquals(2000,nxDate2.getYear());
    }

    @Test
    void nextDateBissextileFebruaryTest(){
        Date date2 = new Date(12,2,2000);
        Date nxDate2 = date2.nextDate();
        assertEquals(13,nxDate2.getDay());
        assertEquals(2,nxDate2.getMonth());
        assertEquals(2000,nxDate2.getYear());
    }

    @Test
    void nextDateNextMonthTest(){
        Date date2 = new Date(28,2,2001);
        Date nxDate2 = date2.nextDate();
        assertEquals(1,nxDate2.getDay());
        assertEquals(3,nxDate2.getMonth());
        assertEquals(2001,nxDate2.getYear());
    }


    /////

    @Test
    void previousDateNominalTest(){
        Date date1 = new Date(12,2,2001);
        Date prevDate = date1.previousDate();
        assertEquals(11,prevDate.getDay());
        assertEquals(2,prevDate.getMonth());
        assertEquals(2001,prevDate.getYear());
    }

    @Test
    void previousDateNewYearTest(){
        Date date1 = new Date(1,1,2001);
        Date prevDate = date1.previousDate();
        assertEquals(31,prevDate.getDay());
        assertEquals(12,prevDate.getMonth());
        assertEquals(2000,prevDate.getYear());
    }

    @Test
    void previousDateBissextileTest(){
        Date date1 = new Date(1,3,2000);
        Date prevDate = date1.previousDate();
        assertEquals(29,prevDate.getDay());
        assertEquals(2,prevDate.getMonth());
        assertEquals(2000,prevDate.getYear());
    }

    @Test
    void previousDateBissextileNotFebruaryTest(){
        Date date1 = new Date(29,4,2000);
        Date prevDate = date1.previousDate();
        assertEquals(28,prevDate.getDay());
        assertEquals(4,prevDate.getMonth());
        assertEquals(2000,prevDate.getYear());
    }

    @Test
    void previousDateBissextileFebruaryTest(){
        Date date1 = new Date(11,2,2000);
        Date prevDate = date1.previousDate();
        assertEquals(10,prevDate.getDay());
        assertEquals(2,prevDate.getMonth());
        assertEquals(2000,prevDate.getYear());
    }

    @Test
    void previousDateBissextileMarchTest(){
        Date date1 = new Date(11,3,2000);
        Date prevDate = date1.previousDate();
        assertEquals(10,prevDate.getDay());
        assertEquals(3,prevDate.getMonth());
        assertEquals(2000,prevDate.getYear());
    }

    @Test
    void previousDatePreviousMonthTest(){
        Date date1 = new Date(1,6,2001);
        Date prevDate = date1.previousDate();
        assertEquals(31,prevDate.getDay());
        assertEquals(5,prevDate.getMonth());
        assertEquals(2001,prevDate.getYear());
    }


    @Test
    void compareToNominal1(){
        Date date1 = new Date(1,6,2001);
        Date date2 = new Date(1,6,2002);
        assertTrue(date1.compareTo(date2)<0);
    }

    @Test
    void compareToSameDate(){
        Date date1 = new Date(1,6,2001);
        Date date2 = new Date(1,6,2001);
        assertEquals(0, date1.compareTo(date2));
    }

    @Test
    void compareToSameDateInferiorToYear0(){
        Date date1 = new Date(1,6,-500);
        Date date2 = new Date(1,6,-500);
        assertEquals(0, date1.compareTo(date2));
    }

    @Test
    void compareToBissextileInferiorToNextYear(){
        Date date1 = new Date(29,2,2000);
        Date date2 = new Date(30,6,2001);
        assertTrue(date1.compareTo(date2)<0);
    }

    @Test
    void compareToBissextileSuperiorToPreviousYear(){
        Date date1 = new Date(29,2,2000);
        Date date2 = new Date(30,6,1999);
        assertTrue(date1.compareTo(date2)>0);
    }

    @Test
    void compareToYearInferiorTo0(){
        Date date1 = new Date(25,2,-500);
        Date date2 = new Date(30,6,1999);
        assertTrue(date1.compareTo(date2)<0);
    }

}