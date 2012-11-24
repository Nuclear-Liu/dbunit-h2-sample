package com.acme.dbunit;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class DataSetCreator {

	public static void main(final String[] args) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		final Connection jdbcConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/test", "user", "pwd");

		final IDatabaseConnection connection = new DatabaseConnection(
				jdbcConnection);

		final QueryDataSet dataSet = new QueryDataSet(connection);
		dataSet.addTable("Person");

		 FlatDtdDataSet.write(dataSet, new FileOutputStream("person.dtd"));
		    FlatXmlDataSet.write(dataSet, new FileOutputStream("person.xml"));

		    jdbcConnection.close();
	}
}
