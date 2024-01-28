package fr.istic.vv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Date implements Comparable<Date> {
    private static final List<Integer> MONTH_WITH_31_DAYS = new ArrayList<>(List.of(1, 3, 5, 7, 8, 10, 12));
    private static final List<Integer> MONTH_WITH_30_DAYS = new ArrayList<>(List.of(4, 6, 9, 11));
    private static final int FEBRUARY = 2;

    private final int day;
    private final int month;
    private final int year;

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Date(int day, int month, int year) {
        if(!isValidDate(day, month, year)) {
            throw new RuntimeException("Int given should form a valid date ! ");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        int lastDay = 0;
        if (MONTH_WITH_31_DAYS.contains(month)) {
            lastDay = 31;
        } else if (MONTH_WITH_30_DAYS.contains(month)) {
            lastDay = 30;
        } else if (month == FEBRUARY) {
            if(isLeapYear(year)) {
                lastDay = 29;
            } else {
                lastDay = 28;
            }
        }
        return day <= lastDay && day >= 1;
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }

    public Date nextDate() {
        int newDay = day + 1;
        int newMonth = month;
        int newYear = year;
        if (!isValidDate(newDay, month, year)) {
            newDay = 1;
            newMonth ++;
            if (!isValidDate(newDay, newMonth, year)) {
                newMonth = 1;
                newYear ++;
            }
        }
        return new Date(newDay, newMonth, newYear);
    }

    public Date previousDate() {
        int newDay = day - 1;
        int newMonth = month;
        int newYear = year;
        if (!isValidDate(newDay, month, year)) {
            newMonth --;
            if (MONTH_WITH_31_DAYS.contains(newMonth)) {
                newDay = 31;
            } else if (MONTH_WITH_30_DAYS.contains(newMonth)) {
                newDay = 30;
            } else if (newMonth == FEBRUARY) {
                if(isLeapYear(year)) {
                    newDay = 29;
                } else {
                    newDay = 28;
                }
            } else {
                newDay = 31;
            }
            if (!isValidDate(newDay, newMonth, year)) {
                newMonth = 12;
                newYear --;
            }
        }
        return new Date(newDay, newMonth, newYear);
    }

    public int compareTo(Date other) {
        int result;
        if(other.getYear() < year) {
            result = 1;
        } else if (other.getYear() > year) {
            result = -1;
        } else {
            if (other.getMonth() < month) {
                result = 1;
            } else if (other.getMonth() > month) {
                result = -1;
            } else {
                if (other.getDay() < day) {
                    result = 1;
                } else if (other.getDay() > day) {
                    result = -1;
                } else {
                    result = 0;
                }
            }
        }
        return result;
    }

}