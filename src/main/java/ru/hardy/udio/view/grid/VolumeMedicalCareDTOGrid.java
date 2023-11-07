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
}
