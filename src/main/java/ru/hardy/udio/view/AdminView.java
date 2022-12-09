package ru.hardy.udio.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import ru.hardy.udio.domain.Patient;
import ru.hardy.udio.service.ExcelService;
import ru.hardy.udio.service.UIUtil;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Route(layout = MainView.class)
@RolesAllowed("ROLE_ADMIN")
public class AdminView extends VerticalLayout {
    @Value( "${jdbc.url}" )
    private String jdbcUrl;

    private final HorizontalLayout horizontalLayout = new HorizontalLayout();;
    private final Tab tabExcelTest = new Tab("Тест экселя");
    private final Tab tabGridTest = new Tab("Тест грид");
    private final Tab tabTest = new Tab("Shipping");
    private final Button button;
    private final UIUtil uiUtil = new UIUtil();
    private final List<Patient> patientList = new ArrayList<>();

    private final Grid<Patient> grid = new Grid<>(Patient.class, false);

    @Autowired
    private Environment environment;
    public AdminView() {

        button = uiUtil.InitButtonOK(new Button("Test"));
        button.addClickListener(e -> {


            //Notification.show(environment.getProperty("testparam"));
                //environment.
//            try {
//                ExcelService excelService = new ExcelService();
//                horizontalLayout.add(uiUtil.initAnchorDownload(excelService.testRead()));
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
        });
        horizontalLayout.setSizeFull();
        grid.addColumn(Patient::getFam);
        grid.addColumn(Patient::getIm);
        grid.addColumn(Patient::getOt);
        grid.addColumn(Patient::getPolis);

        patientList.add(new Patient("Иванов", "Иван", "Петрович", "123123123"));
        patientList.add(new Patient("Петров", "Сергей", "Олегович", "893567356"));
        patientList.add(new Patient("Кабисашвилинидиянц", "Андрей", "Дмитриевич", "544353453"));

        Tabs tabs = new Tabs(tabExcelTest, tabGridTest, tabTest);
        tabs.addSelectedChangeListener(
                event -> setContent(event.getSelectedTab()));

        setContent(tabs.getSelectedTab());
        add(tabs, horizontalLayout);
    }

    private void setContent(Tab tab) {
        horizontalLayout.removeAll();

        if (tab.equals(tabExcelTest)) {
            horizontalLayout.add(button);
        } else if (tab.equals(tabGridTest)) {
            horizontalLayout.add(grid);
            grid.setItems(patientList);
        } else if (tab.equals(tabTest)){
            horizontalLayout.add(new Paragraph("This is the Shipping tab"));
        }
    }
}
