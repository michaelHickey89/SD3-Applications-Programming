package src.main;

import  src.helper.DateValidator;
import src.helper.FileParser;

/**
 *
 * @author Alan.Ryan
 */

public class Main {
     public static void main(String[] args) {
    
    System.out.println("Is 31/01 a valid date? " + DateValidator.isValidDate("31/01"));
    System.out.println("Is 32/01 a valid date? " + DateValidator.isValidDate("32/01"));
    System.out.println("Is 29/02 a valid date? " + DateValidator.isValidDate("29/02"));
    
    System.out.println("\n");
    
    System.out.println("09/02 is the "+ DateValidator.getSuffix(DateValidator.getDayOfYear("09/02")) + " day of the year");
    System.out.println("28/06 is the "+ DateValidator.getSuffix(DateValidator.getDayOfYear("28/06")) + " day of the year");
    System.out.println("25/12 is the "+ DateValidator.getSuffix(DateValidator.getDayOfYear("25/12")) + " day of the year");
    
    //read the contents of the file into an array
    int[] days = FileParser.fillArray();
     
    
    //print data from the file - there's nothing in element 0 of the array 
    for (int day : days) {
             System.out.print(day + " ");
    }
  }
}
