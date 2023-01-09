create table if not exists user_info (
   user_id int auto_increment primary key,
   user_name varchar(64) not null unique,
   password varchar(64) not null,
   email varchar(64) not null,
   nick_name varchar(64) not null,
   is_admin int default(0),
   score int default(0)
);

create table if not exists problems (
   problem_id int auto_increment primary key,
   cur_date varchar(64) not null unique,
   problem varchar(64) not null,
   answer varchar(64) not null
);

create table if not exists records (
   record_id int auto_increment primary key,
   cur_date varchar(64) not null,
   user_id int not null,
   problem_id int not null
);

merge into user_info (user_id,user_name,password,email,nick_name) values (1,'test','ac9f51135e87e3405a4b069239db707c','test@gmail.com','测试账户');
merge into user_info (user_id,user_name,password,email,nick_name,is_admin) values (2,'admin','60832ad22ebaf10344bd30c2838e5551','admin@gmail.com','管理员账户',1);
alter table user_info alter column user_id restart with (select max(user_id) from user_info) + 1;
