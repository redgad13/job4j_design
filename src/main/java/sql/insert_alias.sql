create table producer(
id serial primary key,
title text
);

create table computer(
id serial primary key,
model text,
producer_id int references producer(id)
);

insert into producer(title) values ('apple');
insert into producer(title) values ('intel');

insert into computer(model, producer_id) values ('macintosh', 1);
insert into computer(model, producer_id) values ('pentium-celeron', 2);
insert into computer(model, producer_id) values ('super macintosh', 1);
insert into computer(model, producer_id) values ('pentium', 2);
insert into computer(model, producer_id) values ('celeron', 2);

select p.title, c.model, c.producer_id
from computer c join producer p on c.producer_id = p.id;

select p.title, c.model
from computer c join producer p on c.producer_id = p.id;

select p.title as "Название", c.model as Модель, c.producer_id as Компания
from computer c join producer p on c.producer_id = p.id;