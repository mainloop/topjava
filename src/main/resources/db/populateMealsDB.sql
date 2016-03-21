DELETE FROM meals;

ALTER SEQUENCE meals_seq RESTART WITH 1;

INSERT INTO meals( description, calories, user_id) VALUES
  ('user breakfast', 250, 100000),
  ('user lunch', 550, 100000),
  ('user diner', 650, 100000),
  ('admin breakfast', 350, 100001),
  ('admin lunch', 450, 100001),
  ('admin diner', 550, 100001);