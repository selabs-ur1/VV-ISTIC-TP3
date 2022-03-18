package fr.istic.vv;

/**
 * Class Date
 */
class Date implements Comparable<Date> {

    private final Integer[] numberOfDaysInEachMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int day, month, year;

    /**
     * Constructeur de date
     *
     * @param day   : jour
     * @param month : mois
     * @param year  : année
     */
    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Date invalide !");
        }
        if (isLeapYear(year)) numberOfDaysInEachMonth[1] = 29;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Une date valide est une date avec
     * - un mois compris entre 1 et 12
     * - un jour compris entre ceux du mois proposé (y compris pour les années bissextiles, cf Février)
     *
     * @param day   : jour
     * @param month : mois
     * @param year  : année
     * @return true si c'est une date valide, false sinon
     */
    public boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12) return false;
        return (month == 2 && isLeapYear(year) && day > 0 && day <= 29) ||
                (day > 0 && day <= numberOfDaysInEachMonth[month - 1]);
    }

    /**
     * Une année est bissextile est une année qui contient 366 (29 en février) jours tous les 4 ans
     * au lieu de 365
     *
     * @param year : année
     * @return true si elle est une année bissextile, false sinon
     */
    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    /**
     * Date suivante, si le jour est le jour maximum du mois, on augmente de mois,
     * et on refait la même chose pour ce dernier
     *
     * @return la nouvelle date
     */
    public Date nextDate() {
        // Si le jour est le max du mois courant, on update et on fait la même chose pour le mois
        // Pas besoin de gérer les années bissextiles --> le jour a été update dans le constructeur
        if ((day == numberOfDaysInEachMonth[month - 1])) {
            day = 1;
            if (month == 12) {
                month = 1;
                year++;
            } else {
                month++;
            }
        } else {
            // Sinon on update juste le nombre de jour
            day++;
        }
        return new Date(day, month, year);
    }

    /**
     * Date précédente, si le jour est le jour minimum du mois ( 1 ), on diminue de mois,
     * et on refait la même chose pour ce dernier
     *
     * @return la nouvelle date
     */
    public Date previousDate() {
        // On vérifie que le jour n'est pas le premier du mois, si c'est le cas on revient au dernier
        // jour du mois précédent. On fait de même pour le mois.
        if (day == 1) {
            if (month == 1) {
                day = numberOfDaysInEachMonth[11];
                month = 12;
                year--;
            } else {
                day = numberOfDaysInEachMonth[month - 2];
                month--;
            }
        } else {
            // Sinon on update juste le nombre de jour
            day--;
        }
        return new Date(day, month, year);
    }

    /**
     * Compare 2 dates et retourne un nombre position si la date courant est supérieure,
     * 0 si elles sont égales et -1 si inferieure
     *
     * @param other : autre date
     * @return 1 si supérieure, 0 si égale, -1 si inferieure
     */
    public int compareTo(Date other) {
        if (day == other.getDay() && month == other.getMonth() && year == other.getYear()) return 0;
        if (year > other.year) {
            return 1;
        } else if (year == other.year) {
            // check month
            if (month > other.month) {
                return 1;
            } else if (month == other.month) {
                // Check day
                return day > other.day ? 1 : -1;
            }
        }
        return -1;
    }

    /**
     * Get day
     *
     * @return jour
     */
    public int getDay() {
        return day;
    }

    /**
     * Get month
     *
     * @return mois
     */
    public int getMonth() {
        return month;
    }

    /**
     * Get year
     *
     * @return l'année
     */
    public int getYear() {
        return year;
    }


}
