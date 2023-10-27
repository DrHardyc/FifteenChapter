package ru.hardy.udio.service.apiservice.apiinterface;

import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.nsi.MedicalOrganization;

import java.util.List;

public interface APIResponseServiceInterface {
    void add(APIResponse apiResponse);
    void addAll(List<APIResponse> apiResponses);

    APIResponse processing(APIRequest apiRequest, APIResponse apiResponse, MedicalOrganization medicalOrganization);
}
