package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

  @Test
	void test() {
    assertTrue(Date.isValidDate(29, 2, 2008));
		assertFalse(Date.isValidDate(29, 2, 2007));
		assertFalse(Date.isValidDate(29, 2, 2006));
		assertFalse(Date.isValidDate(29, 2, 2005));
    
    assertFalse(Date.isValidDate(29, 13, 2005));
		assertFalse(Date.isValidDate(31, 4, 2005));
		assertTrue(Date.isValidDate(31, 1, 2008));
		assertFalse(Date.isValidDate(32, 1, 2008));
		assertTrue(Date.isValidDate(1, 1, 1));
    
    assertTrue(Date.isLeapYear(2008));
		assertFalse(Date.isLeapYear(2009));
    
    assertTrue(new Date(1,1,2000).nextDate().getDay()==2);
		assertTrue(new Date(31,1,2000).nextDate().getDay()==1);
    
    assertTrue(new Date(1,1,2000).previousDate().getDay()==31);
		assertTrue(new Date(31,1,2000).previousDate().getDay()==30);
    
    assertTrue(new Date(1,1,2000).compareTo(new Date(1,1,2000))==0);
		assertTrue(new Date(1,1,2000).compareTo(new Date(2,1,2000))<0);
		assertTrue(new Date(2,1,2000).compareTo(new Date(1,1,2000))>0);
		assertThrows(NullPointerException.class, () -> new Date(1,1,2000).compareTo(null));
  }

}
