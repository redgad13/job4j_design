create table type(
id serial primary key,
type_name text
);

create table product(
id serial primary key,
product_name text,
type_id int references type(id),
expired_date date,
price decimal(10, 2)
);

insert into type (type_name) values ('СЫР'), ('МОЛОКО');

insert into product(product_name, type_id, expired_date, price) values
('Сыр плавленный', 1, '01/06/2023', 120), ('Сыр моцарелла', 1, '01/04/2023', 2000),
('Сыр пошехонский', 1, '21/04/2023', 20), ('Сыр виола', 1, '24/04/2023', 2000),
('мороженое', 2, '21/04/2023', 120), ('сметана', 2, '21/05/2023', 60),
('творог', 2, '21/06/2023', 160), ('кефир', 2, '21/05/2022', 100);

select product_name as Продукт from product where type_id = 1;
select product_name as Продукт from product where product_name like '%мороженое%';
select product_name as Продукт, expired_date as Просрочка from product where expired_date < CURRENT_DATE;
select product_name as Продукт from product where price = (select max(price) from product);

select type_name, count(product_name)
from type
join product on type.id = product.type_id
group by type_name;

select product_name
from product
where type_id in (select id from type where type_name in ('СЫР', 'МОЛОКО'));

select type_name, count(product_name)
from type
join product on product.type_id = type.id
group by type_name
having count(product_name) < 10;

select product_name, type.type_name
from product
join type on product.type_id = type.id;

