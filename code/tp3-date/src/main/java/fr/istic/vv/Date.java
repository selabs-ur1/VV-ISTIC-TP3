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

    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }

    /**
     * Checks if the provided date is valid.
     * 
     * @param day   the day of the date
     * @param month the month of the date
     * @param year  the year of the date
     * @return true if the date is valid, false otherwise
     */
    public static boolean isValidDate(int day, int month, int year) {
        boolean valid = true;

        if (day < 1 || day > daysInMonth(month, year)) {
            // Invalid number of days
            valid = false;
        }

        if (month < 1 || month > 12) {
            // Invalid month
            valid = false;
        }

        return valid;
    }

    /**
     * Checks if a year is a leap year. A leap year is a year that is divisible by 4
     * if it is not a century year.
     * If a year is a century year (eg. 1900), it must be divisible by 400 to be a
     * leap year.
     * 
     * @param year the year to check
     * @return true if the year is a leap year, false otherwise
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    /**
     * Returns the number of days in the specified month.
     * 
     * @param month the month
     * @param year  the year
     * @return the number of days in the month
     */
    public static int daysInMonth(int month, int year) {
        int nbOfDays;

        if (month < 1 || month > 12) {
            nbOfDays = 0;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            nbOfDays = 30;
        } else if (month == 2) {
            if (isLeapYear(year)) {
                nbOfDays = 29;
            } else {
                nbOfDays = 28;
            }
        } else {
            nbOfDays = 31;
        }

        return nbOfDays;
    }

    /**
     * @return the day after the current date.
     */
    public Date nextDate() {
        if (day == 31 && month == 12) {
            return new Date(1, 1, year + 1);
        } else if (day == 30 && (month == 4 || month == 6 || month == 9 || month == 11)) {
            return new Date(1, month + 1, year);
        } else if (month == 2) {
            if (day == 28 && isLeapYear(year)) {
                return new Date(29, 2, year);
            } else if (day == 29 || day == 28) {
                return new Date(1, 3, year);
            } else {
                return new Date(day + 1, 2, year);
            }
        } else if (day == 31) {
            return new Date(1, month + 1, year);
        } else {
            return new Date(day + 1, month, year);
        }
    }

    /**
     * @return the day before the current date.
     */
    public Date previousDate() {
        if (day > 1) {
            return new Date(day - 1, month, year);
        } else if (month == 1) {
            return new Date(31, 12, year - 1);
        } else if (month == 5 || month == 7 || month == 10 || month == 12) {
            return new Date(30, month - 1, year);
        } else if (month == 3) {
            if (isLeapYear(year)) {
                return new Date(29, 2, year);
            } else {
                return new Date(28, 2, year);
            }
        } else {
            return new Date(31, month - 1, year);
        }
    }

    /**
     * Compares this date with another date.
     * 
     * @param other the date to compare with
     * @return a negative integer if this date is before the other date, 0 if they
     *         are equal, a positive integer if this date is after the other date
     * 
     * @throws NullPointerException if the other date is null
     */
    @Override
    public int compareTo(Date other) {
        if (other == null) {
            throw new NullPointerException();
        }

        if (year != other.year) {
            return year - other.year;
        } else if (month != other.month) {
            return month - other.month;
        } else {
            return day - other.day;
        }
    }
}