package com.frontline.commom;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

public class Replacement {

	private static final String PROPFILE = "com/frontline/propertie/Replacement";

	public HashMap<String, String> readPropFile() {

		ResourceBundle rb = null;

		// 取得propertie檔案
		rb = ResourceBundle.getBundle(PROPFILE);

		// 取得propertie的指位器
		Enumeration<String> prop = rb.getKeys();

		// 取出key及value放入HashMap中
		HashMap<String, String> hm = new HashMap<String, String>();
		while (prop.hasMoreElements()) {
			String propKey = prop.nextElement();
			String propValue = rb.getString(propKey);
			hm.put(propKey, propValue);
		}
		return hm;
	}

	public String replaceMessage(String message) {

		HashMap<String, String> hm = readPropFile();

		// 將message內容依照propertie條件置換
		Set<String> hms = hm.keySet();
		for (String s : hms) {
			message = message.replaceAll(s, hm.get(s));
		}
		return message;
	}
}