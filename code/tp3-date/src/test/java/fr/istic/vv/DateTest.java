package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    public void isValidate(){
           assertTrue(Date.isValidDate(20,10,2022) );
    }

    @Test
    public void isNotValidate(){
        assertFalse(Date.isValidDate(35,10,2022) );
    }

    @Test
    public void nextDateValidateDay(){
        try {
            Date date = new Date(03,10,2022);
            Date nextDate = date.nextDate();
            assertEquals(nextDate.compareTo(new Date(04,10,2022)), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nextDateValidateMonth(){
        try {
            Date date = new Date(31,10,2022);
            Date nextDate = date.nextDate();
            assertEquals(nextDate.compareTo(new Date(01,11,2022)), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nextDateValidateYear(){
        try {
            Date date = new Date(31,12,2022);
            Date nextDate = date.nextDate();
            assertEquals(nextDate.compareTo(new Date(01,01,2023)), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void previousDateValidateDay(){
        try {
            Date date = new Date(03,10,2022);
            Date previous = date.previousDate();
            assertEquals(previous.compareTo(new Date(02,10,2022)), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void previousDateValidateMonth(){
        try {
            Date date = new Date(01,10,2022);
            Date previous = date.previousDate();
            assertEquals(previous.compareTo(new Date(30,9,2022)), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void previousDateValidateYear(){
        try {
            Date date = new Date(01,01,2022);
            Date previous = date.previousDate();
            assertEquals(previous.compareTo(new Date(31,12,2021)), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isLeapYear(){
        assertTrue(Date.isLeapYear(2024));
    }

    @Test
    public void compareToSup(){
        try {
            Date date = new Date(03,10,2022);
            assertEquals(date.compareTo(new Date(01,10,2022)), 1);
        } catch (Exception e) {
            fail("problème dans le compareTo de la date");
        }
    }

    @Test
    public void compareToInf(){
        try {
            Date date = new Date(03,10,2022);
            assertEquals(date.compareTo(new Date(05,10,2022)), -1);
        } catch (Exception e) {
            fail("problème dans le compareTo de la date");
        }
    }
}