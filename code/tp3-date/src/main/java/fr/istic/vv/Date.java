package fr.istic.vv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;

    final private static List<Integer> month31days = Arrays.asList(1, 3, 5, 7, 8, 10, 12);
    final private static List<Integer> month30days = Arrays.asList(4, 6, 9, 11);

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        return year >= 0 && month <= 12 && month >= 1 && day >= 1 &&
                !(month == 2 && day >= 29) && !(month30days.contains(month) && day > 30) &&
                !(month31days.contains(month) && day > 31);
    }

    public static boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else return year % 100 != 0;
    }

    public Date nextDate() {
        this.day++;
        if (isValidDate(this.day, this.month, this.year)) return new Date(this.day, this.month, this.year);
        else {
            this.day = 1;
            this.month++;
            if (this.month >= 13) {
                this.month = 1;
                this.year++;
            }
            return new Date(this.day, this.month, this.year);
        }
    }

    public Date previousDate() {
        this.day--;
        if (isValidDate(this.day, this.month, this.year)) return new Date(this.day, this.month, this.year);
        else {
            this.month--;
            if (this.month < 1) {
                this.month = 12;
                this.year--;
            }
            if (month == 2 && !isLeapYear(this.year)) this.day = 28;
            else if (month == 2 && isLeapYear(this.year)) this.day = 29;
            else if (is30Day(month)) this.day = 30;
            else this.day = 31;

            return new Date(this.day, this.month, this.year);
        }
    }

    public int compareTo(Date other) {
        if (other == null) throw new NullPointerException();
        if (this.year > other.year) return 1;
        else if (this.year < other.year) return -1;
        else if (this.month > other.month) return 1;
        else if (this.month < other.month) return -1;
        else return Integer.compare(this.day, other.day);
    }

    //### API PRIVEE ####

    private static boolean is30Day(int month) {
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                return true;
            default:
                return false;
        }
    }
}