create table departments(
id serial primary key,
dep_name text
);

create table employees(
id serial primary key,
emp_name text,
dep_id int references departments(id)
);

insert into departments(dep_name) values ('front office'), ('housekeeping'), ('engineers');
insert into employees(emp_name, dep_id) values ('ivan petrov', 1), ('petr ivanov', 2),
('ivan ivanov', 3), ('sergei petrov', 2), ('petr sergeev', 2),
('anna zubkova', 3), ('elena tsareva', 1);

select * from departments d left join employees e on e.dep_id = d.id;
select * from employees e left join departments d on e.dep_id = d.id;
select * from employees cross join departments;

insert into departments(dep_name) values ('marketing');
select * from departments d left join employees e on e.dep_id = d.id where e.emp_name is null;
select * from departments d left join employees e on e.dep_id = d.id;
select d.id, d.dep_name, e.id, e.emp_name, e.dep_id from employees e right join departments d on e.dep_id = d.id;

create table teens(
id serial primary key,
teen_name text,
is_male boolean
);

insert into teens(teen_name, is_male) values ('anna', false), ('irina', false),
('andrei', true), ('vadim', true), ('oleg', true);

select t1.teen_name as "Первое имя", t2.teen_name as "Второе имя" from teens t1 cross join teens t2
where t1.is_male != t2.is_male;