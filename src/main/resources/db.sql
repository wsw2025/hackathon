create table if not exists user_info (
   user_id identity NOT NULL UNIQUE primary key,
   user_name varchar(64) not null unique,
   password varchar(64) not null,
   email varchar(64) not null,
   nick_name varchar(64) not null
);
