package com.mta.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeZone {
	
	public static String formatDate(Date date) {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String time = format.format(date);
		return time;
	}

	public static Date formatDate(String date) {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date time = null;
		try {
			time = format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}

}
