package ru.hardy.udio.service.reportservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.report.VisitType;
import ru.hardy.udio.domain.report.WorkingAgeSex;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.SexService;
import ru.hardy.udio.service.UtilService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

/**
 * @version {@index }
 * @see DNOnkoTherapiReportService
 */
@Service
public class DNOnkoTherapiReportService {

    private final UtilService utilService = new UtilService();

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private SexService sexService;

    // Подлежащие/состоящие на дн
    public int getCountDN(WorkingAgeSex workingAgeSex, List<String> diags, String spec, int status, List<String> intervals){
        return Objects.requireNonNull(getCountReport(workingAgeSex, status, diags, spec, intervals)).size();
    }

    //Впервые выявленные
    public int getCountDNPr(WorkingAgeSex workingAgeSex, List<String> diags, Statement statement, String spec, int status, List<String> intervals){
        int count = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        List<People> dnGetsPeople = getCountReport(workingAgeSex, status, diags, spec, intervals);

        assert dnGetsPeople != null;
        for (People people : dnGetsPeople){
            try {
                ResultSet resultSet = statement.executeQuery("select count(*) from tf_proc.tp_casebill cb " +
                        "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                        "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                        "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                        "left join nsi_med.med_mkb10 mkb on mkb.id = cb.ds1 " +
                        "where (sl.ds1_pr = 1 or sl.pr_d_n = 1) " +
                        " and upper(cbp.pac_fam) = '" + people.getSurname().toUpperCase() +
                        "' and upper(cbp.pac_im)  = '" + people.getName().toUpperCase() +
                        "' and upper(cbp.pac_ot) = '" + people.getPatronymic().toUpperCase() +
                        "' and cbp.pac_dr  = to_date('" + dateFormat.format(people.getDateBirth()) + "', 'DD.MM.YYYY') " +
                        " and cb.enp  = '" + people.getEnp() + "'" +
                        " and mkb.mkb_code in (" +  utilService.transformStringArrayForBars(diags) + ")");
                while (resultSet.next()) {
                    count = count + resultSet.getInt(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return count;
    }

    //Проинформированные
    public int getCountCalling(WorkingAgeSex workingAgeSex, List<String> diags, String spec){

        return switch (workingAgeSex){
            case M_16_60 ->
                    peopleService.getDNTherapistReportCall(16, 60, diags, 1, spec).size();
            case M_older_60 ->
                    peopleService.getDNTherapistReportCall(60, diags, 1, spec).size();
            case W_16_55 ->
                    peopleService.getDNTherapistReportCall(16, 55, diags, 2, spec).size();
            case W_older_55 ->
                    peopleService.getDNTherapistReportCall(55, diags, 2, spec).size();
            case all ->
                    peopleService.getDNTherapistReportCall(diags, spec).size();
        };
    }

    //Визиты поликлиника/дом
    public int getCountVisit(VisitType visitType,
                             WorkingAgeSex workingAgeSex, List<String> diags, Statement statement){

        String strVisitType = "";
        String strVisitTypeSearchValue = "";
        switch (visitType){
            case home -> {
                strVisitType = "sum(crsle.visit_hs::numeric)";
                strVisitTypeSearchValue = "crsle.visit_hs";
            }
            case clinic -> {
                strVisitType = "sum(crsle.visit_ds::numeric)";
                strVisitTypeSearchValue = "crsle.visit_ds";
            }
            case peopleHome -> {
                strVisitType = "count(*)";
                strVisitTypeSearchValue = "crsle.visit_hs";
            }
            case peopleClinic ->{
                strVisitType = "count(*)";
                strVisitTypeSearchValue = "crsle.visit_ds";
            }
        }

        switch (workingAgeSex){
            case M_16_60 -> {
                try {
                    ResultSet resultSet = statement.executeQuery("select " + strVisitType + " from tf_proc.tp_casebill cb " +
                            "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                            "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                            "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                            "inner join tf_proc.tp_casebill_sl_ext crsle on crsle.xid = sl.id " +
                            "left join nsi_med.med_mkb10 mkb on mkb.id = sl.ds1 " +
                            "where coalesce(" + strVisitTypeSearchValue + ", '0') <> '0' " +
                            "and date_part('year', age(cb.date_2, cbp.pac_dr)) between 16 and 60 " +
                            "and cbp.pac_w = 1712801 " +
                            "and mkb.mkb_code in (" + utilService.transformStringArrayForBars(diags) +
                            ") and sl.dn in (1, 2, 6)");
                    if (resultSet.next())
                        return resultSet.getInt(1);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
            case M_older_60 -> {
                try {
                    ResultSet resultSet = statement.executeQuery("select " + strVisitType + " from tf_proc.tp_casebill cb " +
                            "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                            "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                            "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                            "inner join tf_proc.tp_casebill_sl_ext crsle on crsle.xid = sl.id " +
                            "left join nsi_med.med_mkb10 mkb on mkb.id = sl.ds1 " +
                            "where coalesce(" + strVisitTypeSearchValue + ", '0') <> '0' " +
                            "and date_part('year', age(cb.date_2, cbp.pac_dr)) > 60 " +
                            "and cbp.pac_w = 1712801 " +
                            "and mkb.mkb_code in (" + utilService.transformStringArrayForBars(diags) +
                            ") and sl.dn in (1, 2, 6)");
                    if (resultSet.next())
                        return resultSet.getInt(1);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case W_16_55 -> {
                try {
                    ResultSet resultSet = statement.executeQuery("select " + strVisitType + " from tf_proc.tp_casebill cb " +
                            "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                            "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                            "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                            "inner join tf_proc.tp_casebill_sl_ext crsle on crsle.xid = sl.id " +
                            "left join nsi_med.med_mkb10 mkb on mkb.id = sl.ds1 " +
                            "where coalesce(" + strVisitTypeSearchValue + ", '0') <> '0' " +
                            "and date_part('year', age(cb.date_2, cbp.pac_dr)) between 16 and 55 " +
                            "and cbp.pac_w = 1712821 " +
                            "and mkb.mkb_code in (" + utilService.transformStringArrayForBars(diags) +
                            ") and sl.dn in (1, 2, 6)");
                    if (resultSet.next())
                        return resultSet.getInt(1);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case W_older_55 -> {
                try {
                    ResultSet resultSet = statement.executeQuery("select " + strVisitType + " from tf_proc.tp_casebill cb " +
                            "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                            "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                            "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                            "inner join tf_proc.tp_casebill_sl_ext crsle on crsle.xid = sl.id " +
                            "left join nsi_med.med_mkb10 mkb on mkb.id = sl.ds1 " +
                            "where coalesce(" + strVisitTypeSearchValue + ", '0') <> '0' " +
                            "and date_part('year', age(cb.date_2, cbp.pac_dr)) > 55 " +
                            "and cbp.pac_w = 1712821 " +
                            "and mkb.mkb_code in (" + utilService.transformStringArrayForBars(diags) +
                            ") and sl.dn in (1, 2, 6)");
                    if (resultSet.next())
                        return resultSet.getInt(1);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case all -> {
                try {
                    ResultSet resultSet = statement.executeQuery("select " + strVisitType + " from tf_proc.tp_casebill cb " +
                            "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                            "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                            "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                            "inner join tf_proc.tp_casebill_sl_ext crsle on crsle.xid = sl.id " +
                            "left join nsi_med.med_mkb10 mkb on mkb.id = sl.ds1 " +
                            "where coalesce(" + strVisitTypeSearchValue + ", '0') <> '0' " +
                            "and mkb.mkb_code in (" + utilService.transformStringArrayForBars(diags) +
                            ") and sl.dn in (1, 2, 6)");
                    if (resultSet.next())
                        return resultSet.getInt(1);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return -1;
    }

    //Госпитализация
    public int getCountHospitalize(WorkingAgeSex workingAgeSex, List<String> diags, Statement statement, String spec){
        int count = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        List<People> dnGetsPeople = null;
        switch (workingAgeSex){
            case M_16_60 ->
                    dnGetsPeople = peopleService.getDNTherapistReport(16, 60, diags, 1, spec);
            case M_older_60 ->
                    dnGetsPeople = peopleService.getDNTherapistReport(60, diags, 1, spec);
            case W_16_55 ->
                    dnGetsPeople = peopleService.getDNTherapistReport(16, 55, diags, 2, spec);
            case W_older_55 ->
                    dnGetsPeople = peopleService.getDNTherapistReport(55, diags, 2, spec);
            case all ->
                    dnGetsPeople = peopleService.getDNTherapistReport(diags, spec);
        }

        for (People people : dnGetsPeople) {
            try {
                ResultSet resultSet = statement.executeQuery("select count(*) from tf_proc.tp_casebill cb " +
                        "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                        "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                        "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                        "inner join tf_proc.tp_casebill_sl_ext crsle on crsle.xid = sl.id " +
                        "left join nsi_med.med_mkb10 mkb on mkb.id = sl.ds1 " +
                        "left join nsi_med.med_help_conditions mhc on mhc.id = cb.usl_ok " +
                        "where cb.sump > 0" +
                        " and upper(cbp.pac_fam) = '" + people.getSurname().toUpperCase() +
                        "' and upper(cbp.pac_im) = '" + people.getName().toUpperCase() +
                        "' and upper(cbp.pac_ot) = '" + people.getPatronymic().toUpperCase() +
                        "' and cbp.pac_dr = to_date('" + dateFormat.format(people.getDateBirth()) + "', 'dd.mm.yyyy')" +
                        " and cb.enp = '" + people.getEnp() +
                        "' and mhc.code = '1'");
                while (resultSet.next()) {
                    count = count + resultSet.getInt(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return count;
    }


    //Смертность
    public int getCountDeath(WorkingAgeSex workingAgeSex, List<String> diags, Statement statement, String spec){
        int count = 0;

        List<People> dnGetsPeople = null;
        switch (workingAgeSex){
            case M_16_60 ->
                    dnGetsPeople = peopleService.getDNTherapistReport(16, 60, diags, 1, spec);
            case M_older_60 ->
                    dnGetsPeople = peopleService.getDNTherapistReport(60, diags, 1, spec);
            case W_16_55 ->
                    dnGetsPeople = peopleService.getDNTherapistReport(16, 55, diags, 2, spec);
            case W_older_55 ->
                    dnGetsPeople = peopleService.getDNTherapistReport(55, diags, 2, spec);
            case all ->
                    dnGetsPeople = peopleService.getDNTherapistReport(diags, spec);
        }

        for (People people : dnGetsPeople) {
            try {
                ResultSet resultSet = statement.executeQuery("select count(*) from PEOPLE p where " +
                        "p.FAM = '" + people.getSurname() + "' " +
                        "and p.IM = '" + people.getName() + "' " +
                        "and p.OT = '" + people.getPatronymic() + "' " +
                        "and p.dr = PARSE('" + people.getDateBirth() + "' as date) " +
                        "and p.enp = '" + people.getEnp() + "'");
//                        "and p.ds between PARSE('" + dateFormat.format(utilService.transformDate(monthBeg, yearBeg, DateInterval.minDate)) + "' as date) " +
//                        "and PARSE('" + dateFormat.format(utilService.transformDate(monthEnd, yearEnd, DateInterval.maxDate)) + "' as date)");
                while (resultSet.next()) {
                    count = count + resultSet.getInt(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return count;
    }

    //Скорая помощь
    public int getCountAmbulance(WorkingAgeSex workingAgeSex, List<String> diags, Statement statement, String spec){
        int count = 0;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        List<People> dnGetsPeople = null;
        switch (workingAgeSex){
            case M_16_60 ->
                    dnGetsPeople = peopleService.getDNTherapistReport(16, 60, diags, 1, spec);
            case M_older_60 ->
                    dnGetsPeople = peopleService.getDNTherapistReport(60, diags, 1, spec);
            case W_16_55 ->
                    dnGetsPeople = peopleService.getDNTherapistReport(16, 55, diags, 2, spec);
            case W_older_55 ->
                    dnGetsPeople = peopleService.getDNTherapistReport(55, diags, 2, spec);
            case all ->
                    dnGetsPeople = peopleService.getDNTherapistReport(diags, spec);
        }

        assert dnGetsPeople != null;
        for (People people : dnGetsPeople){
            try {
                ResultSet resultSet = statement.executeQuery("select count(*) from tf_proc.tp_casebill cb " +
                        "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                        "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                        "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                        "left join nsi_med.med_purp_visit mpv on mpv.id = sl.p_cel " +
                        "where mpv.code = '1.1' " +
                        " and cbp.pac_fam = '" + people.getSurname() +
                        "' and cbp.pac_im  = '" + people.getName() +
                        "' and cbp.pac_ot = '" + people.getPatronymic() +
                        "' and cbp.pac_dr  = to_date('" + dateFormat.format(people.getDateBirth()) + "', 'DD.MM.YYYY') " +
                        "and cb.enp  = '" + people.getEnp() + "'");
                while (resultSet.next()) {
                    count = count + resultSet.getInt(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return count;
    }

    //Инвалиды
    public int getCountInv(WorkingAgeSex workingAgeSex, List<String> diags, String spec){
        return switch (workingAgeSex){
            case M_16_60 ->
                peopleService.getDNTherapistReportInv(16, 60, diags, 1, spec).size();
            case M_older_60 ->
                    peopleService.getDNTherapistReportInv(60, diags, 1, spec).size();
            case W_16_55 ->
                    peopleService.getDNTherapistReportInv(16, 55, diags, 2, spec).size();
            case W_older_55 ->
                    peopleService.getDNTherapistReportInv(55, diags, 2, spec).size();
            case all ->
                    peopleService.getDNTherapistReportInv(diags, spec).size();
        };
    }

    private List<People> getCountReport(WorkingAgeSex workingAgeSex, int vzyt, List<String> diags, String spec, List<String> intervals){
        switch (workingAgeSex){
            case M_16_60 -> {
                if (vzyt == 1)
                    return peopleService.getDNTherapistReport(16, 60, diags, 1, spec, intervals);
                else return peopleService.getDNTherapistReport(16, 60, diags, 1, spec);
            }
            case M_older_60 -> {
                if (vzyt == 1)
                    return peopleService.getDNTherapistReport(60, diags, 1, spec, intervals);
                else return peopleService.getDNTherapistReport(60, diags, 1, spec);
            }
            case W_16_55 -> {
                if (vzyt == 1)
                    return peopleService.getDNTherapistReport(16, 55, diags, 2, spec, intervals);
                else return peopleService.getDNTherapistReport(16, 55, diags, 2, spec);
            }
            case W_older_55 ->{
                if (vzyt == 1)
                    return peopleService.getDNTherapistReport(56, diags, 2, spec, intervals);
                else return peopleService.getDNTherapistReport(56, diags, 2, spec);
            }
            case all ->{
                if (vzyt == 1)
                    return peopleService.getDNTherapistReport(diags, spec, intervals);
                else return peopleService.getDNTherapistReport(diags, spec);
            }
        };
        return null;
    }
}
