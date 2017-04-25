package com.frontline.dao.util;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {

	// JNDI Connection
	public static Connection getConnection() {

		DataSource ds = null;
		Connection con = null;

		try {
			Context ctx = new InitialContext();
			
			if (ctx == null) {
				throw new RuntimeException("JNDI Context could not found");
			}
			
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/frontlineDB");
			
			if (ds == null) {
				throw new RuntimeException("DataSource could not found");
			}
			
			con = ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} catch (NamingException e) {
			throw new RuntimeException("A JNDI error occured. "
					+ e.getMessage());
		}
		return con;
	}
}
