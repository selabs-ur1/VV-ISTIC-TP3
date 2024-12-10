package fr.istic.vv;

class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    private static int[] NOT_LEAP_YEAR = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31,30,31};
    private static int[] LEAP_YEAR = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31,30,31};

    public Date(int day, int month, int year) {
        if(isValidDate(day, month, year)){
            this.day = day;
            this.month = month;
            this.year = year;
        }else {
            throw new IllegalArgumentException("Invalid date format");
        }
    }

    public static boolean isValidDate(int day, int month, int year) {
        if(day < 1 || month < 1 || month > 12){
            return false;
        }
        if(isLeapYear(year)){
            return LEAP_YEAR[month-1] >= day;
        }else{
            return NOT_LEAP_YEAR[month-1] >= day;
        }
    }

    public static boolean isLeapYear(int year) {
        return (year%400==0)|| ((year%100!=0)&&(year%4==0));
    }

    public Date nextDate() {
        if(NOT_LEAP_YEAR[month-1] == day && month==12){
            return new Date(1, 1, year+1);
        }
        if(isLeapYear(year) && month == 2 && day == 29){
            return new Date(1, month+1, year);
        }else{
            if(NOT_LEAP_YEAR[month-1] == day){
                return new Date(1, month+1, year);
            }else {
                return new Date(day+1, month, year);
            }
        }
    }

    public Date previousDate() {
        if(day == 1 && month==1){
            return new Date(NOT_LEAP_YEAR[11], 12, year-1);
        }
        if(isLeapYear(year) && month == 3 && day == 1){
            // LEAP_YEAR[1] -> last day of february
            return new Date(LEAP_YEAR[1], 2, year);
        }else{
            if(day==1){
                return new Date(NOT_LEAP_YEAR[month-2], month-1, year);
            }else {
                return new Date(day-1, month, year);
            }
        }
    }

    public int compareTo(Date other) {

        return 0;
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