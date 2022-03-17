package fr.istic.vv;

import java.time.DateTimeException;
import java.util.Calendar;
import java.util.GregorianCalendar;

class Date implements Comparable<Date> {

    // Create the local calendar (Calendar.getInstance() will create GregorianCalendar())
    private Calendar date = new GregorianCalendar();

    // Add getter for the access
    // Day start at 1
    public int getDay(){
        return this.date.get(Calendar.DAY_OF_MONTH);
    }

    // Month start a 0 in Gregorian Calendar
    public int getMonth(){
        return this.date.get(Calendar.MONTH);
    }
    public int getYear(){
        return this.date.get(Calendar.YEAR);
    }

    /**
     * Date constructor
     * @param day start at 1
     * @param month start at 0
     * @param year
     * @throws DateTimeException
     */
    public Date(int day, int month, int year) throws DateTimeException {
        if(isValidDate(day, month, year)){
            this.date.set(year, month, day);
        }
        else {
            throw new DateTimeException("The three given integers do not form a valid date");
        }
    }

    /**
     * Returns if it is a valid Date
     * @param day of the date in int
     * @param month of the date in int
     * @param year of the date in int
     * @return true if the three integers form a valid date, otherwise false
     */
    public static boolean isValidDate(int day, int month, int year) {
        if (month >= 0 && month <= 11) {
            if (month == 0 || month == 2 || month == 4 || month == 6
                    || month == 7 || month == 9 || month == 11) {
                return day >= 1 && day <= 31;
            }
            else if (month == 1 ) {
                if(isLeapYear(year)) {
                    return day >=1 && day <=29;
                }
                else {
                    return day >=1 && day <=28;
                }
            }
            else {
                return day >=1 && day <= 30;
            }
        }
        return false;
    }

    public static boolean isLeapYear(int year) {
        GregorianCalendar leapYear = new GregorianCalendar();
        return leapYear.isLeapYear(year);
    }

    public Date nextDate() {
        date.add(Calendar.DATE,1);
        return new Date(date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.MONTH), date.get(Calendar.YEAR));
    }

    public Date previousDate() {
        date.add(Calendar.DATE,-1);
        return new Date(date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.MONTH), date.get(Calendar.YEAR));
    }

    /**
     * compareTo follows the `Comparable` convention:
     * the method throws a `NullPointerException` if `other` is `null`
     * @param other comparate date
     * @return a positive integer if `date` is posterior to `other`,
     *         a negative integer if `date` is anterior to `other`,
     *         `0` if `date` and `other` represent the same date.
     * @throws NullPointerException
     */
    public int compareTo(Date other) throws NullPointerException {
        return this.date.compareTo(other.date);
    }
}