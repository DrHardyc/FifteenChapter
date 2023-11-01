package ru.hardy.udio.view.dialog;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualhistoryonkocase.InsuranceCase;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleRequest;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleRequestRecord;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleResponseRecord;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequestRecord;
import ru.hardy.udio.domain.api.schedulepianddispplot.DTO.SchedulePIAndDispPlotRequestRecordDTO;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequestRecord;
import ru.hardy.udio.domain.button.BtnVariant;
import ru.hardy.udio.domain.button.UdioButton;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.domain.struct.dto.DNOutDto;
import ru.hardy.udio.view.grid.*;
import ru.hardy.udio.view.span.CMSpan;

import java.awt.*;
import java.util.List;

@Service
public class DialogViewGen {

    private final Dialog dialog = new Dialog();
    private final HorizontalLayout horizontalLayout = new HorizontalLayout();//Костыль Excel

    private final Span label = new Span();
    private UdioButton btnExcel;

    public DialogViewGen(){
        Button closeButton = new Button(new Icon(VaadinIcon.CLOSE_SMALL),
                (event) -> dialog.close());
        label.getStyle().set("margin-right", "auto");
        closeButton.getStyle().set("margin-left", "auto");
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        dialog.setHeight("100vh");
        dialog.setWidth("100vw");
        dialog.getHeader().add(horizontalLayout);//Костыль Excel
        dialog.getHeader().add(closeButton);
        dialog.setCloseOnOutsideClick(false);
        dialog.setDraggable(true);
        dialog.setResizable(true);
        dialog.setModal(false);
    }

    private void initFooter(){
        btnExcel = new UdioButton(".xlsx", BtnVariant.XLS);
        dialog.getFooter().add(label, btnExcel);
    }
    public Dialog getPADataPatientRequestRecordDialog(List<PADataPatientRequestRecord> paDataPatientRequestRecords) {
        initFooter();
        Grid<PADataPatientRequestRecord> grid = GridUtils.createNewDialogGrid(horizontalLayout, btnExcel);
        PADataPatientRequestRecordGrid paDataPatientRequestRecordGrid = new PADataPatientRequestRecordGrid();
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        GridListDataView<PADataPatientRequestRecord> paDataPatientRequestRecordGridListDataView = grid.setItems(paDataPatientRequestRecords);
        paDataPatientRequestRecordGrid.getGrid(grid, paDataPatientRequestRecordGridListDataView);
        paDataPatientRequestRecordGridListDataView.addItemCountChangeListener(ev -> {
            label.setText(String.valueOf(paDataPatientRequestRecordGridListDataView.getItemCount()));
        });
        dialog.add(grid);

        return dialog;
    }
    public Dialog getIndividualInformingRequestRecordDialog(List<IndividualInformingRequestRecord> individualInformingRequestRecords){
        initFooter();
        Grid<IndividualInformingRequestRecord> grid = GridUtils.createNewDialogGrid(horizontalLayout, btnExcel);
        IndividualInformingRequestRecordGrid individualInformingRequestRecordGrid = new IndividualInformingRequestRecordGrid();
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        GridListDataView<IndividualInformingRequestRecord> individualInformingRequestRecordGridListDataView = grid.setItems(individualInformingRequestRecords);
        individualInformingRequestRecordGrid.getGrid(grid, individualInformingRequestRecordGridListDataView);
        individualInformingRequestRecordGridListDataView.addItemCountChangeListener(ev -> {
            label.setText(String.valueOf(individualInformingRequestRecordGridListDataView.getItemCount()));
        });
        dialog.add(grid);

        return dialog;
    }

    public Dialog geOperatingScheduleResponseRecordDialog(List<OperatingScheduleRequestRecord> operatingScheduleRequestRecords){
        initFooter();
        Grid<OperatingScheduleRequestRecord> grid = GridUtils.createNewDialogGrid(horizontalLayout, btnExcel);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        GridListDataView<OperatingScheduleRequestRecord> operatingScheduleRequestRecordGridListDataView = grid.setItems(operatingScheduleRequestRecords);
        OperatingScheduleRequestRecordGrid.getGrid(grid, operatingScheduleRequestRecordGridListDataView);
        operatingScheduleRequestRecordGridListDataView.addItemCountChangeListener(ev -> {
            label.setText(String.valueOf(operatingScheduleRequestRecordGridListDataView.getItemCount()));
        });
        dialog.add(grid);

        return dialog;
    }

    public Dialog getInsuranceCases(List<InsuranceCase> insuranceCases) {
        initFooter();
        Grid<InsuranceCase> grid = GridUtils.createNewDialogGrid(horizontalLayout, btnExcel);
        InsuranceCaseGrid insuranceCaseGrid = new InsuranceCaseGrid();
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        GridListDataView<InsuranceCase> insuranceCaseGridListDataView = grid.setItems(insuranceCases);
        insuranceCaseGrid.getGrid(grid, insuranceCaseGridListDataView);
        insuranceCaseGridListDataView.addItemCountChangeListener(ev -> {
            label.setText(String.valueOf(insuranceCaseGridListDataView.getItemCount()));
        });
        dialog.add(grid);

        return dialog;
    }
    public Dialog getPeopleInfo(List<IndividualInformingRequestRecord> individualInformingRequestRecordList,
                                List<PADataPatientRequestRecord> paDataPatientRequestRecordList,
                                List<InsuranceCase> insuranceCaseList) {
        Dialog dialog1 = new Dialog();
        Button closeButton = new Button(new Icon(VaadinIcon.CLOSE_SMALL),
                (event) -> dialog1.close());
        closeButton.getStyle().set("margin-left", "auto");
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        HorizontalLayout hlII = new HorizontalLayout();
        Button btnII = new Button("Информирование");
        btnII.addClickListener(e -> {
            new DialogViewGen().getIndividualInformingRequestRecordDialog(individualInformingRequestRecordList).open();
        });
        Span spanII = new CMSpan(String.valueOf(individualInformingRequestRecordList.size()));
        hlII.add(btnII, spanII);

        HorizontalLayout hlPA = new HorizontalLayout();
        Span spanPA = new CMSpan(String.valueOf(paDataPatientRequestRecordList.size()));
        Button btnPa = new Button("Посещения/Обращения");
        btnPa.addClickListener(e -> {
            new DialogViewGen().getPADataPatientRequestRecordDialog(paDataPatientRequestRecordList).open();
        });
        hlPA.add(btnPa, spanPA);

        HorizontalLayout hlOnko = new HorizontalLayout();
        Span spanOnko = new CMSpan(String.valueOf(insuranceCaseList.size()));
        Button btnOnko = new Button("История онко-случаев");
        btnOnko.addClickListener(e -> {
            new DialogViewGen().getInsuranceCases(insuranceCaseList).open();
        });
        hlOnko.add(btnOnko, spanOnko);

        dialog1.add(hlII, hlPA, hlOnko);
        dialog1.setHeight("25vh");
        dialog1.setWidth("20vw");
        Span badge = new Span("Общая информация");
        badge.getStyle().set("font-weight", "bold");
        dialog1.getHeader().add(badge);
        dialog1.getHeader().add(closeButton);
        dialog1.setCloseOnOutsideClick(false);
        dialog1.setDraggable(true);
        dialog1.setResizable(true);
        dialog1.setModal(false);
        return dialog1;
    }

    public Dialog getDieReportDialog(List<DNOutDto> dnOutDtos){
        initFooter();
        Grid<DNOutDto> grid = GridUtils.createNewDialogGrid(horizontalLayout, btnExcel);
        DNOutDtoGrid dnOutDtoGrid = new DNOutDtoGrid();

        GridListDataView<DNOutDto> dnOutGridListDataView = grid.setItems(dnOutDtos);
        dnOutDtoGrid.getGrid(grid, dnOutGridListDataView);
        dnOutGridListDataView.addItemCountChangeListener(ev -> {
            label.setText(String.valueOf(dnOutGridListDataView.getItemCount()));
        });
        dialog.add(grid);

        return dialog;
    }

    public Dialog getMainReportDialog(List<DNGet> dnGets){
        initFooter();
        Grid<DNGet> grid = GridUtils.createNewDialogGrid(horizontalLayout, btnExcel);
        DNGetGrid dnGetGrid = new DNGetGrid();

        GridListDataView<DNGet> dnGetGridListDataView = grid.setItems(dnGets);
        dnGetGrid.getGrid(grid, dnGetGridListDataView);
        dnGetGridListDataView.addItemCountChangeListener(ev -> {
            label.setText(String.valueOf(dnGetGridListDataView.getItemCount()));
        });
        dialog.add(grid);

        return dialog;
    }

    public Dialog getDetailDialog(boolean bPolicy){
        dialog.setHeight("50vh");
        dialog.setWidth("50vw");

        FormLayout formLayout = new FormLayout();

        TextField tfRegAddress = new TextField();
        tfRegAddress.setReadOnly(true);
        TextField tfLocationAddress = new TextField();
        tfLocationAddress.setReadOnly(true);
        TextField tfMOAttach = new TextField();
        tfMOAttach.setReadOnly(true);

        Span spPolicy;
        if (bPolicy){
            spPolicy = new Span(createIcon(VaadinIcon.CHECK),
                    new Span("Действующий"));
            spPolicy.addClassNames(LumoUtility.TextColor.SUCCESS,
                    LumoUtility.Padding.SMALL,
                    LumoUtility.Background.BASE,
                    LumoUtility.BoxShadow.XSMALL,
                    LumoUtility.BorderRadius.LARGE);
        } else
        {
            spPolicy = new Span(createIcon(VaadinIcon.EXCLAMATION_CIRCLE_O),
                    new Span("Погашен"));
            spPolicy.getElement().getThemeList().add("badge error");
        }

        TextField tfCause = new TextField();
        tfCause.setReadOnly(true);

        formLayout.addFormItem(tfRegAddress, "Адрес регистрации");
        formLayout.addFormItem(tfLocationAddress, "Адрес места жительства");
        formLayout.addFormItem(tfMOAttach, "МО прикрепления");
        formLayout.addFormItem(spPolicy, "Полис");
        formLayout.addFormItem(tfCause, "Причина");
        dialog.add(formLayout);

        return dialog;
    }

    private Icon createIcon(VaadinIcon vaadinIcon) {
        Icon icon = vaadinIcon.create();
        icon.getStyle().set("padding", "var(--lumo-space-xs");
        return icon;
    }


}
