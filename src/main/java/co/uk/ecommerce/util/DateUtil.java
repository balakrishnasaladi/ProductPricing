package co.uk.ecommerce.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil
{

	public static String dateAsString(final Date date) throws ParseException
	{
		final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		final String dateString = formatter.format(date);
		return dateString;
	}

	public static Date convertDate(final String dateString) throws ParseException
	{
		final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		final Date date = formatter.parse(dateString);
		return date;
	}

	public static Date incrementDate(final Date date)
	{
		final Calendar calander = Calendar.getInstance();
		calander.setTime(date);
		calander.add(Calendar.DATE, 1);
		return calander.getTime();
	}

	public static boolean isCurrent(final Date fromDate, final Date toDate)
	{
		final Date current = new Date();
		if (current.after(fromDate) && current.before(toDate))
		{
			return true;
		}
		return false;
	}
}
