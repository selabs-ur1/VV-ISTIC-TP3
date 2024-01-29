package fr.istic.vv;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {

    // isDateValid tests
    @Test
    public void test1(){
        Date date = new Date(31,1,2010);
        assertTrue(date.isValidDate());
    }

    @Test
    public void test2(){
        Date date = new Date(31,4,2010);
        assertFalse(date.isValidDate());
    }

    @Test
    public void test3(){
        Date date = new Date(29,2,2012);
        assertTrue(date.isValidDate());
    }

    @Test
    public void test4(){
        Date date = new Date(29,2,2010);
        assertFalse(date.isValidDate());
    }

    @Test
    public void test5(){
        Date date = new Date(-1,2,2012);
        assertFalse(date.isValidDate());
    }

    @Test
    public void test6(){
        Date date = new Date(31,13,2012);
        assertFalse(date.isValidDate());
    }

    @Test
    public void test27(){
        Date date = new Date(31,13,-1);
        assertFalse(date.isValidDate());
    }

    // IsLeapYear tests

    @Test
    public void test7(){
        Date date = new Date(1,2,2011);
        assertFalse(date.isLeapYear());
    }

    @Test
    public void test8(){
        Date date = new Date(1,2,2016);
        assertTrue(date.isLeapYear());
    }

    // getNextNext tests

    @Test
    public void test9(){
        Date date = new Date(31,7,2016);
        assertTrue(date.getNextDate().equalsDates(new Date(1, 8, 2016)));
    }

    @Test
    public void test10(){
        Date date = new Date(28,2,2016);
        assertTrue(date.getNextDate().equalsDates(new Date(29, 2, 2016)));
    }

    @Test
    public void test11(){
        Date date = new Date(28,2,2017);
        assertTrue(date.getNextDate().equalsDates(new Date(1, 3, 2017)));
    }

    @Test
    public void test12(){
        Date date = new Date(31,12,2017);
        assertTrue(date.getNextDate().equalsDates(new Date(1, 1, 2018)));
    }

    @Test
    public void test13(){
        Date date = new Date(17,9,2017);
        assertTrue(date.getNextDate().equalsDates(new Date(18, 9, 2017)));
    }

    // getPreviousDate tests

    @Test
    public void test14(){
        Date date = new Date(1, 8, 2016);
        assertTrue(date.getPreviousDate().equalsDates(new Date(31, 7, 2016)));
    }

    @Test
    public void test15(){
        Date date = new Date(29,2,2016);
        assertTrue(date.getPreviousDate().equalsDates(new Date(28, 2, 2016)));
    }

    @Test
    public void test16(){
        Date date = new Date(1,3,2017);
        assertTrue(date.getPreviousDate().equalsDates(new Date(28, 2, 2017)));
    }

    @Test
    public void test17(){
        Date date = new Date(1,1,2018);
        assertTrue(date.getPreviousDate().equalsDates(new Date(31, 12, 2017)));
    }

    @Test
    public void test18(){
        Date date = new Date(17,9,2017);
        assertTrue(date.getPreviousDate().equalsDates(new Date(16, 9, 2017)));
    }

    // compareTo tests

    @Test
    public void test19(){
        Date date = new Date(17,9,2017);
        assertThrows(NullPointerException.class, () -> {
            date.compareTo(null); // Appel de la méthode avec un argument null
        });
    }

    @Test
    public void test20(){
        Date date = new Date(17,9,2017);
        assertEquals(date.compareTo(new Date(17,9,2017)), 0);
    }

    @Test
    public void test21(){
        Date date = new Date(17,9,2016);
        assertEquals(date.compareTo(new Date(17,9,2017)), -1);
    }

    @Test
    public void test22(){
        Date date = new Date(17,8,2017);
        assertEquals(date.compareTo(new Date(17,9,2017)), -1);
    }

    @Test
    public void test23(){
        Date date = new Date(16,9,2017);
        assertEquals(date.compareTo(new Date(17,9,2017)), -1);
    }

    @Test
    public void test24(){
        Date date = new Date(16,9,2018);
        assertEquals(date.compareTo(new Date(17,9,2017)), 1);
    }

    @Test
    public void test25(){
        Date date = new Date(16,10,2017);
        assertEquals(date.compareTo(new Date(17,9,2017)), 1);
    }

    @Test
    public void test26(){
        Date date = new Date(18,9,2017);
        assertEquals(date.compareTo(new Date(17,9,2017)), 1);
    }

    // equalsDates tests

    @Test
    public void test28(){
        Date date = new Date(18,9,2017);
        assertFalse(date.equalsDates(new Date(17,9,2017)));
    }

    // getMaxDaysInMonth tests

    @Test
    public void test29(){
        Date date = new Date(18,13,2017);
        assertEquals(date.getMaxDaysInMonth(), 0);
    }
}
