package fr.istic.vv;

import java.util.Calendar;

class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;
    //The constructor throws an exception if the three given integers do not form a valid date.
    public Date(int day, int month, int year) { 
        if (!isValidDate(day, month, year)) throw new IllegalArgumentException("Invalid date");
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getYear() {
        return this.year;
    }
    public int getMonth() {
        return this.month;
    }
    public int getDay() {
        return this.day;
    }

    public static boolean isValidDate(int day, int month, int year) { 
        if (year < 0) return false;
        if (month < 1 || month > 12) return false;
        if (day < 1) return false;
        if (month == 2) {
            if (isLeapYear(year)) return day <= 29;
            else return day <= 28;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) return day <= 30;
        return day <= 31;
    }   


    public static boolean isLeapYear(int year) {
         return (year%4==0 && (year%100 != 0 || year%400 == 0)); 
    }

    public Date nextDate() {  
            int day = this.day;
            int month = this.month;
            int year = this.year;
        if(day==31 && month==12) {
            return new Date(1, 1, year+1);
        }
        if(day==31 && (month==1 || month==3 || month==5 || month==7 || month==8 || month==10)) {
            return new Date(1, month+1, year);
        }
        else if(day==30 && (month==4 || month==6 || month==9 || month==11)) {
            return new Date(1, month+1, year);
        }
        else if(day==28 && month==2 && !isLeapYear(year)) {
            return new Date(1, month+1, year);
        }
        else if(day==29 && month==2 && isLeapYear(year)) {
            return new Date(1, month+1, year);
        }
        else {
            return new Date(day+1, month, year);
        }
    }

    public Date previousDate() { 
        int day = this.day;
        int month = this.month;
        int year = this.year;
    if(day==1 && month==1) {
        return new Date(31, 12, year-1);
    }
    if(day==1 && (month==5 || month==7 || month==10 || month==12)) {
        return new Date(30, month-1, year);
    }
    else if(day==1 && (month==2 || month==4 || month==6 || month==9 || month==11 || month==8)) {
        return new Date(31, month-1, year);
    }
    else if(day==1 && month==3 && !isLeapYear(year)) {
        return new Date(28, month-1, year);
    }
    else if(day==1 && month==3 && isLeapYear(year)) {
        return new Date(29, month-1, year);
    }
    else {
        return new Date(day-1, month, year);
    }
    }

    @Override
    public int compareTo(Date other) { 
    if (other == null) throw new NullPointerException("The other date cannot be null");

    // Compare years first
    if (this.year != other.year) {
        return Integer.compare(this.year, other.year);
    }
    // If years are equal, compare months
    if (this.month != other.month) {
        return Integer.compare(this.month, other.month);
    }
    // If months are also equal, compare days
    return Integer.compare(this.day, other.day);
}


}