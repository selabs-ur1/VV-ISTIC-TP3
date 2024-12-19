package fr.istic.vv;

import java.time.Month;
import java.util.Objects;

class Date implements Comparable<Date> {

    private static final int[] DAYS_IN_MONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private static final int[] DAYS_IN_MONTH_LEAP = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private int day;
    private int month;
    private  int year;

    private boolean isLeapYear;

    public Date(int day, int month, int year) throws Exception {
        if(isValidDate(day,month,year)) {
            this.day = day;
            this.month = month;
            this.year = year;
            isLeapYear = isLeapYear(year);
        }
        else {
            throw new IllegalArgumentException("Date invalide : " + day + "/" +month+ "/"+ year);
        }
    }

    public static boolean isValidDate(int day, int month, int year) {

        if (month < 1 || month > 12) {
            return false;
        } else if (year <= 0) {
            return false;
        }

        int[] daysInMonth = (isLeapYear(year))? DAYS_IN_MONTH_LEAP: DAYS_IN_MONTH;

        return 0 < day &&  day <=  daysInMonth[month-1];
    }

    public static boolean isLeapYear(int year) {
            return year >0 && ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0));
    }

    public Date nextDate() throws Exception {
        int dayTomorrow = day +1;
        int monthTomorrow = month;
        int yearTomorrow = year;

        int[] daysInMonth = (isLeapYear)? DAYS_IN_MONTH_LEAP: DAYS_IN_MONTH;

        if(dayTomorrow > daysInMonth[month-1]){
            dayTomorrow = 1;
            monthTomorrow++;
            if(monthTomorrow > 12) {
                monthTomorrow = 1;
                yearTomorrow++;
            }
        }

        return new Date(dayTomorrow, monthTomorrow, yearTomorrow);
    }

    public Date previousDate() throws Exception {
        int dayTomorrow = day -1;
        int monthTomorrow = month;
        int yearTomorrow = year;

        int[] daysInMonth = (isLeapYear)? DAYS_IN_MONTH_LEAP: DAYS_IN_MONTH;

        if(dayTomorrow == 0){
            monthTomorrow--;

            if(monthTomorrow < 1) {
                monthTomorrow = 12;
                yearTomorrow--;
            }

            dayTomorrow = daysInMonth[monthTomorrow-1];
        }

        return new Date(dayTomorrow, monthTomorrow, yearTomorrow);
    }

    public int compareTo(Date other) {
        Objects.requireNonNull(other, "La date comparer est nul");


        int diffYear = other.getYear() - year;
        if(diffYear != 0)
            return diffYear;

        int diffMonth =  other.getMonth() - month ;
        if(diffMonth != 0)
            return diffMonth;

        return other.getDay() -day;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}