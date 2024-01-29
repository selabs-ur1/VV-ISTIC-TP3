package fr.istic.vv;

import java.util.Objects;

class Date implements Comparable<Date> {
    private int day;

    private int month;

    private int year;
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if ( month < 1 || month > 12 || day < 1) {
            return false;
        }

        int lastDayOfMonth = 31;

        if (month == 4 || month == 6 || month == 9 || month == 11) {
            lastDayOfMonth = 30;
        } else if (month == 2) {
            lastDayOfMonth = isLeapYear(year) ? 29 : 28;
        }

        return day <= lastDayOfMonth;
    }

    public static boolean isLeapYear(int year) { 
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
     }

    public Date nextDate() {
        int nextDay = day + 1;
        int nextMonth = month;
        int nextYear = year;

        int lastDayOfMonth = 31;

        if (month == 4 || month == 6 || month == 9 || month == 11) {
            lastDayOfMonth = 30;
        } else if (month == 2) {
            lastDayOfMonth = isLeapYear(year) ? 29 : 28;
        }

        if (nextDay > lastDayOfMonth) {
            nextDay = 1;
            nextMonth++;
        }

        if (nextMonth > 12) {
            nextMonth = 1;
            nextYear++;
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

            if (prevMonth == 4 || prevMonth == 6 || prevMonth == 9 || prevMonth == 11) {
                prevDay = 30;
            } else if (prevMonth == 2) {
                prevDay = isLeapYear(prevYear) ? 29 : 28;
            } else {
                prevDay = 31;
            }
        }

        return new Date(prevDay, prevMonth, prevYear);
    }

    public int compareTo(Date other) {  Objects.requireNonNull(other, "Cannot compare to null");

            if (this.year != other.year) {
                return this.year - other.year;
            } else if (this.month != other.month) {
                return this.month - other.month;
            } else {
                return this.day - other.day;
            } 
        
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        Date date = (Date) o;
        return day == date.day && month == date.month && year == date.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }
}