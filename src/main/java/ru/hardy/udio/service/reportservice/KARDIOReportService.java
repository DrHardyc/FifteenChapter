package ru.hardy.udio.service.reportservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.report.DateInterval;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.UtilService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class KARDIOReportService {

    @Autowired
    private PeopleService peopleService;
    private final UtilService utilService = new UtilService();

    public int getCountKARDIOBars(String monthBeg, String monthEnd, String yearBeg, String yearEnd, Statement statement){
        int count = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


        List<People> dnGetsPeople = peopleService.getDNKardioReport();

        for (People people : dnGetsPeople) {
            try {
                ResultSet resultSet = statement.executeQuery("select count(*) from tf_proc.tp_casebill cb " +
                        "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                        "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                        "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                        "left join nsi_med.med_mkb10 mkb on mkb.id = cb.ds1 " +
                        "left join nsi_med.med_purp_visit mpv on mpv.id = sl.p_cel " +
                        "where cb.date_1 between " +
                        "to_date('" + dateFormat.format(utilService.transformDate(monthBeg, yearBeg, DateInterval.minDate))
                            + "', 'dd.mm.yyyy') and " +
                        "to_date('" + dateFormat.format(utilService.transformDate(monthEnd, yearEnd, DateInterval.maxDate))
                            + "', 'dd.mm.yyyy')" +
                        " and upper(cbp.pac_fam) = '" + people.getFam().toUpperCase() +
                        "' and upper(cbp.pac_im)  = '" + people.getIm().toUpperCase() +
                        "' and upper(cbp.pac_ot) = '" + people.getOt().toUpperCase() +
                        "' and cbp.pac_dr  = to_date('" + dateFormat.format(people.getDr()) + "', 'DD.MM.YYYY') " +
                        "and cb.enp  = '" + people.getEnp() + "'" +
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
        return peopleService.getDNKardioReport().size();
    }
}
