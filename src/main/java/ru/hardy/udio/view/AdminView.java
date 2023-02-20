package ru.hardy.udio.view;

import com.linuxense.javadbf.DBFReader;
import com.vaadin.flow.component.UI;
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
import org.springframework.beans.factory.annotation.Autowired;
import ru.hardy.udio.domain.Role;
import ru.hardy.udio.domain.struct.DataUdioResp;
import ru.hardy.udio.domain.struct.DataUdioRespIdenty;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.*;
import ru.hardy.udio.service.SRZ.DBFSearchService;
import ru.hardy.udio.view.dialog.DialogView;

import javax.annotation.security.RolesAllowed;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Route(layout = MainView.class)
@RolesAllowed("ROLE_ADMIN")
public class AdminView extends VerticalLayout {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private DBFSearchService dbfSearchService;
    @Autowired
    private UserService userService;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private DataUdioRespIdentyService dataUdioRespIdentyService;

    public AdminView()  {


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
        Button btnGetHashForKey = new Button("Полчить ключ по hashу");
        btnGetHashForKey.addClickListener(e -> {
            //Notification.show(tokenService.getHashWithLpu(tfLpuKeyGen.getValue()));
        });

        dGenKey.add(tfLpuKeyGen, lGenKey, btnGetKey);

        vlKeyGen.add(btnGetHashForKey, btnKeyGen, dGenKey);
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
        upload.addSucceededListener(event -> {
            DBFReader reader = new DBFReader(buffer.getInputStream());
        });


        btnTest.addClickListener(event -> {

//            Token token = tokenService.genToken("150002");
//            Notification.show(token.getKey());
//            ExecutorService executor = Executors.newFixedThreadPool(10);
//            executor.execute(new Thread(() -> {
//                try {
//                    testAsync(this.getUI().get());
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }));


//
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//            List<DataFilePatient> dataFilePatientList = new ArrayList<>();
//            try {
//                dataFilePatientList.add(new DataFilePatient("ПЛИЕВА", "МАРИНА", "АЛАНОВНА",
//                        simpleDateFormat.parse("2008-09-21"), "1590199778000327"));
//                dataFilePatientList.add(new DataFilePatient("АЙЛАРОВ", "ТАМЕРЛАН", "ИРБЕКОВИЧ",
//                        simpleDateFormat.parse("1987-08-08"), "1551210841000249"));
//                dataFilePatientList.add(new DataFilePatient("АЙЛasdfАРОВ", "ТАМЕРЛadsfАН", "ИРБЕКОasdfВИЧ",
//                        simpleDateFormat.parse("1987-08-08"), "1551212841000249"));
//            } catch (ParseException ex) {
//                throw new RuntimeException(ex);
//            }
//            dbfSearchService.setDataDBF(dataFilePatientList, generatedString);
//
//            long delay = 1L;
//            while (!dbfSearchService.checkDBFFile(generatedString)){
//                try {
//                    Thread.sleep(delay);
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//
//            try {
//                Thread.sleep(2000);
//                Future<List<DataFilePatient>> dataFilePatients = dbfSearchService.getDateDBF(generatedString, dataFilePatientList);
//                System.out.println(dataFilePatients.toString());
//            } catch (IOException | InterruptedException ex) {
//                throw new RuntimeException(ex);
//            }

            //while ()

        });

        grid.addColumn(People::getFam);
        grid.addColumn(People::getIm);
        grid.addColumn(People::getOt);
        grid.addColumn(People::getDr);


//        @Data
//        class TestChart {
//            private String name;
//            private Integer value;
//
//            public TestChart(String name, Integer value){
//                this.name = name;
//                this.value = value;
//            }
//        }
//
//        List<TestChart> testCharts = new ArrayList<>();
//        testCharts.add(new TestChart("150001", 15));
//        testCharts.add(new TestChart("150002", 30));
//
//        VerticalLayout vlTestChart = new VerticalLayout();
//        ApexCharts chart = ApexChartsBuilder.get().withChart(ChartBuilder.get()
//                        .withType(Type.BAR)
//                        .build())
//                .withPlotOptions(PlotOptionsBuilder.get()
//                        .withBar(BarBuilder.get()
//                                .withHorizontal(true)
//                                .build())
//                        .build())
//                .withDataLabels(DataLabelsBuilder.get()
//                        .withEnabled(false)
//                        .build())
//                .withSeries(new Series<>("%", testCharts.stream().map(TestChart::getValue).toArray()))
//                .withXaxis(XAxisBuilder.get()
//                        .withCategories(testCharts.stream().map(TestChart::getName).collect(Collectors.toList()))
//                        .build())
//                .build();
//        chart.setHeight("100%");
//        Button update = new Button("Update", buttonClickEvent -> {
//            chart.updateSeries(new Series<>(400.0, 430.0, 448.0, 470.0, 540.0, 580.0, 690.0, 1100.0, 1200.0, 500.0));
//            Notification.show("The chart was updated!").setPosition(Notification.Position.TOP_END);
//        });
//        vlTestChart.add(chart, update);
//
//        tabSheet.add("Графики", vlTestChart);

        add(tabSheet);
    }

    public void testAsync(UI ui) throws InterruptedException {
        for (int i = 0; i < 5; i++){
            Thread.sleep(1000);
            int finalI = i;
            ui.access(() -> Notification.show(String.valueOf(finalI)));
        }
        System.out.println("Поток завершил работу");
        ui.access(() -> Notification.show("Поток завершил работу"));
    }

}
