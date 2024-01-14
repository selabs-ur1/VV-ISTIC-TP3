package fr.istic.vv;

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
            throw new IllegalArgumentException("Invalid date");
        }
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (year < 0 || month < 1 || month > 12 || day < 1 || day > 31) {
            return false;
        }
        return true;
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public Date nextDate() {
        if (day < 31) {
            return new Date(day + 1, month, year);
        } else if (month < 12) {
            return new Date(1, month + 1, year);
        } else {
            return new Date(1, 1, year + 1);
        }
    }

    public Date previousDate() {
        if (day > 1) {
            return new Date(day - 1, month, year);
        } else if (month > 1) {
            int previousMonthDays = month == 3 && isLeapYear(year) ? 29 : 28;
            return new Date(previousMonthDays, month - 1, year);
        } else {
            return new Date(31, 12, year - 1);
        }
    }

    @Override
    public int compareTo(Date other) {
        if (year != other.year) {
            return Integer.compare(year, other.year);
        } else if (month != other.month) {
            return Integer.compare(month, other.month);
        } else {
            return Integer.compare(day, other.day);
        }
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }
}
