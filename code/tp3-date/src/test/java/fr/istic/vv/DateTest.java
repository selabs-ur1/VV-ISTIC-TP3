package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    public void testIsValideDate() {
        //isValidDate
        assertTrue( Date.isValidDate(1, 1, 2020));
    }
    @Test
    public void testIsValideDate2() {
        //isValidDate
        assertTrue( Date.isValidDate(29, 2, 2024));
    } @Test
    public void testIsValideDate3() {
        //isValidDate
        assertTrue( Date.isValidDate(1, 12, 202));
    } @Test
    public void testIsValideDate4() {
        //isValidDate
        assertTrue( Date.isValidDate(13, 12, 20200));
    } @Test
    public void testIsValideDate5() {
        //isValidDate
        assertTrue( Date.isValidDate(30, 4, 2025));
    } @Test
    public void testIsValideDate6() {
        //isValidDate
        assertFalse( Date.isValidDate(1, 1, -1));
    } @Test
    public void testIsValideDate7() {
        //isValidDate
        assertFalse( Date.isValidDate(30, 2, 2024));
    } @Test
    public void testIsValideDate8() {
        //isValidDate
        assertFalse( Date.isValidDate(0, 1, 21000));
    }

        //isLeapYear
        @Test
        public void testIsLeapYear() {
            assertTrue( Date.isLeapYear(2020));
        }
        @Test
        public void testIsLeapYear2() {
            assertTrue( Date.isLeapYear(2000));
        } 
        @Test
        public void testIsLeapYear3() {
            assertTrue( Date.isLeapYear(2040));
        } 
        @Test
        public void testIsLeapYear4() {
            assertTrue( Date.isLeapYear(2400));
        } 
        @Test
        public void testIsLeapYear5() {
            assertTrue(Date.isLeapYear(400));
        }
         @Test
        public void testIsLeapYear6() {
            assertFalse(Date.isLeapYear(2023));
        } 
        @Test
        public void testIsLeapYear7() {
            assertFalse(Date.isLeapYear(1));
        } 
        @Test
        public void testIsLeapYear8() {
            assertFalse(Date.isLeapYear(-1));
        }

        @Test
        public void testNextDate1(){
            Date date = new Date(1,12,2020);
            Date nextDate = new Date(2,12,2020);
            Date newDate = date.nextDate();

            assertEquals(0, newDate.compareTo(nextDate));
            // assertEquals(newDate.getDay(), nextDate.getDay());
            // assertEquals(newDate.getMonth(), nextDate.getMonth());
            // assertEquals(newDate.getYear(), nextDate.getYear());
        }
        @Test
        public void testNextDate2(){
            Date date = new Date(29,02,2020);
            Date nextDate = new Date(1,03,2020);
            Date newDate = date.nextDate();
            
            assertEquals(0, newDate.compareTo(nextDate));
            // assertEquals(newDate.getDay(), nextDate.getDay());
            // assertEquals(newDate.getMonth(), nextDate.getMonth());
            // assertEquals(newDate.getYear(), nextDate.getYear());
        }
        @Test
        public void testNextDate3(){
            Date date = new Date(31,12,2020);
            Date nextDate = new Date(1,1,2021);
            Date newDate = date.nextDate();
            
            assertEquals(0, newDate.compareTo(nextDate));
            // assertEquals(newDate.getDay(), nextDate.getDay());
            // assertEquals(newDate.getMonth(), nextDate.getMonth());
            // assertEquals(newDate.getYear(), nextDate.getYear());
        }
        @Test
        public void testNextDate4(){
            Date date = new Date(1,12,2020);
            Date nextDate = new Date(3,12,2020);
            Date newDate = date.nextDate();
            
            assertEquals(-1, newDate.compareTo(nextDate));
            // assertEquals(newDate.getDay(), nextDate.getDay());
            // assertEquals(newDate.getMonth(), nextDate.getMonth());
            // assertEquals(newDate.getYear(), nextDate.getYear());
        }

        @Test
        public void testPreviousDate1(){
            Date date = new Date(1,12,2020);
            Date prevDate = new Date(30,11,2020);
            Date newDate = date.previousDate();

            assertEquals(0, newDate.compareTo(prevDate));
            // assertEquals(newDate.getDay(), nextDate.getDay());
            // assertEquals(newDate.getMonth(), nextDate.getMonth());
            // assertEquals(newDate.getYear(), nextDate.getYear());
        }
        @Test
        public void testPreviousDate2(){
            Date date = new Date(1,3,2021);
            Date prevDate = new Date(28,2,2021);
            Date newDate = date.previousDate();
            
            assertEquals(0, newDate.compareTo(prevDate));
            // assertEquals(newDate.getDay(), nextDate.getDay());
            // assertEquals(newDate.getMonth(), nextDate.getMonth());
            // assertEquals(newDate.getYear(), nextDate.getYear());
        }
        @Test
        public void testPreviousDate3(){
            Date date = new Date(1,3,2020);
            Date prevDate = new Date(29,2,2020);
            Date newDate = date.previousDate();
            
            assertEquals(0, newDate.compareTo(prevDate));
            // assertEquals(newDate.getDay(), nextDate.getDay());
            // assertEquals(newDate.getMonth(), nextDate.getMonth());
            // assertEquals(newDate.getYear(), nextDate.getYear());
        }
        @Test
        public void testPreviousDate4(){
            Date date = new Date(1,01,2021);
            Date prevDate = new Date(31,12,2020);
            Date newDate = date.previousDate();
            
            assertEquals(0, newDate.compareTo(prevDate));
            // assertEquals(newDate.getDay(), nextDate.getDay());
            // assertEquals(newDate.getMonth(), nextDate.getMonth());
            // assertEquals(newDate.getYear(), nextDate.getYear());
        }
}