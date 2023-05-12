CREATE TABLE customers(
id serial PRIMARY KEY,
first_name text,
last_name text,
age int,
country text
);

INSERT INTO customers (first_name, last_name, age, country) VALUES ('Ivan', 'Petrov', 18, 'Russia');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Sergei', 'Ivanov', 19, 'USA');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Petr', 'Yan', 18, 'Thailand');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Vadim', 'Kuzin', 35, 'Russia');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Oleg', 'Taktarov', 45, 'Brazil');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Mikhail', 'Prishvin', 90, 'USSR');

SELECT * FROM customers WHERE age = (SELECT min(age) FROM customers);

CREATE TABLE orders(
id serial PRIMARY KEY,
amount int,
customer_id int references customers(id)
);

INSERT INTO orders (amount, customer_id) VALUES (10, 1);
INSERT INTO orders (amount, customer_id) VALUES (20, 1);
INSERT INTO orders (amount, customer_id) VALUES (30, 2);
INSERT INTO orders (amount, customer_id) VALUES (40, 3);
INSERT INTO orders (amount, customer_id) VALUES (50, 4);
INSERT INTO orders (amount, customer_id) VALUES (60, 5);
INSERT INTO orders (amount, customer_id) VALUES (70, 6);
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Yan', 'Poslednikh', 50, 'Latvia');

SELECT * FROM orders;

SELECT first_name Имя, last_name Фамилия
FROM customers c LEFT JOIN orders o on o.customer_id = c.id
WHERE c.id NOT IN (SELECT orders.customer_id FROM orders);
