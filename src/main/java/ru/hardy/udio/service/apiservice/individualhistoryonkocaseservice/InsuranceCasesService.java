package ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualhistoryonkocase.InsuranceCase;
import ru.hardy.udio.repo.apirepo.individualhistoryonkocaserepo.InsuranceCasesRepo;

import java.util.List;

@Service
public class InsuranceCasesService {

    @Autowired
    private InsuranceCasesRepo insuranceCasesRepo;

    public void addAll(List<InsuranceCase> insuranceCaseList){
        insuranceCasesRepo.saveAll(insuranceCaseList);
    }
}
