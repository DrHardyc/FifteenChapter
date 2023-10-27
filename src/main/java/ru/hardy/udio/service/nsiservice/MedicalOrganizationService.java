package ru.hardy.udio.service.nsiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.repo.nsirepo.MedicalOrganizationRepo;

@Service
public class MedicalOrganizationService {

    @Autowired
    private MedicalOrganizationRepo medicalOrganizationRepo;


    public MedicalOrganization getByCode(int codeMO) {
        return medicalOrganizationRepo.findByCodeMO(codeMO);
    }
}
