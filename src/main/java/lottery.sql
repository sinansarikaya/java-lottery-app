CREATE DATABASE lottery;

CREATE TABLE names(
	ID SERIAL,
	name VARCHAR(20)
);
CREATE TABLE results(
	ID SERIAL,
	name VARCHAR(20),
	days VARCHAR(10),
	date DATE
);

SELECT * FROM names;