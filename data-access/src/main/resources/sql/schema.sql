create table Account( id int IDENTITY primary key, name varchar(128), owner_id int, active boolean );
create table User ( id int primary key, firstName varchar(128), lastName varchar(128), active boolean );
create table Region( id int IDENTITY primary key, name varchar(128), timeZone varchar(128), createdBy_id int, createdDate timestamp,lastModifiedBy_id int, lastModifiedDate timestamp);
create table AccountProperty( id int IDENTITY primary key, name varchar(128) not null, value varchar(512) not null);
create table Account_AccountProperty( properties_id int, account_id int);
create table Ticket(id varchar(64) primary key, persistentType varchar(128), typeName varchar(512), submissionTime TIMESTAMP, submitter varchar(128), source varchar(128), sourceId varchar(128), correlationId varchar(128), raw_data CLOB, data_bytes BLOB);

create table UpdateAccountTicket(id varchar(128) primary key, account_id int );
alter table Account add constraint fk_account_owner foreign key (owner_id) references User (id);  
alter table Account_AccountProperty add constraint fk_acc_acc_property_account foreign key (account_id) references Account(id);
alter table Account_AccountProperty add constraint fk_acc_acc_property_property foreign key (properties_id) references AccountProperty(id);