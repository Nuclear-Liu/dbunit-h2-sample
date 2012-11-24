package com.acme.dbunit;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.oracle.Oracle10DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;

public class AbstractIntegrationTest {

	protected DataSource dataSource;

	@Before
	public void init() throws Exception {
		// insert data into db
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
	}

	@After
	public void after() throws Exception {
		// insert data into db
		DatabaseOperation.DELETE_ALL.execute(getConnection(), getDataSet());
	}

	private IDatabaseConnection getConnection() throws Exception {
		// get connection
		final Connection con = dataSource.getConnection();
		final DatabaseMetaData databaseMetaData = con.getMetaData();
		// oracle schema name is the user name
		final IDatabaseConnection connection = new DatabaseConnection(con,
				databaseMetaData.getUserName().toUpperCase());
		final DatabaseConfig config = connection.getConfig();
		// oracle 10g
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
				new Oracle10DataTypeFactory());
		// receycle bin
		config.setProperty(
				DatabaseConfig.FEATURE_SKIP_ORACLE_RECYCLEBIN_TABLES,
				Boolean.TRUE);
		return connection;
	}

	private IDataSet getDataSet() throws Exception {
		// get insert data
		final File file = new File("src/test/resources/dataset.xml");

		return new FlatXmlDataSetBuilder().build(file);
	}

}
