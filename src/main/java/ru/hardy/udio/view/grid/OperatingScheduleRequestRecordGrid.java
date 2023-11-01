package ru.hardy.udio.view.grid;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleRequestRecord;

public class OperatingScheduleRequestRecordGrid {
    public static void getGrid(Grid<OperatingScheduleRequestRecord> grid,
                               GridListDataView<OperatingScheduleRequestRecord> operatingScheduleRequestRecordGridListDataView) {
        Grid.Column<OperatingScheduleRequestRecord> codeDepCol = grid
                .addColumn(OperatingScheduleRequestRecord::getCodeDep)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<OperatingScheduleRequestRecord> nameDepCol = grid
                .addColumn(OperatingScheduleRequestRecord::getNameDep)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<OperatingScheduleRequestRecord> scheduleDepCol = grid
                .addColumn(OperatingScheduleRequestRecord::getScheduleDep)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<OperatingScheduleRequestRecord> holidaysDepCol = grid
                .addColumn(OperatingScheduleRequestRecord::getHolidaysDep)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<OperatingScheduleRequestRecord>addressCol = grid
                .addColumn(OperatingScheduleRequestRecord::getAddress)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<OperatingScheduleRequestRecord> cabinetsCol = grid
                .addColumn(new ComponentRenderer<>(Button::new, (button, cabinets) -> {
                    button.setIcon(new Icon(VaadinIcon.BED));
                    button.addClickListener(event -> {
                        //todo
                    });
                }));

        OperatingScheduleRequestRecordGridFilter operatingScheduleRequestRecordGridFilter =
                new OperatingScheduleRequestRecordGridFilter(operatingScheduleRequestRecordGridListDataView);
        grid.getHeaderRows().clear();
        grid.setMultiSort(true, Grid.MultiSortPriority.APPEND);
        HeaderRow headerRow = grid.appendHeaderRow();

        headerRow.getCell(codeDepCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Код отделения", operatingScheduleRequestRecordGridFilter::setCodeDep));
        headerRow.getCell(nameDepCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Наименование отделения", operatingScheduleRequestRecordGridFilter::setNameDep));
        headerRow.getCell(scheduleDepCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Расписание", operatingScheduleRequestRecordGridFilter::setScheduleDep));
        headerRow.getCell(holidaysDepCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Праздничные дни", operatingScheduleRequestRecordGridFilter::setHolidaysDep));
        headerRow.getCell(addressCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Адрес работы выездных бригад", operatingScheduleRequestRecordGridFilter::setAddress));
        headerRow.getCell(cabinetsCol)
                .setComponent(new Span("Кабинеты"));
    }

    private static class OperatingScheduleRequestRecordGridFilter {
        private final GridListDataView<OperatingScheduleRequestRecord> dataView;

        private String codeDep;
        private String nameDep;
        private String scheduleDep;
        private String holidaysDep;
        private String address;

        public void setCodeDep(String codeDep) {
            this.codeDep = codeDep;

        }

        public void setNameDep(String nameDep) {
            this.nameDep = nameDep;
            this.dataView.refreshAll();
        }

        public void setScheduleDep(String scheduleDep) {
            this.scheduleDep = scheduleDep;
            this.dataView.refreshAll();
        }

        public void setHolidaysDep(String holidaysDep) {
            this.holidaysDep = holidaysDep;
            this.dataView.refreshAll();
        }

        public void setAddress(String address) {
            this.address = address;
            this.dataView.refreshAll();
        }

        public OperatingScheduleRequestRecordGridFilter(GridListDataView<OperatingScheduleRequestRecord> dataView) {
            this.dataView = dataView;
            this.dataView.addFilter(this::refresh);
        }

        public boolean refresh(OperatingScheduleRequestRecord operatingScheduleRequestRecord) {
            boolean matchesCodeDep = matches(String.valueOf(operatingScheduleRequestRecord.getCodeDep()), codeDep);
            boolean matchesNameDep = matches(operatingScheduleRequestRecord.getNameDep(), nameDep);
            boolean matchesScheduleDep = matches(operatingScheduleRequestRecord.getScheduleDep(), scheduleDep);
            boolean matchesHolidaysDep= matches(operatingScheduleRequestRecord.getHolidaysDep(), holidaysDep);
            boolean matchesAddress = matches(operatingScheduleRequestRecord.getAddress(), address);
            return matchesCodeDep && matchesNameDep && matchesScheduleDep && matchesHolidaysDep && matchesAddress;
        }

        private boolean matches(String value, String searchTerm) {
            return searchTerm == null || searchTerm.isEmpty() || value.toLowerCase().contains(searchTerm.toLowerCase());
        }
    }
}
