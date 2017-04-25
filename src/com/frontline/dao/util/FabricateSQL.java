package com.frontline.dao.util;

import java.util.ArrayList;

public class FabricateSQL {

	public String modifyCondition(String name, String startDate,
			String endDate, String message) {

		String condition = " WHERE 1 = 1";

		if (name.trim().length() != 0) {
			condition += " AND name LIKE ?";
		}
		if (startDate.trim().length() != 0 && endDate.trim().length() != 0) {
			condition += " AND date BETWEEN ?" + " AND ?";
		} else if (startDate.trim().length() != 0) {
			condition += " AND date >= ?";
		} else if (endDate.trim().length() != 0) {
			condition += " AND date <= ?";
		}
		if (message.trim().length() != 0) {
			condition += " AND message LIKE ?";
		}
		return condition;
	}

	public ArrayList<String> modifyPsValues(String name, String startDate,
			String endDate, String message) {

		ArrayList<String> psValues = new ArrayList<String>();

		if (name.trim().length() != 0) {
			psValues.add("%" + name.trim() + "%");
		}
		if (startDate.trim().length() != 0 && endDate.trim().length() != 0) {
			psValues.add(startDate);
			psValues.add(endDate);
		} else if (startDate.trim().length() != 0) {
			psValues.add(startDate);
		} else if (endDate.trim().length() != 0) {
			psValues.add(endDate);
		}
		if (message.trim().length() != 0) {
			psValues.add("%" + message.trim() + "%");
		}
		return psValues;
	}
}
