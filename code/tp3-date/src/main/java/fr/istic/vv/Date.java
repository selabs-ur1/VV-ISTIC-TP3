package fr.istic.vv;

import java.time.LocalDate;
import java.time.Year;
import java.util.Calendar;
import java.util.GregorianCalendar;

class Date implements Comparable<Date> {

    private final Calendar internalDate;

    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Inputted date is not valid.");
        }
        internalDate = new GregorianCalendar(year, month, day);
    }

    public static boolean isValidDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setLenient(false); // Don't automatically convert invalid date.
        calendar.set(year, month - 1, day, 0, 0, 0);
        try {
            calendar.getTimeInMillis(); // Lazy update, throws IllegalArgumentException if invalid date.
            return true;
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isLeapYear(int year) {
        return Year.isLeap(year);
    }

    public Date nextDate() {
        Calendar cal = (Calendar) this.internalDate.clone();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return new Date(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
    }

    public Date previousDate() {
        Calendar cal = (Calendar) this.internalDate.clone();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return new Date(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
    }

    public Calendar getAsCalendar() {
        return this.internalDate;
    }

    public int compareTo(Date other) {
        return this.internalDate.compareTo(other.getAsCalendar());
    }
}