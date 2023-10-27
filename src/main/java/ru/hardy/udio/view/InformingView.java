package ru.hardy.udio.view;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.button.BtnVariant;
import ru.hardy.udio.domain.button.UdioButton;
import ru.hardy.udio.domain.generic.ResultProcessingClass;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.individualinformingservice.IndividualInformingRequestRecordService;
import ru.hardy.udio.view.dialog.DialogViewGen;

import java.util.List;

@Route(layout = MainView.class)
@RolesAllowed("ROLE_TFOMS")
public class InformingView extends VerticalLayout {

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private IndividualInformingRequestRecordService individualInformingRequestRecordService;

    public InformingView(){

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
            IndividualInformingRequestRecord historyInformingRequestRecord
                    = new IndividualInformingRequestRecord();
            historyInformingRequestRecord.setSurname(tfSurname.getValue());
            historyInformingRequestRecord.setName(tfName.getValue());
            historyInformingRequestRecord.setPatronymic(tfPatronymic.getValue());
            historyInformingRequestRecord.setEnp(tfENP.getValue());

            ResultProcessingClass<People> peopleResultProcessingClass = peopleService.search(historyInformingRequestRecord);

            List<IndividualInformingRequestRecord> individualInformingRequestRecordList =
                    individualInformingRequestRecordService.getAllByPeople(peopleResultProcessingClass.getProcessingClass());
            if (peopleResultProcessingClass.getProcessingClass() != null) {
                DialogViewGen dialogViewGen = new DialogViewGen();
                Dialog dialog = dialogViewGen.getIndividualInformingRequestRecordDialog(individualInformingRequestRecordList);
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
