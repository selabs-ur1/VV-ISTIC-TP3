package fr.istic.vv;

class Date implements Comparable<Date> {

    private final int day;
    private final int month;
    private final int year;

    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (day < 1 || month < 1 || month > 12) {
            return false;
        }
        if (day > 31) {
            return false;
        }
        if (month == 2) {
            if (isLeapYear(year)) {
                return day <= 29;
            } else {
                return day <= 28;
            }
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return day <= 30;
        }
        return true;
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    public Date nextDate() {
        int nextDay = day + 1;
        int nextMonth = month;
        int nextYear = year;
        if(month % 2 == 0 && day == 30) {
            nextDay = 1;
            nextMonth++;
        } else if(month % 2 == 1 && day == 31) {
            nextDay = 1;
            nextMonth++;
        } else if(month == 2) {
            if(isLeapYear(year) && day == 29) {
                nextDay = 1;
                nextMonth++;
            } else if(!isLeapYear(year) && day == 28) {
                nextDay = 1;
                nextMonth++;
            }
        }
        if(month == 12 && day == 31) {
            nextDay = 1;
            nextMonth = 1;
            nextYear++;
        }
        return new Date(nextDay, nextMonth, nextYear);
    }

    public Date previousDate() {
        int previousDay = day - 1;
        int previousMonth = month;
        int previousYear = year;
        if (previousDay < 1) {
            previousMonth--;
            if (previousMonth < 1) {
                previousMonth = 12;
                previousYear--;
            }
            if (previousMonth == 2) {
                if (isLeapYear(previousYear)) {
                    previousDay = 29;
                } else {
                    previousDay = 28;
                }
            } else if (previousMonth == 4 || previousMonth == 6 || previousMonth == 9 || previousMonth == 11) {
                previousDay = 30;
            } else {
                previousDay = 31;
            }
        }
        return new Date(previousDay, previousMonth, previousYear);
    }

    public int compareTo(Date other) {
        if (year != other.year) {
            return year - other.year;
        }
        if (month != other.month) {
            return month - other.month;
        }
        return day - other.day;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Date)) {
            return false;
        }
        Date other = (Date) obj;
        return day == other.day && month == other.month && year == other.year;
    }

}