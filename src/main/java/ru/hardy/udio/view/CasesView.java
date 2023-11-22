package ru.hardy.udio.view;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatientRequestRecord;
import ru.hardy.udio.domain.button.BtnVariant;
import ru.hardy.udio.domain.button.UdioButton;
import ru.hardy.udio.domain.generic.ResultProcessingClass;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.mo.PADataPatientRequestRecordService;
import ru.hardy.udio.view.dialog.DialogGridGen;

import java.util.List;

@Route(layout = MainView.class)
@RolesAllowed("ROLE_TFOMS")
public class CasesView extends VerticalLayout {

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private PADataPatientRequestRecordService paDataPatientRequestRecordService;


    public CasesView(){
        FormLayout flPeople = new FormLayout();

        TextField tfSurname = new TextField();
        tfSurname.setPlaceholder("Введите фамилия");

        TextField tfName = new TextField();
        tfName.setPlaceholder("Введите имя");

        TextField tfPatronymic = new TextField();
        tfPatronymic.setPlaceholder("Введите отчество");

        TextField tfENP = new TextField();
        tfENP.setPlaceholder("Введите ЕНП");

        TextField tfMainDiag = new TextField();
        tfMainDiag.setPlaceholder("Введите диагноз");

        UdioButton ubtnSearch = new UdioButton("Поиск", BtnVariant.SEARCH);

        flPeople.addFormItem(tfSurname, "Фамилия");
        flPeople.addFormItem(tfName, "Имя");
        flPeople.addFormItem(tfPatronymic, "Отчество");
        flPeople.addFormItem(tfENP, "ЕНП");
        flPeople.addFormItem(tfMainDiag, "Основной диагноз");

        ubtnSearch.addClickListener(e -> {
            PADataPatientRequestRecord paDataPatientRequestRecord
                    = new PADataPatientRequestRecord();
            paDataPatientRequestRecord.setSurname(tfSurname.getValue());
            paDataPatientRequestRecord.setName(tfName.getValue());
            paDataPatientRequestRecord.setPatronymic(tfPatronymic.getValue());
            paDataPatientRequestRecord.setEnp(tfENP.getValue());

            ResultProcessingClass<People> peopleResultProcessingClass = peopleService.search(paDataPatientRequestRecord);

            List<PADataPatientRequestRecord> paDataPatientRequestRecords =
                    paDataPatientRequestRecordService.getAllByPeople(peopleResultProcessingClass.getProcessingClass());
            if (peopleResultProcessingClass.getProcessingClass() != null) {
                DialogGridGen dialogGridGen = new DialogGridGen();
                Dialog dialog = dialogGridGen.getPADataPatientRequestRecordDialog(paDataPatientRequestRecords);
                dialog.open();
            } else {
                Notification.show("Пациент не найден");
            }
        });
        UdioButton udioButton = new UdioButton("Test", BtnVariant.SEARCH);
        udioButton.addClickListener(e -> {
            tfSurname.setValue("Премудрая");
            tfName.setValue("Василиса");
            tfPatronymic.setValue("Ивановна");
            tfENP.setValue("1235486925412365");
        });
        add(flPeople, ubtnSearch, udioButton);
    }
}
