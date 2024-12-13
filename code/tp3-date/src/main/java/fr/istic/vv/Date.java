package fr.istic.vv;

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
        if (year < 1 || month < 1 || month > 12 || day < 1) {
            return false;
        }
        int[] daysInMonth = { 31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        return day <= daysInMonth[month - 1];
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    public Date nextDate() {
        assert (isValidDate(day, month, year));
        int[] daysInMonth = { 31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        int newDay = day + 1;
        int newMonth = month;
        int newYear = year;

        if (newDay > daysInMonth[month - 1]) {
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
        assert (isValidDate(day, month, year));
        int[] daysInMonth = { 31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        int newDay = day - 1;
        int newMonth = month;
        int newYear = year;

        if (newDay == 0) {
            newMonth--;
            if (newMonth == 0) {
                newMonth = 12;
                newYear--;
            }
            newDay = daysInMonth[newMonth - 1];
        }
        return new Date(newDay, newMonth, newYear);
    }

    public int compareTo(Date other) {
        if (other == null) {
            throw new NullPointerException("The other date is null");
        }
        if (this.year != other.year) {
            return this.year - other.year;
        }
        if (this.month != other.month) {
            return this.month - other.month;
        }
        return this.day - other.day;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Date
                && ((Date) o).day == day
                && ((Date) o).month == month
                && ((Date) o).year == year;
    }
}