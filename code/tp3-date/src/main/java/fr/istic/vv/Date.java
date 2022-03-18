package fr.istic.vv;

import javax.print.attribute.standard.MediaSize.Other;

class Date implements Comparable<Date> {

    public int day;
    public int month;
    public int year;
    public Date(int day, int month, int year) { 
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) { 
        if(month > 12 || month <= 0) return false;
        if(day <= 0 || day > 31) return false;
                switch(month){
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                    return day <= 31;
                    case 2:
                    if(isLeapYear(year)) return day <= 29;
                    return day <= 28;
                    default:
                    return day <= 30;
                }
     }

    public static boolean isLeapYear(int year) { return year%4 == 0 && year%100 != 0; }

    public Date nextDate() { 
        if(isValidDate(day+1, month, year)) return new Date(day+1, month, year);
        if(isValidDate(1, month+1, year)) return new Date(1, month+1, year);
        return new Date(1, 1, year+1); }

    public Date previousDate() { 
        if(isValidDate(day-1, month, year)) return new Date(day-1, month, year);
        if(isValidDate(1, month-1, year)) return previousValidDate(31, month-1, year);
        return new Date(31, 12, year-1); }

    public int compareTo(Date other) {
        int ret = 0;
        Date first;
        Date last;
        if(this.year < other.year){
            first = this;
            last = other;
        } else if(this.year > other.year) {

            first = other;
            last = this;
        } else {
            if(this.month < other.month){

                first = this;
                last = other;
            } else if(this.month > other.month){

                first = other;
                last = this;
            } else {
                if(this.day < other.day){

                    first = this;
                    last = other;
                } else if(this.day > other.day){

                    first = other;
                    last = this;
                }
                else{
                    return 0;
                }
            }
        }
        while (!first.equals(last) && ret < 50){
            first = first.nextDate();
            ret += 1;
        }
        if(last == other) return -ret;
        return ret; }

    private Date previousValidDate(int day,int month,int year){
        while(day > 0 && !isValidDate(day, month, year)){
            day --;
        }
        return new Date(day, month, year);
    }

    public boolean equals(Date d){
        if(d.day != day) return false;
        if(d.month != month) return false;
        if(d.year != year) return false;
        return true;
    }
}