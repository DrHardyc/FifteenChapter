package ru.hardy.udio.service.report;

import org.springframework.stereotype.Service;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.service.ServiceUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class KARDIOReport {

    private final List<DNGet> dnGets;
    private final ServiceUtil serviceUtil = new ServiceUtil();

    public KARDIOReport(List<DNGet> dnGets){
        this.dnGets = dnGets;
    }

    public int getCountKARDIOBars(String month, String year){
        int count = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getBars();

        List<DNGet> dnGetsPeople = dnGets.stream()
            .filter(c -> c.getPeople().getAge() >= 18 && c.getDiag().charAt(0) == 'I')
            .toList()
            .stream()
            .distinct()
            .toList();

        for (DNGet dnGet : dnGetsPeople) {
            try {
                ResultSet resultSet = statement.executeQuery("select count(*) from tf_proc.tp_casebill cb " +
                        "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                        "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                        "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                        "left join nsi_med.med_mkb10 mkb on mkb.id = cb.ds1 " +
                        "left join nsi_med.med_purp_visit mpv on mpv.id = sl.p_cel " +
                        "where cbb.month = " + month +
                        " and cbb.year = " + year +
                        " and cbp.pac_fam = '" + dnGet.getPeople().getFam() +
                        "' and cbp.pac_im  = '" + dnGet.getPeople().getIm() +
                        "' and cbp.pac_ot = '" + dnGet.getPeople().getOt() +
                        "' and cbp.pac_dr  = to_date('" + dateFormat.format(dnGet.getPeople().getDr()) + "', 'DD.MM.YYYY') " +
                        "and cb.enp  = '" + dnGet.getPeople().getEnp() + "'" +
                        "and substring(mkb.mkb_code, 1, 1) = 'I' " +
                        "and mpv.code = '1.3'");

                while (resultSet.next()) {
                    count = count + resultSet.getInt(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return count;
    }

    public int getCountKARDIO(){
        return dnGets.stream()
                .filter(c -> c.getPeople().getAge() >= 18 && c.getDiag().charAt(0) == 'I')
                .toList()
                .stream()
                .distinct()
                .toList().size();
    }
}
