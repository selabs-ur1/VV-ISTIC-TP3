package fr.istic.vv;

import java.util.Objects;

class Date implements Comparable<Date> {

    private final int day;
    private final int month;
    private final int year;

    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        return (year > 0 && month > 0 && month < 13 && day > 0 && day <= daysInMonth(month, year));
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    public Date nextDate() {
        int nextDay = day + 1;
        int nextMonth = month;
        int nextYear = year;
        if (nextDay > daysInMonth(nextMonth, nextYear)) {
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
        int previousDay = day - 1;
        int previousMonth = month;
        int previousYear = year;
        if (previousDay < 1) {
            previousMonth--;
            if (previousMonth < 1) {
                previousMonth = 12;
                previousYear--;
            }
            previousDay = daysInMonth(previousMonth, previousYear);
        }
        return new Date(previousDay, previousMonth, previousYear);
    }

    public int compareTo(Date other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare to null");
        }
        if (year != other.year) {
            return year - other.year;
        }
        if (month != other.month) {
            return month - other.month;
        }
        return day - other.day;
    }

    private static int daysInMonth(int month, int year) {
        switch (month) {
            case 2:
                return isLeapYear(year) ? 29 : 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }
}