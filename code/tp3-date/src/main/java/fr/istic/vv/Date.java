package fr.istic.vv;

import java.util.Arrays;
import java.util.List;

class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new IllegalArgumentException("La date entrée n'est pas valide.");
        }
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (0 < month && month < 13) {
            int monthLength = monthLength(month, year);
            if (0 < day && day < monthLength + 1) {
                return true;
            }
        }

        return false;
    }

    private static int monthLength(int month, int year) {
        int monthLength = -1;

        List<Integer> monthsWith31Days = Arrays.asList(1, 3, 5, 7, 8, 10, 12);
        List<Integer> monthsWith30Days = Arrays.asList(4, 6, 9, 11);

        if (monthsWith31Days.contains(month)) {
            monthLength = 31;
        } else if (monthsWith30Days.contains(month)) {
            monthLength = 30;
        } else if (month == 2) {
            if (isLeapYear(year)) {
                monthLength = 29;
            } else {
                monthLength = 28;
            }
        }

        return monthLength;
    }

    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    public Date nextDate() {
        Date nextDate = new Date(day, month, year);

        int monthLength = monthLength(month, year);

        if (nextDate.day < monthLength) {
            nextDate.day++;
        } else if (month < 12) {
            nextDate.month++;
            nextDate.day = 1;
        } else {
            nextDate.year++;
            nextDate.month = 1;
            nextDate.day = 1;
        }

        return nextDate;
    }

    public Date previousDate() {
        Date previousDate = new Date(day, month, year);

        if (1 < previousDate.day) {
            previousDate.day--;
        } else {
            if (1 < month) {
                previousDate.month--;
                previousDate.day = monthLength(previousDate.month, year);
            } else {
                previousDate.year--;
                previousDate.month = 12;
                previousDate.day = 31;
            }
        }

        return previousDate;
    }

    public int compareTo(Date other) {
        if (other.year < year) {
            return 1;
        } else if (year < other.year) {
            return -1;
        } else {
            if (other.month < month) {
                return 1;
            } else if (month < other.month) {
                return -1;
            } else {
                if (other.day < day) {
                    return 1;
                } else if (day < other.day) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Date otherDate = (Date) obj;
        return this.day == otherDate.day && this.month == otherDate.month && this.year == otherDate.year;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }
}