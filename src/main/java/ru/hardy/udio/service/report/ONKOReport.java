package ru.hardy.udio.service.report;

import org.springframework.stereotype.Service;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.report.AgeLimit;
import ru.hardy.udio.domain.report.DateInterval;
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

    public int getCountOnkoSpec(AgeLimit ageLimit, String monthBeg, String monthEnd, String yearBeg,
                                String yearEnd, String spec, String usl_ok, Statement statement){
        int count = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        List<DNGet> dnGetsPeople = null;
        switch (ageLimit){
            case older_18 ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() >= 18)
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
                            .toList()
                            .stream()
                            .distinct()
                            .toList();
        }

        for (DNGet dnGet : dnGetsPeople) {
            try {
                ResultSet resultSet = statement.executeQuery("select count(distinct(cbp.pac_fam, cbp.pac_im, cbp.pac_ot, cbp.pac_dr, cb.enp)) " +
                                "from tf_proc.tp_casebill cb " +
                                "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                                "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                                "left join nsi_med.med_help_conditions mhc on mhc.id = cb.usl_ok " +
                                "left join nsi.specialities spec on spec.id = sl.prvs " +
                                "left join nsi_med.med_mkb10 mkb on mkb.id = sl.ds1 " +
                                "where cb.date_1 between " +
                                "to_date('" + dateFormat.format(serviceUtil.transformDate(monthBeg, yearBeg, DateInterval.minDate))
                                + "', 'dd.mm.yyyy') and " +
                                "to_date('" + dateFormat.format(serviceUtil.transformDate(monthEnd, yearEnd, DateInterval.maxDate))
                                + "', 'dd.mm.yyyy') " +
                                " and upper(cbp.pac_fam) = '" + dnGet.getPeople().getFam().toUpperCase() +
                                "' and upper(cbp.pac_im)  = '" + dnGet.getPeople().getIm().toUpperCase() +
                                "' and upper(cbp.pac_ot) = '" + dnGet.getPeople().getOt().toUpperCase() +
                                "' and cbp.pac_dr  = to_date('" + dateFormat.format(dnGet.getPeople().getDr()) + "', 'DD.MM.YYYY') " +
                                "and cb.enp  = '" + dnGet.getPeople().getEnp() + "'" +
                                " and mhc.code in (" + serviceUtil.transformStringArrayForBars(usl_ok) +
                                ") and (substring(mkb.mkb_code, 1, 1) = 'C' or substring(mkb.mkb_code, 1, 2) = 'D0')");

                if (resultSet.next()) {
                    count = count + resultSet.getInt(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return count;
    }
}
