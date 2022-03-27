package fr.istic.vv;

class Date implements Comparable<Date> {

    static int MAX_VALID_YR = 9999;
    static int MIN_VALID_YR = 1800;
    private int day;
    private int month;
    private int year;

    // The constructor throws an exception if the three given integers do not form a valid date.
    public Date(int day, int month, int year) throws Exception {
        if (!isValidDate(day, month, year)) {
            throw new Exception("The date is not valid");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * returns `true` if the three integers form a valid year, otherwise `false`.
     * @param day
     * @param month
     * @param year
     * @return
     */
    public static boolean isValidDate(int day, int month, int year) {
    // If year, month and day are not in given range
        if (year > MAX_VALID_YR ||
                year < MIN_VALID_YR) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1 || day > 31) {
            return false;
        }
        // Handle February month with leap year
        if (month == 2)
        {
            if (isLeapYear(year))
                return (day <= 29);
            else
                return (day <= 28);
        }

        // Months of April, June, Sept and Nov must have number of days less than or equal to 30.
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return (day <= 30);
        }

        return true;

      }

    public static boolean isLeapYear(int year) { // Return true if year is
        // a multiple of 4 AND not
        // multiple of 100.
        // OR year is multiple of 400.
        return (((year % 4 == 0) &&
                (year % 100 != 0)) ||
                (year % 400 == 0));
    }

    public Date nextDate() throws Exception {
        int nextDay, nextMonth, nextYear;
        if (this.day == maxDay(this.month, this.year)) {
            if (this.month == 12) {
                nextDay = 1;
                nextMonth = 1;
                nextYear = year + 1;
            } else {
                nextDay = 1;
                nextMonth = this.month+1;
                nextYear = this.year;
            }
        } else {
            nextDay = this.day + 1;
            nextMonth = this.month;
            nextYear = this.year;
        }

        return new Date(nextDay, nextMonth, nextYear);
    }

    public Date previousDate() throws Exception {
        int preDay, preMonth, preYear;

        // the 1st january
        if (this.day == 1 && this.month == 1) {
            preDay = 31;
            preMonth = 12;
            preYear = this.year - 1;
        }

        // the first day of the other months
        else if (this.day == 1) {
            preMonth = month - 1;
            preDay = maxDay(preMonth, year);
            preYear = year;
        } else {
            // the other days
            preDay = day - 1;
            preMonth = month;
            preYear = year;
        }

        return new Date(preDay, preMonth, preYear);
    }

    /**
     * `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
     * `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
     * `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
     * the method throws a `NullPointerException` if `other` is `null`
     * @param other
     * @return
     */
    public int compareTo(Date other) {
        if (other == null) {
            throw new NullPointerException();
        }

        if (year > other.year) {
            return 1;
        } else if (year < other.year) {
            return -1;
        } else {
            // date.year == other.year
            if (month > other.month) {
                return 1;
            } else if (month < other.month) {
                return -1;
            } else {
                // date.month == other.month
                if (day > other.day) {
                    return 1;
                } else if (day < other.day) {
                    return -1;
                } else {
                    // date.day == other.day
                    return 0;
                }
            }
        }

        }

    public static int maxDay(int month, int year) {
        int daymax;
        if (month == 2)
        {
            if (isLeapYear(year)) {
                daymax = 29;
            }
            else {
                daymax = 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            daymax = 30;
        } else {
            daymax = 31;
        }

        return daymax;
    }

}
