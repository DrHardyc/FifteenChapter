package ru.hardy.udio.service.apiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequest;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMOResponse;
import ru.hardy.udio.domain.api.hospitalization.mo.HospitalizationRequest;
import ru.hardy.udio.domain.api.hospitalization.mo.HospitalizationResponse;
import ru.hardy.udio.domain.api.hospitalization.smo.HospitalizationSMORequest;
import ru.hardy.udio.domain.api.hospitalization.smo.HospitalizationSMOResponse;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingRequest;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingResponse;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseRequest;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseResponse;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequest;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponse;
import ru.hardy.udio.domain.api.numberavailableseats.mo.NumberAvailableSeatsRequest;
import ru.hardy.udio.domain.api.numberavailableseats.mo.NumberAvailableSeatsResponse;
import ru.hardy.udio.domain.api.numberavailableseats.smo.NumberAvailableSeatsSMORequest;
import ru.hardy.udio.domain.api.numberavailableseats.smo.NumberAvailableSeatsSMOResponse;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleRequest;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleResponse;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatientRequest;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatientResponse;
import ru.hardy.udio.domain.api.padatapatients.smo.PADataPatientSMORequest;
import ru.hardy.udio.domain.api.padatapatients.smo.PADataPatientSMOResponse;
import ru.hardy.udio.domain.api.recommendationspatient.mo.RecommendationsPatientRequest;
import ru.hardy.udio.domain.api.recommendationspatient.mo.RecommendationsPatientResponse;
import ru.hardy.udio.domain.api.recommendationspatient.smo.RecommendationsPatientSMORequest;
import ru.hardy.udio.domain.api.recommendationspatient.smo.RecommendationsPatientSMOResponse;
import ru.hardy.udio.domain.api.schedulepianddispplot.mo.SchedulePIAndDispPlotRequest;
import ru.hardy.udio.domain.api.schedulepianddispplot.mo.SchedulePIAndDispPlotResponse;
import ru.hardy.udio.domain.api.schedulepianddispplot.smo.SchedulePIAndDispPlotSMORequest;
import ru.hardy.udio.domain.api.schedulepianddispplot.smo.SchedulePIAndDispPlotSMOResponse;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareRequest;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareResponse;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;
import ru.hardy.udio.service.apiservice.choosingmoservice.ChoosingMOResponseService;
import ru.hardy.udio.service.apiservice.hospitalizationservice.mo.HospitalizationResponseService;
import ru.hardy.udio.service.apiservice.hospitalizationservice.smo.HospitalizationSMOResponseService;
import ru.hardy.udio.service.apiservice.individualhistoryinformingservice.IndividualHistoryInformingResponseService;
import ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice.IndividualHistoryOnkoCaseResponseService;
import ru.hardy.udio.service.apiservice.individualinformingservice.IndividualInformingResponseService;
import ru.hardy.udio.service.apiservice.numberavailableseatsservice.mo.NumberAvailableSeatsResponseService;
import ru.hardy.udio.service.apiservice.numberavailableseatsservice.smo.NumberAvailableSeatsSMOResponseService;
import ru.hardy.udio.service.apiservice.operatingscheduleservice.OperatingScheduleResponseService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.mo.PADataPatientResponseService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.smo.PADataPatientSMOResponseService;
import ru.hardy.udio.service.apiservice.recommendationspatientservice.smo.RecommendationsPatientSMOResponseService;
import ru.hardy.udio.service.apiservice.recommendationspatientservice.so.RecommendationsPatientResponseService;
import ru.hardy.udio.service.apiservice.schedulepianddispplotservice.mo.SchedulePIAndDispPlotResponseService;
import ru.hardy.udio.service.apiservice.schedulepianddispplotservice.smo.SchedulePIAndDispPlotSMOResponseService;
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
    @Autowired
    private PADataPatientSMOResponseService paDataPatientSMOResponseService;
    @Autowired
    private SchedulePIAndDispPlotSMOResponseService schedulePIAndDispPlotSMOResponseService;
    @Autowired
    private NumberAvailableSeatsSMOResponseService numberAvailableSeatsSMOResponseService;
    @Autowired
    private HospitalizationSMOResponseService hospitalizationSMOResponseService;
    @Autowired
    private RecommendationsPatientSMOResponseService recommendationsPatientSMOResponseService;




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
        } else if (apiResponse instanceof PADataPatientSMOResponse) {
            paDataPatientSMOResponseService.add(apiResponse);
        } else if (apiResponse instanceof NumberAvailableSeatsSMOResponse) {
            numberAvailableSeatsSMOResponseService.add(apiResponse);
        } else if (apiResponse instanceof HospitalizationSMOResponse) {
            hospitalizationSMOResponseService.add(apiResponse);
        } else if (apiResponse instanceof RecommendationsPatientSMOResponse) {
            recommendationsPatientSMOResponseService.add(apiResponse);
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
        } else if (apiResponse instanceof PADataPatientSMOResponse) {
            return paDataPatientSMOResponseService.processing(apiRequest, apiResponse, medicalOrganization);
        } else if (apiResponse instanceof SchedulePIAndDispPlotSMOResponse) {
            return schedulePIAndDispPlotSMOResponseService.processing(apiRequest, apiResponse, medicalOrganization);
        } else if (apiResponse instanceof NumberAvailableSeatsSMOResponse) {
            return numberAvailableSeatsSMOResponseService.processing(apiRequest, apiResponse, medicalOrganization);
        } else if (apiResponse instanceof HospitalizationSMOResponse) {
            return hospitalizationSMOResponseService.processing(apiRequest, apiResponse, medicalOrganization);
        } else if (apiResponse instanceof RecommendationsPatientSMOResponse) {
            return recommendationsPatientSMOResponseService.processing(apiRequest, apiResponse, medicalOrganization);
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
        } else if (apiRequest instanceof PADataPatientSMORequest) {
            return paDataPatientSMOResponseService.getWithReqId(apiRequest.getReqID(), codeMO);
        } else if (apiRequest instanceof SchedulePIAndDispPlotSMORequest) {
            return schedulePIAndDispPlotSMOResponseService.getWithReqId(apiRequest.getReqID(), codeMO);
        } else if (apiRequest instanceof NumberAvailableSeatsSMORequest) {
            return numberAvailableSeatsSMOResponseService.getWithReqId(apiRequest.getReqID(), codeMO);
        } else if (apiRequest instanceof HospitalizationSMORequest) {
            return hospitalizationSMOResponseService.getWithReqId(apiRequest.getReqID(), codeMO);
        } else if (apiRequest instanceof RecommendationsPatientSMORequest) {
            return recommendationsPatientSMOResponseService.getWithReqId(apiRequest.getReqID(), codeMO);
        }
        return null;
    }
}
