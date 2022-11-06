package com.bigcorp.companies.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcInit {

	public void launchQueries(Connection con) {
		String dropTableQuery = "drop table COMPANY IF EXISTS ";
		String createTableQuery = "create table COMPANY ("
				+ "       ID bigint not null,"
				+ "        NAME varchar(255),"
				+ "        primary key (ID) "
				+ "    )";
		String insertQuery = "insert into COMPANY(ID, NAME) VALUES (1, 'bigCorp')";
		String selectQuery = "select ID, NAME from COMPANY";
		try (Statement stmt = con.createStatement()) {
			stmt.executeUpdate(dropTableQuery);
			stmt.executeUpdate(createTableQuery);
			stmt.executeUpdate(insertQuery);
			ResultSet rs = stmt.executeQuery(selectQuery);
			while (rs.next()) {
				long id = rs.getLong("ID");
				String name = rs.getString("NAME");
				System.out.println("id : " + id + " , name : " + name);
			}
		} catch (SQLException e) {
			throw new RuntimeException("SQL Error", e);
		}
	}

	public static void main(String[] args) {
		Properties connectionProps = new Properties();
		connectionProps.put("user", "test");
		connectionProps.put("password", "test");
		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:h2:file:C:/dev/h2-data/sample-jdbc",
					connectionProps);
			JdbcInit jdbcInit = new JdbcInit();
			jdbcInit.launchQueries(connection);
		} catch (SQLException e) {
			throw new RuntimeException("Could not connect to database ", e);
		}

	}

}
