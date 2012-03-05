package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static int getDayKey(String datekey)
    {
        try
        {
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date date = (Date)formatter.parse(datekey);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        
            return cal.get(Calendar.DAY_OF_WEEK);
        }
        catch(ParseException e)
        {
            System.out.println(e);
            return 0;
        }
    }
    
    public static Date getToday()
    {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();
        
        return date;
    }

    public static long getWeekStart(String datekey)
    {
        try
        {
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date date = (Date)formatter.parse(datekey);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        
            // Set calendar to Sunday
            cal.set(Calendar.DAY_OF_WEEK, 1);
        
            return cal.getTimeInMillis();
        }
        catch(ParseException e)
        {
            System.out.println(e);
            return 0;
        }
    }

    public static long getLastSunday(long weekstart)
    {
        Date date = new Date(weekstart);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        // Set calendar last Sunday
        cal.add(Calendar.DAY_OF_MONTH, -7);
        
        return cal.getTimeInMillis();
    }

    /*public static long getWeekStart()
    {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        // Set calendar to Sunday
        cal.set(Calendar.DAY_OF_WEEK, 1);
        
        return cal.getTimeInMillis();
    }
    
 public static ArrayList<String> getWeekDays()
    {
        ArrayList<String> days = new ArrayList<String>();
        
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        // Set calendar to Sunday
        cal.set(Calendar.DAY_OF_WEEK, 1);
        
        days.add(cal.toString());
        cal.set(Calendar.DAY_OF_WEEK, 2);
        days.add(cal.toString());
        cal.set(Calendar.DAY_OF_WEEK, 3);
        days.add(cal.toString());
        cal.set(Calendar.DAY_OF_WEEK, 4);
        days.add(cal.toString());
        cal.set(Calendar.DAY_OF_WEEK, 5);
        days.add(cal.toString());
        cal.set(Calendar.DAY_OF_WEEK, 6);
        days.add(cal.toString());
        cal.set(Calendar.DAY_OF_WEEK, 7);
        days.add(cal.toString());
        
        return days;
    }*/
}
