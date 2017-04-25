package com.frontline.dao;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.frontline.dao.util.DatabaseUtility;
import com.frontline.model.Guestbook;

public class GuestbookDaoImpl implements GuestbookDao {

	private static final String TABLE_NAME = "guestbook";

	// Insert SQL
	private static final String INSERT_MESSAGE_STMT = "INSERT INTO "
			+ TABLE_NAME + "(name, date, message) VALUES(?, ?, ?)";
	// Update SQL
	private static final String UPDATE_MESSAGE_STMT = "UPDATE " + TABLE_NAME
			+ " SET message= ? WHERE id = ?";
	// Delete SQL
	private static final String DELETE_MESSAGE_STMT = "DELETE FROM "
			+ TABLE_NAME + " WHERE id = ?";
	// Query SQL
	private static final String QUERY_MESSAGE_STMT = "SELECT id, name, date, message FROM "
			+ TABLE_NAME;
	// Query ID SQL
	private static final String QUERY_ID_STMT = "SELECT * FROM " + TABLE_NAME
			+ " WHERE id = ?";

	public void createMessage(Guestbook gb) {

		Object[] args = { gb.getName(), gb.getDate(), gb.getMessage() };
		int[] types = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

		DatabaseUtility.updateValue(INSERT_MESSAGE_STMT, args, types);
	}

	public void deleteMessage(Guestbook gb) {

		Object[] args = { gb.getId() };
		int[] types = { Types.INTEGER };

		DatabaseUtility.updateValue(DELETE_MESSAGE_STMT, args, types);
	}

	public void updateMessage(Guestbook gb) {

		Object[] args = { gb.getMessage(), gb.getId() };
		int[] types = { Types.VARCHAR, Types.INTEGER };

		DatabaseUtility.updateValue(UPDATE_MESSAGE_STMT, args, types);
	}

	public ArrayList<Guestbook> queryMessage(String condition,
			ArrayList<String> psValues) {

		int size = psValues.size();
		Object[] args = new Object[size];
		int[] types = new int[size];

		int i = 0;
		for (String s : psValues) {
			args[i] = s;
			types[i] = Types.VARCHAR;
			i++;
		}

		List<Map> al = DatabaseUtility.loadValue(
				QUERY_MESSAGE_STMT + condition, args, types);

		Guestbook gb = null;
		ArrayList<Guestbook> result = new ArrayList<Guestbook>();

		for (Map hm : al) {
			gb = new Guestbook();
			gb.setId((Integer) hm.get("id"));
			gb.setDate(((Date) hm.get("date")).toString());
			gb.setName((String) hm.get("name"));
			gb.setMessage((String) hm.get("message"));
			result.add(gb);
		}
		return result;
	}

	public Guestbook queryMessage(int id) {

		Object[] args = { id };
		int[] types = { Types.INTEGER };

		List<Map> al = DatabaseUtility.loadValue(QUERY_ID_STMT, args, types);

		Map hm = al.get(0);
		Guestbook gb = new Guestbook();
		gb.setId((Integer) hm.get("id"));
		gb.setDate(((Date) hm.get("date")).toString());
		gb.setName((String) hm.get("name"));
		gb.setMessage((String) hm.get("message"));
		return gb;
	}
}
