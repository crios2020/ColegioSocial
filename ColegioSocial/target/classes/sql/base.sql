show databases;
drop database if exists colegio;
create database colegio;
use colegio;

drop table if exists alumnos;
drop table if exists cursos;

create table cursos(
	id int auto_increment primary key,
    titulo varchar(20) not null,
    profesor varchar(20) not null,
    dia enum('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES'),
    turno enum('MAÑANA','TARDE','NOCHE')
);

create table alumnos(
	id int auto_increment primary key,
    nombre varchar(20) not null,
    apellido varchar(20) not null,
    edad int,
    idCurso int not null
);

alter table alumnos
	add constraint PK_alumnos_idCurso
    foreign key(idCurso)
    references cursos(id);
