package fr.istic.vv;

import java.util.List;
import java.util.Objects;

class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;
    private final static List<Integer> thirty = List.of(4, 6, 9, 11);
    private final static List<Integer> thirtyOne = List.of(1, 3, 5, 7, 8, 10, 12);

    public Date(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (day <= 0) return false;
        if (thirty.contains(month)) {
            return (day <= 30);
        } else if (thirtyOne.contains(month)) {
            return (day <= 31);
        } else if (month == 2) {
            if (isLeapYear(year)){
                return (day <= 29);
            } else return (day <= 28);
        } else {
            return false;
        }
    }

    public static boolean isLeapYear(int year) {
        if (year % 100 == 0){
            return (year % 400 == 0);
        }
        return (year % 4 == 0);
    }

    public Date nextDate() {
        int nextDay = this.day + 1;
        int nextMonth = this.month;
        int nextYear = this.year;
        if (isValidDate(nextDay, nextMonth, nextYear)) {
            return new Date(nextDay, nextMonth, nextYear);
        } else if (isValidDate(1, nextMonth + 1, nextYear)) {
            return new Date(1, nextMonth + 1, nextYear);
        } else {
            return new Date(1, 1, nextYear + 1);
        }
    }

    public Date previousDate() {
        int previousDay = this.day - 1;
        int previousMonth = this.month;
        int previousYear = this.year;
        if (isValidDate(previousDay, previousMonth, previousYear)) {
            return new Date(previousDay, previousMonth, previousYear);
        } else {
            if (thirty.contains(previousMonth - 1)) {
                return new Date(30, previousMonth - 1, previousYear);
            } else if (thirtyOne.contains(previousMonth - 1)) {
                return new Date(31, previousMonth - 1, previousYear);
            } else if ((previousMonth - 1) == 2) {
                if (isLeapYear(year)){
                    return new Date(29, previousMonth - 1, previousYear);
                } else return new Date(28, previousMonth - 1, previousYear);
            } else {
                return new Date(31, 12, previousYear - 1);
            }
        }
    }

    public int compareTo(Date other) {
        if (Objects.isNull(other)) throw new NullPointerException();
        if (this.year != other.year) return (this.year - other.year);
        if (this.month != other.month) return (this.month - other.month);
        if (this.day != other.day) return (this.day - other.day);
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return day == date.day && month == date.month && year == date.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }
}
