DROP TABLE user_roles IF EXISTS;
DROP TABLE voting_history IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE meals_history IF EXISTS;
DROP TABLE meals IF EXISTS;
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
    date_of_last_updating   TIMESTAMP  DEFAULT now()  NOT NULL,
    rating    INT DEFAULT 0,
    admin_id     INTEGER      NOT NULL
);

CREATE TABLE meals
(
    restaurant_id INTEGER NOT NULL,
    meals_key    VARCHAR(255),
    meal_price INTEGER,
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS (id) ON DELETE CASCADE
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

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE voting_history
(
    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    user_id INTEGER UNIQUE NOT NULL,
    restaurant_id INTEGER NOT NULL,
    date_of_voting TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS (id) ON DELETE CASCADE
);

INSERT INTO users (name, email, password)
VALUES ('User', 'user@gmail.com', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Admin2', 'admin2@gmail.com', 'admin2');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('ADMIN', 3);

INSERT INTO restaurants (name, address, admin_id, rating, date_of_last_updating)
VALUES ('Happy on the Roof', 'Moscow, Tverskaya st.', 2, 1, now()),
       ('Rozengrals', 'Riga', 3, 2, now()),
       ('Nyhavn 17', 'Copenhagen', 3, 3, now()),
       ('Niyama', 'Chita', 2, 4, now()),
       ('Zaryadiye', 'Moscow', 2, 5,  '2020-09-09');

INSERT INTO meals (restaurant_id, meals_key, meal_price)
VALUES (4, 'Pannacota', 100),
       (5, 'Middle-century pork', 400),
       (6, 'Smoresbrod', 450),
       (8, 'Cheburek', 150);