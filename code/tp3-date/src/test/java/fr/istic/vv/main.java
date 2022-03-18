package fr.istic.vv;

import org.junit.platform.commons.util.StringUtils;

import java.util.Scanner;

public class main {

    public static void main(String[] args){

        Scanner scanner= new Scanner(System.in);
        System.out.print("Write a date with the following format : \"dd mm aaaa\"\n" +
                "Where dd is the day, mm the month and aaaa the year with a numerical format. For instance:\n" +
                "\"26 01 1999\"\n>");

        String initString = scanner.nextLine();

        String[] map = initString.split(" ");
        Date date = new Date(Integer.parseInt(map[0]),Integer.parseInt(map[1]),Integer.parseInt(map[2]));

        System.out.print("The saved date is :"+date+"\n>");
        System.out.print("Type any of the following letter to apply a method on the saved object :\n" +
                "'A' to see if the date is valid.\n" +
                "'B' to see if the date is in a leap year.\n" +
                "'C' to get the next date.\n" +
                "'D' to get the previous date.\n>");

        while (!initString.isBlank()){
            initString = scanner.nextLine();
            switch (initString){
                case "A": System.out.print("Validity of the date : "+date.isValidDate()+"\n>");break;
                case "B": System.out.print("The year is a leap one: "+date.isLeapYear()+"\n>");break;
                case "C": System.out.print("The next day is : "+date.nextDate()+"\n>");break;
                case "D": System.out.print("The previous day is : "+date.previousDate()+"\n>");break;
            }
        }

    }

}
