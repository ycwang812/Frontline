package com.frontline.dao.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DatabaseUtility {

	// DML四大指令CRUD(Retrieve, Create, Update, Delete)

	// Create, Update, Delete
	public static int updateValue(String sql, Object[] args, int[] types) {

		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = DBConnection.getConnection();

			// 印出SQL指令
			System.out.println("SQL指令：" + sql);

			// Use the Connection to create a Statement object
			stmt = con.prepareStatement(sql);

			int j = 0;
			for (int i = 0; i < args.length; i++) {

				Object arg = args[i];
				int type = types[i];
				j++;

				switch (type) {
				case Types.VARCHAR:
					stmt.setString(j, (String) arg);
					break;
				case Types.INTEGER:
					stmt.setInt(j, (Integer) arg);
					break;
				case Types.DATE:
					stmt.setDate(j, (Date) arg);
					break;
				default:
					stmt.setString(j, (String) arg);
					break;
				}
			}

			// 印出條件
			System.out.println("DML條件：" + stmt);

			// Execute query using Statement, receive the ResultSet
			int resultNo = stmt.executeUpdate();

			// 印出代碼
			System.out.println("STMT代碼：" + resultNo);

			return resultNo;

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	// Retrieve
	public static List<Map> loadValue(String sql, Object[] args, int[] types) {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = DBConnection.getConnection();

			// 印出SQL指令
			System.out.println("SQL指令：" + sql);

			// Use the Connection to create a Statement object
			stmt = con.prepareStatement(sql);

			int j = 0;
			for (int i = 0; i < args.length; i++) {

				Object arg = args[i];
				int type = types[i];
				j++;

				switch (type) {
				case Types.VARCHAR:
					stmt.setString(j, (String) arg);
					break;
				case Types.INTEGER:
					stmt.setInt(j, (Integer) arg);
					break;
				case Types.DATE:
					stmt.setDate(j, (Date) arg);
					break;
				default:
					stmt.setString(j, (String) arg);
					break;
				}
			}

			// 印出條件
			System.out.println("DML條件：" + stmt);

			// Execute query using Statement, receive the ResultSet
			rs = stmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			List<Map> al = new ArrayList<Map>();

			while (rs.next()) {

				Map hm = new HashMap();

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					switch (rsmd.getColumnType(i)) {
					case Types.VARCHAR:
						hm.put(rsmd.getColumnName(i), rs.getString(i));
						break;
					case Types.INTEGER:
						hm.put(rsmd.getColumnName(i), rs.getInt(i));
						break;
					case Types.DATE:
						hm.put(rsmd.getColumnName(i), rs.getDate(i));
						break;
					default:
						hm.put(rsmd.getColumnName(i), rs.getString(i));
						break;
					}
				}
				al.add(hm);
			}
			return al;
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
}
