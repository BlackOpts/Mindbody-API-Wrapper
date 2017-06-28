package mbapi.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mbapi.Constants.ScheduleType;
import mbapi.Models.Service;
import mbapi.Models.ServiceCategory;
import mbapi.Models.SessionType;

/**
 * Created on 8/23/16.
 */
public class Utility
{

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    /**
     * Get date from date string in the format of yyyy-MM-ddThh:mm:ss
     * @param str
     * @return null if parsing fails
     */
    public static Date getDateFromISO(String str)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try
        {
            return formatter.parse(str);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    public static  Date addDaysToDate(Date date, int days)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    /**
     * Get date from date string in the format of yyyy-MM-ddThh:mm:ss
     * @param date
     * @return null if parsing fails
     */
    public static String getStringFromDate(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return formatter.format(date);
    }

    /**
     * Get date from date string in the given date format
     * @param date
     * @param format
     * @return null if parsing fails
     */
    public static String getStringFromDate(Date date, String format)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static boolean dateIsAfterDate(Date date1, Date date2)
    {
        long d1 = Long.parseLong(simpleDateFormat.format(date1));
        long d2 = Long.parseLong(simpleDateFormat.format(date2));

        return d1 > d2;
    }

    public static boolean dateIsBeforeDate(Date date1, Date date2)
    {
        long d1 = Long.parseLong(simpleDateFormat.format(date1));
        long d2 = Long.parseLong(simpleDateFormat.format(date2));

        return d1 < d2;
    }

    public static boolean dateIsEquelToDate(Date date1, Date date2)
    {
        long d1 = Long.parseLong(simpleDateFormat.format(date1));
        long d2 = Long.parseLong(simpleDateFormat.format(date2));

        return d1 == d2;
    }

    /**
    *  Convenience method to add a specified number of minutes to a Date object
    *  @param  minutes  The number of minutes to add
    *  @param  beforeTime  The time that will have minutes added to it
    *  @return  A date object with the specified number of minutes added to it
    */
    public static Date addMinutesToDate(int minutes, Date beforeTime)
    {
        final long ONE_MINUTE_IN_MILLIS = 60000; //millisecs

        long timeInMilliseconds = beforeTime.getTime();
        Date newDate = new Date(timeInMilliseconds + (minutes * ONE_MINUTE_IN_MILLIS));
        return newDate;
    }

    /**
     *  Convenience method to add a specified number of years to a Date object
     *  @param  years  The number of minutes to add
     *  @param  beforeTime  The time that will have years added to it
     *  @return  A date object with the specified number of minutes added to it
     */
    public static Date addYearsToDate(int years, Date beforeTime)
    {
        final long ONE_YEAR_IN_MILLIS = 60000 * 60 * 24 * 365;

        long timeInMilliseconds = beforeTime.getTime();
        Date newDate = new Date(timeInMilliseconds + (years * ONE_YEAR_IN_MILLIS));
        return newDate;
    }

    public static ArrayList<ServiceCategory> filterServiceCategoriesByScheduleType(ArrayList<ServiceCategory> all, ScheduleType scheduleType)
    {
        ArrayList<ServiceCategory> result = new ArrayList<>();
        for (ServiceCategory sc: all)
        {
            if (sc.Type == scheduleType)
            {
                result.add(sc);
            }
        }
        return result;
    }

    public static ArrayList<SessionType> filterSessionTypesByServiceCategory(ArrayList<SessionType> all, ServiceCategory serviceCategory)
    {
        ArrayList<SessionType> result = new ArrayList<>();
        for (SessionType sessionType: all)
        {
            if (sessionType.ServiceCategoryID.equals(serviceCategory.ID))
            {
                result.add(sessionType);
            }
        }
        return result;
    }

    public static Service filterForLowestPriceService(List<Service> list)
    {
        Service current = null;
        for (Service item : list)
        {
            if (current == null)
            {
                current = item;
            }
            else if (Double.parseDouble(item.OnlinePrice) < Double.parseDouble(current.OnlinePrice))
            {
                current = item;
            }
        }
        return current;
    }
}
