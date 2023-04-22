create table master(
id serial primary key,
master_name varchar(15),
pets_quantity int
);

create table pet(
id serial primary key,
nickname varchar(10)
);

create table pet_owner(
id serial primary key,
master_id int references master(id),
pet_id int references pet(id)
);

insert into master(master_name) values ('John');
insert into master(master_name) values ('Bill');
insert into master(master_name) values ('Joe');
insert into pet(nickname) values ('Zhuchka');
insert into pet(nickname) values ('Belka');
insert into pet(nickname) values ('Strelka');
insert into pet(nickname) values ('Malish');
insert into pet_owner(master_id, pet_id) values (1, 1);
insert into pet_owner(master_id, pet_id) values (2, 2);
insert into pet_owner(master_id, pet_id) values (3, 3);
insert into pet_owner(master_id, pet_id) values (3, 4);

select * from pet_owner;

create table dog_master(
id serial primary key,
dm_name varchar(15),
dogs_qnty int
);

create table dogs(
id serial primary key,
nickname varchar(12),
master_id int references dog_master(id)
);

alter table dogs rename column dog_id to master_id;

insert into dog_master(dm_name) values ('First master');
insert into dog_master(dm_name) values ('Second master');
insert into dogs (nickname, master_id) values ('First dog', 1);
insert into dogs (nickname, master_id) values ('Second dog', 1);
insert into dogs (nickname, master_id) values ('Third dog', 2);
insert into dogs (nickname, master_id) values ('Fourth dog', 2);
insert into dogs (nickname, master_id) values ('Fifth dog', 2);
select * from dogs;

create table superability(
id serial primary key,
ability_name varchar(30)
);

create table superhero(
id serial primary key,
hero_name varchar(15),
hero_ability int references superability(id) unique
);

insert into superability(ability_name) values ('i can fly');
insert into superability(ability_name) values ('i am very strong');
insert into superability(ability_name) values ('i am spider');

insert into superhero(hero_name) values ('superman');
insert into superhero(hero_name) values ('hulk');
insert into superhero(hero_name) values ('spiderman');

select * from superhero;