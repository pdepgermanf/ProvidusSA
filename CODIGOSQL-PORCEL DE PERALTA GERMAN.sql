
DROP DATABASE IF EXISTS ProvidusSA;
CREATE DATABASE ProvidusSA;
USE ProvidusSA;
-- creacion de tablas
create table INFORME(id_informe int primary key auto_increment ,tipo varchar(50), titulo varchar(50),fecha date );


create table COBRADOR(id_cobrador int primary key auto_increment, nombre varchar(50), apellido varchar(50), telefono varchar(50));

create table CLIENTE(id_cliente int primary key auto_increment, nombre varchar(50), apellido varchar(50), localidad varchar(50), telefono varchar(50), id_cobrador int, foreign key(id_cobrador) references COBRADOR(id_cobrador) );

create table MENSAJE(id_mensaje int primary key auto_increment,motivo varchar(50),contenido varchar(255),id_cobrador int, foreign key(id_cobrador) references COBRADOR(id_cobrador));

create table PLAN(id_plan int primary key, importe_plan double,id_cliente int, estado VARCHAR(50) DEFAULT "ACTIVO" , foreign key(id_cliente) references CLIENTE(id_cliente));

create table PAGO(numero_transaccion varchar(100) primary key, tipo_pago varchar(50),nom_banco varchar(50), importe_pago double, fecha_transaccion date  );

-- Toma la pk de la entidad fuerte
create table CUOTA(id_plan int, num_cuota int, importe_cuota double, estado varchar(50), numero_transaccion varchar(100), primary key(id_plan,num_cuota), foreign key (id_plan) references PLAN(id_plan), foreign key (numero_transaccion) references PAGO(numero_transaccion));

create table DETALLE_INFORME(id_informe int, id_plan int, num_cuota int, observacion varchar(50), primary key(id_informe,id_plan,num_cuota), foreign key(id_informe) references INFORME(id_informe),foreign key(id_plan,num_cuota) references CUOTA(id_plan,num_cuota) );

-- llenado de las tablas

insert into COBRADOR(nombre,apellido,telefono)
values ("Manuela", "Antonutti", "5464852364");

insert into COBRADOR(nombre,apellido,telefono)
values ("Ana", "Diaz", "548845125");

insert into CLIENTE(nombre,apellido,localidad,telefono,id_cobrador)
values ("Jose", "Martinez","Cordoba","56564864654",2);

insert into CLIENTE(nombre,apellido,localidad,telefono,id_cobrador)
values ("Sasha", "Martinez","Jesus Maria","56564864654",2);

insert into MENSAJE(motivo,contenido,id_cobrador)
values ("recordatorio", "Realizar el pago",1);

insert into PLAN(id_plan,importe_plan,id_cliente)
values (1,210000,1);

insert into PLAN(id_plan,importe_plan,id_cliente)
values (2,250000,1);

insert into PLAN(id_plan,importe_plan,id_cliente)
values (3,150000,2);

insert into PLAN(id_plan,importe_plan,id_cliente)
values (4,300000,2);

insert into PLAN(id_plan,importe_plan,id_cliente)
values (6,500000,1);

insert into CUOTA(id_plan,num_cuota,importe_cuota,estado)
values (2,1,15210,"PAGADO");

insert into CUOTA(id_plan,num_cuota,importe_cuota,estado)
values (1,12,150123,"PAGADO");

insert into CUOTA(id_plan,num_cuota,importe_cuota,estado)
values (3,53,150213,"PAGADO");

insert into CUOTA(id_plan,num_cuota,importe_cuota,estado)
values (6,45,1123321,"PAGADO");

insert into CUOTA(id_plan,num_cuota,importe_cuota,estado)
values (4,44,150000,"PAGADO");

insert into CUOTA(id_plan,num_cuota,importe_cuota,estado)
values (4,45,150000,"PAGADO");

insert into PAGO(numero_transaccion, tipo_pago, nom_banco, importe_pago, fecha_transaccion)
values ("TX10001", "Transferencia", "Cordoba", 15210, "2025-01-10");

insert into PAGO(numero_transaccion, tipo_pago, nom_banco, importe_pago, fecha_transaccion)
values ("TX10002", "Deposito", "Nacion", 150123, "2025-02-14");

insert into PAGO(numero_transaccion, tipo_pago, nom_banco, importe_pago, fecha_transaccion)
values ("TX10003", "Transferencia", "MacroBansud", 150213, "2025-03-20");

insert into PAGO(numero_transaccion, tipo_pago, nom_banco, importe_pago, fecha_transaccion)
values ("TX10004", "Deposito", "Cordoba", 1123321, "2025-04-05");

insert into PAGO(numero_transaccion, tipo_pago, nom_banco, importe_pago, fecha_transaccion)
values ("TX10005", "Transferencia", "Nacion", 150000, "2025-05-18");

insert into PAGO(numero_transaccion, tipo_pago, nom_banco, importe_pago, fecha_transaccion)
values ("TX10006", "Transferencia", "MacroBansud", 150000, "2025-05-18");

update CUOTA set numero_transaccion = "TX10001"
where id_plan = 2 and num_cuota = 1;

update CUOTA set numero_transaccion = "TX10002"
where id_plan = 1 and num_cuota = 12;

update CUOTA set numero_transaccion = "TX10003"
where id_plan = 3 and num_cuota = 53;

update CUOTA set numero_transaccion = "TX10004"
where id_plan = 6 and num_cuota = 45;

update CUOTA set numero_transaccion = "TX10005"
where id_plan = 4 and num_cuota = 44;

update CUOTA set numero_transaccion = "TX10006"
where id_plan = 4 and num_cuota = 45;


insert into INFORME(tipo,titulo,fecha)
values ("PAGOS","PRIMER INFORME SEPTIEMBRE","2025-09-14");

insert into INFORME(tipo,titulo,fecha)
values ("BAJAS","BAJAS DEL MES DE SEPTEIMBRE","2025-09-14");

insert into INFORME(tipo,titulo,fecha)
values ("ACTUALIZACIONES","ACTUALIZACIONES DEL MES DE SEPTIEMBRE 2025","2025-09-14");

insert into INFORME(tipo,titulo,fecha)
values ("IMPAGOS","CLIENTES IMPAGOS MES DE SEPTIEMBRE 2025","2025-09-14");


insert into DETALLE_INFORME(id_informe,id_plan,num_cuota,observacion)
values (1,2,1,"pago de cuota");

insert into DETALLE_INFORME(id_informe,id_plan,num_cuota,observacion)
values (1,1,12,"pago de cuota");

insert into DETALLE_INFORME(id_informe,id_plan,num_cuota,observacion)
values (1,3,53,"pago de cuota");

insert into DETALLE_INFORME(id_informe,id_plan,num_cuota,observacion)
values (1,6,45,"pago de cuota");

insert into DETALLE_INFORME(id_informe,id_plan,num_cuota,observacion)
values (1,4,44,"pago de cuota");

insert into DETALLE_INFORME(id_informe,id_plan,num_cuota,observacion)
values (1,4,45,"pago de cuota");



SELECT * FROM CLIENTE;

SELECT * FROM DETALLE_INFORME;

SELECT nombre,apellido,localidad,di.id_plan,cu.num_cuota,importe_cuota,tipo_pago,nom_banco,fecha_transaccion,importe_pago,pa.numero_transaccion
FROM DETALLE_INFORME di
JOIN CUOTA cu ON di.id_plan=cu.id_plan AND di.num_cuota=cu.num_cuota
JOIN PAGO pa ON cu.numero_transaccion=pa.numero_transaccion
JOIN PLAN pl ON di.id_plan=pl.id_plan
JOIN CLIENTE cli ON cli.id_cliente=pl.id_cliente
WHERE id_informe=1;

SELECT nombre,apellido,localidad,di.id_plan,observacion FROM DETALLE_INFORME di JOIN CUOTA cu ON di.id_plan=cu.id_plan AND di.num_cuota=cu.num_cuota JOIN PLAN pl ON di.id_plan=pl.id_plan JOIN CLIENTE cli ON cli.id_cliente=pl.id_cliente WHERE id_informe=1;
