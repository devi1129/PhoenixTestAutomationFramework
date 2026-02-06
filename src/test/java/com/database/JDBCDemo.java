package com.database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

	public static void main(String[] args) throws SQLException {

		// Step 1: Establish the connection to the Phoenix Database

		Connection conn = DriverManager.getConnection("jdbc:mysql://64.227.160.186:3306/SR_DEV", "srdev_ro_automation",
				"Srdev@123");

		Statement statement = conn.createStatement();

		ResultSet resultSet = statement.executeQuery("select first_name,last_name, mobile_number  from tr_customer;");

		while (resultSet.next()) {

			String fname = resultSet.getString("first_name");
			String lname = resultSet.getString("last_name");
			String mobile_number = resultSet.getString("mobile_number");

			System.out.println(fname + "|" + lname + "|" + mobile_number);

		}

	}

}
