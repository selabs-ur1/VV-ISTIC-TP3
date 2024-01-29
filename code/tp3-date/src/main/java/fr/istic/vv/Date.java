package fr.istic.vv;

import java.io.IOException;

class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) throws IOException { 
        if(!isValidDate(day, month, year)) {
            throw new IOException("Date is not valid");
        }

        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (day <= 0){
            return false;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return day <= 31;
            case 2:
                return (isLeapYear(year) ? day <= 29 : day <= 28);
            case 4:
            case 6:
            case 9:
            case 11:
                return day <= 30;
            default:
                return false;
        }
    }

    public static boolean isLeapYear(int year) { 
        return ((year%4 == 0 && year%100 != 0) || year%400 == 0);
    }

    public Date nextDate() throws IOException { 
        if(isValidDate(this.day+1, this.month, this.year)){
            return new Date(this.day+1, this.month, this.year);
        }
        else if(isValidDate(1, this.month+1, this.year)) {
            return new Date(1, this.month+1, this.year);
        }
        else {
            return new Date(1, 1, this.year+1);
        }
     }

    public Date previousDate() throws IOException {
        
        if(this.day != 1){
            return new Date(this.day-1, this.month, this.year);
        }
        else if(this.month != 1){
            int day = 31;
            switch (month-1) {
                case 2:
                    day = (isLeapYear(year) ? 29 : 28);
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    day = 30;
                    break;
            }
            return new Date(day, this.month-1, this.year);
        }
        else {
            return new Date(31, 12, this.year-1);
        }
    }

    public int compareTo(Date other) {

        // year comparaison
        if(this.year > other.year){
            return 1;
        }
        else if(this.year < other.year) {
            return -1;
        }

        // month comparaison
        if(this.month > other.month){
            return 1;
        }
        else if(this.month < other.month) {
            return -1;
        }

        // day comparaison
        if(this.day > other.day){
            return 1;
        }
        else if(this.day < other.day) {
            return -1;
        }

        return 0; 
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) {
            return false;
        }

        if(obj.getClass() != this.getClass()){
            return false;
        }

        Date other = (Date) obj;

        return (
            this.day == other.day &&
            this.month == other.month &&
            this.year == other.year
        );
    }

    @Override
    public String toString() {
        return "Date [day=" + day + ", month=" + month + ", year=" + year + "]";
    }

    
}