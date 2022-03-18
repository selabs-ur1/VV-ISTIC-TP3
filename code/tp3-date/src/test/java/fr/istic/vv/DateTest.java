package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    //IS VAlID
    @Test
    void testIsValidTrue(){
        assertTrue(Date.isValidDate(1,1,0));
    }
    @Test
    void testIsValidFalse(){
        assertFalse(Date.isValidDate(0,0,0));
    }

    //IS LEAP YEAR
    @Test
    void testIsLeapFalse(){
        assertFalse(Date.isLeapYear(1900));
    }
    @Test
    void testIsLeapTrue(){
        assertTrue(Date.isLeapYear(2000));
    }

    //NEXT DAY
    //true
    //changement jour
    @Test
    void testIsNextDayTrueDay(){
        Date date = new Date(1,1,0);
        Date nextDay = new Date(2,1,0);
        assertEquals(nextDay.getDay(), date.nextDate().getDay());
        assertEquals(nextDay.getMonth(), date.nextDate().getMonth());
        assertEquals(nextDay.getYear(), date.nextDate().getYear());
    }
    //changement de mois
    @Test
    void testIsNextDayTrueMonth(){
        Date date = new Date(31,1,0);
        Date nextDay = new Date(1,2,0);
        assertEquals(nextDay.getDay(), date.nextDate().getDay());
        assertEquals(nextDay.getMonth(), date.nextDate().getMonth());
        assertEquals(nextDay.getYear(), date.nextDate().getYear());
    }
    //changement année
    @Test
    void testIsNextDayTrueYear(){
        Date date = new Date(31,12,0);
        Date nextDay = new Date(1,1,1);
        assertEquals(nextDay.getDay(), date.nextDate().getDay());
        assertEquals(nextDay.getMonth(), date.nextDate().getMonth());
        assertEquals(nextDay.getYear(), date.nextDate().getYear());
    }
    //false
    @Test
    void testIsNextDayFalse(){
        Date date = new Date(31,12,0);
        Date nextDay = new Date(1,1,2);
        assertEquals(nextDay.getDay(), date.nextDate().getDay());
        assertEquals(nextDay.getMonth(), date.nextDate().getMonth());
        assertNotEquals(nextDay.getYear(), date.nextDate().getYear());
    }
    //null
    @Test
    void testIsNextDayNull(){
        Date date = new Date(1,0,0);
        assertNull(date.nextDate());
    }

    //PREVIOUS DAY
    //true
    //changement jour
    @Test
    void testIsPreviousDayTrueDay(){
        Date date = new Date(2,1,0);
        Date previousDay = new Date(1,1,0);
        assertEquals(previousDay.getDay(), date.previousDate().getDay());
        assertEquals(previousDay.getMonth(), date.previousDate().getMonth());
        assertEquals(previousDay.getYear(), date.previousDate().getYear());
    }
    //changement de mois
    @Test
    void testIsPreviousDayTrueMonth(){
        Date date = new Date(1,2,0);
        Date previousDay = new Date(31,1,0);
        assertEquals(previousDay.getDay(), date.previousDate().getDay());
        assertEquals(previousDay.getMonth(), date.previousDate().getMonth());
        assertEquals(previousDay.getYear(), date.previousDate().getYear());
    }
    //changement année
    @Test
    void testIsPreviousDayTrueYear(){
        Date date = new Date(1,1,1);
        Date previousDay = new Date(31,12,0);
        assertEquals(previousDay.getDay(), date.previousDate().getDay());
        assertEquals(previousDay.getMonth(), date.previousDate().getMonth());
        assertEquals(previousDay.getYear(), date.previousDate().getYear());
    }
    //false
    @Test
    void testIsPreviousDayFalse(){
        Date date = new Date(1,1,0);
        Date previousDay = new Date(31,12,2);
        assertEquals(previousDay.getDay(), date.previousDate().getDay());
        assertEquals(previousDay.getMonth(), date.previousDate().getMonth());
        assertNotEquals(previousDay.getYear(), date.previousDate().getYear());
    }
    //null
    @Test
    void testIsPreviousDayNull(){
        Date date = new Date(1,0,0);
        assertNull(date.previousDate());
    }

    //COMPARE TO
    //0
    @Test
    void testCompareToSameFalse(){
        Date date1 = new Date(1,1,0);
        Date date2 = new Date(31,12,2);
        assertNotEquals(date1.compareTo(date2), 0);
    }
    @Test
    void testCompareToSameTrue(){
        Date date1 = new Date(1,1,0);
        Date date2 = new Date(1,1,0);
        assertEquals(date1.compareTo(date2), 0);
    }
    //-1
    @Test
    void testCompareToLowerFalse(){
        Date date1 = new Date(2,1,0);
        Date date2 = new Date(1,1,0);
        assertNotEquals(date1.compareTo(date2), -1);
    }
    @Test
    void testCompareToLowerTrue(){
        Date date1 = new Date(1,1,0);
        Date date2 = new Date(2,1,0);
        assertEquals(date1.compareTo(date2), -1);
    }

    //1
    @Test
    void testCompareToGreaterFalse(){
        Date date1 = new Date(1,1,0);
        Date date2 = new Date(2,1,0);
        assertNotEquals(date1.compareTo(date2), 1);
    }
    @Test
    void testCompareToGreaterTrue(){
        Date date1 = new Date(12,1,0);
        Date date2 = new Date(2,1,0);
        assertEquals(date1.compareTo(date2), 1);
    }
    //throw error
    @Test
    void testCompareToError(){
        Date date1 = new Date(0,1,0);
        Date date2 = new Date(0,1,0);
        assertThrows(NullPointerException.class, () -> {date1.compareTo(date2);} );
    }



}