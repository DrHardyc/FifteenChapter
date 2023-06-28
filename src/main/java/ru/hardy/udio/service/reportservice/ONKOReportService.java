package ru.hardy.udio.service.reportservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.report.AgeLimit;
import ru.hardy.udio.domain.report.DateInterval;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.UtilService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ONKOReportService {

    @Autowired
    private PeopleService peopleService;
    private final UtilService utilService = new UtilService();

    public int getCountOnko(AgeLimit ageLimit){
        return switch (ageLimit){
            case older_18 ->
                    peopleService.getDNOnkoReport()
                            .stream()
                            .filter(c -> c.getAge() >= 18)
                            .toList()
                            .size();
            case younger_18 ->
                    peopleService.getDNOnkoReport()
                            .stream()
                            .filter(c -> c.getAge() < 18)
                            .toList()
                            .size();
            case all ->
                    peopleService.getDNOnkoReport()
                            .stream()
                            .distinct()
                            .toList()
                            .size();
        };
    }

    public int getCountOnkoSpec(AgeLimit ageLimit, String monthBeg, String monthEnd, String yearBeg,
                                String yearEnd, String spec, List<String> usl_ok, Statement statement){
        int count = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        List<People> dnGetsPeople = null;
        switch (ageLimit){
            case older_18 ->
                    dnGetsPeople = peopleService.getDNOnkoReport()
                            .stream()
                            .filter(c -> c.getAge() >= 18)
                            .toList();
            case younger_18 ->
                    dnGetsPeople = peopleService.getDNOnkoReport()
                            .stream()
                            .filter(c -> c.getAge() < 18)
                            .toList();
            case all ->
                    dnGetsPeople = peopleService.getDNOnkoReport()
                            .stream()
                            .toList();
        }

        for (People people : dnGetsPeople) {
            try {
                ResultSet resultSet = statement.executeQuery("select count(distinct(cbp.pac_fam, cbp.pac_im, cbp.pac_ot, cbp.pac_dr, cb.enp)) " +
                                "from tf_proc.tp_casebill cb " +
                                "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                                "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                                "left join nsi_med.med_help_conditions mhc on mhc.id = cb.usl_ok " +
                                "left join nsi.specialities spec on spec.id = sl.prvs " +
                                "left join nsi_med.med_mkb10 mkb on mkb.id = sl.ds1 " +
                                "where cb.date_1 between " +
                                "to_date('" + dateFormat.format(utilService.transformDate(monthBeg, yearBeg, DateInterval.minDate))
                                + "', 'dd.mm.yyyy') and " +
                                "to_date('" + dateFormat.format(utilService.transformDate(monthEnd, yearEnd, DateInterval.maxDate))
                                + "', 'dd.mm.yyyy') " +
                                " and upper(cbp.pac_fam) = '" + people.getFam().toUpperCase() +
                                "' and upper(cbp.pac_im)  = '" + people.getIm().toUpperCase() +
                                "' and upper(cbp.pac_ot) = '" + people.getOt().toUpperCase() +
                                "' and cbp.pac_dr  = to_date('" + dateFormat.format(people.getDr()) + "', 'DD.MM.YYYY') " +
                                "and cb.enp  = '" + people.getEnp() + "'" +
                                " and mhc.code in (" + utilService.transformStringArrayForBars(usl_ok) +
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
