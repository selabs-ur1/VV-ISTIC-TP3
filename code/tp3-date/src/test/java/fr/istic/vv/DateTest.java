package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    @Test
    public void testThrowExceptionOnCreate() {
        Date date = null;
        try {
            date = new Date(0, 0, 0);
        }catch (RuntimeException ignored) {}
        assertNull(date);
    }

    @Test
    public void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2024));
    }

    @Test
    public void testIsNotLeapYear() {
        assertFalse(Date.isLeapYear(2023));
    }

    @Test
    public void testIsSpecialLeapYear() {
        assertTrue(Date.isLeapYear(2000));
    }

    @Test
    public void testIsSpecialNotLeapYear() {
        assertFalse(Date.isLeapYear(1900));
    }

    @Test
    public void testIsValidDateWithInvalidDayNumberUnder() {
        assertFalse(Date.isValidDate(0, 1, 2023));
    }

    @Test
    public void testIsValidDateWithInvalidDayNumberOver() {
        assertFalse(Date.isValidDate(32, 1, 2023));
    }

    @Test
    public void testIsValidDateWithInvalidMonthNumberUnder() {
        assertFalse(Date.isValidDate(1, 0, 2023));
    }

    @Test
    public void testIsValidDateWithInvalidMonthNumberOver() {
        assertFalse(Date.isValidDate(1, 13, 2023));
    }

    @Test
    public void testIsValidDateWith31DaysMonth() {
        assertTrue(Date.isValidDate(31, 1, 2023));
    }

    @Test
    public void testIsValidDateWith31In30DaysMonth() {
        assertFalse(Date.isValidDate(31, 4, 2023));
    }

    @Test
    public void testIsValidDateWith30DaysMonth() {
        assertTrue(Date.isValidDate(30, 4, 2023));
    }

    @Test
    public void testIsValidDateWith29FebruaryNotLeap() {
        assertFalse(Date.isValidDate(29, 2, 2023));
    }

    @Test
    public void testIsValidDateWith29FebruaryLeap() {
        assertTrue(Date.isValidDate(29, 2, 2024));
    }

    @Test
    public void testCompareToTheSameDate() {
        Date date1 = new Date(1,1,2023);
        Date date2 = new Date(1,1,2023);
        assert(date1.compareTo(date2) == 0);
    }

    @Test
    public void testCompareToAnteriorYear() {
        Date date1 = new Date(1,1,2023);
        Date date2 = new Date(1,1,2022);
        assert(date1.compareTo(date2) > 0);
    }

    @Test
    public void testCompareToAnteriorMonth() {
        Date date1 = new Date(1,2,2023);
        Date date2 = new Date(1,1,2023);
        assert(date1.compareTo(date2) > 0);
    }

    @Test
    public void testCompareToAnteriorDay() {
        Date date1 = new Date(2,1,2023);
        Date date2 = new Date(1,1,2023);
        assert(date1.compareTo(date2) > 0);
    }

    @Test
    public void testCompareToPosteriorYear() {
        Date date1 = new Date(1,1,2022);
        Date date2 = new Date(1,1,2023);
        assert(date1.compareTo(date2) < 0);
    }

    @Test
    public void testCompareToPosteriorMonth() {
        Date date1 = new Date(1,1,2023);
        Date date2 = new Date(1,2,2023);
        assert(date1.compareTo(date2) < 0);
    }

    @Test
    public void testCompareToPosteriorDay() {
        Date date1 = new Date(1,1,2023);
        Date date2 = new Date(2,1,2023);
        assert(date1.compareTo(date2) < 0);
    }

    @Test
    public void testNextDate() {
        Date date1 = new Date(1,1,2023);
        Date date2 = new Date(2,1,2023);
        assert(date2.compareTo(date1.nextDate()) == 0);
    }

    @Test
    public void testNextDateLastDayOfTheMonth() {
        Date date1 = new Date(31,1,2023);
        Date date2 = new Date(1,2,2023);
        assert(date2.compareTo(date1.nextDate()) == 0);
    }

    @Test
    public void testNextDate29FebruaryNotLeap() {
        Date date1 = new Date(28,2,2023);
        Date date2 = new Date(1,3,2023);
        assert(date2.compareTo(date1.nextDate()) == 0);
    }

    @Test
    public void testNextDate29FebruaryLeap() {
        Date date1 = new Date(28,2,2024);
        Date date2 = new Date(29,2,2024);
        assert(date2.compareTo(date1.nextDate()) == 0);
    }

    @Test
    public void testNextDateLastDayOfTheYear() {
        Date date1 = new Date(31,12,2023);
        Date date2 = new Date(1,1,2024);
        assert(date2.compareTo(date1.nextDate()) == 0);
    }

    @Test
    public void testPreviousDate() {
        Date date1 = new Date(2,1,2023);
        Date date2 = new Date(1,1,2023);
        assert(date2.compareTo(date1.previousDate()) == 0);
    }

    @Test
    public void testPreviousDateLastDayOfTheMonth() {
        Date date1 = new Date(1,2,2023);
        Date date2 = new Date(31,1,2023);
        assert(date2.compareTo(date1.previousDate()) == 0);
    }

    @Test
    public void testPreviousDate28FebruaryNotLeap() {
        Date date1 = new Date(1,3,2023);
        Date date2 = new Date(28,2,2023);
        assert(date2.compareTo(date1.previousDate()) == 0);
    }

    @Test
    public void testPreviousDate28FebruaryLeap() {
        Date date1 = new Date(1,3,2024);
        Date date2 = new Date(29,2,2024);
        assert(date2.compareTo(date1.previousDate()) == 0);
    }

    @Test
    public void testPreviousDateLastDayOfTheYear() {
        Date date1 = new Date(1,1,2024);
        Date date2 = new Date(31,12,2023);
        assert(date2.compareTo(date1.previousDate()) == 0);
    }

    @Test
    public void testGetters() {
        Date date = new Date(1,1,1);
        assertTrue(date.getDay() == date.getMonth() && date.getDay() == date.getYear());
    }

    @Test
    public void testPreviousDateToA30DaysMonth() {
        Date date1 = new Date(1,5,2023);
        Date date2 = new Date(30,4,2023);
        assert(date2.compareTo(date1.previousDate()) == 0);
    }

    @Test
    public void testNextDateWithIntegerAddition() {
        Date date = new Date(30, 12, 2023);
        Date nextDate = date.nextDate();
        assertEquals(31, nextDate.getDay());
    }

    @Test
    public void testNextDateWithIncrementChange() {
        Date date = new Date(31, 12, 2023);
        Date nextDate = date.nextDate();
        assertEquals(1, nextDate.getDay());
        assertEquals(1, nextDate.getMonth());
        assertEquals(2024, nextDate.getYear());
    }

    @Test
    public void testNextDateNotNull() {
        Date date = new Date(30, 12, 2023);
        assertNotNull(date.nextDate());
    }

    @Test
    public void testPreviousDateWithIntegerSubtraction() {
        Date date = new Date(2, 1, 2023);
        Date prevDate = date.previousDate();
        assertEquals(1, prevDate.getDay());
    }

    @Test
    public void testPreviousDateWithIncrementChange() {
        Date date = new Date(1, 1, 2023);
        Date prevDate = date.previousDate();
        assertEquals(31, prevDate.getDay());
        assertEquals(12, prevDate.getMonth());
        assertEquals(2022, prevDate.getYear());
    }

    @Test
    public void testPreviousDateNotNull() {
        Date date = new Date(2, 1, 2023);
        assertNotNull(date.previousDate());
    }

    @Test
    public void testCompareToWithZeroReturnValue() {
        Date date1 = new Date(1, 1, 2023);
        Date date2 = new Date(1, 1, 2023);
        assertEquals(0, date1.compareTo(date2));
    }

    @Test
    public void testCompareToMutation() {
        Date date1 = new Date(1, 1, 2023);
        Date date2 = new Date(2, 2, 2024);
        assertTrue(date1.compareTo(date2) != 0);
    }

    @Test
    public void testPreviousDateNegatedToA31DaysMonth() {
        Date date1 = new Date(1,5,2023);
        Date date2 = new Date(1,4,2023);
        assert(date2.compareTo(date1.previousDate()) < 0);
    }
}