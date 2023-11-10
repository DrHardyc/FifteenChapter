package ru.hardy.udio.service.apiservice.hospitalizationservice.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.hospitalization.dto.HospitalizationPatientDTO;
import ru.hardy.udio.domain.api.hospitalization.mo.Hospitalization;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.apiservice.hospitalizationservice.mo.HospitalizationService;

import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalizationPatientDTOService {
    @Autowired
    private HospitalizationService hospitalizationService;

    public List<HospitalizationPatientDTO> getAllByCodeHospitalization(int codeHospitalization){
        List<Hospitalization> hospitalizations = hospitalizationService.getAllByCodeHospitalization(codeHospitalization);
        List<HospitalizationPatientDTO> hospitalizationPatientDTOS = new ArrayList<>();
        hospitalizations.forEach(hospitalization -> {
            hospitalizationPatientDTOS.add(new HospitalizationPatientDTO(hospitalization));
        });

        return hospitalizationPatientDTOS;
    }

    public List<HospitalizationPatientDTO> getAllByPeople(People people) {
        List<Hospitalization> hospitalizations = hospitalizationService.getAllByPeople(people);
        List<HospitalizationPatientDTO> hospitalizationPatientDTOS = new ArrayList<>();
        hospitalizations.forEach(hospitalization -> {
            hospitalizationPatientDTOS.add(new HospitalizationPatientDTO(hospitalization));
        });

        return hospitalizationPatientDTOS;
    }
}
