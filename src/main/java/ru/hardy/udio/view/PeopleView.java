package ru.hardy.udio.view;


import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hardy.udio.domain.api.PeopleInfo;
import ru.hardy.udio.domain.api.individualhistoryonkocase.InsuranceCase;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequestRecord;
import ru.hardy.udio.domain.button.BtnVariant;
import ru.hardy.udio.domain.button.UdioButton;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice.IndividualHistoryOnkoCaseResponseRecordService;
import ru.hardy.udio.service.apiservice.individualinformingservice.IndividualInformingRequestRecordService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.PADataPatientRequestRecordService;
import ru.hardy.udio.view.dialog.DialogViewGen;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Route(layout = MainView.class)
@RolesAllowed("ROLE_TFOMS")
public class PeopleView extends VerticalLayout {

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private PADataPatientRequestRecordService paDataPatientRequestRecordService;

    @Autowired
    private IndividualInformingRequestRecordService individualInformingRequestRecordService;

    @Autowired
    private IndividualHistoryOnkoCaseResponseRecordService individualHistoryOnkoCaseResponseRecordService;


    public PeopleView(){
        FormLayout flPeople = new FormLayout();

        TextField tfSurname = new TextField();
        tfSurname.setPlaceholder("Введите фамилия");

        TextField tfName = new TextField();
        tfName.setPlaceholder("Введите имя");

        TextField tfPatronymic = new TextField();
        tfPatronymic.setPlaceholder("Введите отчество");

        DatePicker dpDateBirth = new DatePicker();
        dpDateBirth.setPlaceholder("Введите дату рождения");

        TextField tfENP = new TextField();
        tfENP.setPlaceholder("Введите ЕНП");

        UdioButton ubtnSearch = new UdioButton("Поиск", BtnVariant.SEARCH);

        flPeople.addFormItem(tfSurname, "Фамилия");
        flPeople.addFormItem(tfName, "Имя");
        flPeople.addFormItem(tfPatronymic, "Отчество");
        flPeople.addFormItem(dpDateBirth, "Дата рождения");
        flPeople.addFormItem(tfENP, "ЕНП");

        Grid <PeopleInfo> grid = new Grid<>();
        grid.addColumn(PeopleInfo::getSurname).setHeader("Фамилия");
        grid.addColumn(PeopleInfo::getName).setHeader("Имя");
        grid.addColumn(PeopleInfo::getInforming).setHeader("Информирование");
        grid.addColumn(PeopleInfo::getInsuranceCase).setHeader("Страховые случаи");
        grid.addColumn(PeopleInfo::getVisitsCalls).setHeader("Посещения/Обращения");
        ubtnSearch.addClickListener(e -> {
            //========================= удалить тест=============
//            tfSurname.setValue("Амбалов");
//            tfName.setValue("Виталий");
//            tfPatronymic.setValue("юрьевич");
//            tfENP.setValue("1558630820000121");
            //====================================================
                tfSurname.getValue();
                tfName.getValue();
                tfPatronymic.getValue();
                dpDateBirth.getValue();
                tfENP.getValue();
            List<People> peopleList = peopleService.getAllPeopleByJDBC(tfSurname.getValue(), tfName.getValue(),
                    tfPatronymic.getValue(), dpDateBirth, tfENP.getValue());
            List<PeopleInfo> peopleInfoList = new ArrayList<>();
            for (People peopleNew : peopleList) {
                List<PADataPatientRequestRecord> visitsCallsList =
                        paDataPatientRequestRecordService.getAllByPeople(peopleNew);
                List<IndividualInformingRequestRecord> informingList =
                        individualInformingRequestRecordService.getAllByPeople(peopleNew);
                List<InsuranceCase> insuranceCases =
                        individualHistoryOnkoCaseResponseRecordService.getInsuredCases(peopleNew);
                peopleInfoList.add(new PeopleInfo(peopleNew, visitsCallsList, informingList, insuranceCases));
            }
            grid.setItems(peopleInfoList);
            grid.addItemDoubleClickListener(ev -> {
                DialogViewGen dialogViewGen = new DialogViewGen();
                Dialog dialog = new Dialog();
                switch (ev.getColumn().getHeaderText()) {
                    case "Информирование" -> {
                        dialog = dialogViewGen.getIndividualInformingRequestRecordDialog(ev.getItem().getInformingList());
                    }
                    case "Страховые случаи" -> {
                        dialog = dialogViewGen.getInsuranceCases(ev.getItem().getInsuranceCaseList());
                    }
                    case "Посещения/Обращения" -> {
                        dialog = dialogViewGen.getPADataPatientRequestRecordDialog(ev.getItem().getVisitsCallsList());
                    }
                }
                dialog.open();
            });
        });
        add(flPeople, ubtnSearch,  grid);
    }
}
