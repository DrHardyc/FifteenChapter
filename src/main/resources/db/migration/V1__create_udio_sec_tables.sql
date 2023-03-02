create table udio_sec.token (
    id int8 not null,
    key varchar(255),
    lpu varchar(255),
    primary key (id)
);

create table udio_sec.user_role (
    user_id int8 not null,
    roles varchar(255)
);

create table udio_sec.usr (
    id int8 not null,
    active boolean not null,
    email varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
);

