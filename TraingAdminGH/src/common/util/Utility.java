package common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

public final class Utility
{

  /**
   * This method is used to check if the collection is null or empty.
   * 
   * @param collection
   *          collection to be checked
   * 
   * @return true - if collection is Null Or Empty, false; otherwise
   * 
   */
  public static boolean isCollectionNullOrEmpty(final Collection<?> collection)
  {
    boolean isCollectionNullOrEmpty = true;

    if (!isCollectionNull(collection) && collection.size() > 0)
    {
      isCollectionNullOrEmpty = false;
    }

    return isCollectionNullOrEmpty;
  }

  /**
   * This method is used to check if the collection is null.
   * 
   * @param collection
   *          collection to be checked
   * 
   * @return true - if collection is Null, false; otherwise
   * 
   */
  public static boolean isCollectionNull(final Collection<?> collection)
  {
    boolean isCollectionNull = true;

    if (collection != null)
    {
      isCollectionNull = false;
    }

    return isCollectionNull;
  }

  /**
   * This method is used to check if the object is null or empty.
   * 
   * @param object
   *          value to be checked
   * 
   * @return true - if value is Null Or Empty, false; otherwise
   * 
   */
  public static boolean isObjectNullOrEmpty(final Object object)
  {
    boolean isObjectNullOrEmpty = true;

    if (object != null && !Constants.EMPTY_STRING.equals(object.toString()))
    {
      isObjectNullOrEmpty = false;
    }

    return isObjectNullOrEmpty;
  }

  /**
   * This method converts boolean value into String Constant value. If boolean
   * value is true returns 'Y' else returns 'N'.
   * 
   * @param value
   *          value to be converted
   * 
   * @return String converted value
   */
  public static char convertBooleanToChar(final boolean value)
  {
    if (value)
    {
      return Constants.CHAR_Y_IN_CAPS;
    }
    else
    {
      return Constants.CHAR_N_IN_CAPS;
    }
  }

  /**
   * This method converts boolean value into String Constant value. If boolean
   * value is true returns 'Y' else returns 'N'.
   * 
   * @param value
   *          value to be converted
   * 
   * @return String converted value
   */
  public static String convertBooleanToString(final boolean value)
  {
    if (value)
    {
      return Constants.TEXT_YES;
    }
    else
    {
      return Constants.TEXT_NO;
    }
  }

  /**
   * This method converts boolean value into String Constant value. If boolean
   * value is true returns 'Y' else returns 'N'.
   * 
   * @param value
   *          value to be converted
   * 
   * @return String converted value
   */
  public static char convertStringToChar(final String value)
  {
    char convertedValue = Constants.INVALID_CHAR;
    if (!isObjectNullOrEmpty(value))
    {
      convertedValue = value.charAt(0);
    }
    return convertedValue;
  }

  /**
   * This method converts boolean value into String Constant value. If boolean
   * value is true returns 'Y' else returns 'N'.
   * 
   * @param value
   *          value to be converted
   * 
   * @return String converted value
   */
  public static String convertCharToString(final char value)
  {
    String convertedvalue = null;
    if (!isObjectNullOrEmpty(value))
    {
      convertedvalue = String.valueOf(value);

    }
    return convertedvalue;
  }

  /**
   * This method convert String value into boolean. If String value is 'Y'
   * return true else it is false.
   * 
   * @param value
   *          value to be converted
   * 
   * @return boolean converted value
   */
  public static boolean convertStringToBoolean(final String value)
  {

    boolean returnValue = false;

    if (!isObjectNullOrEmpty(value) && (Constants.TEXT_YES.equalsIgnoreCase(value)) || (convertCharToString(Constants.CHAR_Y_IN_CAPS).equals(value)))
    {
      returnValue = true;
    }

    return returnValue;
  }

  /**
   * This method is used to convert the value passed to a Double.
   * 
   * @param value
   *          to be converted
   * 
   * @return converted double value or null.
   */
  public static Double convertToDouble(final String value)
  {
    Double doubleValue = null;
    try
    {
      if (!isObjectNullOrEmpty(value))
      {
        doubleValue = Double.parseDouble(value);
      }
    }
    catch (NumberFormatException nfe)
    {
      doubleValue = null;
    }
    return doubleValue;
  }

  /**
   * This method is used to convert the value passed to a Long.
   * 
   * @param value
   *          to be converted
   * 
   * @return converted long value or null.
   */
  public static Long convertTolong(final String value)
  {
    Long longValue = null;
    try
    {
      if (!isObjectNullOrEmpty(value))
      {
        longValue = Long.parseLong(value);
      }
    }
    catch (NumberFormatException nfe)
    {
      longValue = null;
    }
    return longValue;
  }

  /**
   * This method is used to convert the value passed to an Integer.
   * 
   * @param value
   *          to be converted
   * @return converted integer value or null.
   */
  public static Integer convertToInteger(final String value)
  {
    Integer integerValue = null;
    try
    {
      if (!isObjectNullOrEmpty(value))
      {
        integerValue = Integer.parseInt(value);
      }
    }
    catch (NumberFormatException nfe)
    {
      integerValue = null;
    }
    return integerValue;
  }

  /**
   * This method is used to convert the any object value passed to an String.
   * 
   * @param value
   *          to be converted. This value is supposed to be a number only.
   * @return converted object value or null.
   */
  public static String convertNumberToString(final Object value)
  {
    String stringValue = null;
    if (!isObjectNullOrEmpty(value))
    {
      stringValue = value.toString();
    }
    return stringValue;
  }

  /**
   * This method is used to check if the string passed is of the particular
   * format or not.
   * 
   * @param format
   *          format to be checked with
   * 
   * @param stringToBeChecked
   *          string to be checked
   * 
   * @return true/false depending whether the parameter passed is satisfying the
   *         passed format or not.
   * 
   */
  public static boolean isMatchingFormat(final String format, final String stringToBeChecked)
  {
    boolean isMatchingFormat = false;
    if (!isObjectNullOrEmpty(stringToBeChecked) && !isObjectNullOrEmpty(format))
    {
      Pattern pattern = Pattern.compile(format);
      Matcher matcher = pattern.matcher(stringToBeChecked);
      isMatchingFormat = matcher.matches();
    }

    return isMatchingFormat;
  }

  /**
   * This method is used to check if the parameter passed is alpha numeric or
   * not.
   * 
   * @param aplhaNumValue
   *          the String to be checked
   * 
   * @return true if value is Alpha Numeric else false
   */
  public static boolean isAlphaNumeric(final String aplhaNumValue)
  {
    boolean matchFound = false;

    if (!isObjectNullOrEmpty(aplhaNumValue))
    {
      matchFound = isMatchingFormat(Constants.ALPHA_NUMERIC_FORMAT, aplhaNumValue);
    }

    return matchFound;
  }

  /**
   * This method is used to check if the parameter passed is of a numeric value
   * or not.
   * 
   * @param numValue
   *          the String to be checked
   * 
   * @return true if value is Numeric else false
   */
  public static boolean isNumeric(final String numValue)
  {
    boolean matchFound = false;

    if (!isObjectNullOrEmpty(numValue))
    {
      matchFound = isMatchingFormat(Constants.NUMERIC_FORMAT, numValue);
    }
    return matchFound;
  }

  /**
   * This method is used to get the current date without any formatting.
   * 
   * @return current(today's) date
   */
  public static Date getCurrentDate()
  {
    Date currentDate = new GregorianCalendar().getTime();
    return currentDate;
  }

  /**
   * This method is used to get the current date as per the format passed.
   * 
   * @param dateFormat
   *          date format
   * @return Date in a particular format
   */
  public static Date getCurrentDateAsPerFormat(final String dateFormat)
  {
    // gets the current date without any formatting
    Date currentDate = getCurrentDate();

    // formats the curent date as per the date format passed as the
    // parameter
    Date formattedDate = getDateAsPerFormat(currentDate, dateFormat);

    return formattedDate;
  }

  /**
   * This method is used to get the current date as per the format passed.
   * 
   * @param dateToBeFormatted
   *          date to be formatted
   * @param dateFormat
   *          date format
   * @return Date in a particular format
   */
  public static Date getDateAsPerFormat(final Date dateToBeFormatted, final String dateFormat)
  {
    Date formattedDate = null;
    if (!isObjectNullOrEmpty(dateToBeFormatted) && !isObjectNullOrEmpty(dateFormat))
    {
      // formats the date as per the format and return the date in the
      // string
      // format
      String formattedStringDate = convertDateToString(dateToBeFormatted, dateFormat);

      // converts the 'String' formatted date to 'Date'
      formattedDate = convertStringToDate(formattedStringDate, dateFormat);
    }
    else
    {
      formattedDate = dateToBeFormatted;
    }
    return formattedDate;
  }

  /**
   * This method is used to convert string formatted date to Date.
   * 
   * @param dateString
   *          String to convert to a date.
   * @param dateFormat
   *          date format
   * @return Date converted String to Date.
   * 
   */
  public static Date convertStringToDate(final String dateString, final String dateFormat)
  {
    Date convertedDate = null;

    if (!isObjectNullOrEmpty(dateString) && !isObjectNullOrEmpty(dateFormat))
    {
      // creating a date format from the parameter passed
      SimpleDateFormat sDateFormat = new SimpleDateFormat(dateFormat);
      try
      {
        // parsing the String to a particular formatted Date
        convertedDate = sDateFormat.parse(dateString);
      }
      catch (ParseException e)
      {
        /*
         * this block of code is reached when the parsing of the Date to a
         * particular format is not successful
         */
        convertedDate = null;
      }
    }
    return convertedDate;
  }

  /**
   * This method is used to convert date to String
   * 
   * @param date
   *          date to be converted to String.
   * @param dateFormat
   *          date format
   * @return Date converts String to Date.
   * 
   */
  public static String convertDateToString(final Date date, final String dateFormat)
  {
    String stringDate ="";

    if (!isObjectNullOrEmpty(date) && !isObjectNullOrEmpty(dateFormat))
    {
      // creating a date format from the parameter passed
      SimpleDateFormat sDateFormat = new SimpleDateFormat(dateFormat);

      /*
       * formats the date as per the format and return the date in the string
       * format
       */
      stringDate = sDateFormat.format(date);
    }
    return stringDate;
  }

  /**
   * This method is used to check if the Date is valid according to the
   * parameter Format passed.
   * 
   * @param dateString
   *          String to convert to a date.
   * @param dateFormat
   *          date format
   * @return true/false depending if the date is in a valid format or not.
   * 
   */
  public static boolean isValidDate(final String dateString, final String dateFormat)
  {
    Date aDate = null;

    boolean isValidDate = true;
    if (!isObjectNullOrEmpty(dateString) && !isObjectNullOrEmpty(dateFormat))
    {
      aDate = convertStringToDate(dateString, dateFormat);
    }
    if (null == aDate)
    {
      isValidDate = false;
    }
    return isValidDate;
  }

  /**
   * This method is used to check if the email address passed is a valid email
   * id or not.
   * 
   * @param emailId
   *          the email address to be checked
   * 
   * @return true if email address is valid else false
   */
  public static boolean isValidEmailAddress(final String emailId)
  {
    boolean isValidEmailAddress = true;

    if (!isObjectNullOrEmpty(emailId))
    {
      isValidEmailAddress = isMatchingFormat(Constants.EMAIL_VALIDATION_FORMAT, emailId);
    }
    return isValidEmailAddress;
  }

  /**
   * This method is used to trim spaces which are falling before, after or at
   * both the places of the entered string.
   * 
   * @param stringToBeTrimmed
   *          string to be trimmed
   * @param locationToBeTrimmed
   *          location to be trimmed
   * @return trimmed string
   */
  public static String trimSpaces(final String stringToBeTrimmed, String locationToBeTrimmed)
  {
    String trimmedString = null;
    if (!isObjectNullOrEmpty(stringToBeTrimmed))
    {
      if (Constants.TRIM_LEFT.equals(locationToBeTrimmed))
      {
        trimmedString = leftTrim(stringToBeTrimmed);
      }
      else if (Constants.TRIM_RIGHT.equals(locationToBeTrimmed))
      {
        trimmedString = rightTrim(stringToBeTrimmed);
      }
      else
      {
        trimmedString = trim(stringToBeTrimmed);
      }
    }
    else
    {
      trimmedString = stringToBeTrimmed;
    }
    return trimmedString;
  }

  /**
   * This method is used to trim the spaces on the left of the entered word.
   * 
   * @param stringToBeTrimmed
   *          string to be trimmed
   * @return trimmed string
   */
  private static String leftTrim(String stringToBeTrimmed)
  {
    return StringUtils.stripStart(stringToBeTrimmed, Constants.BLANK_STRING);
  }

  /**
   * This method is used to trim the spaces on the right of the entered word.
   * 
   * @param stringToBeTrimmed
   *          string to be trimmed
   * @return trimmed string
   */
  private static String rightTrim(String stringToBeTrimmed)
  {
    return StringUtils.stripEnd(stringToBeTrimmed, Constants.BLANK_STRING);
  }

  /**
   * This method is used to trim the spaces on the left and right of the entered
   * word.
   * 
   * @param stringToBeTrimmed
   *          string to be trimmed
   * @return trimmed string
   */
  private static String trim(String stringToBeTrimmed)
  {
    return stringToBeTrimmed.trim();
  }

  /**
   * Returns the start date of current year.
   * 
   * @param dateFormat
   *          date format
   * @return Date
   * 
   * @author hemantkumarnj
   * @date 17.Jun.2008
   */
  public static Date getStartDate(final String dateFormat)
  {
    final String firstOfJan = "01-JAN-";
    return getDateAsPerFormat(convertStringToDate(firstOfJan + Calendar.getInstance().get(Calendar.YEAR), Constants.DATE_FORMAT_DD_MMM_YYYY),
        dateFormat);
  }

  /**
   * This method is used to get an empty string incase the parameter passed is
   * null.
   * 
   * @param pObject -
   *          object to be checked.
   * 
   * @return empty string if the parameter passed is null.
   * 
   */
  public static Object ifNullGetEmptyString(Object pObject)
  {
    return pObject == null ? Constants.EMPTY_STRING : pObject;
  }

  /**
   * This method is used to get an empty string incase the parameter passed is
   * null.
   * 
   * @param collection -
   *          Collection to be cleared or nullified.
   * @param clear
   *          boolean whether the collection is to be cleared or nullified.
   * @return empty string if the parameter passed is null.
   * 
   */
  public static Collection<?> clearorNullifyCollection(Collection<?> collection, boolean clear)
  {
    Collection<?> collectionToBeReturned = collection;
    if (!isCollectionNull(collection))
    {
      if (clear)
      {
        collectionToBeReturned.clear();
      }
      else
      {
        collectionToBeReturned = null;
      }
    }
    return collectionToBeReturned;
  }

  /**
   * This method is used to get an empty string incase the parameter passed is
   * null.
   * 
   * @param stringToBeBrokenDown -
   *          string to be tokenised.
   * @param delimeter
   *          delimeter for tokenising
   * @return empty string if the parameter passed is null.
   * 
   */
  public static String[] getStringSeparatedByDelimiter(String stringToBeBrokenDown, String delimeter)
  {
    String[] tokenizedString = null;
    if (!isObjectNullOrEmpty(stringToBeBrokenDown))
    {
      tokenizedString = stringToBeBrokenDown.split(delimeter);
    }

    return tokenizedString;
  }

  /**
   * This method is used to check if the parameter entered is a correct amount
   * or not.
   * 
   * @param amount -
   *          amount to be checked.
   * 
   * @return true if the amount is valid false otherwise.
   * 
   */
  @Deprecated
  public static boolean isAmountValid(final double amount)
  {
    boolean isAmountValid = false;
    if (!isObjectNullOrEmpty(amount))
    {
      isAmountValid = isMatchingFormat(Constants.MONETARY_DATA_FORMAT, String.valueOf(amount));
    }
    return isAmountValid;
  }

  /**
   * This method is used to check if the parameter entered is a correct amount
   * or not.
   * 
   * @param amount -
   *          amount to be checked.
   * 
   * @return true if the amount is valid false otherwise.
   * 
   */
  public static boolean isAmountValid(final String amount)
  {
    boolean isAmountValid = false;
    if (!isObjectNullOrEmpty(amount))
    {
      isAmountValid = isMatchingFormat(Constants.MONETARY_DATA_FORMAT, amount);
    }
    return isAmountValid;
  }

  /**
   * This method is used to get an empty string incase the parameter passed is
   * null.
   * 
   * @param dateToBeCompared -
   *          date to be compared.
   * @param dateToCompareWith -
   *          date to compare with.
   * @return -1,0,1 depending on whether the first date is before, same day or
   *         after the the second date respectively.
   * 
   */
  public static String compareDate(Date dateToBeCompared, Date dateToCompareWith)
  {
    String comparedDate = null;
    if (!isObjectNullOrEmpty(dateToBeCompared) && !isObjectNullOrEmpty(dateToCompareWith))
    {
      getDateAsPerFormat(dateToBeCompared, Constants.DATE_FORMAT_DD_MM_YYYY);
      getDateAsPerFormat(dateToCompareWith, Constants.DATE_FORMAT_DD_MM_YYYY);

      if (dateToBeCompared.before(dateToCompareWith))
      {
        comparedDate = Constants.DATE_BEFORE;
      }
      else if (dateToBeCompared.after(dateToCompareWith))
      {
        comparedDate = Constants.DATE_AFTER;
      }
      else
      {
        comparedDate = Constants.DATE_SAME;
      }

    }
    return comparedDate;
  }

  /**
   * This method returns Concatenated String with Separator added in between the
   * two words.
   * 
   * @param pStringCollection
   *          Collection<String> collection of string to be concatenated
   * @param pSeperator
   *          String separator to be used between two words
   * @return Concatenated String
   */
  public static String concatenateStringList(Collection<String> pStringCollection, String pSeperator)
  {
    String concatenatedString = Constants.EMPTY_STRING;
    for (String stringToConCatenate : pStringCollection)
    {
      if (stringToConCatenate != null)
      {
        if (concatenatedString.equalsIgnoreCase(Constants.EMPTY_STRING))
        {
          concatenatedString = concatenatedString + stringToConCatenate;
        }
        else
        {
          concatenatedString = concatenatedString + pSeperator + stringToConCatenate;
        }
      }
    }
    return concatenatedString;
  }

  /**
   * This method is used to convert object to string
   * 
   * @param pObject
   *          object to be converted
   * @return object converted to string
   */
  public static String convertObjectToString(Object pObject)
  {
    return ToStringBuilder.reflectionToString(pObject);
  }

  /**
   * This method is used to get the start date of the specified year as per the
   * format passed.
   * 
   * @param dateFormat
   *          date format
   * @param pYear
   *          year
   * @return Date in a particular format
   */
  public static Date getStartOfTheYearDateAsPerFormat(final String dateFormat, final String pYear)
  {
    Calendar calendar = new GregorianCalendar();
    calendar.set(Calendar.DATE, 1);
    calendar.set(Calendar.MONTH, 0);
    calendar.set(Calendar.YEAR, Integer.parseInt(pYear));

    // gets the date without any formatting
    Date date = calendar.getTime();

    // formats the date as per the date format passed as the parameter
    Date formattedDate = getDateAsPerFormat(date, dateFormat);

    return formattedDate;
  }
}
