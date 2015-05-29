SET FOREIGN_KEY_CHECKS = 0;
drop table if exists address;
drop table if exists users;
drop table if exists event;
SET FOREIGN_KEY_CHECKS = 1;

create table address
(address_id INT NOT NULL AUTO_INCREMENT,
  street VARCHAR(60),
  street_nr VARCHAR(3),
  postcode INT(5) ,
  city varchar(20),
  PRIMARY KEY (address_id)
);



create table users (user_id INT NOT NULL AUTO_INCREMENT,
 email varchar(70) NOT NULL,
 password_ varchar(30) NOT NULL,
 first_name varchar(25)NOT NULL,
 last_name varchar(25) NOT NULL,
 address_id int,
 FOREIGN KEY (address_id) references address(address_id) on delete cascade,
 PRIMARY KEY (user_id),
 UNIQUE INDEX (email)
);

create table event (
event_id INT NOT NULL AUTO_INCREMENT,
title varchar(50) not null,
description varchar(120),
place varchar(30),
event_begin datetime NOT NULL,
event_end datetime NOT NULL,
full_day boolean,
category varchar(20),
primary key ( event_id),
user_id int,
foreign key ( user_id) references users ( user_id) on delete cascade
);