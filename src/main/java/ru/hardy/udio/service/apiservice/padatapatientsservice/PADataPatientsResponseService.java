package ru.hardy.udio.service.apiservice.padatapatientsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.padatapatients.*;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.padatapatientsrepo.PADataPatientsResponseRepo;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.ExamService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PADataPatientsResponseService {

    @Autowired
    private PADataPatientsResponseRepo paDataPatientsResponseRepo;

    @Autowired
    private ExamService examService;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private PADataPatientsResponseRecordService paDataPatientsResponseRecordService;

    @Autowired
    private PADataPatientsService doDataPatientsService;

    public void add(PADataPatientsResponse paDataPatientsResponse){
        paDataPatientsResponseRepo.save(paDataPatientsResponse);
    }

    public PADataPatientsResponse getWithReqId(String reqID, int codeMO) {
        return paDataPatientsResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }

    public PADataPatientsResponse processing(PADataPatientsRequest paDataPatientsRequest,
                                             PADataPatientsResponse paDataPatientsResponse, int codeMO) {
        String errMess;
        int errCode;
        int count = 0;
        List<PADataPatientsResponseRecord> paDataPatientsResponseRecords = new ArrayList<>();
        for (PADataPatientsRequestRecord paDataPatientsRequestRecord : paDataPatientsRequest.getPatients()){

            People people = peopleService.search(paDataPatientsRequestRecord);
            if (people != null) {
                errCode = 500;
                errMess = "Успешное выполнение обработки записи";
                if (examService.checkEmptyString(paDataPatientsRequestRecord.getSurname())) {
                    errCode = 504;
                    errMess = "|Ошибка распознавания поля 'surname'|";
                }
                if (examService.checkEmptyString(paDataPatientsRequestRecord.getName())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'name'|";
                }
                if (examService.checkEmptyString(paDataPatientsRequestRecord.getPatronymic())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'Patronymic'|";
                }
                if (examService.checkEmptyDate(paDataPatientsRequestRecord.getDateBirth())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'dateBirth' : " + paDataPatientsRequestRecord.getDateBirth() + "|";
                }
                if (examService.checkEnp(paDataPatientsRequestRecord.getEnp())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'enp' : " + paDataPatientsRequestRecord.getEnp() + "|";
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
                if (doDataPatientsService.checkPatient(people, codeMO, paDataPatientsRequestRecord.getMainDiagnosis(),
                        paDataPatientsRequestRecord.getCodeTypePreventiveActions(),
                        paDataPatientsRequestRecord.getDateInsuranceCase())){
                    errCode = 502;
                    errMess = "Пациент c таким диагнозом ранее был добавлен для данной МО";
                } else if (errCode == 500) {
                    PADataPatient paDataPatient = doDataPatientsService.searchPatient(
                            people, codeMO, paDataPatientsRequestRecord.getMainDiagnosis(),
                            paDataPatientsRequestRecord.getCodeTypePreventiveActions());
                    if (paDataPatient != null)
                    {
                        errMess = "Запись успешно обновлена";
                        paDataPatient.setRequestRecord(paDataPatientsRequestRecord);
                        paDataPatient.setDateEdit(Date.from(Instant.now()));
                        doDataPatientsService.update(paDataPatient);
                    } else {
                        errMess = "Запись успешно добавлена";
                        doDataPatientsService.add(people, paDataPatientsRequestRecord, codeMO);
                    }
                }

            } else {
                errCode = 503;
                errMess = "Ошибка поиска в СРЗ";
            }

            paDataPatientsResponseRecords.add(new PADataPatientsResponseRecord(paDataPatientsRequestRecord, paDataPatientsResponse,
                    errCode, errMess));
            count++;
            paDataPatientsResponse.setNumberRecordsProcessed(count);
            add(paDataPatientsResponse);
        }
        paDataPatientsResponseRecordService.addAll(paDataPatientsResponseRecords);
        paDataPatientsResponse.setResultRequestCode(200);
        paDataPatientsResponse.setPatients(paDataPatientsResponseRecords);
        paDataPatientsResponse.setReqID(paDataPatientsRequest.getReqID());
        add(paDataPatientsResponse);

        return paDataPatientsResponse;
    }
}
