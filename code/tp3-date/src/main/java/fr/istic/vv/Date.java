package fr.istic.vv;

class Date implements Comparable<Date> {

    int day;
    int month;
    int year;

    public Date(int day, int month, int year) {
        if(!isValidDate(day, month, year)) throw new IllegalArgumentException("Date invalide");
        this.day=day;
        this.month=month;
        this.year=year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        //cas de l'annee
        if(year<=0) return false;
        //cas des mois
        if(month<1 || month >12) return false;
        //cas des jours inférieurs à 1 ou supérieur à 31
        if(day<1 || day>31) return false;

        //cas du mois de Février qui change selon si l'annee est bissextile ou non
        if(month == 2){
            if(isLeapYear(year)) return day<=29;
            else return day<=28;
        }

        //cas général, pour les mois à 30 jours
        if(month == 4 || month == 6 || month == 9 || month == 11) return day <=30;
        //sinon, il s'agit de mois à 31 jours
        return day <= 31;
    }

    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year %100 !=0);
    }

    public Date nextDate() {
        int day = this.day;
        int month = this.month;
        int year = this.year;

        //cas de la fin d'annee
        if(day == 31 && month == 12){
            return new Date(1,1,year+1);
        }

        //cas de la fin de mois de 30 jours
        else if(day == 30 && (month == 4 || month == 6 || month == 9 || month == 11)){
            return new Date(1, month+1, year);
        }

        //cas de la fin de mois de 31 jours
        else if(day == 31 && (month == 1 || month == 3 || month == 5 
                || month == 7 || month == 8 || month == 10)){
            return new Date(1, month+1, year);
        }

        //cas du mois de Février en annee non bissextile
        else if(day == 28 && month == 2 && !isLeapYear(year)){
            return new Date(1, month+1, year);
        }

        //cas du mois de Février durant une annee bissextile
        else if(day == 29 && month == 2 && isLeapYear(year)){
            return new Date(1, month+1, year);
        }

        //cas des autres jours des mois
        else return new Date(day+1, month, year);
    }

    public Date previousDate() { 
        int day = this.day;    
        int month = this.month;
        int year = this.year;

        //cas du premier d'une annee (donc nouvel an)
        if(day == 1 && month == 1) return new Date(31, 12, year-1);

        //cas du premier des mois dont le mois précédent possède 30 jours
        if(day == 1 && (month == 5 || month == 7  || month == 10 || month == 12)){
            return new Date(30, month-1, year);
        }

        //cas du premier des mois dont le mois précédent contient 31 jours
        else if (day == 1 && (month == 2 || month == 4 || month == 6 || month == 8 
                                || month == 9 || month == 11)){
            return new Date(31, month-1, year);
        }

        //cas du premier du mois de Mars en annee non bissextile
        else if(day == 1 && month == 3 && !isLeapYear(year)) {
            return new Date(28, month-1, year);
        }

        //cas du premier du mois de Mars en annee bissextile
        else if(day == 1 && month == 3 && isLeapYear(year)) {
            return new Date(29, month-1, year);
        }

        //tous les autres jours des mois
        else{
            return new Date(day-1, month, year);
        }
    }

    /**
     * compareTo follows the Comparable convention:

     date.compareTo(other) returns a positive integer if date is posterior to other
     date.compareTo(other) returns a negative integer if date is anterior to other
     date.compareTo(other) returns 0 if date and other represent the same date.
     the method throws a NullPointerException if other is null

     */
    public int compareTo(Date other) {
        //cas de la date "other" étant nulle
        if(other == null) throw new NullPointerException("Impossible de comparer avec une date nulle");
        
        //cas de l'annee différente donc soit inferieure soit superieure
        if(this.year != other.year){
            //si date est inferieure a other, alors -1 sinon +1
           return (this.year < other.year)?-1:1;
        }

        //si les annees sont les memes, alors on passe aux mois
        if(this.month != other.month){
            /*si les mois sont différents, que le mois de date est inferieur à celui de other,
             alors on retourne -1 sinon +1
            */
            return (this.month < other.month)?-1:1;
        }

        //si les mois sont également identiques, alors faire de même avec les jours
        //ici, le cas où les jours sont différents
        if(this.day != other.day){
            return (this.day < other.day)?-1:1;
        }

        //tous les cas où les dates ne pouvaient être égales ont été écartés, donc 
        //date et other sont forcément égales
        return 0;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Date){
            Date d = (Date)other;
            return this.year == d.year && this.month == d.month && this.day == d.day;
        }else return false;
    }

}