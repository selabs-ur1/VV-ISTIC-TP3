package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    private static final int DAY_VALID = 10;
    private static final int DAY_VALID_NEXT = 11;
    private static final int DAY_VALID_PREVIOUS = 9;
    private static final int DAY_INVALID_ZERO = 0;
    private static final int DAY_MAX_30 = 30;
    private static final int DAY_MAX_31 = 31;
    private static final int DAY_MIN = 1;
    private static final int DAY_FEBRUARY = 28;
    private static final int DAY_MAX_FEBRUARY = 29;
    private static final int MONTH_VALID_11 = 11;
    private static final int MONTH_VALID_12 = 12;

    private static final int MONTH_VALID_1 = 1;
    private static final int MONTH_VALID_2 = 2;
    private static final int MONTH_VALID_3 = 3;
    private static final int MONTH_MIN = 1;
    private static final int MONTH_INVALID_ZERO = 0;
    private static final int MONTH_INVALID_GREATER = 18;
    private static final int YEAR_VALID = 2023;
    private static final int YEAR_LEAP = 2024;
    private static final int YEAR_INVALID = -1;

    private static final Date DATE_VALID_5_11 = new Date(DAY_VALID, MONTH_VALID_11, YEAR_VALID);
    private static final Date DATE_NEXT_6_11 = new Date(DAY_VALID_NEXT, MONTH_VALID_11, YEAR_VALID);
    private static final Date DATE_PREVIOUS_4_11 = new Date(DAY_VALID_PREVIOUS, MONTH_VALID_11, YEAR_VALID);
    private static final Date DATE_NEXT_30_11 = new Date(DAY_MAX_30, MONTH_VALID_11, YEAR_VALID);
    private static final Date DATE_NEXT_31_12 = new Date(DAY_MAX_31, MONTH_VALID_12, YEAR_VALID);
    private static final Date DATE_NEXT_28_2 = new Date(DAY_FEBRUARY, MONTH_VALID_2, YEAR_LEAP);
    private static final Date DATE_NEXT_29_2 = new Date(DAY_MAX_FEBRUARY, MONTH_VALID_2, YEAR_LEAP);
    private static final Date DATE_PREVIOUS_01_3 = new Date(DAY_MIN, MONTH_VALID_3, YEAR_LEAP);
    private static final Date DATE_PREVIOUS_01_12 = new Date(DAY_MIN, MONTH_VALID_12, YEAR_VALID);
    private static final Date DATE_PREVIOUS_01_01 = new Date(DAY_MIN, MONTH_VALID_1, YEAR_LEAP);
    private static final Date DATE_VALID_5_12 = new Date(DAY_VALID, MONTH_VALID_12, YEAR_VALID);
    private static final Date DATE_VALID_5_11_24 = new Date(DAY_VALID, MONTH_VALID_11, YEAR_LEAP);

    @Test
    public void testValidDate() {
        assertTrue(Date.isValidDate(DAY_VALID, MONTH_VALID_11, YEAR_VALID));
    }

    @Test
    public void testInvalidDateDayZero() {
        assertFalse(Date.isValidDate(DAY_INVALID_ZERO, MONTH_VALID_11, YEAR_VALID));
    }

    @Test
    public void testInvalidDateDayMax() {
        assertFalse(Date.isValidDate(DAY_MAX_31, MONTH_VALID_11, YEAR_VALID));
    }

    @Test
    public void testInvalidDateFebruary() {
        assertFalse(Date.isValidDate(DAY_MAX_FEBRUARY, MONTH_VALID_2, YEAR_VALID));
    }

    @Test
    public void testValidFebruaryLeapYear() {
        assertTrue(Date.isValidDate(DAY_MAX_FEBRUARY, MONTH_VALID_2, YEAR_LEAP));
    }

    @Test
    public void testInvalidDateMonthZero() {
        assertFalse(Date.isValidDate(DAY_VALID, MONTH_INVALID_ZERO, YEAR_VALID));
    }

    @Test
    public void testInvalidDateMonthGreater() {
        assertFalse(Date.isValidDate(DAY_VALID, MONTH_INVALID_GREATER, YEAR_VALID));
    }

    @Test
    public void testInvalidDateYear() {
        assertFalse(Date.isValidDate(DAY_VALID, MONTH_VALID_11, YEAR_INVALID));
    }

    @Test
    public void testIsLeapYear() {
        assertTrue(Date.isLeapYear(YEAR_LEAP));
    }

    @Test
    public void testIsNotLeapYear() {
        assertFalse(Date.isLeapYear(YEAR_VALID));
    }

    @Test
    public void testNextDate() {
        assertEquals(DATE_NEXT_6_11, DATE_VALID_5_11.nextDate());
    }

    @Test
    public void testNextDateMaxDay() {
        assertEquals(new Date(DAY_MIN, MONTH_VALID_12, YEAR_VALID), DATE_NEXT_30_11.nextDate());
    }



    @Test
    public void testNextDateLeapYear() {
        assertEquals(new Date(DAY_MAX_FEBRUARY, MONTH_VALID_2, YEAR_LEAP), DATE_NEXT_28_2.nextDate());
    }

    @Test
    public void testNextDateMaxLeapYear() {
        assertEquals(new Date(DAY_MIN, MONTH_VALID_3, YEAR_LEAP), DATE_NEXT_29_2.nextDate());
    }

    @Test
    public void testPreviousDate() {
        assertEquals(DATE_PREVIOUS_4_11, DATE_VALID_5_11.previousDate());
    }

    @Test
    public void testPreviousDateMin() {
        assertEquals(new Date(DAY_MAX_30, MONTH_VALID_11, YEAR_VALID), DATE_PREVIOUS_01_12.previousDate());
    }

    @Test
    public void testPreviousDateMaxLeapYear() {
        assertEquals(new Date(DAY_MAX_FEBRUARY, MONTH_VALID_2, YEAR_LEAP), DATE_PREVIOUS_01_3.previousDate());
    }

    @Test
    public void testPreviousDateYear() {
        assertEquals(new Date(DAY_MAX_31, MONTH_VALID_12, YEAR_VALID), DATE_PREVIOUS_01_01.previousDate());
    }

    @Test
    public void testCompareToSame() {
        assertEquals(0, DATE_VALID_5_11.compareTo(new Date(DAY_VALID, MONTH_VALID_11, YEAR_VALID)));
    }
    @Test
    public void testNextDateMaxMonth() {
        assertEquals(new Date(DAY_MIN, MONTH_MIN, YEAR_LEAP), DATE_NEXT_31_12.nextDate());
    }
    @Test
    public void testCompareToGreater() {
        assertEquals(1, DATE_NEXT_6_11.compareTo(DATE_VALID_5_11));
    }

    @Test
    public void testCompareToSmaller() {
        assertEquals(-1, DATE_PREVIOUS_4_11.compareTo(DATE_VALID_5_11));
    }

    @Test
    public void testCompareToOtherMonth() {
        assertEquals(-1, DATE_VALID_5_11.compareTo(DATE_VALID_5_12));
    }

    @Test
    public void testCompareToOtherYear() {
        assertEquals(-1, DATE_VALID_5_11.compareTo(DATE_VALID_5_11_24));
    }

    @Test
    public void testCompareToNull() {
        assertThrows(NullPointerException.class, () -> DATE_VALID_5_11.compareTo(null));
    }

    @Test
    public void testConstructorInvalidDay() {
        assertThrows(IllegalArgumentException.class, () -> new Date(DAY_INVALID_ZERO, MONTH_VALID_11, YEAR_VALID));
    }

    @Test
    public void testConstructorInvalidYear() {
        assertThrows(IllegalArgumentException.class, () -> new Date(DAY_VALID, MONTH_VALID_11, YEAR_INVALID));
    }

    @Test
    public void testConstructorInvalidMonth() {
        assertThrows(IllegalArgumentException.class, () -> new Date(DAY_VALID, MONTH_INVALID_ZERO, YEAR_VALID));
    }

    @Test
    public void testToString() {
        assertEquals("05/11/2023", DATE_VALID_5_11.toString());
    }
}