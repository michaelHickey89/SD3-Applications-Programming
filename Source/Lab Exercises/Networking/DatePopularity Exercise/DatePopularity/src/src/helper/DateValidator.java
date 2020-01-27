package src.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateValidator {
 
    
    //this method return value is based on a 366 day year (i.e. a leap year)
    public static int getDayOfYear(String dateString) {
        int month = 0;
        SimpleDateFormat sdf;
        Date date = null;
        try {
            month = Integer.parseInt(dateString.substring(3));

            sdf = new SimpleDateFormat("dd/MM");
            date = null;

            date = sdf.parse(dateString);
        }//end try
        catch (Exception ex) {
            System.out.println(ex);
        }//end catch
        GregorianCalendar gCal = new GregorianCalendar();
        gCal.setTime(date);

        if (month >= 3) 
            return gCal.get(GregorianCalendar.DAY_OF_YEAR) + 1;
         else 
            return gCal.get(GregorianCalendar.DAY_OF_YEAR);
    }
    
    //this method validates a date in the form dd/mm
    public static boolean isValidDate(String dateToValidate) {

        dateToValidate += "/2016";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            sdf.parse(dateToValidate);

        } catch (ParseException e) {

            return false;
        }

        return true;

    }
    
    //returns the appropriate suffix for a number
    public static String getSuffix(int day) {
        String[] suffix = {"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        int m = day % 100;
        return String.valueOf(day) + suffix[(m > 10 && m < 20) ? 0 : (m % 10)];
    }
}