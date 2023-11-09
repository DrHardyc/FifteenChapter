package ru.hardy.udio.view.grid;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.service.apiservice.numberavailableseatsservice.dto.NumberAvailableSeatsDTOService;
import ru.hardy.udio.service.apiservice.operatingscheduleservice.OperatingScheduleRequestRecordService;
import ru.hardy.udio.service.apiservice.schedulepianddispplotservice.mo.SchedulePIAndDispPlotRequestRecordService;
import ru.hardy.udio.service.apiservice.volumemedicalcareservice.dto.VolumeMedicalCareDTOService;
import ru.hardy.udio.service.nsiservice.MedicalOrganizationService;
import ru.hardy.udio.view.dialog.DialogViewGen;

@Service
public class MedicalOrganizationGrid {

    @Autowired
    private OperatingScheduleRequestRecordService operatingScheduleRequestRecordService;

    @Autowired
    private MedicalOrganizationService medicalOrganizationService;

    @Autowired
    private SchedulePIAndDispPlotRequestRecordService schedulePIAndDispPlotRequestRecordService;

    @Autowired
    private VolumeMedicalCareDTOService volumeMedicalCareDTOService;

    @Autowired
    private NumberAvailableSeatsDTOService numberAvailableSeatsDTOService;


    public void getGrid(Grid<MedicalOrganization> grid, GridListDataView<MedicalOrganization> medicalOrganizationGridListDataView) {

        Grid.Column<MedicalOrganization> codeMOCol = grid
                .addColumn(MedicalOrganization::getCodeMO)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<MedicalOrganization> nameMOCol = grid
                .addColumn(MedicalOrganization::getShortName)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<MedicalOrganization> scheduleCol = grid
                .addColumn(new ComponentRenderer<>(Button::new, (button, medicalOrganization) -> {
                    button.setIcon(new Icon(VaadinIcon.CHART_LINE));
                    button.addClickListener(event -> {
                        new DialogViewGen()
                                .getSchedulePIAndDispPlotResponseRecordDialog(schedulePIAndDispPlotRequestRecordService.getAllByMO(
                                        medicalOrganizationService.getByCode(medicalOrganization.getCodeMO())
                                )).open();
                    });
                }));
        Grid.Column<MedicalOrganization> operatingCol = grid
                .addColumn(new ComponentRenderer<>(Button::new, (button, medicalOrganization) -> {
                    button.setIcon(new Icon(VaadinIcon.CALENDAR));
                    button.addClickListener(event -> {
                        new DialogViewGen().getOperatingScheduleResponseRecordDialog(
                                operatingScheduleRequestRecordService.getAllByMO(
                                        medicalOrganizationService.getByCode(medicalOrganization.getCodeMO()))).open();
                    });
                }));
        Grid.Column<MedicalOrganization> medicalCareCol = grid
                .addColumn(new ComponentRenderer<>(Button::new, (button, medicalOrganization) -> {
                    button.setIcon(new Icon(VaadinIcon.DOCTOR));
                    button.addClickListener(event -> {
                        new DialogViewGen().getVolumeMedicalCareResponseRecordDialog(
                                volumeMedicalCareDTOService.getAllByDateInterval(medicalOrganization)).open();
                    });
                }));
        Grid.Column<MedicalOrganization> seatsCol = grid
                .addColumn(new ComponentRenderer<>(Button::new, (button, medicalOrganization) -> {
                    button.setIcon(new Icon(VaadinIcon.BED));
                    button.addClickListener(event -> {
                        new DialogViewGen().getNumberAvailableSeatsDialog(
                                numberAvailableSeatsDTOService.getAllByMO(medicalOrganization.getCodeMO())).open();
                    });
                }));

        MedicalOrganizationGridFilter medicalOrganizationGridFilter =
                new MedicalOrganizationGridFilter(medicalOrganizationGridListDataView);
        grid.getHeaderRows().clear();
        grid.setMultiSort(true, Grid.MultiSortPriority.APPEND);
        HeaderRow headerRow = grid.appendHeaderRow();

        headerRow.getCell(codeMOCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Код МО", medicalOrganizationGridFilter::setCodeMO));
        headerRow.getCell(nameMOCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Наименование МО", medicalOrganizationGridFilter::setNameMO));
        headerRow.getCell(scheduleCol)
                .setComponent(new Span("План-графики"));
        headerRow.getCell(operatingCol)
                .setComponent(new Span("График работы"));
        headerRow.getCell(medicalCareCol)
                .setComponent(new Span("Объемы мед. помощи"));
        headerRow.getCell(seatsCol)
                .setComponent(new Span("Свободные места"));
    }

    private static class MedicalOrganizationGridFilter {
        private final GridListDataView<MedicalOrganization> dataView;

        private String codeMO;
        private String nameMO;

        public void setCodeMO(String codeMO) {
            this.codeMO = codeMO;
            this.dataView.refreshAll();
        }

        public void setNameMO(String nameMO) {
            this.nameMO = nameMO;
            this.dataView.refreshAll();
        }

        public MedicalOrganizationGridFilter(GridListDataView<MedicalOrganization> dataView) {
            this.dataView = dataView;
            this.dataView.addFilter(this::refresh);
        }

        public boolean refresh(MedicalOrganization medicalOrganization) {
            boolean matchesCodeMOAttach = matches(String.valueOf(medicalOrganization.getCodeMO()), codeMO);
            boolean matchesCodeMO = matches(medicalOrganization.getShortName(), nameMO);
            return matchesCodeMOAttach && matchesCodeMO;
        }

        private boolean matches(String value, String searchTerm) {
            return searchTerm == null || searchTerm.isEmpty() || value.toLowerCase().contains(searchTerm.toLowerCase());
        }
    }
}
