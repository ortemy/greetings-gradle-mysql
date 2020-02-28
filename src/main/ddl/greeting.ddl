create table greeting
(
	id bigint not null
		primary key,
	greeting_type int null,
	message varchar(255) null
)
engine=MyISAM
;

