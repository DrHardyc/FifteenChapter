package ru.hardy.udio.view.grid;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.server.StreamResource;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.task.ReportTask;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.function.Consumer;

@Service
public class ReportTaskGrid {

    public static void getGrid(Grid<ReportTask> grid, GridListDataView<ReportTask> reportTaskGridListDataView) {

        Grid.Column<ReportTask> nameCol = grid.addColumn(ReportTask::getName).setResizable(true).setSortable(true);
        Grid.Column<ReportTask> filenameCol = grid.addColumn(new ComponentRenderer<>(item -> {
            return new Anchor(new StreamResource(item.getFile_name(),
                    () -> {
                        try {
                            return new FileInputStream("reports\\" + item.getFile_name());
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }),
                    item.getFile_name());
        })).setResizable(true).setSortable(true).setWidth("300px");
        Grid.Column<ReportTask> statusCol = grid.addColumn(ReportTask::getStatus).setResizable(true).setSortable(true);
        Grid.Column<ReportTask> errCol = grid.addColumn(ReportTask::getResult).setResizable(true).setSortable(true);
        Grid.Column<ReportTask> usernameCol = grid.addColumn(ReportTask::getUsername).setResizable(true).setSortable(true);
        Grid.Column<ReportTask> dateBegCol = grid.addColumn(new LocalDateTimeRenderer<>(
                ReportTask::getLocalDateTimeDateBeg, "dd.MM.yyyy")).setResizable(true).setComparator(ReportTask::getLocalDateTimeDateBeg);
        Grid.Column<ReportTask> dateEditCol = grid.addColumn(new LocalDateTimeRenderer<>(
                ReportTask::getLocalDateTimeDateEdit, "dd.MM.yyyy")).setResizable(true).setComparator(ReportTask::getLocalDateTimeDateEdit);

        ReportTaskGridFilter taskReportGrid = new ReportTaskGridFilter(reportTaskGridListDataView);
        grid.getHeaderRows().clear();
        HeaderRow headerRow = grid.appendHeaderRow();

        headerRow.getCell(nameCol).setComponent(createFilterHeader("Наименование", taskReportGrid::setName));
        headerRow.getCell(filenameCol).setComponent(createFilterHeader("Файл excel", taskReportGrid::setFileName));
        headerRow.getCell(statusCol).setComponent(createFilterHeader("Статус", taskReportGrid::setStatus));
        headerRow.getCell(errCol).setComponent(createFilterHeader("Результат выполнения", taskReportGrid::setErrorText));
        headerRow.getCell(usernameCol).setComponent(createFilterHeader("Пользователь", taskReportGrid::setUsername));
        headerRow.getCell(dateBegCol).setComponent(createFilterHeader("Дата начала выполнения", taskReportGrid::setDateBeg));
        headerRow.getCell(dateEditCol).setComponent(createFilterHeader("Дата окончания выполнения", taskReportGrid::setDateEdit));
    }


    private static class ReportTaskGridFilter{
        private final GridListDataView<ReportTask> dataView;

        private String name;

        private String file_name;

        private String status;

        private String error_text;

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

        public void setErrorText(String error_text) {
            this.error_text = error_text;
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
            boolean matchesStatus = matches(reportTask.getStatus().toString(), status);
            boolean matchesErrorText = matches(reportTask.getResult(), error_text);
            boolean matchesUsername = matches(reportTask.getUsername(), username);
            boolean matchesDateBeg = matches(reportTask.getDateBegString(), dateBeg);
            boolean matchesDateEdit = matches(reportTask.getDateEditString(), dateEdit);

            return matchesName && matchesFilename && matchesStatus && matchesErrorText && matchesUsername
                    && matchesDateBeg && matchesDateEdit;
        }

        private boolean matches(String value, String searchTerm) {
            return searchTerm == null || searchTerm.isEmpty() || value.toLowerCase().contains(searchTerm.toLowerCase());
        }
    }
    private static Component createFilterHeader(String labelText, Consumer<String> filterChangeConsumer) {
        Label label = new Label(labelText);
        TextField textField = new TextField();
        label.getStyle().set("padding-top", "var(--lumo-space-m)")
                .set("font-size", "var(--lumo-font-size-xs)");
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.setClearButtonVisible(true);
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        if (labelText.equals("Файл excel")){
            textField.setWidth("300px");
        } else textField.setWidthFull();
        textField.getStyle().set("max-width", "100%");
        textField.addValueChangeListener(
                e -> filterChangeConsumer.accept(e.getValue()));
        VerticalLayout layout = new VerticalLayout(label, textField);
        layout.getThemeList().clear();
        layout.getThemeList().add("spacing-xs");
        return layout;
    }

}
