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
                "       else snut.name_sokr " +
                "       end \"name\", ssu.name \"status\", max(ul.date_beg) \"date\" " +
                "from regul.person_ul ul " +
                "    inner join regul.name_ul nu on ul.id = nu.personul_id " +
                "    inner join regul.short_name_ul_type snut on nu.id = snut.nameul_id " +
                "    inner join regul.status_ul su on ul.id = su.personul_id " +
                "    inner join regul.status_st_ul ssu on su.id = ssu.statusul_id " +
                "group by ul.inn, ul.ogrn, ul.reg_n_foms, snut.name_sokr, nu.full_name, ssu.name " +
                "union all " +
                "select ip.inn_fl, ip.ogrn_ip, ip.reg_n_foms, fi.surname || ' ' || fi.name || ' ' || fi.patronymic, coalesce(sp.name, ssu.name), max(ip.date_beg) " +
                "from regul.person_ip ip " +
                "    inner join regul.fl f on ip.id = f.personip_id " +
                "    inner join regul.fio_ip fi on f.id = fi.fl_id " +
                "    inner join regul.status_ip si on f.personip_id = si.personip_id " +
                "    inner join regul.status_st_ul ssu on si.id = ssu.statusip_id " +
                "    inner join regul.prekrash p on ip.id = p.personip_id " +
                "    inner join regul.status_pr sp on p.id = sp.prekrash_id " +
                "group by ip.inn_fl, ip.ogrn_ip, ip.reg_n_foms, fi.surname || ' ' || fi.name || ' ' || fi.patronymic, coalesce(sp.name, ssu.name)", regULUIMapper);
    }
}
