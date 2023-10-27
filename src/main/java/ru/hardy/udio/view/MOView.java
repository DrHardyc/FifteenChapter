package ru.hardy.udio.view;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import ru.hardy.udio.domain.api.PeopleInfo;
import ru.hardy.udio.domain.button.BtnVariant;
import ru.hardy.udio.domain.button.UdioButton;
import ru.hardy.udio.view.MainView;

@Route(layout = MainView.class)
@RolesAllowed("ROLE_TFOMS")
public class MOView extends VerticalLayout{

    public MOView(){
        FormLayout flMO = new FormLayout();

        TextField tfCodeMO = new TextField();
        tfCodeMO.setPlaceholder("Введите код МО");

        TextField tfCodeDep = new TextField();
        tfCodeDep.setPlaceholder("Введите код отделения");

        UdioButton udioButton = new UdioButton("Поиск", BtnVariant.SEARCH);
        flMO.addFormItem(tfCodeMO, "Код МО");
        flMO.addFormItem(tfCodeDep, "Код отделения");

        Grid<PeopleInfo> grid = new Grid<>();
        grid.addColumn(PeopleInfo::getSurname).setHeader("Код МО");
        grid.addColumn(PeopleInfo::getName).setHeader("Наименование МО");
        grid.addColumn(PeopleInfo::getInforming).setHeader("План-графики профмероприятий");
        grid.addColumn(PeopleInfo::getInsuranceCase).setHeader("Объемы");
        grid.addColumn(PeopleInfo::getInsuranceCase).setHeader("Койко-места");
        grid.addColumn(PeopleInfo::getInsuranceCase).setHeader("Госпитализация");
        grid.addColumn(PeopleInfo::getVisitsCalls).setHeader("ЗЛ выбравшие данную МО!");
        grid.setItems(new PeopleInfo());

        add(flMO, udioButton, grid);
    }
}
