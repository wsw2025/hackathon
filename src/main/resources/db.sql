create table if not exists user_info (
   user_id int auto_increment primary key,
   user_name varchar(64) not null unique,
   password varchar(64) not null,
   email varchar(64) not null,
   nick_name varchar(64) not null,
   is_admin int default(0),
   score int default(0),
   image varchar(255) default '/images/default.png'
);

create table if not exists problems (
   problem_id int auto_increment primary key,
   title varchar(64) not null unique,
   problem varchar(10000) not null,
   answer varchar(10000) not null,
   in_put varchar(10000) not null,
   category varchar(10000) not null,
   difficulty  varchar(10000) not null
);

create table if not exists records (
   record_id int auto_increment primary key,
   cur_date varchar(64) not null,
   user_id int not null,
   problem_id int not null
);

create table if not exists submissions (
     submission_id int auto_increment primary key,
     cur_date varchar(64) not null,
     user_id int not null,
     problem_id int not null,
     title varchar(64) not null,
     code varchar(100000) not null,
     status int default(0)
  );

create table if not exists discussions (
   discussion_id int auto_increment primary key,
   cur_date varchar(64) not null,
   user_id int not null,
   nick_name int not null,
   image varchar(64)  not null,
   problem_id int not null,
   content varchar(1000) not null,
   status int default(0)
);

merge into user_info (user_id,user_name,password,email,nick_name) values (1,'test','ac9f51135e87e3405a4b069239db707c','test@gmail.com','测试账户');
merge into user_info (user_id,user_name,password,email,nick_name,is_admin) values (2,'admin','60832ad22ebaf10344bd30c2838e5551','admin@gmail.com','管理员账户',1);
merge into problems (problem_id,title,problem,answer,in_put,difficulty,category) values (1,'A+B Problem','Given two numbers a and b, output the sum of a and b.

Sample input
8 9

Sample output
17','101','55 46','Very Easy','Start Here');

alter table user_info alter column user_id restart with (select max(user_id) from user_info) + 1;
