package ru.hardy.udio.view;


import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hardy.udio.domain.api.recommendationspatient.mo.RecommendationsPatient;
import ru.hardy.udio.domain.button.BtnVariant;
import ru.hardy.udio.domain.button.UdioButton;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.hospitalizationservice.dto.HospitalizationPatientDTOService;
import ru.hardy.udio.service.apiservice.hospitalizationservice.mo.HospitalizationService;
import ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice.IndividualHistoryOnkoCaseResponseRecordService;
import ru.hardy.udio.service.apiservice.individualinformingservice.IndividualInformingRequestRecordService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.mo.PADataPatientRequestRecordService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.mo.PADataPatientService;
import ru.hardy.udio.service.apiservice.recommendationspatientservice.so.RecommendationsPatientService;
import ru.hardy.udio.view.dialog.DialogGen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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

    @Autowired
    private PADataPatientService paDataPatientService;
    @Autowired
    private HospitalizationService hospitalizationService;
    @Autowired
    private RecommendationsPatientService recommendationsPatientService;
    @Autowired
    private HospitalizationPatientDTOService hospitalizationPatientDTOService;

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

        ComboBox<String> cbOtherFilter = getCbOtherFilters();

        UdioButton ubtnSearch = new UdioButton("Поиск", BtnVariant.SEARCH);

        flPeople.addFormItem(tfSurname, "Фамилия");
        flPeople.addFormItem(tfName, "Имя");
        flPeople.addFormItem(tfPatronymic, "Отчество");
        flPeople.addFormItem(dpDateBirth, "Дата рождения");
        flPeople.addFormItem(tfENP, "ЕНП");
        flPeople.addFormItem(cbOtherFilter, "Общие фильтры");

        Grid <People> grid = new Grid<>();
        grid.addColumn(People::getFIO).setHeader("ФИО");
        grid.addColumn(People::getAge).setHeader("Возраст");
        grid.addColumn(People::getDateBirth).setHeader("Дата рождения");
        grid.addColumn(People::getEnp).setHeader("ЕНП");

        ubtnSearch.addClickListener(e -> {
            if (cbOtherFilter.getValue() == null) {
                grid.setItems(peopleService.getAllPeopleByNotEmptyField(tfSurname.getValue(), tfName.getValue(),
                        tfPatronymic.getValue(), dpDateBirth, tfENP.getValue()));
            } else if (cbOtherFilter.getValue().equals("Подлежащие")){
                grid.setItems(getAllByCodePAPatient(tfSurname.getValue(), tfName.getValue(), tfPatronymic.getValue(), dpDateBirth, tfENP.getValue(),
                        0, 0));
            } else if (cbOtherFilter.getValue().equals("Начавшие прохождение диспансеризации")){
                grid.setItems(getAllByCodePAPatient(tfSurname.getValue(), tfName.getValue(), tfPatronymic.getValue(), dpDateBirth, tfENP.getValue(),
                        0, 1));
            } else if (cbOtherFilter.getValue().equals("Завершившие I этап диспансеризации")){
                grid.setItems(getAllByCodePAPatient(tfSurname.getValue(), tfName.getValue(), tfPatronymic.getValue(), dpDateBirth, tfENP.getValue(),
                        1, 2));
            } else if (cbOtherFilter.getValue().equals("Направленные на II этап диспансеризации")){
                grid.setItems(getAllByCodePAPatient(tfSurname.getValue(), tfName.getValue(), tfPatronymic.getValue(), dpDateBirth, tfENP.getValue(),
                        2, 3));
            } else if (cbOtherFilter.getValue().equals("Завершившие II этап диспансеризации")){
                grid.setItems(getAllByCodePAPatient(tfSurname.getValue(), tfName.getValue(), tfPatronymic.getValue(), dpDateBirth, tfENP.getValue(),
                        2, 2));
            } else if (cbOtherFilter.getValue().equals("Прошедшие профосмотры")){
                grid.setItems(getAllByCodePAPatient(tfSurname.getValue(), tfName.getValue(), tfPatronymic.getValue(), dpDateBirth, tfENP.getValue(),
                        4, 2));
            } else if (cbOtherFilter.getValue().equals("Прошедшие Д-наблюдение")){
                grid.setItems(getAllByCodePAPatient(tfSurname.getValue(), tfName.getValue(), tfPatronymic.getValue(), dpDateBirth, tfENP.getValue(),
                        3, 4));
            } else if (cbOtherFilter.getValue().equals("Госпитализированные")){
                grid.setItems(getAllByCodeHospitalization(tfSurname.getValue(), tfName.getValue(), tfPatronymic.getValue(), dpDateBirth, tfENP.getValue()));
            } else if (cbOtherFilter.getValue().equals("Телемедицина")){
                grid.setItems(getAllByRecommendationPatient(tfSurname.getValue(), tfName.getValue(), tfPatronymic.getValue(), dpDateBirth, tfENP.getValue()));
            }

        });

        grid.addItemDoubleClickListener(ev -> {
            DialogGen dialogGen = new DialogGen();
            dialogGen.getPeopleInfo(individualInformingRequestRecordService.getAllByPeople(ev.getItem()),
                    paDataPatientRequestRecordService.getAllByPeople(ev.getItem()),
                    individualHistoryOnkoCaseResponseRecordService.getInsuredCases(ev.getItem()),
                    hospitalizationPatientDTOService.getAllByPeople(ev.getItem())).open();
        });
        add(flPeople, ubtnSearch,  grid);
    }

    @NotNull
    private static ComboBox<String> getCbOtherFilters() {
        List<String> otherFilterList = new ArrayList<>();
        otherFilterList.add("Подлежащие");
        otherFilterList.add("Начавшие прохождение диспансеризации");
        otherFilterList.add("Завершившие I этап диспансеризации");
        otherFilterList.add("Направленные на II этап диспансеризации");
        otherFilterList.add("Завершившие II этап диспансеризации");
        otherFilterList.add("Прошедшие профосмотры");
        otherFilterList.add("Прошедшие Д-наблюдение");
        otherFilterList.add("Госпитализированные");
        otherFilterList.add("Телемедицина");
        ComboBox<String> cbOtherFilter = new ComboBox<>();
        cbOtherFilter.setItems(otherFilterList);
        return cbOtherFilter;
    }

    private List<People> getAllByRecommendationPatient(String surname, String name, String patronymic, DatePicker dpDateBirth, String enp) {
        HashMap<Long, List<People>> hashMap = new HashMap<>();
        List<People> peopleListNew = new ArrayList<>();
        List<People> peopleList = peopleService.getAllPeopleByNotEmptyField(surname, name, patronymic, dpDateBirth, enp);
        List<RecommendationsPatient> patients = recommendationsPatientService.getAllByPeopleList(peopleList);
        patients.forEach(patient -> {
            if (!hashMap.containsKey(patient.getPeople().getId())){
                peopleListNew.add(patient.getPeople());
                hashMap.put(patient.getPeople().getId(), peopleListNew);
            }
        });
        return peopleListNew;
    }

    private List<People> getAllByCodePAPatient(String surname, String name, String patronymic, DatePicker dpDateBirth, String enp, int par1, int par2){
        List<People> peopleListNew = new ArrayList<>();
        List<People> peopleList = peopleService.getAllPeopleByNotEmptyField(surname, name, patronymic, dpDateBirth, enp);
        peopleList.forEach(people -> {
            People peopleNew = paDataPatientService.getByPeopleListAndFilterParam(people, par1, par2);
            if (peopleNew != null) peopleListNew.add(peopleNew);
        });
        return peopleListNew;
    }

    private List<People> getAllByCodeHospitalization(String surname, String name, String patronymic,
                                                     DatePicker dpDateBirth, String enp){
        List<People> peopleListNew = new ArrayList<>();
        List<People> peopleList = peopleService.getAllPeopleByNotEmptyField(surname, name, patronymic, dpDateBirth, enp);
        peopleList.forEach(people -> {
            People peopleNew = hospitalizationService.getByPeopleListAndFilterParam(people);
            if (peopleNew != null) peopleListNew.add(peopleNew);
        });
        return peopleListNew;
    }
}
