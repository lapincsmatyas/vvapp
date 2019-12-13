create table event_type
(
	event_type_id serial not null
		constraint event_type_pk
			primary key,
	name varchar
);

create table event_role
(
	event_role_id serial not null
		constraint event_role_pk
			primary key,
	name varchar,
	event_type_id integer not null
		constraint event_role_event_type_event_type_id_fk
			references event_type
);

create table senior_group
(
	group_id integer not null
		constraint group_pk
			primary key,
	name varchar not null
);

create unique index group_group_id_uindex
	on senior_group (group_id);

create table user_role
(
	user_role_id serial not null
		constraint user_role_pk
			primary key,
	name varchar not null
);


create table senior
(
	senior_id serial not null
		constraint senior_pk
			primary key,
	name varchar,
	email varchar,
	mobile varchar,
	auth_sch_id varchar,
	group_id integer default 1
		constraint senior_group_group_id_fk
			references senior_group,
	last_login timestamp,
	user_role_id integer
		constraint senior_user_role_user_role_id_fk
			references user_role
);


create table event
(
	name varchar(100),
	event_id serial not null
		constraint event_pk
			primary key,
	event_type_id integer default 1 not null
		constraint event_event_type_id_fk
			references event_type,
	supervisor_id integer
		constraint event_senior_senior_id_fk
			references senior
);

create unique index senior_senior_id_uindex
	on senior (senior_id);

create table participation
(
	event_id integer not null
		constraint participation_event_event_id_fk
			references event,
	senior_id integer not null
		constraint participation_senior_senior_id_fk
			references senior,
	event_role_id integer not null
		constraint participation_event_role_event_role_id_fk
			references event_role,
	participation_id serial not null
		constraint participation_pk
			primary key,
	state boolean default false
);

create unique index participation_participation_id_uindex
	on participation (participation_id);

create table review
(
	review_id serial not null
		constraint review_pk
			primary key,
	senior_id integer not null
		constraint review_senior_senior_id_fk
			references senior,
	date timestamp not null,
	text text not null,
	participation_id integer not null
		constraint review_participation_participation_id_fk
			references participation
);

create unique index review_review_id_uindex
	on review (review_id);

create table spring_session
(
	primary_id char(36) not null
		constraint spring_session_pk
			primary key,
	session_id char(36) not null,
	creation_time bigint not null,
	last_access_time bigint not null,
	max_inactive_interval integer not null,
	expiry_time bigint not null,
	principal_name varchar(100)
);

create unique index spring_session_ix1
	on spring_session (session_id);

create index spring_session_ix2
	on spring_session (expiry_time);

create index spring_session_ix3
	on spring_session (principal_name);

create table spring_session_attributes
(
	session_primary_id char(36) not null
		constraint spring_session_attributes_fk
			references spring_session
				on delete cascade,
	attribute_name varchar(200) not null,
	attribute_bytes bytea not null,
	constraint spring_session_attributes_pk
		primary key (session_primary_id, attribute_name)
);