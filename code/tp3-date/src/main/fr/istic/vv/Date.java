package fr.istic.vv;

class Date implements Comparable<Date> {
    private int day, month, year;
    private static final int[] dayPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) throw new IllegalArgumentException("Date is not valid");

        this.day=day;
        this.month=month;
        this.year=year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if( month<0 || month>12) return false;
        if( (month == 2 && isLeapYear(year) && day>0 && day<=29) || ( day>0 && day<=dayPerMonth[month-1])) return true;;
        return false; }

    public static boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return true;
        }
    }

    public Date nextDate() {
        int day = this.day + 1;
        month = this.month;
        year = this.year;
        if(!isValidDate(day,month,year)){
            ++month;
            day = 1;
            if(!isValidDate(day,month,year)){
                ++year;
                month = 1;
                day = 1;
            }
        }
        return new Date(day, month, year);
    }

    public Date previousDate() {
        int day = this.day - 1, month = this.month, year = this.year;
        if(!isValidDate(day,month,year)){
            --month;
            if (month == 0) {
                --year;
                month = 12;
                day = 31;
            }else{
                day = dayPerMonth[month - 1];
                if ( month == 2 && isLeapYear(year)) ++day;
            }
        }
        return new Date(day, month, year);
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

    public int compareTo(Date other) {
        if (other==null) throw new NullPointerException();
        if (this.year > other.getYear() ) return 1;
        else if (this.year < other.getYear() ) return -1;
        else if (this.year == other.getYear() ) {
            if (this.month > other.getMonth() ) return 1;
            else if (this.month < other.getMonth() ) return -1;
            else if (this.month == other.getMonth() ) {
                if (this.day > other.getDay() ) return 1;
                else if (this.day < other.getDay() ) return -1;
            }
        }
        return 0;
    }
}