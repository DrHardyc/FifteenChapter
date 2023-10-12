package ru.hardy.udio.view.grid;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.server.StreamResource;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.task.ReportTask;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
public class ReportTaskGrid {

    public static void getGrid(Grid<ReportTask> grid, GridListDataView<ReportTask> reportTaskGridListDataView) {

        Grid.Column<ReportTask> nameCol = grid.addColumn(ReportTask::getName).setResizable(true).setSortable(true);
        Grid.Column<ReportTask> filenameCol = grid.addColumn(new ComponentRenderer<>(item -> {
            return new Anchor(new StreamResource(item.getFile_name(),
                    () -> {
                        try {
                            return new FileInputStream("C:\\udio\\reports\\" + item.getFile_name());
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }),
                    item.getFile_name());
        })).setResizable(true).setSortable(true).setWidth("300px");
        Grid.Column<ReportTask> statusCol = grid.addColumn(ReportTask::getStatusRUCaption).setResizable(true).setSortable(true);
        Grid.Column<ReportTask> periodCol = grid.addColumn(ReportTask::getPeriod).setResizable(true).setSortable(true);
        Grid.Column<ReportTask> usernameCol = grid.addColumn(ReportTask::getUsername).setResizable(true).setSortable(true);
        Grid.Column<ReportTask> dateBegCol = grid.addColumn(new LocalDateTimeRenderer<>(
                ReportTask::getLocalDateTimeDateBeg, "dd.MM.yyyy HH:mm:ss")).setResizable(true)
                .setComparator(ReportTask::getLocalDateTimeDateBeg);
        Grid.Column<ReportTask> dateEditCol = grid.addColumn(new LocalDateTimeRenderer<>(
                ReportTask::getLocalDateTimeDateEdit, "dd.MM.yyyy HH:mm:ss")).setResizable(true).setComparator(ReportTask::getLocalDateTimeDateEdit);
        grid.setMultiSort(true, Grid.MultiSortPriority.APPEND);
        ReportTaskGridFilter taskReportGrid = new ReportTaskGridFilter(reportTaskGridListDataView);
        grid.getHeaderRows().clear();
        HeaderRow headerRow = grid.appendHeaderRow();

        headerRow
                .getCell(nameCol)
                .setComponent(GridUtils
                        .createFilterHeader("Наименование", taskReportGrid::setName));
        headerRow
                .getCell(filenameCol)
                .setComponent(GridUtils
                        .createFilterHeader("Файл excel", taskReportGrid::setFileName));
        headerRow
                .getCell(statusCol)
                .setComponent(GridUtils.createFilterHeader("Статус", taskReportGrid::setStatus));
        headerRow
                .getCell(periodCol)
                .setComponent(GridUtils.createFilterHeader("Период", taskReportGrid::setPeriod));
        headerRow
                .getCell(usernameCol)
                .setComponent(GridUtils
                        .createFilterHeader("Пользователь", taskReportGrid::setUsername));
        headerRow
                .getCell(dateBegCol)
                .setComponent(GridUtils
                        .createFilterHeader("Дата начала выполнения", taskReportGrid::setDateBeg));
        headerRow
                .getCell(dateEditCol)
                .setComponent(GridUtils
                        .createFilterHeader("Дата окончания выполнения", taskReportGrid::setDateEdit));
    }


    private static class ReportTaskGridFilter{
        private final GridListDataView<ReportTask> dataView;

        private String name;

        private String file_name;

        private String status;

        private String period;

        private String username;

        private String dateBeg;
        private String dateEdit;

        public void setName(String name) {
            this.name = name;
            this.dataView.refreshAll();
        }

        public void setFileName(String file_name) {
            this.file_name = file_name;
            this.dataView.refreshAll();
        }

        public void setStatus(String status) {
            this.status = status;
            this.dataView.refreshAll();
        }

        public void setPeriod(String period) {
            this.period = period;
            this.dataView.refreshAll();
        }

        public void setUsername(String username) {
            this.username = username;
            this.dataView.refreshAll();
        }

        public void setDateBeg(String dateBeg) {
            this.dateBeg = dateBeg;
            this.dataView.refreshAll();
        }

        public void setDateEdit(String dateEdit) {
            this.dateEdit = dateEdit;
            this.dataView.refreshAll();
        }


        public String getCount(){
            return String.valueOf(dataView.getItemCount());
        }

        public ReportTaskGridFilter(GridListDataView<ReportTask> dataView) {
            this.dataView = dataView;
            this.dataView.addFilter(this::refresh);
        }
        public boolean refresh(ReportTask reportTask) {
            boolean matchesName = matches(reportTask.getName(), name);
            boolean matchesFilename = matches(reportTask.getFile_name(), file_name);
            boolean matchesStatus = matches(reportTask.getStatusRUCaption(), status);
            boolean matchesPeriod = matches(reportTask.getPeriod(), period);
            boolean matchesUsername = matches(reportTask.getUsername(), username);
            boolean matchesDateBeg = matches(reportTask.getDateBegString(), dateBeg);
            boolean matchesDateEdit = matches(reportTask.getDateEditString(), dateEdit);

            return matchesName && matchesFilename && matchesStatus && matchesPeriod && matchesUsername
                    && matchesDateBeg && matchesDateEdit;
        }

        private boolean matches(String value, String searchTerm) {
            return searchTerm == null || searchTerm.isEmpty() || value.toLowerCase().contains(searchTerm.toLowerCase());
        }
    }
}
