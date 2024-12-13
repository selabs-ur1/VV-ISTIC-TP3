package fr.istic.vv;

class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;

    private final static int[] numberOfDayInMonth = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException();
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isLeapYear(int year) {
        return (year > 0) && (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }

    public static int numberOfDayInMonth(int year, int month) {
        if (year <1 || month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        int numberOfDays = numberOfDayInMonth[month - 1];
        if (isLeapYear(year) && month == 2) {
            numberOfDays++;
        }
        return numberOfDays;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (year < 1) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        int daysInMonth = numberOfDayInMonth(year, month);
        return day >= 1 && day <= daysInMonth;
    }

    public Date nextDate() {
        int newDay = day + 1;
        int newMonth = month;
        int newYear = year;

        if (newDay > numberOfDayInMonth(year, month)) {
            newMonth++;
            newDay = 1;

            if (newMonth > 12) {
                newYear++;
                newMonth = 1;
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
                newYear--;
                newMonth = 12;
            }
            newDay = numberOfDayInMonth(newYear, newMonth);

        }
        return new Date(newDay, newMonth, newYear);

    }

    @Override
    public int compareTo(Date other) {

        if (other == null) {
            throw new NullPointerException();
        }
        if (this.year > other.year) {
            return 1;
        }

        int yearDiff = this.year - other.year;
        int monthDiff = this.month - other.month;
        int dayDiff = this.day - other.day;

        if (yearDiff != 0) {
            return yearDiff;
        }
        if (monthDiff != 0) {
            return monthDiff;
        }
        return dayDiff;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Date other = (Date) obj;
        return this.day == other.day &&
                this.month == other.month &&
                this.year == other.year;
    }

    @Override
    public int hashCode() {
        int result = 7 * year * 11 * month * 13 * day;
        return result;
    }

}