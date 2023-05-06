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
    begin
       if (new.count <= 5) then
       new.price = new.price - new.price * 0.2;
       end if;
       return new;
    end;
$$
LANGUAGE 'plpgsql';

CREATE trigger discount_trigger
    before INSERT
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
    begin
        update products
        set price = price + price * 0.2
        where id in (select id from inserted) and count <= 5;
        return new;
    end;
$$
LANGUAGE 'plpgsql';

CREATE trigger tax_trigger
    after INSERT on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

INSERT INTO products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
	SELECT * FROM products;

CREATE TABLE history_of_price (
    id serial PRIMARY KEY,
    history_name varchar(50),
    price integer,
    date timestamp
);

	create or replace function input()
    returns trigger as
$$
    begin
        insert into history_of_price(history_name, price, date)
        values (new.name, new.price, current_timestamp);
        return new;
    end
$$ language 'plpgsql';

create trigger input_trigger
after insert on products
for each row
execute procedure input();

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end
$$;

call insert_data('product_2', 'producer_2', 15, 32);

CREATE or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
    begin
        if u_count > 0 then
        UPDATE products set count = count - u_count where id = u_id;
        end if;
        if tax >0 then
        UPDATE products set price = price + price * tax;
        end if;
    end;
$$;

call update_data(10, 0, 1);
call insert_data('product_1', 'producer_1', 3, 50);
call insert_data('product_3', 'producer_3', 8, 115);