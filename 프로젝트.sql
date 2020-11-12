drop table pj_customer;

create table pj_customer(
id varchar2(20) primary key,
password varchar2(20) not null,
name varchar2(20) not null,
sex char(1) not null,
address varchar2(20) not null,
email varchar2(20) not null,
phoneNumber varchar2(20) not null,
joindate date not null,
del char(1));

insert into pj_customer
values('master', '1234','master','m','asd','asd@asd.com','010-1111-1111',sysdate, 'n');

drop table pj_product;

create table pj_product(
p_Company varchar2(20),
p_Name varchar2(20),
p_Price number(10),
p_Quantity number(10),
p_Regdate varchar2(20),
p_Color varchar2(20),
p_Content varchar2(500),
p_Image varchar2(20),
p_pcName varchar2(20));

drop table pj_order;

select * from pj_order;

create table pj_order(
orNumber varchar2(20) primary key,
or_date date not null,
or_totalCost number(10) not null,
or_deliveryState varchar(20) not null,
or_deliveryLocation varchar2(20) not null,
or_buyType varchar2(20) not null,
or_color varchar2(20) not null,
or_count number(10) not null,
id varchar2(20) constraint pj_order_id_fk references pj_customer
);

select * from PJ_ORDER;

drop table pj_pet;

create table pj_pet(
petNumber number(10) primary key,
pet_name varchar2(20) not null,
pet_sex char(1) not null,
pet_type varchar2(20) not null,
pet_age number(10) not null);


drop table pj_productBoard;

create TABLE pj_productBoard(
   pbLike number(10) not null,
   pbIp varchar2(10) not null,
   pbRegdate date not null,
   pbDel char(1) default 'n'
);

drop table pj_my;

create table pj_my(
    pNum varchar2(20) primary key,
    petNumber number constraint pj_my_petNumber_fk references pj_pet,
    id varchar2(20) constraint pj_my_id_fk references pj_customer,
    orNumber varchar2(20) constraint pj_my_orNumber_fk references pj_order
);

drop table pet_health;

create table pet_health(
   petHealth varchar2(100) not null,
    id varchar2(20) constraint pet_health_id_fk references pj_customer,
    pNum varchar2(20) constraint pet_health_pNum_fk references pj_my,
    petNumber number(10) constraint pet_health_petNumber_fk references pj_pet
);

drop table pj_petloc;

create table pj_petloc(
   petLocation varchar2(20) not null,
    id varchar2(20) constraint pet_petloc_id_fk references pj_customer,
    pNum varchar2(20) constraint pj_petloc_pNum_fk references pj_my,
    petNumber number(10) constraint pj_petloc_petNumber_fk references pj_pet
);

drop table pj_productCompany;

create table pj_productCompany(
pcName varchar2(20),
pcAddress varchar2(20),
pcPhoneNumber varchar2(20));

drop table pj_helpBoard;

create table pj_helpBoard(
     id varchar2(20) constraint pet_helpBoard_id_fk references pj_customer,
   hb_writer varchar2(20) not null,
   hb_number number primary key,
   hb_subject varchar2(50) not null,
   hb_content varchar2(500) not null,
   hb_readcount number default 0,
   hb_ref number not null,
   hb_re_step number not null,
   hb_re_level number not null,
   hb_reg_date date not null,
   hb_del char(1)
);
insert into pj_helpboard
values('master','asd',123,'test','asd',0,0,0,0,sysdate,'n');

select * from pj_helpboard;
 
 drop table pj_Board;
 
create table pj_Board(
 bNumber number(10) PRIMARY KEY,
 id varchar2(20) constraint pet_Board_id_fk references pj_customer,
 b_subject varchar(50),
 b_content varchar(500),
 b_ip varchar(20),
 b_readcount number(38),
 b_regdate date,
 b_del char(3));

drop table replyBoard;

create table replyBoard(
rno number primary key,
bNumber number(10) constraint replyBoard_bNumber_fk references pj_Board,
id varchar2(20) constraint replyBoard_id_fk references pj_customer,
r_content varchar2(500),
r_regdate date not null,
r_updatedate date not null,
r_del char(3)
 );