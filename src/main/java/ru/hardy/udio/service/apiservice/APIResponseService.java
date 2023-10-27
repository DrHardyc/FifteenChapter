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
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;
import ru.hardy.udio.service.apiservice.choosingmoservice.ChoosingMOResponseService;
import ru.hardy.udio.service.apiservice.hospitalizationservice.HospitalizationRequestService;
import ru.hardy.udio.service.apiservice.hospitalizationservice.HospitalizationResponseService;
import ru.hardy.udio.service.apiservice.individualhistoryinformingservice.IndividualHistoryInformingResponseService;
import ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice.IndividualHistoryOnkoCaseResponseService;
import ru.hardy.udio.service.apiservice.individualinformingservice.IndividualInformingResponseService;
import ru.hardy.udio.service.apiservice.numberavailableseatsservice.NumberAvailableSeatsResponseService;
import ru.hardy.udio.service.apiservice.operatingscheduleservice.OperatingScheduleResponseService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.PADataPatientResponseService;
import ru.hardy.udio.service.apiservice.recommendationspatientservice.RecommendationsPatientResponseService;
import ru.hardy.udio.service.apiservice.schedulepianddispplotservice.SchedulePIAndDispPlotResponseService;
import ru.hardy.udio.service.apiservice.volumemedicalcareservice.VolumeMedicalCareResponseService;

import java.util.List;

@Service
public class APIResponseService implements APIResponseServiceInterface {
    @Autowired
    private ChoosingMOResponseService choosingMOResponseService;
    @Autowired
    private IndividualHistoryInformingResponseService individualHistoryInformingResponseService;
    @Autowired
    private IndividualHistoryOnkoCaseResponseService individualHistoryOnkoCaseResponseService;
    @Autowired
    private IndividualInformingResponseService individualInformingResponseService;
    @Autowired
    private NumberAvailableSeatsResponseService numberAvailableSeatsResponseService;
    @Autowired
    private OperatingScheduleResponseService operatingScheduleResponseService;
    @Autowired
    private PADataPatientResponseService paDataPatientResponseService;
    @Autowired
    private SchedulePIAndDispPlotResponseService schedulePIAndDispPlotResponseService;
    @Autowired
    private VolumeMedicalCareResponseService volumeMedicalCareResponseService;
    @Autowired
    private HospitalizationResponseService hospitalizationResponseService;
    @Autowired
    private RecommendationsPatientResponseService recommendationsPatientResponseService;


    @Override
    public void add(APIResponse apiResponse) {
        if (apiResponse instanceof ChoosingMOResponse){
            choosingMOResponseService.add(apiResponse);
        } else if (apiResponse instanceof IndividualHistoryInformingResponse){
            individualHistoryInformingResponseService.add(apiResponse);
        } else if (apiResponse instanceof IndividualHistoryOnkoCaseResponse) {
            individualHistoryOnkoCaseResponseService.add(apiResponse);
        } else if (apiResponse instanceof IndividualInformingResponse) {
            individualInformingResponseService.add(apiResponse);
        } else if (apiResponse instanceof NumberAvailableSeatsResponse) {
            numberAvailableSeatsResponseService.add(apiResponse);
        } else if (apiResponse instanceof OperatingScheduleResponse) {
            operatingScheduleResponseService.add(apiResponse);
        } else if (apiResponse instanceof PADataPatientResponse) {
            paDataPatientResponseService.add(apiResponse);
        } else if (apiResponse instanceof SchedulePIAndDispPlotResponse) {
            schedulePIAndDispPlotResponseService.add(apiResponse);
        } else if (apiResponse instanceof VolumeMedicalCareResponse) {
            volumeMedicalCareResponseService.add(apiResponse);
        } else if (apiResponse instanceof HospitalizationResponse) {
            hospitalizationResponseService.add(apiResponse);
        } else if (apiResponse instanceof RecommendationsPatientResponse) {
            recommendationsPatientResponseService.add(apiResponse);
        }
    }

    @Override
    public void addAll(List<APIResponse> apiResponses) {
        if (apiResponses instanceof ChoosingMOResponse){
            choosingMOResponseService.addAll(apiResponses);
        } else if (apiResponses instanceof IndividualHistoryInformingResponse){
            individualHistoryInformingResponseService.addAll(apiResponses);
        } else if (apiResponses instanceof IndividualHistoryOnkoCaseResponse) {
            individualHistoryOnkoCaseResponseService.addAll(apiResponses);
        } else if (apiResponses instanceof IndividualInformingResponse) {
            individualInformingResponseService.addAll(apiResponses);
        } else if (apiResponses instanceof NumberAvailableSeatsResponse) {
            numberAvailableSeatsResponseService.addAll(apiResponses);
        } else if (apiResponses instanceof OperatingScheduleResponse) {
            operatingScheduleResponseService.addAll(apiResponses);
        } else if (apiResponses instanceof PADataPatientResponse) {
            paDataPatientResponseService.addAll(apiResponses);
        } else if (apiResponses instanceof SchedulePIAndDispPlotResponse) {
            schedulePIAndDispPlotResponseService.addAll(apiResponses);
        } else if (apiResponses instanceof VolumeMedicalCareResponse) {
            volumeMedicalCareResponseService.addAll(apiResponses);
        } else if (apiResponses instanceof RecommendationsPatientResponse) {
            recommendationsPatientResponseService.addAll(apiResponses);
        }
    }

    @Override
    public APIResponse processing(APIRequest apiRequest, APIResponse apiResponse, MedicalOrganization medicalOrganization) {
        if (apiResponse instanceof ChoosingMOResponse){
            return choosingMOResponseService.processing(apiRequest, apiResponse, medicalOrganization);
        } else if (apiResponse instanceof IndividualHistoryInformingResponse){
            return individualHistoryInformingResponseService.processing(apiRequest, apiResponse, medicalOrganization);
        } else if (apiResponse instanceof IndividualHistoryOnkoCaseResponse) {
            return individualHistoryOnkoCaseResponseService.processing(apiRequest, apiResponse, medicalOrganization);
        } else if (apiResponse instanceof IndividualInformingResponse) {
            return individualInformingResponseService.processing(apiRequest, apiResponse, medicalOrganization);
        } else if (apiResponse instanceof NumberAvailableSeatsResponse) {
            return numberAvailableSeatsResponseService.processing(apiRequest, apiResponse, medicalOrganization);
        } else if (apiResponse instanceof OperatingScheduleResponse) {
            return operatingScheduleResponseService.processing(apiRequest, apiResponse, medicalOrganization);
        } else if (apiResponse instanceof PADataPatientResponse) {
            return paDataPatientResponseService.processing(apiRequest, apiResponse, medicalOrganization);
        } else if (apiResponse instanceof SchedulePIAndDispPlotResponse) {
            return schedulePIAndDispPlotResponseService.processing(apiRequest, apiResponse, medicalOrganization);
        } else if (apiResponse instanceof VolumeMedicalCareResponse) {
            return volumeMedicalCareResponseService.processing(apiRequest, apiResponse, medicalOrganization);
        } else if (apiResponse instanceof HospitalizationResponse) {
            return hospitalizationResponseService.processing(apiRequest, apiResponse, medicalOrganization);
        } else if (apiResponse instanceof RecommendationsPatientResponse) {
            return recommendationsPatientResponseService.processing(apiRequest, apiResponse, medicalOrganization);
        }
        return null;
    }

    public APIResponse getWithReqId(APIRequest apiRequest, int codeMO) {
        if (apiRequest instanceof ChoosingMORequest){
            return choosingMOResponseService.getWithReqId(apiRequest.getReqID(), codeMO);
        } else if (apiRequest instanceof IndividualHistoryInformingRequest){
            return individualHistoryInformingResponseService.getWithReqId(apiRequest.getReqID(), codeMO);
        } else if (apiRequest instanceof IndividualHistoryOnkoCaseRequest) {
            return individualHistoryOnkoCaseResponseService.getWithReqId(apiRequest.getReqID(), codeMO);
        } else if (apiRequest instanceof IndividualInformingRequest) {
            return individualInformingResponseService.getWithReqId(apiRequest.getReqID(), codeMO);
        } else if (apiRequest instanceof NumberAvailableSeatsRequest) {
            return numberAvailableSeatsResponseService.getWithReqId(apiRequest.getReqID(), codeMO);
        } else if (apiRequest instanceof OperatingScheduleRequest) {
            return operatingScheduleResponseService.getWithReqId(apiRequest.getReqID(), codeMO);
        } else if (apiRequest instanceof PADataPatientRequest) {
            return paDataPatientResponseService.getWithReqId(apiRequest.getReqID(), codeMO);
        } else if (apiRequest instanceof SchedulePIAndDispPlotRequest) {
            return schedulePIAndDispPlotResponseService.getWithReqId(apiRequest.getReqID(), codeMO);
        } else if (apiRequest instanceof VolumeMedicalCareRequest) {
            return volumeMedicalCareResponseService.getWithReqId(apiRequest.getReqID(), codeMO);
        } else if (apiRequest instanceof HospitalizationRequest) {
            return hospitalizationResponseService.getWithReqId(apiRequest.getReqID(), codeMO);
        } else if (apiRequest instanceof RecommendationsPatientRequest) {
            return recommendationsPatientResponseService.getWithReqId(apiRequest.getReqID(), codeMO);
        }
        return null;
    }
}
