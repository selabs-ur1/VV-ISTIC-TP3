package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    
    /*
     * isValideDate Tests
     */
        @Test
        public void testValidDatesBisectileValide() {
            assertTrue(Date.isValidDate(15, 1, 2024)); // 1 janvier 2024 (Bisextile)
        }

        @Test
        public void testValidDatesNonBisextileValide() {
            assertTrue(Date.isValidDate(15, 1, 2023)); // 1 janvier 2024 (Bisextile)
        }

        @Test
        public void testValidDatesJourInValide() {
            assertFalse(Date.isValidDate(32, 1, 2024)); // 32 janvier 2024 (jour invalide)
        }

        @Test
        public void testValidDatesMoisInValide() {
            assertFalse(Date.isValidDate(1, 13, 2024)); // Mois invalide (mois 13)
        }

        @Test
        public void testValidDatesMoisFevrierInValide() {
            assertFalse(Date.isValidDate(30, 2, 2024)); // Mois invalide (mois 13)
        }
        
        @Test
        public void testValidDatesAnneeInValide() {
            assertFalse(Date.isValidDate(31, 2, -2024)); // Année invalide (année négative)
        }

    /*
     * isLeapYear Tests
     */
        
        @Test
        public void testLeapYearsValid() {
            assertTrue(Date.isLeapYear(2024)); // Bissextile
        }
    
        @Test
        public void testNonLeapYearsInvalid() {
            assertFalse(Date.isLeapYear(2023)); // Non bissextile
        }
    
    /*
     * nextDate Tests
     */

        @Test
        public void testNextDateNextDay() throws Exception {
            Date date = new Date(1, 1, 2024);
            Date nextDate = date.nextDate();
            Date dateExpected = new Date(2, 1, 2024);
            assertEquals(dateExpected.getDay(), nextDate.getDay()); // 1 janvier 2024 -> 2 janvier 2024
            assertEquals(dateExpected.getMonth(), nextDate.getMonth()); // 1 janvier 2024 -> 2 janvier 2024
            assertEquals(dateExpected.getYear(), nextDate.getYear()); // 1 janvier 2024 -> 2 janvier 2024
        }

        @Test
        public void testNextDateNextMonth() throws Exception {
            Date date = new Date(31, 1, 2024);
            Date nextDate = date.nextDate();
            Date dateExpected = new Date(1, 2, 2024);
            assertEquals(dateExpected.getDay(), nextDate.getDay()); // 31 janvier 2024 -> 1 février 2024
            assertEquals(dateExpected.getMonth(), nextDate.getMonth()); // 31 janvier 2024 -> 1 février 2024
            assertEquals(dateExpected.getYear(), nextDate.getYear()); // 31 janvier 2024 -> 1 février 2024
        }

        @Test
        public void testNextDateNextYear() throws Exception {
            Date date = new Date(31, 12, 2024);
            Date nextDate = date.nextDate();
            Date dateExpected = new Date(1, 1, 2025);
            assertEquals(dateExpected.getDay(), nextDate.getDay()); // 31 décembre 2024 -> 1 janvier 2025
            assertEquals(dateExpected.getMonth(), nextDate.getMonth()); // 31 décembre 2024 -> 1 janvier 2025
            assertEquals(dateExpected.getYear(), nextDate.getYear()); // 31 décembre 2024 -> 1 janvier 2025

        }

        @Test
        public void testNextDayNextBisextileDay() throws Exception {
            Date date = new Date(28, 2, 2024);
            Date nextDate = date.nextDate();
            Date dateExpected = new Date(29, 2, 2024);
            assertEquals(dateExpected.getDay(), nextDate.getDay()); // 28 février 2024 -> 29 février 2024 (année bissextile)
            assertEquals(dateExpected.getMonth(), nextDate.getMonth()); // 28 février 2024 -> 29 février 2024 (année bissextile)
            assertEquals(dateExpected.getYear(), nextDate.getYear()); // 28 février 2024 -> 29 février 2024 (année bissextile)
        }

        @Test
        public void testNextDayNextNonBisextileDay() throws Exception {
            Date date = new Date(28, 2, 2023);
            Date nextDate = date.nextDate();
            Date dateExpected = new Date(1, 3, 2023);
            assertEquals(dateExpected.getDay(), nextDate.getDay()); // 28 février 2023 -> 1 mars 2023 (année non bissextile)
            assertEquals(dateExpected.getMonth(), nextDate.getMonth()); // 28 février 2023 -> 1 mars 2023 (année non bissextile)
            assertEquals(dateExpected.getYear(), nextDate.getYear()); // 28 février 2023 -> 1 mars 2023 (année non bissextile)
        }

        /*
        * previousDate Tests
        */

        @Test
        public void testPreviousDatePreviousDay() throws Exception {
            Date date1 = new Date(2, 1, 2024);
            Date previousDate1 = date1.previousDate();
            Date dateExpected = new Date(1, 1, 2024);
            assertEquals(dateExpected.getDay(), previousDate1.getDay()); // 2 janvier 2024 -> 1 janvier 2024
            assertEquals(dateExpected.getMonth(), previousDate1.getMonth()); // 2 janvier 2024 -> 1 janvier 2024
            assertEquals(dateExpected.getYear(), previousDate1.getYear()); // 2 janvier 2024 -> 1 janvier 2024
        }

        @Test
        public void testPreviousDatePreviousMonth() throws Exception {
            Date date = new Date(1, 2, 2024);
            Date previousDate = date.previousDate();
            Date dateExpected = new Date(31, 1, 2024);
            assertEquals(dateExpected.getDay(), previousDate.getDay());
            assertEquals(dateExpected.getMonth(), previousDate.getMonth()); // 1 février 2024 -> 31 janvier 2024
            assertEquals(dateExpected.getYear(), previousDate.getYear()); // 1 février 2024 -> 31 janvier 2024

        }
        
        @Test
        public void testPreviousDatePreviousYear() throws Exception {
            Date date = new Date(1, 1, 2024);
            Date previousDate = date.previousDate();
            Date dateExpected = new Date(31, 12, 2023);
            assertEquals(dateExpected.getDay(), previousDate.getDay()); // 1 janvier 2024 -> 31 décembre 2023
            assertEquals(dateExpected.getMonth(), previousDate.getMonth()); // 1 janvier 2024 -> 31 décembre 2023
            assertEquals(dateExpected.getYear(), previousDate.getYear()); // 1 janvier 2024 -> 31 décembre 2023
        }

        @Test
        public void testPreviousDatePreviousBisextileDay() throws Exception {
            Date date = new Date(1, 3, 2024);
            Date previousDate = date.previousDate();
            Date dateExpected = new Date(29, 2, 2024);
            assertEquals(dateExpected.getDay(), previousDate.getDay()); // 1 mars 2024 -> 29 février 2024
            assertEquals(dateExpected.getMonth(), previousDate.getMonth()); // 1 mars 2024 -> 29 février 2024
            assertEquals(dateExpected.getYear(), previousDate.getYear()); // 1 mars 2024 -> 29 février 2024
        }

        @Test
        public void testPreviousDatePreviousNonBisextileDay() throws Exception {
            Date date = new Date(1, 3, 2023);
            Date previousDate = date.previousDate();
            Date dateExpected = new Date(28, 2, 2023);
            assertEquals(dateExpected.getDay(), previousDate.getDay()); // 1 mars 2024 -> 28 février 2024
            assertEquals(dateExpected.getMonth(), previousDate.getMonth()); // 1 mars 2024 -> 28 février 2024
            assertEquals(dateExpected.getYear(), previousDate.getYear()); // 1 mars 2024 -> 28 février 2024
        }

        /*
        * compareTo Tests
        */

        @Test
        public void testCompareToEqual() throws Exception {
            Date date = new Date(1, 1, 2024);
            Date dateEquals = new Date(1, 1, 2024);
    
            // Cas d'égalité
            assertEquals(0, date.compareTo(dateEquals)); // 1 janvier 2024 == 1 janvier 2024
        }

        @Test
        public void testCompareToAnteriorYear() throws Exception {
            Date date = new Date(1, 1, 2024);
            Date anterior = new Date(1, 1, 2025);

            // Cas de année antérieure
            assertTrue(date.compareTo(anterior) < 0); // 1 janvier 2024 < 1 janvier 2025
        }

        @Test
        public void testCompareToPosteriorYear() throws Exception {
            Date date = new Date(1, 1, 2024);
            Date posterior = new Date(1, 1, 2023);

            // Cas de année postérieure
            assertTrue(date.compareTo(posterior) > 0); // 1 janvier 2024 > 1 janvier 2023
        }

        @Test
        public void testCompareToAnteriorMonth() throws Exception {
            Date date = new Date(1, 1, 2024);
            Date anterior = new Date(1, 2, 2024);

            // Cas de mois antérieure
            assertTrue(date.compareTo(anterior) < 0); // 1 janvier 2024 < 1 février 2024
        }

        @Test
        public void testCompareToPosteriorMonth() throws Exception {
            Date date = new Date(1, 2, 2024);
            Date posterior = new Date(1, 1, 2024);

            // Cas de mois postérieure
            assertTrue(date.compareTo(posterior) > 0); // 1 février 2024 > 1 janvier 2024
        }

        @Test
        public void testCompareToAnteriorDay() throws Exception {
            Date date = new Date(1, 1, 2024);
            Date anterior = new Date(2, 1, 2024);

            // Cas de jour antérieure
            assertTrue(date.compareTo(anterior) < 0); // 1 janvier 2024 < 2 janvier 2024
        }

        @Test
        public void testCompareToPosteriorDay() throws Exception {
            Date date = new Date(2, 1, 2024);
            Date posterior = new Date(1, 1, 2024);

            // Cas de jour postérieure
            assertTrue(date.compareTo(posterior) > 0); // 2 janvier 2024 > 1 janvier 2024
        }

        @Test
        public void testCompareToNull() throws Exception {
            Date date1 = new Date(1, 1, 2024);
             // Comparaison avec `null` (doit lancer une NullPointerException)
             try {
                date1.compareTo(null);
                fail("Exception expected");
            } catch (NullPointerException e) {
                // Test réussi
            }
        }
}