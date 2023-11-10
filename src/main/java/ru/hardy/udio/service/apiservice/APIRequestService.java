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
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.apiinterface.APIRequestServiceInterface;
import ru.hardy.udio.service.apiservice.choosingmoservice.ChoosingMORequestService;
import ru.hardy.udio.service.apiservice.hospitalizationservice.mo.HospitalizationRequestService;
import ru.hardy.udio.service.apiservice.hospitalizationservice.mo.HospitalizationResponseService;
import ru.hardy.udio.service.apiservice.hospitalizationservice.smo.HospitalizationSMORequestService;
import ru.hardy.udio.service.apiservice.individualhistoryinformingservice.IndividualHistoryInformingRequestService;
import ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice.IndividualHistoryOnkoCaseRequestService;
import ru.hardy.udio.service.apiservice.individualinformingservice.IndividualInformingRequestService;
import ru.hardy.udio.service.apiservice.numberavailableseatsservice.mo.NumberAvailableSeatsRequestService;
import ru.hardy.udio.service.apiservice.numberavailableseatsservice.smo.NumberAvailableSeatsSMORequestService;
import ru.hardy.udio.service.apiservice.operatingscheduleservice.OperatingScheduleRequestService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.mo.PADataPatientRequestService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.smo.PADataPatientSMORequestService;
import ru.hardy.udio.service.apiservice.recommendationspatientservice.smo.RecommendationsPatientSMORequestService;
import ru.hardy.udio.service.apiservice.recommendationspatientservice.so.RecommendationsPatientRequestService;
import ru.hardy.udio.service.apiservice.schedulepianddispplotservice.mo.SchedulePIAndDispPlotRequestService;
import ru.hardy.udio.service.apiservice.schedulepianddispplotservice.smo.SchedulePIAndDispPlotSMORequestService;
import ru.hardy.udio.service.apiservice.volumemedicalcareservice.VolumeMedicalCareRequestService;
import ru.hardy.udio.service.nsiservice.MedicalOrganizationService;

import java.time.Instant;
import java.util.Date;

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
    private MedicalOrganizationService medicalOrganizationService;
    @Autowired
    private PADataPatientSMORequestService paDataPatientSMORequestService;
    @Autowired
    private SchedulePIAndDispPlotSMORequestService schedulePIAndDispPlotSMORequestService;
    @Autowired
    private NumberAvailableSeatsSMORequestService numberAvailableSeatsSMORequestService;
    @Autowired
    private HospitalizationSMORequestService hospitalizationSMORequestService;
    @Autowired
    private RecommendationsPatientSMORequestService recommendationsPatientSMORequestService;


    @Override
    public void add(APIRequest apiRequest) {
        apiRequest.setDateBeg(Date.from(Instant.now()));
        apiRequest.setDateEdit(Date.from(Instant.now()));
        switch (apiRequest) {
            case ChoosingMORequest choosingMORequest -> choosingMORequestService.add(apiRequest);
            case IndividualHistoryInformingRequest individualHistoryInformingRequest ->
                    individualHistoryInformingRequestService.add(apiRequest);
            case IndividualHistoryOnkoCaseRequest individualHistoryOnkoCaseRequest ->
                    individualHistoryOnkoCaseRequestService.add(apiRequest);
            case IndividualInformingRequest individualInformingRequest ->
                    individualInformingRequestService.add(apiRequest);
            case NumberAvailableSeatsRequest numberAvailableSeatsRequest ->
                    numberAvailableSeatsRequestService.add(apiRequest);
            case OperatingScheduleRequest operatingScheduleRequest -> operatingScheduleRequestService.add(apiRequest);
            case PADataPatientRequest paDataPatientRequest -> paDataPatientRequestService.add(apiRequest);
            case SchedulePIAndDispPlotRequest schedulePIAndDispPlotRequest ->
                    schedulePIAndDispPlotRequestService.add(apiRequest);
            case VolumeMedicalCareRequest volumeMedicalCareRequest -> volumeMedicalCareRequestService.add(apiRequest);
            case HospitalizationRequest hospitalizationRequest -> hospitalizationRequestService.add(apiRequest);
            case RecommendationsPatientRequest recommendationsPatientRequest ->
                    recommendationsPatientRequestService.add(apiRequest);
            case PADataPatientSMORequest paDataPatientSMORequest -> paDataPatientSMORequestService.add(apiRequest);
            case SchedulePIAndDispPlotSMORequest schedulePIAndDispPlotSMORequest ->
                    schedulePIAndDispPlotSMORequestService.add(apiRequest);
            case NumberAvailableSeatsSMORequest numberAvailableSeatsSMORequest ->
                    numberAvailableSeatsSMORequestService.add(apiRequest);
            case HospitalizationSMORequest hospitalizationSMORequest ->
                    hospitalizationSMORequestService.add(apiRequest);
            case RecommendationsPatientSMORequest recommendationsPatientSMORequest ->
                    recommendationsPatientSMORequestService.add(apiRequest);
            default -> {
            }
        }
    }

    @Override
    public boolean checkChild(APIRequest apiRequest) {
        return switch (apiRequest) {
            case ChoosingMORequest choosingMORequest -> choosingMORequestService.checkChild(apiRequest);
            case IndividualHistoryInformingRequest individualHistoryInformingRequest -> true;
            case IndividualHistoryOnkoCaseRequest individualHistoryOnkoCaseRequest ->
                    individualHistoryOnkoCaseRequestService.checkChild(apiRequest);
            case IndividualInformingRequest individualInformingRequest ->
                    individualInformingRequestService.checkChild(apiRequest);
            case NumberAvailableSeatsRequest numberAvailableSeatsRequest ->
                    numberAvailableSeatsResponseService.checkChild(apiRequest);
            case OperatingScheduleRequest operatingScheduleRequest ->
                    operatingScheduleResponseService.checkChild(apiRequest);
            case PADataPatientRequest paDataPatientRequest -> paDataPatientResponseService.checkChild(apiRequest);
            case SchedulePIAndDispPlotRequest schedulePIAndDispPlotRequest ->
                    schedulePIAndDispPlotResponseService.checkChild(apiRequest);
            case VolumeMedicalCareRequest volumeMedicalCareRequest ->
                    volumeMedicalCareResponseService.checkChild(apiRequest);
            case HospitalizationRequest hospitalizationRequest -> hospitalizationResponseService.checkChild(apiRequest);
            case RecommendationsPatientRequest recommendationsPatientRequest ->
                    recommendationsPatientRequestService.checkChild(apiRequest);
            case SchedulePIAndDispPlotSMORequest schedulePIAndDispPlotSMORequest ->
                    schedulePIAndDispPlotSMORequestService.checkChild(apiRequest);
            case NumberAvailableSeatsSMORequest numberAvailableSeatsSMORequest ->
                    numberAvailableSeatsSMORequestService.checkChild(apiRequest);
            case HospitalizationSMORequest hospitalizationSMORequest ->
                    hospitalizationSMORequestService.checkChild(apiRequest);
            case RecommendationsPatientSMORequest recommendationsPatientSMORequest ->
                    recommendationsPatientSMORequestService.checkChild(apiRequest);
            case null, default -> apiRequest instanceof PADataPatientSMORequest;
        };
    }

    public APIResponse acceptance(String token, APIRequest apiRequest) {
        int codeMO = tokenService.getCodeMOWithToken(token);
        APIResponse apiResponse = getInstanceResponse(apiRequest);
        if (apiResponse != null) {
            if (tokenService.checkToken(token)) {
                try {
                    setMedicalOrganization(apiRequest, medicalOrganizationService.getByCode(codeMO));
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
                    return apiResponseService.processing(apiRequest, apiResponse,
                            medicalOrganizationService.getByCode(tokenService.getCodeMOWithToken(token)));
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

    private void setMedicalOrganization(APIRequest apiRequest, MedicalOrganization medicalOrganization) {
        if (apiRequest instanceof ChoosingMORequest){
            ((ChoosingMORequest) apiRequest).setMedicalOrganization(medicalOrganization);
        } else if (apiRequest instanceof IndividualHistoryInformingRequest){
            ((IndividualHistoryInformingRequest) apiRequest).setMedicalOrganization(medicalOrganization);
        } else if (apiRequest instanceof IndividualHistoryOnkoCaseRequest) {
            ((IndividualHistoryOnkoCaseRequest) apiRequest).setMedicalOrganization(medicalOrganization);
        } else if (apiRequest instanceof IndividualInformingRequest) {
            ((IndividualInformingRequest) apiRequest).setMedicalOrganization(medicalOrganization);
        } else if (apiRequest instanceof NumberAvailableSeatsRequest) {
            ((NumberAvailableSeatsRequest) apiRequest).setMedicalOrganization(medicalOrganization);
        } else if (apiRequest instanceof OperatingScheduleRequest) {
            ((OperatingScheduleRequest) apiRequest).setMedicalOrganization(medicalOrganization);
        } else if (apiRequest instanceof PADataPatientRequest) {
            ((PADataPatientRequest) apiRequest).setMedicalOrganization(medicalOrganization);
        } else if (apiRequest instanceof SchedulePIAndDispPlotRequest) {
            ((SchedulePIAndDispPlotRequest) apiRequest).setMedicalOrganization(medicalOrganization);
        } else if (apiRequest instanceof VolumeMedicalCareRequest) {
            ((VolumeMedicalCareRequest) apiRequest).setMedicalOrganization(medicalOrganization);
        } else if (apiRequest instanceof HospitalizationRequest) {
            ((HospitalizationRequest) apiRequest).setMedicalOrganization(medicalOrganization);
        } else if (apiRequest instanceof RecommendationsPatientRequest) {
            ((RecommendationsPatientRequest) apiRequest).setMedicalOrganization(medicalOrganization);
        }
    }

    public APIResponse testAcceptance(String token, APIRequest apiRequest) {
        int codeMO = tokenService.getCodeMOWithToken(token);
        APIResponse apiResponse = getInstanceResponse(apiRequest);
        if (apiResponse != null) {
            if (tokenService.checkToken(token)) {
                try {
                    setMedicalOrganization(apiRequest, medicalOrganizationService.getByCode(codeMO));
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
                    return apiResponseService.processing(apiRequest, apiResponse,
                            medicalOrganizationService.getByCode(tokenService.getCodeMOWithToken(token)));
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
        } else if (apiRequest instanceof RecommendationsPatientRequest) {
            ((RecommendationsPatientResponse) apiResponse).setRequest((RecommendationsPatientRequest) apiRequest);
        } else if (apiRequest instanceof PADataPatientSMORequest) {
            ((PADataPatientSMOResponse) apiResponse).setRequest((PADataPatientSMORequest) apiRequest);
        } else if (apiRequest instanceof SchedulePIAndDispPlotSMORequest) {
            ((SchedulePIAndDispPlotSMOResponse) apiResponse).setRequest((SchedulePIAndDispPlotSMORequest) apiRequest);
        } else if (apiRequest instanceof NumberAvailableSeatsSMORequest) {
            ((NumberAvailableSeatsSMOResponse) apiResponse).setRequest((NumberAvailableSeatsSMORequest) apiRequest);
        } else if (apiRequest instanceof HospitalizationSMORequest) {
            ((HospitalizationSMOResponse) apiResponse).setRequest((HospitalizationSMORequest) apiRequest);
        } else if (apiRequest instanceof RecommendationsPatientSMORequest) {
            ((RecommendationsPatientSMOResponse) apiResponse).setRequest((RecommendationsPatientSMORequest) apiRequest);
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
        } else if (apiRequest instanceof PADataPatientSMORequest) {
            return new PADataPatientSMOResponse();
        } else if (apiRequest instanceof SchedulePIAndDispPlotSMORequest) {
            return new SchedulePIAndDispPlotSMOResponse();
        } else if (apiRequest instanceof NumberAvailableSeatsSMORequest) {
            return new NumberAvailableSeatsSMOResponse();
        } else if (apiRequest instanceof HospitalizationSMORequest) {
            return new HospitalizationSMOResponse();
        } else if (apiRequest instanceof RecommendationsPatientSMORequest) {
            return new RecommendationsPatientSMOResponse();
        }
        return null;
    }
}
