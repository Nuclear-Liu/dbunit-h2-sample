package com.acme.dbunit;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;

public class StartServer {

	public static void main(final String[] args) throws Exception {
		System.out.println("Starting server");

		final DataSource dataSource = JdbcConnectionPool.create(
				"jdbc:h2:mem:test;MODE=Oracle", "username", "password");

		// add application code here

	}

}
