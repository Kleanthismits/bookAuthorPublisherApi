# bookAuthorPublisherApi

Simple API for CRUD operations on a database with books and their respective authors and publishers

API documentation: https://documenter.getpostman.com/view/5813131/Tz5v1ubW

### How to run ###
1. Open an SQL shell for PostgeSQL (or open PgAdmin)

2. Create the database using following command:
	> CREATE DATABASE bookapi;
	
	The user of the database will be the default postgres with password : postgres.
	The port should be: 5432
	The rest of the schema (tables, sequences etc) will be created by hibernate the first time the application runs.
	
3. Download the project zip, extract to a folder 

	
	>  in that folder run command: 
			./mvnw spring-boot:run
	
	or 
	
	> import project to your favourite IDE
	
	the application will run on http://localhost:8080/
	
> You can use the included data.sql file in folder 'db'  to populate the database with some random data.
> There is also a dump of the database along with its data in case you want to restore it.
> You can use your favourite database (you will have to change the spring datasource properties in application.yml file)



The API was built using following technologies:
* Spring Boot
* Hibernate
* PostgeSQL
