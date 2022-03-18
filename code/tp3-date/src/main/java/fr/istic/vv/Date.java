package fr.istic.vv;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Date implements Comparable<Date> {


    private Map<Integer,Integer> months ;

    private final int day;
    private final int month;
    private final int year;

    // Starting at 1 as January and ending with Decemeber as 11
    private static final int TOTAL_NUMBER_OF_MONTHS = 13;

    public static final int JANVIER =1;
    public static final int FEVRIER =2;
    public static final int MARS =3;
    public static final int AVRIL =4;
    public static final int MAI =5;
    public static final int JUIN =6;
    public static final int JUILLET =7;
    public static final int AOUT =8;
    public static final int SPETEMBRE =9;
    public static final int OCTOBRE =10;
    public static final int NOVEMBRE =11;
    public static final int DECEMBRE =12;

    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;

        this.months = new HashMap<>();
        months.put(JANVIER,31);
        if (isLeapYear()) {months.put(FEVRIER, 29);} else  { months.put(FEVRIER, 28);}
        months.put(MARS,31);
        months.put(AVRIL,30);
        months.put(MAI,31);
        months.put(JUIN,30);
        months.put(JUILLET,31);
        months.put(AOUT,31);
        months.put(SPETEMBRE,30);
        months.put(OCTOBRE,31);
        months.put(NOVEMBRE,30);
        months.put(DECEMBRE,31);

    }

    public boolean isValidDate() {
        // Le check sur validité du mois est inclu à la validité du jour.
        return year>=0 && isAValidDay(day,month);
    }

    public boolean isLeapYear() {
        // Une année bissextile tous les 4 ans
        return year%4==0;
    }

    public Date nextDate() {
        if(day == months.get(month)){
            int nextMonth = month+1;
            if (isAValidMonth(nextMonth)){
                // Cas de passage d'un mois
                return new Date(1,nextMonth,year);
            }else {
                // Cas de passage d'une année
                return new Date(1,JANVIER,year+1);
            }
        }else {
            // Cas de passage d'un jour simple
            return new Date(day+1,month,year);
        }
    }

    public Date previousDate(){
        // Cas de retour d'un jour
        if(day == 1) {
            int previousMonth = month - 1;
            if (isAValidMonth(previousMonth)) {
                // Cas de retour d'un mois en arrière
                return new Date(months.get(previousMonth), previousMonth, year);
            } else {
                // Cas de retour d'une année
                return new Date(months.get(TOTAL_NUMBER_OF_MONTHS - 1), TOTAL_NUMBER_OF_MONTHS - 1, year - 1);
            }
        }else {
            return new Date(day-1,month,year);
        }
    }

    public int compareTo(Date other) {
        int result =0;
        // Cas années différentes
        if(year != other.year){
            result = year>other.year ? 1 : -1;
        }else{
            // Cas même années mois différents
            if(month != other.month){
                result = month>other.month ? 1 : -1;
            }else{
                // Cas même années, même mois, jour différents
                if(day!= other.day){
                    result = day>other.day ? 1 : -1;
                }
            }
        }
        return result;
    }


    private boolean isAValidMonth(int month){
        return month>0 && month < TOTAL_NUMBER_OF_MONTHS && months.containsKey(month);
    }

    private boolean isAValidDay(int day, int month) {
        // Pour un mois valide, un jour inclu
       return  isAValidMonth(month) && isDayInTheMonth(day,month);
    }

    private boolean isDayInTheMonth(int day, int month) {
        // Jour positif et inclu dans le mois courant
        return day>0 && day <= months.get(month);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return day == date.day && month == date.month && year == date.year && Objects.equals(months, date.months);
    }

    @Override
    public int hashCode() {
        return Objects.hash(months, day, month, year);
    }

    @Override
    public String toString() {
        return day+"/"+month+"/"+year;
    }
}