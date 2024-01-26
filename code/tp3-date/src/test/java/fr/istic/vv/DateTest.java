package fr.istic.vv;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    // isValidDate and isLeapYear Tests

    @Test
    void testIsValidDate() {
        assertFalse(Date.isValidDate(1, 1, -1), "Year less than 1 should be invalid");
        assertTrue(Date.isValidDate(1, 1, 2022), "Year 1 or greater should be valid");

        assertFalse(Date.isValidDate(1, 0, 2022), "Month less than 1 should be invalid");
        assertTrue(Date.isValidDate(1, 1, 2022), "Month between 1 and 12 should be valid");
        assertFalse(Date.isValidDate(1, 13, 2022), "Month greater than 12 should be invalid");

        assertFalse(Date.isValidDate(0, 1, 2020), "Day less than 1 should be invalid");
        assertTrue(Date.isValidDate(1, 1, 2022), "Day within the range should be valid");
        assertFalse(Date.isValidDate(32, 1, 2022), "Day greater than days in associated month should be invalid");
        assertFalse(Date.isValidDate(30, 2, 2022), "Day greater than days in associated month should be invalid");

        assertTrue(Date.isValidDate(29, 2, 2020), "Leap date should be valid");
        assertFalse(Date.isValidDate(29, 2, 2021), "Non leap date should be invalid");
    }

    @Test
    void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2020), "2020 should be a leap year");
        assertFalse(Date.isLeapYear(2022), "2022 should not be a leap year");
    }

    // nextDate and previousDate Tests

    @Test
    void testNextDate() {
        Date date1 = new Date(1, 1, 2022);
        assertEquals("2022-01-02", date1.nextDate().toString(), "Next date within the same year/month test failed");

        Date date2 = new Date(31, 1, 2023);
        assertEquals("2023-02-01", date2.nextDate().toString(), "Next date transitioning to the next month test failed");

        Date date3 = new Date(31, 12, 2022);
        assertEquals("2023-01-01", date3.nextDate().toString(), "Next date transitioning to the next year test failed");
    }

    @Test
    void testPreviousDate() {
        Date date1 = new Date(2, 1, 2023);
        assertEquals("2023-01-01", date1.previousDate().toString(), "Previous date within the same year/month test failed");

        Date date2 = new Date(1, 2, 2023);
        assertEquals("2023-01-31", date2.previousDate().toString(), "Previous date transitioning to the previous month test failed");

        Date date3 = new Date(1, 1, 2023);
        assertEquals("2022-12-31", date3.previousDate().toString(), "Previous date transitioning to the previous year test failed");
    }

    // compareTo Tests

    @Test
    void testCompareTo() {
        Date date1 = new Date(1, 1, 2022);
        Date date2 = new Date(1, 1, 2023);
        assertTrue(date1.compareTo(date2) < 0, "CompareTo test - The other date is posterior failed for year");
        assertFalse(date1.compareTo(date2) >= 0, "CompareTo test - The other date is posterior failed for year");
        assertTrue(date2.compareTo(date1) > 0, "CompareTo test - The other date is anterior failed for year");
        assertFalse(date2.compareTo(date1) <= 0, "CompareTo test - The other date is anterior failed for year");

        Date date3 = new Date(1, 1, 2023);
        Date date4 = new Date(1, 2, 2023);
        assertTrue(date3.compareTo(date4) < 0, "CompareTo test - The other date is posterior failed for month");
        assertFalse(date3.compareTo(date4) >= 0, "CompareTo test - The other date is posterior failed for month");
        assertTrue(date4.compareTo(date3) > 0, "CompareTo test - The other date is anterior failed for month");
        assertFalse(date4.compareTo(date3) <= 0, "CompareTo test - The other date is anterior failed for month");

        Date date6 = new Date(1, 1, 2023);
        Date date7 = new Date(2, 1, 2023);
        assertTrue(date6.compareTo(date7) < 0, "CompareTo test - The other date is posterior failed for month");
        assertFalse(date6.compareTo(date7) >= 0, "CompareTo test - The other date is posterior failed for month");
        assertTrue(date7.compareTo(date6) > 0, "CompareTo test - The other date is anterior failed for month");
        assertFalse(date7.compareTo(date6) <= 0, "CompareTo test - The other date is anterior failed for month");

        Date date8 = new Date(1, 1, 2022);
        assertEquals(0, date1.compareTo(date8), "CompareTo test - The other date is the same failed");

        assertThrows(NullPointerException.class, () -> date1.compareTo(null), "CompareTo test - Null date failed");
    }
}