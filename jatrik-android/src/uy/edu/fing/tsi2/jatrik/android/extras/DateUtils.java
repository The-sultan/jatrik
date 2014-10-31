package uy.edu.fing.tsi2.jatrik.android.extras;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtils {

	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat sdfDateHour = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    public static SimpleDateFormat sdfHour = new SimpleDateFormat("hh:mm");
    public static SimpleDateFormat sdfHour24 = new SimpleDateFormat("HH:mm");

    public static final long DAY_MILLISECONDS = 86400000;

	public static Date getDate(String fechaString)	{
		try	{
			if (null != fechaString) {
				return sdf.parse(fechaString);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date getDateHour(String fechaString) {
		try {
			return sdfDateHour.parse(fechaString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getDatetoString(Date date)	{
		return sdfDateHour.format(date);
	}

	public static Date getDateWithoutTime(Date date) {
		Calendar cal = new GregorianCalendar();
		
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
    }

    public static Date removeHour(Date date) {
        Date result = removeHour(getCalendar(date)).getTime();
        return result;
    }

    public static Calendar removeHour(Calendar calendar) {
        Calendar result = getCalendar(calendar.getTime());
        result.set(Calendar.HOUR_OF_DAY, 0);
        result.set(Calendar.MINUTE, 0);
        result.set(Calendar.SECOND, 0);
        result.set(Calendar.MILLISECOND, 0);
        return result;
    }


    public static Date removeSeconds(Date date) {
        Date result = removeSeconds(getCalendar(date)).getTime();
        return result;
    }


    public static Calendar removeSeconds(Calendar calendar) {
        Calendar result = getCalendar(calendar.getTime());
        result.set(Calendar.SECOND, 0);
        result.set(Calendar.MILLISECOND, 0);
        return result;
    }

    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTime(date);

        return calendar;
    }
    public static int getDaysBetween(Date d1, Date d2) {
        sdf.setLenient(false);
        sdfDateHour.setLenient(false);
        sdfHour.setLenient(false);
        sdfHour24.setLenient(false);

        long millis1 = d1.getTime();
        long millis2 = d2.getTime();
        long millis = millis2 - millis1;
        int dias = (int)(millis/DAY_MILLISECONDS);

        return dias;
    }

	public static Integer getEdad(Date fechaNacimiento) {
	    Date currentDate=new Date();
	    
	    long diff = currentDate.getTime() - fechaNacimiento.getTime();
	    Calendar c = Calendar.getInstance();
	    c.setTimeInMillis(diff);
	    int result=c.get(Calendar.YEAR);
	    return new Integer(result);
	}

	public static String getFecha(Date fechaInput,SimpleDateFormat format) {
			return format.format(fechaInput);
	}

	public static String getFecha(Date fechaInput) {
		return DateUtils.getFecha(fechaInput,DateUtils.sdf);
	}

	public static Long getHowTimeAGo(int anio){
		Date fechaInput = new Date();
		Calendar calfechaInput = getCalendar(fechaInput);
		int  anioActual = calfechaInput.get(Calendar.YEAR);
		return new Long(anioActual - anio);
	}
	
	public static Long getDifereciaMes(int mes) {
		Date fechaInput = new Date();
		Calendar calfechaInput = getCalendar(fechaInput);
		int  mesActual = calfechaInput.get(Calendar.MONTH);
		return new Long(mesActual - mes);
	}

	public static Date createDate(int year, int month, int dayOfMonth) {
		Date date = new Date();
		date = DateUtils.setYearToDate(year, date);
		date = DateUtils.setMonthToDate(month, date);
		date = DateUtils.setDayOfMonthToDate(dayOfMonth, date);
		return date;
	}

	public static Date setYearToDate(int year, Date date) {
		Calendar cal = getCalendar(date);
		cal.set(Calendar.YEAR, year);
		return cal.getTime();
	}

	public static Date setMonthToDate(int month, Date date) {
		Calendar cal = getCalendar(date);
		cal.set(Calendar.MONTH, month);
		return cal.getTime();
	}

	public static Date setDayOfMonthToDate(int dayOfMonth, Date date) {
		Calendar cal = getCalendar(date);
		cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		return cal.getTime();
	}

	public static int getYearToDate(Date date) {
		Calendar cal = getCalendar(date);
		return cal.get(Calendar.YEAR);
	}

	public static int getMonthToDate(Date date) {
		Calendar cal = getCalendar(date);
		return cal.get(Calendar.MONTH);
	}

	public static int getDayOfMonthToDate(Date date) {
		Calendar cal = getCalendar(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static Date addDays(Date date, int days) {
		Calendar cal = getCalendar(date);
		cal.add(Calendar.DATE, days);

		return cal.getTime();
	}

	public static Date addMonths(Date date, int months) {
		Calendar cal = getCalendar(date);
		cal.add(Calendar.MONTH, months);

		return cal.getTime();
	}

	public static Date addYears(Date date, int years) {
		Calendar cal = getCalendar(date);
		cal.add(Calendar.YEAR, years);

		return cal.getTime();
	}

	 public static Long calculaCantidadMeses(Date fechaDeCancelado) {
			Long result = 0L;

			Long aux = 0L;

			if (DateUtils.getHowTimeAGo(DateUtils.getYearToDate(fechaDeCancelado)) > 0) {
				aux = DateUtils.getHowTimeAGo(DateUtils
						.getYearToDate(fechaDeCancelado)) - 1L;
			} else {
				return new Long(DateUtils.getMonthToDate(new Date())
						- DateUtils.getMonthToDate(fechaDeCancelado));
			}

			result = aux * 12 + DateUtils.getMonthToDate(new Date())
					+ (12 - DateUtils.getMonthToDate(fechaDeCancelado));
			return result;

		}

}
