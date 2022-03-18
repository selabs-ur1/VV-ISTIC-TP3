package fr.istic.vv;

import java.time.YearMonth;

class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
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

    public static boolean isValidDate(int day, int month, int year) {
        if(day > 0 && month > 0 && month <= 12){
            YearMonth yearMonthObject = YearMonth.of(year, month);
            int daysInMonth = yearMonthObject.lengthOfMonth();
            return day <= daysInMonth;
        }
        return false;
    }

    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));    }

    public Date nextDate() {

        if(Date.isValidDate(this.day, this.month, this.year)){
            YearMonth yearMonthObject = YearMonth.of(this.year, this.month);
            int daysInMonth = yearMonthObject.lengthOfMonth();
            if(this.day+1 < daysInMonth){
                return new Date(day+1, month, year);
            }else {
                if(this.month == 12 && this.day == 31){
                    return new Date(1, 1, year+1);
                }
                return new Date(1, month+1, year);
            }
        }
        return null;
    }

    public Date previousDate() {
        if(Date.isValidDate(this.day, this.month, this.year)) {
            //premier mais pas janv, changemement de mois
            if(this.day == 1 && this.month!=1){
                YearMonth yearMonthObject = YearMonth.of(this.year, this.month-1);
                int daysInMonth = yearMonthObject.lengthOfMonth();
                return new Date(daysInMonth, month-1, year);
            }else {
                //premier janv
                if(this.month == 1 && this.day == 1){
                    return new Date(31, 12, year-1);
                }
                return new Date(day-1, month, year);
            }
        }
        return null;

    }

    public int compareTo(Date other) {

        if(Date.isValidDate(other.day, other.month, other.year)) {
            //Même date -> 0
            if (this.day == other.day && this.month == other.month && this.year == other.year) {
                return 0;
            }
            // date > other -> 1
            // changement année
            //même année delta mois
            //même année, même mois, delta date
            if (    this.year > other.year
                    || (this.year == other.year && this.month > other.month)
                    || (this.year == other.year && this.month == other.month && this.day > other.day)
            ) {
                return 1;
            } else {
                return -1;
            }
        }else{
            throw new NullPointerException("null");
        }
    }

}