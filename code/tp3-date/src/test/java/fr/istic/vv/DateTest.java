package fr.istic.vv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class DateTest {

    // pour isLeapYear
    @Test
    void testLeapNegativeYear() {
        // Partition: < 0
        assertFalse(Date.isLeapYear(-1), "-1 should not be a leap year");
        assertFalse(Date.isLeapYear(-400), "-400 should not be a leap year even though can be divided by 400");
    }

    @Test
    void testLeapYearZero() {
        // Partition: 0
        assertFalse(Date.isLeapYear(0), "Year 0 is not considered a leap year");
    }

    @Test
    void testLeapYear() {
        // Partition: année bissextile
        assertTrue(Date.isLeapYear(2000), "2000 is a leap year (divisible by 400)");
        assertTrue(Date.isLeapYear(2024), "2024 is a leap year (divisible by 4, not 100)");
    }

    @Test
    void testLeapNormalYear() {
        // Partition: année normale
        assertFalse(Date.isLeapYear(1900), "1900 is not a leap year (divisible by 100, not 400)");
        assertFalse(Date.isLeapYear(2023), "2023 is not a leap year (not divisible by 4)");
    }

    // pour isValideDate
    @Test
    void testValidDateNegativeYear() {
        // Partition: year < 0
        assertFalse(Date.isValidDate(15, 5, -1), "Year -1 should be invalid");
    }

    @Test
    void testValidDateYearZero() {
        // Partition: year == 0
        assertFalse(Date.isValidDate(15, 5, 0), "Year 0 should be invalid");
    }

    @Test
    void testValidDateYearOne() {
        assertTrue(Date.isValidDate(15, 5, 1), "Year -1 should be invalid");

    }

    @Test
    void testValidDateLeapYear() {
        // Partition: année bissextile
        assertTrue(Date.isValidDate(29, 2, 2024), "29th February 2024 should be valid (leap year)");
        assertFalse(Date.isValidDate(30, 2, 2024), "30th February 2024 should be invalid (leap year)");
    }

    @Test
    void testValidDateNormalYear() {
        // Partition: année normale
        assertTrue(Date.isValidDate(28, 2, 2023), "28th February 2023 should be valid (normal year)");
        assertFalse(Date.isValidDate(29, 2, 2023), "29th February 2023 should be invalid (normal year)");
    }

    @Test
    void testValidDateInvalidMonth() {
        // Partition: month < 0, month == 0, month > 12
        assertFalse(Date.isValidDate(15, -1, 2023), "Month -1 should be invalid");
        assertFalse(Date.isValidDate(15, 0, 2023), "Month 0 should be invalid");
        assertFalse(Date.isValidDate(15, 13, 2023), "Month 13 should be invalid");
    }

    @Test
    void testValidDateValidMonths() {
        // Partition: {1, 3, 5, 7, 8, 10, 12}
        assertTrue(Date.isValidDate(31, 1, 2023), "31st January 2023 should be valid");
        assertFalse(Date.isValidDate(32, 1, 2023), "32nd January 2023 should be invalid");

        // Partition: {4, 6, 9, 11}
        assertTrue(Date.isValidDate(30, 4, 2023), "30th April 2023 should be valid");
        assertFalse(Date.isValidDate(31, 4, 2023), "31st April 2023 should be invalid");
    }

    @Test
    void testValidDateFebruary() {
        // Partition: month == 2
        assertTrue(Date.isValidDate(28, 2, 2023), "28th February 2023 should be valid");
        assertTrue(Date.isValidDate(29, 2, 2024), "29th February 2024 should be valid (leap year)");
        assertFalse(Date.isValidDate(30, 2, 2023), "30th February 2023 should be invalid");
    }

    @Test
    void testValidDateInvalidDay() {
        // Partition: day < 0, day == 0, day > max(month, year)
        assertFalse(Date.isValidDate(-1, 1, 2023), "Day -1 should be invalid");
        assertFalse(Date.isValidDate(0, 1, 2023), "Day 0 should be invalid");
        assertFalse(Date.isValidDate(32, 1, 2023), "Day 32 should be invalid for January");
    }

    // pour nextDate

    @Test
    void testNextDateWithNegativeYear() {
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 1, -1).nextDate(),
                "Year -1 should throw an exception");
    }

    @Test
    void testNextDateWithYearZero() {
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 1, 0).nextDate(),
                "Year 0 should throw an exception");
    }

    @Test
    void testNextDateInLeapYear() {
        Date feb29LeapYear = new Date(29, 2, 2024);
        assertEquals(new Date(1, 3, 2024), feb29LeapYear.nextDate(),
                "Next day of 29th Feb 2024 should be 1st Mar 2024");

        Date feb28LeapYear = new Date(28, 2, 2024);
        assertEquals(new Date(29, 2, 2024), feb28LeapYear.nextDate(),
                "Next day of 28th Feb 2024 should be 29th Feb 2024");
    }

    @Test
    void testNextDateInNormalYear() {
        Date feb28NormalYear = new Date(28, 2, 2023);
        assertEquals(new Date(1, 3, 2023), feb28NormalYear.nextDate(),
                "Next day of 28th Feb 2023 should be 1st Mar 2023");
    }

    @Test
    void testNextDateWithInvalidMonth() {
        assertThrows(IllegalArgumentException.class, () -> new Date(1, -1,
                2023).nextDate(),
                "Month -1 should throw an exception");
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 0,
                2023).nextDate(),
                "Month 0 should throw an exception");
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 13,
                2023).nextDate(),
                "Month 13 should throw an exception");
    }

    @Test
    void testNextDateEdgeCases() {
        // End of the year
        Date dec31 = new Date(31, 12, 2023);
        assertEquals(new Date(1, 1, 2024), dec31.nextDate(), "Next day of 31st Dec2023 should be 1st Jan 2024");

        // End of a month
        Date nov30 = new Date(30, 11, 2023);
        assertEquals(new Date(1, 12, 2023), nov30.nextDate(), "Next day of 30th Apr2023 should be 1st May 2023");
    }

    @Test
    void testNextDateInvalidDay() {
        assertThrows(IllegalArgumentException.class, () -> new Date(32, 1, 2023).nextDate(),
                "Day 32 should throw an exception for January");
    }

    // pour previousDate
    @Test
    void testPreviousDateWithNegativeYear() {
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 1,
                -1).previousDate(),
                "Year -1 should throw an exception");
    }

    @Test
    void testPreviousDateWithYearZero() {
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 1,
                0).previousDate(),
                "Year 0 should throw an exception");
    }

    @Test
    void testPreviousDateInLeapYear() {
        Date mar1LeapYear = new Date(1, 3, 2024);
        assertEquals(new Date(29, 2, 2024), mar1LeapYear.previousDate(),
                "Previous day of 1st Mar 2024 should be 29th Feb 2024");

    }

    @Test
    void testPreviousDateInNormalYear() {
        Date mar1NormalYear = new Date(1, 3, 2023);
        assertEquals(new Date(28, 2, 2023), mar1NormalYear.previousDate(),
                "Previous day of 1st Mar 2023 should be 28th Feb 2023");

    }

    @Test
    void testPreviousDateInJanuary() {
        Date feb1 = new Date(1, 2, 2023);
        assertEquals(new Date(31, 1, 2023), feb1.previousDate(),
                "Previous day of 1st Feb 2023 should be 31st Jan 2023");

    }

    @Test
    void testPreviousYearInMonth() {
        Date mar1NormalYear = new Date(2, 3, 2023);
        assertEquals(new Date(1, 3, 2023), mar1NormalYear.previousDate(),
                "Previous day of 2nd Mar 2023 should be 1st Mar 2023");

    }

    @Test
    void testPreviousDateWithInvalidMonth() {
        assertThrows(IllegalArgumentException.class, () -> new Date(1, -1,
                2023).previousDate(),
                "Month -1 should throw an exception");
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 0,
                2023).previousDate(),
                "Month 0 should throw an exception");
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 13,
                2023).previousDate(),
                "Month 13 should throw an exception");
    }

    @Test
    void testPreviousDateEdgeCases() {
        // Start of the year
        Date jan1 = new Date(1, 1, 2024);
        assertEquals(new Date(31, 12, 2023), jan1.previousDate(),
                "Previous day of 1st Jan 2024 should be 31st Dec 2023");

        // Start of a month
        Date may1 = new Date(1, 5, 2023);
        assertEquals(new Date(30, 4, 2023), may1.previousDate(),
                "Previous day of 1st May 2023 should be 30th Apr 2023");
    }

    @Test
    void testPreviousDateInvalidDay() {
        assertThrows(IllegalArgumentException.class, () -> new Date(-1, 1, 2023).previousDate(),
                "Day -1 should throw an exception");
        assertThrows(IllegalArgumentException.class, () -> new Date(0, 1, 2023).previousDate(),
                "Day 0 should throw an exception");
    }

    // pour compareTo

    @Test
    void testCompareToNullDateShouldThrowNullPointerException() {
        Date date = new Date(1, 1, 2023);
        assertThrows(NullPointerException.class, () -> date.compareTo(null));
    }

    @Test
    void testCompareToYearGreaterShouldReturnPositive() {
        Date date1 = new Date(1, 1, 2024);
        Date date2 = new Date(1, 1, 2023);
        assertTrue(date1.compareTo(date2) > 0);
    }

    @Test
    void testCompareToYearEqualShouldReturnZero() {
        Date date1 = new Date(1, 1, 2023);
        Date date2 = new Date(1, 1, 2023);
        assertEquals(0, date1.compareTo(date2));
    }

    @Test
    void testCompareToYearLessShouldReturnNegative() {
        Date date1 = new Date(1, 1, 2022);
        Date date2 = new Date(1, 1, 2023);
        assertTrue(date1.compareTo(date2) < 0);
    }

    @Test
    void testCompareToMonthGreaterShouldReturnPositive() {
        Date date1 = new Date(1, 2, 2023);
        Date date2 = new Date(1, 1, 2023);
        assertTrue(date1.compareTo(date2) > 0);
    }

    @Test
    void testCompareToMonthEqualShouldReturnZero() {
        Date date1 = new Date(1, 1, 2023);
        Date date2 = new Date(1, 1, 2023);
        assertEquals(0, date1.compareTo(date2));
    }

    @Test
    void testCompareToMonthLessShouldReturnNegative() {
        Date date1 = new Date(1, 1, 2023);
        Date date2 = new Date(1, 2, 2023);
        assertTrue(date1.compareTo(date2) < 0);
    }

    @Test
    void testCompareToDayGreaterShouldReturnPositive() {
        Date date1 = new Date(2, 1, 2023);
        Date date2 = new Date(1, 1, 2023);
        assertTrue(date1.compareTo(date2) > 0);
    }

    @Test
    void testCompareToDayEqualShouldReturnZero() {
        Date date1 = new Date(1, 1, 2023);
        Date date2 = new Date(1, 1, 2023);
        assertEquals(0, date1.compareTo(date2));
    }

    @Test
    void testCompareToDayLessShouldReturnNegative() {
        Date date1 = new Date(1, 1, 2023);
        Date date2 = new Date(2, 1, 2023);
        assertTrue(date1.compareTo(date2) < 0);
    }

    // pour numberOfDayInMounth

    @Test
    void testYearOutOfBound() {
        // year <0, =0,
        assertThrows(IllegalArgumentException.class, () -> Date.numberOfDayInMonth(-2, 3));
        assertThrows(IllegalArgumentException.class, () -> Date.numberOfDayInMonth(0, 3));
    }

    @Test
    void testYearBissextileMonthFebruary() {
        assertEquals(29, Date.numberOfDayInMonth(2020, 2)); // 2020 is a leap year
    }

    @Test
    void testYearBissextileMonthInLongMonths() {
        assertEquals(31, Date.numberOfDayInMonth(2020, 1));
        assertEquals(31, Date.numberOfDayInMonth(2020, 7));
    }

    @Test
    void testYearBissextileMonthInShortMonths() {
        assertEquals(30, Date.numberOfDayInMonth(2020, 4));
        assertEquals(30, Date.numberOfDayInMonth(2020, 9));
    }

    @Test
    void testYearNormalMonthFebruary() {
        assertEquals(28, Date.numberOfDayInMonth(2021, 2)); // 2021 is not a leap year
    }

    @Test
    void testYearNormalMonthInLongMonths() {
        assertEquals(31, Date.numberOfDayInMonth(2021, 1));
        assertEquals(31, Date.numberOfDayInMonth(2021, 8));
    }

    @Test
    void testYearNormalMonthInShortMonths() {
        assertEquals(30, Date.numberOfDayInMonth(2021, 6));
        assertEquals(30, Date.numberOfDayInMonth(2021, 11));
    }

    @Test
    void testMonthOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> Date.numberOfDayInMonth(2021, 13));
    }

    @Test
    void testMonthNegative() {
        assertThrows(IllegalArgumentException.class, () -> Date.numberOfDayInMonth(2021, -5));
    }

    // pour hashcode
    @Test
    void testHashcode() {
        Date date1 = new Date(14, 4, 2023);
        assertEquals(113401288, date1.hashCode());
    }

    // pour equals
    @Test
    void testEqualsSameObj() {
        Date date1 = new Date(14, 4, 2023);
        Date date2 = date1;
        assertTrue(date1.equals(date2));

    }

    @Test
    void testEqualsNullObject() {
        Date date1 = new Date(14, 4, 2023);
        assertFalse(date1.equals(null));

    }

    @Test
    void testEqualsNotSameClass() {
        Date date1 = new Date(14, 4, 2023);
        String date2 = "toto";
        assertFalse(date1.equals(date2));
    }

    @Test
    void testEqualsNotSameDay() {
        Date date1 = new Date(15, 4, 2023);
        Date date2 = new Date(14, 4, 2023);
        assertFalse(date1.equals(date2));
    }

    @Test
    void testEqualsNotSameMonth() {
        Date date1 = new Date(14, 5, 2023);
        Date date2 = new Date(14, 4, 2023);
        assertFalse(date1.equals(date2));
    }

    @Test
    void testEqualsNotSameYear() {
        Date date1 = new Date(14, 4, 2024);
        Date date2 = new Date(14, 4, 2023);
        assertFalse(date1.equals(date2));
    }

}