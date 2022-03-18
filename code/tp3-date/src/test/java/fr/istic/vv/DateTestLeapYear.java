package fr.istic.vv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static fr.istic.vv.Date.isLeapYear;

public class DateTestLeapYear
{

    @Nested
    class FailedTest
    {
        @Test
        public void isLeapYearFail()
        {
            Assertions.assertFalse(isLeapYear(1937));
            Assertions.assertFalse(isLeapYear(1935));
            Assertions.assertFalse(isLeapYear(0));
            Assertions.assertFalse(isLeapYear(-1936));
            Assertions.assertFalse(isLeapYear(44883463));
        }
    }

    @Nested
    class ValidTest
    {
        @Test
        public void isLeapYearOk()
        {
            Assertions.assertTrue(isLeapYear(1936));
            Assertions.assertTrue(isLeapYear(4));
        }
    }
}
