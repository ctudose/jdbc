#Java Project Accessing Databases through JDBC in Java

## Short Description

This is a sample project to test how Java JDBC works. For this project, you'll be working with an H2 database having one COUNTRY table. You will connect to the database, execute SELECT statements, execute INSERT, UPDATE and DELETE statements against the database and check the expected results. 

#Task 1
Insert a list of 12 countries to the COUNTRY table in the H2 database.
Objectives:
Create code to properly manage JDBC database connections.
Employ try/catch/finally blocks to properly dispose of connections after use.
Create code to execute an INSERT statement against a database.
Test name:
testInsertCountries

#Task 2
Select the list of countries having names starting with A.
Objectives:
Create code to properly manage JDBC database connections.
Employ try/catch/finally blocks to properly dispose of connections after use.
Create Code to read data from a database using JDBC.
Create code to execute a SELECT statement in JDBC, including decoding the results into objects.
Test name:
testSelectCountriesStartingWithA

#Task3
Execute an SQL query containing a syntax error.
Objectives:
Create code to properly manage JDBC database connections.
Employ try/catch/finally blocks to properly dispose of connections after use.
Demonstrate how to handle SQLExceptions that may occur when running a statement against a database.
Test name:
testWrongSQL

#Task4
Update a country name
Objectives:
Create code to properly manage JDBC database connections.
Employ try/catch/finally blocks to properly dispose of connections after use.
Create code to execute an UPDATE statement against a database. Show how to obtain the number of rows affected.
Test name:
testUpdateCountry

#Task5
Delete countries having names starting with A.
Objectives:
Create code to properly manage JDBC database connections.
Employ try/catch/finally blocks to properly dispose of connections after use.
Create code to execute a DELETE statement against a database. Show how to obtain the number of rows affected.
Test name:
testDeleteCountries