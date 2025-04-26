CREATE SCHEMA project;
SET search_path = project, pg_catalog;


CREATE TABLE users (
                       id uuid,
                       username text,
                       email text,
                       password text,
                       PRIMARY KEY (id)
);