package us.freeenergy.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.expression.ParseException;

import com.google.gson.Gson;

public class GeneralUtility 
{
	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final Gson GSON = new Gson();
	
    public static <T> T fromJson(String data, Class<T> tClass) {
        return GSON.fromJson(data, tClass);
    }
    
    public static Date parseToDate(String myString)  {
        // String format = "yyyy-MM-dd'T'HH:mm:ss:SSS";

        Date parsedDate;
		try {
			parsedDate = (Date) sdf2.parse(myString);
			return parsedDate;
		} catch (java.text.ParseException e) {
			return null;
		}
        
    }
    
    public static Timestamp parseToTime(String myString) throws ParseException {
        // String format = "yyyy-MM-dd'T'HH:mm:ss:SSS";

        Date parsedDate;
		try {
			parsedDate = (Date) sdf1.parse(myString);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	        return timestamp;
		} catch (java.text.ParseException e) {
			return null;
		}
        
    }
}
