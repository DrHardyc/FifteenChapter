package ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseResponseRecord;
import ru.hardy.udio.domain.api.individualhistoryonkocase.InsuranceCase;
import ru.hardy.udio.repo.apirepo.individualhistoryonkocaserepo.IndividualHistoryOnkoCaseResponseRecordRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class IndividualHistoryOnkoCaseResponseRecordService {

    @Autowired
    private IndividualHistoryOnkoCaseResponseRecordRepo individualHistoryOnkoCaseResponseRecordRepo;

    public void addAll(List<IndividualHistoryOnkoCaseResponseRecord> individualHistoryOnkoCaseResponseRecords) {
        individualHistoryOnkoCaseResponseRecordRepo.saveAll(individualHistoryOnkoCaseResponseRecords);
    }

    public List<InsuranceCase> getInsuredCases(InsuredPerson insuredPerson){
        List<InsuranceCase> insuranceCases = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getBars();
        try {
            ResultSet resultSet = statement.executeQuery("select mor.reg_code, mor_attach.reg_code, cbp.tel, " +
                    "cbb.nschet, cbb.dschet, " +
                    "case when cb.usl_ok = 3 and sl.p_cel = 3.0 then 'Обращение' " +
                    "else 'Посещение' " +
                    "end, cb.date_1, cb.date_2, mkb1.mkb_code, mkb2.mkb_code, mkb3.mkb_code, mhr.caption, " +
                    "case when sl.pr_d_n = 1 or sl.dn = 1 then 'Состоит' " +
                    "when sl.pr_d_n = 2 or sl.dn = 2 then 'Взят' " +
                    "end from tf_proc.tp_casebill cb " +
                    "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                    "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                    "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                    "left join tf_proc.tp_casebill_ds ds on ds.pid = cb.id " +
                    "left join mo_passport.mo_reg_codes mor on mor.pid = cb.mo " +
                    "left join mo_passport.mo_reg_codes mor_attach on mor_attach.pid = cb.mo_attach " +
                    "left join nsi_med.med_sex ms on ms.id = cbp.pac_w " +
                    "left join nsi_med.med_mkb10 mkb1 on mkb1.id = cb.ds1 " +
                    "left join nsi_med.med_mkb10 mkb2 on mkb2.id = ds.ds and ds.ds_type = 2 " +
                    "left join nsi_med.med_mkb10 mkb3 on mkb3.id = ds.ds and ds.ds_type = 3 " +
                    "left join nsi_med.med_help_results mhr on mhr.id = cb.rslt " +
                    "where (substring(mkb1.mkb_code, 1, 1) = 'C' or sl.ds_onk = 1) and " +
                    "cb.sumv > 0 and cb.is_regional_case = 1 " +
                    "and upper(cbp.pac_fam) = upper('" + insuredPerson.getSurname() + "') " +
                    "and upper(cbp.pac_im) = upper('" + insuredPerson.getName() + "') " +
                    "and upper(cbp.pac_ot) = upper('" + insuredPerson.getPatronymic() + "') " +
                    "and cbp.pac_dr = to_date('" + dateFormat.format(insuredPerson.getDateBirth()) + "', 'yyyy-mm-dd') " +
                    "and cb.enp = '" + insuredPerson.getEnp() + "'" +
                    "group by mor.reg_code, mor_attach.reg_code, cbp.pac_fam, cbp.pac_im, cbp.pac_ot, cbp.pac_dr, cb.enp, ms.caption, " +
                    "cbb.nschet, cbb.dschet, cb.usl_ok, sl.p_cel, cbp.tel, cb.date_1, cb.date_2, mkb1.mkb_code, mkb2.mkb_code, " +
                    "mkb3.mkb_code, mhr.caption, cb.pr_d_n, sl.pr_d_n, sl.dn");

            while (resultSet.next()){
                insuranceCases.add(new InsuranceCase(
                        resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getDate(5), resultSet.getString(6),
                        resultSet.getDate(7), resultSet.getDate(8), resultSet.getString(9),
                        resultSet.getString(10), resultSet.getString(11),
                        resultSet.getString(12), resultSet.getString(13)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return insuranceCases;
    }
}
