package ru.hardy.udio.view;

import com.github.appreciated.apexcharts.ApexCharts;
import com.linuxense.javadbf.DBFReader;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hardy.udio.domain.Role;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.*;
import ru.hardy.udio.view.dialog.DialogView;

import java.util.Collections;

@Route(layout = MainView.class)
@RolesAllowed("ROLE_ADMIN")
public class AdminView extends VerticalLayout{

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;


    @Autowired
    private PeopleService peopleService;

    @Autowired
    private DataFilePatientService dataFilePatientService;


    public AdminView(){
        TabSheet tabSheet = new TabSheet();
        Button btnTest = new UIUtil().InitButtonOK(new Button("Test"));

        VerticalLayout vlTestExcel = new VerticalLayout();
        vlTestExcel.add(btnTest);
        tabSheet.add("Тест excel", vlTestExcel);

        VerticalLayout vlTestGrid = new VerticalLayout();
        Grid<People> grid = new Grid<>(People.class, false);
        vlTestGrid.add(grid);

        //Пользователи
        VerticalLayout vlUsers = new VerticalLayout();
        Button btnAddNewUser = new Button("Создать");
        TextField tfUserName = new TextField("Имя пользователя");
        PasswordField pfPassword = new PasswordField("Пароль");
        vlUsers.add(tfUserName, pfPassword, btnAddNewUser);

        btnAddNewUser.addClickListener(e -> {
            userService.addUser(tfUserName.getValue(), pfPassword.getValue(), Collections.singleton(Role.ROLE_USER));
        });

        //генерация ключей
        Dialog dGenKey = new Dialog();
        Button btnGetKey = new Button("Создать");
        Label lGenKey = new Label();
        TextField tfLpuKeyGen = new TextField();
        tfLpuKeyGen.setPlaceholder("Введите код ЛПУ");

        TabSheet tsKeyGen = new TabSheet();
        VerticalLayout vlKeyGen = new VerticalLayout();
        Button btnKeyGen = new Button("Сгенерировать ключ для ЛПУ");
        btnKeyGen.addClickListener(e -> {
            DialogView dialogView = new DialogView();
            Dialog dialog = dialogView.getKeyGenDialog(tokenService);
            dialog.open();
        });

        TextField tfHashKey = new TextField();
        tfHashKey.setPlaceholder("Введите hash");

        dGenKey.add(tfLpuKeyGen, lGenKey, btnGetKey);

        vlKeyGen.add(btnKeyGen, dGenKey);
        tsKeyGen.add("Генерация ключа", vlKeyGen);
        //

        tabSheet.add("Пользователи", vlUsers);
        vlUsers.add(tsKeyGen);
        //------------

        vlTestGrid.add(grid);
        Button btnTestGrid = new Button("Test grid");
        btnTestGrid.addClickListener(e -> {
            grid.setItems(peopleService.getAll());
        });

        vlTestGrid.add(btnTestGrid);
        tabSheet.add("Тест Grid", vlTestGrid);

        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);

        vlTestExcel.add(upload);
        upload.addSucceededListener(e -> {
            DBFReader reader = new DBFReader(buffer.getInputStream());
        });
        btnTest.addClickListener(e -> {

        });

        grid.addColumn(People::getFam);
        grid.addColumn(People::getIm);
        grid.addColumn(People::getOt);
        grid.addColumn(People::getDr);

        VerticalLayout vlTestChart = new VerticalLayout();

        tabSheet.add("Графики", vlTestChart);

        add(tabSheet);
    }

    @Override
    public void onAttach(AttachEvent attachEvent) {
        this.add(createApexChart());
    }

    private ApexCharts createApexChart(){
        AChartService aChartService = new AChartService();
        return aChartService.getAC(aChartService.getFromFilePatient(dataFilePatientService.getAll(), "age"));

    }
}
