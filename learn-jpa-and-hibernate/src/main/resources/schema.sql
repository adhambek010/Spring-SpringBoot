create table course(
    id bigint not null ,
    name varchar(45) not null,
    author varchar(45) not null,
    primary key (id)
);
insert into course(id,name, author)
values (1, 'Learn SpringBoot', 'Naveen Reddy');
select * from course;