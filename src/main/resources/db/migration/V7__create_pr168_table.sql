create table udio_nsi.pr168 (
    id int8 not null,
    diag varchar(255),
    periodpr168 varchar(255),
    duration varchar(255),
    spec int4,
    primary key (id)
);

insert into udio_nsi.pr168 values (1, 'I10,I11,I12,I13,I14,I15,E78,R73.0,R73.9', 'ONE_1YEAR', 'ALL', 76);
insert into udio_nsi.pr168 values (2, 'I20,I21,I22,I23,I24,I25,Z95.1,Z95.5,I44,I45,I46,I47,I48,I49,Z95.0,I50,I65.2,' ||
                                      'K86,D13.4', 'TWO_1YEAR', 'ALL', 76);
insert into udio_nsi.pr168 values (3, 'E11', 'CLINIC_RECOMMEND', 'ALL', 76);
insert into udio_nsi.pr168 values (4, 'I69.0,I69.1,I69.2,I69.3,I69.4,I67.8',
                                   'ONE_3MONTH_IN_FIRST_1YEAR_AND_OTHER_ONE_6MONTH', 'ALL', 76);
insert into udio_nsi.pr168 values (5, 'K20', 'ONE_6MONTH', 'THREE_YEAR', 76);
insert into udio_nsi.pr168 values (6, 'K21.0', 'ONE_6MONTH', 'FIVE_YEAR', 76);
insert into udio_nsi.pr168 values (7, 'K25', 'ONE_6MONTH', 'ALL', 76);
insert into udio_nsi.pr168 values (8, 'K26', 'ONE_1YEAR', 'FIVE_YEAR', 76);
insert into udio_nsi.pr168 values (9, 'K31.7,J44.0,J44.8,J44.9,J47.0,J45.0,J45.1,J45.8,J45.9,J12,J13,J14,J84.1,N81.5,' ||
                                      'K29.4,K29.5,D12.6,K31.7,K50,K51,K22.0,K22.2,K22.7,D37.6', 'ONE_1YEAR', 'ALL', 76);
insert into udio_nsi.pr168 values (10, 'N18.1', 'FOUR_1YEAR', 'ALL', 76);
insert into udio_nsi.pr168 values (11, 'N18.9', 'ONE_1YEAR', 'ALL', 76);
insert into udio_nsi.pr168 values (12, 'D12.8', 'TWO_1YEAR', 'ALL', 76);
insert into udio_nsi.pr168 values (13, 'K62.1', 'FOUR_1YEAR', 'ALL', 76);
insert into udio_nsi.pr168 values (14, 'K70.3,K74.3,K74.4,K74.5,K75.6', 'ONE_4MONTH', 'ALL', 76);

insert into udio_nsi.pr168 values (15, 'I05,I06,I07,I08,I09,I34,I35,I36,I37,I51.0,I51.1,I51.2,I71,Z95.2,Z95.3,' ||
                                       'Z95.4,Z95.8,Z95.9,I10,I11,I12,I13,I14,I15,I20,I21,I22,I23,I24,I25,Z95.1,Z95.5,' ||
                                       'I27.0,I28,I27.2,I27.8,I42,I44,I45,I46,I47,I48,I49,Z95.0,I50,I65.2,E78',
                                   'TWO_1YEAR', 'ALL', 25);
insert into udio_nsi.pr168 values (16, 'I26', 'FOUR_1YEAR', 'ONE_YEAR', 25);
insert into udio_nsi.pr168 values (18, 'I33,I38,I39,I40,I41,I51.4', 'TWO_1YEAR', 'ONE_YEAR', 25);
insert into udio_nsi.pr168 values (19, 'Q21.0,Q21.1,Q21.4,', 'ONE_1YEAR', 'FIVE_YEAR', 25);
insert into udio_nsi.pr168 values (20, 'Q21.3,Q22.4,Q22.5,', 'TWO_1YEAR', 'ALL', 25);
insert into udio_nsi.pr168 values (21, 'Q20,Q21,Q22,Q23,Q24,Q25,Q26,Q27,Q28', 'ONE_1YEAR', 'ALL', 25);

insert into udio_nsi.pr168 values (22, 'B18.0,B18.1,B18.2,E34.8,D13.7,D35.0,D35.1,D35.2,D35.8,E34.5,E22,E04.1,E04.2' ||
                                       'E05.1,E05.2', 'ONE_1YEAR', 'ALL', 24);
insert into udio_nsi.pr168 values (23, 'D44.8,D35.0,D35.1,D35.8', 'TWO_1YEAR', 'ALL', 24);
insert into udio_nsi.pr168 values (24, 'D35.1,E21.0', 'ONE_1YEAR', 'FIVE_YEAR', 24);
insert into udio_nsi.pr168 values (25, 'D35.0', 'TWO_1YEAR', 'TEN_YEAR', 24);

insert into udio_nsi.pr168 values (26, 'Q85.1', 'ONE_1YEAR', 'ALL', 35);

insert into udio_nsi.pr168 values (27, 'D11,Q78.1', 'ONE_1YEAR', 'ALL', 90);

insert into udio_nsi.pr168 values (28, 'D30.3,D30.4,N48.0,D41.0,D30.0,D29.1', 'ONE_1YEAR', 'ALL', 84);

insert into udio_nsi.pr168 values (29, 'M96', 'TWO_1YEAR', 'TEN_YEAR', 79);

insert into udio_nsi.pr168 values (30, 'D31,D23.1', 'TWO_1YEAR_IN_FIRST_2YEAR_AND_OTHER_ONE_1YEAR', 'ALL', 46);

insert into udio_nsi.pr168 values (31, 'J38.1,D14.1,D14.2,D14.0,D14,D10.4,D10.5,D10.6,D10.7,D10.9,J37,J31', 'ONE_1YEAR', 'ALL', 45);
insert into udio_nsi.pr168 values (32, 'J33', 'TWO_1YEAR', 'ALL', 45);

insert into udio_nsi.pr168 values (33, 'K13.2,D10.0,D10.1,D10.2,D10.3,K13.7,Q78.1', 'TWO_1YEAR', 'ALL', 69);
insert into udio_nsi.pr168 values (34, 'K13.0,L43', 'ONE_1YEAR', 'ALL', 69);

insert into udio_nsi.pr168 values (35, 'D22,Q82,5,D23,L57.1', 'ONE_1YEAR', 'ALL', 17);
insert into udio_nsi.pr168 values (36, 'L82', 'TWO_1YEAR', 'ALL', 17);
insert into udio_nsi.pr168 values (37, 'Q82.1', 'ONE_3MONTH_IN_FIRST_YEAR_OTHER_TWO_1YEAR', 'ALL', 17);

insert into udio_nsi.pr168 values (38, 'N84', 'ONE_6MONTH_IN_FIRST_1YEAR_AND_OTHER_ONE_1YEAR', 'FIVE_YEAR', 2);
insert into udio_nsi.pr168 values (39, 'E28.2, N88.0', 'ONE_1YEAR', 'ALL', 2);
insert into udio_nsi.pr168 values (40, 'N85.0', 'TWO_1YEAR', 'FIVE_YEAR', 2);
insert into udio_nsi.pr168 values (41, 'E87.1, N87.2', 'ONE_3MONTH_OTHER_TWO_1YEAR_IN_2YEAR_OTHER_ONE_1YEAR_IN_DUR_20YEARS', 'TWENTY_YEAR', 2);

insert into udio_nsi.pr168 values (42, 'В24', 'ONE_1YEAR', 'FIVE_YEAR', 41);
insert into udio_nsi.pr168 values (43, 'N60', 'ONE_1YEAR', 'MIN_5YEAR_TO_60YEAR', 41);

