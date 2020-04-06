1. application.properties file contains configurations, regarding the connection with database, change or configure accordingly.
2. Create database rardb2 and make it default.
3. In order to let the db to store larger texts, execute the following statement:

create table review 
(
text varchar(2000),

id int,

movieId varchar(50),

userName varchar(100),

primary key(id)
);