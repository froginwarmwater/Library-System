create database library;

USE library
Go
CREATE TABLE administrator
(  username varchar(50) NOT NULL PRIMARY KEY,
    pwd varchar(50) NOT NULL,
);

INSERT INTO administrator
VALUES('admin','admin')