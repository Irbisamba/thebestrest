DROP TABLE user_authority IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE meals_history IF EXISTS;
DROP TABLE RESTAURANTS IF EXISTS;



DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 1;

CREATE TABLE users
(
    id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL,
    email            VARCHAR(255)            NOT NULL,
    password         VARCHAR(255)            NOT NULL
);

CREATE TABLE restaurants
(
    id INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL,
    address             VARCHAR(255),
    date_of_last_updating   TIMESTAMP    NOT NULL,
    rating    INT,
    admin_id     INTEGER      NOT NULL,
    meal_title VARCHAR(255),
    meal_price INT
);

CREATE TABLE meals_history
(
    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    meal_title VARCHAR(255) NOT NULL ,
    price INTEGER NOT NULL,
    restaurant_id INTEGER NOT NULL,
    date   TIMESTAMP    NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS (id) ON DELETE CASCADE
);

CREATE TABLE user_authority
(
    restaurant_id INTEGER NOT NULL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);
