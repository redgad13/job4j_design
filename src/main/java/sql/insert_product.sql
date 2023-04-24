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

create table product_with_types(
id serial primary key,
type_id int references type(id),
product_id int references product(id)
);

insert into type (type_name) values ('СЫР'), ('МОЛОКО');

insert into product(product_name, type_id, expired_date, price) values
('Сыр плавленный', 1, '01/06/2023', 120), ('Сыр моцарелла', 1, '01/04/2023', 2000),
('Сыр пошехонский', 1, '21/04/2023', 20), ('Сыр виола', 1, '24/04/2023', 2000),
('мороженое', 2, '21/04/2023', 120), ('сметана', 2, '21/05/2023', 60),
('творог', 2, '21/06/2023', 160), ('кефир', 2, '21/05/2022', 100);

select p.product_name as Продукт
from product_with_types as pwt
join product as p on p.id = pwt.product_id
join type as t on t.id = pwt.type_id
where t.type_name like 'СЫР';

select product_name from product where type_id = 1;

select product_name as Продукт from product where product_name like '%мороженое%';
select product_name as Продукт, expired_date as Просрочка from product where expired_date < CURRENT_DATE;

