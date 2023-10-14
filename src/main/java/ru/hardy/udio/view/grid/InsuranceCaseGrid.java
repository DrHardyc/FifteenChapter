package ru.hardy.udio.view.grid;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import ru.hardy.udio.domain.api.individualhistoryonkocase.InsuranceCase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class InsuranceCaseGrid {

    public void getGrid(Grid<InsuranceCase> grid, GridListDataView<InsuranceCase> insuranceCaseGridListDataView) {
        Grid.Column<InsuranceCase> codeMOAttachCol = grid
                .addColumn(InsuranceCase::getCodeMOAttach)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<InsuranceCase> codeMOCol = grid
                .addColumn(InsuranceCase::getCodeMO)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<InsuranceCase> contactDetailsCol = grid
                .addColumn(InsuranceCase::getContactDetails)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<InsuranceCase> invoiceNumberCol = grid
                .addColumn(InsuranceCase::getInvoiceNumber)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<InsuranceCase> invoiceDateCol = grid
                .addColumn(new LocalDateRenderer<>(
                        InsuranceCase::getInvoiceDateLocalDate, "dd.MM.yyyy"))
                .setResizable(true)
                .setComparator(InsuranceCase::getInvoiceDateLocalDate);
        Grid.Column<InsuranceCase> insuranceCaseNameCol = grid
                .addColumn(InsuranceCase::getInsuranceCaseName)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<InsuranceCase> treatmentStartDateCol = grid
                .addColumn(new LocalDateRenderer<>(
                        InsuranceCase::getTreatmentStartDateLocalDate, "dd.MM.yyyy"))
                .setResizable(true)
                .setComparator(InsuranceCase::getTreatmentStartDateLocalDate);
        Grid.Column<InsuranceCase> treatmentEndDateCol = grid
                .addColumn(new LocalDateRenderer<>(
                        InsuranceCase::getTreatmentEndDateLocalDate, "dd.MM.yyyy"))
                .setResizable(true)
                .setComparator(InsuranceCase::getTreatmentEndDateLocalDate);
        Grid.Column<InsuranceCase> mainDiagnosisCol = grid
                .addColumn(InsuranceCase::getMainDiagnosis)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<InsuranceCase> concomitantDiagnosisCol = grid
                .addColumn(InsuranceCase::getConcomitantDiagnosis)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<InsuranceCase> complicationsDiagnosisCol = grid
                .addColumn(InsuranceCase::getComplicationsDiagnosis)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<InsuranceCase> resultSeekingCol = grid
                .addColumn(InsuranceCase::getResultSeeking)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<InsuranceCase> informationDOCol = grid
                .addColumn(InsuranceCase::getInformationDO)
                .setResizable(true)
                .setSortable(true);

        InsuranceCaseGridFilter insuranceCaseGridFilter =
                new InsuranceCaseGridFilter(insuranceCaseGridListDataView);
        grid.getHeaderRows().clear();
        grid.setMultiSort(true, Grid.MultiSortPriority.APPEND);
        HeaderRow headerRow = grid.appendHeaderRow();

        headerRow.getCell(codeMOAttachCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Код МО прикрепления", insuranceCaseGridFilter::setCodeMOAttach));
        headerRow.getCell(codeMOCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Код МО", insuranceCaseGridFilter::setCodeMO));
        headerRow.getCell(contactDetailsCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Контактные данные", insuranceCaseGridFilter::setContactDetails));
        headerRow.getCell(invoiceNumberCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Номер счета", insuranceCaseGridFilter::setInvoiceNumber));
        headerRow.getCell(invoiceDateCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Дата счета", insuranceCaseGridFilter::setInvoiceDate));
        headerRow.getCell(insuranceCaseNameCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Посещение/обращение", insuranceCaseGridFilter::setInsuranceCaseName));
        headerRow.getCell(treatmentStartDateCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Дата начала лечения", insuranceCaseGridFilter::setTreatmentStartDate));
        headerRow.getCell(treatmentEndDateCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Дата окончания лечения", insuranceCaseGridFilter::setTreatmentEndDate));
        headerRow.getCell(mainDiagnosisCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Код основного диагноза", insuranceCaseGridFilter::setMainDiagnosis));
        headerRow.getCell(concomitantDiagnosisCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Код сопутствующего диагноза", insuranceCaseGridFilter::setConcomitantDiagnosis));
        headerRow.getCell(complicationsDiagnosisCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Код диагноза осложнения", insuranceCaseGridFilter::setComplicationsDiagnosis));
        headerRow.getCell(resultSeekingCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Результат обращения за медицинской помощью", insuranceCaseGridFilter::setResultSeeking));
        headerRow.getCell(informationDOCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Сведения о диспансерном наблюдении", insuranceCaseGridFilter::setInformationDO));

    }

    private static class InsuranceCaseGridFilter {
        private final GridListDataView<InsuranceCase> dataView;

        public void setCodeMOAttach(String codeMOAttach) {
            this.codeMOAttach = codeMOAttach;
            this.dataView.refreshAll();
        }

        public void setCodeMO(String codeMO) {
            this.codeMO = codeMO;
            this.dataView.refreshAll();
        }

        public void setContactDetails(String contactDetails) {
            this.contactDetails = contactDetails;
            this.dataView.refreshAll();
        }

        public void setInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
            this.dataView.refreshAll();
        }

        public void setInvoiceDate(String invoiceDate) {
            this.invoiceDate = invoiceDate;
            this.dataView.refreshAll();
        }

        public void setInsuranceCaseName(String insuranceCaseName) {
            this.insuranceCaseName = insuranceCaseName;
            this.dataView.refreshAll();
        }

        public void setTreatmentStartDate(String treatmentStartDate) {
            this.treatmentStartDate = treatmentStartDate;
            this.dataView.refreshAll();
        }

        public void setTreatmentEndDate(String treatmentEndDate) {
            this.treatmentEndDate = treatmentEndDate;
            this.dataView.refreshAll();
        }

        public void setMainDiagnosis(String mainDiagnosis) {
            this.mainDiagnosis = mainDiagnosis;
            this.dataView.refreshAll();
        }

        public void setConcomitantDiagnosis(String concomitantDiagnosis) {
            this.concomitantDiagnosis = concomitantDiagnosis;
            this.dataView.refreshAll();
        }

        public void setComplicationsDiagnosis(String complicationsDiagnosis) {
            this.complicationsDiagnosis = complicationsDiagnosis;
            this.dataView.refreshAll();
        }

        public void setResultSeeking(String resultSeeking) {
            this.resultSeeking = resultSeeking;
            this.dataView.refreshAll();
        }

        public void setInformationDO(String informationDO) {
            this.informationDO = informationDO;
            this.dataView.refreshAll();
        }

        private String codeMOAttach;
        private String codeMO;
        private String contactDetails;
        private String invoiceNumber;
        private String invoiceDate;
        private String insuranceCaseName;
        private String treatmentStartDate;
        private String treatmentEndDate;
        private String mainDiagnosis;
        private String concomitantDiagnosis;
        private String complicationsDiagnosis;
        private String resultSeeking;
        private String informationDO;

        public InsuranceCaseGridFilter(GridListDataView<InsuranceCase> dataView) {
            this.dataView = dataView;
            this.dataView.addFilter(this::refresh);
        }
        public boolean refresh(InsuranceCase insuranceCase) {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

            boolean matchesCodeMOAttach = matches(String.valueOf(insuranceCase.getCodeMOAttach()), codeMOAttach);
            boolean matchesCodeMO = matches(String.valueOf(insuranceCase.getCodeMO()), codeMO);
            boolean matchesContactDetails = matches(insuranceCase.getContactDetails(), contactDetails);
            boolean matchesInvoiceNumber = matches(insuranceCase.getInvoiceNumber(), invoiceNumber);
            boolean matchesInvoiceDate = matches(dateFormat.format(insuranceCase.getInvoiceDate()), invoiceDate);
            boolean matchesInsuranceCaseName = matches(insuranceCase.getInsuranceCaseName(), insuranceCaseName);
            boolean matchesTreatmentStartDate = matches(dateFormat.format(insuranceCase.getTreatmentStartDate()), treatmentStartDate);
            boolean matchesTreatmentEndDate = matches(dateFormat.format(insuranceCase.getTreatmentEndDate()), treatmentEndDate);
            boolean matchesMainDiagnosis = matches(insuranceCase.getMainDiagnosis(), mainDiagnosis);
            boolean matchesConcomitantDiagnosis = matches(insuranceCase.getConcomitantDiagnosis(), concomitantDiagnosis);
            boolean matchesComplicationsDiagnosis = matches(insuranceCase.getComplicationsDiagnosis(), complicationsDiagnosis);
            boolean matchesResultSeeking = matches(insuranceCase.getResultSeeking(), resultSeeking);
            boolean matchesInformationDO = matches(insuranceCase.getInformationDO(), informationDO);

            return matchesCodeMOAttach && matchesCodeMO && matchesContactDetails && matchesInvoiceNumber
                    && matchesInvoiceDate && matchesInsuranceCaseName && matchesTreatmentStartDate && matchesTreatmentEndDate
                    && matchesMainDiagnosis && matchesConcomitantDiagnosis && matchesComplicationsDiagnosis
                    && matchesResultSeeking && matchesInformationDO;
        }

        private boolean matches(String value, String searchTerm) {
            return searchTerm == null || searchTerm.isEmpty() || value.toLowerCase().contains(searchTerm.toLowerCase());
        }
    }
}
