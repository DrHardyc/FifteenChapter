package ru.hardy.udio.service.report;

import org.springframework.stereotype.Service;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.report.AgeLimit;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.service.ServiceUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ONKOReport {
    private final List<DNGet> dnGets;
    private final ServiceUtil serviceUtil = new ServiceUtil();

    public ONKOReport(List<DNGet> dnGets){
        this.dnGets = dnGets;
    }

    public int getCountOnko(AgeLimit ageLimit){
        return switch (ageLimit){
            case older_18 ->
                    dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() >= 18)
                            .toList()
                            .stream()
                            .distinct()
                            .toList()
                            .size();
            case younger_18 ->
                    dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() < 18)
                            .toList()
                            .stream()
                            .distinct()
                            .toList()
                            .size();
            case all ->
                    dnGets
                            .stream()
                            .distinct()
                            .toList()
                            .size();
        };
    }

    public int getCountOnkoSpec(AgeLimit ageLimit, String month, String year, String spec, String usl_ok){
        int count = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getBars();

        List<DNGet> dnGetsPeople = null;
        switch (ageLimit){
            case older_18 ->
                dnGetsPeople = dnGets
                        .stream()
                        .filter(c -> c.getPeople().getAge() >= 18
                         && spec.contains(c.getSpecialization().toString()))
                        .toList()
                        .stream()
                        .distinct()
                        .toList();
            case younger_18 ->
                dnGetsPeople = dnGets
                        .stream()
                        .filter(c -> c.getPeople().getAge() < 18)
                        .toList()
                        .stream()
                        .distinct()
                        .toList();
            case all ->
                dnGetsPeople = dnGets
                        .stream()
                        .distinct()
                        .toList();
        };

        for (DNGet dnGet : dnGetsPeople) {
            try {
                ResultSet resultSet = statement.executeQuery("select count(*) from tf_proc.tp_casebill cb " +
                        "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                        "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                        "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                        "left join nsi_med.med_help_conditions mhc on mhc.id = cb.usl_ok " +
                        "left join nsi.specialities spec on spec.id = sl.prvs " +
                        "where cbb.month = " + month +
                        " and cbb.year = " + year +
                        " and cbp.pac_fam = '" + dnGet.getPeople().getFam() +
                        "' and cbp.pac_im  = '" + dnGet.getPeople().getIm() +
                        "' and cbp.pac_ot = '" + dnGet.getPeople().getOt() +
                        "' and cbp.pac_dr  = to_date('" + dateFormat.format(dnGet.getPeople().getDr()) + "', 'DD.MM.YYYY') " +
                        "and cb.enp  = '" + dnGet.getPeople().getEnp() +
                        "' and mhc.code in (" + serviceUtil.transformStringArrayForBars(usl_ok) +
                        ") and spec.code in (" + serviceUtil.transformStringArrayForBars(spec) + ")");

                while (resultSet.next()) {
                    count = count + resultSet.getInt(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }



        return count;
    }
}
