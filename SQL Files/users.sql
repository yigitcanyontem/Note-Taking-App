CREATE TABLE users (
    email varchar(255) NOT NULL PRIMARY KEY ,
    password varchar(255) NOT NULL,
    resetcode varchar(255) NOT NULL
);