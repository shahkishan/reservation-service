
CREATE TABLE IF NOT EXISTS slots(id integer PRIMARY KEY, slot_time text);
CREATE TABLE IF NOT EXISTS  tables(id integer PRIMARY KEY, tbl_name text);
CREATE TABLE IF NOT EXISTS  reservations(id varchar(50) PRIMARY KEY, name varchar(50) not null, contact varchar(15) not null, reservation_date date, slot_id integer, table_id integer, unique (reservation_date, slot_id, table_id));

insert into slots (id, slot_time) values (1, '11AM-1PM');
insert into slots (id, slot_time) values (2, '1PM-3PM');
insert into slots (id, slot_time) values (3, '3PM-5PM');
insert into slots (id, slot_time) values (4, '5PM-7PM');

insert into tables (id, tbl_name) values (1, 'table1');
insert into tables (id, tbl_name) values (2, 'table2');
insert into tables (id, tbl_name) values (3, 'table3');
insert into tables (id, tbl_name) values (4, 'table4');
insert into tables (id, tbl_name) values (5, 'table5');
insert into tables (id, tbl_name) values (6, 'table6');
insert into tables (id, tbl_name) values (7, 'table7');
insert into tables (id, tbl_name) values (8, 'table8');
insert into tables (id, tbl_name) values (9, 'table9');
insert into tables (id, tbl_name) values (10, 'table10');
insert into tables (id, tbl_name) values (11, 'table11');
insert into tables (id, tbl_name) values (12, 'table12');
insert into tables (id, tbl_name) values (13, 'table13');
insert into tables (id, tbl_name) values (14, 'table14');
insert into tables (id, tbl_name) values (15, 'table15');
insert into tables (id, tbl_name) values (16, 'table16');
insert into tables (id, tbl_name) values (17, 'table17');
insert into tables (id, tbl_name) values (18, 'table18');
insert into tables (id, tbl_name) values (19, 'table19');
insert into tables (id, tbl_name) values (20, 'table20');

insert into reservations (id, name, contact, reservation_date, slot_id, table_id) values ('1', 'Davis', '123456', '2020-10-05', 3, 2 );
insert into reservations (id, name, contact, reservation_date, slot_id, table_id) values ('2', 'Arden', '123456', '2020-10-06', 2, 17);
insert into reservations (id, name, contact, reservation_date, slot_id, table_id) values ('3', 'Ebony', '123456', '2020-10-05', 4, 3);
insert into reservations (id, name, contact, reservation_date, slot_id, table_id) values ('4', 'Rodi', '123456', '2020-10-06', 2, 3);
insert into reservations (id, name, contact, reservation_date, slot_id, table_id) values ('5', 'Susanna', '123456', '2020-10-05', 2, 18);
insert into reservations (id, name, contact, reservation_date, slot_id, table_id) values ('6', 'Flory', '123456', '2020-10-05', 4, 16);
insert into reservations (id, name, contact, reservation_date, slot_id, table_id) values ('7', 'Randene', '123456', '2020-10-05', 4, 14);
insert into reservations (id, name, contact, reservation_date, slot_id, table_id) values ('8', 'Serge', '123456', '2020-10-05', 1, 4);
insert into reservations (id, name, contact, reservation_date, slot_id, table_id) values ('9', 'Eduardo', '123456', '2020-10-06', 4, 17);
insert into reservations (id, name, contact, reservation_date, slot_id, table_id) values ('10', 'Tamas', '123456', '2020-10-05', 4, 1);
