package fr.istic.vv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static fr.istic.vv.Date.isValidDate;
import static org.junit.jupiter.api.Assertions.*;
import fr.istic.vv.DateTestUtils;

class DateTestValidDate {

    @Nested
    class FailedTest
    {
        @Test
        public void validDateRandomYearFail()
        {
            Assertions.assertFalse(isValidDate(17,13,DateTestUtils.createYear()));
            Assertions.assertFalse(isValidDate(17,-1,DateTestUtils.createYear()));
        }
        @Test
        public void validDateRandomMonthFail()
        {
            Assertions.assertFalse(isValidDate(32,DateTestUtils.createMonth(),1999));
            Assertions.assertFalse(isValidDate(-1,DateTestUtils.createMonth(),1999));
        }
        @Test
        public void validDateRandomDayFail()
        {
            Assertions.assertFalse(isValidDate(DateTestUtils.createDay(),0,1999));
            Assertions.assertFalse(isValidDate(DateTestUtils.createDay(),-1,1999));
        }

        @Test
        public void validDateLeapYearFail() {
            Assertions.assertFalse(isValidDate(29,2,1937));
            Assertions.assertFalse(isValidDate(30,2,1936));
            Assertions.assertFalse(isValidDate(-29,2,1936));
            Assertions.assertFalse(isValidDate(29,-2,1936));
        }

        @Test
        public void validDateFailMonth() {
            Assertions.assertFalse(isValidDate(29,13,1936));
            Assertions.assertFalse(isValidDate(29,0,1936));
        }

        @Test
        public void validDateFailDay() {
            Assertions.assertFalse(isValidDate(31,4,1936));
            Assertions.assertFalse(isValidDate(32,3,1936));
            Assertions.assertFalse(isValidDate(0,3,1936));
        }

        @Test
        public void validDateFailYear() {
            System.out.println(isValidDate(30,4,0));
        }

    }

    @Nested
    class ValidTest{
        @Test
        public void validDateDayOk()
        {
            Assertions.assertTrue(isValidDate(17,3,1999));
            Assertions.assertTrue(isValidDate(31,3,1999));
        }

        @Test
        public void validDateMonthOk()
        {
            Assertions.assertTrue(isValidDate(17,12,1999));
            Assertions.assertTrue(isValidDate(29,2,1936));
            Assertions.assertTrue(isValidDate(29,1,1936));
            Assertions.assertTrue(isValidDate(29,7,1936));
        }

        @Test
        public void validDateYearOk()
        {
            Assertions.assertTrue(isValidDate(17,12,1));
            Assertions.assertTrue(isValidDate(28,2,19366494));
        }
    }


}