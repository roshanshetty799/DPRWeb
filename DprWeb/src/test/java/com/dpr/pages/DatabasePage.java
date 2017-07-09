package com.dpr.pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DatabasePage {

	private WebDriver driver;
	private Connection con;
	private Statement s;
	ResultSet rs;

	public DatabasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	private void establishDatabaseConnection() {

		// No longer valid in Java 8
		/*
		 * try { Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); } catch
		 * (ClassNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		try {
			con = DriverManager.getConnection("jdbc:sqlserver://clubdb084.devneta.local:1433;databaseName=Advantage;",
					"sa", "strong5@pw");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet executeQuery(String name) {
		establishDatabaseConnection();
		try {
			s = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Unable to create statement object");

		}

		try {
			rs = s.executeQuery(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;

	}

}
