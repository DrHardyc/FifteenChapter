package ru.hardy.udio.view;

import com.linuxense.javadbf.DBFReader;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hardy.udio.domain.deamon.Deamon;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.security.SecurityUtils;
import ru.hardy.udio.service.DataFileService;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.UIUtilService;
import ru.hardy.udio.service.deamonservice.DeamonService;
import ru.hardy.udio.service.deamonservice.SearchDead;

@Route(layout = MainView.class)
@RolesAllowed("ROLE_ADMIN")
public class AdminView extends VerticalLayout{
    @Autowired
    private TokenService tokenService;
//    @Autowired
//    private UserService userService;
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private DataFileService dataFileService;
    @Autowired
    private DeamonService deamonService;
    @Autowired
    private SearchDead searchDead;

    private final Grid<Deamon> gridDeamon = new Grid<>();

    private final Button btnCryptToken = new Button("Получить токен");
    public AdminView(){
        TabSheet tabSheet = new TabSheet();
        Button btnTest = new UIUtilService().InitButtonOK(new Button("Test"));

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
            //userService.addUser(tfUserName.getValue(), pfPassword.getValue(), Collections.singleton(Role.ROLE_USER));
        });

        //генерация ключей
        Dialog dGenKey = new Dialog();
        Button btnGetKey = new Button("Создать");
        Label lGenKey = new Label();
        TextField tfLpuKeyGen = new TextField();
        tfLpuKeyGen.setPlaceholder("Введите код ЛПУ");

        TextField tfHashKey = new TextField();
        tfHashKey.setPlaceholder("Введите hash");

        dGenKey.add(tfLpuKeyGen, lGenKey, btnGetKey);

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

        grid.addColumn(People::getSurname);
        grid.addColumn(People::getName);
        grid.addColumn(People::getPatronymic);
        grid.addColumn(People::getDateBirth);

        VerticalLayout vlTestChart = new VerticalLayout();

        tabSheet.add("Графики", vlTestChart);

        VerticalLayout vlDeamon = new VerticalLayout();
        gridDeamon.addColumn(Deamon::getName);
        gridDeamon.addColumn(Deamon::getDeamonStatus);
        gridDeamon.addColumn(Deamon::getDateStart);
        gridDeamon.addComponentColumn(deamon -> {
            Button btnStart = new Button();
            btnStart.addClickListener(e -> {
                switch (deamon.getName()){
                    case "SearchDead" : {
                        searchDead.setDaemon(true);
                        searchDead.start();
                    }
                    case "2L" : {

                    }
                }
            });
            return btnStart;
        });

        vlDeamon.add(gridDeamon);
        tabSheet.add("Демоны", vlDeamon);

        add(tabSheet);

    }

    @Override
    public void onAttach(AttachEvent attachEvent) {
        gridDeamon.setItems(deamonService.getAll());
        TextField tfClearToken = new TextField("Чистый токен");
        TextField tfCryptToken = new TextField("Измененный токен");
        TextField tfCodeMO = new TextField("Код МО");


        btnCryptToken.addClickListener(e -> {
            SecurityUtils securityUtils = new SecurityUtils();
            String cryptToken = securityUtils.encodeString(tfClearToken.getValue());
            tfCryptToken.setValue(cryptToken);
            if (!tokenService.checkToken(tfClearToken.getValue())) {
                tokenService.addNewToken(cryptToken, Integer.parseInt(tfCodeMO.getValue()));
            }
        });
        add(tfCodeMO, tfClearToken, tfCryptToken, btnCryptToken);
    }


//    private ApexCharts createApexChart(){
//        AChartService aChartService = new AChartService();
//        return aChartService.getAC(aChartService.getFromFilePatient(dataFilePatientService.getAll(), "age"));
//
//    }
}
