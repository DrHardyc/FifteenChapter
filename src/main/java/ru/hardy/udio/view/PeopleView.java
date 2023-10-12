package ru.hardy.udio.view;


import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hardy.udio.domain.api.PeopleInfo;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseRequestRecord;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequestRecord;
import ru.hardy.udio.domain.button.BtnVariant;
import ru.hardy.udio.domain.button.UdioButton;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice.IndividualHistoryOnkoCaseRequestRecordService;
import ru.hardy.udio.service.apiservice.individualinformingservice.IndividualInformingRequestRecordService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.PADataPatientRequestRecordService;
import ru.hardy.udio.view.dialog.DialogViewGen;

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
    private IndividualHistoryOnkoCaseRequestRecordService individualHistoryOnkoCaseRequestRecordService;


    public PeopleView(){
        FormLayout flPeople = new FormLayout();

        TextField tfSurname = new TextField();
        tfSurname.setPlaceholder("Введите фамилия");

        TextField tfName = new TextField();
        tfName.setPlaceholder("Введите имя");

        TextField tfPatronymic = new TextField();
        tfPatronymic.setPlaceholder("Введите отчество");

        TextField tfENP = new TextField();
        tfENP.setPlaceholder("Введите ЕНП");

        UdioButton ubtnSearch = new UdioButton("Поиск", BtnVariant.SEARCH);

        flPeople.addFormItem(tfSurname, "Фамилия");
        flPeople.addFormItem(tfName, "Имя");
        flPeople.addFormItem(tfPatronymic, "Отчество");
        flPeople.addFormItem(tfENP, "ЕНП");

        Grid <PeopleInfo> grid = new Grid<>();
        grid.addColumn(PeopleInfo::getSurname).setHeader("Фамилия");
        grid.addColumn(PeopleInfo::getName).setHeader("Имя");
        grid.addColumn(PeopleInfo::getInforming).setHeader("Информирование");
        grid.addColumn(PeopleInfo::getInsuranceCase).setHeader("Страховые случаи");
        grid.addColumn(PeopleInfo::getVisitsCalls).setHeader("Посещения/Обращения");
        ubtnSearch.addClickListener(e -> {
            PADataPatientRequestRecord paDataPatientRequestRecord
                    = new PADataPatientRequestRecord();
            paDataPatientRequestRecord.setSurname(tfSurname.getValue());
            paDataPatientRequestRecord.setName(tfName.getValue());
            paDataPatientRequestRecord.setPatronymic(tfPatronymic.getValue());
            paDataPatientRequestRecord.setEnp(tfENP.getValue());

            People people = peopleService.search(paDataPatientRequestRecord);

            List<PADataPatientRequestRecord> paDataPatientRequestRecordList =
                    paDataPatientRequestRecordService.getAllByPeople(people);
            List<IndividualInformingRequestRecord> individualInformingRequestRecordList =
                    individualInformingRequestRecordService.getAllByPeople(people);
            List<IndividualHistoryOnkoCaseRequestRecord> individualHistoryOnkoCaseRequestRecordList =
                    individualHistoryOnkoCaseRequestRecordService.getAllByPeople(people);

            PeopleInfo peopleInfo = new PeopleInfo(people, individualHistoryOnkoCaseRequestRecordList.size(),
                    paDataPatientRequestRecordList.size(), individualInformingRequestRecordList.size());
            grid.setItems(peopleInfo);

            grid.addItemDoubleClickListener(ev -> {
                DialogViewGen dialogViewGen = new DialogViewGen();
                Dialog dialog = null;
                switch (ev.getColumn().getHeaderText()) {
                    case "Информирование" -> {
                        dialog = dialogViewGen.getIndividualInformingRequestRecordDialog(individualInformingRequestRecordList);
                    }
                    case "Страховые случаи" -> {

                    }
                    case "Посещения/Обращения" -> {
                        dialog = dialogViewGen.getPADataPatientRequestRecordDialog(paDataPatientRequestRecordList);
                    }
                }
                Objects.requireNonNull(dialog).open();
            });

        });
        UdioButton udioButton = new UdioButton("Test", BtnVariant.SEARCH);
        udioButton.addClickListener(e -> {
            tfSurname.setValue("Премудрая");
            tfName.setValue("Василиса");
            tfPatronymic.setValue("Ивановна");
            tfENP.setValue("1235486925412365");
        });
        add(flPeople, ubtnSearch, udioButton, grid);
    }
}
