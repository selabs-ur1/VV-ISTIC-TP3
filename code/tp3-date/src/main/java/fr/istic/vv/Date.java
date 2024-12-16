package fr.istic.vv;

class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;

    private static final int[] DAYS_IN_MONTHS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private static final int[] DAYS_IN_MONTHS_LEAP = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }

    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date: " + day + "/" + month + "/" + year);
        }

        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12) {
            return false;
        }

        int daysInMonth = getDaysInMonth(month, year);
        return day >= 1 && day <= daysInMonth;
    }

    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0; // Divisible by 400 is a leap year
            }
            return true; // Divisible by 4 but not 100
        }
        return false; // Not divisible by 4
    }

    public Date nextDate() {
        int newDay = day + 1;
        int newMonth = month;
        int newYear = year;

        int daysInCurrentMonth = getDaysInMonth(month, year);

        if (newDay > daysInCurrentMonth) {
            newDay = 1;
            newMonth++;
            if (newMonth > 12) {
                newMonth = 1;
                newYear++;
            }
        }

        return new Date(newDay, newMonth, newYear);
    }

    public Date previousDate() {
        int newDay = day - 1;
        int newMonth = month;
        int newYear = year;

        if (newDay < 1) {
            newMonth--;
            if (newMonth < 1) {
                newMonth = 12;
                newYear--;
            }
            newDay = getDaysInMonth(newMonth, newYear);
        }

        return new Date(newDay, newMonth, newYear);
    }

    @Override
    public int compareTo(Date other) {
        if (other == null) {
            throw new IllegalArgumentException("The other date is null");
        }

        if (this.year != other.year) {
            return this.year - other.year;
        }
        if (this.month != other.month) {
            return this.month - other.month;
        }
        return this.day - other.day;
    }

    public static int getDaysInMonth(int month, int year) {
        return isLeapYear(year) ? DAYS_IN_MONTHS_LEAP[month - 1] : DAYS_IN_MONTHS[month - 1];
    }
}