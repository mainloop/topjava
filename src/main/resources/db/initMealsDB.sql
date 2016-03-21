DROP TABLE IF EXISTS meals;

CREATE SEQUENCE meals_seq START 1;

CREATE TABLE meals
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('meals_seq'),
  description VARCHAR NOT NULL,
  calories    INTEGER DEFAULT 500 NOT NULL,
  date        TIMESTAMP DEFAULT now(),
  user_id     INTEGER NOT NULL
);


