package com.vehicleloan.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static Date addMonths(Date date, int monthsToAdd) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, monthsToAdd);
		return calendar.getTime();
	}
}
