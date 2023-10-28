create table if not exists emails (
   email_id int auto_increment primary key,
   email varchar(64) not null
);

create table if not exists messages (
   message_id int auto_increment primary key,
   email varchar(64) not null,
   name varchar(64) not null,
   time varchar(64) not null,
   message varchar(10064) not null
);

create table if not exists posts (
   post_id int auto_increment primary key,
   date varchar(64) not null,
   category varchar(64) not null,
   img varchar(64) not null
);


merge into posts (post_id,date, category, img) values (1,'Aug 23','Lifestyle','assets/images/blog/01.jpg');
alter table posts alter column post_id restart with (select max(post_id) from posts) + 1;



