package fr.istic.vv;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    public void testDateConstructor() {
        // Partition 1: Valid date
        new Date(1, 1, 2022);

        // Partition 2: Invalid date
        assertThrows(IllegalArgumentException.class, () -> new Date(32, 1, 2022));
    }

    @Test
    public void testIsValidDate() {
        // Partition 1: Normal valid date
        assertTrue(Date.isValidDate(1, 1, 2022));

        // Partition 2: Leap year date
        assertTrue(Date.isValidDate(29, 2, 2024));

        // Partition 3: Invalid day
        assertFalse(Date.isValidDate(32, 1, 2022));

        // Partition 4: Invalid month
        assertFalse(Date.isValidDate(1, 13, 2022));

        // Partition 5: Invalid year
        assertFalse(Date.isValidDate(1, 1, -2022));
    }

    @Test
    public void testIsLeapYear() {
        // Partition 1: Leap year
        assertTrue(Date.isLeapYear(2024));

        // Partition 2: Non-leap year
        assertFalse(Date.isLeapYear(2023));
    }

    @Test
    public void testCompareTo() {
        // Partition 1: Date is posterior to other
        assertTrue(new Date(2, 1, 2022).compareTo(new Date(1, 1, 2022)) > 0);

        // Partition 2: Date is anterior to other
        assertTrue(new Date(1, 1, 2022).compareTo(new Date(2, 1, 2022)) < 0);

        // Partition 3: Date is the same as other
        assertEquals(0, new Date(1, 1, 2022).compareTo(new Date(1, 1, 2022)));

        // Partition 4: other is null
        assertThrows(NullPointerException.class, () -> new Date(1, 1, 2022).compareTo(null));
    }
}
