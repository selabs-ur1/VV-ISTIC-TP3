package fr.istic.vv;

import fr.istic.vv.Exception.InvalidDateException;
import fr.istic.vv.Exception.InvalidMethodUseException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

class Date implements Comparable<Date> {

    public static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    private int day;
    private int month;
    private int year;


    public Date(int day, int month, int year) throws InvalidDateException {
        if (!isValidDate(day,month,year)) {
            throw new InvalidDateException("The date is not valid");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        try {
            df.setLenient(false);
            df.parse(day+"/"+month+"/"+year);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isLeapYear(int year)
    {
        return isValidDate(29,2,year);
    }

    public Date nextDate() throws InvalidDateException
    {
        //normal case
        if(isValidDate(this.getDay()+1,this.getMonth(),this.getYear()))
        {
            return new Date(this.getDay()+1,this.getMonth(),this.getYear());
        }
        // current day is last of the month
        else if (isValidDate(1,this.getMonth() + 1,this.getYear()))
        {
            return new Date(1,this.getMonth() + 1,this.getYear());
        }
        // current day is last of the year
        else
        {
            return new Date(1,1,this.getYear()+1);
        }
    }

    public Date previousDate() throws InvalidDateException, InvalidMethodUseException {

        final int month = this.getMonth();

        if(month == 1 && this.getDay() == 1 && this.getYear() == 1)
        {
            throw new InvalidMethodUseException("previousDate", "unable to parse year Before Christ");
        }
        //normal case
        if(this.getDay() != 1)
        {
            return new Date(this.getDay()-1,this.getMonth(),this.getYear());
        }

        //change year error would be thrown if year == 1
        if(this.getMonth() == 1)
        {
            return new Date(31,12,this.getYear()-1);
        }

        // day == 1 && month before is a 31 ending one
        if(month == 2 || month == 4 || month ==  6 || month ==  8 || month == 9 || month == 11)
        {
            return new Date(31,month-1,this.getYear());
        }

        // day == 1 && month before is a 30 ending one
        if(month ==  5 || month ==  7 || month == 10 || month == 12)
        {
            return new Date(30,month-1,this.getYear());
        }

        // day == 1 && month before is a 31 ending one
        return isLeapYear(this.getYear()) ?
                new Date(29,2,this.getYear()) :
                new Date(28,2,this.getYear());

    }

    public int compareTo(Date other)
    {
        final int year = this.getYear();
        final int month = this.getMonth();
        final int day = this.getDay();
        final int oYear = other.getYear();
        final int oMonth = other.getMonth();
        final int oDay = other.getDay();

        //same year
        if( year == oYear)
        {
            //same mont
            if( month == oMonth)
            {
                //same day
                if(day == oDay) {
                    return 0;
                }
                return day < oDay ? -1 : 1;
            }
            else
            {
                return month < oMonth ? -1 : 1;
            }
        }
        else {
            return year < oYear ? -1 : 1;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return day == date.day && month == date.month && year == date.year;
    }
}