package fr.istic.vv;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;

class Date implements Comparable<Date> {
    
    private int day, month, year;

    public Date(int day, int month, int year) {
        this.day = day;
    	this.month = month;
    	this.year = year;
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

    public static boolean isValidDate(int day, int month, int year) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
    	try {
    		sdf.parse(day+"/"+month+"/"+year);
        	return true;
    	}catch(Exception e) {
    		return false;
    	}
    }

    public static boolean isLeapYear(int year) {
        return Year.isLeap(year);
    }

    public Date nextDate() {
        LocalDate ld = LocalDate.of(year, month, day).plusDays(1);
    	return new Date(ld.getDayOfMonth(), ld.getMonthValue(), ld.getYear());
    }

    public Date previousDate() {
        LocalDate ld = LocalDate.of(year, month, day).minusDays(1);
    	return new Date(ld.getDayOfMonth(), ld.getMonthValue(), ld.getYear());
    }

    public int compareTo(Date other) {
        return LocalDate.of(year, month, day).compareTo(LocalDate.of(other.getYear(), other.getMonth(), other.getDay()));
    }

}
