package ru.hardy.udio.service.apiservice.apiinterface;

import ru.hardy.udio.domain.abstractclasses.APIRequest;

public interface APIRequestServiceInterface {
    void add(APIRequest apiRequest);
    boolean checkChild(APIRequest request);
}
