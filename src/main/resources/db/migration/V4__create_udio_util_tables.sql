create table udio_util.app_param (
    id int8 not null,
    description varchar(255),
    name varchar(255),
    value varchar(255),
    user_id int8 not null,
    primary key (id)
);