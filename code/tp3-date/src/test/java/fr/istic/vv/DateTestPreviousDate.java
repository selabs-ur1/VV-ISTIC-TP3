package fr.istic.vv;

import fr.istic.vv.Exception.InvalidDateException;
import fr.istic.vv.Exception.InvalidMethodUseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DateTestPreviousDate
{

    @Nested
    class FailedTest
    {

        @Test
        public void previousDate() throws InvalidDateException, InvalidMethodUseException {
            Date day = new Date(16,3,1999);
            Date tomorrow = new Date(17,3,1999);

            Assertions.assertNotEquals(day.previousDate(), tomorrow);
        }

        @Test
        public void previousDate1stDayMarch() throws InvalidDateException, InvalidMethodUseException {
            Date day = new Date(1,3,1999);

            Assertions.assertThrows(InvalidDateException.class, () -> {
                Date tomorrow = new Date(29,2,1999);
                Assertions.assertNotEquals(day.previousDate(), tomorrow);
            });
        }

        @Test
        public void previousDate1stDayAndYear() throws InvalidDateException, InvalidMethodUseException {
            Date day = new Date(1,1,1);

            //test throw error because our class do not cover year Before Christ
            Assertions.assertThrows(InvalidMethodUseException.class, day::previousDate);

        }

        @Test
        public void previousDate1stDayMarch2() throws InvalidDateException, InvalidMethodUseException {
            Date day = new Date(1,4,1999);
            Date tomorrow = new Date(30,3,1999);

            Assertions.assertNotEquals(day.previousDate(), tomorrow);
        }

        @Test
        public void previousDate1stYearDay() throws InvalidDateException, InvalidMethodUseException {
            Date day = new Date(1,1,1999);
            Date tomorrow = new Date(1,1,1998);

            Assertions.assertNotEquals(day.previousDate(), tomorrow);
        }
    }

    @Nested
    class OkTest
    {

        @Test
        public void previousDate() throws InvalidDateException, InvalidMethodUseException {
            Date day = new Date(16,3,1999);
            Date tomorrow = new Date(15,3,1999);

            Assertions.assertEquals(day.previousDate(), tomorrow);
        }

        @Test
        public void previousDate1stDayMarch() throws InvalidDateException, InvalidMethodUseException {
            Date day = new Date(1,3,1999);
            Date tomorrow = new Date(28,2,1999);

            Assertions.assertEquals(day.previousDate(), tomorrow);
        }

        @Test
        public void previousDate1stDayMarchLeap() throws InvalidDateException, InvalidMethodUseException {
            Date day = new Date(1,3,1936);
            Date tomorrow = new Date(29,2,1936);

            Assertions.assertEquals(day.previousDate(), tomorrow);
        }

        @Test
        public void previousDate1stDayMarch2() throws InvalidDateException, InvalidMethodUseException {
            Date day = new Date(1,4,1999);
            Date tomorrow = new Date(31,3,1999);

            Assertions.assertEquals(day.previousDate(), tomorrow);
        }

        @Test
        public void previousDate1stYearDay() throws InvalidDateException, InvalidMethodUseException {
            Date day = new Date(1,1,1999);
            Date tomorrow = new Date(31,12,1998);

            Assertions.assertEquals(day.previousDate(), tomorrow);
        }

        @Test
        public void previousDate1stDay() throws InvalidDateException, InvalidMethodUseException{
            Date day = new Date(1,8,1999);
            Date tomorrow = new Date(31,7,1999);

            Assertions.assertEquals(day.previousDate(), tomorrow);
        }

        @Test
        public void previousDate1stDay2() throws InvalidDateException, InvalidMethodUseException {
            Date day = new Date(1,7,1999);
            Date tomorrow = new Date(30,6,1999);

            Assertions.assertEquals(day.previousDate(), tomorrow);
        }
    }

}
