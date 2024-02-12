package ru.hardy.udio.view.dialog;


import ch.qos.logback.core.util.Loader;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.User;
import ru.hardy.udio.domain.api.hospitalization.dto.HospitalizationPatientDTO;
import ru.hardy.udio.domain.api.individualhistoryonkocase.InsuranceCase;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.numberavailableseats.dto.NumberAvailableSeatsDTO;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleRequestRecord;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatientRequestRecord;
import ru.hardy.udio.domain.api.schedulepianddispplot.dto.SchedulePIAndDispPlotDTO;
import ru.hardy.udio.domain.api.schedulepianddispplot.mo.SchedulePIAndDispPlotRequestRecord;
import ru.hardy.udio.domain.api.volumemedicalcare.dto.VolumeMedicalCareDTO;
import ru.hardy.udio.domain.button.BtnVariant;
import ru.hardy.udio.domain.button.UdioButton;
import ru.hardy.udio.domain.regul.*;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.domain.struct.dto.DNOutDto;
import ru.hardy.udio.service.AChartService;
import ru.hardy.udio.service.regulservice.FileUlService;
import ru.hardy.udio.view.grid.*;
import ru.hardy.udio.view.span.CMSpan;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class DialogGen extends Dialog {

    //private final Dialog dialog = new Dialog();
    private final HorizontalLayout horizontalLayout = new HorizontalLayout();//Костыль Excel
    private final Span label = new Span();
    private UdioButton btnExcel;
    private UdioButton btnChart;

    public DialogGen(){
        Button closeButton = new Button(new Icon(VaadinIcon.CLOSE_SMALL),
                (event) -> this.close());
        label.getStyle().set("margin-right", "auto");
        closeButton.getStyle().set("margin-left", "auto");
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        this.setHeight("100vh");
        this.setWidth("100vw");
        this.getHeader().add(horizontalLayout);//Костыль Excel
        this.getHeader().add(closeButton);
        this.setCloseOnOutsideClick(false);
        this.setDraggable(true);
        this.setResizable(true);
        this.setModal(false);
    }

    public DialogGen(String height, String width, String caption){
        Button closeButton = new Button(new Icon(VaadinIcon.CLOSE_SMALL),
                (event) -> this.close());
        label.getStyle().set("margin-right", "auto");
        closeButton.getStyle().set("margin-left", "auto");
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        this.setHeight(height + "vh");
        this.setWidth(width + "vw");
        this.getHeader().add(horizontalLayout);//Костыль Excel
        horizontalLayout.add(new H4(caption));
        this.getHeader().add(closeButton);
        this.setCloseOnOutsideClick(false);
        this.setDraggable(true);
        this.setResizable(true);
        this.setModal(false);
    }

    private void initFooter(){
        btnExcel = new UdioButton(".xlsx", BtnVariant.XLS);
        btnExcel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnChart = new UdioButton("график", BtnVariant.CHART);
        btnChart.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.getFooter().add(label, btnChart, btnExcel);
    }

    public Dialog getSchedulePIAndDispPlotResponseRecordDialog(List<SchedulePIAndDispPlotRequestRecord> schedulePIAndDispPlotRequestRecords) {
        initFooter();
        TreeGrid<SchedulePIAndDispPlotDTO> grid = GridUtils.createNewDialogTreeGrid(horizontalLayout, btnExcel);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        SchedulePIAndDispPlotGrid schedulePIAndDispPlotGrid = new SchedulePIAndDispPlotGrid();

        schedulePIAndDispPlotGrid.getGrid(grid, schedulePIAndDispPlotRequestRecords);
        this.add(grid);

        return this;
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
        this.add(grid);

        return this;
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
        this.add(grid);

        return this;
    }

    public Dialog getOperatingScheduleResponseRecordDialog(List<OperatingScheduleRequestRecord> operatingScheduleRequestRecords){
        initFooter();
        Grid<OperatingScheduleRequestRecord> grid = GridUtils.createNewDialogGrid(horizontalLayout, btnExcel);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        GridListDataView<OperatingScheduleRequestRecord> operatingScheduleRequestRecordGridListDataView = grid.setItems(operatingScheduleRequestRecords);
        OperatingScheduleRequestRecordGrid.getGrid(grid, operatingScheduleRequestRecordGridListDataView);
        operatingScheduleRequestRecordGridListDataView.addItemCountChangeListener(ev -> {
            label.setText(String.valueOf(operatingScheduleRequestRecordGridListDataView.getItemCount()));
        });
        this.add(grid);

        return this;
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
        this.add(grid);

        return this;
    }
    public Dialog getPeopleInfo(List<IndividualInformingRequestRecord> individualInformingRequestRecordList,
                                List<PADataPatientRequestRecord> paDataPatientRequestRecordList,
                                List<InsuranceCase> insuranceCaseList, List<HospitalizationPatientDTO> hospitalizationPatientDTOS
            /*,List<RecommendationsPatientRequestRecord> recommendationsPatientRequestRecords*/) {
        Dialog dialog1 = new Dialog();
        Button closeButton = new Button(new Icon(VaadinIcon.CLOSE_SMALL),
                (event) -> dialog1.close());
        closeButton.getStyle().set("margin-left", "auto");
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        HorizontalLayout hlII = new HorizontalLayout();
        Button btnII = new Button("Информирование");
        btnII.addClickListener(e -> {
            new DialogGen().getIndividualInformingRequestRecordDialog(individualInformingRequestRecordList).open();
        });
        Span spanII = new CMSpan(String.valueOf(individualInformingRequestRecordList.size()));
        hlII.add(btnII, spanII);

        HorizontalLayout hlPA = new HorizontalLayout();
        Span spanPA = new CMSpan(String.valueOf(paDataPatientRequestRecordList.size()));
        Button btnPa = new Button("Посещения/Обращения");
        btnPa.addClickListener(e -> {
            new DialogGen().getPADataPatientRequestRecordDialog(paDataPatientRequestRecordList).open();
        });
        hlPA.add(btnPa, spanPA);

        HorizontalLayout hlOnko = new HorizontalLayout();
        Span spanOnko = new CMSpan(String.valueOf(insuranceCaseList.size()));
        Button btnOnko = new Button("История онко-случаев");
        btnOnko.addClickListener(e -> {
            new DialogGen().getInsuranceCases(insuranceCaseList).open();
        });
        hlOnko.add(btnOnko, spanOnko);

        HorizontalLayout hlHospitalization = new HorizontalLayout();
        Span spanHospitalization = new CMSpan(String.valueOf(hospitalizationPatientDTOS.size()));
        Button btnHospitalization = new Button("Госпитализация");
        btnHospitalization.addClickListener(e -> {
            new DialogGen().getHospitalization(hospitalizationPatientDTOS).open();
        });
        hlHospitalization.add(btnHospitalization, spanHospitalization);

        dialog1.add(hlII, hlPA, hlOnko, hlHospitalization);
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

    private Dialog getHospitalization(List<HospitalizationPatientDTO> hospitalizationPatientDTOS) {
        initFooter();
        Grid grid = GridUtils.createNewDialogGrid(horizontalLayout, btnExcel);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        HospitalizationGrid hospitalizationGrid = new HospitalizationGrid();

        hospitalizationGrid.getGrid(grid, hospitalizationPatientDTOS);
        this.add(grid);

        return this;
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
        this.add(grid);

        return this;
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
        this.add(grid);

        return this;
    }

    public Dialog getDetailDialog(boolean bPolicy){
        this.setHeight("50vh");
        this.setWidth("50vw");

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
        this.add(formLayout);

        return this;
    }

    private Icon createIcon(VaadinIcon vaadinIcon) {
        Icon icon = vaadinIcon.create();
        icon.getStyle().set("padding", "var(--lumo-space-xs");
        return icon;
    }

    public Dialog getVolumeMedicalCareDialog(List<VolumeMedicalCareDTO> volumeMedicalCareDTOS) {
        initFooter();
        TreeGrid<VolumeMedicalCareDTO> grid = new GridUtils().createNewDialogVolumeMedicalCareDTOGrid(volumeMedicalCareDTOS, horizontalLayout, btnExcel);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        VolumeMedicalCareDTOGrid volumeMedicalCareDTOGrid = new VolumeMedicalCareDTOGrid();

        btnChart.addClickListener(buttonClickEvent -> {
            new Dialog(new AChartService().getRangedVerticalBarChartExample(volumeMedicalCareDTOS)).open();
        });

        volumeMedicalCareDTOGrid.getGrid(grid, volumeMedicalCareDTOS);
        this.add(grid);

        return this;
    }

    public Dialog getNumberAvailableSeatsDialog(List<NumberAvailableSeatsDTO> numberAvailableSeatsDTOS) {
        initFooter();
        Grid grid = GridUtils.createNewDialogGrid(horizontalLayout, btnExcel);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        NumberAvailableSeatsGrid numberAvailableSeatsGrid = new NumberAvailableSeatsGrid();

        numberAvailableSeatsGrid.getGrid(grid, numberAvailableSeatsDTOS);
        this.add(grid);

        return this;
    }

    public Dialog getLoadSettingDialog(TextField tfPathIn, TextField tfPathOut, Button btnSave){
        VerticalLayout verticalLayout = new VerticalLayout(tfPathIn, tfPathOut, btnSave);
        this.add(verticalLayout);
        return this;
    }

    public Dialog getAddNewRegUL(FileUlService fileUlService, User user){
        TabSheet tabSheet = new TabSheet();

        TextField tfFullName = new TextField("Название");
        TextField tfShortName = new TextField("Сокращенное");
        TextField tfStatus = new TextField("Статус");
        ComboBox<String> cbType = new ComboBox<>("Вид");
        cbType.setItems("ИП", "ЮЛ");
        TextField tfINN = new TextField("ИНН");
        TextField tfOGRN = new TextField("ОГРН");
        TextField tfKPP = new TextField("КПП");

        FormLayout flMain = new FormLayout(tfFullName, tfShortName, tfStatus, cbType, tfINN, tfOGRN,
                tfKPP);
        flMain.setColspan(tfFullName, 4);
        flMain.setColspan(tfShortName, 2);
        flMain.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 4));
        tabSheet.add("Основные", flMain);

        TextField tfRegion = new TextField("Регион");
        TextField tfRaion = new TextField("Район");
        TextField tfGorod = new TextField("Город");
        TextField tfNasPunkt = new TextField("Населенный пункт");
        TextField tfUlica = new TextField("Улица");
        TextField tfDom = new TextField("Дом");
        TextField tfKorpus = new TextField("Корпус");
        TextField tfKvartira = new TextField("Квартира");
        TextField tfIndex = new TextField("Индекс");

        FormLayout flAddress = new FormLayout(tfRegion, tfRaion,
                tfGorod, tfNasPunkt, tfUlica, tfDom, tfKorpus, tfKvartira, tfIndex);
        flAddress.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 5));
        tabSheet.add("Адрес", flAddress);


        TextField tfRegNO = new TextField("Орган выполневший регистрацию");
        DatePicker dpDateRegNO = new DatePicker("Дата регистрации");
        TextField tfPFR = new TextField("Орган ПФР");
        TextField tfNumberPFR = new TextField("Номер");
        TextField tfFSS = new TextField("Орган ФСС");
        TextField tfNumberFSS = new TextField("Номер");

        FormLayout flRegistration = new FormLayout(tfRegNO, dpDateRegNO, tfPFR, tfNumberPFR,
                tfFSS, tfNumberFSS);
        flRegistration.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3));
        flRegistration.setColspan(tfRegNO, 2);
        flRegistration.setColspan(tfPFR, 2);
        flRegistration.setColspan(tfFSS, 2);
        tabSheet.add("Регистрационные органы", flRegistration);

        TextField tfSurname = new TextField("Фамилия");
        TextField tfName = new TextField("Имя");
        TextField tfPatronymic = new TextField("Отчество");
        TextField tfDolj = new TextField("Должность");
        FormLayout flFizLico = new FormLayout(tfSurname, tfName, tfPatronymic, tfDolj);
        flFizLico.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 4));
        tabSheet.add("Физическое лицо", flFizLico);

        Button btnSave = new Button("Сохранить");
        btnSave.addClickListener(event -> {
            FileUL fileUL = new FileUL();
            fileUL.setIdFile("0");
            fileUL.setUser_id(user.getId());
            fileUL.setQuantityDoc("1");
            fileUL.setInfoType("Добавлено вручную");
            DocumentUL documentUL = new DocumentUL();
            if (cbType.getValue().equals("ИП")){
                PersonIP personIP = new PersonIP();
                personIP.setFl(new FL(new FIOIP(tfSurname.getValue(), tfName.getValue(), tfPatronymic.getValue())));
                personIP.setStatus(new StatusIP(new Status(tfStatus.getValue())));
                personIP.setInnFl(tfINN.getValue());
                personIP.setOgrnIp(tfOGRN.getValue());
                personIP.setAdrMJ(new AdrMJ(new AdresRF(tfIndex.getValue(),
                        new RegionType(tfRegion.getValue()),
                        new RaionType(tfRaion.getValue()),
                        new GorodType(tfGorod.getValue()),
                        new NaselPunktType(tfNasPunkt.getValue()),
                        new UlicaType(tfUlica.getValue()),
                        tfDom.getValue(),
                        tfKorpus.getValue(),
                        tfKvartira.getValue())));

                personIP.setRegOrg(new RegOrg(tfRegNO.getValue()));
                personIP.setRegIP(new RegIP(dpDateRegNO.getValue()));
                personIP.setRegPF(new RegPFIP(new OrgPFIP(tfNumberPFR.getValue(), tfPFR.getValue())));
                personIP.setRegFSSIP(new RegFSSIP(new OrgFSSIP(tfNumberFSS.getValue(), tfFSS.getValue())));
                documentUL.setPersonIP(personIP);
            } else {
                PersonUL personUL = new PersonUL();
                personUL.setNameUl(new NameUL(tfFullName.getValue(), new ShortNameUlType(tfShortName.getValue())));
                personUL.setStatusUl(Collections.singleton(new StatusUl(new Status(tfStatus.getValue()))));
                personUL.setInn(tfINN.getValue());
                personUL.setOgrn(tfOGRN.getValue());
                personUL.setKpp(tfKPP.getValue());
                personUL.setAddressUL(new AddressUL(new AdrRFEGRULType(tfIndex.getValue(),
                        new RegionType(tfRegion.getValue()),
                        new RaionType(tfRaion.getValue()),
                        new GorodType(tfGorod.getValue()),
                        new NaselPunktType(tfNasPunkt.getValue()),
                        new UlicaType(tfUlica.getValue()),
                        tfDom.getValue(),
                        tfKorpus.getValue(),
                        tfKvartira.getValue())));
                personUL.setRegOrg(new RegOrg(tfRegNO.getValue()));
                personUL.setObrUL(new ObrUL(dpDateRegNO.getValue()));
                personUL.setRegPF(new RegPF(tfNumberPFR.getValue(), new OrgPF(tfPFR.getValue())));
                personUL.setRegFSS(new RegFSS(tfNumberFSS.getValue(), new OrgFSS(tfFSS.getValue())));
                documentUL.setPersonUL(personUL);
            }
            fileUL.setDocumentUL(Collections.singleton(documentUL));
            fileUlService.add(fileUL);
        });

        this.getFooter().add(btnSave);
        this.add(tabSheet);
        return this;
    }
}
