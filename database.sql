-- SQL Required to get set up with the project
-- database config is in the application.properties
-- db default name is book_store
CREATE TABLE "owners"
(
	"id" SERIAL PRIMARY KEY,
	"name" VARCHAR(100)
);

CREATE TABLE "pets"
(
	"id" SERIAL PRIMARY KEY,
	"name" VARCHAR (100),
	"color" VARCHAR (20),
	"date" DATE,
	"checked_in" BOOLEAN,
	"owner_id" INT
);



