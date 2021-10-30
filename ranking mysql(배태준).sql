drop database if exists boarddb;
create database boarddb;
use boarddb;
drop table if exists board;
create table board(
	 id int auto_increment primary key, 
     Usrname varchar(20),
     Record varchar(20),
     ranking int(5));

      
desc board;
select*from board;