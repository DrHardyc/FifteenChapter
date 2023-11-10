package ru.hardy.udio.service.apiservice.recommendationspatientservice.smo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.recommendationspatient.mo.RecommendationsPatient;
import ru.hardy.udio.domain.api.recommendationspatient.smo.ParticipantSMO;
import ru.hardy.udio.domain.api.recommendationspatient.smo.RecommendationsPatientSMORequest;
import ru.hardy.udio.domain.api.recommendationspatient.smo.RecommendationsPatientSMOResponse;
import ru.hardy.udio.domain.api.recommendationspatient.smo.RecommendationsPatientSMOResponseRecord;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.recommendationspatientrepo.smo.RecommendationsPatientSMOResponseRepo;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;
import ru.hardy.udio.service.apiservice.recommendationspatientservice.so.RecommendationsPatientService;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationsPatientSMOResponseService implements APIResponseServiceInterface {
    @Autowired
    private RecommendationsPatientSMOResponseRepo recommendationsPatientSMOResponseRepo;

    @Autowired
    private RecommendationsPatientService recommendationsPatientService;

    @Autowired
    private PeopleService peopleService;

    @Override
    public void add(APIResponse apiResponse) {
        recommendationsPatientSMOResponseRepo.save((RecommendationsPatientSMOResponse) apiResponse);
    }

    @Override
    public void addAll(List<APIResponse> apiResponses) {

    }

    @Override
    public APIResponse processing(APIRequest apiRequest, APIResponse apiResponse, MedicalOrganization medicalOrganization) {
        RecommendationsPatientSMORequest recommendationsPatientSMORequest = (RecommendationsPatientSMORequest) apiRequest;
        RecommendationsPatientSMOResponse recommendationsPatientSMOResponse = (RecommendationsPatientSMOResponse) apiResponse;

        List<RecommendationsPatientSMOResponseRecord> recommendationsPatientResponseRecords = new ArrayList<>();
        List<RecommendationsPatient> recommendationsPatients = new ArrayList<>();
        if (recommendationsPatientSMORequest.getPatients() != null && !recommendationsPatientSMORequest.getPatients().isEmpty()) {
            recommendationsPatientSMORequest.getPatients().forEach(patient -> {
                People people = peopleService.getByInsuredPerson(patient);
                recommendationsPatients.addAll(recommendationsPatientService.getAllByPeople(people));
            });
        } else {
            recommendationsPatients.addAll(recommendationsPatientService.getAll());
        }
        recommendationsPatients.forEach(recommendationsPatient -> {
            List<ParticipantSMO> participantSMOS = new ArrayList<>();
            recommendationsPatient.getRequestRecord().getParticipants().forEach(participant -> {
                participantSMOS.add(new ParticipantSMO(participant));
            });
            recommendationsPatientResponseRecords.add(new RecommendationsPatientSMOResponseRecord(recommendationsPatient.getRequestRecord(),
                    recommendationsPatientSMOResponse, participantSMOS));
        });
        recommendationsPatientSMOResponse.setPatients(recommendationsPatientResponseRecords);
        recommendationsPatientSMOResponse.setResultResponseCode(200);
        recommendationsPatientSMOResponse.setDateBeg(Date.from(Instant.now()));
        recommendationsPatientSMOResponse.setDateEdit(Date.from(Instant.now()));
        recommendationsPatientSMOResponse.setNumberRecordsProcessed(recommendationsPatientResponseRecords.size());
        add(recommendationsPatientSMOResponse);
        return recommendationsPatientSMOResponse;
    }

    public APIResponse getWithReqId(String reqID, int codeMO) {
        return recommendationsPatientSMOResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }
}
