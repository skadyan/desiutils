create table TrxSet( 
	id int IDENTITY primary key
	, status varchar(128)
	, enteredBy varchar(128)
	, approvedBy varchar(128)
	, keywords varchar(1024)
	, businessArea varchar(128)
	, reasonType varchar(128)
	, created TIMESTAMP
);
