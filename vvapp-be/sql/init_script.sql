create table event_type
(
    event_type_id serial not null
        constraint event_type_pk
            primary key,
    name          varchar
);

create table event
(
    name          varchar(100),
    event_id      serial            not null
        constraint event_pk
            primary key,
    event_type_id integer default 1 not null
        constraint event_event_type_id_fk
            references event_type
);

create table event_role
(
    event_role_id serial  not null
        constraint event_role_pk
            primary key,
    name          varchar,
    event_type_id integer not null
        constraint event_role_event_type_event_type_id_fk
            references event_type
);

create table senior
(
    senior_id serial not null
        constraint senior_pk
            primary key,
    name      varchar,
    email     varchar
);

create unique index senior_senior_id_uindex
    on senior (senior_id);

create table participation
(
    event_id         integer not null
        constraint participation_event_event_id_fk
            references event,
    senior_id        integer not null
        constraint participation_senior_senior_id_fk
            references senior,
    event_role_id    integer not null
        constraint participation_event_role_event_role_id_fk
            references event_role,
    participation_id serial  not null
        constraint participation_pk
            primary key
);

create unique index participation_participation_id_uindex
    on participation (participation_id);

create table review
(
    review_id        serial    not null
        constraint review_pk
            primary key,
    senior_id        integer   not null
        constraint review_senior_senior_id_fk
            references senior,
    date             timestamp not null,
    text             text      not null,
    participation_id integer   not null
        constraint review_participation_participation_id_fk
            references participation
);

create unique index review_review_id_uindex
    on review (review_id);


