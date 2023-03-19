use pms_db;

-- drop table users;

-- create table users(
-- id varchar(3),
-- username varchar(50) not null,
-- pswd varchar(16) unique not null,
-- primary key(id),
-- constraint person unique(username, pswd)
-- );

-- insert into users values("S1","Chandan Parbhakar","chadan@123");
-- insert into users values("S2","Aditya Varma","aditya@21");
-- insert into users values("S3","Aashish Chikkara","Aashish#23");
-- insert into users values("S4","Abhinav Dubey","abh!1997");
-- insert into users values("S5","Mayank Chandak","mayankC007");
-- insert into users values("S6","Mayank Chandak","mayankC007");

-- select * from users;

drop table parking_slot; 
create table parking_slot(
slot_no varchar(5),
vehicle_type int check(vehicle_type in(2,4)),
is_empty boolean,
primary key(slot_no)
);

insert into parking_slot values("A-01",2,true);
insert into parking_slot values("A-02",2,true);
insert into parking_slot values("A-03",2,true);
insert into parking_slot values("A-04",2,true);
insert into parking_slot values("A-05",2,true);
insert into parking_slot values("A-06",2,true);
insert into parking_slot values("A-07",2,true);
insert into parking_slot values("A-08",2,true);
insert into parking_slot values("B-01",4,true);
insert into parking_slot values("B-02",4,true);
insert into parking_slot values("B-03",4,true);
insert into parking_slot values("B-04",4,true);
insert into parking_slot values("B-05",4,true);
insert into parking_slot values("B-06",4,true);
insert into parking_slot values("B-07",4,true);
insert into parking_slot values("B-08",4,true);

select * from parking_slot;
select slot_no from parking_slot where is_empty=true and vehicle_type=2;
update parking_slot set is_empty = false where slot_no in ("A-01","B-01","B-02","B-03","B-04");

-- drop table vehicle_name_code;
-- create table vehicle_name_code(
-- id varchar(4),
-- vehicle_make varchar(30)not null,
-- primary key(id)
-- );
-- 
-- alter table vehicle_name_code add unique(vehicle_make);
-- 
-- insert into vehicle_name_code values("MSD","Maruti Suzuki Dzire");
-- insert into vehicle_name_code values("MSC","Maruti Suzuki Ciaz");
-- insert into vehicle_name_code values("MSB","Maruti Suzuki Baleno");
-- insert into vehicle_name_code values("HC","Honda City");
-- insert into vehicle_name_code values("RD","Renault Duster");
-- -- insert into vehicle_name_code values("RU","Renault Duster");
-- insert into vehicle_name_code value("TVSJ","TVS Jupiter");
-- 
-- select * from vehicle_name_code;


drop table parking_history ;
create table parking_history(
id int NOT NULL AUTO_INCREMENT,
entry_date varchar(11) not null,
driver_name varchar(50) not null,
driver_ph_no bigint not null,
vehicle_type int check(vehicle_type in(2,4)),
license_plate_no varchar(10) not null,
slot_no varchar(5) not null,
entry_time varchar(8) not null,
exit_time varchar(8) default null,
primary key(id),
foreign key(slot_no) references parking_slot(slot_no)on update cascade
);

select * from parking_history;


insert into parking_history(entry_date, driver_name, driver_ph_no, vehicle_type, license_plate_no, slot_no, entry_time, exit_time) values("18-03-2023","Chandan Singh",7762435910,4,"MH31AC3069","B-01","12:16:20","14:16:29");
insert into parking_history(entry_date, driver_name, driver_ph_no, vehicle_type, license_plate_no, slot_no, entry_time, exit_time) values("18-03-2023","Monika Hamand",7762335910,4,"MH31AC4423","B-02","12:17:10",null);
insert into parking_history(entry_date, driver_name, driver_ph_no, vehicle_type, license_plate_no, slot_no, entry_time, exit_time) values("18-03-2023","Keerthi Jajula",7862435910,2,"MH40AY7845","A-01","04:16:09",null);
insert into parking_history(entry_date, driver_name, driver_ph_no, vehicle_type, license_plate_no, slot_no, entry_time, exit_time) values("18-03-2023","Kamaldeep Singh",7762035910,4,"MH40FY2022","B-03","12:10:39",null);
insert into parking_history(entry_date, driver_name, driver_ph_no, vehicle_type, license_plate_no, slot_no, entry_time, exit_time) values("18-03-2023","Harpreet Singh",7762430910,4,"MH31BQ9081","B-04","11:10:23",null);
insert into parking_history(entry_date, driver_name, driver_ph_no, vehicle_type, license_plate_no, slot_no, entry_time, exit_time) values("18-03-2023","Chandan Singh",7762435910,4,"MH31AC3069","B-04","17:10:23",null);
-- delete from parking_history where id in (6);
-- update parking_history set entry_time = "04:10" where id = 3;
-- update parking_history set exit_time = "12:54:10" where id = 3;
select driver_name, driver_ph_no, vehicle_type, license_plate_no, slot_no, entry_time from parking_history where exit_time is null; 


drop table invoice;
create table invoice(
id int NOT NULL AUTO_INCREMENT,
inv_date varchar(11) not null,
parking_id int unique,
amount_paid int not null,
payment_method varchar(5) check(payment_method in("cash","UPI")) not null,
primary key(id),
foreign key(parking_id) references parking_history(id)
);

select * from invoice;


insert into invoice(inv_date, parking_id, amount_paid, payment_method)values("18-03-2023",1,20,"cash");

-- delete from invoice where parking_id in (3);

drop table parking_area_info;
create table parking_area_info(
id varchar(3),
no_of_slots int not null,
base_price_2 int not null,
per_hr_charge_2 int not null,
base_price_4 int not null,
per_hr_charge_4 int not null,
primary key(id) 
);

select * from parking_area_info;

insert into parking_area_info values("PI1",16,10,5,20,10);
