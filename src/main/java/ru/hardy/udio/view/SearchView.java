package ru.hardy.udio.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import ru.hardy.udio.domain.button.BtnVariant;
import ru.hardy.udio.domain.button.UdioButton;


@Route(layout = MainView.class)
@RolesAllowed("ROLE_TFOMS")
public class SearchView extends VerticalLayout {

    public SearchView(){
        TabSheet tabSheet = new TabSheet();

        FormLayout flPeople = new FormLayout();
        TextField tfSurname = new TextField();
        tfSurname.setPlaceholder("Введите фамилия");

        TextField tfName = new TextField();
        tfName.setPlaceholder("Введите имя");

        TextField tfPatronymic = new TextField();
        tfPatronymic.setPlaceholder("Введите отчество");

        DatePicker dpDateBirth = new DatePicker();
        dpDateBirth.setPlaceholder("Введите дату рождения");

        TextField tfEnp = new TextField();
        tfEnp.setPlaceholder("Введите ЕНП");

        TextField tfMainDiag = new TextField();
        tfMainDiag.setPlaceholder("Диагноз МКБ10");

        UdioButton ubSearch = new UdioButton("Поиск", BtnVariant.SEARCH);

        flPeople.addFormItem(tfSurname, "Фамилия");
        flPeople.addFormItem(tfName, "Имя");
        flPeople.addFormItem(tfPatronymic, "Отчество");
        flPeople.addFormItem(dpDateBirth, "Дата Рождения");
        flPeople.addFormItem(tfEnp, "ЕНП");
        flPeople.addFormItem(tfMainDiag, "Основной диагноз");
        flPeople.addFormItem(ubSearch, "");

        tabSheet.add("Люди", flPeople);
        add(tabSheet);

        ubSearch.addClickListener(e -> {

        });

    }
}
