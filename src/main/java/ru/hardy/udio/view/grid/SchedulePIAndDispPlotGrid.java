package ru.hardy.udio.view.grid;

import com.vaadin.flow.component.treegrid.TreeGrid;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.schedulepianddispplot.DTO.MonthEventDTO;
import ru.hardy.udio.domain.api.schedulepianddispplot.DTO.SchedulePIAndDispPlotDTO;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequestRecord;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulePIAndDispPlotGrid {

    public void getGrid(TreeGrid<SchedulePIAndDispPlotDTO> grid, List<SchedulePIAndDispPlotRequestRecord> schedulePIAndDispPlotRequestRecords) {
        List<SchedulePIAndDispPlotDTO> schedulePIAndDispPlotDTOS = new ArrayList<>();
        for (SchedulePIAndDispPlotRequestRecord schedulePIAndDispPlotRequestRecord : schedulePIAndDispPlotRequestRecords) {
            SchedulePIAndDispPlotDTO parent =
                    new SchedulePIAndDispPlotDTO(schedulePIAndDispPlotRequestRecord.getNameDep(), schedulePIAndDispPlotRequestRecord);
            schedulePIAndDispPlotDTOS.add(parent);

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
            schedulePIAndDispPlotDTOS.add(
                    new SchedulePIAndDispPlotDTO(clinicalExaminationDTO, "Диспансеризация", parent));
            schedulePIAndDispPlotDTOS.add(
                    new SchedulePIAndDispPlotDTO(firstStageClinicalExaminationDTO, "Первый этап диспансеризации", parent));
            schedulePIAndDispPlotDTOS.add(
                    new SchedulePIAndDispPlotDTO(secondStageClinicalExaminationDTO, "Второй этап диспансеризации", parent));
            schedulePIAndDispPlotDTOS.add(
                    new SchedulePIAndDispPlotDTO(dispensaryObservationDTO, "Д-наблюдение", parent));
            schedulePIAndDispPlotDTOS.add(
                    new SchedulePIAndDispPlotDTO(preventiveExamination, "Профосмотр", parent));

        }

        grid.addHierarchyColumn(SchedulePIAndDispPlotDTO::getName).setHeader("Отделение").setResizable(true);
        grid.addColumn(SchedulePIAndDispPlotDTO::getMonth1).setHeader("Январь");
        grid.addColumn(SchedulePIAndDispPlotDTO::getMonth2).setHeader("Февраль");
        grid.addColumn(SchedulePIAndDispPlotDTO::getMonth3).setHeader("Март");
        grid.addColumn(SchedulePIAndDispPlotDTO::getMonth4).setHeader("Апрель");
        grid.addColumn(SchedulePIAndDispPlotDTO::getMonth5).setHeader("Май");
        grid.addColumn(SchedulePIAndDispPlotDTO::getMonth6).setHeader("Июнь");
        grid.addColumn(SchedulePIAndDispPlotDTO::getMonth7).setHeader("Июль");
        grid.addColumn(SchedulePIAndDispPlotDTO::getMonth8).setHeader("Август");
        grid.addColumn(SchedulePIAndDispPlotDTO::getMonth9).setHeader("Сентябрь");
        grid.addColumn(SchedulePIAndDispPlotDTO::getMonth10).setHeader("Октябрь");
        grid.addColumn(SchedulePIAndDispPlotDTO::getMonth11).setHeader("Ноябрь");
        grid.addColumn(SchedulePIAndDispPlotDTO::getMonth12).setHeader("Декабрь");

        schedulePIAndDispPlotDTOS.forEach(schedulePIAndDispPlotRequestRecordDTO ->
                grid.getTreeData().addItem(schedulePIAndDispPlotRequestRecordDTO.getParent(),
                        schedulePIAndDispPlotRequestRecordDTO));
    }
}
