package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.Date.isValidDate;
import static org.junit.jupiter.api.Assertions.*;

class CompareToTests {

    //SUPERIOR

    @Test
    public void testDaySuperior(){
        assertTrue(((new Date(15,2,2022)).compareTo(new Date(14,2,2022))) > 0);
    }

    //NULL

    @Test
    public void compareToNull(){
        assertThrows(NullPointerException.class, () ->{
            (new Date(15,2,2022)).compareTo(null);
        });
    }

}