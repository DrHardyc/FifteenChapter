package ru.hardy.udio.service.apiservice.volumemedicalcareservice.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.Diagnosis;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareDiagnosis;
import ru.hardy.udio.domain.api.volumemedicalcare.dto.VolumeMedicalCareDTO;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.service.UtilService;
import ru.hardy.udio.service.apiservice.volumemedicalcareservice.VolumeMedicalCareDiagnosisService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VolumeMedicalCareDTOService {

    @Autowired
    private VolumeMedicalCareDiagnosisService volumeMedicalCareDiagnosisService;

    public List<VolumeMedicalCareDTO> getAllByDateInterval(MedicalOrganization medicalOrganization){
        List<VolumeMedicalCareDiagnosis> volumeMedicalCareDiagnoses = volumeMedicalCareDiagnosisService
                .getAllByCodeMOAndDateInterval(medicalOrganization, UtilService.DateTo900Format(7),
                        UtilService.DateTo900Format(0));
        Map<String, List<VolumeMedicalCareDiagnosis>> nameDepList =
                volumeMedicalCareDiagnoses.stream()
                        .collect(Collectors
                                .groupingBy(volumeMedicalCareDiagnosis -> volumeMedicalCareDiagnosis.getRequestRecord().getNameDep()));

        List<VolumeMedicalCareDTO> parents = new ArrayList<>();
        List<VolumeMedicalCareDTO> volumeMedicalCareDTOS = new ArrayList<>();
        for (String nameDep: nameDepList.keySet()) {
            VolumeMedicalCareDTO parent = new VolumeMedicalCareDTO();
            parent.setName(nameDep);
            parents.add(parent);
            volumeMedicalCareDTOS.add(parent);
        }
        for (VolumeMedicalCareDTO parent : parents) {
            Map<String, List<VolumeMedicalCareDiagnosis>> codesDiagnosis =
                    volumeMedicalCareDiagnosisService.getAllByCodeMOAndCodeDepDateInterval(medicalOrganization, parent.getName(),
                                    UtilService.DateTo900Format(7), UtilService.DateTo900Format(0)).stream()
                            .collect(Collectors
                                    .groupingBy(Diagnosis::getCodeDiagnosis));
            for (String codeDiagnosis: codesDiagnosis.keySet()){
                VolumeMedicalCareDTO child = new VolumeMedicalCareDTO();
                child.setParent(parent);
                child.setName(codeDiagnosis);
                volumeMedicalCareDTOS.add(child);
                child.setDay1(volumeMedicalCareDiagnosisService.getAllByCodeMOAndCodeDepAndCodeDiagDateInterval(
                        medicalOrganization, parent.getName(), codeDiagnosis, UtilService.DateTo900Format(1),
                        UtilService.DateTo900Format(0)));
                child.setDay2(volumeMedicalCareDiagnosisService.getAllByCodeMOAndCodeDepAndCodeDiagDateInterval(
                        medicalOrganization, parent.getName(), codeDiagnosis, UtilService.DateTo900Format(2),
                        UtilService.DateTo900Format(1)));
                child.setDay3(volumeMedicalCareDiagnosisService.getAllByCodeMOAndCodeDepAndCodeDiagDateInterval(
                        medicalOrganization, parent.getName(), codeDiagnosis, UtilService.DateTo900Format(3),
                        UtilService.DateTo900Format(2)));
                child.setDay4(volumeMedicalCareDiagnosisService.getAllByCodeMOAndCodeDepAndCodeDiagDateInterval(
                        medicalOrganization, parent.getName(), codeDiagnosis, UtilService.DateTo900Format(4),
                        UtilService.DateTo900Format(3)));
                child.setDay5(volumeMedicalCareDiagnosisService.getAllByCodeMOAndCodeDepAndCodeDiagDateInterval(
                        medicalOrganization, parent.getName(), codeDiagnosis, UtilService.DateTo900Format(5),
                        UtilService.DateTo900Format(4)));
                child.setDay5(volumeMedicalCareDiagnosisService.getAllByCodeMOAndCodeDepAndCodeDiagDateInterval(
                        medicalOrganization, parent.getName(), codeDiagnosis, UtilService.DateTo900Format(6),
                        UtilService.DateTo900Format(5)));
                child.setDay7(volumeMedicalCareDiagnosisService.getAllByCodeMOAndCodeDepAndCodeDiagDateInterval(
                        medicalOrganization, parent.getName(), codeDiagnosis, UtilService.DateTo900Format(7),
                        UtilService.DateTo900Format(6)));
            }
        }

        return volumeMedicalCareDTOS;
    }
}
