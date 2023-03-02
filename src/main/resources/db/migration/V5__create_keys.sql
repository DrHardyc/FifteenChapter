create sequence public.hibernate_sequence start 1 increment 1;
create sequence udio_sec.usr_seq start 2 increment 1;

alter table if exists udio_sec.user_role
    add constraint FKfpm8swft53ulq2hl11yplpr5
    foreign key (user_id)
    references udio_sec.usr;

alter table if exists udio_datacontrol.data_udio_resp
    add constraint FK9w6f46e0nk3njs3lcyep8gpoj
    foreign key (dataudiorespidenty_id)
    references udio_datacontrol.data_udio_resp_identy;

alter table if exists udio_tfoms.data_file_patient
    add constraint FKg86f4kfy5ixa16l70xrmt5wjp
    foreign key (sex_id) references udio_tfoms.sex;

alter table if exists udio_tfoms.data_file_patient
    add constraint FKksigs8ailwgrwwuvo7wax9qda
    foreign key (datafile_id) references udio_tfoms.data_file;

alter table if exists udio_tfoms.dgroup
    add constraint FK1btbcvgymd1tr73tpwogicgix
    foreign key (people_id) references udio_tfoms.people;

alter table if exists udio_tfoms.dncase
    add constraint FK6bxtwr6jip661e75070fis6af
    foreign key (people_id) references udio_tfoms.people;

alter table if exists udio_tfoms.dnget
    add constraint FKcow8hgookrgj1yx5aix5ny8yh
    foreign key (people_id) references udio_tfoms.people;

alter table if exists udio_tfoms.nhistory_dfp
    add constraint FKa5hk9vumrbxcctdan4rsrhoyr
    foreign key (data_file_patient_id) references udio_tfoms.data_file_patient;

alter table if exists udio_tfoms.nhistory_dnget
    add constraint FK8vl3kr33vm31mbyrsokbgx0f1
    foreign key (dnget_id) references udio_tfoms.dnget;

alter table if exists udio_tfoms.people
    add constraint FKnjs6ei2pq3pvu8scv6i4fg2tl
    foreign key (dfp_id) references udio_tfoms.data_file_patient;

alter table if exists udio_tfoms.people
    add constraint FKljh16dgcarsvf6sv85o23j39h
    foreign key (sex_id) references udio_tfoms.sex;

alter table if exists udio_util.app_param
    add constraint FK92jjp9qdpdvyrqr5pxr7lavth
    foreign key (user_id) references udio_sec.usr;
