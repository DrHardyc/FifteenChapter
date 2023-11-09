package ru.hardy.udio.view;


import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hardy.udio.domain.button.BtnVariant;
import ru.hardy.udio.domain.button.UdioButton;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice.IndividualHistoryOnkoCaseResponseRecordService;
import ru.hardy.udio.service.apiservice.individualinformingservice.IndividualInformingRequestRecordService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.mo.PADataPatientRequestRecordService;
import ru.hardy.udio.view.dialog.DialogViewGen;


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

        Grid <People> grid = new Grid<>();
        grid.addColumn(People::getFIO).setHeader("ФИО");
        grid.addColumn(People::getAge).setHeader("Возраст");
        grid.addColumn(People::getDateBirth).setHeader("Дата рождения");
        grid.addColumn(People::getEnp).setHeader("ЕНП");
        //List<PeopleInfo> peopleInfoList = new ArrayList<>();

        ubtnSearch.addClickListener(e -> {
            //========================= тест=============
//            tfSurname.setValue("Амбалов");
//            tfName.setValue("Виталий");
//            tfPatronymic.setValue("юрьевич");
//            tfENP.setValue("1558630820000121");
            //====================================================
//            tfSurname.getValue();
//            tfName.getValue();
//            tfPatronymic.getValue();
//            dpDateBirth.getValue();
//            tfENP.getValue();
            grid.setItems(peopleService.getAllPeopleByNotEmptyField(tfSurname.getValue(), tfName.getValue(),
                    tfPatronymic.getValue(), dpDateBirth, tfENP.getValue()));
        });

        grid.addItemDoubleClickListener(ev -> {
            DialogViewGen dialogViewGen = new DialogViewGen();
            dialogViewGen.getPeopleInfo(individualInformingRequestRecordService.getAllByPeople(ev.getItem()),
                    paDataPatientRequestRecordService.getAllByPeople(ev.getItem()),
                    individualHistoryOnkoCaseResponseRecordService.getInsuredCases(ev.getItem())).open();
        });
        add(flPeople, ubtnSearch,  grid);
    }
}
