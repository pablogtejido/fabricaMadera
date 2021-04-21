/* DELETE 'fabricaMaderaDB' database*/
DROP SCHEMA fabricaMaderaDB;
/* DELETE USER 'products_user1' AT LOCAL SERVER*/
DROP USER 'spq'@'%';

/* CREATE ''fabricaMaderaDB' DATABASE */
CREATE SCHEMA fabricaMaderaDB;
/* CREATE THE USER 'products_user1' AT LOCAL SERVER WITH PASSWORD 'password' */
CREATE USER 'spq'@'%' IDENTIFIED BY 'spq';
/* GRANT FULL ACCESS TO THE DATABASE FOR THE USER 'products_user1' AT LOCAL SERVER*/
GRANT ALL ON fabricaMaderaDB.* TO 'spq'@'%';