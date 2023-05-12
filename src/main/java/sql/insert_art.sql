CREATE TABLE movie(
id serial PRIMARY KEY,
movie_name text,
director text
);

CREATE TABLE book(
id serial PRIMARY KEY,
title text,
author text
);

INSERT INTO movie (movie_name, director)
VALUES ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');

INSERT INTO book (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');

SELECT movie_name as "Название фильма"
FROM movie
INTERSECT
SELECT title
from book;

SELECT title as "Не экранизированы" from book
EXCEPT
SELECT movie_name from movie;

SELECT movie_name as "Название фильма"
FROM movie
INTERSECT
SELECT title
from book
UNION ALL (SELECT title as "Не экранизированы" from book
EXCEPT
SELECT movie_name from movie);
