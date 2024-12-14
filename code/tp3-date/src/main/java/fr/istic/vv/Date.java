package fr.istic.vv;

public class Date implements Comparable<Date> {

    private final int day;
    private final int month;
    private final int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (year < 1) {
            return false;
        }

        if (month < 1 || month > 12) {
            return false;
        }

        int daysInMonth = getDaysInMonth(month, year);
        return day >= 1 && day <= daysInMonth;
    }

    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
            return true;
        }
        return false;
    }

    private static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return 0;
        }
    }

    public Date nextDate() {
        int daysInMonth = getDaysInMonth(month, year);
        if (day < daysInMonth) {
            return new Date(day + 1, month, year);
        } else {
            if (month == 12) {
                return new Date(1, 1, year + 1);
            } else {
                return new Date(1, month + 1, year);
            }
        }
    }

    public Date previousDate() {
        if (day > 1) {
            return new Date(day - 1, month, year);
        } else {
            if (month == 1) {
                return new Date(31, 12, year - 1);
            } else {
                return new Date(getDaysInMonth(month - 1, year), month - 1, year);
            }
        }
    }

    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        }
        if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        }
        return Integer.compare(this.day, other.day);
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

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
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
        return day == other.day && month == other.month && year == other.year;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(day, month, year);
    }
}
