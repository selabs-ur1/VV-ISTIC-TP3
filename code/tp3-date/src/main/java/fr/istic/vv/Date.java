package fr.istic.vv;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

class Date implements Comparable<Date> {
    private LocalDate date;
    public Date(int day, int month, int year) {
        this.date = LocalDate.of(year, month, day);
    }

    public static boolean isValidDate(int day, int month, int year) {
        try {
            LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    public static boolean isLeapYear(int year) {
        if(year >= 0){
            if ((year % 4 == 0)) {
                if(year % 100 != 0){
                    return true; // Année bissextile
                }

            }

            if(year % 400 == 0){
                return true; // Année bissextile
            }
        }

        return false;
    }

    public LocalDate getDateToLocalDate(){
        return this.date;
    }

    public Date nextDate() {
        LocalDate nextDay = this.date.plusDays(1);
        return new Date(nextDay.getDayOfMonth(),nextDay.getMonthValue(),nextDay.getYear());
    }

    public Date previousDate() {
        LocalDate beforeDay = this.date.minusDays(1);

        return new Date(beforeDay.getDayOfMonth(),beforeDay.getMonthValue(),beforeDay.getYear());
    }

    public int compareTo(Date other) {
        /*
        positif : `date` is posterior to `other`
        négatif : `date` is anterior to `other`
        0 : `date` and `other` represent the same date.
        null : throws a `NullPointerException`
        */
        if(other == null){
            throw new NullPointerException("Date enter is null");
        }

        LocalDate otherLocalDate=other.getDateToLocalDate();

        if(this.date.equals(otherLocalDate)){ //`date` and `other` represent the same date.
            return 0;
        }
        if (this.date.isAfter(otherLocalDate)){ //`date` is posterior to `other`
            return 1;
        }else{ //`date` is anterior to `other`
            return -1;
        }
    }





}