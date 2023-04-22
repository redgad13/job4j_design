create table fauna(
id serial primary key,
fauna_name text,
avg_age int,
discovery_date date
);

insert into fauna(fauna_name, avg_age, discovery_date) values ('rabbit', 10, '01.02.1700');
insert into fauna(fauna_name, avg_age, discovery_date) values ('fish', 30, '25/12/1800');
insert into fauna(fauna_name, avg_age, discovery_date) values ('turtle', 15000, '31/08/900');
insert into fauna(fauna_name, avg_age, discovery_date) values ('dinosaur', 100, null);
insert into fauna(fauna_name, avg_age, discovery_date) values ('new form', 1, '01/01/2023');
insert into fauna(fauna_name, avg_age, discovery_date) values ('gold fish', 45, '15/03/1951');

select * from fauna;
select * from fauna where fauna_name = 'fish';
select * from fauna where fauna_name like '%fish';
select * from fauna where avg_age > 10000 and < 20000;
select * from fauna where avg_age < 20000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01/01/1950';



