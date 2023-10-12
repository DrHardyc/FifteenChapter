package ru.hardy.udio.view.grid;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class IndividualInformingRequestRecordGrid {

    public void getGrid(Grid<IndividualInformingRequestRecord> grid, GridListDataView<IndividualInformingRequestRecord> individualInformingRequestRecordGridListDataView) {
        Grid.Column<IndividualInformingRequestRecord> mainDiagnosisCol = grid
                .addColumn(IndividualInformingRequestRecord::getMainDiagnosis)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<IndividualInformingRequestRecord> sequenceInformationCol = grid
                .addColumn(IndividualInformingRequestRecord::getSequenceInformation)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<IndividualInformingRequestRecord> wayInformingCol = grid
                .addColumn(IndividualInformingRequestRecord::getWayInforming)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<IndividualInformingRequestRecord> dateNotificationCol = grid
                .addColumn(new LocalDateRenderer<>(
                        IndividualInformingRequestRecord::getDateNotificationLocalDate, "dd.MM.yyyy"))
                .setResizable(true)
                .setComparator(IndividualInformingRequestRecord::getDateNotification);
        Grid.Column<IndividualInformingRequestRecord> plannedNotificationDateCol = grid
                .addColumn(new LocalDateRenderer<>(
                        IndividualInformingRequestRecord::getPlannedNotificationDateLocalDate, "dd.MM.yyyy"))
                .setResizable(true)
                .setComparator(IndividualInformingRequestRecord::getPlannedNotificationDate);
        Grid.Column<IndividualInformingRequestRecord> dateBegCol = grid
                .addColumn(new LocalDateRenderer<>(
                        IndividualInformingRequestRecord::getDateBegLocalDate, "dd.MM.yyyy"))
                .setResizable(true)
                .setComparator(IndividualInformingRequestRecord::getDateBeg);

        IndividualInformingRequestRecordGridFilter individualInformingRequestRecordGridFilter =
                new IndividualInformingRequestRecordGridFilter(individualInformingRequestRecordGridListDataView);
        grid.getHeaderRows().clear();
        grid.setMultiSort(true, Grid.MultiSortPriority.APPEND);
        HeaderRow headerRow = grid.appendHeaderRow();

        headerRow.getCell(mainDiagnosisCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                        "Основной диагноз", individualInformingRequestRecordGridFilter::setMainDiagnosis));
        headerRow.getCell(sequenceInformationCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                        "Очередность информирования", individualInformingRequestRecordGridFilter::setSequenceInformation));
        headerRow.getCell(wayInformingCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                        "Способ информирования", individualInformingRequestRecordGridFilter::setWayInforming));
        headerRow.getCell(dateNotificationCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                        "Дата информирования", individualInformingRequestRecordGridFilter::setDateNotification));
        headerRow.getCell(plannedNotificationDateCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                        "Планируемая дата следующего информирования", individualInformingRequestRecordGridFilter::setPlannedNotificationDate));
        headerRow.getCell(dateBegCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                        "Дата добавления записи", individualInformingRequestRecordGridFilter::setDateBeg));

    }

    private static class IndividualInformingRequestRecordGridFilter {
        private final GridListDataView<IndividualInformingRequestRecord> dataView;

        private String sequenceInformation;
        private String wayInforming;
        private String mainDiagnosis;
        private String dateNotification;
        private String plannedNotificationDate;
        private String dateBeg;

        public void setSequenceInformation(String sequenceInformation) {
            this.sequenceInformation = sequenceInformation;
            this.dataView.refreshAll();
        }

        public void setWayInforming(String wayInforming) {
            this.wayInforming = wayInforming;
            this.dataView.refreshAll();
        }

        public void setMainDiagnosis(String mainDiagnosis) {
            this.mainDiagnosis = mainDiagnosis;
            this.dataView.refreshAll();
        }

        public void setDateNotification(String dateNotification) {
            this.dateNotification = dateNotification;
            this.dataView.refreshAll();
        }

        public void setPlannedNotificationDate(String plannedNotificationDate) {
            this.plannedNotificationDate = plannedNotificationDate;
            this.dataView.refreshAll();
        }

        public void setDateBeg(String dateBeg) {
            this.dateBeg = dateBeg;
            this.dataView.refreshAll();
        }

        public IndividualInformingRequestRecordGridFilter(GridListDataView<IndividualInformingRequestRecord> dataView) {
            this.dataView = dataView;
            this.dataView.addFilter(this::refresh);
        }
        public boolean refresh(IndividualInformingRequestRecord individualInformingRequestRecord) {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

            boolean matchesSequenceInformation = matches(String.valueOf(individualInformingRequestRecord.getSequenceInformation()), sequenceInformation);
            boolean matchesWayInforming = matches(String.valueOf(individualInformingRequestRecord.getWayInforming()), wayInforming);
            boolean matchesMainDiagnosis = matches(individualInformingRequestRecord.getMainDiagnosis(), mainDiagnosis);
            boolean matchesDateNotification = matches(dateFormat.format(individualInformingRequestRecord.getDateNotification()), dateNotification);
            boolean matchesPlannedNotificationDate = matches(dateFormat.format(individualInformingRequestRecord.getPlannedNotificationDate()), plannedNotificationDate);
            boolean matchesDateBeg = matches(dateFormat.format(individualInformingRequestRecord.getDateBeg()), dateBeg);

            return matchesSequenceInformation && matchesWayInforming && matchesMainDiagnosis && matchesDateNotification
                     && matchesPlannedNotificationDate && matchesDateBeg;
        }

        private boolean matches(String value, String searchTerm) {
            return searchTerm == null || searchTerm.isEmpty() || value.toLowerCase().contains(searchTerm.toLowerCase());
        }
    }

}
