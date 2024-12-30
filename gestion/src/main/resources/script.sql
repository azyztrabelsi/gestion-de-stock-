create database gestion;
use gestion;

create table article(
id int auto_increment primary key,
CodeArticle varchar(20) not null,
Libelle varchar(100) not null,
Qte int not null,
PrixHT DOUBLE not null
);

insert into article(CodeArticle,Libelle,Qte,PrixHT) values ("aziz","trabelsi",12,12);