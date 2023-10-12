package ru.hardy.udio.view.grid;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequestRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.function.Consumer;

public class PADataPatientRequestRecordGrid {
    public void getGrid(Grid<PADataPatientRequestRecord> grid, GridListDataView<PADataPatientRequestRecord> paDataPatientRequestRecordGridListDataView) {
        Grid.Column<PADataPatientRequestRecord> mainDiagnosisCol = grid.addColumn(PADataPatientRequestRecord::getMainDiagnosis)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<PADataPatientRequestRecord> concomitantDiagnosisCol = grid
                .addColumn(PADataPatientRequestRecord::getConcomitantDiagnosis)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<PADataPatientRequestRecord> diagnosisComplicationsCol = grid
                .addColumn(PADataPatientRequestRecord::getDiagnosisComplications)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<PADataPatientRequestRecord> codeTypePreventiveActionsCol = grid
                .addColumn(PADataPatientRequestRecord::getCodeTypePreventiveActions)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<PADataPatientRequestRecord> nameTypePreventiveActionsCol = grid
                .addColumn(PADataPatientRequestRecord::getNameTypePreventiveActions)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<PADataPatientRequestRecord> dateIncludeCol = grid
                .addColumn(new LocalDateRenderer<>(PADataPatientRequestRecord::getDateIncludeLocalDate, "dd.MM.yyyy"))
                .setResizable(true)
                .setComparator(PADataPatientRequestRecord::getDateIncludeLocalDate);
        Grid.Column<PADataPatientRequestRecord> periodPACol = grid
                .addColumn(PADataPatientRequestRecord::getPeriodPA)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<PADataPatientRequestRecord> specialtyDoctorCodeCol = grid
                .addColumn(PADataPatientRequestRecord::getSpecialtyDoctorCode)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<PADataPatientRequestRecord> scheduledMonthAdmissionCol = grid
                .addColumn(PADataPatientRequestRecord::getScheduledMonthAdmission)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<PADataPatientRequestRecord> locationInspectionCol = grid
                .addColumn(PADataPatientRequestRecord::getLocationInspection)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<PADataPatientRequestRecord> dateInsuranceCaseCol = grid
                .addColumn(new LocalDateRenderer<>(PADataPatientRequestRecord::getDateInsuranceCaseLocalDate, "dd.MM.yyyy"))
                .setResizable(true)
                .setComparator(PADataPatientRequestRecord::getDateInsuranceCaseLocalDate);
        Grid.Column<PADataPatientRequestRecord> resultDispensaryAppointmentDoctorCol = grid
                .addColumn(PADataPatientRequestRecord::getResultDispensaryAppointmentDoctor)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<PADataPatientRequestRecord> resultDispensaryAppointmentCol = grid
                .addColumn(PADataPatientRequestRecord::getResultDispensaryAppointment)
                .setResizable(true)
                .setSortable(true);


        PADataPatientRequestRecordGridFilter paDataPatientRequestRecordGridFilter =
                new PADataPatientRequestRecordGridFilter(paDataPatientRequestRecordGridListDataView);
        grid.getHeaderRows().clear();
        grid.setMultiSort(true, Grid.MultiSortPriority.APPEND);
        HeaderRow headerRow = grid.appendHeaderRow();

        headerRow.getCell(mainDiagnosisCol)
                .setComponent(
                        GridUtils.createFilterHeader("Основной диагноз",
                        paDataPatientRequestRecordGridFilter::setMainDiagnosis));
        headerRow.getCell(concomitantDiagnosisCol)
                .setComponent(
                        GridUtils.createFilterHeader("Сопутствующий диагноз",
                        paDataPatientRequestRecordGridFilter::setConcomitantDiagnosis));
        headerRow.getCell(diagnosisComplicationsCol)
                .setComponent(
                        GridUtils.createFilterHeader("Диагноз осложнения",
                        paDataPatientRequestRecordGridFilter::setDiagnosisComplications));
        headerRow.getCell(codeTypePreventiveActionsCol)
                .setComponent(GridUtils.createFilterHeader("Код профмероприятия",
                        paDataPatientRequestRecordGridFilter::setCodeTypePreventiveActions));
        headerRow.getCell(nameTypePreventiveActionsCol)
                .setComponent(GridUtils.createFilterHeader("Тип профмероприятия",
                        paDataPatientRequestRecordGridFilter::setNameTypePreventiveActions));
        headerRow.getCell(dateIncludeCol)
                .setComponent(GridUtils.createFilterHeader("Дата включения в группу Д-наблюдения",
                        paDataPatientRequestRecordGridFilter::setDateInclude));
        headerRow.getCell(periodPACol)
                .setComponent(GridUtils.createFilterHeader("Период прохождения профмероприятий",
                        paDataPatientRequestRecordGridFilter::setPeriodPA));
        headerRow.getCell(specialtyDoctorCodeCol)
                .setComponent(GridUtils.createFilterHeader("Код специальности врача",
                        paDataPatientRequestRecordGridFilter::setSpecialtyDoctorCode));
        headerRow.getCell(scheduledMonthAdmissionCol)
                .setComponent(GridUtils.createFilterHeader("Плановый период (месяц) проведения следующего профмероприятия",
                        paDataPatientRequestRecordGridFilter::setScheduledMonthAdmission));
        headerRow.getCell(locationInspectionCol)
                .setComponent(GridUtils.createFilterHeader("Место проведения профмероприятия",
                        paDataPatientRequestRecordGridFilter::setLocationInspection));
        headerRow.getCell(dateInsuranceCaseCol)
                .setComponent(GridUtils.createFilterHeader("Дата посещения/обращения застрахованного лица",
                        paDataPatientRequestRecordGridFilter::setDateInsuranceCase));
        headerRow.getCell(resultDispensaryAppointmentDoctorCol)
                .setComponent(GridUtils.createFilterHeader("Результат профмероприятия",
                        paDataPatientRequestRecordGridFilter::setResultDispensaryAppointmentDoctor));
        headerRow.getCell(resultDispensaryAppointmentCol)
                .setComponent(GridUtils.createFilterHeader("Описание результата профмероприятия",
                        paDataPatientRequestRecordGridFilter::setResultDispensaryAppointment));

    }

    private static class PADataPatientRequestRecordGridFilter {
        private final GridListDataView<PADataPatientRequestRecord> dataView;

        private String mainDiagnosis;
        private String concomitantDiagnosis;
        private String diagnosisComplications;
        private String codeTypePreventiveActions;
        private String nameTypePreventiveActions;
        private String dateInclude;
        private String periodPA;
        private String specialtyDoctorCode;
        private String scheduledMonthAdmission;
        private String locationInspection;
        private String dateInsuranceCase;
        private String resultDispensaryAppointmentDoctor;
        private String resultDispensaryAppointment;

        public void setMainDiagnosis(String mainDiagnosis) {
            this.mainDiagnosis = mainDiagnosis;
            this.dataView.refreshAll();
        }

        public void setConcomitantDiagnosis(String concomitantDiagnosis) {
            this.concomitantDiagnosis = concomitantDiagnosis;
            this.dataView.refreshAll();
        }

        public void setDiagnosisComplications(String diagnosisComplications) {
            this.diagnosisComplications = diagnosisComplications;
            this.dataView.refreshAll();
        }

        public void setCodeTypePreventiveActions(String codeTypePreventiveActions) {
            this.codeTypePreventiveActions = codeTypePreventiveActions;
            this.dataView.refreshAll();
        }

        public void setNameTypePreventiveActions(String nameTypePreventiveActions) {
            this.nameTypePreventiveActions = nameTypePreventiveActions;
            this.dataView.refreshAll();
        }

        public void setDateInclude(String dateInclude) {
            this.dateInclude = dateInclude;
            this.dataView.refreshAll();
        }

        public void setPeriodPA(String periodPA) {
            this.periodPA = periodPA;
            this.dataView.refreshAll();
        }

        public void setSpecialtyDoctorCode(String specialtyDoctorCode) {
            this.specialtyDoctorCode = specialtyDoctorCode;
            this.dataView.refreshAll();
        }

        public void setScheduledMonthAdmission(String scheduledMonthAdmission) {
            this.scheduledMonthAdmission = scheduledMonthAdmission;
            this.dataView.refreshAll();
        }

        public void setLocationInspection(String locationInspection) {
            this.locationInspection = locationInspection;
            this.dataView.refreshAll();
        }

        public void setDateInsuranceCase(String dateInsuranceCase) {
            this.dateInsuranceCase = dateInsuranceCase;
            this.dataView.refreshAll();
        }

        public void setResultDispensaryAppointmentDoctor(String resultDispensaryAppointmentDoctor) {
            this.resultDispensaryAppointmentDoctor = resultDispensaryAppointmentDoctor;
            this.dataView.refreshAll();
        }

        public void setResultDispensaryAppointment(String resultDispensaryAppointment) {
            this.resultDispensaryAppointment = resultDispensaryAppointment;
            this.dataView.refreshAll();
        }

        public PADataPatientRequestRecordGridFilter(GridListDataView<PADataPatientRequestRecord> dataView) {
            this.dataView = dataView;
            this.dataView.addFilter(this::refresh);
        }
        public boolean refresh(PADataPatientRequestRecord paDataPatientRequestRecord) {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

            boolean matchesMainDiagnosis = matches(paDataPatientRequestRecord.getMainDiagnosis(), mainDiagnosis);
            boolean matchesConcomitantDiagnosis = matches(paDataPatientRequestRecord.getConcomitantDiagnosis(), concomitantDiagnosis);
            boolean matchesDiagnosisComplications = matches(paDataPatientRequestRecord.getDiagnosisComplications(), diagnosisComplications);
            boolean matchesCodeTypePreventiveActions = matches(String.valueOf(paDataPatientRequestRecord.getCodeTypePreventiveActions()), codeTypePreventiveActions);
            boolean matchesNameTypePreventiveActions = matches(paDataPatientRequestRecord.getNameTypePreventiveActions(), nameTypePreventiveActions);
            boolean matchesDateInclude = matches(dateFormat.format(paDataPatientRequestRecord.getDateInclude()), dateInclude);
            boolean matchesPeriodPA = matches(paDataPatientRequestRecord.getPeriodPA(), periodPA);
            boolean matchesSpecialtyDoctorCode = matches(String.valueOf(paDataPatientRequestRecord.getSpecialtyDoctorCode()), specialtyDoctorCode);
            boolean matchesScheduledMonthAdmission = matches(String.valueOf(paDataPatientRequestRecord.getScheduledMonthAdmission()), scheduledMonthAdmission);
            boolean matchesLocationInspection = matches(String.valueOf(paDataPatientRequestRecord.getDateInsuranceCaseLocalDate()), locationInspection);
            boolean matchesDateInsuranceCase = matches(dateFormat.format(paDataPatientRequestRecord.getDateInsuranceCase()), dateInsuranceCase);
            boolean matchesResultDispensaryAppointmentDoctor = matches(String.valueOf(paDataPatientRequestRecord.getResultDispensaryAppointmentDoctor()), resultDispensaryAppointmentDoctor);
            boolean matchesResultDispensaryAppointment = matches(String.valueOf(paDataPatientRequestRecord.getResultDispensaryAppointment()), resultDispensaryAppointment);

            return matchesMainDiagnosis && matchesConcomitantDiagnosis && matchesDiagnosisComplications
                    && matchesCodeTypePreventiveActions && matchesNameTypePreventiveActions && matchesDateInclude
                    && matchesPeriodPA && matchesSpecialtyDoctorCode && matchesScheduledMonthAdmission && matchesLocationInspection
                    && matchesDateInsuranceCase && matchesResultDispensaryAppointmentDoctor && matchesResultDispensaryAppointment;
        }

        private boolean matches(String value, String searchTerm) {
            return searchTerm == null || searchTerm.isEmpty() || value.toLowerCase().contains(searchTerm.toLowerCase());
        }
    }
}
