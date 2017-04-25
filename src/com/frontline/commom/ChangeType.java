package com.frontline.commom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeType {

	public Date parseDate(String s) {

		Date date = null;

		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(s);
		} catch (ParseException e) {
			throw new RuntimeException("String pares Date error occured. "
					+ e.getMessage());
		}
		return date;
	}
}
