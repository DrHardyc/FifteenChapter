package ru.hardy.udio.service.apiservice.volumemedicalcareservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCare;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareRequestRecord;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.repo.apirepo.volumemedicalcarerepo.VolumeMedicalCareRequestRecordRepo;

import java.util.Date;
import java.util.List;

@Service
public class VolumeMedicalCareRequestRecordService {

    @Autowired
    private VolumeMedicalCareRequestRecordRepo volumeMedicalCareRequestRecordRepo;

    public List<VolumeMedicalCareRequestRecord> getAllByMO(MedicalOrganization medicalOrganization,
                                                           Date dateBeg, Date dateEnd){
        return volumeMedicalCareRequestRecordRepo.findAllByMOLast7Days(medicalOrganization, dateBeg, dateEnd);
    }



}
