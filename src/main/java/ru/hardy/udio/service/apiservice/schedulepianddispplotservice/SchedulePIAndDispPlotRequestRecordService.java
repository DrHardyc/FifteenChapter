package ru.hardy.udio.service.apiservice.schedulepianddispplotservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequestRecord;
import ru.hardy.udio.domain.api.schedulepianddispplot.DTO.SchedulePIAndDispPlotDTO;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo.SchedulePIAndDispPlotRequestRecordRepo;

import java.util.List;

@Service
public class SchedulePIAndDispPlotRequestRecordService {

    @Autowired
    private SchedulePIAndDispPlotRequestRecordRepo schedulePIAndDispPlotRequestRecordRepo;

    public void addAll(List<SchedulePIAndDispPlotRequestRecord> schedulePIAndDispPlotRequestRecords){
        schedulePIAndDispPlotRequestRecordRepo.saveAll(schedulePIAndDispPlotRequestRecords);
    }

    public List<SchedulePIAndDispPlotRequestRecord> getAllByMO(MedicalOrganization medicalOrganization) {
        return schedulePIAndDispPlotRequestRecordRepo.findAllActualByMO(medicalOrganization);
    }

    public List<SchedulePIAndDispPlotDTO> getDTO(MedicalOrganization medicalOrganization) {
//        List<SchedulePIAndDispPlotRequestRecordDTO> schedulePIAndDispPlotRequestRecordDTO = new ArrayList<>();
//        List<SchedulePIAndDispPlotRequestRecord> schedulePIAndDispPlotRequestRecords = schedulePIAndDispPlotRequestRecordRepo.findAllActualByMO(medicalOrganization);
//
//        for (SchedulePIAndDispPlotRequestRecord schedulePIAndDispPlotRequestRecord: schedulePIAndDispPlotRequestRecords){
//            schedulePIAndDispPlotRequestRecord.getMonths().forEach(month -> {
//                for (int i = 1; i < 13; i++) {
//                    if (month.getCodeTypePreventiveActions() == 0){
//                        schedulePIAndDispPlotRequestRecordDTO.add(new SchedulePIAndDispPlotRequestRecordDTO(
//                                schedulePIAndDispPlotRequestRecord.getNameDep(),
//                                month.quantityPlan, i));
//                    } else {
//                        schedulePIAndDispPlotRequestRecordDTO.add(new SchedulePIAndDispPlotRequestRecordDTO(
//                                schedulePIAndDispPlotRequestRecord.getNameDep(),
//                                month.quantityPlan, i));
//                    }
//                }
//            });
//        }
//        return schedulePIAndDispPlotRequestRecordDTO;
        return null;
    }
}
