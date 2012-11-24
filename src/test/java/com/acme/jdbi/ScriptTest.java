package com.acme.jdbi;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Script;

public class ScriptTest {

	@Test
	public void test() {

		final DataSource dataSource = JdbcConnectionPool.create(
				"jdbc:h2:mem:test;MODE=Oracle", "username", "password");
		final DBI dbi = new DBI(dataSource);
		final Handle h = dbi.open();

		final Script ddl = h.createScript("ddl.sql");
		ddl.execute();

		h.close();
	}

}
