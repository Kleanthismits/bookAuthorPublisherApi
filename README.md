# bookAuthorPublisherApi

Simple API for CRUD operations on a database with books and their respective authors and publishers

### How to run ###
1. Open an SQL shell for PostgeSQL (or PgAdmin)
2. Create the database using following command:
	> CREATE DATABASE bookapi;
	
	The user of the database will be the default postgres with password : postgres.
	The rest of the schema (tables, sequences etc) will be created by hibernate the first time the application runs.
	
3. Download the project zip, extract to a folder and in that folder run command: 
	> ./mvnw spring-boot:run
	for building and starting the project on http://localhost:8080/
	
4. You can use the included data.sql file to populate the database with some random data.




The API was built using following technologies:
* Spring Boot
* Hibernate
* PostgeSQL
