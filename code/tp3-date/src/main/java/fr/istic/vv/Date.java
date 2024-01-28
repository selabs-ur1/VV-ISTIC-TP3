package fr.istic.vv;

import java.util.Objects;

class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12) return false;
        if (day < 1 || day > 31) return false;
        if (month == 2) {
            if (isLeapYear(year)) {
                return day <= 29;
            } else {
                return day <= 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return day <= 30;
        }
        return true;
    }

    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        return year % 4 == 0;
    }

    public Date nextDate() {
        int day = this.day;
        int month = this.month;
        int year = this.year;
        if (day == 31 && month == 12) {
            day = 1;
            month = 1;
            year++;
        } else if (day == 31) {
            day = 1;
            month++;
        } else if (month == 2) {
            if (isLeapYear(year)) {
                if (day == 29) {
                    day = 1;
                    month++;
                } else {
                    day++;
                }
            } else {
                if (day == 28) {
                    day = 1;
                    month++;
                } else {
                    day++;
                }
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day == 30) {
                day = 1;
                month++;
            } else {
                day++;
            }
        } else {
            day++;
        }
        return new Date(day, month, year);
    }

    public Date previousDate() {
        int day = this.day;
        int month = this.month;
        int year = this.year;
        if (day == 1 && month == 1) {
            day = 31;
            month = 12;
            year--;
        } else if (day == 1) {
            month--;
            if (month == 2) {
                if (isLeapYear(year)) {
                    day = 29;
                } else {
                    day = 28;
                }
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                day = 30;
            } else {
                day = 31;
            }
        } else {
            day--;
        }
        return new Date(day, month, year);
    }

    public int compareTo(Date other) {
        Objects.requireNonNull(other);
        if (this.year < other.year) return -1;
        if (this.year > other.year) return 1;
        if (this.month < other.month) return -1;
        if (this.month > other.month) return 1;
        if (this.day < other.day) return -1;
        if (this.day > other.day) return 1;
        return 0;
    }

}
