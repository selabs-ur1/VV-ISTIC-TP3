package fr.istic.vv;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;

    private static final Map<Integer, Integer> lastDayPerMonth = Stream.of(new Object[][] {
            {1, 31},
            {2, 28},
            {3, 31},
            {4, 30},
            {5, 31},
            {6, 30},
            {7, 31},
            {8, 31},
            {9, 30},
            {10, 31},
            {11, 30},
            {12, 31}
    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (Integer) data[1]));

    public Date(int day, int month, int year) {
        if(!isValidDate(day, month, year)) throw new IllegalArgumentException("The Date is invalide");

        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if(year < 0) return false;
        if(month < 1 || month > 12) return false;
        if(isLeapYear(year) && month==2) return (day >= 1) && (day <= 29);
        if(!isLeapYear(year) && month==2) return (day >= 1) && (day <= 28);
        return (day >= 1) && (day <= lastDayPerMonth.get(month));
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public Date nextDate() {
        int nextDay = day + 1;
        int nextMonth = month;
        int nextYear = year;

        if (nextMonth == 2 && nextDay == 29 && isLeapYear(nextYear)) {
            return new Date(nextDay, nextMonth, nextYear);
        }

        if (nextDay > lastDayPerMonth.get(month)) {
            nextDay = 1;
            nextMonth++;
            if (nextMonth > 12) {
                nextMonth = 1;
                nextYear++;
            }
        }

        if (!isValidDate(nextDay, nextMonth, nextYear)) {
            throw new IllegalStateException("Invalid next date calculated.");
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

            previousDay = lastDayPerMonth.get(previousMonth);
        }

        if (!isValidDate(previousDay, previousMonth, previousYear)) {
            throw new IllegalStateException("Invalid previous date calculated.");
        }

        return new Date(previousDay, previousMonth, previousYear);
    }

    public int compareTo(Date other) {
        if (other == null) throw new NullPointerException("Cannot compare to null.");

        int yearComparison = Integer.compare(year, other.year);
        if (yearComparison != 0) return yearComparison;

        int monthComparison = Integer.compare(month, other.month);
        if (monthComparison != 0) return monthComparison;

        return Integer.compare(day, other.day);
    }

    //Required to pass some tests
    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }

}
