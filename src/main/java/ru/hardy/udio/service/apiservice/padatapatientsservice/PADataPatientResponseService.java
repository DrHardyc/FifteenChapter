package ru.hardy.udio.service.apiservice.padatapatientsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.padatapatients.*;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.padatapatientsrepo.PADataPatientResponseRepo;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.ExamService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PADataPatientResponseService {

    @Autowired
    private PADataPatientResponseRepo paDataPatientResponseRepo;

    @Autowired
    private ExamService examService;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private PADataPatientResponseRecordService paDataPatientResponseRecordService;

    @Autowired
    private PADataPatientService doDataPatientsService;

    public void add(PADataPatientResponse paDataPatientResponse){
        paDataPatientResponseRepo.save(paDataPatientResponse);
    }

    public PADataPatientResponse getWithReqId(String reqID, int codeMO) {
        return paDataPatientResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }

    public PADataPatientResponse processing(PADataPatientRequest paDataPatientRequest,
                                            PADataPatientResponse paDataPatientResponse, int codeMO) {
        String errMess;
        int errCode;
        int count = 0;
        List<PADataPatientResponseRecord> paDataPatientResponseRecords = new ArrayList<>();
        for (PADataPatientRequestRecord paDataPatientRequestRecord : paDataPatientRequest.getPatients()){

            People people = peopleService.search(paDataPatientRequestRecord);
            if (people != null) {
                errCode = 500;
                errMess = "Успешное выполнение обработки записи";
                if (examService.checkEmptyString(paDataPatientRequestRecord.getSurname())) {
                    errCode = 504;
                    errMess = "|Ошибка распознавания поля 'surname'|";
                }
                if (examService.checkEmptyString(paDataPatientRequestRecord.getName())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'name'|";
                }
                if (examService.checkEmptyString(paDataPatientRequestRecord.getPatronymic())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'Patronymic'|";
                }
                if (examService.checkEmptyDate(paDataPatientRequestRecord.getDateBirth())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'dateBirth' : " + paDataPatientRequestRecord.getDateBirth() + "|";
                }
                if (examService.checkEnp(paDataPatientRequestRecord.getEnp())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'enp' : " + paDataPatientRequestRecord.getEnp() + "|";
                }
//                if (!examService.checkSpecialtyDoctorCode(paDataPatientsRequestRecord.getSpecialtyDoctorCode())) {
//                    errCode = 504;
//                    errMess = errMess + "|Ошибка распознавания поля 'specialtyDoctorCode' : " + paDataPatientsRequestRecord.getSpecialtyDoctorCode() + "|";
//                }
//                if (!examService.checkResultDispensaryAppointment(paDataPatientsRequestRecord.getResultDispensaryAppointment())) {
//                    errCode = 504;
//                    errMess = errMess + "|Ошибка распознавания поля 'resultDispensaryAppointment' : " + paDataPatientsRequestRecord.getResultDispensaryAppointment() + "|";
//                }
//                if (!examService.checkResultDispensaryAppointment(paDataPatientsRequestRecord.getResultDispensaryAppointmentDoctor())) {
//                    errCode = 504;
//                    errMess = errMess + "|Ошибка распознавания поля 'resultDispensaryAppointmentDoc' : " + paDataPatientsRequestRecord.getResultDispensaryAppointmentDoctor() + "|";
//                }
                if (doDataPatientsService.checkPatient(people, codeMO, paDataPatientRequestRecord.getMainDiagnosis(),
                        paDataPatientRequestRecord.getCodeTypePreventiveActions(),
                        paDataPatientRequestRecord.getDateInsuranceCase())){
                    errCode = 502;
                    errMess = "Пациент c таким диагнозом ранее был добавлен для данной МО";
                } else if (errCode == 500) {
                    PADataPatient paDataPatient = doDataPatientsService.searchPatient(
                            people, codeMO, paDataPatientRequestRecord.getMainDiagnosis(),
                            paDataPatientRequestRecord.getCodeTypePreventiveActions());
                    if (paDataPatient != null)
                    {
                        errMess = "Запись успешно обновлена";
                        paDataPatient.setRequestRecord(paDataPatientRequestRecord);
                        paDataPatient.setDateEdit(Date.from(Instant.now()));
                        doDataPatientsService.update(paDataPatient);
                    } else {
                        errMess = "Запись успешно добавлена";
                        doDataPatientsService.add(people, paDataPatientRequestRecord, codeMO);
                    }
                }

            } else {
                errCode = 503;
                errMess = "Ошибка поиска в СРЗ";
            }

            paDataPatientResponseRecords.add(new PADataPatientResponseRecord(paDataPatientRequestRecord, paDataPatientResponse,
                    errCode, errMess));
            count++;
            paDataPatientResponse.setNumberRecordsProcessed(count);
            add(paDataPatientResponse);
        }
        paDataPatientResponseRecordService.addAll(paDataPatientResponseRecords);
        paDataPatientResponse.setResultRequestCode(200);
        paDataPatientResponse.setPatients(paDataPatientResponseRecords);
        paDataPatientResponse.setReqID(paDataPatientRequest.getReqID());
        add(paDataPatientResponse);

        return paDataPatientResponse;
    }
}
