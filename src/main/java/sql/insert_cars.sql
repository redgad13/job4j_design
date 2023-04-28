create table car_bodies(
id serial primary key,
body_name text
);

create table car_engines(
id serial primary key,
engine_name text
);

create table car_transmissions(
id serial primary key,
transmission_name text
);

create table cars(
id serial primary key,
car_name text,
body_id int references car_bodies(id),
engine_id int references car_engines(id),
transmission_id int references car_transmissions(id)
);

insert into car_bodies(body_name) values ('first body'), ('second body'), ('third body'), ('fourth body');
insert into car_engines(engine_name) values ('first engine'), ('second engine'), ('third engine'), ('fourth engine');
insert into car_transmissions(transmission_name) values ('first transmission'), ('second transmission'), ('third transmission'), ('fourth transmission');
insert into cars(car_name, body_id, engine_id, transmission_id) values
('first car', 1, 1, 1), ('second car', 2, 2, 2), ('third car', 3, 3, 3), ('fourth car', 4, 4, 4);
insert into car_bodies(body_name) values ('sixth');
insert into car_bodies(body_name) values ('that very body');
insert into cars(car_name, body_id, engine_id, transmission_id) values
('fifth car', 5, 1, 1);

select car_name as Машина, engine_name as Двигатель, body_name as Кузов, transmission_name as Коробка
from cars c
left join car_engines ce on ce.id = c.engine_id
left join car_bodies cb on cb.id = c.body_id
left join car_transmissions ct on ct.id = c.transmission_id;

select body_name
from cars c
left join car_bodies cb on cb.id = c.body_id
where body_name LIKE 'that very body';