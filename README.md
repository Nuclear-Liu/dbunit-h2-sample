# README #

Using [DbUnit](http://www.dbunit.org/) with [Junit 4](http://www.junit.org/) style annotations, testing a [H2](http://www.h2database.com/html/main.html) in memory database in Oracle compatibility mode.

And just because I like to use buzzwords and whatnot a little [JDBI](http://www.jdbi.org/) is sprinkled in.

## Usage ##

### Prerequisites ###

1. Extract DDL to create original database
2. Extract dataset from origin Oracle database using DbUni
3. Using Maven's [exec plugin](http://mojo.codehaus.org/exec-maven-plugin/), start h2 database, create the schema using the extracted ddl and fill it with the dataset

### Tests ###

Per test run one scenario.

- Check for deleted data manually
- Check for changed data manually
- New data can be checked by using DbUnit's delete functionality, removing all data defined in a dataset from a table, leaving only the newly inserted database

After each test clean the instance dataset, and insert a fresh dataset. Inserting a fresh dataset can be done using DbUnit, or h2 cvsread method.

## Problems ##

### h2 ###

- Sequences. It doesn't support Oracle's `nomaxvalue`, `nocycle` flag.
- Constraints. It doesn't support `using index` when adding constraints.

### jdbi ###

Doesn't cope well with intermixed sql commands and comments

	create table attributes (
		id number not null,				-- attribut id
		type varchar2(4) not null,		-- type
		"comment" varchar2(255) null	-- comment
	);

## Resources ##

- [DbUnit with JUnit 4.x and Spring for testing Oracle DB Application](http://ralf.schaeftlein.de/2009/01/05/dbunit-with-junit-4x-and-spring-for-testing-oracle-db-application/)
- [DbUnit, Getting Started](http://dbunit.sourceforge.net/howto.html)
