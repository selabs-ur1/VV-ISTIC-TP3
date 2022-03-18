package fr.istic.vv;

import fr.istic.vv.Exception.InvalidDateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTestNextDate {


    @Nested
    class FailedTest {
        @Test
        public void nextDateFail() throws InvalidDateException {
            Date day = new Date(16,3,1999);
            Date tomorrow = new Date(18,3,1999);

            Assertions.assertNotEquals(day.nextDate(), tomorrow);
        }

        @Test
        public void nextDateFail2() throws InvalidDateException {
            Date day = new Date(31,3,1999);
            Date tomorrow = new Date(1,3,1999);

            Assertions.assertNotEquals(day.nextDate(), tomorrow);
        }
    }

    @Nested
    class ValidTest {

        @Test
        public void nextDateNormal() throws InvalidDateException {
            Date day = new Date(17,3,1999);
            Date tomorrow = new Date(18,3,1999);
            Assertions.assertEquals(day.nextDate(), tomorrow);
        }

        @Test
        public void nextDateEndMonth() throws InvalidDateException {
            Date day = new Date(31,3,1999);
            Date tomorrow = new Date(1,4,1999);
            Assertions.assertEquals(day.nextDate(), tomorrow);
        }

        @Test
        public void nextDateEndYear() throws InvalidDateException {
            Date day = new Date(31,12,1999);
            Date tomorrow = new Date(1,1,2000);
            Assertions.assertEquals(day.nextDate(), tomorrow);
        }

        @Test
        public void nextDateEndFebrLeapYear() throws InvalidDateException {
            Date day = new Date(29,2,2000);
            Date tomorrow = new Date(1,3,2000);
            Assertions.assertEquals(day.nextDate(), tomorrow);
        }

        @Test
        public void nextDateEndFebrNotLeapYear() throws InvalidDateException {
            Date day = new Date(28,2,1999);
            Date tomorrow = new Date(1,3,1999);
            Assertions.assertEquals(day.nextDate(), tomorrow);
        }
    }
}
