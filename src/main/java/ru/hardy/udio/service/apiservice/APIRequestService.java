package ru.hardy.udio.service.apiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequest;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMOResponse;
import ru.hardy.udio.domain.api.hospitalization.HospitalizationRequest;
import ru.hardy.udio.domain.api.hospitalization.HospitalizationResponse;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingRequest;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingResponse;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseRequest;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseResponse;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequest;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponse;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequest;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsResponse;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleRequest;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleResponse;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequest;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientResponse;
import ru.hardy.udio.domain.api.recommendationspatient.RecommendationsPatientRequest;
import ru.hardy.udio.domain.api.recommendationspatient.RecommendationsPatientResponse;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequest;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotResponse;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareRequest;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareResponse;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.apiinterface.APIRequestServiceInterface;
import ru.hardy.udio.service.apiservice.choosingmoservice.ChoosingMORequestService;
import ru.hardy.udio.service.apiservice.hospitalizationservice.HospitalizationRequestService;
import ru.hardy.udio.service.apiservice.hospitalizationservice.HospitalizationResponseService;
import ru.hardy.udio.service.apiservice.individualhistoryinformingservice.IndividualHistoryInformingRequestService;
import ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice.IndividualHistoryOnkoCaseRequestService;
import ru.hardy.udio.service.apiservice.individualinformingservice.IndividualInformingRequestService;
import ru.hardy.udio.service.apiservice.numberavailableseatsservice.NumberAvailableSeatsRequestService;
import ru.hardy.udio.service.apiservice.operatingscheduleservice.OperatingScheduleRequestService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.PADataPatientRequestService;
import ru.hardy.udio.service.apiservice.recommendationspatientservice.RecommendationsPatientRequestService;
import ru.hardy.udio.service.apiservice.recommendationspatientservice.RecommendationsPatientResponseService;
import ru.hardy.udio.service.apiservice.schedulepianddispplotservice.SchedulePIAndDispPlotRequestService;
import ru.hardy.udio.service.apiservice.volumemedicalcareservice.VolumeMedicalCareRequestService;

@Service
public class APIRequestService implements APIRequestServiceInterface {

    @Autowired
    private ChoosingMORequestService choosingMORequestService;
    @Autowired
    private IndividualHistoryInformingRequestService individualHistoryInformingRequestService;
    @Autowired
    private IndividualHistoryOnkoCaseRequestService individualHistoryOnkoCaseRequestService;
    @Autowired
    private IndividualInformingRequestService individualInformingRequestService;
    @Autowired
    private NumberAvailableSeatsRequestService numberAvailableSeatsResponseService;
    @Autowired
    private OperatingScheduleRequestService operatingScheduleResponseService;
    @Autowired
    private PADataPatientRequestService paDataPatientResponseService;
    @Autowired
    private SchedulePIAndDispPlotRequestService schedulePIAndDispPlotResponseService;
    @Autowired
    private VolumeMedicalCareRequestService volumeMedicalCareResponseService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private APIResponseService apiResponseService;
    @Autowired
    private NumberAvailableSeatsRequestService numberAvailableSeatsRequestService;
    @Autowired
    private OperatingScheduleRequestService operatingScheduleRequestService;
    @Autowired
    private PADataPatientRequestService paDataPatientRequestService;
    @Autowired
    private SchedulePIAndDispPlotRequestService schedulePIAndDispPlotRequestService;
    @Autowired
    private VolumeMedicalCareRequestService volumeMedicalCareRequestService;
    @Autowired
    private HospitalizationRequestService hospitalizationRequestService;
    @Autowired
    private HospitalizationResponseService hospitalizationResponseService;
    @Autowired
    private RecommendationsPatientRequestService recommendationsPatientRequestService;
    @Autowired
    private RecommendationsPatientResponseService recommendationsPatientResponseService;


    @Override
    public void add(APIRequest apiRequest) {
        if (apiRequest instanceof ChoosingMORequest){
            choosingMORequestService.add(apiRequest);
        } else if (apiRequest instanceof IndividualHistoryInformingRequest){
            individualHistoryInformingRequestService.add(apiRequest);
        } else if (apiRequest instanceof IndividualHistoryOnkoCaseRequest) {
            individualHistoryOnkoCaseRequestService.add(apiRequest);
        } else if (apiRequest instanceof IndividualInformingRequest) {
            individualInformingRequestService.add(apiRequest);
        } else if (apiRequest instanceof NumberAvailableSeatsRequest) {
            numberAvailableSeatsRequestService.add(apiRequest);
        } else if (apiRequest instanceof OperatingScheduleRequest) {
            operatingScheduleRequestService.add(apiRequest);
        } else if (apiRequest instanceof PADataPatientRequest) {
            paDataPatientRequestService.add(apiRequest);
        } else if (apiRequest instanceof SchedulePIAndDispPlotRequest) {
            schedulePIAndDispPlotRequestService.add(apiRequest);
        } else if (apiRequest instanceof VolumeMedicalCareRequest) {
            volumeMedicalCareRequestService.add(apiRequest);
        } else if (apiRequest instanceof HospitalizationRequest) {
            hospitalizationRequestService.add(apiRequest);
        } else if (apiRequest instanceof RecommendationsPatientRequest) {
            recommendationsPatientRequestService.add(apiRequest);
        }
    }

    @Override
    public boolean checkChild(APIRequest apiRequest) {
        if (apiRequest instanceof ChoosingMORequest){
            return choosingMORequestService.checkChild(apiRequest);
        } else if (apiRequest instanceof IndividualHistoryInformingRequest){
            return true;
        } else if (apiRequest instanceof IndividualHistoryOnkoCaseRequest) {
            return individualHistoryOnkoCaseRequestService.checkChild(apiRequest);
        } else if (apiRequest instanceof IndividualInformingRequest) {
            return individualInformingRequestService.checkChild(apiRequest);
        } else if (apiRequest instanceof NumberAvailableSeatsRequest) {
            return numberAvailableSeatsResponseService.checkChild(apiRequest);
        } else if (apiRequest instanceof OperatingScheduleRequest) {
            return operatingScheduleResponseService.checkChild(apiRequest);
        } else if (apiRequest instanceof PADataPatientRequest) {
            return paDataPatientResponseService.checkChild(apiRequest);
        } else if (apiRequest instanceof SchedulePIAndDispPlotRequest) {
            return schedulePIAndDispPlotResponseService.checkChild(apiRequest);
        } else if (apiRequest instanceof VolumeMedicalCareRequest) {
            return volumeMedicalCareResponseService.checkChild(apiRequest);
        } else if (apiRequest instanceof HospitalizationRequest) {
            return hospitalizationResponseService.checkChild(apiRequest);
        } else if (apiRequest instanceof RecommendationsPatientRequest) {
            return recommendationsPatientRequestService.checkChild(apiRequest);
        }
        return false;
    }

    public APIResponse acceptance(String token, APIRequest apiRequest) {
        int codeMO = tokenService.getCodeMOWithToken(token);
        APIResponse apiResponse = getInstanceResponse(apiRequest);
        if (apiResponse != null) {
            if (tokenService.checkToken(token)) {
                try {
                    apiRequest.setCodeMO(codeMO);
                    add(apiRequest);
                    setRequestForResponse(apiRequest, apiResponse);
                    if (apiResponseService.getWithReqId(apiRequest, codeMO) != null) {
                        apiResponse.setCodeMO(codeMO);
                        apiResponse.setResultResponseCode(402);
                        apiResponseService.add(apiResponse);
                        return apiResponse;
                    }

                    apiResponse.setResultResponseCode(201);
                    apiResponse.setCodeMO(codeMO);
                    apiResponse.setReqID(apiRequest.getReqID());
                    apiResponseService.add(apiResponse);
                    if (!checkChild(apiRequest)) {
                        apiResponse.setResultResponseCode(400);
                        apiResponseService.add(apiResponse);
                        return apiResponse;
                    }
                    return apiResponseService.processing(apiRequest, apiResponse, codeMO);
                } catch (Exception e) {
                    apiResponse.setResultResponseCode(400);
                    apiResponse.setResultRequestMess(e.getMessage());
                    apiResponseService.add(apiResponse);
                }
            } else {
                apiResponse.setResultResponseCode(403);
                apiResponseService.add(apiResponse);
            }
        }
        return apiResponse;
    }

    public APIResponse testAcceptance(String token, APIRequest apiRequest) {
        int codeMO = tokenService.getCodeMOWithToken(token);
        APIResponse apiResponse = getInstanceResponse(apiRequest);
        if (apiResponse != null) {
            if (tokenService.checkToken(token)) {
                try {
                    apiRequest.setCodeMO(codeMO);
                    setRequestForResponse(apiRequest, apiResponse);
                    if (apiResponseService.getWithReqId(apiRequest, codeMO) != null) {
                        apiResponse.setCodeMO(codeMO);
                        apiResponse.setResultResponseCode(402);
                        return apiResponse;
                    }

                    apiResponse.setResultResponseCode(201);
                    apiResponse.setCodeMO(codeMO);
                    apiResponse.setReqID(apiRequest.getReqID());
                    if (!checkChild(apiRequest)) {
                        apiResponse.setResultResponseCode(400);
                        return apiResponse;
                    }
                    return apiResponseService.processing(apiRequest, apiResponse, codeMO);
                } catch (Exception e) {
                    apiResponse.setResultResponseCode(400);
                    apiResponse.setResultRequestMess(e.getMessage());
                }
            } else {
                apiResponse.setResultResponseCode(403);
            }
        }
        return apiResponse;
    }

    private void setRequestForResponse(APIRequest apiRequest, APIResponse apiResponse) {
        if (apiRequest instanceof ChoosingMORequest){
            ((ChoosingMOResponse) apiResponse).setRequest((ChoosingMORequest) apiRequest);
        } else if (apiRequest instanceof IndividualHistoryInformingRequest){
            ((IndividualHistoryInformingResponse) apiResponse).setRequest((IndividualHistoryInformingRequest) apiRequest);
        } else if (apiRequest instanceof IndividualHistoryOnkoCaseRequest) {
            ((IndividualHistoryOnkoCaseResponse) apiResponse).setRequest((IndividualHistoryOnkoCaseRequest) apiRequest);
        } else if (apiRequest instanceof IndividualInformingRequest) {
            ((IndividualInformingResponse) apiResponse).setRequest((IndividualInformingRequest) apiRequest);
        } else if (apiRequest instanceof NumberAvailableSeatsRequest) {
            ((NumberAvailableSeatsResponse) apiResponse).setRequest((NumberAvailableSeatsRequest) apiRequest);
        } else if (apiRequest instanceof OperatingScheduleRequest) {
            ((OperatingScheduleResponse) apiResponse).setRequest((OperatingScheduleRequest) apiRequest);
        } else if (apiRequest instanceof PADataPatientRequest) {
            ((PADataPatientResponse) apiResponse).setRequest((PADataPatientRequest) apiRequest);
        } else if (apiRequest instanceof SchedulePIAndDispPlotRequest) {
            ((SchedulePIAndDispPlotResponse) apiResponse).setRequest((SchedulePIAndDispPlotRequest) apiRequest);
        } else if (apiRequest instanceof VolumeMedicalCareRequest) {
            ((VolumeMedicalCareResponse) apiResponse).setRequest((VolumeMedicalCareRequest) apiRequest);
        } else if (apiRequest instanceof HospitalizationRequest) {
            ((HospitalizationResponse) apiResponse).setRequest((HospitalizationRequest) apiRequest);
        }
    }

    private APIResponse getInstanceResponse(APIRequest apiRequest) {
        if (apiRequest instanceof ChoosingMORequest){
            return new ChoosingMOResponse();
        } else if (apiRequest instanceof IndividualHistoryInformingRequest){
            return new IndividualHistoryInformingResponse();
        } else if (apiRequest instanceof IndividualHistoryOnkoCaseRequest) {
            return new IndividualHistoryOnkoCaseResponse();
        } else if (apiRequest instanceof IndividualInformingRequest) {
            return new IndividualInformingResponse();
        } else if (apiRequest instanceof NumberAvailableSeatsRequest) {
            return new NumberAvailableSeatsResponse();
        } else if (apiRequest instanceof OperatingScheduleRequest) {
            return new OperatingScheduleResponse();
        } else if (apiRequest instanceof PADataPatientRequest) {
            return new PADataPatientResponse();
        } else if (apiRequest instanceof SchedulePIAndDispPlotRequest) {
            return new SchedulePIAndDispPlotResponse();
        } else if (apiRequest instanceof VolumeMedicalCareRequest) {
            return new VolumeMedicalCareResponse();
        } else if (apiRequest instanceof HospitalizationRequest) {
            return new HospitalizationResponse();
        } else if (apiRequest instanceof RecommendationsPatientRequest) {
            return new RecommendationsPatientResponse();
        }
        return null;
    }
}
