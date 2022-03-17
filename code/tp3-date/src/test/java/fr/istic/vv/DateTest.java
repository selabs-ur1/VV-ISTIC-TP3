package fr.istic.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    /** Test Date constructor with correct inputs.
     * Init : create Date with correct day, month and year.
     * Expected : day, month and year must be equal to the inputs */
    @Test
    @DisplayName("Test Date constructor with a correct date")
    public void testCorrectDate() throws DateTimeException {
        Date testDate = new Date(10,3,2022);
        assertEquals(10, testDate.getDay());
        assertEquals(3, testDate.getMonth());
        assertEquals(2022, testDate.getYear());
    }

    /** Test Date constructor with wrongs inputs.
     * Init : create Date with wrong day.
     * Expected : wrongs inputs must throw a DateTimeException */
    @ParameterizedTest
    @CsvSource({"32,03,2022", "00,03,2022", "-01,03,2022"})
    @DisplayName("Test Date constructor with a wrong day date")
    void testWrongDateDay(String day, String month, String year) {
        assertThrows(DateTimeException.class,() -> new Date(Integer.parseInt(day),Integer.parseInt(month),
                Integer.parseInt(year)), day + "/" + month + "/" + year);
    }

    /** Test Date constructor with wrongs inputs.
     * Init : create Date with wrong day.
     * Expected : wrongs inputs must throw a DateTimeException */
    @Test
    @DisplayName("Test Date constructor with a wrong month date")
    public void testWrongDateMonth() throws DateTimeException {
        assertThrows(DateTimeException.class,() -> new Date(25,12,2022));
    }

    /** Test Date constructor with wrong input.
     * Init : create Date with wrong day.
     * Expected : wrongs inputs must throw a DateTimeException */
    @Test
    @DisplayName("Test Date constructor with a wrong negative day")
    public void testWrongDateNegativeDay() throws DateTimeException {
        assertThrows(DateTimeException.class,() -> new Date(-20,3,2022));
    }

    /** Test Date constructor with wrong input.
     * Init : create Date with wrong month.
     * Expected : wrong input must throw a DateTimeException */
    @Test
    @DisplayName("Test Date constructor with a wrong negative month")
    public void testWrongDateNegativeMonth() throws DateTimeException {
        assertThrows(DateTimeException.class,() -> new Date(25,-2,2022));
    }

    /** Test isValidDate function with correct input.
     * Expected : isValidDate return True */
    @Test
    @DisplayName("Test isValidDate function with correct dates")
    public void testIsValidDate(){
        assertTrue(Date.isValidDate(12,5,1999));
    }

    /** Test isValidDate function with correct input.
     * Expected : isValidDate return True */
    @ParameterizedTest
    @CsvSource({"01,9,2030", "31,9,2018", "01,5,2022", "30,5,2020"})
    @DisplayName("Test isValidDate function with correct limit day dates")
    public void testIsValidDateDayLimit(String day, String month, String year){
        assertTrue(Date.isValidDate(Integer.parseInt(day),Integer.parseInt(month), Integer.parseInt(year)),
                day + "/" + month + "/" + year);
    }

    /** Test isValidDate function with correct input.
     * Expected : isValidDate return True */
    @ParameterizedTest
    @CsvSource({"01,1,2022", "29,1,2008", "28,1,2006"})
    @DisplayName("Test isValidDate function with correct February limit day dates")
    public void testIsValidDateFebruaryDayLimit(String day, String month, String year){
        assertTrue(Date.isValidDate(Integer.parseInt(day),Integer.parseInt(month), Integer.parseInt(year)),
                day + "/" + month + "/" + year);
    }

    /** Test isValidDate function with correct input.
     * Expected : isValidDate return True */
    @ParameterizedTest
    @CsvSource({"12,11,2018", "4,0,0"})
    @DisplayName("Test isValidDate function with correct limit month dates")
    public void testIsValidDateMonthLimit(String day, String month, String year){
        assertTrue(Date.isValidDate(Integer.parseInt(day),Integer.parseInt(month), Integer.parseInt(year)),
                day + "/" + month + "/" + year);
    }

    /** Test isValidDate function with correct input.
     * Expected : isValidDate return True */
    @ParameterizedTest
    @CsvSource({"12,5,-1999", "12,5,1000000000"})
    @DisplayName("Test isValidDate function with correct negative or big year date")
    public void testIsValidDateNegativeYear(String day, String month, String year){
        assertTrue(Date.isValidDate(Integer.parseInt(day),Integer.parseInt(month), Integer.parseInt(year)),
                day + "/" + month + "/" + year);
    }

    /** Test isValidDate function with incorrect inputs.
     * Expected : isValidDate return false for incorrect day dates */
    @ParameterizedTest
    @CsvSource({"32,0,2020", "31,5,2030","29,1,2022","0,2,0"})
    @DisplayName("Test isValidDate function with limit incorrect day dates")
    public void testIsNotValidDateDay(String day, String month, String year){
        assertFalse(Date.isValidDate(Integer.parseInt(day),Integer.parseInt(month), Integer.parseInt(year)),
                day + "/" + month + "/" + year);
    }

    /** Test isValidDate function with incorrect inputs.
     * Expected : isValidDate return false for incorrect day dates */
    @Test
    @DisplayName("Test isValidDate function with incorrect day dates")
    public void testIsNotValidDateNegativeDay(){
        assertFalse(Date.isValidDate(-1,11,2020));
    }

    /** Test isValidDate function with incorrect inputs.
     * Expected : isValidDate return false for incorrect day dates */
    @Test
    @DisplayName("Test isValidDate function with incorrect day dates")
    public void testIsNotValidDateLeapYear(){
        assertFalse(Date.isValidDate(30,1,2020));
    }


    /** Test isValidDate function with incorrect inputs.
     * Expected : isValidDate return false for incorrect day dates */
    @Test
    @DisplayName("Test isValidDate function with incorrect day dates")
    public void testIsNotValidDateNegativeMonth(){
        assertFalse(Date.isValidDate(3,-1,2020));
    }

    /** Test isLeapYear function with corrects inputs.
     * Expected : isLeapYear return true for correct leap years */
    @ParameterizedTest
    @CsvSource({"1992", "2000", "2020"})
    @DisplayName("Test isLeapYear function with correct leap years")
    public void testIsLeapYear(String year){
        assertTrue(Date.isLeapYear(Integer.parseInt(year)));
    }

    /** Test isLeapYear function with wrongs inputs.
     * Expected : isLeapYear return false for incorrect leap years */
    @Test
    @DisplayName("Test isLeapYear function with incorrect leap years")
    public void testIsNotLeapYear(){
        assertFalse(Date.isLeapYear(2022));
    }

    /** Test nextDate function with correct input
     * Expected : nextDate return the following day */
    @Test
    @DisplayName("Test nextDate function")
    public void testNextDate(){
        Date test = new Date(13,2,2022).nextDate();
        assertEquals(14, test.getDay());
        assertEquals(2, test.getMonth());
        assertEquals(2022, test.getYear());
    }

    /** Test nextDate function with correct input
     * Expected : nextDate return the following day */
    @Test
    @DisplayName("Test nextDate function")
    public void testNextDateChangeYear(){
        Date test = new Date(31,11,2022).nextDate();
        assertEquals(1, test.getDay());
        assertEquals(0, test.getMonth());
        assertEquals(2023, test.getYear());
    }

    /** Test nextDate function with wrongs inputs.
     * Expected : nextDate return the following day */
    @Test
    @DisplayName("Test nextDate function")
    public void testWrongNextDate() throws DateTimeException {
        assertThrows(DateTimeException.class,() -> new Date(-13,2,2022).nextDate());
    }

    /** Test previousDate function with correct input.
     * Expected : previousDate return the previous day*/
    @Test
    @DisplayName("Test previousDate function")
    public void testPreviousDate(){
        Date test = new Date(13,2,2022).previousDate();
        assertEquals(12, test.getDay());
        assertEquals(2, test.getMonth());
        assertEquals(2022, test.getYear());
    }

    /** Test previousDate function with correct input.
     * Expected : previousDate return the previous day and change year*/
    @Test
    @DisplayName("Test previousDate function")
    public void testPreviousDateChangeYear(){
        Date test = new Date(1,0,2022).previousDate();
        assertEquals(31, test.getDay());
        assertEquals(11, test.getMonth());
        assertEquals(2021, test.getYear());
    }

    /** Test compareTo function with corrects inputs.
     * Expected : compareTo return 0*/
    @Test
    @DisplayName("Compare dates equals")
    void testEqualsCompareTo() {
        Date date1 = new Date(13,2,2022);
        Date date2 = new Date(13,2,2022);
        assertEquals(0, date1.compareTo(date2));
    }

    /** Test compareTo function with corrects inputs.
     * Expected : compareTo return -1*/
    @Test
    @DisplayName("Test CompareTo")
    public void testCompareToBefore() {
        Date date1 = new Date(13,1,2026);
        Date date2 = new Date(3,2,2028);
        assertEquals(-1, date1.compareTo(date2));
    }

    /** Test compareTo function with corrects inputs.
     * Expected : compareTo return 1*/
    @Test
    @DisplayName("Test CompareTo")
    public void testCompareToAfter() {
        Date date1 = new Date(13,1,2028);
        Date date2 = new Date(3,2,2026);
        assertEquals(1, date1.compareTo(date2));
    }

    /** Test compareTo function with corrects inputs.
     * Expected : compareTo return 1*/
    @Test
    @DisplayName("Test CompareTo")
    public void testExceptionCompareTo() throws NullPointerException {
        Date date1 = new Date(13,1,2026);
        assertThrows(NullPointerException.class,() -> date1.compareTo(null));
    }
}