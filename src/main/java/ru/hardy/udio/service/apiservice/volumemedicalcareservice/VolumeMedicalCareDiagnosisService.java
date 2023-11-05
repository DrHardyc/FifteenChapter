package ru.hardy.udio.service.apiservice.volumemedicalcareservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareDiagnosis;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.repo.apirepo.volumemedicalcarerepo.VolumeMedicalCareRepoDiagnosisRepo;

import java.util.Date;
import java.util.List;

@Service
public class VolumeMedicalCareDiagnosisService {

    @Autowired
    private VolumeMedicalCareRepoDiagnosisRepo volumeMedicalCareRepoDiagnosisRepo;

    public List<VolumeMedicalCareDiagnosis> getAllByCodeMOAndDateInterval(MedicalOrganization medicalOrganization,
                                                                          Date dateBeg, Date dateEnd){
        return volumeMedicalCareRepoDiagnosisRepo.findAllByMOAndDateInterval(medicalOrganization, dateBeg, dateEnd);
    }

    public List<VolumeMedicalCareDiagnosis> getAllByCodeMOAndCodeDepDateInterval(MedicalOrganization medicalOrganization,
                                                                          String nameDep, Date dateBeg, Date dateEnd){
        return volumeMedicalCareRepoDiagnosisRepo.findAllByMOAndCodeDepDateInterval(medicalOrganization, nameDep, dateBeg, dateEnd);
    }

    public int getAllByCodeMOAndCodeDepAndCodeDiagDateInterval(MedicalOrganization medicalOrganization,
                                                                                 String nameDep, String codeDiag, Date dateBeg, Date dateEnd){
        VolumeMedicalCareDiagnosis volumeMedicalCareDiagnosis =
                volumeMedicalCareRepoDiagnosisRepo
                        .findAllByMOAndCodeDepAndCodeDiagDateInterval(medicalOrganization, codeDiag, nameDep, dateBeg, dateEnd);
        if (volumeMedicalCareDiagnosis != null){
            return volumeMedicalCareDiagnosis.getQuantity();
        }
        return 0;
    }
}
