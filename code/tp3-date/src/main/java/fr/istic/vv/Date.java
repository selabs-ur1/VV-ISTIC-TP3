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
        if (year < 1 || month < 1 || month > 12) {
            return false;
        }

        int maxDaysInMonth = 31;
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDaysInMonth = 30;
        } else if (month == 2) {
            maxDaysInMonth = isLeapYear(year) ? 29 : 28;
        }

        return day >= 1 && day <= maxDaysInMonth;
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public Date nextDate() {
        int nextDay = day + 1;
        int nextMonth = month;
        int nextYear = year;

        if (nextDay > getDaysInMonth(month, year)) {
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
            }
            prevDay = getDaysInMonth(prevMonth, prevYear);
        }

        return new Date(prevDay, prevMonth, prevYear);
    }

    private int getDaysInMonth(int month, int year) {
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return 31;
        }
    }

    @Override
    public int compareTo(Date other) {
        Objects.requireNonNull(other);

        if (year != other.year) {
            return Integer.compare(year, other.year);
        } else if (month != other.month) {
            return Integer.compare(month, other.month);
        } else {
            return Integer.compare(day, other.day);
        }
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