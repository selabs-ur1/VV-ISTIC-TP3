package fr.istic.vv;

import java.util.Objects;

class Date implements Comparable<Date> {

    public int day;
    public int month;
    public int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public boolean isValidDate() {
        // Vérification de l'année
        if (year < 0) {
            return false;
        }

        // Vérification du mois
        if (month < 1 || month > 12) {
            return false;
        }

        // Vérification du jour
        int maxDays = getMaxDaysInMonth();
        return day >= 1 && day <= maxDays;
    }

    public int getMaxDaysInMonth() {
        switch (month) {
            case 1: // Janvier
            case 3: // Mars
            case 5: // Mai
            case 7: // Juillet
            case 8: // Août
            case 10: // Octobre
            case 12: // Décembre
                return 31;

            case 4: // Avril
            case 6: // Juin
            case 9: // Septembre
            case 11: // Novembre
                return 30;

            case 2: // Février
                return isLeapYear() ? 29 : 28;

            default:
                return 0; // Mois invalide
        }
    }

    public boolean isLeapYear() {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public Date getNextDate() {

        int nextDay = day;
        int nextMonth = month;
        int nextYear = year;

        int maxDaysInMonth = getMaxDaysInMonth();

        if (day == maxDaysInMonth) {
            // Si c'est le dernier jour du mois, passer au premier jour du mois suivant
            nextDay = 1;
            if (month == 12) {
                // Si c'est décembre, passer au premier jour de janvier de l'année suivante
                nextMonth = 1;
                nextYear++;
            } else {
                // Sinon, passer au premier jour du mois suivant
                nextMonth++;
            }
        } else {
            // Sinon, simplement passer au jour suivant dans le même mois
            nextDay++;
        }

        return new Date(nextDay, nextMonth, nextYear);

    }

    public Date getPreviousDate() {

        int previousDay = day;
        int previousMonth = month;
        int previousYear = year;

        if (day == 1) {
            // Si c'est le premier jour du mois, passer au dernier jour du mois précédent
            if (month == 1) {
                // Si c'est janvier, passer au dernier jour de décembre de l'année précédente
                previousMonth = 12;
                previousYear--;
            } else {
                // Sinon, passer au dernier jour du mois précédent
                previousMonth--;
            }

            Date previousMonthDate = new Date(1, previousMonth, previousYear);
            previousDay = previousMonthDate.getMaxDaysInMonth();

        } else {
            // Sinon, simplement passer au jour précédent dans le même mois
            previousDay--;
        }

        return new Date(previousDay, previousMonth, previousYear);

    }

    public int compareTo(Date other) {
        Objects.requireNonNull(other, "La date 'other' ne doit pas être null.");

        // Comparaison par année
        int yearComparison = Integer.compare(this.year, other.year);
        if (yearComparison != 0) {
            return yearComparison;
        }

        // Comparaison par mois
        int monthComparison = Integer.compare(this.month, other.month);
        if (monthComparison != 0) {
            return monthComparison;
        }

        // Comparaison par jour
        return Integer.compare(this.day, other.day);
    }

    public boolean equalsDates(Date date) {

        if (this.day == date.day && this.month == date.month && this.year == date.year) {
            return true;
        }
        return false;
    }
}