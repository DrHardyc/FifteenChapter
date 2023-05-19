package ru.hardy.udio.service.report;

import org.springframework.stereotype.Service;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.report.DNTherapistReport.DateInterval;
import ru.hardy.udio.domain.report.DNTherapistReport.VisitType;
import ru.hardy.udio.domain.report.DNTherapistReport.WorkingAgeSex;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.service.ServiceUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class DNTherapistReportService {

    private final List<DNGet> dnGets;
    private final ServiceUtil serviceUtil = new ServiceUtil();

    public DNTherapistReportService(List<DNGet> dnGets){
        this.dnGets = dnGets;
    }

    // Подлежащие/состоящие на дн
    public int getCountDN(WorkingAgeSex workingAgeSex, String diags){
        return switch (workingAgeSex){
            case M_16_60 ->
                 dnGets
                    .stream()
                    .filter(c -> c.getPeople().getAge() >= 16
                            && c.getPeople().getAge() <= 60
                            && c.getPeopleSex().equals("1")
                            && diags.contains(transformDiag(c.getDiag())))
                    .toList()
                    .stream()
                    .distinct()
                    .toList()
                    .size();

            case M_older_60 ->
                dnGets
                    .stream()
                    .filter(c -> c.getPeople().getAge() > 60
                            && c.getPeopleSex().equals("1")
                            && diags.contains(transformDiag(c.getDiag())))
                    .toList().stream().distinct().toList().size();

            case W_16_55 ->
                dnGets
                    .stream()
                    .filter(c -> c.getPeople().getAge() >= 16
                            && c.getPeople().getAge() <= 55
                            && c.getPeopleSex().equals("2")
                            && diags.contains(transformDiag(c.getDiag())))
                    .toList()
                    .stream()
                    .distinct()
                    .toList()
                    .size();

            case W_older_55 ->
                dnGets
                    .stream()
                    .filter(c -> c.getPeople().getAge() > 55
                            && c.getPeopleSex().equals("2")
                            && diags.contains(transformDiag(c.getDiag())))
                    .toList()
                    .stream()
                    .distinct()
                    .toList()
                    .size();
            case all ->
                dnGets
                    .stream()
                    .filter(c -> diags.contains(transformDiag(c.getDiag())))
                    .toList()
                    .stream()
                    .distinct()
                    .toList()
                    .size();
        };
    }

    //Впервые выявленные
    public int getCountDNPr(WorkingAgeSex workingAgeSex, String diags, String month, String year){
        int count = 0;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getBars();
        List<DNGet> dnGetsPeople = null;
        switch (workingAgeSex){
            case M_16_60 ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() >= 16
                                    && c.getPeople().getAge() <= 60
                                    && c.getPeopleSex().equals("1")
                                    && diags.contains(transformDiag(c.getDiag()))
                                    && c.getDate_1().after(serviceUtil.transformDate(month, year, DateInterval.minDate))
                                    && c.getDate_1().before(serviceUtil.transformDate(month, year, DateInterval.maxDate)))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();

            case M_older_60 ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() > 60
                                    && c.getPeopleSex().equals("1")
                                    && diags.contains(transformDiag(c.getDiag()))
                                    && c.getDate_1().after(serviceUtil.transformDate(month, year, DateInterval.minDate))
                                    && c.getDate_1().before(serviceUtil.transformDate(month, year, DateInterval.maxDate)))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();

            case W_16_55 ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() >= 16
                                    && c.getPeople().getAge() <= 55
                                    && c.getPeopleSex().equals("2")
                                    && diags.contains(transformDiag(c.getDiag()))
                                    && c.getDate_1().after(serviceUtil.transformDate(month, year, DateInterval.minDate))
                                    && c.getDate_1().before(serviceUtil.transformDate(month, year, DateInterval.maxDate)))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();

            case W_older_55 ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() > 55
                                    && c.getPeopleSex().equals("2")
                                    && diags.contains(transformDiag(c.getDiag()))
                                    && c.getDate_1().after(serviceUtil.transformDate(month, year, DateInterval.minDate))
                                    && c.getDate_1().before(serviceUtil.transformDate(month, year, DateInterval.maxDate)))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();
            case all ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> diags.contains(transformDiag(c.getDiag()))
                                    && c.getDate_1().after(serviceUtil.transformDate(month, year, DateInterval.minDate))
                                    && c.getDate_1().before(serviceUtil.transformDate(month, year, DateInterval.maxDate)))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();
        }

        assert dnGetsPeople != null;
        for (DNGet dnGet : dnGetsPeople){
            try {
                ResultSet resultSet = statement.executeQuery("select count(*) from tf_proc.tp_casebill cb " +
                        "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                        "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                        "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                        "where sl.ds1_pr = 1 " +
                        "and cbb.month = " + month +
                        "and cbb.year = " + year +
                        " and cbp.pac_fam = '" + dnGet.getPeople().getFam() +
                        "' and cbp.pac_im  = '" + dnGet.getPeople().getIm() +
                        "' and cbp.pac_ot = '" + dnGet.getPeople().getOt() +
                        "' and cbp.pac_dr  = to_date('" + dateFormat.format(dnGet.getPeople().getDr()) + "', 'DD.MM.YYYY') " +
                        "and cb.enp  = '" + dnGet.getPeople().getEnp() + "'");
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
    public int getCountCalling(WorkingAgeSex workingAgeSex, String diags, String month, String year){

        return switch (workingAgeSex){
            case M_16_60 ->
                dnGets
                    .stream()
                    .filter(c ->
                         c.getPeople().getAge() >= 16
                                 && c.getPeople().getAge() <= 60
                                 && c.getPeopleSex().equals("1")
                                 && diags.contains(transformDiag(c.getDiag()))
                                 && c.getDate_call() != null
                                 && c.getDate_call().after(serviceUtil.transformDate(month, year, DateInterval.minDate))
                                 && c.getDate_call().before(serviceUtil.transformDate(month, year, DateInterval.maxDate)))
                    .toList()
                    .stream()
                    .distinct()
                    .toList()
                    .size();

            case M_older_60 ->
                dnGets
                    .stream()
                    .filter(c ->
                        c.getPeople().getAge() > 60
                                && c.getPeopleSex().equals("1")
                                && diags.contains(transformDiag(c.getDiag()))
                                && c.getDate_call() != null
                                && c.getDate_call().after(serviceUtil.transformDate(month, year, DateInterval.minDate))
                                && c.getDate_call().before(serviceUtil.transformDate(month, year, DateInterval.maxDate)))
                    .toList()
                    .stream()
                    .distinct()
                    .toList()
                    .size();

            case W_16_55 ->
                dnGets
                    .stream()
                    .filter(c -> c.getPeople().getAge() >= 16
                            && c.getPeople().getAge() <= 55
                            && c.getPeopleSex().equals("2")
                            && diags.contains(transformDiag(c.getDiag()))
                            && c.getDate_call() != null
                            && c.getDate_call().after(serviceUtil.transformDate(month, year, DateInterval.minDate))
                            && c.getDate_call().before(serviceUtil.transformDate(month, year, DateInterval.maxDate)))
                    .toList()
                    .stream()
                    .distinct()
                    .toList()
                    .size();

            case W_older_55 ->
                dnGets
                    .stream()
                    .filter(c -> c.getPeople().getAge() > 55
                            && c.getPeopleSex().equals("2")
                            && diags.contains(transformDiag(c.getDiag()))
                            && c.getDate_call() != null
                            && c.getDate_call().after(serviceUtil.transformDate(month, year, DateInterval.minDate))
                            && c.getDate_call().before(serviceUtil.transformDate(month, year, DateInterval.maxDate)))
                    .toList()
                    .stream()
                    .distinct()
                    .toList()
                    .size();
            case all ->
                dnGets
                    .stream()
                    .filter(c -> diags.contains(transformDiag(c.getDiag()))
                            && c.getDate_call() != null
                            && c.getDate_call().after(serviceUtil.transformDate(month, year, DateInterval.minDate))
                            && c.getDate_call().before(serviceUtil.transformDate(month, year, DateInterval.maxDate)))
                    .toList()
                    .stream()
                    .distinct()
                    .toList()
                    .size();
        };
    }

    //Визиты поликлиника/дом
    public int getCountVisit(VisitType visitType,
                             WorkingAgeSex workingAgeSex, String diags, String month, String year){

        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getBars();
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
                            "inner join tf_proc.tp_case c on cb.tp_case = c.id " +
                            "inner join tf_proc.tp_caseraw_sl crsl on c.caseraw = crsl.pid " +
                            "inner join tf_proc.tp_caseraw_sl_ext crsle on crsle.xid = crsl.id " +
                            "left join nsi_med.med_mkb10 mkb on mkb.id = sl.ds1 " +
                            "where cbb.month = " + month +
                            " and cbb.year = " + year +
                            " and coalesce(" + strVisitTypeSearchValue + ", '0') <> '0' " +
                            "and date_part('year', age(cb.date_2, cbp.pac_dr)) between 16 and 60 " +
                            "and cbp.pac_w = 1712801 " +
                            "and mkb.mkb_code in (" + transformDiagForBars(diags) +
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
                            "inner join tf_proc.tp_case c on cb.tp_case = c.id " +
                            "inner join tf_proc.tp_caseraw_sl crsl on c.caseraw = crsl.pid " +
                            "inner join tf_proc.tp_caseraw_sl_ext crsle on crsle.xid = crsl.id " +
                            "left join nsi_med.med_mkb10 mkb on mkb.id = sl.ds1 " +
                            "where cbb.month = " + month +
                            " and cbb.year = " + year +
                            " and coalesce(" + strVisitTypeSearchValue + ", '0') <> '0' " +
                            "and date_part('year', age(cb.date_2, cbp.pac_dr)) > 60 " +
                            "and cbp.pac_w = 1712801 " +
                            "and mkb.mkb_code in (" + transformDiagForBars(diags) +
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
                            "inner join tf_proc.tp_case c on cb.tp_case = c.id " +
                            "inner join tf_proc.tp_caseraw_sl crsl on c.caseraw = crsl.pid " +
                            "inner join tf_proc.tp_caseraw_sl_ext crsle on crsle.xid = crsl.id " +
                            "left join nsi_med.med_mkb10 mkb on mkb.id = sl.ds1 " +
                            "where cbb.month = " + month +
                            " and cbb.year = " + year +
                            " and coalesce(" + strVisitTypeSearchValue + ", '0') <> '0' " +
                            "and date_part('year', age(cb.date_2, cbp.pac_dr)) between 16 and 55 " +
                            "and cbp.pac_w = 1712821 " +
                            "and mkb.mkb_code in (" + transformDiagForBars(diags) +
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
                            "inner join tf_proc.tp_case c on cb.tp_case = c.id " +
                            "inner join tf_proc.tp_caseraw_sl crsl on c.caseraw = crsl.pid " +
                            "inner join tf_proc.tp_caseraw_sl_ext crsle on crsle.xid = crsl.id " +
                            "left join nsi_med.med_mkb10 mkb on mkb.id = sl.ds1 " +
                            "where cbb.month = " + month +
                            " and cbb.year = " + year +
                            " and coalesce(" + strVisitTypeSearchValue + ", '0') <> '0' " +
                            "and date_part('year', age(cb.date_2, cbp.pac_dr)) > 55 " +
                            "and cbp.pac_w = 1712821 " +
                            "and mkb.mkb_code in (" + transformDiagForBars(diags) +
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
                            "inner join tf_proc.tp_case c on cb.tp_case = c.id " +
                            "inner join tf_proc.tp_caseraw_sl crsl on c.caseraw = crsl.pid " +
                            "inner join tf_proc.tp_caseraw_sl_ext crsle on crsle.xid = crsl.id " +
                            "left join nsi_med.med_mkb10 mkb on mkb.id = sl.ds1 " +
                            "where cbb.month = " + month +
                            " and cbb.year = " + year +
                            " and coalesce(" + strVisitTypeSearchValue + ", '0') <> '0' " +
                            "and mkb.mkb_code in (" + transformDiagForBars(diags) +
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
    public int getCountHospitalize(WorkingAgeSex workingAgeSex, String diags, String month, String year){
        int count = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getBars();

        List<DNGet> dnGetsPeople = null;
        switch (workingAgeSex){
            case M_16_60 ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() >= 16
                                    && c.getPeople().getAge() <= 60
                                    && c.getPeopleSex().equals("1")
                                    && diags.contains(transformDiag(c.getDiag())))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();

            case M_older_60 ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() > 60
                                    && c.getPeopleSex().equals("1")
                                    && diags.contains(transformDiag(c.getDiag())))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();

            case W_16_55 ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() >= 16
                                    && c.getPeople().getAge() <= 55
                                    && c.getPeopleSex().equals("2")
                                    && diags.contains(transformDiag(c.getDiag())))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();

            case W_older_55 ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() > 55
                                    && c.getPeopleSex().equals("2")
                                    && diags.contains(transformDiag(c.getDiag())))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();
            case all ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> diags.contains(transformDiag(c.getDiag())))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();
        }

        for (DNGet dnGet : dnGetsPeople) {
            try {
                ResultSet resultSet = statement.executeQuery("select count(*) from tf_proc.tp_casebill cb" +
                        "    inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                        "    inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                        "    inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                        "    inner join tf_proc.tp_case c on cb.tp_case = c.id " +
                        "    inner join tf_proc.tp_caseraw_sl crsl on c.caseraw = crsl.pid " +
                        "    inner join tf_proc.tp_caseraw_sl_ext crsle on crsle.xid = crsl.id " +
                        "    left join nsi_med.med_mkb10 mkb on mkb.id = sl.ds1 " +
                        "    left join nsi_med.med_help_conditions mhc on mhc.id = cb.usl_ok " +
                        "    where cbb.month = " + month +
                        "    and cbb.year = " + year +
                        "    and cb.sump > 0" +
                        " and cbp.pac_fam = '" + dnGet.getPeople().getFam() +
                        "' and cbp.pac_im = '" + dnGet.getPeople().getIm() +
                        "' and cbp.pac_ot = '" + dnGet.getPeople().getOt() +
                        "' and cbp.pac_dr = to_date('" + dateFormat.format(dnGet.getPeople().getDr()) + "', 'dd.mm.yyyy')" +
                        " and cb.enp = '" + dnGet.getPeople().getEnp() +
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
    public int getCountDeath(WorkingAgeSex workingAgeSex, String diags, String month, String year){
        int count = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getSRZ();

        List<DNGet> dnGetsPeople = null;
        switch (workingAgeSex){
            case M_16_60 ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() >= 16
                                    && c.getPeople().getAge() <= 60
                                    && c.getPeopleSex().equals("1")
                                    && diags.contains(transformDiag(c.getDiag())))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();

            case M_older_60 ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() > 60
                                    && c.getPeopleSex().equals("1")
                                    && diags.contains(transformDiag(c.getDiag())))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();

            case W_16_55 ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() >= 16
                                    && c.getPeople().getAge() <= 55
                                    && c.getPeopleSex().equals("2")
                                    && diags.contains(transformDiag(c.getDiag())))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();

            case W_older_55 ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() > 55
                                    && c.getPeopleSex().equals("2")
                                    && diags.contains(transformDiag(c.getDiag())))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();
            case all ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> diags.contains(transformDiag(c.getDiag())))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();
        }

        for (DNGet dnGet : dnGetsPeople) {
            try {
                ResultSet resultSet = statement.executeQuery("select count(*) from PEOPLE p where " +
                        "p.FAM = '" + dnGet.getPeople().getFam() + "' " +
                        "and p.IM = '" + dnGet.getPeople().getIm() + "' " +
                        "and p.OT = '" + dnGet.getPeople().getOt() + "' " +
                        "and p.dr = PARSE('" + dnGet.getPeople().getDr() + "' as date) " +
                        "and p.enp = '" + dnGet.getPeople().getEnp() + "' " +
                        "and p.ds between PARSE('" + dateFormat.format(serviceUtil.transformDate(month, year, DateInterval.minDate)) + "' as date) " +
                        "and PARSE('" + dateFormat.format(serviceUtil.transformDate(month, year, DateInterval.maxDate)) + "' as date)");
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
    public int getCountAmbulance(WorkingAgeSex workingAgeSex, String diags, String month, String year){
        int count = 0;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getBars();
        List<DNGet> dnGetsPeople = null;
        switch (workingAgeSex){
            case M_16_60 ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() >= 16
                                    && c.getPeople().getAge() <= 60
                                    && c.getPeopleSex().equals("1")
                                    && diags.contains(transformDiag(c.getDiag()))
                                    && c.getDate_1().after(serviceUtil.transformDate(month, year, DateInterval.minDate))
                                    && c.getDate_1().before(serviceUtil.transformDate(month, year, DateInterval.maxDate)))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();

            case M_older_60 ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() > 60
                                    && c.getPeopleSex().equals("1")
                                    && diags.contains(transformDiag(c.getDiag()))
                                    && c.getDate_1().after(serviceUtil.transformDate(month, year, DateInterval.minDate))
                                    && c.getDate_1().before(serviceUtil.transformDate(month, year, DateInterval.maxDate)))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();

            case W_16_55 ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() >= 16
                                    && c.getPeople().getAge() <= 55
                                    && c.getPeopleSex().equals("2")
                                    && diags.contains(transformDiag(c.getDiag()))
                                    && c.getDate_1().after(serviceUtil.transformDate(month, year, DateInterval.minDate))
                                    && c.getDate_1().before(serviceUtil.transformDate(month, year, DateInterval.maxDate)))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();

            case W_older_55 ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> c.getPeople().getAge() > 55
                                    && c.getPeopleSex().equals("2")
                                    && diags.contains(transformDiag(c.getDiag()))
                                    && c.getDate_1().after(serviceUtil.transformDate(month, year, DateInterval.minDate))
                                    && c.getDate_1().before(serviceUtil.transformDate(month, year, DateInterval.maxDate)))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();
            case all ->
                    dnGetsPeople = dnGets
                            .stream()
                            .filter(c -> diags.contains(transformDiag(c.getDiag()))
                                    && c.getDate_1().after(serviceUtil.transformDate(month, year, DateInterval.minDate))
                                    && c.getDate_1().before(serviceUtil.transformDate(month, year, DateInterval.maxDate)))
                            .toList()
                            .stream()
                            .distinct()
                            .toList();
        }

        assert dnGetsPeople != null;
        for (DNGet dnGet : dnGetsPeople){
            try {
                ResultSet resultSet = statement.executeQuery("select count(*) from tf_proc.tp_casebill cb " +
                        "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                        "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                        "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                        "left join nsi_med.med_purp_visit mpv on mpv.id = sl.p_cel " +
                        "where mpv.code = '1.1' " +
                        "and cbb.month = " + month +
                        " and cbb.year = " + year +
                        " and cbp.pac_fam = '" + dnGet.getPeople().getFam() +
                        "' and cbp.pac_im  = '" + dnGet.getPeople().getIm() +
                        "' and cbp.pac_ot = '" + dnGet.getPeople().getOt() +
                        "' and cbp.pac_dr  = to_date('" + dateFormat.format(dnGet.getPeople().getDr()) + "', 'DD.MM.YYYY') " +
                        "and cb.enp  = '" + dnGet.getPeople().getEnp() + "'");
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
    public int getCountInv(WorkingAgeSex workingAgeSex, String diags){
        return switch (workingAgeSex){
            case M_16_60 ->
                dnGets
                    .stream()
                    .filter(c -> c.getPeople().getAge() >= 16
                            && c.getPeople().getAge() <= 60
                            && c.getPeopleSex().equals("1")
                            && diags.contains(transformDiag(c.getDiag()))
                            && c.getPeople().getInv() != 0
                            && c.getPeople().getInv() != null)
                    .toList()
                    .stream()
                    .distinct()
                    .toList()
                    .size();

            case M_older_60 ->
                dnGets
                    .stream()
                    .filter(c -> c.getPeople().getAge() > 60
                            && c.getPeopleSex().equals("1")
                            && diags.contains(transformDiag(c.getDiag()))
                            && c.getPeople().getInv() != 0)
                    .toList()
                    .stream()
                    .distinct()
                    .toList()
                    .size();

            case W_16_55 ->
                 dnGets
                    .stream()
                    .filter(c -> c.getPeople().getAge() >= 16
                            && c.getPeople().getAge() <= 55
                            && c.getPeopleSex().equals("2")
                            && diags.contains(transformDiag(c.getDiag()))
                            && c.getPeople().getInv() != 0
                            && c.getPeople().getInv() != null)
                    .toList()
                    .stream()
                    .distinct()
                    .toList()
                    .size();

            case W_older_55 ->
                 dnGets
                    .stream()
                    .filter(c -> c.getPeople().getAge() > 55
                            && c.getPeopleSex().equals("2")
                            && diags.contains(transformDiag(c.getDiag()))
                            && c.getPeople().getInv() != 0
                            && c.getPeople().getInv() != null)
                    .toList()
                    .stream()
                    .distinct()
                    .toList()
                    .size();

            case all ->
                    dnGets
                            .stream()
                            .filter(c -> diags.contains(transformDiag(c.getDiag()))
                                    && c.getPeople().getInv() != 0
                                    && c.getPeople().getInv() != null)
                            .toList()
                            .stream()
                            .distinct()
                            .toList()
                            .size();
        };
    }

    private String transformDiag(String diag){
        if(diag!= null && diag.substring(diag.indexOf(".") + 1).equals("0") && diag.length() == 5){
            return diag.substring(0, 3);
        } else return diag;
    }

    private String transformDiagForBars(String diags){
        int count = 0;
        StringBuilder resultStr = new StringBuilder();
        String[] str = diags.replaceAll("\\s", "").trim().split(",");
        while (count < str.length){
            if (count == 0){
                resultStr.append("'").append(str[count]).append("'");
            } else  resultStr.append(",'").append(str[count]).append("'");
            count++;
        }
        return resultStr.toString();
    }
}
