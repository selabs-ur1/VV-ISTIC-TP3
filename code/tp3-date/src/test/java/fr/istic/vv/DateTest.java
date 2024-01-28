package fr.istic.vv;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    /*
    isValide: (true)
        0<day=< 31
        0< month <= 12

        Annee >= 0
        Annee < 0 doit peut étre mis dans le not dépendent de la lib localDate

    */

    @Test
    public void testValidDateFirstDayYear() {
        assertTrue(Date.isValidDate(1, 1, 2023));
    }

    @Test
    public void testValidDateFebruryLeapYear() {
        assertTrue(Date.isValidDate(29, 2, 2024));
    }

    @Test
    public void testValidDateLastDayYear() {
        assertTrue(Date.isValidDate(31, 12, 2023));
    }



    /*
    isValide: (false)
        day > 31
        day < 0

        month > 12
        month <= 0

        Annee >= 0
        Annee < 0
    */

    @Test
    public void testInvalidDateWithDaytAt0() {
        assertFalse(Date.isValidDate(0, 1, 2023));
    }
    @Test
    public void testInvalidDateWithDaySupAt31() {
        assertFalse(Date.isValidDate(32, 1, 2023));
    }
    @Test
    public void testInvalidDateWithMonthSupAt12() {
        assertFalse(Date.isValidDate(1, 13, 2023));
    }

    @Test
    public void testInvalidDateNotLeapYear() {
        assertFalse(Date.isValidDate(29, 2, 2023)); // Not a leap year
    }

    /*isLeapYear:

        Annee bisextile
        Annee non bisextile*/

    @ParameterizedTest
    @ValueSource(ints = {4, 8, 12, 16, 20, 24, 28, 32, 36, 40,
            44, 48, 52, 56, 64, 68, 72, 76, 80, 84,
            88, 92, 96, 104, 108, 112, 116, 120, 124, 128,
            132, 136, 140, 144, 148, 152, 156, 160, 164, 168,
            172, 176, 184, 188, 192, 196, 204, 208, 212, 216,
            220, 224, 228, 232, 236, 240, 244, 248, 252, 256,
            260, 264, 272, 276, 280, 284, 288, 292, 296, 304,
            308, 312, 316, 320, 324, 328, 332, 336, 340, 344,
            348, 352, 356, 364, 368, 372, 376, 380, 384, 388,
            392, 396, 404, 408, 412, 416, 420, 424, 428, 432,
            436, 440, 444, 448, 452, 456, 464, 468, 472, 476,
            480, 484, 488, 492, 496})
    void testIsLeapYear(int year) {
        assertTrue(Date.isLeapYear(year));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 5, 6, 7, 9, 10, 11, 13,
            14, 15, 17, 18, 19, 21, 22, 23, 25, 26,
            27, 29, 30, 31, 33, 34, 35, 37, 38, 39,
            41, 42, 43, 45, 46, 47, 49, 50, 51, 53,
            54, 55, 57, 58, 59, 61, 62, 63, 65, 66,
            67, 69, 70, 71, 73, 74, 75, 77, 78, 79,
            81, 82, 83, 85, 86, 87, 89, 90, 91, 93,
            94, 95, 97, 98, 99, 101, 102, 103, 105, 106,
            107, 109, 110, 111, 113, 114, 115, 117, 118, 119,
            121, 122, 123, 125, 126, 127, 129, 130, 131, 133,
            134, 135, 137, 138, 139, 141, 142, 143, 145, 146,
            147, 149, 150, 151, 153, 154, 155, 157, 158, 159,
            161, 162, 163, 165, 166, 167, 169, 170, 171, 173,
            174, 175, 177, 178, 179, 181, 182, 183, 185, 186,
            187, 189, 190, 191, 193, 194, 195, 197, 198, 199,
            201, 202, 203, 205, 206, 207, 209, 210, 211, 213,
            214, 215, 217, 218, 219, 221, 222, 223, 225, 226,
            227, 229, 230, 231, 233, 234, 235, 237, 238, 239,
            241, 242, 243, 245, 246, 247, 249, 250, 251, 253,
            254, 255, 257, 258, 259, 261, 262, 263, 265, 266,
            267, 269, 270, 271, 273, 274, 275, 277, 278, 279,
            281, 282, 283, 285, 286, 287, 289, 290, 291, 293,
            294, 295, 297, 298, 299, 301, 302, 303, 305, 306,
            307, 309, 310, 311, 313, 314, 315, 317, 318, 319,
            321, 322, 323, 325, 326, 327, 329, 330, 331, 333,
            334, 335, 337, 338, 339, 341, 342, 343, 345, 346,
            347, 349, 350, 351, 353, 354, 355, 357, 358, 359,
            361, 362, 363, 365, 366, 367, 369, 370, 371, 373,
            374, 375, 377, 378, 379, 381, 382, 383, 385, 386,
            387, 389, 390, 391, 393, 394, 395, 397, 398, 399,
            401, 402, 403, 405, 406, 407, 409, 410, 411, 413,
            414, 415, 417, 418, 419, 421, 422, 423, 425, 426,
            427, 429, 430, 431, 433, 434, 435, 437, 438, 439,
            441, 442, 443, 445, 446, 447, 449, 450, 451, 453,
            454, 455, 457, 458, 459, 461, 462, 463, 465, 466,
            467, 469, 470, 471, 473, 474, 475, 477, 478, 479,
            481, 482, 483, 485, 486, 487, 489, 490, 491, 493,
            494, 495, 497, 498, 499})
    void testIsNotLeapYear(int year) {
        assertFalse(Date.isLeapYear(year));
    }

    @Test
    public void testBoundaryCases0() {
        assertTrue(Date.isLeapYear(0)); // Year 0 is considered a leap year
    }

    @Test
    public void testBoundaryCasesNegativYear(){
        assertFalse(Date.isLeapYear(-2000)); // Negative years are not leap years
    }


    /*
        compareTo:
        Date null

        Date ==
        Date before
        Date next
    */
    @Test
    void testOtherNull(){
        Date date = new Date(1,1,2023);
        Date other = null;

        assertThrows(NullPointerException.class,() -> {
            date.compareTo(other);
        });
    }

    @Test
    void testSameDate(){
        Date date = new Date(1,1,2023);

        Date other = new Date(1,1,2023);

        int result = date.compareTo(other);
        int oracleResult = 0; // result when `date` equal `other`

        assertEquals(result,oracleResult);
    }
    @Test
    void testOtherAfter(){
        Date date = new Date(1,1,2023);
        Date other =  date.nextDate();

        int result = date.compareTo(other);
        int oracleResult = -1; // result when `date` is anterior to `other`

        assertEquals(result,oracleResult);
    }

    @Test
    void testOtherBefore(){
        Date other = new Date(1,1,2023);
        Date date =  other.nextDate();

        int result = date.compareTo(other);
        int oracleResult = 1; // result when `date` is posterior to `other`

        assertEquals(result,oracleResult);
    }


    /*nextDate:
        pas de param*/

    @Test
    void testNextDateBasic() {
        Date dateStart = new Date(1,1,2023);
        Date oracleNextDate = new Date(2,1,2023);

        Date nextDate = dateStart.nextDate();

        int result = nextDate.compareTo(oracleNextDate);
        int oracleResult = 0; // result when `date` equal `other`

        assertEquals(result,oracleResult);
    }

    @Test
    void testNextDateLastDayMonth() {
        Date dateStart = new Date(31,1,2023);
        Date oracleNextDate = new Date(1,2,2023);

        Date nextDate = dateStart.nextDate();

        int result = nextDate.compareTo(oracleNextDate);
        int oracleResult = 0; // result when `date` equal `other`

        assertEquals(result,oracleResult);
    }

    @Test
    void testNextDateLastDayYear() {
        Date dateStart = new Date(31,12,2023);
        Date oracleNextDate = new Date(1,1,2024);

        Date nextDate = dateStart.nextDate();

        int result = nextDate.compareTo(oracleNextDate);
        int oracleResult = 0; // result when `date` equal `other`

        assertEquals(result,oracleResult);
    }

    /*
    PreviousDate
    */


    @Test
    void testPreviousDateBasic() {
        Date dateStart = new Date(1,1,2023);
        Date oraclePreviousDate = new Date(31,12,2022);

        Date previousDate = dateStart.previousDate();

        int result = previousDate.compareTo(oraclePreviousDate);
        int oracleResult = 0; // result when `date` equal `other`

        assertEquals(result,oracleResult);
    }

    @Test
    void testPreviousDateLastDayMonth() {
        Date dateStart = new Date(31,1,2023);
        Date oraclePreviousDate = new Date(30,1,2023);

        Date previousDate = dateStart.previousDate();

        int result = previousDate.compareTo(oraclePreviousDate);
        int oracleResult = 0; // result when `date` equal `other`

        assertEquals(result,oracleResult);
    }

    @Test
    void testPreviousDateLastDayYear() {
        Date dateStart = new Date(31,12,2023);
        Date oraclePreviousDate = new Date(30,12,2023);

        Date previousDate = dateStart.previousDate();

        int result = previousDate.compareTo(oraclePreviousDate);
        int oracleResult = 0; // result when `date` equal `other`

        assertEquals(result,oracleResult);
    }


}