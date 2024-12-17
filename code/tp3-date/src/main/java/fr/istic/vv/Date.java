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
        if (year < 0 || month < 1 || month > 12 || day < 1) {
            return false;
        }

        return day <= maxDays(month, year);
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    public static int maxDays(int month, int year) {
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        }
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        return 0; // Invalid month
    }

    public Date nextDate() {
        this.day++;

        if (this.day > maxDays(this.month, this.year)) {
            this.day = 1;
            this.month++;
            if (this.month > 12) {
                this.month = 1;
                this.year++;
            }
        }

        return new Date(this.day, this.month, this.year);
    }

    public Date previousDate() {
        this.day--;

        if (this.day < 1) {
            this.month--;
            if (this.month < 1) {
                this.month = 12;
                this.year--;
            }
            this.day = maxDays(this.month, this.year);
        }

        return new Date(this.day, this.month, this.year);
    }

    public int compareTo(Date other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare to null");
        }

        if (this.year != other.year) {
            return this.year - other.year;
        }
        if (this.month != other.month) {
            return this.month - other.month;
        }
        return this.day - other.day;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
