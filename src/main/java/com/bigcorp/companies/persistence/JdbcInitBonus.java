package com.bigcorp.companies.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcInitBonus {

	public void launchQueries(Connection con) {
		String dropTableQuery = "drop table COMPANY IF EXISTS ";
		String createTableQuery = "create table COMPANY ("
				+ "       ID bigint not null,"
				+ "        NAME varchar(255),"
				+ "		   AGE bigint,"
				+ "        primary key (ID) "
				+ "    )";
		String insertQuery = "insert into COMPANY(ID, NAME, AGE) VALUES (1, 'bigCorp', 12)";
		String insertQuery2 = "insert into COMPANY(ID, NAME, AGE) VALUES (2, 'bigCorp2', 24)";
		String selectQuery = "select ID, NAME, AGE from COMPANY";
		try (Statement stmt = con.createStatement()) {
			stmt.executeUpdate(dropTableQuery);
			stmt.executeUpdate(createTableQuery);
			stmt.executeUpdate(insertQuery);
			stmt.executeUpdate(insertQuery2);
			ResultSet rs = stmt.executeQuery(selectQuery);
			while (rs.next()) {
				long id = rs.getLong("ID");
				String name = rs.getString("NAME");
				long age = rs.getLong("AGE");
				System.out.println("id : " + id + " , name : " + name + " , age : " + age);
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
			JdbcInitBonus jdbcInit = new JdbcInitBonus();
			jdbcInit.launchQueries(connection);
		} catch (SQLException e) {
			throw new RuntimeException("Could not connect to database ", e);
		}

	}

}
