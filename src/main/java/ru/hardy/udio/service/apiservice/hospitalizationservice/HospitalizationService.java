package ru.hardy.udio.service.apiservice.hospitalizationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.hospitalization.Hospitalization;
import ru.hardy.udio.domain.api.hospitalization.HospitalizationRequestRecordPatient;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.hospitalizationrepo.HospitalizationRepo;

import java.util.Date;
import java.time.Instant;

@Service
public class HospitalizationService {
    @Autowired
    private HospitalizationRepo hospitalizationRepo;

    public boolean checkPatient(People people, String mainDiagnosis, int hospitalization) {
        return hospitalizationRepo.findByPeopleAndPatient_MainDiagnosisAndPatient_Hospitalization(people, mainDiagnosis, hospitalization) != null;
    }

    public void add(People people, HospitalizationRequestRecordPatient hospitalizationRequestRecordPatient) {
        Hospitalization hospitalization = new Hospitalization();
        hospitalization.setDateBeg(Date.from(Instant.now()));
        hospitalization.setDateEdit(Date.from(Instant.now()));
        hospitalization.setPeople(people);
        hospitalization.setPatient(hospitalizationRequestRecordPatient);
        hospitalizationRepo.save(hospitalization);
    }
}
