package ru.hardy.udio.service.reportservice;

import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.report.DateInterval;
import ru.hardy.udio.domain.report.Efficiency;
import ru.hardy.udio.service.UtilService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class EfficiencyService {

    UtilService utilService = new UtilService();

    public List<Efficiency> getAll(Statement statement, String monthBeg, String monthEnd, String yearBeg, String yearEnd, int part){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        List<Efficiency> efficiencies = new ArrayList<>();
        List<Efficiency> eff1s = new ArrayList<>();
        List<Efficiency> eff2s = new ArrayList<>();
        List<Efficiency> eff3s = new ArrayList<>();
        String strPart = "";
        String strDiag = "";
        switch (part){
            case 23 -> {
                strPart = " and cbp.pac_w = 1712821 ";
                strDiag = " and substring(mkb.mkb_code, 1, 3) = 'C53' ";
            }
            case 24 -> {
                strPart = " and cbp.pac_w = 1712821 ";
                strDiag = " and substring(mkb.mkb_code, 1, 3) = 'C50' ";
            }
            case 3 -> {
                strPart = " and date_part('year', age(cb.date_2, cbp.pac_dr)) >= 18 ";
                strDiag = " and substring(mkb.mkb_code, 1, 1) = 'C'" ;
            }
        }
        try {
            ResultSet resultSet = statement.executeQuery("select mor.reg_code, concat(cbp.pac_fam, cbp.pac_im, cbp.pac_ot, cbp.pac_dr, cb.enp), " +
                    "min(cb.date_1), tr.code " +
                    "from tf_proc.tp_casebill cb " +
                    "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                    "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                    "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                    "inner join tf_proc.tp_casebill_ds ds on ds.pid = cb.id " +
                    "left join mo_passport.mo mo on mo.id = cb.mo_attach " +
                    "inner join mo_passport.mo_reg_codes mor on mor.pid = mo.id " +
                    "left join nsi_med.med_mkb10 mkb on mkb.id = cb.ds1 " +
                    "left join tf_proc.tp_casebill_sl_ext sle on sle.xid = sl.id " +
                    "left join tf_proc.tpd_refpurposes tr on tr.id = sle.cel " +
                    "where cb.sump > 0 " +
                    "and cb.is_regional_case = 1 " +
                    "and cbp.pac_w = 1712821 " +
                    "and sl.ds_onk = 1 " + strPart +
                    "group by mor.reg_code, cbp.pac_fam, cbp.pac_im, cbp.pac_ot, cbp.pac_dr, cb.enp, tr.code");
            while (resultSet.next()){
                eff1s.add(new Efficiency(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(4), resultSet.getDate(3)));
            }

            resultSet = statement.executeQuery("select mor.reg_code, concat(cbp.pac_fam, cbp.pac_im, cbp.pac_ot, cbp.pac_dr, cb.enp), " +
                    "max(cb.date_1), tr.code " +
                    "from tf_proc.tp_casebill cb " +
                    "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                    "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                    "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                    "inner join tf_proc.tp_casebill_ds ds on ds.pid = cb.id " +
                    "left join mo_passport.mo mo on mo.id = cb.mo_attach " +
                    "inner join mo_passport.mo_reg_codes mor on mor.pid = mo.id " +
                    "left join nsi_med.med_mkb10 mkb on mkb.id = cb.ds1 " +
                    "left join tf_proc.tp_casebill_sl_ext sle on sle.xid = sl.id " +
                    "left join tf_proc.tpd_refpurposes tr on tr.id = sle.cel " +
                    "where cb.date_1 between to_date('" + dateFormat.format(utilService.transformDate(monthBeg,yearBeg, DateInterval.minDate)) + "', 'dd.mm.yyyy') " +
                    "and to_date('" + dateFormat.format(utilService.transformDate(monthEnd, yearEnd, DateInterval.maxDate)) + "', 'dd.mm.yyyy') " +
                    "and cb.sump > 0 " +
                    "and cb.is_regional_case = 1 " +
                    "and cbp.pac_w = 1712821 " + strDiag + strPart +
                    "group by mor.reg_code, cbp.pac_fam, cbp.pac_im, cbp.pac_ot, cbp.pac_dr, cb.enp, tr.code");

            while (resultSet.next()) {
                eff2s.add(new Efficiency(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(4), resultSet.getDate(3)));
            }

            List<Efficiency> newEffs = new ArrayList<>() ;
            for (Efficiency eff1 : eff1s){
                for (Efficiency eff2 : eff2s){
                    if (eff1.getConcat().equals(eff2.getConcat())
                            && eff1.getDate_1().before(eff2.getDate_1())){
                        newEffs.add(eff1);
                    }
                }
            }

            resultSet = statement.executeQuery("select mor.reg_code, concat(cbp.pac_fam, cbp.pac_im, cbp.pac_ot, cbp.pac_dr, cb.enp), " +
                    "max(cb.date_1), tr.code " +
                    "from tf_proc.tp_casebill cb " +
                    "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                    "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                    "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                    "inner join tf_proc.tp_casebill_ds ds on ds.pid = cb.id " +
                    "left join mo_passport.mo mo on mo.id = cb.mo_attach " +
                    "inner join mo_passport.mo_reg_codes mor on mor.pid = mo.id " +
                    "left join nsi_med.med_mkb10 mkb on mkb.id = cb.ds1 " +
                    "left join tf_proc.tp_casebill_sl_ext sle on sle.xid = sl.id " +
                    "left join tf_proc.tpd_refpurposes tr on tr.id = sle.cel " +
                    "where cb.date_1 between to_date('" + dateFormat.format(utilService.transformDate(monthBeg, yearBeg, DateInterval.minDate)) + "', 'dd.mm.yyyy') " +
                    "and to_date('" + dateFormat.format(utilService.transformDate(monthEnd, yearEnd, DateInterval.maxDate)) + "', 'dd.mm.yyyy') " +
                    "and cb.sump > 0 " +
                    "and cb.is_regional_case = 1 " +
                    "and cbp.pac_w = 1712821 " + strDiag +
                    "and (sl.ds1_pr = 1 or ds.ds_pr = 1 or sl.c_zab = 264496125) " + strPart +
                    "group by mor.reg_code, cbp.pac_fam, cbp.pac_im, cbp.pac_ot, cbp.pac_dr, cb.enp, tr.code");

            while (resultSet.next()) {
                eff3s.add(new Efficiency(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(4), resultSet.getDate(3)));
            }

            efficiencies.addAll(newEffs);

            boolean flag;
            for (Efficiency eff3: eff3s){
                flag = true;
                for(Efficiency newEff : newEffs){
                    if (eff3.getConcat().equals(newEff.getConcat())){
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    efficiencies.add(eff3);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return efficiencies;
    }
}
