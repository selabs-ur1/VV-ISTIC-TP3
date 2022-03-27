package fr.istic.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {


    @DisplayName("test isValidDate 1")
    @Test
    public void test29DaysFebruaryCommonYear() {
        assertFalse(Date.isValidDate(29, 2, 2019), "February in common years should not have more than 28 days.");
    }

    @DisplayName("test isValidDate 2")
    @Test
    public void testNegativeYear() {
        assertFalse(Date.isValidDate(1, 1, -1), "The value of year should not be negative");
    }

    @DisplayName("test isValidDate 3")
    @Test
    public void testNegativeDayMonth0Year() {
        assertFalse(Date.isValidDate(-1, -1, 0), "The value of year should not be 0 and the values of day and month should not be negative");
    }

    @DisplayName("test isValidDate 4")
    @Test
    public void testDay0() {
        assertFalse(Date.isValidDate(0, 4, 2020), "The value of day should not be 0");
    }

    @DisplayName("test isValidDate 5")
    @Test
    public void testMonth0NegativeDay() {
        assertFalse(Date.isValidDate(-2, 0, 2019), "The value of day should not be negative and the value of month should not be 0");
    }

    @DisplayName("test isValidDate 6")
    @Test
    public void testDay0Month13() {
        assertFalse(Date.isValidDate(0, 13, 2018), "The value of day should not be 0 and the value of month should not be greater than 12");
    }

    @Test
    @DisplayName("test isValidDate 7")
    public void test29DaysFebruaryLeapYear() {
        assertTrue(Date.isValidDate(29, 2, 2020), "February in leap years could have 29 days.");
    }

    @Test
    @DisplayName("test isValidDate 8")
    public void testThrowException() {
        assertThrows(Exception.class, ()-> new Date(29, 2, 2022));
        assertDoesNotThrow(()-> new Date(29, 2, 2020));
    }

    // new test cases for isValidDate() after mutation testing
    @Test
    @DisplayName("test isValidDate 9")
    public void testLessThan1() {
        assertFalse(Date.isValidDate(1, 0, 2020), "Month should not be less than 1.");
        assertFalse(Date.isValidDate(0, 1, 2020), "Day should not be less than 1.");
    }

    @Test
    @DisplayName("test isValidDate 10")
    public void testMaxMinYear() {
        assertFalse(Date.isValidDate(1, 1, 10000), "Year should not be greater than 9999.");
        assertFalse(Date.isValidDate(1, 1, 1700), "Year should not be less than 1800.");
    }

    @Test
    @DisplayName("test isValidDate 11")
    public void testFeb30LeapYear() {
        assertFalse(Date.isValidDate(30, 2, 2020), "February in leap years should not have more than 29 days");
    }

    @Test
    @DisplayName("test isValidDate 12")
    public void testFeb27CommonYear() {
        assertTrue(Date.isValidDate(27, 2, 2022), "February could have less than 28 days");
    }

    @Test
    @DisplayName("test isValidDate 13")
    public void testMonth46911Days31() {
        assertFalse(Date.isValidDate(31, 4, 2022), "April should not have more than 30 days");
        assertFalse(Date.isValidDate(31, 6, 2022), "June should not have more than 30 days");
        assertFalse(Date.isValidDate(31, 9, 2022), "September should not have more than 30 days");
        assertFalse(Date.isValidDate(31, 11, 2022), "November should not have more than 30 days");
    }



    @Test
    @DisplayName("test isLeapYear 1")
    public void testIsLeapYear() {
        assertFalse(Date.isLeapYear(2021), "Leap years should be multiple of 4");
        assertTrue(Date.isLeapYear(2000), "Leap years could be multiple of 400, 100, and 4");
    }

    @Test
    @DisplayName("test isLeapYear 2")
    public void testIsLeapYear2() {
        assertFalse(Date.isLeapYear(1900), "Leap years should not be multiple of 4 and 100");
    }

    @Test
    @DisplayName("test isLeapYear 3")
    public void testIsLeapYear3() {
        assertTrue(Date.isLeapYear(2020), "Leap years could be multiple of 4, not 100 and 400");
    }

    @Test
    @DisplayName("test maxDay 1")
    public void testFebLeapYear() {
        assertEquals(29,Date.maxDay(2,2020), "Leap years should have 29 days in February");
        assertEquals(28,Date.maxDay(2,2001), "Common years should have 28 days in February");

        assertEquals(31, Date.maxDay(5, 2018), "May should have 31 days");
        assertEquals(30, Date.maxDay(11, 2011), "October should have 31 days");
    }


    @Test
    @DisplayName("test compareTo 1")
    public void testDay1LeapYear() {
        try {
            Date date = new Date(1, 3, 2020);

            assertEquals(1, date.compareTo(new Date(25, 2, 2020)), "2020-03-01 should be posterior to 2020-02-25");
            assertEquals(-1, date.compareTo(new Date(1, 4, 2020)), "2020-03-01 should be anterior to 2020-04-01");
            assertEquals(0, date.compareTo(new Date(1, 3, 2020)), "2020-03-01 should be the same as 2020-03-01");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test compareTo 2")
    public void testJanCommonYear() {
        try {
            Date date = new Date(10, 1, 2022);

            assertEquals(1, date.compareTo(new Date(25, 12, 2020)), "2022-01-10 should be posterior to 2020-12-25");
            assertEquals(-1, date.compareTo(new Date(1, 2, 2022)), "2022-01-10 should be anterior to 2022-02-01");
            assertEquals(0, date.compareTo(new Date(10, 1, 2022)), "2022-01-10 should be the same as 2022-01-10");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test compareTo 3")
    public void testDecCommonYear() {
        try {
            Date date = new Date(31, 12, 2015);

            assertEquals(1, date.compareTo(new Date(25, 12, 2015)), "2015-12-31 should be posterior to 2015-12-25");
            assertEquals(-1, date.compareTo(new Date(12, 1, 2016)), "2015-12-31 should be anterior to 2016-01-12");
            assertEquals(0, date.compareTo(new Date(31, 12, 2015)), "2015-12-31 should be the same as 2015-12-31");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test compareTo 4")
    public void testThrowNullException() {
        assertThrows(NullPointerException.class, ()->new Date(1, 1, 2000).compareTo(null));
        assertDoesNotThrow(()->new Date(1, 1, 2000).compareTo(new Date(1, 1, 2000)));
    }

    @Test
    @DisplayName("test compareTo 5")
    public void testCommonDay() {
        try {
            Date date = new Date(20, 7, 2021);

            assertEquals(1, date.compareTo(new Date(12, 7, 2021)), "2021-07-20 should be posterior to 2021-07-12");
            assertEquals(-1, date.compareTo(new Date(28, 7, 2021)), "2021-07-20 should be anterior to 2021-07-28");
            assertEquals(0, date.compareTo(new Date(20, 7, 2021)), "2021-07-20 should be the same as 2021-07-20");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    @DisplayName("test nextDate 1")
    public void testFeb29LeapYear() {
        try {
            Date date = new Date(29, 2, 2020);
            Date nextDate = new Date(1, 3, 2020);
            assertEquals(0, date.nextDate().compareTo(nextDate), "The next date of the last day of February should be the first day of March");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test nextDate 2")
    public void testFeb28CommonYear() {
        try {
            Date date = new Date(28, 2, 2022);
            Date nextDate = new Date(1, 3, 2022);
            assertEquals(0, date.nextDate().compareTo(nextDate), "The next date of the last day of February should be the first day of March");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test nextDate 3")
    public void testEndYear() {
        try {
            Date date = new Date(31, 12, 2021);
            Date nextDate = new Date(1, 1, 2022);
            assertEquals(0, date.nextDate().compareTo(nextDate),"The next date of the last day of year should be the first day of next year");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test nextDate 4")
    public void testEndMonth1() {
        try {
            Date date = new Date(31, 3, 2021);
            Date nextDate = new Date(1, 4, 2021);
            assertEquals(0, date.nextDate().compareTo(nextDate), "The next date of the last day of month should be the first day of next month");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test nextDate 5")
    public void testEndMonth2() {
        try {
            Date date = new Date(30, 11, 2021);
            Date nextDate = new Date(1, 12, 2021);
            assertEquals(0, date.nextDate().compareTo(nextDate),"The next date of the last day of month should be the first day of next month");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test nextDate 6")
    public void testCommonDate() {
        try {
            Date date = new Date(1, 3, 2021);
            Date nextDate = new Date(2, 3, 2021);
            assertEquals(0, date.nextDate().compareTo(nextDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // new test cases for nextDate() after mutation testing
    @Test
    @DisplayName("test nextDate 7")
    public void testCommonDate2() {
        try {
            Date date = new Date(12, 6, 2021);
            Date nextDate = new Date(11, 6, 2021);
            assertNotEquals(0, nextDate.compareTo(date.nextDate()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    @DisplayName("test previousDate 1")
    public void testMar1LeapYear() {
        try {
            Date date = new Date(1, 3, 2020);
            Date preDate = new Date(29, 2, 2020);
            assertEquals(0,date.previousDate().compareTo(preDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test previousDate 2")
    public void testMar1CommonYear() {
        try {
            Date date = new Date(1, 3, 1999);
            Date preDate = new Date(28, 2, 1999);
            assertEquals(0,date.previousDate().compareTo(preDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test previousDate 3")
    public void testJan1() {
        try {
            Date date = new Date(1, 1, 2019);
            Date preDate = new Date(31, 12, 2018);
            assertEquals(0,date.previousDate().compareTo(preDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test previousDate 4")
    public void testDay1Month30() {
        try {
            Date date = new Date(1, 12, 2020);
            Date preDate = new Date(30, 11, 2020);
            assertEquals(0,date.previousDate().compareTo(preDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test previousDate 5")
    public void testDay1Month31() {
        try {
            Date date = new Date(1, 8, 2021);
            Date preDate = new Date(31, 7, 2021);
            assertEquals(0,date.previousDate().compareTo(preDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test previousDate 6")
    public void testDayMaxMonth() {
        try {
            Date date = new Date(31, 5, 2021);
            Date preDate = new Date(30, 5, 2021);
            assertEquals(0,date.previousDate().compareTo(preDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // new test cases after mutation testing
    @Test
    @DisplayName("test previousDate 7")
    public void testMonth1NotDay1() {
        try {
            Date date = new Date(3, 1, 2021);
            Date preDate = new Date(2, 1, 2021);
            assertEquals(0, preDate.compareTo(date.previousDate()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test previousDate 7")
    public void testpredayplus1() {
        try {
            Date date = new Date(20, 7, 2021);
            Date preDate = new Date(21, 7, 2021);
            assertNotEquals(0, preDate.compareTo(date.previousDate()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



