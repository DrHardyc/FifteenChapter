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

    @Transactional
    public List<DNGet> getAllOnkologist(){
        return dnGetRepo.findAllByOnkologist();
    }

}
