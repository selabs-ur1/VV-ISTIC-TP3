package fr.istic.vv;

class Date implements Comparable<Date> {

    private final int day;
    private final int month;
    private final int year;

    private static final int[] DAYS_IN_MONTH = {31,28,31,30,31,30,31,31,30,31,30,31};

    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) throw new IllegalArgumentException("Invalid date");

        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (year < 0) return false;
        if (month < 1 || month > 12) return false;
        if (isLeapYear(year) && month == 2) return day >= 1 && day <= 29;
        return day >= 1 && day <= DAYS_IN_MONTH[month - 1];
    }

    public static boolean isLeapYear(int year) {
        if (year < 0) throw new IllegalArgumentException("Year must be positive");
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    public Date nextDate() {
        int nextDay = day + 1;
        int nextMonth = month;
        int nextYear = year;
        if (isLeapYear(year) && month == 2 && day == 29) {
            nextDay = 1;
            nextMonth++;
        } else if (nextDay > DAYS_IN_MONTH[nextMonth - 1] && !isLeapYear(year)) {
            nextDay = 1;
            nextMonth++;
            if (nextMonth > 12) {
                nextMonth = 1;
                nextYear++;
            }
        }
        return new Date(nextDay, nextMonth, nextYear);
    }

    public Date previousDate() {
        int previousDay = day - 1;
        int previousMonth = month;
        int previousYear = year;
        if (isLeapYear(year) && month == 3 && day == 1) {
            previousDay = 29;
            previousMonth--;
        } else if (previousDay < 1) {
            previousMonth--;
            if (previousMonth < 1) {
                previousMonth = 12;
                previousYear--;
            }
            previousDay = DAYS_IN_MONTH[previousMonth - 1];
        }
        return new Date(previousDay, previousMonth, previousYear);
    }



    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof Date)) return false;
        Date otherDate = (Date) other;
        return day == otherDate.day && month == otherDate.month && year == otherDate.year;
    }
    public int compareTo(Date other) {
        if (other == null) throw new NullPointerException();
        if (year != other.year) return year - other.year;
        if (month != other.month) return month - other.month;
        return day - other.day;
    }
    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }

}