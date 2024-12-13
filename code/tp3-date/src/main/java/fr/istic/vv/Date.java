package fr.istic.vv;

class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;

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

    public Date(int day, int month, int year) throws Exception {
        if (!isValidDate(day, month, year)) {
            throw new Exception("Invalide date format");
        }
        
        this.day = day;
        this.month = month;
        this.year = year;
        
     }

    public static boolean isValidDate(int day, int month, int year) { 
        if (year < 0 || month < 1 || month > 12 || day < 1) {
            return false;
        }

        // Days in each month (considering leap years for February)
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // February has 29 days in a leap year
        if (isLeapYear(year)) {
            daysInMonth[1] = 29;
        }

        return day <= daysInMonth[month - 1];
    }
     

    public static boolean isLeapYear(int year) { 
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
     }

    public Date nextDate() throws Exception { 
        // Increment the day
        int newDay = day + 1;
        int newMonth = month;
        int newYear = year;
  
        // Handle overflow of days in the month
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  
        if (isLeapYear(year)) {
          daysInMonth[1] = 29; // February has 29 days in a leap year
        }
  
        if (newDay > daysInMonth[month - 1]) {
            newDay = 1;
            newMonth++;
            if (newMonth > 12) {
                newMonth = 1;
                newYear++;
            }
        }
        return new Date(newDay, newMonth, newYear);
    }

    public Date previousDate() throws Exception { 
        // Decrement the day
        int newDay = day - 1;
        int newMonth = month;
        int newYear = year;
  
        // Handle underflow of days in the month
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  
        if (isLeapYear(year)) {
            daysInMonth[1] = 29; // February has 29 days in a leap year
        }
  
        if (newDay < 1) {
            newMonth--;
            if (newMonth < 1) {
                newMonth = 12;
                newYear--;
            }
            newDay = daysInMonth[newMonth - 1];
        }
        return new Date(newDay, newMonth, newYear);
    }

    public int compareTo(Date other) { 
        if (other == null) {
            throw new NullPointerException("Argument cannot be null");
        }

        // Compare years
        if (this.year != other.year) {
            return this.year - other.year;
        }

        // Compare months
        if (this.month != other.month) {
            return this.month - other.month;
        }

        // Compare days
        return this.day - other.day;
    }

}