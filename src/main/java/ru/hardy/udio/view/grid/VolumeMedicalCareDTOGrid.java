package ru.hardy.udio.view.grid;


import com.vaadin.flow.component.treegrid.TreeGrid;
import ru.hardy.udio.domain.abstractclasses.Department;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareDiagnosis;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareRequestRecord;
import ru.hardy.udio.domain.api.volumemedicalcare.dto.VolumeMedicalCareDTO;
import ru.hardy.udio.service.UtilService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class VolumeMedicalCareDTOGrid {

    public void getGrid(TreeGrid<VolumeMedicalCareDTO> grid, List<VolumeMedicalCareDTO> volumeMedicalCareDTOS) {

        grid.addHierarchyColumn(VolumeMedicalCareDTO::getName).setHeader("Отделение/Диагнозы").setResizable(true);
        grid.addColumn(VolumeMedicalCareDTO::getDay1).setHeader("Вчера");
        grid.addColumn(VolumeMedicalCareDTO::getDay2).setHeader("Позавчера");
        grid.addColumn(VolumeMedicalCareDTO::getDay3).setHeader("3 дня назад");
        grid.addColumn(VolumeMedicalCareDTO::getDay4).setHeader("4 дня назад");
        grid.addColumn(VolumeMedicalCareDTO::getDay5).setHeader("5 дня назад");
        grid.addColumn(VolumeMedicalCareDTO::getDay6).setHeader("6 дней назад");
        grid.addColumn(VolumeMedicalCareDTO::getDay7).setHeader("7 дней назад");

        volumeMedicalCareDTOS.forEach(volumeMedicalCareDTO ->
                grid.getTreeData().addItem(volumeMedicalCareDTO.getParent(),
                        volumeMedicalCareDTO));

    }

    private boolean checkCodeDiagIteration(List<String> diagnosisCodesIteration, String diagnosisCode) {
        for (String diagnosisCodeIteration: diagnosisCodesIteration){
            if (diagnosisCodeIteration.equals(diagnosisCode)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean checkDepartmentNameIteration(List<String> departmentNames, String departmentName){
        for (String departmentNameIteration: departmentNames){
            if (departmentNameIteration.equals(departmentName)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private int getFilterDiagnosis(int beg, int end, List<VolumeMedicalCareDiagnosis> volumeMedicalCareDiagnosisList, String codeDiag){
        List<VolumeMedicalCareDiagnosis> volumeMedicalCareDiagnoses = volumeMedicalCareDiagnosisList.stream().filter(
                diagnosisDays ->
                diagnosisDays.getRequestRecord().getDepartment().getDate_beg().after(UtilService.DateTo900Format(beg))
                        && diagnosisDays.getRequestRecord().getDepartment().getDate_beg().before(UtilService.DateTo900Format(end))
                        && diagnosisDays.getCodeDiagnosis().equals(codeDiag)).toList();

        if (volumeMedicalCareDiagnoses.size() != 0){
            return volumeMedicalCareDiagnoses.get(0).getQuantity();
        }
        return 0;
    }
}
