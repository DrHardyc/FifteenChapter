package ru.hardy.udio.service.apiservice.hospitalizationservice.mo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.hospitalization.mo.Hospitalization;
import ru.hardy.udio.domain.api.hospitalization.mo.HospitalizationRequestRecordPatient;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.hospitalizationrepo.HospitalizationRepo;

import java.util.Date;
import java.time.Instant;
import java.util.List;

@Service
public class HospitalizationService {
    @Autowired
    private HospitalizationRepo hospitalizationRepo;

    public boolean checkPatient(People people, String mainDiagnosis, int hospitalization) {
        return hospitalizationRepo.findByPeopleAndPatient_MainDiagnosisAndPatient_CodeHospitalization(people, mainDiagnosis, hospitalization) != null;
    }

    public void add(People people, HospitalizationRequestRecordPatient hospitalizationRequestRecordPatient) {
        Hospitalization hospitalization = new Hospitalization();
        hospitalization.setDateBeg(Date.from(Instant.now()));
        hospitalization.setDateEdit(Date.from(Instant.now()));
        hospitalization.setPeople(people);
        hospitalization.setPatient(hospitalizationRequestRecordPatient);
        hospitalizationRepo.save(hospitalization);
    }

    public List<Hospitalization> getAllByCodeHospitalization(int codeHospitalization) {
        return hospitalizationRepo.findAllByPatient_CodeHospitalization(codeHospitalization);
    }

    public People getByPeopleListAndFilterParam(People people) {
        List<Hospitalization> hospitalizations = hospitalizationRepo.findByPeople(people);
        if (!hospitalizations.isEmpty()){
            return hospitalizations.get(0).getPeople();
        }
        return null;
    }

    public List<Hospitalization> getAllByPeople(People people) {
        return hospitalizationRepo.findAllByPeople(people);
    }
}
