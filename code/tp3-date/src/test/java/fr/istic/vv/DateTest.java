package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    //Test avec une date dont le mois est valide
    @Test
    public void testDateValidMonth() {
        assertDoesNotThrow(() ->new Date(1, 1, 1));
    }

    //Test avec une date dont le mois est valide
    @Test
    public void testDateValidMonth2() {
        assertDoesNotThrow(() ->new Date(1, 12, 1));
    }

    //Test avec une date dont le mois est pas valide
    @Test
    public void testDateNotValidMonth() {
        assertThrows(IllegalArgumentException.class, () ->new Date(1, 0, 1));
    }

    //Test avec une date dont le mois est pas valide
    @Test
    public void testDateNotValidMonth2() {
        assertThrows(IllegalArgumentException.class, () ->new Date(1, 13, 1));
    }

    //Test avec une date dont l'annee est pas valide
    @Test
    public void testDateValidYear() {
        assertDoesNotThrow(() ->new Date(1, 1, 1));
    }

    //Test avec une date dont l'annee est pas valide
    @Test
    public void testDateValidYear2() {
        assertDoesNotThrow(() ->new Date(1, 1, 2025));
    }

    //Test avec une date dont l'annee est pas valide
    @Test
    public void testDateNotValidYear() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 1, -1));
    }

    //Test avec une date dont le jour est pas valide
    @Test
    public void testDateValidDay() {
        assertDoesNotThrow(() ->new Date(1, 1, 1));
    }

    //Test avec une date dont le jour est pas valide
    @Test
    public void testDateValidDay2() {
        assertDoesNotThrow(() ->new Date(31, 1, 1));
    }

    //Test avec une date dont le jour est pas valide
    @Test
    public void testDateNotValidDay() {
        assertThrows(IllegalArgumentException.class, () ->new Date(0, 1, 1));
    }

    //Test avec une date dont le jour est pas valide
    @Test
    public void testDateNotValidDay2() {
        assertThrows(IllegalArgumentException.class, () ->new Date(32, 1, 1));
    }

    //Test avec une date dont l'annee est bissextile et valide
    @Test
    public void testIsLeapYearDateBissextile() {
        assertTrue(Date.isLeapYear(2024));
    }

    //Test avec une date dont l'annee n'est pas bissextile
    @Test
    public void testIsLeapYearDateNotBissextile() {
        assertFalse(Date.isLeapYear(2023));
    }

    //Test avec une date dont l'annee n'est pas bissextile et pas valide
    @Test
    public void testIsLeapYearDateNotBissextile2() {
        assertFalse(Date.isLeapYear(-1));
    }

    //Test avec une date dont l'annee est bissextile car divisible par 400
    @Test
    public void testIsLeapYearDateBissextile2() {
        assertTrue(Date.isLeapYear(2000 ));
    }

    //Test date suivante de 28/02 sur une annee bissextile
    @Test
    public void testNextDateBissextile() throws Exception {
        Date date = new Date(28, 2 ,2024);
        Date nextDate = date.nextDate();
        assertTrue(nextDate.getDay() == 29 && nextDate.getMonth() == 2 && nextDate.getYear() == 2024);
    }

    //Test date suivante de 29/02 sur une annee normal
    @Test
    public void testNextDateNormal() throws Exception {
        Date date = new Date(28, 2 ,2023);
        Date nextDate = date.nextDate();
        assertTrue(nextDate.getDay() == 1 && nextDate.getMonth() == 3 && nextDate.getYear() == 2023);
    }

    //Test date precedant de 01/01 sur une annee normal
    @Test
    public void testPreviousDateNormal2() throws Exception {
        Date date = new Date(1, 1 ,2023);
        assertTrue(date.previousDate().getDay() == 31);
    }

    //Test date precedant de 01/02 sur une annee normal
    @Test
    public void testPreviousDateNormal3() throws Exception {
        Date date = new Date(1, 2 ,2023);
        assertTrue(date.previousDate().getDay() == 31);
    }

    //Test date precedant de 05/01 sur une annee normal
    @Test
    public void testPreviousDateNormal4() throws Exception {
        Date date = new Date(5, 1 ,2023);
        assertTrue(date.previousDate().getDay() == 4);
    }

    //Test date precedant de 28/02 sur une annee bissextile
    @Test
    public void testPreviousDateBissextile() throws Exception {
        Date date = new Date(1, 3 ,2024);
        assertTrue(date.previousDate().getDay() == 29);
    }

    //Test date precedant de 29/02 sur une annee normal
    @Test
    public void testPreviousDateNormal() throws Exception {
        Date date = new Date(1, 3 ,2023);
        assertTrue(date.previousDate().getDay() == 28);
    }

    //Test date compare 29/02/2024 et 30/03/2024
    @Test
    public void testCompareToSupp() throws Exception {
        Date date = new Date(29, 2 ,2024);
        assertTrue(date.compareTo(new Date(30, 3 ,2024)) > 0);
    }

    //Test date compare 29/02/2024 et 30/03/2025
    @Test
    public void testCompareToSupp2() throws Exception {
        Date date = new Date(29, 2 ,2024);
        assertTrue(date.compareTo(new Date(30, 3 ,2025)) > 0);
    }

    //Test date compare 29/02/2024 et 29/01/2024
    @Test
    public void testCompareToInf() throws Exception {
        Date date = new Date(29, 2 ,2024);
        assertTrue(date.compareTo(new Date(29, 1 ,2024)) < 0);
    }

    //Test date compare 29/02/2024 et 29/02/2024
    @Test
    public void testCompareToEg() throws Exception {
        Date date = new Date(29, 2 ,2024);
        assertTrue(date.compareTo(new Date(29, 2 ,2024)) == 0);
    }

    //Test sur l'annee 0
    @Test
    public void testDateYearZero() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 1 ,0));
    }

    //Test sur l'annee 2000
    @Test
    public void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2000));
    }

    //Test sur l'annee 0
    @Test
    public void testIsLeapYear1() {
        assertFalse(Date.isLeapYear(0));
    }

    //Test sur l'annee 1
    @Test
    public void testIsLeapYear2() {
        assertFalse(Date.isLeapYear(1));
    }

    //Test sur l'annee 4
    @Test
    public void testIsLeapYear3() {
        assertTrue(Date.isLeapYear(4));
    }

    //Test sur l'annee 100
    @Test
    public void testIsLeapYear4() {
        assertFalse(Date.isLeapYear(100));
    }

    //Test sur l'annee 400
    @Test
    public void testIsLeapYear5() {
        assertTrue(Date.isLeapYear(400));
    }

    //Test date suivante de 31/12 sur une annee normal
    @Test
    public void testNextDateNewYear() throws Exception {
        Date date = new Date(31, 12 ,2023);
        Date nextDate = date.nextDate();
        assertTrue(nextDate.getDay() == 1  && nextDate.getMonth() == 1 && nextDate.getYear() == 2024);
    }

    //Test date suivante de 30/11 sur une annee normal
    @Test
    public void testNextDateNewYear2() throws Exception {
        Date date = new Date(30, 11 ,2023);
        Date nextDate = date.nextDate();
        assertTrue(nextDate.getMonth() == 12);
    }

    //Test date precedant de 05/02 sur une annee normal Day
    @Test
    public void testPreviousDateNormal5Day() throws Exception {
        Date date = new Date(1, 1 ,2023);
        assertTrue(date.previousDate().getDay() == 31);
    }

    //Test date precedant de 05/02 sur une annee normal Month
    @Test
    public void testPreviousDateNormal5Month() throws Exception {
        Date date = new Date(1, 1 ,2023);
        assertTrue(date.previousDate().getMonth() == 12);
    }

    //Test date precedant de 05/02 sur une annee normal Year
    @Test
    public void testPreviousDateNormal5Year() throws Exception {
        Date date = new Date(1, 1 ,2023);
        assertTrue(date.previousDate().getYear() == 2022);
    }

    //Test date precedant de 05/02 sur une annee normal Day
    @Test
    public void testPreviousDateNormal6Day() throws Exception {
        Date date = new Date(1, 10 ,2023);
        assertTrue(date.previousDate().getDay() == 30);
    }

    //Test date precedant de 05/02 sur une annee normal Month
    @Test
    public void testPreviousDateNormal6Month() throws Exception {
        Date date = new Date(1, 10 ,2023);
        assertTrue(date.previousDate().getMonth() == 9);
    }

    //Test date precedant de 05/02 sur une annee normal Year
    @Test
    public void testPreviousDateNormal6Year() throws Exception {
        Date date = new Date(1, 10 ,2023);
        assertTrue(date.previousDate().getYear() == 2023);
    }

    //Test date precedant de 05/02 sur une annee normal Day
    @Test
    public void testPreviousDateNormal7Day() throws Exception {
        Date date = new Date(1, 2 ,2023);
        assertTrue(date.previousDate().getDay() == 31);
    }

    //Test date precedant de 05/02 sur une annee normal Month
    @Test
    public void testPreviousDateNormal7Month() throws Exception {
        Date date = new Date(1, 2 ,2023);
        assertTrue(date.previousDate().getMonth() == 1);
    }

    //Test date precedant de 05/02 sur une annee normal Year
    @Test
    public void testPreviousDateNormal7Year() throws Exception {
        Date date = new Date(1, 2 ,2023);
        assertTrue(date.previousDate().getYear() == 2023);
    }

    //Test date compare 29/02/2024 et 28/02/2024
    @Test
    public void testCompareToInf2() throws Exception {
        Date date = new Date(29, 2 ,2024);
        assertTrue(date.compareTo(new Date(28, 2 ,2024)) == -1);
    }
}