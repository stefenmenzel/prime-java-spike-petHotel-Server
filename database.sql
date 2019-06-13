
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

	"type" VARCHAR (100),

	"color" VARCHAR (20),

	"date" VARCHAR (20),

	"checked_in" BOOLEAN DEFAULT false,

	"owner_id" INT REFERENCES "owners"

);

DROP TABLE pets;





INSERT INTO "owners"

	("name")

VALUES

	('Chris'),

	('Ally'),

	('Dane');



INSERT INTO "pets"

	("name", "type", "color", "checked_in", "owner_id")

VALUES

	('Charlie', 'Shih-tzu', 'Black', FALSE, 1),

	('Thorin', 'Rabbit', 'White', FALSE, 1),

	('Gatsby', 'Cat', 'White', FALSE, 2),

	('Juniper', 'Cat', 'Tabby', FALSE, 3);

