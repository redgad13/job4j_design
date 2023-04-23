create table devices(
    id serial primary key,
    device_name varchar(255),
    price float
);

create table people(
    id serial primary key,
    person_name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);
insert into people(person_name) values ('ivan'), ('vadim'), ('petr'), ('anna');
insert into devices(device_name, price) values ('notebook', 10000), ('cell phone', 3000), ('TV', 8000),
('iPod', 2000), ('iPad', 4000), ('earphones', 300), ('tablet', 3500), ('smart watch', 2000);
insert into devices_people(people_id, device_id) values (1, 1), (1, 2), (2, 3), (2, 4),
 (3, 5), (3, 6), (4, 7), (4, 8);

select device_name as Имя, price as цена from devices;
select avg(price) as "Средняя цена" from devices;

select p.person_name as name, avg(d.price) as "Average price"
from devices_people as dp
join devices as d on dp.device_id = d.id
join people as p on dp.people_id = p.id
group by p.person_name;

select p.person_name as "Person name", avg(d.price) as "Av Device price"
from devices_people as dp
join people as p on p.id = dp.people_id
join devices as d on d.id = dp.device_id
group by p.person_name
having avg(d.price) > 5000;