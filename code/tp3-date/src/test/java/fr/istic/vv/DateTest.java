package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    // Test constructor with valid date
    @Test
    void testValidDateConstructor1() {
        Date date = new Date(1, 1, 2023);
        assertEquals(1, date.getDay());
    }
    @Test
    void testValidDateConstructor2() {
        Date date = new Date(1, 1, 2023);
        assertEquals(1, date.getMonth());
    }

    @Test
    void testValidDateConstructor3() {
        Date date = new Date(1, 1, 2023);
        assertEquals(2023, date.getYear());
    }

    // Test isValidDate method
    @Test
    void testIsValidDate1() {
        assertTrue(Date.isValidDate(1, 1, 2023));
    }

    @Test
    void testIsValidDate2() {
        assertFalse(Date.isValidDate(0, 13, 2023));
    }

    @Test
    void testIsLeapYear1() {
        assertTrue(Date.isLeapYear(2020));
    }
    /*@Test
    void testIsLeapYear2() {
        assertFalse(Date.isLeapYear(2021));
    }*/

    @Test
    void testIsLeapYear3() {
        assertTrue(Date.isLeapYear(2000));
    }

    @Test
    void testNotIsLeapYear1() {
        assertTrue(Date.notIsLeapYear(2000));
    }

    @Test
    void testNotIsLeapYear2() {
        assertTrue(Date.notIsLeapYear(2020));
    }

    /*@Test
    void testIsLeapYear4() {
        assertTrue(Date.isLeapYear(2000));
    }*/

    // Test nextDate method
    @Test
    void testNextDate1() {
        Date date = new Date(30, 12, 2023);
        Date next = date.nextDate();
        assertEquals(31, next.getDay());
    }

    @Test
    void testNextDate2() {
        Date date = new Date(30, 12, 2023);
        Date next = date.nextDate();
        assertEquals(12, next.getMonth());
    }

    @Test
    void testNextDate3() {
        Date date = new Date(30, 12, 2023);
        Date next = date.nextDate();
        assertEquals(2023, next.getYear());
    }

    // Test previousDate method
    @Test
    void testPreviousDate1() {
        Date date = new Date(1, 1, 2023);
        Date previous = date.previousDate();
        assertEquals(31, previous.getDay());
    }

    @Test
    void testPreviousDate2() {
        Date date = new Date(1, 1, 2023);
        Date previous = date.previousDate();
        assertEquals(12, previous.getMonth());
    }

    @Test
    void testPreviousDate3() {
        Date date = new Date(1, 1, 2023);
        Date previous = date.previousDate();
        assertEquals(2022, previous.getYear());
    }

    // Test compareTo method
    @Test
    void testCompareTo1() {
        Date date1 = new Date(1, 1, 2023);
        Date date2 = new Date(1, 1, 2024);
        assertEquals(-1, date1.compareTo(date2));
    }

    @Test
    void testCompareTo2() {
        Date date1 = new Date(1, 1, 2023);
        assertEquals(0, date1.compareTo(date1));
    }

    @Test
    void testCompareTo3() {
        Date date1 = new Date(1, 1, 2023);
        assertThrows(NullPointerException.class, () -> date1.compareTo(null));
    }

    @Test
    void testCompareTo4() {
        Date date1 = new Date(1, 1, 2024);
        Date date2 = new Date(1, 1, 2023);
        assertEquals(1, date1.compareTo(date2));
    }

    //Tests after the coverage

    @Test
    void testInvalidDateConstructor1() {
        assertThrows(IllegalArgumentException.class, () -> new Date(32, 13, 2023));
    }

    @Test
    void testInvalidDateConstructor2() {
        assertThrows(IllegalArgumentException.class, () -> new Date(29, 2, 2021));
    }

    @Test
    void testInvalidDateConstructor3() {
        assertThrows(IllegalArgumentException.class, () -> new Date(-5, 4, 2022));
    }

    @Test
    void testInvalidDateConstructor4() {
        assertThrows(IllegalArgumentException.class, () -> new Date(10, -2, 2022));
    }

    @Test
    void testInvalidDateConstructor5() {
        assertThrows(IllegalArgumentException.class, () -> new Date(31, 6, -1));
    }

    @Test
    void testNextDate4() {
        Date date = new Date(30, 4, 2022);
        Date nextDate = date.nextDate();
        assertEquals(1, nextDate.getDay());
    }

    @Test
    void testNextDate5() {
        Date date = new Date(30, 4, 2022);
        Date nextDate = date.nextDate();
        assertEquals(5, nextDate.getMonth());
    }

    @Test
    void testNextDate6() {
        Date date = new Date(30, 4, 2022);
        Date nextDate = date.nextDate();
        assertEquals(2022, nextDate.getYear());
    }

    @Test
    void testNextDate7() {
        Date date = new Date(28, 2, 2020);
        Date nextDate = date.nextDate();
        assertEquals(29, nextDate.getDay());
    }

    @Test
    void testNextDate8() {
        Date date = new Date(28, 2, 2020);
        Date nextDate = date.nextDate();
        assertEquals(2, nextDate.getMonth());
    }

    @Test
    void testNextDate9() {
        Date date = new Date(28, 2, 2020);
        Date nextDate = date.nextDate();
        assertEquals(2020, nextDate.getYear());
    }

    @Test
    void testNextDate10() {
        Date date = new Date(28, 2, 2021);
        Date nextDate = date.nextDate();
        assertEquals(1, nextDate.getDay());
    }

    @Test
    void testNextDate11() {
        Date date = new Date(28, 2, 2021);
        Date nextDate = date.nextDate();
        assertEquals(3, nextDate.getMonth());
    }

    @Test
    void testNextDate12() {
        Date date = new Date(28, 2, 2021);
        Date nextDate = date.nextDate();
        assertEquals(2021, nextDate.getYear());
    }

    @Test
    void testNextDate13() {
        Date date = new Date(31, 12, 2021);
        Date nextDate = date.nextDate();
        assertEquals(1, nextDate.getDay());
    }

    @Test
    void testNextDate14() {
        Date date = new Date(31, 12, 2021);
        Date nextDate = date.nextDate();
        assertEquals(1, nextDate.getMonth());
    }

    @Test
    void testNextDate15() {
        Date date = new Date(31, 12, 2021);
        Date nextDate = date.nextDate();
        assertEquals(2022, nextDate.getYear());
    }

    @Test
    void testNextDate16() {
        Date date = new Date(28, 2, 2021);
        Date nextDate = date.nextDate();
        assertEquals(1, nextDate.getDay());
    }

    @Test
    void testNextDate17() {
        Date date = new Date(28, 2, 2021);
        Date nextDate = date.nextDate();
        assertEquals(3, nextDate.getMonth());
    }

    @Test
    void testNextDate18() {
        Date date = new Date(28, 2, 2021);
        Date nextDate = date.nextDate();
        assertEquals(2021, nextDate.getYear());
    }

    @Test
    void testPreviousDate4() {
        Date date = new Date(1, 6, 2023);
        Date previousDate = date.previousDate();
        assertEquals(31, previousDate.getDay());
    }

    @Test
    void testPreviousDate5() {
        Date date = new Date(1, 6, 2023);
        Date previousDate = date.previousDate();
        assertEquals(5, previousDate.getMonth());
    }

    @Test
    void testPreviousDate6() {
        Date date = new Date(1, 6, 2023);
        Date previousDate = date.previousDate();
        assertEquals(2023, previousDate.getYear());
    }

    @Test
    void TestCompareTo5(){
        Date date1 = new Date(1, 6, 2023);
        Date date2 = new Date(1, 7, 2023);
        assertEquals(-1, date1.compareTo(date2));
    }

    @Test
    void previousDate7(){
        Date date = new Date(1, 5, 2023);
        Date previousDate = date.previousDate();
        assertEquals(2023, previousDate.getYear());
    }

    @Test
    void previousDate8(){
        Date date = new Date(1, 5, 2023);
        Date previousDate = date.previousDate();
        assertEquals(2023, previousDate.getYear());
    }

    @Test
    void previousDate9(){
        Date date = new Date(1, 3, 2023);
        Date previousDate = date.previousDate();
        assertEquals(2023, previousDate.getYear());
    }

    // 3rd question

    @Test
    void testIsValidDateAprilHas30Days() {
        assertFalse(Date.isValidDate(31, 4, 2023));
    }

    @Test
    void testIsValidDateFebruaryNonLeapYear() {
        assertFalse(Date.isValidDate(29, 2, 2021));
    }

    @Test
    void testIsValidDateFebruaryLeapYear1() {
        assertFalse(Date.isValidDate(30, 2, 2020));
    }

    @Test
    void testIsValidDateJanuaryHas31Days() {
        assertTrue(Date.isValidDate(31, 1, 2023));
    }

    @Test
    void testIsValidDateJuneHas30Days() {
        assertTrue(Date.isValidDate(30, 6, 2023));
    }

    @Test
    void testIsValidDateFebruaryLeapYear2() {
        assertTrue(Date.isValidDate(28, 2, 2020));
    }


}
