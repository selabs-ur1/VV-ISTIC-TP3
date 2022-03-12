package fr.istic.vv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;
    private static final List<Integer> longMonths = Arrays.asList(1,3,5,7,8,10,12);

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Date(int day, int month, int year) {
        if(isValidDate(day, month, year)){
            this.day=day;
            this.month=month;
            this.year=year;
        }
        else{
            throw new IllegalArgumentException("Invalid date");
        }
    }

    public static boolean isValidDate(int day, int month, int year) {
        if(day<1 || day>31){
            return false;
        }
        if(month<1 || month>12){
            return false;
        }
        if(day==29 && month==2){
            return isLeapYear(year);
        }
        if(!longMonths.contains(month) && day==31){
            return false;
        }
        return true;
    }

    public static boolean isLeapYear(int year) {
        return year%4==0 && year%100!=0;
    }

    public Date nextDate() {
        //general case and leap year : only day increases
        if(day<28 || (day==28 && isLeapYear(year))){
            return new Date(day+1, month, year);
        }
        //if day is 30 and month can go up to 31, increase day
        if(day==30 && longMonths.contains(month)){
            return new Date(day+1, month, year);
        }
        //only possibility for day now is to be 31
        //if month is december, increase year and 1st january
        if(month==12){
            return new Date(1, 1, year+1);
        }
        //otherwise increase month and day is 1st
        return new Date(1, month+1, year);
    }

    public Date previousDate() {
        if(day==1){
            if(month==1){
                return new Date(31,12,year-1);
            }
            else{
                //if previous month is a 31 day month
                if(longMonths.contains(month-1)){
                    return new Date(31, month-1, year);
                }
                else{
                    return new Date(30, month-1, year);
                }
            }
        }
        else{
            return new Date(day-1, month, year);
        }
    }

    public int compareTo(Date other) {
        Objects.requireNonNull(other);
        /*if(day==other.getDay() && month==other.getMonth() && year==other.getYear()){
            return 0;
        }*/
        if(year!=other.year){
            return year-other.getYear();
        }
        else{
            if(month!=other.getMonth()){
                return month-other.getMonth();
            }
            else{
                return day-other.getDay();//if == returns 0
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return getDay() == date.getDay() && getMonth() == date.getMonth() && getYear() == date.getYear();
    }

}
