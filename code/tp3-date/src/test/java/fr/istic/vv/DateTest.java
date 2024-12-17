package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.Exception.*;

import static org.junit.jupiter.api.Assertions.*;

import fr.istic.vv.Date;

class DateTest {

    //tests sur le constructeur où une Excepetion est attendue
    //permet également de commencer à tester isValidDate qui est call dedans
    @Test 
    public void testConstruException(){
        assertThrows(IllegalArgumentException.class, () -> {new Date(-1,12,2001);});
    }

    @Test 
    public void testConstruException2(){
        assertThrows(IllegalArgumentException.class, () -> {new Date(33,12,2001);});
    }

    @Test 
    public void testConstruException3(){
        assertThrows(IllegalArgumentException.class, () -> {new Date(22,13,2001);});
    }

    @Test 
    public void testConstruException4(){
        assertThrows(IllegalArgumentException.class, () -> {new Date(12,12,0);});
    }

    //tests de isLeapYear
    @Test 
    public void testLeapYearNOk(){
        assertFalse(Date.isLeapYear(1900));
    }

    @Test
    public void testLeapYearOk(){
        //pas divisible par 400, divisible par 4, et pas 100
        assertTrue(Date.isLeapYear(2024));
    }


    //tests de isValidDate
    @Test
    public void testisValideDateOkFevrierB(){
        //annee bissextile 29 fevrier
        assertTrue(Date.isValidDate(29, 2, 2024));
    }

    @Test
    public void testisValideDateOkFevrierNonB(){
        //annee bissextile 29 fevrier
        assertTrue(Date.isValidDate(28, 2, 2025));
    }

    @Test
    public void testisValideDateOkMoisDe31J(){
        //annee bissextile 29 fevrier

        assertTrue(Date.isValidDate(31, 10, 2025));
    }

    @Test
    public void testisValideDateOkMoisDe30J(){
        //annee bissextile 29 fevrier
        assertTrue(Date.isValidDate(30, 9, 2025));
    }

    //Tests de previousDate
    @Test
    public void testPreviousDateNewYear(){
        Date newYear = new Date(1, 1, 2001);
        Date lastPreviousYear = new Date(31,12,2000);
        assertEquals(newYear.previousDate(), lastPreviousYear);
    }

    @Test
    public void testPreviousDateMoisPrecA30J(){
        Date mois31 = new Date(1, 10, 2001);
        Date mois30 = new Date(30,9,2001);
        assertEquals(mois31.previousDate(), mois30);
    }

    @Test
    public void testPreviousDateMoisPrecA31J(){
        Date current = new Date(01, 11, 2001);
        Date newDate = new Date(31,10,2001);
        assertEquals(current.previousDate(), newDate);
    }    
    
    @Test
    public void testPreviousDatePremierMarsB(){
        Date newYear = new Date(01, 3, 2024);
        Date lastPreviousYear = new Date(29,2,2024);
        assertEquals(newYear.previousDate(), lastPreviousYear);
    }    
    
    @Test
    public void testPreviousDatePremierMarsNonB(){
        Date newYear = new Date(01, 3, 2003);
        Date lastPreviousYear = new Date(28,2,2003);
        assertEquals(newYear.previousDate(), lastPreviousYear);
    }

    //Tests de nextDate
    @Test
    public void testNexDateNewYear(){
        Date newYear = new Date(1, 1, 2001);
        Date lastPreviousYear = new Date(31,12,2000);
        assertEquals(newYear, lastPreviousYear.nextDate());
    }

    @Test
    public void testNextDateMoisSuivA30J(){
        Date mois31 = new Date(1, 10, 2001);
        Date mois30 = new Date(30,9,2001);
        assertEquals(mois31, mois30.nextDate());
    }

    @Test
    public void testNextDateMoisSuivA31J(){
        Date current = new Date(01, 11, 2001);
        Date newDate = new Date(31,10,2001);
        assertEquals(current, newDate.nextDate());
    }    
    
    @Test
    public void testNextDatePremierMarsB(){
        Date premierMars = new Date(01, 3, 2024);
        Date lastFevrier = new Date(29,2,2024);
        assertEquals(lastFevrier.nextDate(), premierMars);
    }    
    
    @Test
    public void testNextDatePremierMarsNonB(){
        Date premierMars = new Date(01, 3, 2003);
        Date lastFevrier = new Date(28,2,2003);
        assertEquals(lastFevrier.nextDate(), premierMars);
    }

    //Tests de compareTo
    @Test 
    public void testCompareToException(){
        assertThrows(NullPointerException.class, () -> {new Date(22,12,1900).compareTo(null);});
    }

    @Test
    public void testCompareToYearDiffSup(){
        Date current = new Date(01, 3, 2003);
        Date aTester = new Date(01,3,2004);
        assertEquals(current.compareTo(aTester), -1);
    }

    @Test
    public void testCompareToYearDiffInf(){
        Date current = new Date(01, 3, 2003);
        Date aTester = new Date(01,3,2004);
        assertEquals(aTester.compareTo(current), 1);
    }


    @Test
    public void testCompareToMonthSup(){
        Date current = new Date(01, 2, 2004);
        Date aTester = new Date(01,3,2004);
        assertEquals(current.compareTo(aTester), -1);
    }

    @Test
    public void testCompareToMonthInf(){
        Date current = new Date(01, 2, 2004);
        Date aTester = new Date(01,3,2004);
        assertEquals(aTester.compareTo(current), 1);
    }

    @Test
    public void testCompareToDaySup(){
        Date current = new Date(11, 2, 2004);
        Date aTester = new Date(12,2,2004);
        assertEquals(current.compareTo(aTester), -1);
    }

    @Test
    public void testCompareToDayInf(){
        Date current = new Date(11, 2, 2004);
        Date aTester = new Date(12,2,2004);
        assertEquals(aTester.compareTo(current), 1);
    }

    @Test
    public void testCompareToSameDate(){
        Date current = new Date(1, 2, 2004);
        Date aTester = new Date(01,2,2004);
        assertEquals(aTester.compareTo(current), 0);
    }


}