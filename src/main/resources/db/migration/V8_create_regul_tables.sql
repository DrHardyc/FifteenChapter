-- create sequence regul.tablename_id_seq;
-- create table regul.tablename
-- (
--     id                           bigint default nextval('regul.tablename_id_seq'::regclass) not null
--         constraint tablename_pk
--             primary key,
--     col1                     varchar                                                 not null,
--     col2                     varchar                                                 not null,
--     col3                     varchar                                                 not null,
--     tablenameparent_id                    bigint
--         constraint tablenameparent_fk_id
--             references regul.tablenameparent
-- );

create sequence regul.fileul_id_seq;
create table regul.file_ul
(
    id                           bigint default nextval('regul.fileul_id_seq'::regclass) not null
        constraint file_ul_pk
            primary key,
    id_file                      varchar,
    format_version               varchar,
    info_type                    varchar,
    transmission_program_version varchar,
    quantity_doc                 varchar
);
create sequence regul.sender_people_id_seq;
create table regul.sender_people
(
    id                           bigint default nextval('regul.sender_people_id_seq'::regclass) not null
        constraint sender_people_pk
            primary key,
    position                     varchar,
    phone                        varchar,
    email                        varchar,
    fileul_id                    bigint
        constraint fileul_fk_id
        references regul.file_ul
);
create sequence regul.in_pr_dog_inv_tov_id_seq;
create table regul.in_pr_dog_inv_tov
(
    id                           bigint default nextval('regul.in_pr_dog_inv_tov_id_seq'::regclass) not null
        constraint in_pr_dog_inv_tov_pk
            primary key,
    name                     varchar,
    number                   varchar,
    date                     varchar,
    uchrdoginvtov_id         bigint
        constraint uchrdoginvtov_fk_id
            references regul.uchr_dog_inv_tov
);
create sequence regul.document_ul_id_seq;
create table regul.document_ul
(
    id                           bigint default nextval('regul.document_ul_id_seq'::regclass) not null
        constraint document_ul_pk
            primary key,
    id_doc                       varchar,
    fileul_id                    bigint
        constraint fileul_fk_id
            references regul.file_ul
);
create sequence regul.person_ul_id_seq;
create table regul.person_ul
(
    id                           bigint default nextval('regul.person_ul_id_seq'::regclass) not null
        constraint person_ul_pk
            primary key,
    date_vipl               varchar,
    ogrn                    varchar,
    date_ogrn               varchar,
    inn                     varchar,
    kpp                     varchar,
    opf                     varchar,
    kod_opf                 varchar,
    full_name_opf           varchar,
    documentul_id           bigint
        constraint documentl_fk_id
            references regul.document_ul
);
create sequence regul.konv_zaim_id_seq;
create table regul.konv_zaim
(
    id                           bigint default nextval('regul.konv_zaim_id_seq'::regclass) not null
        constraint konv_zaim_pk
            primary key,
    number                   varchar,
    date                     varchar,
    personul_id              bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.fio_type_id_seq;
create table regul.fio_type
(
    id                           bigint default nextval('regul.fio_type_id_seq'::regclass) not null
        constraint fio_type_pk
            primary key,
    surname                  varchar,
    name                     varchar,
    patronymic               varchar,
    senderpeople_id         bigint
        constraint senderpeople_fk_id
            references regul.sender_people,
    konvzaim_id                    bigint
        constraint konvzaim_fk_id
            references regul.konv_zaim
);
create sequence regul.name_ul_id_seq;
create table regul.name_ul
(
    id                       bigint default nextval('regul.name_ul_id_seq'::regclass) not null
        constraint name_ul_pk
            primary key,
    full_name                     varchar,
    personul_id                  bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.short_name_ul_id_seq;
create table regul.short_name_ul_type
(
    id                           bigint default nextval('regul.short_name_ul_id_seq'::regclass) not null
        constraint short_name_ul_pk
            primary key,
    name_sokr                     varchar,
    nameul_id                    bigint
        constraint nameul_fk_id
            references regul.name_ul
);
create sequence regul.name_ul_kod_okin_id_seq;
create table regul.name_ul_kod_okin
(
    id                           bigint default nextval('regul.name_ul_kod_okin_id_seq'::regclass) not null
        constraint name_ul_kod_okin_pk
            primary key,
    kod_okin                     varchar,
    name_okin                     varchar,
    nameul_id                    bigint
        constraint nameul_fk_id
            references regul.name_ul
);
create sequence regul.uchrediteli_id_seq;
create table regul.uchrediteli
(
    id                           bigint default nextval('regul.uchrediteli_id_seq'::regclass) not null
        constraint uchrediteli_pk
            primary key,
    personul_id                    bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.uchr_ul_ros_id_seq;
create table regul.uchr_ul_ros
(
    id                           bigint default nextval('regul.uchr_ul_ros_id_seq'::regclass) not null
        constraint uchr_ul_ros_pk
            primary key,
    uchrediteli_id                    bigint
        constraint uchrediteli_fk_id
            references regul.uchrediteli
);
create sequence regul.uchr_fl_id_seq;
create table regul.uchr_fl
(
    id                           bigint default nextval('regul.uchr_fl_id_seq'::regclass) not null
        constraint uchr_fl_pk
            primary key,
    vid_adr_klassif             varchar,
    ogrn_ip                     varchar,
    uchrediteli_id              bigint
        constraint uchrediteli_fk_id
            references regul.uchrediteli
);
create sequence regul.uchr_rf_sub_mo_id_seq;
create table regul.uchr_rf_sub_mo
(
    id                           bigint default nextval('regul.uchr_rf_sub_mo_id_seq'::regclass) not null
        constraint uchr_rf_sub_mo_pk
            primary key,
    uchrediteli_id          bigint
        constraint uchrediteli_fk_id
            references regul.uchrediteli
);
create sequence regul.org_osush_pr_id_seq;
create table regul.org_osush_pr
(
    id                           bigint default nextval('regul.org_osush_pr_id_seq'::regclass) not null
        constraint org_osush_pr_pk
            primary key,
    uchrrfsubmo_id                    bigint
        constraint uchrrfsubmo_fk_id
            references regul.uchr_rf_sub_mo
);
create sequence regul.uchr_pif_id_seq;
create table regul.uchr_pif
(
    id                           bigint default nextval('regul.uchr_pif_id_seq'::regclass) not null
        constraint uchr_pif_pk
            primary key,
    uchrediteli_id                    bigint
        constraint uchrediteli_fk_id
            references regul.uchrediteli
);
create sequence regul.uchr_dog_inv_tov_id_seq;
create table regul.uchr_dog_inv_tov
(
    id                           bigint default nextval('regul.uchr_dog_inv_tov_id_seq'::regclass) not null
        constraint uchr_dog_inv_tov_pk
            primary key,
    uchrdoginvtov_id                    bigint
        constraint uchrdoginvtov_fk_id
            references regul.uchr_dog_inv_tov
);
create sequence regul.dolia_ooo_id_seq;
create table regul.dolia_ooo
(
    id                           bigint default nextval('regul.dolia_ooo_id_seq'::regclass) not null
        constraint dolia_ooo_pk
            primary key,
    nomin_stoim                    varchar,
    personul_id                    bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.obrem_type_id_seq;
create table regul.obrem_type
(
    id                           bigint default nextval('regul.obrem_type_id_seq'::regclass) not null
        constraint obrem_type_pk
            primary key,
    vid                     varchar,
    uchrulros_id            bigint
        constraint uchrulros_fk_id
            references regul.uchr_ul_ros,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl,
    orgosushpr_id           bigint
        constraint orgosushpr_fk_id
            references regul.org_osush_pr,
    uchrpif_id           bigint
        constraint uchrpif_fk_id
            references regul.uchr_pif,
    uchrdoginvtov_id           bigint
        constraint uchrdoginvtov_fk_id
            references regul.uchr_dog_inv_tov,
    doliaooo_id           bigint
        constraint doliaooo_fk_id
            references regul.dolia_ooo
);
create sequence regul.zalog_derj_ul_id_seq;
create table regul.zalog_derj_ul
(
    id                           bigint default nextval('regul.zalog_derj_ul_id_seq'::regclass) not null
        constraint zalog_derj_ul_pk
            primary key,
    obremtype_id                    bigint
        constraint obremtype_fk_id
            references regul.obrem_type
);
create sequence regul.uchr_ul_in_id_seq;
create table regul.uchr_ul_in
(
    id                           bigint default nextval('regul.uchr_ul_in_id_seq'::regclass) not null
        constraint uchr_ul_in_pk
            primary key,
    uchrediteli_id                    bigint
        constraint uchrediteli_fk_id
            references regul.uchrediteli
);
create sequence regul.uprav_zal_type_id_seq;
create table regul.uprav_zal_type
(
    id                           bigint default nextval('regul.uprav_zal_type_id_seq'::regclass) not null
        constraint uprav_zal_type_pk
            primary key,
    uchrulros_id                    bigint
        constraint uchrulros_fk_id
            references regul.uchr_ul_ros,
    uchrulin_id                    bigint
        constraint uchrulin_fk_id
            references regul.uchr_ul_in,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl,
    orgosushpr_id           bigint
        constraint orgosushpr_fk_id
            references regul.org_osush_pr,
    uchrpif_id           bigint
        constraint uchrpif_fk_id
            references regul.uchr_pif,
    uchrdoginvtov_id           bigint
        constraint uchrdoginvtov_fk_id
            references regul.uchr_dog_inv_tov,
    doliaooo_id           bigint
        constraint doliaooo_fk_id
            references regul.dolia_ooo
);
create sequence regul.upr_zal_ul_id_seq;
create table regul.upr_zal_ul
(
    id                           bigint default nextval('regul.upr_zal_ul_id_seq'::regclass) not null
        constraint upr_zal_ul_pk
            primary key,
    uprzalul_id                    bigint
        constraint uprzalul_fk_id
            references regul.uprav_zal_type
);
create sequence regul.dov_up_ul_type_id_seq;
create table regul.dov_up_ul_type
(
    id                           bigint default nextval('regul.dov_up_ul_type_id_seq'::regclass) not null
        constraint dov_up_ul_type_pk
            primary key,
    date_otkr_nasl                  varchar,
    uchrulros_id                    bigint
        constraint uchrulros_fk_id
            references regul.uchr_ul_ros,
    uchrulin_id                    bigint
        constraint uchrulin_fk_id
            references regul.uchr_ul_in,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl
);
create sequence regul.zaim_dav_ul_id_seq;
create table regul.zaim_dav_ul
(
    id                           bigint default nextval('regul.zaim_dav_ul_id_seq'::regclass) not null
        constraint zaim_dav_ul_pk
            primary key,
    konvzaim_id                  bigint
        constraint konvzaim_fk_id
            references regul.konv_zaim
);
create sequence regul.podrazd_id_seq;
create table regul.podrazd
(
    id                           bigint default nextval('regul.podrazd_id_seq'::regclass) not null
        constraint podrazd_pk
            primary key,
    personul_id              bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.filial_id_seq;
create table regul.filial
(
    id                           bigint default nextval('regul.filial_id_seq'::regclass) not null
        constraint filial_pk
            primary key,
    vid_adr_Classif               varchar,
    podrazd_id                    bigint
        constraint podrazd_fk_id
            references regul.podrazd
);
create sequence regul.predst_id_seq;
create table regul.predst
(
    id                           bigint default nextval('regul.predst_id_seq'::regclass) not null
        constraint predst_pk
            primary key,
    vid_adr_classif               varchar,
    podrazd_id                    bigint
        constraint podrazd_fk_id
            references regul.podrazd
);
create sequence regul.name_poln_type_id_seq;
create table regul.name_poln_type
(
    id                           bigint default nextval('regul.name_poln_type_id_seq'::regclass) not null
        constraint name_poln_type_pk
            primary key,
    name_long                    varchar,
    nameul_id                    bigint
        constraint nameul_fk_id
            references regul.name_ul,
    zalogderjul_id                    bigint
        constraint zalogderjul_fk_id
            references regul.zalog_derj_ul,
    uprzalul_id                    bigint
        constraint uprzalul_fk_id
            references regul.upr_zal_ul,
    dovuprultpye_id                    bigint
        constraint dovuprultpye_fk_id
            references regul.dov_up_ul_type,
    uchrulin_id                    bigint
        constraint uchrulin_fk_id
            references regul.uchr_ul_in,
    zaimdavul_id                    bigint
        constraint zaimdavul_fk_id
            references regul.zaim_dav_ul,
    filial_id           bigint
        constraint filial_fk_id
            references regul.filial,
    predst_id           bigint
        constraint predst_fk_id
            references regul.predst
);
create sequence regul.name_sokr_type_id_seq;
create table regul.name_sokr_type
(
    id                           bigint default nextval('regul.name_sokr_type_id_seq'::regclass) not null
        constraint name_sokr_type_pk
            primary key,
    name_short                     varchar,
    nameul_id                    bigint
        constraint nameul_fk_id
            references regul.name_ul
);
create sequence regul.address_ul_id_seq;
create table regul.address_ul
(
    id                           bigint default nextval('regul.address_ul_id_seq'::regclass) not null
        constraint address_ul_pk
            primary key,
    vid_adr_klassif                varchar,
    personul_id                    bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.mnul_id_seq;
create table regul.mnul
(
    id                           bigint default nextval('regul.mnul_id_seq'::regclass) not null
        constraint mnul_pk
            primary key,
    id_nom                     varchar,
    region                     varchar,
    name_region                varchar,
    addressul_id               bigint
        constraint addressul_fk_id
            references regul.address_ul
);
create sequence regul.adr_fias_egrul_type_id_seq;
create table regul.adr_fias_egrul_type
(
    id                           bigint default nextval('regul.adr_fias_egrul_type_id_seq'::regclass) not null
        constraint adr_fias_egrul_type_pk
            primary key,
    id_nom                    varchar,
    index                     varchar,
    region                    varchar,
    name_region               varchar,
    addressul_id             bigint
        constraint addressul_fk_id
            references regul.address_ul,
    predst_id           bigint
        constraint predst_fk_id
            references regul.predst,
    filial_id           bigint
        constraint filial_fk_id
            references regul.filial
);
create sequence regul.adr_rf_egrul_type_id_seq;
create table regul.adr_rf_egrul_type
(
    id                           bigint default nextval('regul.adr_rf_egrul_type_id_seq'::regclass) not null
        constraint adr_rf_egrul_type_pk
            primary key,
    index                     varchar,
    kod_region                varchar,
    kod_adr_kladr             varchar,
    dom                       varchar,
    korpus                    varchar,
    kvart                     varchar,
    addressul_id              bigint
        constraint addressul_fk_id
            references regul.address_ul,
    filial_id           bigint
        constraint filial_fk_id
            references regul.filial,
    predst_id           bigint
        constraint predst_fk_id
            references regul.predst
);
create sequence regul.ned_adr_ul_id_seq;
create table regul.ned_adr_ul
(
    id                           bigint default nextval('regul.ned_adr_ul_id_seq'::regclass) not null
        constraint ned_adr_ul_pk
            primary key,
    prizn_ned_adres_ul                     varchar,
    text_ned_adres_ul                      varchar,
    address_ul_id                    bigint
        constraint address_ul_fk_id
            references regul.address_ul
);
create sequence regul.resh_sud_ned_adr_id_seq;
create table regul.resh_sud_ned_adr
(
    id                           bigint default nextval('regul.resh_sud_ned_adr_id_seq'::regclass) not null
        constraint resh_sud_ned_adr_pk
            primary key,
    name                     varchar,
    number                   varchar,
    date                     varchar,
    nedadrul_id                    bigint
        constraint nedadrul_fk_id
            references regul.ned_adr_ul
);
create sequence regul.resh_izm_mn_id_seq;
create table regul.resh_izm_mn
(
    id                           bigint default nextval('regul.resh_izm_mn_id_seq'::regclass) not null
        constraint resh_izm_mn_pk
            primary key,
    text_resh_izm_mn         varchar,
    addressul_id             bigint
        constraint addressul_fk_id
            references regul.address_ul
);
create sequence regul.email_ul_id_seq;
create table regul.email_ul
(
    id                           bigint default nextval('regul.email_ul_id_seq'::regclass) not null
        constraint email_ul_pk
            primary key,
    email                    varchar,
    personul_id              bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.obr_ul_id_seq;
create table regul.obr_ul
(
    id                           bigint default nextval('regul.obr_ul_id_seq'::regclass) not null
        constraint obr_ul_pk
            primary key,
    status_mkf               varchar,
    ogrn                     varchar,
    date_ogrn                varchar,
    reg_nom                  varchar,
    date_reg                 varchar,
    name_ro                  varchar,
    kod_ul                   varchar,
    personul_id              bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.sp_obr_ul_id_seq;
create table regul.sp_obr_ul
(
    id                           bigint default nextval('regul.sp_obr_ul_id_seq'::regclass) not null
        constraint sp_obr_ul_pk
            primary key,
    code                     varchar,
    name                     varchar,
    obrul_id                    bigint
        constraint obrul_fk_id
            references regul.obr_ul
);
create sequence regul.reg_in_ul_id_seq;
create table regul.reg_in_ul
(
    id                           bigint default nextval('regul.reg_in_ul_id_seq'::regclass) not null
        constraint reg_in_ul_pk
            primary key,
    inn                     varchar,
    name_ru                 varchar,
    name_en                 varchar,
    oksm                    varchar,
    name_country            varchar,
    date_reg                varchar,
    reg_num                 varchar,
    code_io_str_reg         varchar,
    obrul_id                bigint
        constraint obrul_fk_id
            references regul.obr_ul
);
create sequence regul.reg_org_id_seq;
create table regul.reg_org
(
    id                           bigint default nextval('regul.reg_org_id_seq'::regclass) not null
        constraint reg_org_pk
            primary key,
    kod_no                     varchar,
    name_no                    varchar,
    adr_ro                     varchar,
    personul_id                bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.status_ul_id_seq;
create table regul.status_ul
(
    id                           bigint default nextval('regul.status_ul_id_seq'::regclass) not null
        constraint status_ul_pk
            primary key,
    personul_id                    bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.status_st_ul_id_seq;
create table regul.status_st_ul
(
    id                           bigint default nextval('regul.status_st_ul_id_seq'::regclass) not null
        constraint status_st_ul_pk
            primary key,
    code                            varchar,
    name                            varchar,
    srok_likv                       varchar,
    statusul_id                    bigint
        constraint statusul_fk_id
            references regul.status_ul
);
create sequence regul.resh_isk_kul_id_seq;
create table regul.resh_isk_kul
(
    id                           bigint default nextval('regul.resh_isk_kul_id_seq'::regclass) not null
        constraint resh_isk_kul_pk
            primary key,
    date_resh                  varchar,
    number                     varchar,
    date_publication           varchar,
    number_jurnal              varchar,
    statusul_id                bigint
        constraint statusul_fk_id
            references regul.status_ul
);
create sequence regul.prek_ul_id_seq;
create table regul.prek_ul
(
    id                           bigint default nextval('regul.prek_ul_id_seq'::regclass) not null
        constraint prek_ul_pk
            primary key,
    date_prekr_ul                  varchar,
    personul_id                    bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.sp_prekr_ul_id_seq;
create table regul.sp_prekr_ul
(
    id                           bigint default nextval('regul.sp_prekr_ul_id_seq'::regclass) not null
        constraint sp_prekr_ul_pk
            primary key,
    code_sp_prekr_ul              varchar,
    name_sp_prekr_ul              varchar,
    prekrul_id                    bigint
        constraint prekrul_fk_id
            references regul.prek_ul
);
create sequence regul.reg_org_prekr_id_seq;
create table regul.reg_org_prekr
(
    id                           bigint default nextval('regul.reg_org_prekr_id_seq'::regclass) not null
        constraint reg_org_prekr_pk
            primary key,
    code                     varchar,
    name                     varchar,
    prekrul_id               bigint
        constraint prekrul_fk_id
            references regul.prek_ul
);
create sequence regul.type_ustav_id_seq;
create table regul.type_ustav
(
    id                           bigint default nextval('regul.type_ustav_id_seq'::regclass) not null
        constraint type_ustav_pk
            primary key,
    nom_type_ustav                 varchar,
    personul_id                    bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.uchet_ul_id_seq;
create table regul.uchet_ul
(
    id                           bigint default nextval('regul.uchet_ul_id_seq'::regclass) not null
        constraint uchet_ul_pk
            primary key,
    inn                    varchar,
    kpp                     varchar,
    date_post_uch           varchar,
    personul_id             bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.uchet_no_podrazd_type_id_seq;
create table regul.uchet_no_podrazd_type
(
    id                           bigint default nextval('regul.uchet_no_podrazd_type_id_seq'::regclass) not null
        constraint uchet_no_podrazd_type_pk
            primary key,
    kpp                     varchar,
    date_post_uch           varchar,
    filial_id               bigint
        constraint filial_fk_id
            references regul.filial,
    predst_id               bigint
        constraint predst_fk_id
            references regul.predst
);
create sequence regul.no_type_id_seq;
create table regul.no_type
(
    id                           bigint default nextval('regul.no_type_id_seq'::regclass) not null
        constraint no_type_pk
            primary key,
    code                     varchar,
    name                     varchar,
    uchetno_id               bigint
        constraint uchetno_fk_id
            references regul.uchet_ul,
    uchetnopodrazdtype_id       bigint
        constraint uchetnopodrazdtype_fk_id
            references regul.uchet_no_podrazd_type
);
create sequence regul.reg_pf_id_seq;
create table regul.reg_pf
(
    id                           bigint default nextval('regul.reg_pf_id_seq'::regclass) not null
        constraint reg_pf_pk
            primary key,
    reg_nom_pf                  varchar,
    date_prisv_nom              varchar,
    date_reg                    varchar,
    personul_id                 bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.org_pf_id_seq;
create table regul.org_pf
(
    id                           bigint default nextval('regul.org_pf_id_seq'::regclass) not null
        constraint org_pf_pk
            primary key,
    code                     varchar,
    name                     varchar,
    regpf_id                 bigint
        constraint regpf_fk_id
            references regul.reg_pf
);
create sequence regul.reg_fss_id_seq;
create table regul.reg_fss
(
    id                           bigint default nextval('regul.reg_fss_id_seq'::regclass) not null
        constraint reg_fss_pk
            primary key,
    reg_nom_fss                  varchar,
    date_pri_nom                 varchar,
    date_reg                     varchar,
    personul_id                    bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.org_fss_id_seq;
create table regul.org_fss
(
    id                           bigint default nextval('regul.org_fss_id_seq'::regclass) not null
        constraint org_fss_pk
            primary key,
    code                     varchar,
    name                     varchar,
    regfss_id                bigint
        constraint regfss_fk_id
            references regul.reg_fss
);
create sequence regul.ust_kap_id_seq;
create table regul.ust_kap
(
    id                          bigint default nextval('regul.ust_kap_id_seq'::regclass) not null
        constraint ust_kap_pk
            primary key,
    name_vid_kap                varchar,
    sum_kap                     varchar,
    personul_id                 bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.sved_umuk_id_seq;
create table regul.sved_umuk
(
    id                           bigint default nextval('regul.sved_umuk_id_seq'::regclass) not null
        constraint sved_umuk_pk
            primary key,
    vel_umuk                     varchar,
    date_resh                    varchar,
    svedumuk_id                  bigint
        constraint svedumuk_fk_id
            references regul.sved_umuk
);
create sequence regul.polnomochniy_id_seq;
create table regul.polnomochniy
(
    id                           bigint default nextval('regul.polnomochniy_id_seq'::regclass) not null
        constraint polnomochniy_pk
            primary key,
    vid_pol                     varchar,
    personul_id                 bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.upr_org_id_seq;
create table regul.upr_org
(
    id                           bigint default nextval('regul.upr_org_id_seq'::regclass) not null
        constraint upr_org_pk
            primary key,
    personul_id                    bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.name_inn_ul_id_seq;
create table regul.name_inn_ul
(
    id                           bigint default nextval('regul.name_inn_ul_id_seq'::regclass) not null
        constraint name_inn_ul_pk
            primary key,
    ogrn                    varchar,
    inn                     varchar,
    nameLong                varchar,
    dopSv                   varchar,
    uprorg_id               bigint
        constraint uprorg_fk_id
            references regul.upr_org
);
create sequence regul.up_tov_in_ul_id_seq;
create table regul.up_tov_in_ul
(
    id                           bigint default nextval('regul.up_tov_in_ul_id_seq'::regclass) not null
        constraint up_tov_in_ul_pk
            primary key,
    uchrdoginvtov_id                    bigint
        constraint uchrdoginvtov_fk_id
            references regul.uchr_dog_inv_tov
);
create sequence regul.reg_in_ul_egrul_type_id_seq;
create table regul.reg_in_ul_egrul_type
(
    id                           bigint default nextval('regul.reg_in_ul_egrul_type_id_seq'::regclass) not null
        constraint reg_in_ul_egrul_type_pk
            primary key,
    oksm                varchar,
    name_country        varchar,
    date                varchar,
    number              varchar,
    name                varchar,
    code_nps_str_reg    varchar,
    adr_country          varchar,
    uprorg_id           bigint
        constraint uprorg_fk_id
            references regul.upr_org,
    zalogderjul_id           bigint
        constraint zalogderjul_fk_id
            references regul.zalog_derj_ul,
    uprzalul_id           bigint
        constraint uprzalul_fk_id
            references regul.upr_zal_ul,
    dovuprultpye_id           bigint
        constraint dovuprultpye_fk_id
            references regul.dov_up_ul_type,
    uchrulin_id           bigint
        constraint uchrulin_fk_id
            references regul.uchr_ul_in,
    uptovinul_id           bigint
        constraint uptovinulfk_id
            references regul.up_tov_in_ul,
    zaimdavul_id                    bigint
        constraint zaimdavul_fk_id
            references regul.zaim_dav_ul
);
create sequence regul.ned_dan_upr_org_id_seq;
create table regul.ned_dan_upr_org
(
    id                           bigint default nextval('regul.ned_dan_upr_org_id_seq'::regclass) not null
        constraint ned_dan_upr_org_pk
            primary key,
    prizn_ned_dan_upr_org        varchar,
    text_ned_dan_upr_org         varchar,
    uprorg_id                    bigint
        constraint uprorg_fk_id
            references regul.upr_org
);
create sequence regul.dolj_fl_id_seq;
create table regul.dolj_fl
(
    id                           bigint default nextval('regul.dolj_fl_id_seq'::regclass) not null
        constraint dolj_fl_pk
            primary key,
    vid_adr_classif              varchar,
    personul_id                  bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.ned_dan_uchr_type_id_seq;
create table regul.ned_dan_uchr_type
(
    id                           bigint default nextval('regul.ned_dan_uchr_type_id_seq'::regclass) not null
        constraint nned_dan_uchr_type_pk
            primary key,
    prizn_ned_dan_uchr       varchar,
    text_ned_dan_uchr        varchar,
    uchrulros_id             bigint
        constraint uchrulros_fk_id
            references regul.uchr_ul_ros,
    uchrulin_id           bigint
        constraint uchrulin_fk_id
            references regul.uchr_ul_in,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl,
    uchrrfsubmo_id           bigint
        constraint uchrrfsubmo_fk_id
            references regul.uchr_rf_sub_mo,
    uchrpif_id           bigint
        constraint uchrpif_fk_id
            references regul.uchr_pif,
    uchrdoginvtov_id           bigint
        constraint uchrdoginvtov_fk_id
            references regul.uchr_dog_inv_tov
);
create sequence regul.resh_sud_type_id_seq;
create table regul.resh_sud_type
(
    id                           bigint default nextval('regul.resh_sud_type_id_seq'::regclass) not null
        constraint resh_sud_type_pk
            primary key,
    name                     varchar,
    number                   varchar,
    date                     varchar,
    neddanuprorg_id          bigint
        constraint neddanuprorg_fk_id
            references regul.ned_dan_upr_org,
    doljfl_id          bigint
        constraint doljfl_fk_id
            references regul.dolj_fl,
    neddanuchrtype_id          bigint
        constraint neddanuchrtype_fk_id
            references regul.ned_dan_uchr_type,
    obremtype_id          bigint
        constraint obremtype_fk_id
            references regul.obrem_type
);
create sequence regul.name_pred_ul_type_id_seq;
create table regul.name_pred_ul_type
(
    id                           bigint default nextval('regul.name_pred_ul_type_id_seq'::regclass) not null
        constraint name_pred_ul_type_pk
            primary key,
    name                     varchar,
    uprorg_id                bigint
        constraint uprorg_fk_id
            references regul.upr_org,
    uptovinul_id           bigint
        constraint uptovinulfk_id
            references regul.up_tov_in_ul
);
create sequence regul.ak_rafp_type_id_seq;
create table regul.ak_rafp_type
(
    id                 bigint default nextval('regul.ak_rafp_type_id_seq'::regclass) not null
        constraint ak_rafp_type_pk
            primary key,
    number             varchar,
    uprorg_id          bigint
        constraint uprorg_fk_id
            references regul.upr_org,
    uptovinul_id           bigint
        constraint uptovinulfk_id
            references regul.up_tov_in_ul
);
create sequence regul.dolj_id_seq;
create table regul.dolj
(
    id                           bigint default nextval('regul.dolj_id_seq'::regclass) not null
        constraint dolj_pk
            primary key,
    ogrn_ip                     varchar,
    vid                         varchar,
    name_vid                    varchar,
    name                        varchar,
    doljfl_id                     bigint
        constraint doljfl_fk_id
            references regul.dolj_fl
);
create sequence regul.ned_dan_dolj_fl_id_seq;
create table regul.ned_dan_dolj_fl
(
    id                           bigint default nextval('regul.ned_dan_dolj_fl_id_seq'::regclass) not null
        constraint ned_dan_dolj_fl_pk
            primary key,
    priznak_ned_dan_dolj_fl          varchar,
    text_ned_dan_dolj_fl             varchar,
    doljfl_id                    bigint
        constraint doljfl_fk_id
            references regul.dolj_fl
);
create sequence regul.dop_sv_pol_dolj_fl_id_seq;
create table regul.dop_sv_pol_dolj_fl
(
    id                           bigint default nextval('regul.dop_sv_pol_dolj_fl_id_seq'::regclass) not null
        constraint dop_sv_pol_dolj_fl_pk
            primary key,
    priznak                  varchar,
    text                     varchar,
    doljfl_id                bigint
        constraint doljfl_fk_id
            references regul.dolj_fl
);
create sequence regul.diskv_id_seq;
create table regul.diskv
(
    id                           bigint default nextval('regul.diskv_id_seq'::regclass) not null
        constraint diskv_pk
            primary key,
    date_nach_diskv                  varchar,
    date_okonch_diskv                varchar,
    date_resh                       varchar,
    doljfl_id                       bigint
        constraint doljfl_fk_id
            references regul.dolj_fl
);
create sequence regul.corp_dogovor_id_seq;
create table regul.corp_dogovor
(
    id                           bigint default nextval('regul.corp_dogovor_id_seq'::regclass) not null
        constraint corp_dogovor_pk
            primary key,
    vid_sved_korp_dog              varchar,
    personul_id                    bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.zapret_rasp_doliami_id_seq;
create table regul.zapret_rasp_doliami
(
    id                           bigint default nextval('regul.zapret_rasp_doliami_id_seq'::regclass) not null
        constraint zapret_rasp_doliami_pk
            primary key,
    text_zapret_rasp_doliami          varchar,
    uchrediteli_id                    bigint
        constraint uchrediteli_fk_id
            references regul.uchrediteli
);
create sequence regul.reg_starie_id_seq;
create table regul.reg_starie
(
    id                           bigint default nextval('regul.reg_starie_id_seq'::regclass) not null
        constraint reg_starie_pk
            primary key,
    reg_nom                  varchar,
    date                     varchar,
    name                     varchar,
    uchrulros_id             bigint
        constraint uchrulros_fk_id
            references regul.uchr_ul_ros
);
create sequence regul.zalog_derj_fl_id_seq;
create table regul.zalog_derj_fl
(
    id                           bigint default nextval('regul.zalog_derj_fl_id_seq'::regclass) not null
        constraint zalog_derj_fl_pk
            primary key,
    obremtype_id                    bigint
        constraint obremtype_fk_id
            references regul.obrem_type
);
create sequence regul.upr_zal_fl_id_seq;
create table regul.upr_zal_fl
(
    id                           bigint default nextval('regul.upr_zal_fl_id_seq'::regclass) not null
        constraint upr_zal_fl_pk
            primary key,
    upravzaltype_id          bigint
        constraint upravzaltype_fk_id
            references regul.uprav_zal_type
);
create sequence regul.lico_upr_nasl_id_seq;
create table regul.lico_upr_nasl
(
    id                           bigint default nextval('regul.lico_upr_nasl_id_seq'::regclass) not null
        constraint lico_upr_nasl_pk
            primary key,
    ogrn_ip                   varchar,
    date_otkr_nasl            varchar,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl
);
create sequence regul.vid_name_uchr_id_seq;
create table regul.vid_name_uchr
(
    id                           bigint default nextval('regul.vid_name_uchr_id_seq'::regclass) not null
        constraint vid_name_uchr_pk
            primary key,
    code                     varchar,
    name                     varchar,
    code_region              varchar,
    name_region              varchar,
    uchrrfsubmo_id           bigint
        constraint uchrrfsubmo_fk_id
            references regul.uchr_rf_sub_mo
);
create sequence regul.fl_osush_pr_id_seq;
create table regul.fl_osush_pr
(
    id                           bigint default nextval('regul.fl_osush_pr_id_seq'::regclass) not null
        constraint fl_osush_pr_pk
            primary key,
    uchrrfsubmo_id                    bigint
        constraint uchrrfsubmo_fk_id
            references regul.uchr_rf_sub_mo
);
create sequence regul.name_pif_id_seq;
create table regul.name_pif
(
    id                           bigint default nextval('regul.name_pif_id_seq'::regclass) not null
        constraint name_pif_pk
            primary key,
    name                     varchar,
    uchrpif_id       bigint
        constraint uchrpif_fk_id
            references regul.uchr_pif
);
create sequence regul.upr_komp_pif_id_seq;
create table regul.upr_komp_pif
(
    id                           bigint default nextval('regul.upr_komp_pif_id_seq'::regclass) not null
        constraint upr_komp_pif_pk
            primary key,
    uprkomppif_id                    bigint
        constraint uprkomppif_fk_id
            references regul.upr_komp_pif
);
create sequence regul.zaim_dav_fl_id_seq;
create table regul.zaim_dav_fl
(
    id                           bigint default nextval('regul.zaim_dav_fl_id_seq'::regclass) not null
        constraint zaim_dav_fl_pk
            primary key,
    konvzaim_id                  bigint
        constraint konvzaim_fk_id
            references regul.konv_zaim
);
create sequence regul.derj_reestr_ao_id_seq;
create table regul.derj_reestr_ao
(
    id                           bigint default nextval('regul.derj_reestr_ao_id_seq'::regclass) not null
        constraint derj_reestr_ao_pk
            primary key,
    personul_id                    bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.okved_egrul_type_id_seq;
create table regul.okved_egrul_type
(
    id                           bigint default nextval('regul.okved_egrul_type_id_seq'::regclass) not null
        constraint okved_egrul_type_pk
            primary key,
    personul_id                    bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.okved_type_id_seq;
create table regul.okved_type
(
    id                           bigint default nextval('regul.okved_type_id_seq'::regclass) not null
        constraint okved_type_pk
            primary key,
    code                     varchar,
    name                     varchar,
    version                  varchar,
    okvedegrultype_id        bigint
        constraint okvedegrultype_fk_id
            references regul.okved_egrul_type
);
create sequence regul.dolia_okved_id_seq;
create table regul.dolia_okved
(
    id                           bigint default nextval('regul.dolia_okved_id_seq'::regclass) not null
        constraint dolia_okved_pk
            primary key,
    dop_sved_okved              varchar,
    percent                     varchar,
    okvedtype_id                bigint
        constraint okvedtype_fk_id
            references regul.okved_type
);
create sequence regul.license_id_seq;
create table regul.license
(
    id                           bigint default nextval('regul.license_id_seq'::regclass) not null
        constraint license_pk
            primary key,
    nomer_erul                varchar,
    number                    varchar,
    date                      varchar,
    date_nach_lic             varchar,
    date_okonch_lic           varchar,
    naim_lic_vid_deyat        varchar,
    lic_org_vid_deyat         varchar,
    personul_id               bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.priost_lic_id_seq;
create table regul.priost_lic
(
    id                           bigint default nextval('regul.priost_lic_id_seq'::regclass) not null
        constraint tablename_pk
            primary key,
    date                     varchar,
    lic_org                  varchar,
    priostlic_id             bigint
        constraint priostlic_fk_id
            references regul.priost_lic
);
create sequence regul.adr_in_egrul_type_id_seq;
create table regul.adr_in_egrul_type
(
    id                           bigint default nextval('regul.adr_in_egrul_type_id_seq'::regclass) not null
        constraint adr_in_egrul_type_pk
            primary key,
    oksm                     varchar,
    name_country             varchar,
    adr_in                   varchar,
    filial_id        bigint
        constraint filial_fk_id
            references regul.filial,
    predst_id        bigint
        constraint predst_fk_id
            references regul.predst
);
create sequence regul.akr_fil_pred_type_id_seq;
create table regul.akr_fil_pred_type
(
    id                           bigint default nextval('regul.akr_fil_pred_type_id_seq'::regclass) not null
        constraint akr_fil_pred_type_pk
            primary key,
    inn                     varchar,
    kpp                     varchar,
    number_rafp             varchar,
    date_akr_rafp           varchar,
    name_pred_ul            varchar,
    name_long               varchar,
    oksm                    varchar,
    name                    varchar,
    number                  varchar,
    code_io_str_Reg         varchar,
    filial_id               bigint
        constraint filial_fk_id
            references regul.filial,
    predst_id               bigint
        constraint predst_fk_id
            references regul.predst
);
create sequence regul.reorg_id_seq;
create table regul.reorg
(
    id                           bigint default nextval('regul.reorg_id_seq'::regclass) not null
        constraint reorg_pk
            primary key,
    personul_id                    bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.status_reorg_id_seq;
create table regul.status_reorg
(
    id                           bigint default nextval('regul.status_reorg_id_seq'::regclass) not null
        constraint status_reorg_pk
            primary key,
    code                     varchar,
    name                     varchar,
    reorg_id                 bigint
        constraint reorg_fk_id
            references regul.reorg
);
create sequence regul.reorg_ul_id_seq;
create table regul.reorg_ul
(
    id                           bigint default nextval('regul.reorg_ul_id_seq'::regclass) not null
        constraint reorg_ul_reorg_pk
            primary key,
    ogrn                     varchar,
    inn                      varchar,
    name_long                varchar,
    sost_ul_posle            varchar,
    reorg_id                 bigint
        constraint reorg_fk_id
            references regul.reorg
);
create sequence regul.predsh_id_seq;
create table regul.predsh
(
    id                           bigint default nextval('regul.predsh_id_seq'::regclass) not null
        constraint predsh_pk
            primary key,
    ogrn                    varchar,
    inn                     varchar,
    name_ul_long            varchar,
    personul_id             bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.preem_id_seq;
create table regul.preem
(
    id                           bigint default nextval('regul.preem_id_seq'::regclass) not null
        constraint preem_pk
            primary key,
    ogrn                       varchar,
    inn                        varchar,
    name_long                  varchar,
    personul_id                bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.ul_sloj_reorg_id_seq;
create table regul.ul_sloj_reorg
(
    id                           bigint default nextval('regul.ul_sloj_reorg_id_seq'::regclass) not null
        constraint ul_sloj_reorg_pk
            primary key,
    ogrn                     varchar,
    inn                      varchar,
    name_long                varchar,
    predsh_id                bigint
        constraint predsh_fk_id
            references regul.predsh,
    preem_id                bigint
        constraint preem_fk_id
            references regul.preem
);
create sequence regul.kfx_prdsh_id_seq;
create table regul.kfx_prdsh
(
    id                           bigint default nextval('regul.kfx_prdsh_id_seq'::regclass) not null
        constraint kfx_prdsh_pk
            primary key,
    ogrn_ip                     varchar,
    personul_id                 bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.kfx_preem_id_seq;
create table regul.kfx_preem
(
    id                           bigint default nextval('regul.kfx_preem_id_seq'::regclass) not null
        constraint kfx_preem_pk
            primary key,
    ogrn_ip                     varchar,
    personul_id          bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.zap_egrul_id_seq;
create table regul.zap_egrul
(
    id                           bigint default nextval('regul.zap_egrul_id_seq'::regclass) not null
        constraint zap_egrul_pk
            primary key,
    id_zap                     varchar,
    grn                        varchar,
    date_zap                   varchar,
    personul_id                bigint
        constraint personul_fk_id
            references regul.person_ul
);
create sequence regul.vid_zap_id_seq;
create table regul.vid_zap
(
    id                           bigint default nextval('regul.vid_zap_id_seq'::regclass) not null
        constraint vid_zap_pk
            primary key,
    code                     varchar,
    name                     varchar,
    zapegrul_id              bigint
        constraint zapegrul_fk_id
            references regul.zap_egrul
);
create sequence regul.reg_org_type_id_seq;
create table regul.reg_org_type
(
    id                           bigint default nextval('regul.reg_org_type_id_seq'::regclass) not null
        constraint reg_org_type_pk
            primary key,
    code                     varchar,
    name                     varchar,
    zapegrul_id              bigint
        constraint zapegrul_fk_id
            references regul.zap_egrul
);
create sequence regul.zayv_fl_id_seq;
create table regul.zayv_fl
(
    id                           bigint default nextval('regul.zayv_fl_id_seq'::regclass) not null
        constraint zayv_fl_pk
            primary key,
    zapegrul_id              bigint
        constraint zapegrul_fk_id
            references regul.zap_egrul
);
create sequence regul.vid_zaiav_id_seq;
create table regul.vid_zaiav
(
    id                           bigint default nextval('regul.vid_zaiav_id_seq'::regclass) not null
        constraint vid_zaiav_pk
            primary key,
    code                     varchar,
    name                     varchar,
    zayvfl_id                bigint
        constraint zayvfl_fk_id
            references regul.zayv_fl
);
create sequence regul.ul_zaiav_fl_id_seq;
create table regul.ul_zaiav_fl
(
    id                           bigint default nextval('regul.ul_zaiav_fl_id_seq'::regclass) not null
        constraint ul_zaiav_fl_pk
            primary key,
    ogrn                        varchar,
    inn                         varchar,
    name_long                   varchar,
    zayvfl_id                   bigint
        constraint zayvfl_fk_id
            references regul.zayv_fl
);
create sequence regul.upr_org_zaiav_fl_id_seq;
create table regul.upr_org_zaiav_fl
(
    id                           bigint default nextval('regul.upr_org_zaiav_fl_id_seq'::regclass) not null
        constraint upr_org_zaiav_fl_pk
            primary key,
    ogrn                          varchar,
    inn                           varchar,
    name_long                     varchar,
    zayvfl_id                     bigint
        constraint zayvfl_fk_id
            references regul.zayv_fl
);
create sequence regul.fl_zaiav_fl_id_seq;
create table regul.fl_zaiav_fl
(
    id                           bigint default nextval('regul.fl_zaiav_fl_id_seq'::regclass) not null
        constraint fl_zaiav_fl_pk
            primary key,
    zayvfl_id                    bigint
        constraint zayvfl_fk_id
            references regul.zayv_fl
);
create sequence regul.fio_inn_id_seq;
create table regul.fio_inn
(
    id                           bigint default nextval('regul.fio_inn_id_seq'::regclass) not null
        constraint fio_inn_pk
            primary key,
    surname                  varchar,
    name                     varchar,
    patronymic               varchar,
    inn_fl                   varchar,
    flzaiavfl_id             bigint
        constraint flzaiavfl_fk_id
            references regul.fl_zaiav_fl
);
create sequence regul.rojd_fl_id_seq;
create table regul.rojd_fl
(
    id                           bigint default nextval('regul.rojd_fl_id_seq'::regclass) not null
        constraint rojd_fl_pk
            primary key,
    date                     varchar,
    mesto                    varchar,
    pr_date                  varchar,
    flzaiavfl_id             bigint
        constraint flzaiavfl_fk_id
            references regul.fl_zaiav_fl
);
create sequence regul.ud_lich_fl_id_seq;
create table regul.ud_lich_fl
(
    id                           bigint default nextval('regul.ud_lich_fl_id_seq'::regclass) not null
        constraint ud_lich_fl_pk
            primary key,
    code                     varchar,
    name                     varchar,
    ser_nom_doc              varchar,
    date_doc                 varchar,
    vid_doc                  varchar,
    code_vid_doc             varchar,
    flzaiavfl_id             bigint
        constraint flzaiavfl_fk_id
            references regul.fl_zaiav_fl
);
create sequence regul.pred_doc_id_seq;
create table regul.pred_doc
(
    id                           bigint default nextval('regul.pred_doc_id_seq'::regclass) not null
        constraint pred_doc_pk
            primary key,
    name                     varchar,
    number                   varchar,
    date                     varchar,
    zapegrul_id              bigint
        constraint zapegrul_fk_id
            references regul.zap_egrul
);
create sequence regul.svid_id_seq;
create table regul.svid
(
    id                           bigint default nextval('regul.svid_id_seq'::regclass) not null
        constraint svid_pk
            primary key,
    serial                     varchar,
    number                     varchar,
    date_svid                  varchar,
    zapegrul_id         bigint
        constraint zapegrul_fk_id
            references regul.zap_egrul
);
create sequence regul.status_zap_id_seq;
create table regul.status_zap
(
    id                           bigint default nextval('regul.status_zap_id_seq'::regclass) not null
        constraint status_zap_pk
            primary key,
    zapegrul_id                    bigint
        constraint zapegrul_fk_id
            references regul.zap_egrul
);
create sequence regul.id_grn_date_type_id_seq;
create table regul.id_grn_date_type
(
    id                           bigint default nextval('regul.id_grn_date_type_id_seq'::regclass) not null
        constraint id_grn_date_type_pk
            primary key,
    id_zap                     varchar,
    grn                        varchar,
    date_zap                   varchar,
    statuszap_id               bigint
        constraint statuszap_fk_id
            references regul.status_zap,
    zapegrul_id               bigint
        constraint zapegrul_fk_id
            references regul.zap_egrul
);
create sequence regul.dov_upr_gl_type_id_seq;
create table regul.dov_upr_gl_type
(
    id                           bigint default nextval('regul.dov_upr_gl_type_id_seq'::regclass) not null
        constraint dov_upr_gl_type_pk
            primary key,
    ogrnip                     varchar,
    uchrulros_id               bigint
        constraint uchrulros_fk_id
            references regul.uchr_ul_ros,
    uchrulin_id               bigint
        constraint uchrulin_fk_id
            references regul.uchr_ul_in,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl
);
create sequence regul.name_inn_dov_upr_id_seq;
create table regul.name_inn_dov_upr
(
    id                           bigint default nextval('regul.name_inn_dov_upr_id_seq'::regclass) not null
        constraint name_inn_dov_upr_pk
            primary key,
    ogrn                    varchar,
    inn                     varchar,
    name_long               varchar,
    dovuprultpye_id         bigint
        constraint dovuprultpye_fk_id
            references regul.dov_up_ul_type
);
create sequence regul.not_ud_dog_zal_id_seq;
create table regul.not_ud_dog_zal
(
    id                           bigint default nextval('regul.not_ud_dog_zal_id_seq'::regclass) not null
        constraint not_ud_dog_zal_pk
            primary key,
    number                     varchar,
    date                       varchar,
    zalogderjfl_id             bigint
        constraint zalogderjfl_fk_id
            references regul.zalog_derj_fl,
    zalogderjul_id             bigint
        constraint zalogderjul_fk_id
            references regul.zalog_derj_ul
);
create sequence regul.pol_fl_type_id_seq;
create table regul.pol_fl_type
(
    id                           bigint default nextval('regul.pol_fl_type_id_seq'::regclass) not null
        constraint pol_fl_type_pk
            primary key,
    pol                     varchar,
    zalogderjfl_id           bigint
        constraint zalogderjfl_fk_id
            references regul.zalog_derj_fl,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl,
    licouprnasl_id           bigint
        constraint licouprnasl_fk_id
            references regul.lico_upr_nasl,
    zaimdavfl_id                    bigint
        constraint zaimdavfl_fk_id
            references regul.zaim_dav_fl,
    doljfl_id                    bigint
        constraint doljfl_fk_id
            references regul.dolj_fl
);
create sequence regul.obem_prav_type_id_seq;
create table regul.obem_prav_type
(
    id                           bigint default nextval('regul.obem_prav_type_id_seq'::regclass) not null
        constraint obem_prav_type_pk
            primary key,
    obemPrav                     varchar,
    uchrulros_id                 bigint
        constraint uchrulros_fk_id
            references regul.uchr_ul_ros,
    uchrulin_id                 bigint
        constraint uchrulin_fk_id
            references regul.uchr_ul_in,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl,
    uchrrfsubmo_id           bigint
        constraint uchrrfsubmo_fk_id
            references regul.uchr_rf_sub_mo,
    uchrpif_id           bigint
        constraint uchrpif_fk_id
            references regul.uchr_pif,
    uchrdoginvtov_id           bigint
        constraint uchrdoginvtov_fk_id
            references regul.uchr_dog_inv_tov
);
create sequence regul.dolia_ust_kap_egrul_type_id_seq;
create table regul.dolia_ust_kap_egrul_type
(
    id                           bigint default nextval('regul.dolia_ust_kap_egrul_type_id_seq'::regclass) not null
        constraint dolia_ust_kap_egrul_type_pk
            primary key,
    name                   varchar,
    uchrulros_id         bigint
        constraint uchrulros_fk_id
            references regul.uchr_ul_ros,
    uchrulin_id           bigint
        constraint uchrulin_fk_id
            references regul.uchr_ul_in,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl,
    uchrrfsubmo_id           bigint
        constraint uchrrfsubmo_fk_id
            references regul.uchr_rf_sub_mo,
    uchrpif_id           bigint
        constraint uchrpif_fk_id
            references regul.uchr_pif,
    uchrdoginvtov_id           bigint
        constraint uchrdoginvtov_fk_id
            references regul.uchr_dog_inv_tov
);
create sequence regul.razmer_doli_type_id_seq;
create table regul.razmer_doli_type
(
    id                           bigint default nextval('regul.razmer_doli_type_id_seq'::regclass) not null
        constraint razmer_doli_type_pk
            primary key,
    percent                     varchar,
    drob_desyat                 varchar,
    doliaustkapegrultype_id           bigint
        constraint doliaustkapegrultype_fk_id
            references regul.dolia_ust_kap_egrul_type,
    doliaooo_id           bigint
        constraint doliaooo_fk_id
            references regul.dolia_ooo
);
create sequence regul.ul_egrul_type_id_seq;
create table regul.ul_egrul_type
(
    id                           bigint default nextval('regul.ul_egrul_type_id_seq'::regclass) not null
        constraint ul_egrul_tpye_pk
            primary key,
    ogrn                    varchar,
    inn                     varchar,
    name                    varchar,
    uchrulros_id            bigint
        constraint uuchrulros_fk_id
            references regul.uchr_ul_ros,
    zalogderjul_id            bigint
        constraint zalogderjul_fk_id
            references regul.zalog_derj_ul,
    uprzalul_id            bigint
        constraint uprzalul_fk_id
            references regul.upr_zal_ul,
    uchrulin_id            bigint
        constraint uchrulin_fk_id
            references regul.uchr_ul_in,
    orgosushpr_id           bigint
        constraint orgosushpr_fk_id
            references regul.org_osush_pr,
    uchrdoginvtov_id           bigint
        constraint uchrdoginvtov_fk_id
            references regul.uchr_dog_inv_tov,
    uptovinul_id           bigint
        constraint uptovinulfk_id
            references regul.up_tov_in_ul,
    zaimdavul_id                    bigint
        constraint zaimdavul_fk_id
            references regul.zaim_dav_ul,
    konvzaim_id                    bigint
        constraint konvzaim_fk_id
            references regul.konv_zaim,
    derjreestrao_id           bigint
        constraint derjreestrao_fk_id
            references regul.derj_reestr_ao

);
create sequence regul.adr_mj_fias_egrul_type_id_seq;
create table regul.adr_mj_fias_egrul_type
(
    id                           bigint default nextval('regul.adr_mj_fias_egrul_type_id_seq'::regclass) not null
        constraint adr_mj_fias_egrul_type_pk
            primary key,
    id_nom                    varchar,
    index                     varchar,
    region                    varchar,
    name_region               varchar,
    doljfl_id                 bigint
        constraint doljfl_fk_id
            references regul.dolj_fl,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl
);
create sequence regul.adr_mj_rf_egrul_type_id_seq;
create table regul.adr_mj_rf_egrul_type
(
    id                           bigint default nextval('regul.adr_mj_rf_egrul_type_id_seq'::regclass) not null
        constraint adr_mj_rf_egrul_type_pk
            primary key,
    index               varchar,
    code                varchar,
    code_cladr           varchar,
    house               varchar,
    korpus              varchar,
    kvart               varchar,
    doljfl_id           bigint
        constraint doljfl_fk_id
            references regul.dolj_fl,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl
);
create sequence regul.ulica_type_id_seq;
create table regul.ulica_type
(
    id                           bigint default nextval('regul.ulica_type_id_seq'::regclass) not null
        constraint ulica_type_pk
            primary key,
    type                     varchar,
    name                     varchar,
    adrmjrfegrultype_id       bigint
        constraint adrmjrfegrultype_fk_id
            references regul.adr_mj_rf_egrul_type,
    adrrfegrultype_id       bigint
        constraint adrrfegrultype_fk_id
            references regul.adr_rf_egrul_type
);
create sequence regul.nasel_punkt_type_id_seq;
create table regul.nasel_punkt_type
(
    id                           bigint default nextval('regul.nasel_punkt_type_id_seq'::regclass) not null
        constraint nasel_punkt_type_pk
            primary key,
    type                     varchar,
    name                     varchar,
    adrmjrfegrultype_id      bigint
        constraint adrmjrfegrultype_fk_id
            references regul.adr_mj_rf_egrul_type,
    adrrfegrultype_id       bigint
        constraint adrrfegrultype_fk_id
            references regul.adr_rf_egrul_type,
    reshizmmn_id               bigint
        constraint reshizmmn_fk_id
            references regul.resh_izm_mn
);
create sequence regul.gorod_type_id_seq;
create table regul.gorod_type
(
    id                           bigint default nextval('regul.gorod_type_id_seq'::regclass) not null
        constraint gorod_type_pk
            primary key,
    type                     varchar,
    name                     varchar,
    adrmjrfegrultype_id      bigint
        constraint adrmjrfegrultype_fk_id
            references regul.adr_mj_rf_egrul_type,
    adrrfegrultype_id        bigint
        constraint adrrfegrultype_fk_id
            references regul.adr_rf_egrul_type,
    reshizmmn_id               bigint
        constraint reshizmmn_fk_id
            references regul.resh_izm_mn
);
create sequence regul.raion_type_id_seq;
create table regul.raion_type
(
    id                           bigint default nextval('regul.raion_type_id_seq'::regclass) not null
        constraint raion_type_pk
            primary key,
    type                     varchar,
    name                     varchar,
    adrmjrfegrultype_id      bigint
        constraint adrmjrfegrultype_fk_id
            references regul.adr_mj_rf_egrul_type,
    adrrfegrultype_id        bigint
        constraint adrrfegrultype_fk_id
            references regul.adr_rf_egrul_type,
    reshizmmn_id               bigint
        constraint reshizmmn_fk_id
            references regul.resh_izm_mn
);
create sequence regul.region_type_id_seq;
create table regul.region_type
(
    id                           bigint default nextval('regul.region_type_id_seq'::regclass) not null
        constraint region_type_pk
            primary key,
    type_region                     varchar,
    name_region                     varchar,
    adrmjrfegrultype_id             bigint
        constraint adrmjrfegrultype_fk_id
            references regul.adr_mj_rf_egrul_type,
    adrrfegrultype_id               bigint
        constraint adrrfegrultype_fk_id
            references regul.adr_rf_egrul_type,
    reshizmmn_id               bigint
        constraint reshizmmn_fk_id
            references regul.resh_izm_mn
);
create sequence regul.ud_lichn_egrul_type_id_seq;
create table regul.ud_lichn_egrul_type
(
    id                           bigint default nextval('regul.ud_lichn_egrul_type_id_seq'::regclass) not null
        constraint ud_lichn_egrul_type_pk
            primary key,
    code_vid_doc             varchar,
    name                     varchar,
    ser_nom_doc              varchar,
    date_doc                 varchar,
    vid_doc                  varchar,
    code_podrazd_doc         varchar,
    doljfl_id                bigint
        constraint doljfl_fk_id
            references regul.dolj_fl,
    zalogderjfl_id                bigint
        constraint zalogderjfl_fk_id
            references regul.zalog_derj_fl,
    dovupfltype_id                bigint
        constraint dovupfltype_fk_id
            references regul.dov_upr_gl_type,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl,
    licouprnasl_id           bigint
        constraint licouprnasl_fk_id
            references regul.lico_upr_nasl,
    flosushpr_id           bigint
        constraint flosushpr_fk_id
            references regul.fl_osush_pr,
    zaimdavfl_id                    bigint
        constraint zaimdavfl_fk_id
            references regul.zaim_dav_fl
);
create sequence regul.grajd_type_id_seq;
create table regul.grajd_type
(
    id                           bigint default nextval('regul.grajd_type_id_seq'::regclass) not null
        constraint grajd_type_pk
            primary key,
    code                     varchar,
    oksm                     varchar,
    name_country             varchar,
    doljfl_id             bigint
        constraint doljfl_fk_id
            references regul.dolj_fl,
    zalogderjfl_id             bigint
        constraint zalogderjfl_fk_id
            references regul.zalog_derj_fl,
    dovupfltype_id             bigint
        constraint dovupfltype_fk_id
            references regul.dov_upr_gl_type,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl,
    licouprnasl_id           bigint
        constraint licouprnasl_fk_id
            references regul.lico_upr_nasl,
    zaimdavfl_id                    bigint
        constraint zaimdavfl_fk_id
            references regul.zaim_dav_fl
);
create sequence regul.zap_rojd_id_seq;
create table regul.zap_rojd
(
    id                           bigint default nextval('regul.zap_rojd_id_seq'::regclass) not null
        constraint zap_rojd_pk
            primary key,
    number                     varchar,
    doljfl_id                  bigint
        constraint doljfl_fk_id
            references regul.dolj_fl,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl
);
create sequence regul.rojd_egrul_fl_type_id_seq;
create table regul.rojd_egrul_fl_type
(
    id                           bigint default nextval('regul.rojd_egrul_fl_type_id_seq'::regclass) not null
        constraint rojd_egrul_fl_type_pk
            primary key,
    date                     varchar,
    mesto                    varchar,
    pr_date                  varchar,
    doljfl_id                bigint
        constraint doljfl_fk_id
            references regul.dolj_fl,
    zalogderjfl_id                bigint
        constraint zalogderjfl_fk_id
            references regul.zalog_derj_fl,
    dovupfltype_id                bigint
        constraint dovupfltype_fk_id
            references regul.dov_upr_gl_type,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl,
    licouprnasl_id           bigint
        constraint licouprnasl_fk_id
            references regul.lico_upr_nasl,
    flosushpr_id           bigint
        constraint flosushpr_fk_id
            references regul.fl_osush_pr,
    zaimdavfl_id                    bigint
        constraint zaimdavfl_fk_id
            references regul.zaim_dav_fl
);
create sequence regul.pol_fl_id_seq;
create table regul.pol_fl
(
    id                           bigint default nextval('regul.pol_fl_id_seq'::regclass) not null
        constraint pol_fl_pk
            primary key,
    pol                     varchar,
    doljfl_id      bigint
        constraint doljfl_fk_id
            references regul.dolj_fl
);
create sequence regul.fio_zags_id_seq;
create table regul.fio_zags
(
    id                           bigint default nextval('regul.fio_zags_id_seq'::regclass) not null
        constraint fio_zags_pk
            primary key,
    surname                     varchar,
    name                        varchar,
    patronymic                  varchar,
    doljfl_id                   bigint
        constraint doljfl_fk_id
            references regul.dolj_fl,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl
);
create sequence regul.fl_egrul_type_id_seq;
create table regul.fl_egrul_type
(
    id                           bigint default nextval('regul.fl_egrul_type_id_seq'::regclass) not null
        constraint fl_egrul_type_pk
            primary key,
    surname                  varchar,
    name                     varchar,
    patronymic               varchar,
    innfl                    varchar,
    doljfl_id                bigint
        constraint doljfl_fk_id
            references regul.dolj_fl,
    zalogderjfl_id                bigint
        constraint zalogderjfl_fk_id
            references regul.zalog_derj_fl,
    notuddogzal_id                bigint
        constraint notuddogzal_fk_id
            references regul.not_ud_dog_zal,
    upravzaltype_id                bigint
        constraint upravzaltype_fk_id
            references regul.uprav_zal_type,
    dovupfltype_id                bigint
        constraint dovupfltype_fk_id
            references regul.dov_upr_gl_type,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl,
    licouprnasl_id           bigint
        constraint licouprnasl_fk_id
            references regul.lico_upr_nasl,
    flosushpr_id           bigint
        constraint flosushpr_fk_id
            references regul.fl_osush_pr,
    zaimdavfl_id                    bigint
        constraint zaimdavfl_fk_id
            references regul.zaim_dav_fl,
    kfxprdsh_id                    bigint
        constraint kfxprdsh_fk_id
            references regul.kfx_prdsh,
    kfxpreem_id                    bigint
        constraint kfxpreem_fk_id
            references regul.kfx_preem
);
create sequence regul.drob_type_id_seq;
create table regul.drob_type
(
    id                           bigint default nextval('regul.drob_type_id_seq'::regclass) not null
        constraint drob_type_pk
            primary key,
    numerator                    varchar,
    denominator                  varchar,
    ustkap_id                    bigint
        constraint ustkap_fk_id
            references regul.ust_kap,
    doliaustkapegrultype_id                    bigint
        constraint doliaustkapegrultype_fk_id
            references regul.dolia_ust_kap_egrul_type,
    razmerdolitype_id                    bigint
        constraint razmerdolitype_fk_id
            references regul.razmer_doli_type,
    doliaooo_id           bigint
        constraint doliaooo_fk_id
            references regul.dolia_ooo
);
create sequence regul.org_doc_id_seq;
create table regul.org_doc
(
    id                           bigint default nextval('regul.org_doc_id_seq'::regclass) not null
        constraint org_doc_pk
            primary key,
    ogr_doc_sv                     varchar,
    statusul_id                    bigint
        constraint statusul_fk_id
            references regul.status_ul,
    uprorg_id                    bigint
        constraint uprorg_fk_id
            references regul.upr_org,
    doljfl_id                    bigint
        constraint doljfl_fk_id
            references regul.dolj_fl,
    uchrulros_id                    bigint
        constraint uchrulros_fk_id
            references regul.uchr_ul_ros,
    uchrulin_id           bigint
        constraint uchrulin_fk_id
            references regul.uchr_ul_in,
    uchrfl_id           bigint
        constraint uchrfl_fk_id
            references regul.uchr_fl,
    uchrrfsubmo_id           bigint
        constraint uchrrfsubmo_fk_id
            references regul.uchr_rf_sub_mo,
    uchrpif_id           bigint
        constraint uchrpif_fk_id
            references regul.uchr_pif,
    uchrdoginvtov_id           bigint
        constraint uchrdoginvtov_fk_id
            references regul.uchr_dog_inv_tov,
    doliaooo_id           bigint
        constraint doliaooo_fk_id
            references regul.dolia_ooo,
    derjreestrao_id           bigint
        constraint derjreestrao_fk_id
            references regul.derj_reestr_ao,
    license_id           bigint
        constraint license_fk_id
            references regul.license,
    filial_id           bigint
        constraint filial_fk_id
            references regul.filial,
    predst_id           bigint
        constraint predst_fk_id
            references regul.predst,
    reorg_id                 bigint
        constraint reorg_fk_id
            references regul.reorg,
    predsh_id                 bigint
        constraint predsh_fk_id
            references regul.predsh,
    preem_id                 bigint
        constraint preem_fk_id
            references regul.preem,
    zapegrul_id                 bigint
        constraint zapegrul_fk_id
            references regul.zap_egrul
);
create sequence regul.vid_name_code_type_id_seq;
create table regul.vid_name_code_type
(
    id                           bigint default nextval('regul.vid_name_code_type_id_seq'::regclass) not null
        constraint vid_name_code_type_pk
            primary key,
    vid_kod                  varchar,
    name                     varchar,
    mnul_id                  bigint
        constraint mnul_fk_id
            references regul.mnul,
    adrfiasegrultype_id     bigint
        constraint adrfiasegrultype_fk_id
            references regul.adr_fias_egrul_type,
    adrmjfiasegrultype_id     bigint
        constraint adrmjfiasegrultype_fk_id
            references regul.adr_mj_fias_egrul_type,
    filial_id           bigint
        constraint filial_fk_id
            references regul.filial
);
create sequence regul.vid_name_p_type_id_seq;
create table regul.vid_name_p_type
(
    id                           bigint default nextval('regul.vid_name_p_type_id_seq'::regclass) not null
        constraint vid_name_p_type_pk
            primary key,
    vid                     varchar,
    name                    varchar,
    mnul_id                 bigint
        constraint mnul_fk_id
            references regul.mnul,
    adrfiasegrultype_id     bigint
        constraint adrfiasegrultype_fk_id
            references regul.adr_fias_egrul_type,
    adrmjfiasegrultype_id     bigint
        constraint adrmjfiasegrultype_fk_id
            references regul.adr_mj_fias_egrul_type
);
create sequence regul.type_name_p_type_id_seq;
create table regul.type_name_p_type
(
    id                           bigint default nextval('regul.type_name_p_type_id_seq'::regclass) not null
        constraint type_name_p_type_pk
            primary key,
    type_element                     varchar,
    name_element                     varchar,
    adrfiasegrultype_id                    bigint
        constraint adrfiasegrultype_fk_id
            references regul.adr_fias_egrul_type,
    adrmjfiasegrultype_id     bigint
        constraint adrmjfiasegrultype_fk_id
            references regul.adr_mj_fias_egrul_type
);
create sequence regul.number_p_type_id_seq;
create table regul.number_p_type
(
    id                           bigint default nextval('regul.number_p_type_id_seq'::regclass) not null
        constraint number_p_type_pk
            primary key,
    type                     varchar,
    number                   varchar,
    adrfiasegrultype_id      bigint
        constraint adrfiasegrultype_fk_id
            references regul.adr_fias_egrul_type,
    adrmjfiasegrultype_id     bigint
        constraint adrmjfiasegrultype_fk_id
            references regul.adr_mj_fias_egrul_type
);
-- create sequence regul.grn_date_id_seq;
-- create table regul.grn_date
-- (
--     id                           bigint default nextval('regul.grn_date_id_seq'::regclass) not null
--         constraint grn_date_pk
--             primary key,
--     grn                     varchar,
--     date_write              varchar,
--     nameul_id              bigint
--         constraint nameul_fk_id
--             references regul.name_ul,
--     shortnameultype_id      bigint
--         constraint shortnameultype_fk_id
--             references regul.short_name_ul_type,
--     nameulkodokin_id      bigint
--         constraint nameulkodokin_fk_id
--             references regul.name_ul_kod_okin,
--     namepolntype_id      bigint
--         constraint namepolntype_fk_id
--             references regul.name_poln_type,
--     namesokrtype_id      bigint
--         constraint namesokrtype_fk_id
--             references regul.name_sokr_type,
--     mnul_id      bigint
--         constraint mnul_fk_id
--             references regul.mnul,
--     adrfiasegrultype_id      bigint
--         constraint adrfiasegrultype_fk_id
--         references regul.adr_fias_egrul_type,
--     adrrfegrultype_id      bigint
--         constraint adrrfegrultype_fk_id
--             references regul.adr_rf_egrul_type,
--     nedadrul_id      bigint
--         constraint nedadrul_fk_id
--             references regul.ned_adr_ul,
--     reshizmmn_id               bigint
--         constraint reshizmmn_fk_id
--             references regul.resh_izm_mn,
--     emailul_id               bigint
--         constraint emailul_fk_id
--             references regul.email_ul,
--     obrul_id               bigint
--         constraint obrul_fk_id
--             references regul.obr_ul,
--     regorg_id               bigint
--         constraint regorg_fk_id
--             references regul.reg_org,
--     orgdoc_id               bigint
--         constraint orgdoc_fk_id
--             references regul.org_doc,
--     statusul_id               bigint
--         constraint statusul_fk_id
--             references regul.status_ul,
--     prekrul_id               bigint
--         constraint prekrul_fk_id
--             references regul.prek_ul,
--     typeustav_id               bigint
--         constraint typeustav_fk_id
--             references regul.type_ustav,
--     uchetno_id               bigint
--         constraint uchetno_fk_id
--             references regul.uchet_ul,
--     regpf_id               bigint
--         constraint regpf_fk_id
--             references regul.reg_pf,
--     regfss_id               bigint
--         constraint regfss_fk_id
--             references regul.reg_fss,
--     ustkap_id               bigint
--         constraint ustkap_fk_id
--             references regul.ust_kap,
--     svedumuk_id               bigint
--         constraint svedumuk_fk_id
--             references regul.sved_umuk,
--     polnomochniy_id               bigint
--         constraint polnomochniy_fk_id
--             references regul.polnomochniy,
--     uprorg_id                    bigint
--         constraint uprorg_fk_id
--             references regul.upr_org,
--     nameinnul_id                    bigint
--         constraint nameinnul_fk_id
--             references regul.name_inn_ul,
--     reginulegrultype_id                    bigint
--         constraint reginulegrultype_fk_id
--             references regul.reg_in_ul_egrul_type,
--     neddanuprorg_id                    bigint
--         constraint neddanuprorg_fk_id
--             references regul.ned_dan_upr_org,
--     namepredultype_id                    bigint
--         constraint namepredultype_fk_id
--             references regul.name_pred_ul_type,
--     adrafptype_id                    bigint
--         constraint adrafptype_fk_id
--             references regul.ak_rafp_type,
--     doljfl_id                    bigint
--         constraint doljfl_fk_id
--             references regul.dolj_fl,
--     flegrultype_id                    bigint
--         constraint flegrultype_fk_id
--             references regul.fl_egrul_type,
--     fiozagstype_id                    bigint
--         constraint fiozagstype_fk_id
--             references regul.fio_zags,
--     dolj_id                    bigint
--         constraint dolj_fk_id
--             references regul.dolj,
--     neddandoljfl_id                    bigint
--         constraint neddandoljfl_fk_id
--             references regul.ned_dan_dolj_fl,
--     dopsvpoldoljfl_id                    bigint
--         constraint dopsvpoldoljfl_fk_id
--             references regul.dop_sv_pol_dolj_fl,
--     rojdegrultype_id                    bigint
--         constraint rojdegrultype_fk_id
--             references regul.rojd_egrul_fl_type,
--     grajdtype_id                    bigint
--         constraint grajdtype_fk_id
--             references regul.grajd_type,
--     udlichegrultype_id                    bigint
--         constraint udlichegrultype_fk_id
--             references regul.ud_lichn_egrul_type,
--     adrmjrfegrultype_id                    bigint
--         constraint adrmjrfegrultype_fk_id
--             references regul.adr_mj_rf_egrul_type,
--     adrmjfiasegrultype_id     bigint
--         constraint adrmjfiasegrultype_fk_id
--             references regul.adr_mj_fias_egrul_type,
--     diskv_id     bigint
--         constraint diskv_fk_id
--             references regul.diskv,
--     corpdogovor_id     bigint
--         constraint corpdogovor_fk_id
--             references regul.corp_dogovor,
--     zapretraspdoloami_id     bigint
--         constraint zapretraspdoloami_fk_id
--             references regul.zapret_rasp_doliami,
--     uchrulros_id                    bigint
--         constraint uchrulros_fk_id
--             references regul.uchr_ul_ros,
--     ulegrultype_id                    bigint
--         constraint ulegrultype_fk_id
--             references regul.ul_egrul_type,
--     regstarie_id                    bigint
--         constraint regstarie_fk_id
--             references regul.reg_starie,
--     neddanuchrtype_id          bigint
--         constraint neddanuchrtype_fk_id
--             references regul.ned_dan_uchr_type,
--     doliaustkapegrultype_id          bigint
--         constraint doliaustkapegrultype_fk_id
--             references regul.dolia_ust_kap_egrul_type,
--     obempravtype_id          bigint
--         constraint obempravtype_fk_id
--             references regul.obem_prav_type,
--     obremtype_id          bigint
--         constraint obremtype_fk_id
--             references regul.obrem_type,
--     polfltype_id          bigint
--         constraint polfltype_fk_id
--             references regul.pol_fl_type,
--     zalogderjul_id          bigint
--         constraint zalogderjul_fk_id
--             references regul.zalog_derj_ul,
--     upravzaltype_id          bigint
--         constraint upravzaltype_fk_id
--             references regul.uprav_zal_type,
--     dovuprultpye_id          bigint
--         constraint dovuprultpye_fk_id
--             references regul.dov_up_ul_type,
--     nameinndovupr_id          bigint
--         constraint nameinndovupr_fk_id
--             references regul.name_inn_dov_upr,
--     dovupfltype_id          bigint
--         constraint dovupfltype_fk_id
--             references regul.dov_upr_gl_type,
--     uchrulin_id           bigint
--         constraint uchrulin_fk_id
--             references regul.uchr_ul_in,
--     uchrfl_id           bigint
--         constraint uchrfl_fk_id
--             references regul.uchr_fl,
--     licouprnasl_id           bigint
--         constraint licouprnasl_fk_id
--             references regul.lico_upr_nasl,
--     uchrrfsubmo_id           bigint
--         constraint uchrrfsubmo_fk_id
--             references regul.uchr_rf_sub_mo,
--     orgosushpr_id           bigint
--         constraint orgosushpr_fk_id
--             references regul.org_osush_pr,
--     uchrpif_id           bigint
--         constraint uchrpif_fk_id
--             references regul.uchr_pif,
--     namepif_id           bigint
--         constraint namepif_fk_id
--             references regul.name_pif,
--     uchrdoginvtov_id           bigint
--         constraint uchrdoginvtov_fk_id
--             references regul.uchr_dog_inv_tov,
--     inprdoginvtov_id                    bigint
--         constraint inprdoginvtov_fk_id
--             references regul.in_pr_dog_inv_tov,
--     konvzaim_id                    bigint
--         constraint konvzaim_fk_id
--             references regul.konv_zaim,
--     zaimdavfl_id                    bigint
--         constraint zaimdavfl_fk_id
--             references regul.zaim_dav_fl,
--     zaimdavul_id                    bigint
--         constraint zaimdavul_fk_id
--             references regul.zaim_dav_ul,
--     doliaooo_id           bigint
--         constraint doliaooo_fk_id
--             references regul.dolia_ooo,
--     derjreestrao_id           bigint
--         constraint derjreestrao_fk_id
--             references regul.derj_reestr_ao,
--     okvedtype_id           bigint
--         constraint okvedtype_fk_id
--             references regul.okved_type,
--     doliaokved_id           bigint
--         constraint doliaokved_fk_id
--             references regul.dolia_okved,
--     priostlic_id           bigint
--         constraint priostlic_fk_id
--             references regul.priost_lic,
--     license_id           bigint
--         constraint license_fk_id
--             references regul.license,
--     filial_id           bigint
--         constraint filial_fk_id
--             references regul.filial,
--     adrinegrultype_id           bigint
--         constraint adrinegrultype_fk_id
--             references regul.adr_in_egrul_type,
--     akrfilpredtype_id           bigint
--         constraint akrfilpredtype_fk_id
--             references regul.akr_fil_pred_type,
--     uchetnopodrazdtype_id       bigint
--         constraint uchetnopodrazdtype_fk_id
--             references regul.uchet_no_podrazd_type,
--     predst_id           bigint
--         constraint predst_fk_id
--             references regul.predst,
--     reorgul_id           bigint
--         constraint reorgul_fk_id
--             references regul.reorg_ul,
--     reorg_id                 bigint
--         constraint reorg_fk_id
--             references regul.reorg,
--     predsh_id                 bigint
--         constraint predsh_fk_id
--             references regul.predsh,
--     preem_id                bigint
--         constraint preem_fk_id
--             references regul.preem,
--     fioinn_id                bigint
--         constraint fioinn_fk_id
--             references regul.fio_inn,
--     rojdfl_id                bigint
--         constraint rojdfl_fk_id
--             references regul.rojd_fl,
--     udlichfl_id                bigint
--         constraint udlichfl_fk_id
--             references regul.ud_lich_fl,
--     svid_id                bigint
--         constraint svid_fk_id
--             references regul.svid
-- );