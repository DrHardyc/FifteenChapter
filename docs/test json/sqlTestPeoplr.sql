insert into udio_tfoms.people (id, enp, name, patronymic, surname, mo_attach, sex, date_beg, date_birth, date_edit)
values (1, '1235486925412365', '��������', '��������', '���������', -1, 2, now(), to_date('12.02.1111', 'dd.mm.yyyy'), now());

insert into udio_tfoms.people (id, enp, name, patronymic, surname, mo_attach, sex, date_beg, date_birth, date_edit)
values (2, '1235486925412367', '����', '����������', 'Durak', -1, 1, now(), to_date('23.07.1131', 'dd.mm.yyyy'), now());

select * from udio_tfoms.people p where p.surname = '���������'