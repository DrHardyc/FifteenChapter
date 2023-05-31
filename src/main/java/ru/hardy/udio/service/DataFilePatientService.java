package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.struct.DataFile;
import ru.hardy.udio.domain.struct.DataFilePatient;
import ru.hardy.udio.repo.DataFilePatientRepo;
import ru.hardy.udio.repo.PeopleRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DataFilePatientService {
    @Autowired
    private DataFilePatientRepo dataFilePatientRepo;

    @Autowired
    private DataFileService dataFileService;

    @Autowired
    private SexService sexService;

    @Autowired
    private PeopleRepo peopleRepo;

    public List<DataFilePatient> getAll(){
        return dataFilePatientRepo.findAll();
    }

    public void getFromBars() {
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        try {
            Statement statement = dbjdbcConfig.getBars();
            ResultSet resultSet = statement.executeQuery("select cbp.pac_fam, cbp.pac_im, cbp.pac_ot, cbp.pac_dr, " +
                    "coalesce(cb.enp, cb.npolis), ms.code, dg.code, cb.nhistory, mkb.mkb_code," +
                    " spec.code, mor.reg_code,  cb.date_1, cb.date_2 " +
                    "from tf_proc.tp_casebill cb " +
                    "inner join tf_proc.tp_casebill_sl sl on sl.pid = cb.id " +
                    "inner join tf_proc.tp_casebill_patient cbp on cbp.id = cb.pid " +
                    "inner join tf_proc.tp_casebill_bill cbb on cbb.id = cb.tp_casebill_bill " +
                    "left join mo_passport.mo mo on mo.id = cb.mo " +
                    "left join mo_passport.mo_reg_codes mor on mor.pid = mo.id " +
                    "left join nsi_med.med_help_results mhr on mhr.id = cb.rslt " +
                    "left join nsi_med.med_sex ms on ms.id = cbp.pac_w " +
                    "left join nsi_med.med_mkb10 mkb on mkb.id = cb.ds1 " +
                    "left join tf_proc.tp_disability_groups dg on dg.id = cb.inv " +
                    "left join nsi.specialities spec on spec.id = sl.prvs " +
                    "where sl.dn in (1, 2) " +
                    "    and cb.sump > 0 " +
                    "    and coalesce(spec.code, '0') <> '0' " +
                    "order by mor.reg_code, cbp.pac_fam, cbp.pac_im, cbp.pac_ot ");

            List<DataFilePatient> dataFilePatientList = new ArrayList<>();
            int mo_code = 0;
            DataFile dataFile = null;
            long import_id = 0L;
            while (resultSet.next()){
                if (mo_code == 0 ){
                    mo_code = resultSet.getInt(11);
                    dataFile = new DataFile("Первоначальная загрузка с БАРС",
                            Date.from(Instant.now()),mo_code, import_id++);
                } else if (mo_code != resultSet.getInt(11)){
                    dataFile.setDataFilePatient(dataFilePatientList);
                    dataFileService.save(dataFile);
                    mo_code = resultSet.getInt(11);
                    dataFile = new DataFile("Первоначальная загрузка с БАРС",
                            Date.from(Instant.now()), mo_code, import_id++);
                    dataFilePatientList.clear();
                }
                dataFilePatientList.add(new DataFilePatient(resultSet.getString(1),
                    resultSet.getString(2), resultSet.getString(3),
                    resultSet.getDate(4), resultSet.getString(5),
                    null, sexService.getById(resultSet.getLong(6)),
                    resultSet.getInt(7), resultSet.getString(8),
                    resultSet.getString(9), null, resultSet.getInt(10),
                    resultSet.getDate(12), resultSet.getDate(13), "", 0L, dataFile));
            }
            assert dataFile != null;
            dataFile.setDataFilePatient(dataFilePatientList);
            dataFileService.save(dataFile);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean searchFromPeople(DataFilePatient dataFilePatient) {
        return peopleRepo.findPeopleByFamAndImAndOtAndDrAndEnp(dataFilePatient.getFam(), dataFilePatient.getIm(),
                dataFilePatient.getOt(), dataFilePatient.getDr(), dataFilePatient.getEnp()) == null;
    }

    public List<DataFilePatient> getNoSearchFromSRZ(){
        return dataFilePatientRepo.getNoSearchFromSRZ();
    }
}
