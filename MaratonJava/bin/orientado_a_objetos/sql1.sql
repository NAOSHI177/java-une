----facturacion
create table facturacion (numero_factura varchar,fecha_factura date,importe numeric);
create table facturacion_historico (check (fecha_factura <'2018-01-01')) inherits (facturacion);
create table facturacion_2018 (check (fecha_factura between '2018-01-01' and '2018-12-31')) 
inherits (facturacion);

create table facturacion_2018_s1 (check (fecha_factura between '2018-01-01' and '2018-06-30')) 
inherits (facturacion_2018,facturacion);

create table facturacion_2018_s2 (check (fecha_factura between '2018-07-01' and '2018-12-31')) 
inherits (facturacion_2018,facturacion);


create rule fact_insert_historico_rule 
as on insert to facturacion
where fecha_factura <'2018-01-01'
do instead insert into facturacion_historico
values(New.*)


create rule fact_insert_2018_rule 
as on insert to facturacion
where fecha_factura between '2018-01-01' and '2018-12-31'
do instead insert into facturacion_2018
values(New.*)



CREATE OR REPLACE FUNCTION facturacion_insert_proc()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    volatile
	AS $BODY$
BEGIN
	if New.fecha_factura <2018 then
	   insert into facturacion_historico VALUES (NEW.*);
	 end if;
     RETURN null;
END ;
$BODY$;

create trigger fact_insert 
before insert on facturacion
for each row execute procedure  facturacion_insert_proc();