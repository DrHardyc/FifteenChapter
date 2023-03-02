create table udio_tfoms.data_file (
    id int8 not null,
    date timestamp,
    date_beg timestamp,
    date_edit timestamp,
    lpu varchar(255),
    name varchar(255),
    result_code varchar(255),
    result_desc varchar(255),
    primary key (id)
);

create table udio_tfoms.data_file_patient (
    id int8 not null,
    date_1 timestamp,
    date_2 timestamp,
    date_beg timestamp,
    date_edit timestamp,
    dbeg timestamp,
    dr timestamp,
    enp varchar(255),
    fam varchar(255),
    iddiag varchar(255),
    iddokt varchar(255),
    idsrz int8,
    im varchar(255),
    ot varchar(255),
    sex_id int8 not null,
    datafile_id int8 not null,
    primary key (id)
);

create table udio_tfoms.dgroup (
    id int8 not null,
    code_spec int4,
    date_beg date,
    date_edit date,
    ds varchar(255),
    period varchar(255),
    people_id int8 not null,
    primary key (id)
);

create table udio_tfoms.dncase (
    id int8 not null,
    date date,
    date_beg date,
    date_edit date,
    idsl int4,
    people_id int8 not null,
    primary key (id)
);

create table udio_tfoms.dnget (
    id int8 not null,
    date_1 timestamp,
    date_beg timestamp,
    date_edit timestamp,
    dbeg timestamp,
    iddiag varchar(255),
    iddokt varchar(255),
    lpu varchar(255),
    people_id int8 not null,
    primary key (id)
);

create table udio_tfoms.nhistory_dfp (
    data_file_patient_id int8 not null,
    nhistory varchar(255)
);

create table udio_tfoms.nhistory_dnget (
    dnget_id int8 not null,
    nhistory varchar(255)
);

create table udio_tfoms.people (
    id int8 not null,
    date_beg timestamp,
    date_edit timestamp,
    dr timestamp,
    enp varchar(255),
    fam varchar(255),
    idsrz int8,
    im varchar(255),
    ot varchar(255),
    dfp_id int8 not null,
    sex_id int8 not null,
    primary key (id)
);

create table udio_tfoms.sex (
    id int8 not null,
    date_beg timestamp,
    date_edit timestamp,
    name varchar(255),
    primary key (id)
);


