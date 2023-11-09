package ru.hardy.udio.service.apiservice.padatapatientsservice.smo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatient;
import ru.hardy.udio.domain.api.padatapatients.smo.PADataPatientSMORequest;
import ru.hardy.udio.domain.api.padatapatients.smo.PADataPatientSMOResponse;
import ru.hardy.udio.domain.api.padatapatients.smo.PADataPatientSMOResponseRecord;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.repo.apirepo.padatapatientsrepo.smo.PADataPatientSMOResponseRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;
import ru.hardy.udio.service.apiservice.padatapatientsservice.mo.PADataPatientService;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class PADataPatientSMOResponseService implements APIResponseServiceInterface {

    @Autowired
    private PADataPatientSMOResponseRepo paDataPatientSMOResponseRepo;

    @Autowired
    private PADataPatientService paDataPatientService;

    public void add(APIResponse apiResponse){
        paDataPatientSMOResponseRepo.save((PADataPatientSMOResponse) apiResponse);
    }

    @Override
    public void addAll(List<APIResponse> apiResponses) {

    }

    public APIResponse processing(APIRequest apiRequest, APIResponse apiResponse, MedicalOrganization medicalOrganization) {
        PADataPatientSMORequest paDataPatientSMORequest = (PADataPatientSMORequest) apiRequest;
        PADataPatientSMOResponse paDataPatientSMOResponse = (PADataPatientSMOResponse) apiResponse;
        List<PADataPatientSMOResponseRecord> paDataPatientSMOResponseRecords = new ArrayList<>();
        List<PADataPatient> paDataPatients = paDataPatientService
                .getAllByTypeAndStatus(paDataPatientSMORequest.getCodeTypePreventiveActions(),
                        paDataPatientSMORequest.getStatusTypePreventiveActions());
        paDataPatients.forEach(paDataPatient -> {
            paDataPatientSMOResponseRecords.add(new PADataPatientSMOResponseRecord(paDataPatient.getRequestRecord(), paDataPatientSMOResponse));
        });
        paDataPatientSMOResponse.setPatients(paDataPatientSMOResponseRecords);
        paDataPatientSMOResponse.setResultResponseCode(200);
        paDataPatientSMOResponse.setDateBeg(Date.from(Instant.now()));
        paDataPatientSMOResponse.setDateEdit(Date.from(Instant.now()));
        add(paDataPatientSMOResponse);
        return paDataPatientSMOResponse;
    }

    public APIResponse getWithReqId(String reqID, int codeMO) {
        return paDataPatientSMOResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }
}
