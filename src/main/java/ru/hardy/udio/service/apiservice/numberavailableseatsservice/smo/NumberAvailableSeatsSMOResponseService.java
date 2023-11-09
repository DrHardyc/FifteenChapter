package ru.hardy.udio.service.apiservice.numberavailableseatsservice.smo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.numberavailableseats.dto.NumberAvailableSeatsDTO;
import ru.hardy.udio.domain.api.numberavailableseats.smo.NumberAvailableSeatsSMORequest;
import ru.hardy.udio.domain.api.numberavailableseats.smo.NumberAvailableSeatsSMOResponse;
import ru.hardy.udio.domain.api.numberavailableseats.smo.NumberAvailableSeatsSMOResponseRecord;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.smo.NumberAvailableSeatsSMOResponseRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;
import ru.hardy.udio.service.apiservice.numberavailableseatsservice.dto.NumberAvailableSeatsDTOService;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class NumberAvailableSeatsSMOResponseService implements APIResponseServiceInterface {
    @Autowired
    private NumberAvailableSeatsSMOResponseRepo numberAvailableSeatsSMOResponseRepo;

    @Autowired
    private NumberAvailableSeatsDTOService numberAvailableSeatsDTOService;

    @Override
    public void add(APIResponse apiResponse) {
        numberAvailableSeatsSMOResponseRepo.save((NumberAvailableSeatsSMOResponse) apiResponse);
    }

    @Override
    public void addAll(List<APIResponse> apiResponses) {

    }

    @Override
    public APIResponse processing(APIRequest apiRequest, APIResponse apiResponse, MedicalOrganization medicalOrganization) {
        NumberAvailableSeatsSMORequest numberAvailableSeatsSMORequest = (NumberAvailableSeatsSMORequest) apiRequest;
        NumberAvailableSeatsSMOResponse numberAvailableSeatsSMOResponse = (NumberAvailableSeatsSMOResponse) apiResponse;
        List<NumberAvailableSeatsSMOResponseRecord> numberAvailableSeatsSMOResponseRecords = new ArrayList<>();

        List<NumberAvailableSeatsDTO> numberAvailableSeatsDTOS = numberAvailableSeatsDTOService
                .getAllByMO(numberAvailableSeatsSMORequest.getCodeMO());
        numberAvailableSeatsDTOS.forEach(numberAvailableSeatsDTO -> {
            numberAvailableSeatsSMOResponseRecords.add(new NumberAvailableSeatsSMOResponseRecord(numberAvailableSeatsDTO,
                    numberAvailableSeatsSMOResponse));
        });
        numberAvailableSeatsSMOResponse.setDepartments(numberAvailableSeatsSMOResponseRecords);
        numberAvailableSeatsSMOResponse.setResultResponseCode(200);
        numberAvailableSeatsSMOResponse.setCodeMO(numberAvailableSeatsSMORequest.getCodeMO());
        numberAvailableSeatsSMOResponse.setDateBeg(Date.from(Instant.now()));
        numberAvailableSeatsSMOResponse.setDateEdit(Date.from(Instant.now()));
        add(numberAvailableSeatsSMOResponse);
        return numberAvailableSeatsSMOResponse;
    }

    public APIResponse getWithReqId(String reqID, int codeMO) {
        return numberAvailableSeatsSMOResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }
}
