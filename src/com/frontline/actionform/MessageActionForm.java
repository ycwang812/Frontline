package com.frontline.actionform;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

import com.frontline.commom.ChangeType;
import com.frontline.commom.Constants;

public class MessageActionForm extends ValidatorForm implements Serializable {

	private static final long serialVersionUID = 3257741285158007570L;

	private int id;
	private String name;
	private String date;
	private String message;

	private String startYear;
	private String startMonth;
	private String startDay;
	private String endYear;
	private String endMonth;
	private String endDay;
	private String queryName;
	private String queryMessage;

	private String startDate = "";
	private String endDate = "";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getQueryMessage() {
		return queryMessage;
	}

	public void setQueryMessage(String queryMessage) {
		this.queryMessage = queryMessage;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	// 轉換資料及驗證資料
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		// createMessage檢查
		if (mapping.getAttribute().equalsIgnoreCase(Constants.CREATEMESSAGE)) {

			if (name == null || name.trim().length() == 0) {
				errors.add("nameErr", new ActionMessage("error.name.required"));
			} else if (name.trim().length() > 10) {
				errors.add("nameErr",
						new ActionMessage("error.name.lengthover"));
			}

			if (message == null || message.trim().length() == 0) {
				errors.add("msgErr",
						new ActionMessage("error.message.required"));
			} else if (message.trim().length() > 500) {
				errors.add("msgErr", new ActionMessage(
						"error.message.lengthover"));
			}

			// queryMessage檢查
		} else if (mapping.getAttribute().equalsIgnoreCase(
				Constants.QUERYMESSAGE)) {

			if (startYear.trim().length() == 0
					&& startMonth.trim().length() == 0
					&& startDay.trim().length() == 0) {
				// 沒有輸入開始日期
			} else if (startYear.trim().length() != 0
					&& startMonth.trim().length() != 0
					&& startDay.trim().length() != 0) {
				startDate = startYear + "-" + startMonth + "-" + startDay;
			} else {
				errors.add("dateErr", new ActionMessage("error.sy.incomplete"));
			}

			if (endYear.trim().length() == 0 & endMonth.trim().length() == 0
					& endDay.trim().length() == 0) {
				// 沒有輸入結束日期
			} else if (endYear.trim().length() != 0
					&& endMonth.trim().length() != 0
					&& endDay.trim().length() != 0) {
				endDate = endYear + "-" + endMonth + "-" + endDay;
			} else {
				errors.add("dateErr", new ActionMessage("error.ey.incomplete"));
			}

			if (startDate.length() != 0 && endDate.length() != 0) {

				Date sDate = new ChangeType().parseDate(startDate);
				Date eDate = new ChangeType().parseDate(endDate);

				// 檢查結束日期是否小於開始日期
				if (sDate.after(eDate)) {
					errors.add("dateErr", new ActionMessage("error.over"));
				}
			} else if (startDate.length() != 0) {

				Date sDate = new ChangeType().parseDate(startDate);

				// 檢查開始日期是否大於今天
				if (Calendar.getInstance().getTime().before(sDate)) {
					errors.add("dateErr", new ActionMessage("error.sy.overtd"));
				}
			} else if (endDate.length() != 0) {

				Date eDate = new ChangeType().parseDate(endDate);

				// 檢查結束日期是否小於今天
				if (Calendar.getInstance().getTime().before(eDate)) {
					errors.add("dateErr", new ActionMessage("error.ey.overtd"));
				}
			}
			// updateMessage檢查
		} else if (mapping.getAttribute().equalsIgnoreCase(
				Constants.UPATEMESSAGE)) {

			if (message == null || message.trim().length() == 0) {
				errors.add("msgErr",
						new ActionMessage("error.message.required"));
			} else if (message.trim().length() > 500) {
				errors.add("msgErr", new ActionMessage(
						"error.message.lengthover"));
			}
		}
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// createMessage reset
		if (mapping.getAttribute().equalsIgnoreCase(Constants.CREATEMESSAGE)) {
			String d = String.format("%1$tY-%1$tm-%1$td", new Date());
			setDate(d);
		}
	}
}
