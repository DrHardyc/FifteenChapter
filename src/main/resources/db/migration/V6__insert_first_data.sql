insert into udio_sec.usr values (1, true, '', '$2a$08$nJloIzzuA2qb9rncg94U6OO8/G0S267kEsgSMbIZ5dH8oyka7CXQi', 'DrHardy',
                                 'Черчесов Михаил Владимирович', 'Начальник отдела ИБ');
insert into udio_sec.user_role values (1, 'ROLE_TFOMS');
insert into udio_sec.user_role values (1, 'ROLE_ADMIN');
insert into udio_sec.user_role values (1, 'ROLE_USER');

insert into udio_tfoms.sex values (1, now(), now(), 'мужской');
insert into udio_tfoms.sex values (2, now(), now(), 'женский');