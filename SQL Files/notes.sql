CREATE TABLE notes (
    email varchar(255) NOT NULL,
    title varchar(255) NOT NULL,
    note text,
    constraint pk_notes primary key (email, title)
);