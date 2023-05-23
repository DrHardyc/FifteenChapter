create table udio_datacontrol.data_udio_resp (
    id int8 not null,
    code_resp varchar(255),
    date_beg timestamp,
    date_edit timestamp,
    enp varchar(255),
    fam varchar(255),
    im varchar(255),
    ot varchar(255),
    dataudiorespidenty_id int8,
    primary key (id)
);

create table udio_datacontrol.data_udio_resp_identy (
    id int8 not null,
    date_beg timestamp,
    date_edit timestamp,
    identy int8,
    status varchar(255),
    primary key (id)
);

create table udio_datacontrol.data_udio_resp_identy_gen (
    gen_id int8 not null,
    date_beg timestamp,
    date_edit timestamp,
    primary key (gen_id)
);




