package fr.istic.vv;

import java.util.Arrays;

class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;

    private static final int[] DAYS_IN_MONTH = {
            0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public Date(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new IllegalArgumentException("Invalid date");
        }
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (year > 0 && month >= 1 && month <= 12) {
            int maxDays = DAYS_IN_MONTH[month];
            if (month == 2 && isLeapYear(year)) {
                maxDays = 29;
            }
            return day >= 1 && day <= maxDays;
        }
        return false;
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public Date nextDate() {
        int nextDay = day + 1;
        int nextMonth = month;
        int nextYear = year;

        if (nextDay > DAYS_IN_MONTH[nextMonth]) {
            nextDay = 1;
            nextMonth++;
            if (nextMonth > 12) {
                nextMonth = 1;
                nextYear++;
            }
        }

        return new Date(nextDay, nextMonth, nextYear);
    }

    public Date previousDate() {
        int prevDay = day - 1;
        int prevMonth = month;
        int prevYear = year;

        if (prevDay < 1) {
            prevMonth--;
            if (prevMonth < 1) {
                prevMonth = 12;
                prevYear--;

                if (prevYear < 1) {
                    // Handle the edge case where the year is 1
                    prevYear = 9999;
                }
            }

            prevDay = DAYS_IN_MONTH[prevMonth];
            if (prevMonth == 2 && isLeapYear(prevYear)) {
                prevDay = 29;
            }
        }

        return new Date(prevDay, prevMonth, prevYear);
    }

    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        }
        if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        }
        return Integer.compare(this.day, other.day);
    }

    @Override
    public String toString() {
        return String.format("%02d-%02d-%04d", day, month, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date other = (Date) obj;
            return this.day == other.day && this.month == other.month && this.year == other.year;
        }
        return false;
    }

    public static void main(String[] args) {

    }

    public Integer getYear() {
        return null;
    }
}
