CREATE TABLE products (
    id serial PRIMARY KEY,
    name varchar(50),
    producer varchar(50),
    count integer DEFAULT 0,
    price integer
);

create or replace function discount()
    returns trigger as
$$
    begin
        update products for each row
        set price = price - price * 0.2
        where count <= 5 and id = new.id;
        return new;
    end;
$$
LANGUAGE 'plpgsql';

create trigger discount_trigger
    before insert
    on products
    for each row
    execute procedure discount();


INSERT INTO products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
INSERT INTO products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

SELECT * FROM products;

ALTER table products disable trigger discount_trigger;

INSERT INTO products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

SELECT * FROM products;

create or replace function tax()
    returns trigger as
$$
    begin
        update products
        set price = price - price * 0.2
        where id in (select id from inserted) and count <= 5;
        return new;
    end;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
	select * from products;

create table history_of_price (
    id serial primary key,
    history_name varchar(50),
    price integer,
    date timestamp
);

	create or replace function input()
    returns trigger as
$$
    begin
        insert into history_of_price(history_name, price, date)
        values (new.name, new.price, CURRENT_TIMESTAMP);
        return new;
    end
$$ language 'plpgsql';

CREATE trigger input_trigger
after insert on products
for each row
execute procedure input();