create table if not exists user_info (
   user_id int auto_increment primary key,
   user_name varchar(64) not null unique,
   password varchar(64) not null,
   email varchar(64) not null,
   nick_name varchar(64) not null,
   is_admin int default(0)
);

merge into user_info (user_id,user_name,password,email,nick_name) values (1,'test','test','test@gmail.com','测试账户');
merge into user_info (user_id,user_name,password,email,nick_name,is_admin) values (2,'admin','admin','admin@gmail.com','管理员账户',1);
alter table user_info alter column user_id restart with (select max(user_id) from user_info) + 1;
