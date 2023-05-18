	/* DELETE 'LudoFunDB' database*/
	DROP SCHEMA IF EXISTS LudoFunDB;
	/* DELETE USER 'spq' AT LOCAL SERVER*/
	DROP USER IF EXISTS 'gorka'@'localhost';

	/* CREATE 'LudoFunDB' DATABASE */
	CREATE SCHEMA LudoFunDB;
	/* CREATE THE USER 'gorka' AT LOCAL SERVER WITH PASSWORD 'fork' */
	CREATE USER IF NOT EXISTS 'gorka'@'localhost' IDENTIFIED BY 'fork';

	GRANT ALL ON LudoFunDB.* TO 'gorka'@'localhost';