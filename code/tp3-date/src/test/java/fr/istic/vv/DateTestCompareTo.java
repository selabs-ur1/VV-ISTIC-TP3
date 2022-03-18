package fr.istic.vv;

import fr.istic.vv.Exception.InvalidDateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DateTestCompareTo
{
    @Nested
    class FailTest
    {
        @Test
        public void compareToEqualDayNotYearFail() throws InvalidDateException {
            Date day1 = new Date(17,3,1999);
            Date day2 = new Date(17,3,1998);

            Assertions.assertNotEquals(0, day1.compareTo(day2));
        }

        @Test
        public void compareToNegativeDateFail() throws InvalidDateException {
            Date day1 = new Date(17,3,1999);
            Date day2 = new Date(17,3,1998);

            Assertions.assertNotEquals(-1, day1.compareTo(day2));
        }

        @Test
        public void compareToPositiveDateFail() throws InvalidDateException {
            Date day1 = new Date(17,3,1999);
            Date day2 = new Date(17,3,2000);

            Assertions.assertNotEquals(1, day1.compareTo(day2));
        }
    }

    @Nested
    class ValidTest
    {
        @Test
        public void compareToPositiveDate() throws InvalidDateException {
            Date day1 = new Date(17,3,1999);
            Date day2 = new Date(17,3,1998);

            Assertions.assertEquals(1, day1.compareTo(day2));
        }

        @Test
        public void compareToPositiveDate2() throws InvalidDateException {
            Date day1 = new Date(17,3,1999);
            Date day2 = new Date(16,3,1999);

            Assertions.assertEquals(1, day1.compareTo(day2));
        }

        @Test
        public void compareToEqualDate() throws InvalidDateException {
            Date day1 = new Date(17,3,1999);
            Date day2 = new Date(17,3,1999);

            Assertions.assertEquals(0, day1.compareTo(day2));
        }

        @Test
        public void compareToNegativeDate() throws InvalidDateException {
            Date day1 = new Date(17,3,1999);
            Date day2 = new Date(17,3,2000);

            Assertions.assertEquals(-1, day1.compareTo(day2));
        }

        @Test
        public void compareToNegativeDate2() throws InvalidDateException {
            Date day1 = new Date(17,3,1999);
            Date day2 = new Date(18,3,1999);

            Assertions.assertEquals(-1, day1.compareTo(day2));
        }

        @Test
        public void compareToTest() throws InvalidDateException
        {
            Date day1 = new Date(26,8,3014);
            Date day2 = new Date(13,7,1978);

            Assertions.assertEquals(-1, day2.compareTo(day1));
            Assertions.assertEquals(1, day1.compareTo(day2));
        }

        @Test
        public void compareToTest2() throws InvalidDateException
        {
            Date day1 = new Date(26,12,3014);
            Date day2 = new Date(13,1,3014);

            Assertions.assertEquals(-1, day2.compareTo(day1));
        }

        @Test
        public void compareToTest3() throws InvalidDateException
        {
            Date day1 = new Date(4,6,3014);
            Date day2 = new Date(13,6,3014);

            Assertions.assertEquals(1, day2.compareTo(day1));
        }

        @Test
        public void compareToTestOnSameObject() throws InvalidDateException
        {
            Date day1 = new Date(26,8,3014);

            Assertions.assertEquals(0, day1.compareTo(day1));
        }
    }


}
