package ru.hardy.udio.view.grid;

import com.vaadin.flow.component.treegrid.TreeGrid;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.schedulepianddispplot.DTO.MonthEventDTO;
import ru.hardy.udio.domain.api.schedulepianddispplot.DTO.SchedulePIAndDispPlotRequestRecordDTO;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequestRecord;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulePIAndDispPlotRequestRecordGrid {

    public void getGrid(TreeGrid<SchedulePIAndDispPlotRequestRecordDTO> grid, List<SchedulePIAndDispPlotRequestRecord> schedulePIAndDispPlotRequestRecords) {
        List<SchedulePIAndDispPlotRequestRecordDTO> schedulePIAndDispPlotRequestRecordDTOS = new ArrayList<>();
        for (SchedulePIAndDispPlotRequestRecord schedulePIAndDispPlotRequestRecord : schedulePIAndDispPlotRequestRecords) {
            SchedulePIAndDispPlotRequestRecordDTO parent =
                    new SchedulePIAndDispPlotRequestRecordDTO(schedulePIAndDispPlotRequestRecord.getNameDep(), schedulePIAndDispPlotRequestRecord);
            schedulePIAndDispPlotRequestRecordDTOS.add(parent);

            List<MonthEventDTO> clinicalExaminationDTO = new ArrayList<>();
            List<MonthEventDTO> firstStageClinicalExaminationDTO = new ArrayList<>();
            List<MonthEventDTO> secondStageClinicalExaminationDTO = new ArrayList<>();
            List<MonthEventDTO> dispensaryObservationDTO = new ArrayList<>();
            List<MonthEventDTO> preventiveExamination = new ArrayList<>();
            schedulePIAndDispPlotRequestRecord.getMonths().forEach(monthEvent -> {
                switch (monthEvent.getCodeTypePreventiveActions()) {
                    case 0 -> clinicalExaminationDTO.add(new MonthEventDTO(monthEvent.getMonth(), monthEvent.getQuantityPlan()));
                    case 1 -> firstStageClinicalExaminationDTO.add(new MonthEventDTO(monthEvent.getMonth(), monthEvent.getQuantityPlan()));
                    case 2 -> secondStageClinicalExaminationDTO.add(new MonthEventDTO(monthEvent.getMonth(), monthEvent.getQuantityPlan()));
                    case 3 -> dispensaryObservationDTO.add(new MonthEventDTO(monthEvent.getMonth(), monthEvent.getQuantityPlan()));
                    case 4 -> preventiveExamination.add(new MonthEventDTO(monthEvent.getMonth(), monthEvent.getQuantityPlan()));

                }
            });
            schedulePIAndDispPlotRequestRecordDTOS.add(
                    new SchedulePIAndDispPlotRequestRecordDTO(clinicalExaminationDTO, "Диспансеризация", parent));
            schedulePIAndDispPlotRequestRecordDTOS.add(
                    new SchedulePIAndDispPlotRequestRecordDTO(firstStageClinicalExaminationDTO, "Первый этап диспансеризации", parent));
            schedulePIAndDispPlotRequestRecordDTOS.add(
                    new SchedulePIAndDispPlotRequestRecordDTO(secondStageClinicalExaminationDTO, "Второй этап диспансеризации", parent));
            schedulePIAndDispPlotRequestRecordDTOS.add(
                    new SchedulePIAndDispPlotRequestRecordDTO(dispensaryObservationDTO, "Д-наблюдение", parent));
            schedulePIAndDispPlotRequestRecordDTOS.add(
                    new SchedulePIAndDispPlotRequestRecordDTO(preventiveExamination, "Профосмотр", parent));

        }

        grid.addHierarchyColumn(SchedulePIAndDispPlotRequestRecordDTO::getName).setHeader("Отделение").setResizable(true);
        grid.addColumn(SchedulePIAndDispPlotRequestRecordDTO::getMonth1).setHeader("Январь");
        grid.addColumn(SchedulePIAndDispPlotRequestRecordDTO::getMonth2).setHeader("Февраль");
        grid.addColumn(SchedulePIAndDispPlotRequestRecordDTO::getMonth3).setHeader("Март");
        grid.addColumn(SchedulePIAndDispPlotRequestRecordDTO::getMonth4).setHeader("Апрель");
        grid.addColumn(SchedulePIAndDispPlotRequestRecordDTO::getMonth5).setHeader("Май");
        grid.addColumn(SchedulePIAndDispPlotRequestRecordDTO::getMonth6).setHeader("Июнь");
        grid.addColumn(SchedulePIAndDispPlotRequestRecordDTO::getMonth7).setHeader("Июль");
        grid.addColumn(SchedulePIAndDispPlotRequestRecordDTO::getMonth8).setHeader("Август");
        grid.addColumn(SchedulePIAndDispPlotRequestRecordDTO::getMonth9).setHeader("Сентябрь");
        grid.addColumn(SchedulePIAndDispPlotRequestRecordDTO::getMonth10).setHeader("Октябрь");
        grid.addColumn(SchedulePIAndDispPlotRequestRecordDTO::getMonth11).setHeader("Ноябрь");
        grid.addColumn(SchedulePIAndDispPlotRequestRecordDTO::getMonth12).setHeader("Декабрь");

        schedulePIAndDispPlotRequestRecordDTOS.forEach(schedulePIAndDispPlotRequestRecordDTO ->
                grid.getTreeData().addItem(schedulePIAndDispPlotRequestRecordDTO.getParent(),
                        schedulePIAndDispPlotRequestRecordDTO));
    }
}
