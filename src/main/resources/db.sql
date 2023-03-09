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
   difficulty  varchar(10000) not null,
   time varchar(24) default '2023-02-25 03:07:09.530',
   contest_id int default(0)
);

create table if not exists records (
   record_id int auto_increment primary key,
   cur_date varchar(64) not null,
   user_id int not null,
   problem_id int not null
);

create table if not exists contest_records (
   contest_records_id int auto_increment primary key,
   user_id int not null,
   user_name varchar(64) not null,
   contest_id int default(0),
   score int default(0),
   start numeric default (-1),
   stop numeric default (-1),
   image varchar(255) default '/images/default.png'

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
   nick_name varchar(64) not null,
   image varchar(64)  not null,
   problem_id int not null,
   content varchar(1000),
   status int default(0)
);

create table if not exists learns (
   learn_id int auto_increment primary key,
   title varchar(64) not null,
   cur_date varchar(64) not null,
   user_id int not null,
   nick_name varchar(64) not null,
   image varchar(64)  not null,
   content varchar(1000),
   difficulty varchar(64),
   category varchar(64)
);

create table if not exists contests (
   contest_id int auto_increment primary key,
   title varchar(64) not null,
   start varchar(64) not null,
   stop varchar(64) not null,
   content varchar(1000000) not null,
   duration double not null
);


merge into user_info (user_id,user_name,password,email,nick_name) values (1,'test','ac9f51135e87e3405a4b069239db707c','test@gmail.com','测试账户');
merge into user_info (user_id,user_name,password,email,nick_name,is_admin) values (2,'admin','60832ad22ebaf10344bd30c2838e5551','admin@gmail.com','管理员账户',1);
merge into problems (problem_id,title,problem,answer,in_put,difficulty,category) values (1,'A+B Problem','Given two numbers a and b, output the sum of a and b.

Sample input
8 9

Sample output
17','101','55 46','Very Easy','Start Here');


merge into discussions (discussion_id,cur_date,user_id,nick_name,image,problem_id,content,status) values (1,'2023.02.28.16.42.43',2,'admin','/images/2.png',1,'nothing yet...',0);
merge into learns (learn_id,title,cur_date,user_id,nick_name,image,content,difficulty,category) values (1,'Begin Here','2023.02.28.16.42.43',2,'admin','/images/2.png','Hi','Begin Here','Help');
merge into contests (contest_id,title,start,stop,duration,content) values (1,'System Test','2020-03-01T19:32','2040-03-01T19:32',3,'This contest is for system test.');

alter table user_info alter column user_id restart with (select max(user_id) from user_info) + 1;
alter table discussions alter column discussion_id restart with (select max(discussion_id) from discussions) + 1;
alter table problems alter column problem_id restart with (select max(problem_id) from problems) + 1;
alter table learns alter column learn_id restart with (select max(learn_id) from learns) + 1;
alter table contests alter column contest_id restart with (select max(contest_id) from contests) + 1;
