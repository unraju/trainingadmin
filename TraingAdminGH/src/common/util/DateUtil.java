package common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtil
{

  private static String conversionDate = "dd-MMM-yyyy";

  public static String conversionDate1 = "dd-MMM-yyyy HH:mm:ss";

  public static final String DD_MM_YYYY_FORMAT = "dd/MM/yyyy";

  public static final String DD_MMM_YYYY_FORMAT = "dd-MMM-yyyy";

  /**
   * 
   * @param date
   * @return
   */
  public static boolean checkSlashPositionInDate(String date)
  {

    char firstSlash = date.charAt(2);
    char secondSlash = date.charAt(5);

    if (firstSlash == '/' && secondSlash == '/')
    {
      return true;
    }
    return false;
  }

  /**
   * @param date
   * @param strDtFormat
   * @return
   * @throws ParseException
   */
  public static Date getFormattedDateFromString(String date) throws ParseException
  {

    SimpleDateFormat df = new SimpleDateFormat(conversionDate);
    df.setLenient(false);
    return df.parse(date);
  }
  
  /**
   * @param date
   * @param strDtFormat
   * @return
   * @throws ParseException
   */
  public static Date getFormattedDateWithHH_MM_SS_FromString(String date) throws ParseException
  {

    SimpleDateFormat df = new SimpleDateFormat(conversionDate);
    df.setLenient(false);
    return df.parse(date);
  }

  /**
   * 
   * @param timestamp
   * @return
   */
  public static String getStringFromTimestamp(Timestamp timestamp)
  {
    String strDate = "";

    try
    {
      SimpleDateFormat dateFormat = new SimpleDateFormat(conversionDate);
      if (timestamp != null)
      {
        strDate = dateFormat.format(timestamp);
      }
    }
    catch (Exception e)
    {
    }

    return strDate;
  }
  
  public static String getStringFromTimestamp(Timestamp timestamp, String reqDateFormat)
  {
    String strDate = "";

    try
    {
      SimpleDateFormat dateFormat = new SimpleDateFormat(reqDateFormat);
      if (timestamp != null)
      {
        strDate = dateFormat.format(timestamp);
      }
    }
    catch (Exception e)
    {
    }

    return strDate;
  }

  /**
   * 
   * @return
   */
  public static Timestamp getCurrentDateAsTimestamp()
  {
    Calendar c = Calendar.getInstance();
    long time = c.getTimeInMillis();
    return new Timestamp(time);
  }
  
  public static Date getCurrentDateAsTimestampinGMT0530()
  {

    String timeZoneId = "GMT+05:30";
    Calendar mbCal = new GregorianCalendar(TimeZone.getTimeZone(timeZoneId));
    mbCal.setTimeInMillis(new Date().getTime());
  
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, mbCal.get(Calendar.YEAR));
    cal.set(Calendar.MONTH, mbCal.get(Calendar.MONTH));
    cal.set(Calendar.DAY_OF_MONTH, mbCal.get(Calendar.DAY_OF_MONTH));
    cal.set(Calendar.HOUR_OF_DAY, mbCal.get(Calendar.HOUR_OF_DAY));
    cal.set(Calendar.MINUTE, mbCal.get(Calendar.MINUTE));
    cal.set(Calendar.SECOND, mbCal.get(Calendar.SECOND));
    cal.set(Calendar.MILLISECOND, mbCal.get(Calendar.MILLISECOND));
    return  cal.getTime();
  }

  /**
   * 
   * @param date
   * @param strDtFormat
   * @return
   */
  public static String getStringFromDate(Date date, String strDtFormat)
  {
    String strDate = "";

    try
    {
      SimpleDateFormat dateFormat = new SimpleDateFormat(strDtFormat);
      if (date != null)
      {
        strDate = dateFormat.format(date);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return strDate;
  }

  /**
   * 
   * @return
   */
  public static String getCurrentDateAsString()
  {
    Calendar c = Calendar.getInstance();
    Date currentDate = c.getTime();
    return getStringFromDate(currentDate, conversionDate);
  }

  public static String getCurrentDateAsString(String strDtFormat)
  {
    Calendar c = Calendar.getInstance();
    Date currentDate = c.getTime();
    return getStringFromDate(currentDate, strDtFormat);
  }


  public static Date getDateFromStringFormattedDDMMYYYY(String date) throws ParseException
  {
    Date today = null;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    try
    {
      today = df.parse(date);
    }
    finally
    {

    }
    return today;
  }

  public static Date getDateFromStringFormatDATE_TIME(String date) throws ParseException
  {
    Date today = null;
    String dateFormat = "";
    if (date.endsWith("AM") || date.endsWith("PM"))
    {
      dateFormat = "dd-MMM-yyyy hh:mm aaa";
    }
    else
    {
      dateFormat = "dd-MMM-yyyy hh:mm";
    }
    DateFormat df = new SimpleDateFormat(dateFormat);
    try
    {
      today = df.parse(date);
    }
    finally
    {

    }
    return today;
  }

  public static Date getDateFromStringFormattedMMDDYYYY(String date) throws ParseException
  {
    Date today = null;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    try
    {
      today = df.parse(date);
   
    }
    catch (ParseException e)
    {

      e.printStackTrace();
    }
    return today;
  }

  public static String getDateFromStringFormat_dd_MMM_yyyy()
  {
    Calendar c = Calendar.getInstance();
    Date currentDate = c.getTime();
    return getStringFromDate(currentDate, "dd-MMM-yyyy");

  }

  public static Date getDDMMYYYYFormattedDateFromString(String date) throws ParseException
  {
    Date today = null;

    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    try
    {
      today = df.parse(date);
    }
    catch (ParseException e)
    {

      e.printStackTrace();
    }
    return today;
  }

  public static Date getDateInGMT0530Zone(Date currentDate)
  {
    String timeZoneId = "GMT+05:30";
    TimeZone tz = TimeZone.getTimeZone(timeZoneId);
    Calendar mbCal = new GregorianCalendar(TimeZone.getTimeZone(timeZoneId));
    mbCal.setTimeInMillis(currentDate.getTime());
  
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, mbCal.get(Calendar.YEAR));
    cal.set(Calendar.MONTH, mbCal.get(Calendar.MONTH));
    cal.set(Calendar.DAY_OF_MONTH, mbCal.get(Calendar.DAY_OF_MONTH));
    cal.set(Calendar.HOUR_OF_DAY, mbCal.get(Calendar.HOUR_OF_DAY));
    cal.set(Calendar.MINUTE, mbCal.get(Calendar.MINUTE));
    cal.set(Calendar.SECOND, mbCal.get(Calendar.SECOND));
    cal.set(Calendar.MILLISECOND, mbCal.get(Calendar.MILLISECOND));
  
    return cal.getTime();
  }
  
  public static Date getDateInUKTimeZone(Date currentDate)
  {
    String timeZoneId = "GMT";
    Calendar mbCal = new GregorianCalendar(TimeZone.getTimeZone(timeZoneId));
    mbCal.setTimeInMillis(currentDate.getTime());
  
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, mbCal.get(Calendar.YEAR));
    cal.set(Calendar.MONTH, mbCal.get(Calendar.MONTH));
    cal.set(Calendar.DAY_OF_MONTH, mbCal.get(Calendar.DAY_OF_MONTH));
    cal.set(Calendar.HOUR_OF_DAY, mbCal.get(Calendar.HOUR_OF_DAY));
    cal.set(Calendar.MINUTE, mbCal.get(Calendar.MINUTE));
    cal.set(Calendar.SECOND, mbCal.get(Calendar.SECOND));
    cal.set(Calendar.MILLISECOND, mbCal.get(Calendar.MILLISECOND));
  
    return cal.getTime();
  }
  

  public static void main(String[] args)
  {
  Date now = new Date();

  System.out.println("Current Time="+now);
  Calendar cal = Calendar.getInstance();
  System.out.println("Current Timezone="+cal.getTimeZone().getDisplayName());

  //Canada/Central
  String timeZoneId = "India";
  System.out.println("Getting Time in the timezone="+timeZoneId);
  System.out.println("Current Time there="+getDateInGMT0530Zone(now));

  //UK
  String timeZoneId1 = "UK";
  System.out.println("Getting Time in the timezone="+timeZoneId1);
  System.out.println("Current Time there="+getDateInUKTimeZone(now));
  }

}
