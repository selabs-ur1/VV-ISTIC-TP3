package fr.istic.vv;

import java.time.Year;
import java.util.Calendar;

class Date implements Comparable<Date> {

    private final Calendar date;

    /** The constructor throws an exception if the three given integers do not form a valid date. */
    public Date(int day, int month, int year) {
        Calendar.Builder builder = new Calendar.Builder();
        builder.setDate(year,month-1,day);
        builder.setLenient(false);
        this.date = builder.build();
    }

    /** isValidDate returns true if the three integers form a valid year, otherwise false. */
    public static boolean isValidDate(int day, int month, int year) {
        try{
            new Date(day,month,year);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    /** isLeapYear says if the given integer is a leap year. */
    public static boolean isLeapYear(int year) {
        return Year.isLeap(year);
    }

    /** NextDate returns a new Date instance representing the date of the following day. */
    public Date nextDate() {
        Calendar next = (Calendar) date.clone();
        next.add(Calendar.DAY_OF_MONTH,1);
        return new Date(next.get(Calendar.DAY_OF_MONTH), next.get(Calendar.MONTH)+1, next.get(Calendar.YEAR));
    }

    /** PreviousDate returns a new Date instance representing the date of the previous day. */
    public Date previousDate() {
        Calendar next = (Calendar) date.clone();
        next.add(Calendar.DAY_OF_MONTH,-1);
        return new Date(next.get(Calendar.DAY_OF_MONTH), next.get(Calendar.MONTH)+1, next.get(Calendar.YEAR));
    }

    /** CompareTo follows the Comparable convention:
     - date.compareTo(other) returns a positive integer if date is posterior/after to other
     - date.compareTo(other) returns a negative integer if date is anterior/before to other
     - date.compareTo(other) returns 0 if date and other represent the same date.
     - the method throws a NullPointerException if other is null */
    public int compareTo(Date other) {
        return this.date.compareTo(other.date);
    }

    public int getDay(){ return date.get(Calendar.DAY_OF_MONTH); }

    public int getMonth(){ return date.get(Calendar.MONTH)+1; }

    public int getYear(){ return date.get(Calendar.YEAR); }
}