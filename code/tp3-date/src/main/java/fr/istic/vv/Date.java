package fr.istic.vv;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class Date implements Comparable<Date> {
    public int day;
    public int month;
    public int year;
    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        //set of month with 31 days
        Set<Integer> month31 = Set.of(1, 3, 5, 7, 8, 10, 12);
        //set of month with 30 days
        Set<Integer> month30 = Set.of(4, 6, 9, 11);
        //set of month with 28 days
        if(day < 1 || month < 1 || month > 12) {
            return false;
        } else if (month31.contains(month) && day > 31) {
            return false;
        } else if (month30.contains(month) && day > 30) {
            return false;
        } else if (month == 2 && day > 28) {
            if(isLeapYear(year) && day == 29) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
            return true;
        }
        return false;
    }

    public Date nextDate() {
        int newDay = day;
        int newMonth = month;
        int newYear = year;
        if (day == 31 && month == 12) {
            newDay = 1;
            newMonth = 1;
            newYear++;
        } else if (day == 31) {
            newDay = 1;
            newMonth++;
        } else if (day == 30 && Set.of(4, 6, 9, 11).contains(month)) {
            newDay = 1;
            newMonth++;
        } else if (day == 28 && month == 2 && isLeapYear(year)) {
            newDay = 29;
        } else if (day == 28 && month == 2) {
            newDay = 1;
            newMonth++;
        } else {
            newDay++;
        }
        return new Date(newDay, newMonth, newYear);
    }

    public Date previousDate() {
        int oldDay = day;
        int oldMonth = month;
        int oldYear = year;
        if(day==1 && month == 1) {
            oldDay = 31;
            oldMonth = 12;
            oldYear--;
        } else if(day == 1 && Set.of(5, 7, 10, 12).contains(month)) {
            oldDay = 30;
            oldMonth--;
        } else if(day == 1 && Set.of(2, 4, 6, 8, 9, 11).contains(month)) {
            oldDay = 31;
            oldMonth--;
        } else if(day == 1 && month == 3 && isLeapYear(year)) {
            oldDay = 29;
            oldMonth--;
        } else if(day == 1 && month == 3) {
            oldDay = 28;
            oldMonth--;
        } else {
            oldDay--;
        }
        return new Date(oldDay, oldMonth, oldYear);
    }

    public int compareTo(Date other) {
        //return 0 if the dates are equal, 1 if the date is after the other date, -1 if the date is before the other date,
        // NullPointerException if other is null
        if (other == null) {
            throw new NullPointerException("The date is null");
        }
        if (year > other.year) {
            return 1;
        } else if (year < other.year) {
            return -1;
        } else if (month > other.month) {
            return 1;
        } else if (month < other.month) {
            return -1;
        } else if (day > other.day) {
            return 1;
        } else if (day < other.day) {
            return -1;
        } else {
            return 0;
        }
    }

}