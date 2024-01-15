package fr.istic.vv;

/*
 * Contrairement à certaines classe date qui indexe les mois et les jours à partir de 0
 * On choisit de faire commencer les mois et les jours à 1, comme dans la vie courante
 * Seules les années positives sont acceptées (on ne gère pas le temps avant jésus)
 */
class Date implements Comparable<Date> {

	private int day, month, year;

	/*
	 * Dans le cas ou l'on cherche à creer une date avec des paramètres invalide, on
	 * renvoit une exception
	 */
	public Date(int day, int month, int year) throws IllegalArgumentException {
		if (!isValidDate(day, month, year))
			throw new IllegalArgumentException("Invalid parameters to create a Date");
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public static boolean isValidDate(int day, int month, int year) {
		return validYear(year) && validMonth(month) && validDay(day, month, year);
	}

	private static boolean validYear(int year) {
		return year > 0;
	}

	private static boolean validMonth(int month) {
		return (month >= 1) && (month <= 12);
	}

	/*
	 * mois et de année sont requis pour valider la valeur de day
	 */
	private static boolean validDay(int day, int month, int year) {
		int maxDay = daysInMonth(month, year);
		return (day > 0) && (day <= maxDay);
	}

	private static int daysInMonth(int month, int year) {
		switch (month) {
		case 4: // avril
		case 6: // auin
		case 9: // septembre
		case 11: // novembre
			return 30;
		case 2: // fevrier
			return isLeapYear(year) ? 29 : 28;
		default:// le reste
			return 31;
		}
	}

	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	public Date nextDate() {
		int maxDay = daysInMonth(this.month, this.year);
		int newDay = this.day + 1;
		int newMonth = this.month;
		int newYear = this.year;

		if (newDay > maxDay) {
			// on passe au mois suivant, risque de passer à l'année suivante
			newDay = 1;

			if (this.month >= 12) {
				newMonth = 1;
				newYear++;
			} else {
				newMonth++;
			}
		}

		return new Date(newDay, newMonth, newYear);
	}

	public Date previousDate() {
		int newDay, newMonth, newYear;

		if (this.day > 1) {
			return new Date(this.day - 1, this.month, this.year);
		}

		if (this.month > 1) {//passage au mois précédent
			newMonth = this.month - 1;
			newYear = this.year;
		} else {//passage à lannée précédente
			newMonth = 12;
			newYear = (this.year > 1) ? this.year - 1 : 1;
			//si on est deja sur lan 1, on boucle dessus
			//on pourrait ajouter un exception disant que lon ne peut pas passer au jour précédent
		}

		newDay = daysInMonth(newMonth, newYear);

		return new Date(newDay, newMonth, newYear);
	}

	public int compareTo(Date other) throws NullPointerException{
		if(other == null)
			throw new NullPointerException("Null param other");
		if(this.plusGrande(other))
			return 1;
		else if(this.memeJour(other))
			return 0;
		else
			return -1;
	}
	
	private boolean plusGrande(Date other) {
		return (this.year > other.year)
				|| (this.year == other.year && this.month > other.month)
				|| (this.year == other.year && this.month == other.month && this.day > other.day);
	}
	private boolean memeJour(Date other){
		return (this.year == other.year) && (this.month == other.month) && (this.day == other.day);
	}

	public int getDay() {
		return this.day;
	}

	/*public void setDay(int day) throws IllegalArgumentException {
		if (!isValidDate(day, this.month, this.year))
			throw new IllegalArgumentException("Invalid value for day");
		this.day = day;
	}*/

	public int getMonth() {
		return month;
	}

	/*public void setMonth(int month) {
		if (!isValidDate(this.day, month, this.year))
			throw new IllegalArgumentException("Invalid value for month");
		this.month = month;
	}*/

	public int getYear() {
		return year;
	}

	/*public void setYear(int year) {
		if (!isValidDate(this.day, this.month, year))
			throw new IllegalArgumentException("Invalid value for year");
		this.year = year;
	}*/
}