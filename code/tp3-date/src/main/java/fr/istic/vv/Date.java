package fr.istic.vv;

import java.time.LocalDate;
import java.util.Objects;

public class Date implements Comparable<Date> {

    public LocalDate date;

    public Date(int day, int month, int year) {
        this.date = LocalDate.of(year,month,day);
    }

    public static boolean isValidDate(int day, int month, int year) {
        try{
            LocalDate date = LocalDate.of(year,month,day);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public static boolean isLeapYear(int year) {
        LocalDate date = LocalDate.of(year,1,1);
        return date.isLeapYear();
    }

    public Date nextDate() {
        LocalDate nextDate = date.plusDays(1);
        return new Date(nextDate.getDayOfMonth(),nextDate.getMonthValue(), nextDate.getYear());
    }

    public Date previousDate() {
        LocalDate nextDate = date.minusDays(1);
        return new Date(nextDate.getDayOfMonth(),nextDate.getMonthValue(), nextDate.getYear());
    }


    @Override
    public int compareTo(Date other) {
        if(Objects.isNull(other)){
            throw new NullPointerException();
        }

        return this.date.compareTo(other.date);
    }

}