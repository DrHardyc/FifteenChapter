package ru.hardy.udio.service.regulservice;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.regul.grid.RegULUI;
import ru.hardy.udio.domain.regul.grid.RegULUIMapper;

import java.util.List;

@Service
public class RegULUIService {

    public static List<RegULUI> getAll() {
        RowMapper<RegULUI> regULUIMapper = new RegULUIMapper();
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(DBJDBCConfig.getUDIODataSource());
        return jdbcTemplate.query("select ul.inn \"inn\", ul.ogrn \"ogrn\", ul.reg_n_foms \"regnum\", " +
                "       case when snut.name_sokr = '' then nu.full_name " +
                "            else snut.name_sokr " +
                "           end \"name\", ssu.name \"status\", max(ul.date_beg) \"date\", " +
                "            ul.kpp \"kpp\", " +
                "            aret.index || ', ' || rt.name || ', ' || " +
                "            case when (coalesce(rt.name, '0') = '0' or rt.name = '') then '' else rt.name || ', ' end || " +
                "            case when (coalesce(gt.name, npt.name, '0') = '0' or (gt.name = '' and npt.name = '')) then '' else coalesce(gt.name, npt.name) || ', ' end || " +
                "            ut.name || ', ' || aret.dom || " +
                "            case when (coalesce(aret.korpus, '0') = '0' or aret.korpus = '') then '' else ', корп. ' || aret.korpus end || " +
                "            case when (coalesce(aret.kvart, '0') = '0' or aret.kvart = '') then '' else ', кв. ' || aret.kvart end \"address\" " +
                "from regul.person_ul ul " +
                "         inner join regul.name_ul nu on ul.id = nu.personul_id " +
                "         inner join regul.short_name_ul_type snut on nu.id = snut.nameul_id " +
                "         inner join regul.status_ul su on ul.id = su.personul_id " +
                "         inner join regul.status_st_ul ssu on su.id = ssu.statusul_id " +
                "         left join regul.address_ul au on ul.id = au.personul_id " +
                "         left join regul.adr_rf_egrul_type aret on au.id = aret.addressul_id " +
                "         left join regul.raion_type rt on aret.id = rt.adrrfegrultype_id " +
                "         left join regul.region_type r on aret.id = r.adrrfegrultype_id " +
                "         left join regul.gorod_type gt on aret.id = gt.adrrfegrultype_id " +
                "         left join regul.ulica_type ut on aret.id = ut.adrrfegrultype_id " +
                "         left join regul.nasel_punkt_type npt on aret.id = npt.adrrfegrultype_id " +
                "group by ul.inn, ul.ogrn, ul.reg_n_foms, snut.name_sokr, nu.full_name, ssu.name, ul.kpp, " +
                "         aret.index || ', ' || rt.name || ', ' || " +
                "         case when (coalesce(rt.name, '0') = '0' or rt.name = '') then '' else rt.name || ', ' end || " +
                "         case when (coalesce(gt.name, npt.name, '0') = '0' or (gt.name = '' and npt.name = '')) then '' else coalesce(gt.name, npt.name) || ', ' end || " +
                "         ut.name || ', ' || aret.dom || " +
                "         case when (coalesce(aret.korpus, '0') = '0' or aret.korpus = '') then '' else ', корп. ' || aret.korpus end || " +
                "         case when (coalesce(aret.kvart, '0') = '0' or aret.kvart = '') then '' else ', кв. ' || aret.kvart end " +
                "union all " +
                "select ip.inn_fl, ip.ogrn_ip, ip.reg_n_foms, fi.surname || ' ' || fi.name || ' ' || fi.patronymic, " +
                "       ssu.name, max(ip.date_beg), '' \"kpp\", " +
                "       artv.index || ', ' || r.name_region || ', ' || " +
                "        case when (coalesce(rt.name, '0') = '0' or rt.name = '') then '' else rt.name || ', ' end || " +
                "        case when (coalesce(gt.name, npt.name, '0') = '0' or (gt.name = '' and npt.name = '')) then '' else coalesce(gt.name, npt.name) || ', ' end || " +
                "        ut.name || ', ' || artv.dom || " +
                "        case when (coalesce(artv.korp, '0') = '0' or artv.korp = '') then '' else ', корп. ' || artv.korp end || " +
                "        case when (coalesce(artv.kvart, '0') = '0' or artv.kvart = '') then '' else ', кв. ' || artv.kvart end \"address\" " +
                "from regul.person_ip ip " +
                "         inner join regul.fl f on ip.id = f.personip_id " +
                "         inner join regul.fio_ip fi on f.id = fi.fl_id " +
                "         inner join regul.status_ip si on f.personip_id = si.personip_id " +
                "         inner join regul.status_st_ul ssu on si.id = ssu.statusip_id " +
                "         left join regul.adr_mj am on ip.id = am.personip_id " +
                "         left join regul.adr_rf_type_vip artv on am.id = artv.adrmj_id " +
                "         left join regul.gorod_type gt on artv.id = gt.adrrftypevip_id " +
                "         left join regul.nasel_punkt_type npt on artv.id = npt.adrrftypevip_id " +
                "         left join regul.raion_type rt on artv.id = rt.adrrftypevip_id " +
                "         left join regul.region_type r on artv.id = r.adrrftypevip_id " +
                "         left join regul.ulica_type ut on artv.id = ut.adrrftypevip_id " +
                "group by ip.inn_fl, ip.ogrn_ip, ip.reg_n_foms, fi.surname || ' ' || fi.name || ' ' || fi.patronymic, " +
                "         ssu.name, " +
                "         artv.index || ', ' || r.name_region || ', ' || " +
                "         case when (coalesce(rt.name, '0') = '0' or rt.name = '') then '' else rt.name || ', ' end || " +
                "         case when (coalesce(gt.name, npt.name, '0') = '0' or (gt.name = '' and npt.name = '')) then '' else coalesce(gt.name, npt.name) || ', ' end || " +
                "         ut.name || ', ' || artv.dom || " +
                "         case when (coalesce(artv.korp, '0') = '0' or artv.korp = '') then '' else ', корп. ' || artv.korp end || " +
                "         case when (coalesce(artv.kvart, '0') = '0' or artv.kvart = '') then '' else ', кв. ' || artv.kvart end", regULUIMapper);
    }
}
