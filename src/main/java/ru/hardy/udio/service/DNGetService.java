package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.domain.struct.DataFilePatient;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.DNGetRepo;
import ru.hardy.udio.repo.DataFilePatientRepo;
import ru.hardy.udio.repo.PeopleRepo;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class DNGetService {

    @Autowired
    private DNGetRepo dnGetRepo;

    @Autowired
    private DataFilePatientRepo dataFilePatientRepo;

    @Autowired
    private PeopleRepo peopleRepo;

    public void save(DNGet dnGet){
        dnGetRepo.save(dnGet);
    }

    @Transactional
    public List<DNGet> getAll(){
        return dnGetRepo.findAll();
    }

    @Transactional
    public List<DNGet> getAllTherapist(){
        return dnGetRepo.findAllByTherapist();
    }

    @Transactional
    public List<DNGet> getAllONKO(){
        return dnGetRepo.findAllByONKO();
    }

    public List<DNGet> getByPeopleId(Long id) {
        return dnGetRepo.findByPeopleId(id);
    }

    @Transactional
    public void saveOrUpdate(DataFilePatient dataFilePatient, People people, DataFilePatientService dataFilePatientService) {
        DNGet dnGet = null;
        if (dataFilePatient.getDiag() != null && !dataFilePatient.getDiag().isEmpty()){
            dnGet = dnGetRepo.findByDiagAndPeople(dataFilePatient.getDiag(), people);
        }

        if (dnGet != null) {
            dataFilePatientService.updateStatus(dataFilePatient, 5);
            return;
        }

        dnGetRepo.save(new DNGet(dataFilePatient, null, peopleRepo.findById1(people.getId())));
    }
    private void update(DNGet dnGet, DataFilePatient dataFilePatient){
        dnGet.setSpecialization(dataFilePatient.getSpecialization());
        dnGet.setDate_1(dataFilePatient.getDate_1());
        dnGet.setDate_2(dataFilePatient.getDate_2());
        dnGet.setDate_call(dataFilePatient.getDate_call());
        dnGet.setDiag(dataFilePatient.getDiag());
        dnGet.setDate_edit(Date.from(Instant.now()));
        dnGetRepo.save(dnGet);
    }

    public List<DNGet> getAllWithDateInterval(Date dateBeg, Date dateEnd) {
        return dnGetRepo.findAllByDate_1Between(dateBeg, dateEnd);
    }

    public List<DNGet> getAllKARDIO() {
        return dnGetRepo.findAllByKARDIO();
    }

    public void deleteAllByPeople(List<DNGet> dnGets) {
        dnGetRepo.deleteAll(dnGets);
    }

//    public List<DNGetDTO> getAllDTO() {
//        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
//        Statement statement = dbjdbcConfig.getUDIO();
//
//        List<DNGetDTO> dnGets = new ArrayList<>();
//        try {
//            ResultSet resultSet = statement.executeQuery("select concat(p.fam, ' ', p.im, ' ', p.ot), p.mo_attach, " +
//                    "dg.nhistory, dg.date_1, dg.diag, dg.date_call, dg.specialization, p.dr, p.sex_id, p.inv, dg.mo " +
//                    "from udio_tfoms.dnget dg " +
//                    "inner join udio_tfoms.people p on dg.people_id = p.id");
//
//            while (resultSet.next()){
//                dnGets.add(new DNGetDTO(resultSet.getString(1),
//                                        resultSet.getInt(2),
//                                        resultSet.getString(3),
//                                        resultSet.getDate(4),
//                                        resultSet.getString(5),
//                                        resultSet.getDate(6),
//                                        resultSet.getInt(7),
//                                        resultSet.getDate(8),
//                                        resultSet.getInt(9),
//                                        resultSet.getInt(10),
//                                        resultSet.getInt(11)
//                        )
//                );
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return dnGets;
//    }
}
