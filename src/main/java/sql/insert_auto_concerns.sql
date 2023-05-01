CREATE TABLE concern(
id serial PRIMARY KEY,
concern_name varchar(15)
);

CREATE TABLE models(
id serial PRIMARY KEY,
model varchar(15),
concern_id int REFERENCES concern(id)
);

CREATE TABLE buyers(
id serial PRIMARY KEY,
buyer_name varchar(15)
);

CREATE TABLE car_owners(
id serial PRIMARY KEY,
buyer_id int REFERENCES buyers(id),
model_id int REFERENCES models(id)
);

INSERT INTO concern(concern_name) VALUES ('Toyota'), ('BMW'), ('Nissan'), ('VW'), ('GMC');
INSERT INTO models(model, concern_id) VALUES ('Corolla', 1), ('Auris', 1),
('X5', 2), ('X6', 2), ('X7', 2), ('X8', 2),
('Micra', 3), ('Patrol', 3),
('Polo', 4),
(NULL , 5);
INSERT INTO buyers(buyer_name) VALUES ('Sergei'), ('Andrei'), ('Ivan'), ('Alla'), ('Olga'), ('Petr');
INSERT INTO car_owners (buyer_id, model_id) VALUES (1, 2), (2, 3), (3, 5), (4, 7), (5, 8), (6,9);

CREATE VIEW bmw_owners AS
SELECT b.buyer_name, c.concern_name, m.model
FROM car_owners co
JOIN models m ON co.model_id = m.id
JOIN buyers b ON co.buyer_id = b.id
JOIN concern c ON m.concern_id = c.id
GROUP BY (b.buyer_name, c.concern_name, m.model) HAVING c.concern_name LIKE 'BMW';

SELECT * FROM bmw_owners;

SELECT b.buyer_name, c.concern_name, m.model
FROM car_owners co
JOIN buyers b ON co.buyer_id = b.id
JOIN models m ON co.model_id = m.id
JOIN concern c ON m.concern_id = c.id;
INSERT INTO models (model, concern_id) VALUES ('Polo', 5);
INSERT INTO car_owners  (buyer_id, model_id) VALUES (6, 10);

CREATE VIEW polo_owners AS
SELECT b.buyer_name, c.concern_name, m.model
FROM car_owners co
JOIN buyers b ON b.id = co.buyer_id
JOIN models m ON m.id = co.model_id
JOIN concern c ON c.id = m.concern_id
GROUP BY (b.buyer_name, c.concern_name, m.model) HAVING m.model LIKE 'Polo';

SELECT * FROM polo_owners;