CREATE TABLE products (
    id serial PRIMARY KEY,
    name varchar(50),
    producer varchar(50),
    count integer DEFAULT 0,
    price integer
);

CREATE OR replace function discount()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where count <= 5 AND id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

CREATE trigger discount_trigger
    after INSERT
    on products
    for each row
    execute procedure discount();


INSERT INTO products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
INSERT INTO products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

SELECT * FROM products;

ALTER table products disable trigger discount_trigger;

INSERT INTO products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

SELECT * FROM products;

CREATE OR replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where id = (select id from inserted) and count <= 5;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

CREATE trigger tax_trigger
    after INSERT on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
	select * from products;