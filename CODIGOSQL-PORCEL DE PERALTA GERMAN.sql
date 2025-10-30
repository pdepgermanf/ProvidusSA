
DROP DATABASE IF EXISTS ProvidusSA;
CREATE DATABASE ProvidusSA;
USE ProvidusSA;
-- creacion de tablas
create table INFORME(id_informe int primary key auto_increment ,tipo varchar(50), titulo varchar(50),fecha date );


create table COBRADOR(id_cobrador int primary key auto_increment, nombre varchar(50), apellido varchar(50), telefono varchar(50));

create table CLIENTE(id_cliente int primary key auto_increment, nombre varchar(50), apellido varchar(50), localidad varchar(50), telefono varchar(50), id_cobrador int, foreign key(id_cobrador) references COBRADOR(id_cobrador) );

create table MENSAJE(id_mensaje int primary key auto_increment,motivo varchar(50),contenido varchar(255),id_cobrador int, foreign key(id_cobrador) references COBRADOR(id_cobrador));

create table PLAN(id_plan int primary key, importe_plan double,id_cliente int, foreign key(id_cliente) references CLIENTE(id_cliente));

create table PAGO(numero_transaccion varchar(100) primary key, tipo_pago varchar(50),nom_banco varchar(50), importe_pago double, fecha_transaccion date  );

-- Toma la pk de la entidad fuerte
create table CUOTA(id_plan int, num_cuota int, importe_cuota double, estado varchar(50), numero_transaccion varchar(100), primary key(id_plan,num_cuota), foreign key (id_plan) references PLAN(id_plan), foreign key (numero_transaccion) references PAGO(numero_transaccion));

create table DETALLE_INFORME(id_informe int, id_plan int, num_cuota int, observacion varchar(50), primary key(id_informe,id_plan,num_cuota), foreign key(id_informe) references INFORME(id_informe),foreign key(id_plan,num_cuota) references CUOTA(id_plan,num_cuota) );

-- llenado de las tablas

insert into COBRADOR(nombre,apellido,telefono) values ("manuela", "Antonutti", "5464852364");
insert into COBRADOR(nombre,apellido,telefono) values ("Ana", "Diaz", "548845125");
SELECT * FROM COBRADOR;

insert into CLIENTE(nombre,apellido,localidad,telefono,id_cobrador) values ("Jose", "Martinez","Cordoba","56564864654",2);
insert into CLIENTE(nombre,apellido,localidad,telefono,id_cobrador) values ("Sasha", "Martinez","Jesus Maria","56564864654",2);
SELECT * FROM CLIENTE;


insert into MENSAJE(motivo,contenido,id_cobrador) values ("recordatorio", "Realizar el pago",1);
SELECT * FROM MENSAJE;

insert into PLAN(id_plan,importe_plan,id_cliente) values (1,210000,1);
insert into PLAN(id_plan,importe_plan,id_cliente) values (2,250000,1);
insert into PLAN(id_plan,importe_plan,id_cliente) values (3,150000,2);
select * FROM PLAN;

insert into cuota(id_plan,num_cuota,importe_cuota,estado) values(3,10,150000,"IMPAGO");
insert into cuota(id_plan,num_cuota,importe_cuota,estado) values(2,1,210000,"PAGADO");

insert into PAGO(numero_transaccion,tipo_pago,nom_banco,importe_pago,fecha_transaccion) values("5644654686","Deposito","Cordoba",210000,"2025-06-25");
update CUOTA set numero_transaccion="5644654686" where id_plan=2 and num_cuota=1;

select * from CUOTA;
select * from PAGO;

insert into INFORME(tipo,titulo,fecha) values ("bajas","primer informes de bajas septiembre","2025-09-14");
insert into DETALLE_INFORME(id_informe,id_plan,num_cuota,observacion) values(1,3,10,"pago de cuota");
select * from DETALLE_INFORME;

INSERT INTO INFORME(tipo,titulo,fecha) values ("pago","primer informe","26/10/2025");









