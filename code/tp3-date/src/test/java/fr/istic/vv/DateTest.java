package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/* Ordre de test : 
 * 1) public static boolean isLeapYear(int year)
 * 
 * 2) public static boolean isValidDate(int day, int month, int year)
 * le test de isValidDate permet de tester le constructeur
 * On profite du fait que l'on teste des Dates pour effectuer des tests exhaustifs sur jours et mois
 * Seule l'année n'est pas testable de manière exhaustive
 * 
 * 4) public Date nextDate()
 * 5) public Date previousDate()
 * 6) public int compareTo(Date other)
 */
class DateTest {

	private static final int[] leapYears = {2000,2004,2008,2012,2016,2020,2024,2028,2032,2036};
	private static final int[] noLeapYears = {1700,1800,1900,2100,2001,2003,2010};
	int[] daysInMonthLeapYear = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	int[] daysInMonthNoLeapYear = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	/*
	 * Section avant utilisation de PIT
	 */
	
	/*
	 * sous-section test pour isLeapYear
	 */
	@Test
	public void testValidLeapYear() {
		for(int i=0 ; i<leapYears.length ; i++)
			assertTrue(Date.isLeapYear(leapYears[i]), "testValidLeapYear failed for year : " + leapYears[i]);
	}
	
	@Test
	public void testInvalidLeapYear() {
		for(int i=0 ; i<noLeapYears.length ; i++)
			assertFalse(Date.isLeapYear(noLeapYears[i]), "testInvalidLeapYear failed for year : " + noLeapYears[i]);
	}
	
	/*
	 * sous-section test de isValidDate
	 */
	
	/*
	 * input type : Date avec composante nulle
	 * on teste toutes les combinaisons ou 0 invalide la date
	 * il suffit de combiner 1 et 0, les 1 forment toujours une date valide, on veut verifier que les 0 invalident toujours la date
	 */
	@Test
	public void testisValidDate1() {
		List<Integer> zeroOne = List.of(0,1);
		for(int day : zeroOne)
			for(int month : zeroOne)
				for(int year : zeroOne)
					if(day == 0 || month == 0 || year == 0)
						assertFalse(Date.isValidDate(day, month, year), "testDate1, the date " + day+"/"+month+"/"+year + " should not be valid");
					else
						assertTrue(Date.isValidDate(day, month, year),"testDate1, 1/1/1 sould be valid");
	}
	
	/*
	 * input type : Date avec une composante negative
	 * Meme but que testDate1, mais pour une composante negative
	 */
	@Test
	public void testisValidDate2() {
		List<Integer> zeroOne = List.of(-1,1);
		for(int day : zeroOne)
			for(int month : zeroOne)
				for(int year : zeroOne)
					if(day < 0 || month < 0 || year < 0)
						assertFalse(Date.isValidDate(day, month, year), "testDate1, the date " + day+"/"+month+"/"+year + " should not be valid");
					else
						assertTrue(Date.isValidDate(day, month, year),"testDate2, 1/1/1 sould be valid");
	}
	
	/*
	 * input type : Dates avec dépassement sur le mois
	 * on fixe un jour et une année valide, puis on test un dépassement sur le mois
	 */
	@Test
	public void testisValidDate3() {
		assertFalse(Date.isValidDate(1, 13, 1), "1/13/1 should not be valid");
	}
	
	
	/*
	 * input type : Date valide, test exhaustif pour toutes les valeurs de mois valides
	 */
	@Test
	public void testisValidDate4() {
		for(int month=1 ; month < 13 ; month++)
			assertTrue(Date.isValidDate(1, month, 1));

	}
	
	/*
	 * input type : Dates valides pour une année bissextile
	 * Test exhaustif pour une année bissextile
	 * On fixe seulement l'année
	 */
	@Test
	public void testisValidDate5() {
		
		int leapYear = 2000;
		for(int m=1 ; m<13 ; m++) {
			int dMax = daysInMonthLeapYear[m-1];
			for(int d=1 ; d<=dMax ; d++)
				assertTrue(Date.isValidDate(d, m, leapYear),d+"/"+m+"/"+leapYear + " should be valid");
		}		
	}
	
	/*
	 * input type : Dates valides pour une année non bissextile
	 * Test exhaustif pour une année non bissextile
	 * On fixe seulement l'année
	 */
	@Test
	public void testisValidDate6() {
		int noLeapYear = 1900;
		for(int m=1 ; m<13 ; m++) {
			int dMax = daysInMonthNoLeapYear[m-1];
			for(int d=1 ; d<=dMax ; d++)
				assertTrue(Date.isValidDate(d, m, noLeapYear),d+"/"+m+"/"+noLeapYear + " should be valid");
		}
	}
	
	/*
	 * input type : Année invalide exemple 29/2/1900
	 * Cas particulier dune année non bissextile ou l'on fournit le 29 février
	 */
	@Test
	public void testisValidDate7() {
		for(int i=0 ; i<noLeapYears.length ; i++)
			assertFalse(Date.isValidDate(29, 2, noLeapYears[i]),"29/2/" + noLeapYears[i] + " should not be valid");
	}
	
	/*
	 * input type : Date avec depassement sur le jour
	 * Test exhaustif du depassement sur le jour pour une année bissextile
	 * Pas besoin de refaire un test exhaustif pour une année non bissextile, seul le depassement
	 * sur fevrier diffère, donc cas particulier testé dans testisValidDate7
	 */
	@Test
	public void testisValidDate8() {
		
		for(int i=0 ; i<leapYears.length ; i++)
			for(int m=1 ; m<13 ; m++)
				assertFalse(Date.isValidDate(daysInMonthLeapYear[m-1]+1, m, leapYears[i]),
						(daysInMonthLeapYear[m-1]+1) + "/" + m + "/" + leapYears[i] + " should not be valid");
	}
	
	
	
	/*
	 * sous-section test pour nextDate
	 */
	
	/*
	 * Test de la methode nextDate
	 * input -> 1/1/2000
	 * But -> verifier que nextDate renvoit une date valide pour le cas ou l'on incrémente juste le jour
	 */
	@Test
	public void testNextDate1() {
		try {
			Date today = new Date(1,1,2000);
			Date tomorow = today.nextDate();
			assertNotNull(tomorow);
			assertEquals(2,tomorow.getDay());
			assertEquals(1,tomorow.getMonth());
			assertEquals(2000,tomorow.getYear());
		}catch(IllegalArgumentException e) {
			fail("Unexpected exception on Date creation");
		}
	}
	
	/*
	 * Test de la methode nextDate
	 * input -> 31/1/2000
	 * But -> verifier que nextDate renvoit une date valide pour le cas ou on incrémente le mois et remet le jour à 1
	 */
	@Test
	public void testNextDate2() {
		try {
			Date today = new Date(31,1,2000);
			Date tomorow = today.nextDate();
			assertNotNull(tomorow);
			assertEquals(1,tomorow.getDay());
			assertEquals(2,tomorow.getMonth());
			assertEquals(2000,tomorow.getYear());
		}catch(IllegalArgumentException e) {
			fail("Unexpected exception on Date creation");
		}
	}
	
	/*
	 * Test de la methode nextDate
	 * input -> 31/12/2000
	 * But -> verifier que nextDate renvoit une date valide pour le cas ou on incrémente l'année, remet le mois et le jour à 1
	 */
	@Test
	public void testNextDate3() {
		try {
			Date today = new Date(31,12,2000);
			Date tomorow = today.nextDate();
			assertNotNull(tomorow);
			assertEquals(1,tomorow.getDay());
			assertEquals(1,tomorow.getMonth());
			assertEquals(2001,tomorow.getYear());
		}catch(IllegalArgumentException e) {
			fail("Unexpected exception on Date creation");
		}
	}
	
	/*
	 * Test de la methode nextDate
	 * input -> 28/2/2000
	 * But -> verifier que nextDate renvoit bien le 29 fevrier dans le cas d'une année bissextile
	 */
	@Test
	public void testNextDate4() {
		try {
			Date today = new Date(28,2,2000);
			Date tomorow = today.nextDate();
			assertNotNull(tomorow);
			assertEquals(29,tomorow.getDay());
			assertEquals(2,tomorow.getMonth());
			assertEquals(2000,tomorow.getYear());
		}catch(IllegalArgumentException e) {
			fail("Unexpected exception on Date creation");
		}
	}
	
	/*
	 * Test de la methode nextDate
	 * input -> 28/2/2001
	 * But -> verifier que nextDate renvoit bien le 1 mars  dans le cas d'une année non-bissextile
	 */
	@Test
	public void testNextDate5() {
		try {
			Date today = new Date(28,2,2001);
			Date tomorow = today.nextDate();
			assertNotNull(tomorow);
			assertEquals(1,tomorow.getDay());
			assertEquals(3,tomorow.getMonth());
			assertEquals(2001,tomorow.getYear());
		}catch(IllegalArgumentException e) {
			fail("Unexpected exception on Date creation");
		}
	}
	
	/*
	 * Test de la methode nextDate
	 * input -> 1/1/2000
	 * expected result -> 1/1/2001
	 * But -> verifier que l'on arrive à passer une année bissextile entiere
	 */
	@Test
	public void testNextDate6() {
		try {
			Date today = new Date(1,1,2000);
			for(int i=1 ; i<=366 ; i++)
				today = today.nextDate();
			assertNotNull(today);
			assertEquals(1,today.getDay());
			assertEquals(1,today.getMonth());
			assertEquals(2001,today.getYear());
		}catch(IllegalArgumentException e) {
			fail("Unexpected exception on Date creation");
		}
	}
	
	/*
	 * Test de la methode nextDate
	 * input -> 1/1/2001
	 * expected result -> 1/1/2002
	 * But -> verifier que l'on arrive à passer une année non-bissextile entiere
	 */
	@Test
	public void testNextDate7() {
		try {
			Date today = new Date(1,1,2001);
			for(int i=1 ; i<=365 ; i++)
				today = today.nextDate();
			assertNotNull(today);
			assertEquals(1,today.getDay());
			assertEquals(1,today.getMonth());
			assertEquals(2002,today.getYear());
		}catch(IllegalArgumentException e) {
			fail("Unexpected exception on Date creation");
		}
	}
	
	/*
	 * sous-section test pour previousDate
	 */
	/*
	 * Test de la methode previousDate
	 * input -> 2/1/2000
	 * But -> verifier que previousDate renvoit une date valide pour le cas ou l'on décrémente juste le jour
	 */
	@Test
	public void testPreviousDate1() {
		try {
			Date today = new Date(2,1,2000);
			Date yesterday = today.previousDate();
			assertNotNull(yesterday);
			assertEquals(1,yesterday.getDay());
			assertEquals(1,yesterday.getMonth());
			assertEquals(2000,yesterday.getYear());
		}catch(IllegalArgumentException e) {
			fail("Unexpected exception on Date creation");
		}
	}
	
	/*
	 * Test de la methode previousDate
	 * But -> verifier que previousDate renvoit une date valide pour le cas ou on décremente le mois
	 * expected new month -> current month-1
	 * expected new day -> dayInMonth for month-1
	 * Test exhaustif pour une année bissextile
	 */
	@Test
	public void testPrevioustDate2() {
		for(int m=2 ; m<=12 ; m++) {
			try {
				Date today = new Date(1,m,2000);
				Date yesterday = today.previousDate();
				assertNotNull(yesterday);
				assertEquals(m-1,yesterday.getMonth());
				//m-2 car les jours/mois sont indexé à partir de 0
				assertEquals(daysInMonthLeapYear[m-2],yesterday.getDay());
				assertEquals(2000,yesterday.getYear());
			}catch(IllegalArgumentException e) {
				fail("Unexpected exception on Date creation");
			}
		}
	}
	
	/*
	 * Test de la methode previousDate
	 * But -> verifier que previousDate renvoit une date valide pour le cas ou on décremente le mois
	 * expected new month -> current month-1
	 * expected new day -> dayInMonth for month-1
	 * Test exhaustif pour une année non-bissextile
	 */
	@Test
	public void testPreviousDate3() {
		for(int m=2 ; m<=12 ; m++) {
			try {
				Date today = new Date(1,m,2001);
				Date yesterday = today.previousDate();
				assertNotNull(yesterday);
				assertEquals(m-1,yesterday.getMonth());
				//m-2 car les jours/mois sont indexé à partir de 0
				assertEquals(daysInMonthNoLeapYear[m-2],yesterday.getDay());
				assertEquals(2001,yesterday.getYear());
			}catch(IllegalArgumentException e) {
				fail("Unexpected exception on Date creation");
			}
		}
	}
	
	/*
	 * Test de la methode previousDate
	 * But -> verifier que previousDate renvoit une date valide pour le cas ou l'on décrémentre l'année
	 */
	@Test
	public void testPreviousDate4() {
		try {
			Date today = new Date(1,1,2001);
			Date yesterday = today.previousDate();
			assertNotNull(yesterday);
			assertEquals(31,yesterday.getDay());
			assertEquals(12,yesterday.getMonth());
			assertEquals(2000,yesterday.getYear());
		}catch(IllegalArgumentException e) {
			fail("Unexpected exception on Date creation");
		}
	}
	
	/*
	 * Test de la methode previousDate
	 * But -> verifier que l'on arrive a parcourir une année bissextile en arrière
	 */
	@Test
	public void testPreviousDate5() {
		try {
			Date today = new Date(31,12,2000);
			for(int i=1 ; i<=366 ; i++)
				today = today.previousDate();
			assertNotNull(today);
			assertEquals(31,today.getDay());
			assertEquals(12,today.getMonth());
			assertEquals(1999,today.getYear());
		}catch(IllegalArgumentException e) {
			fail("Unexpected exception on Date creation");
		}
	}
	
	/*
	 * Test de la methode previousDate
	 * But -> verifier que l'on arrive a parcourir une année non-bissextile en arrière
	 */
	@Test
	public void testPreviousDate6() {
		try {
			Date today = new Date(31,12,1999);
			for(int i=1 ; i<=365 ; i++)
				today = today.previousDate();
			assertNotNull(today);
			assertEquals(31,today.getDay());
			assertEquals(12,today.getMonth());
			assertEquals(1998,today.getYear());
		}catch(IllegalArgumentException e) {
			fail("Unexpected exception on Date creation");
		}
	}
	
	/*
	 * sous section de test pour compareTo
	 */
	
	/*
	 * But -> verifier que compareTo(null) jette bien une exception
	 */
	@Test
	public void testCompareTo1() {
		Date date = new Date(1,1,1);
		assertThrows(NullPointerException.class, () -> {
	        date.compareTo(null);
	    });
	}
	
	/*
	 * But -> verifier que compareTo > 0
	 */
	@Test
	public void testCompareTo2() {
		Date date1 = new Date(2,1,1);
		Date date2 = new Date(1,1,1);
		int result = date1.compareTo(date2);
		assertTrue(result > 0);
	}
	
	/*
	 * But -> verifier que compareTo == 0
	 */
	@Test
	public void testCompareTo3() {
		Date date1 = new Date(1,1,1);
		Date date2 = new Date(1,1,1);
		int result = date1.compareTo(date2);
		assertTrue(result == 0);
	}
	
	/*
	 * But -> verifier que compareTo < 0
	 */
	@Test
	public void testCompareTo4() {
		Date date1 = new Date(1,1,1);
		Date date2 = new Date(2,1,1);
		int result = date1.compareTo(date2);
		assertTrue(result < 0);
	}
	
	
	/*
	 * fin section avant PIT
	 */
	
	/*
	 * Utilisation de pit > mvn clean install org.pitest:pitest-maven:mutationCoverage
	 * resultats : 
	 * >> Generated 73 mutations Killed 68 (93%)
	 * >> Ran 160 tests (2.19 tests per mutation)
	 * 
	 * Line Coverage 48/61   -> 79%
	 * On commente les setters pour examiner si le manque de couverture provient de la
	 * (étant donné qu'il ne font pas partie de la spécif de l'interface du tp)
	 * 
	 * Ré-execution de PIT 
	 * >> Generated 70 mutations Killed 68 (97%)
	 * >> Ran 162 tests (2.31 tests per mutation)
	 * 
	 * Line Coverage 48/49	-> 97%
	 * En effet le problème provenait des setters jamais utilisés
	 * Reste à trouver la ligne non couverte
	 * 
	 * Après examination de pit-report, la ligne non couverte est celle jettant une exception dans le constructeur
	 * Rajoutons donc un test visant a provoquer cette exception
	 */
	
	@Test
	public void testConstructorShouldThrow() {
		assertThrows(IllegalArgumentException.class, () -> {
	        Date badDate = new Date(-1,-1,-1);
	    });
	}
	
	/*
	 * Ré-execution de PIT
	 * >> Generated 70 mutations Killed 68 (97%)
	 * >> Ran 159 tests (2.27 tests per mutation)
	 * 
	 * Line Coverage 49/49	-> 100%
	 */
	
	/*
	 * 2 mutants ont survécus
	 * 
	 * Le premier mutant modifie l'expression conditionnelle de la ternaire dans previousDate :
	 * newYear = (this.year > 1) ? this.year - 1 : 1;
	 * 
	 * En effet nous n'avions pas écrit de test pour le cas particulier ou nous somme deja sur l'année minimum
	 */
	
	/*
	 * l'année minimum représentable est l'an 1
	 * Donc si on appelle previousDate sur 1/1/1, on devrait boucler au niveau de l'année
	 * input -> 1/1/1
	 * expected result -> 31/12/1
	 */
	@Test
	public void testPreviousDateOnMinimumYearShouldLoop() {
		Date minimumDate = new Date(1,1,1);
		Date yesterday = minimumDate.previousDate();
		assertNotNull(yesterday);
		assertEquals(31,yesterday.getDay());
		assertEquals(12,yesterday.getMonth());
		assertEquals(1,yesterday.getYear());
	}
	
	/*
	 * Le second mutant concerne une methode privée utilisée pour compareTo : plusGrande
	 * 
	 * Code original :
	  private boolean plusGrande(Date other) {
		return (this.year > other.year)
				|| (this.year == other.year && this.month > other.month)
				|| (this.year == other.year && this.month == other.month && this.day > other.day);
		}
		
	 * Code du mutant ayant survécu 
	 * private boolean plusGrande(Date other) {
		return !(this.year > other.year)
				|| (this.year == other.year && this.month > other.month)
				|| (this.year == other.year && this.month == other.month && this.day > other.day);
	}
	
	/*
	 * Nous allons donc proposer une meilleurs couverture de compareTo
	 */
	
	/*
	 * But -> date1 > date2 because date1.year > date2.year
	 */
	@Test
	public void testCompareToPositiveThanksToYear() {
		Date date1 = new Date(1,1,2);
		Date date2 = new Date(31,12,1);
		int result = date1.compareTo(date2);
		assertTrue(result > 0);
	}
	
	/*
	 * But -> date1 > date2 because date1.month > date2.month
	 */
	@Test
	public void testCompareToPositiveThanksToMonth() {
		Date date1 = new Date(1,2,1);
		Date date2 = new Date(1,1,1);
		int result = date1.compareTo(date2);
		assertTrue(result > 0);
	}
	
	/*
	 * But -> date1 > date2 because date1.day > date2.day
	 */
	@Test
	public void testCompareToPositiveThanksToDay() {
		Date date1 = new Date(2,1,1);
		Date date2 = new Date(1,1,1);
		int result = date1.compareTo(date2);
		assertTrue(result > 0);
	}
	
	/*
	 * Ré-exécution de PIT
	>> Generated 70 mutations Killed 70 (100%)
	>> Ran 192 tests (2.74 tests per mutation)
	 */
}
