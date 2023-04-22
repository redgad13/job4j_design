create table users(
id serial primary key,
user_name varchar(15)
);

create table role(
id serial primary key,
role varchar(15),
user_id int references users(id)
);

create table rule(
id serial primary key,
rule varchar(15)
);

create table item(
id serial primary key,
item varchar(15),
user_id int references users(id),
category_id int references category(id),
state_id int references state(id)
);

create table comment(
id serial primary key,
comment varchar(15),
item_id int references item(id)
);

create table attch(
id serial primary key,
attch varchar(15),
item_id int references item(id)
);

create table state(
id serial primary key,
state varchar(15)
);

create table category(
id serial primary key,
category varchar(15)
);

create table role_rule(
id serial primary key,
role_id int references role(id),
rule_id int references rule(id)
);
