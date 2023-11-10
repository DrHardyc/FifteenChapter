package ru.hardy.udio.service.apiservice.hospitalizationservice.smo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.hospitalization.dto.HospitalizationPatientDTO;
import ru.hardy.udio.domain.api.hospitalization.smo.HospitalizationSMORequest;
import ru.hardy.udio.domain.api.hospitalization.smo.HospitalizationSMOResponse;
import ru.hardy.udio.domain.api.hospitalization.smo.HospitalizationSMOResponseRecord;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.repo.apirepo.hospitalizationrepo.smo.HospitalizationSMOResponseRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;
import ru.hardy.udio.service.apiservice.hospitalizationservice.dto.HospitalizationPatientDTOService;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalizationSMOResponseService implements APIResponseServiceInterface {
    @Autowired
    private HospitalizationSMOResponseRepo hospitalizationSMOResponseRepo;
    @Autowired
    private HospitalizationPatientDTOService hospitalizationPatientDTOService;

    @Override
    public void add(APIResponse apiResponse) {
        hospitalizationSMOResponseRepo.save((HospitalizationSMOResponse) apiResponse);
    }

    @Override
    public void addAll(List<APIResponse> apiResponses) {

    }

    @Override
    public APIResponse processing(APIRequest apiRequest, APIResponse apiResponse, MedicalOrganization medicalOrganization) {
        HospitalizationSMORequest hospitalizationSMORequest = (HospitalizationSMORequest) apiRequest;
        HospitalizationSMOResponse hospitalizationSMOResponse = (HospitalizationSMOResponse) apiResponse;
        List<HospitalizationSMOResponseRecord> hospitalizationSMOResponseRecords = new ArrayList<>();
        List<HospitalizationPatientDTO> hospitalizationPatientDTOS = hospitalizationPatientDTOService
                .getAllByCodeHospitalization(hospitalizationSMORequest.getCodeHospitalization());
        hospitalizationPatientDTOS.forEach(hospitalizationPatientDTO -> {
            hospitalizationSMOResponseRecords.add(new HospitalizationSMOResponseRecord(hospitalizationPatientDTO, hospitalizationSMOResponse));
        });

        hospitalizationSMOResponse.setDateBeg(Date.from(Instant.now()));
        hospitalizationSMOResponse.setDateEdit(Date.from(Instant.now()));
        hospitalizationSMOResponse.setResultResponseCode(200);
        hospitalizationSMOResponse.setPatients(hospitalizationSMOResponseRecords);
        add(hospitalizationSMOResponse);
        return hospitalizationSMOResponse;
    }

    public APIResponse getWithReqId(String reqID, int codeMO) {
        return hospitalizationSMOResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }
}
