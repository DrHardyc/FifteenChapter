package ru.hardy.udio.view;

import com.linuxense.javadbf.DBFReader;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import ru.hardy.udio.domain.Role;
import ru.hardy.udio.domain.struct.DataFilePatient;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.SRZ.DBFSearchService;
import ru.hardy.udio.service.UIUtil;
import ru.hardy.udio.service.UserService;

import javax.annotation.security.RolesAllowed;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Route(layout = MainView.class)
@RolesAllowed("ROLE_ADMIN")
public class AdminView extends VerticalLayout {

    @Autowired
    private DBFSearchService dbfSearchService;
    @Autowired
    private UserService userService;

    @Autowired
    private PeopleService peopleService;

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
        tabSheet.add("Пользователи", vlUsers);

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
            System.out.println(reader.getCharactersetName());
        });

        btnTest.addClickListener(e -> {

            String generatedString = RandomStringUtils.random(10, true, true);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            List<DataFilePatient> dataFilePatientList = new ArrayList<>();
            try {
                dataFilePatientList.add(new DataFilePatient("ПЛИЕВА", "МАРИНА", "АЛАНОВНА",
                        simpleDateFormat.parse("2008-09-21"), "1590199778000327"));
                dataFilePatientList.add(new DataFilePatient("АЙЛАРОВ", "ТАМЕРЛАН", "ИРБЕКОВИЧ",
                        simpleDateFormat.parse("1987-08-08"), "1551210841000249"));
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            dbfSearchService.setDataDBF(dataFilePatientList, generatedString);

            //while ()

        });

        grid.addColumn(People::getFam);
        grid.addColumn(People::getIm);
        grid.addColumn(People::getOt);
        grid.addColumn(People::getDr);


        VerticalLayout vlTestChart = new VerticalLayout();
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
//                .withSeries(new Series<>(400.0, 430.0, 448.0, 470.0, 540.0, 580.0, 690.0, 1100.0, 1200.0, 1380.0))
//                .withXaxis(XAxisBuilder.get()
//                        .withCategories()
//                        .build())
//                .build();
//        chart.setHeight("400px");
//        Button update = new Button("Update", buttonClickEvent -> {
//            chart.updateSeries(new Series<>(400.0, 430.0, 448.0, 470.0, 540.0, 580.0, 690.0, 1100.0, 1200.0, 500.0));
//            Notification.show("The chart was updated!");
//        });
//        vlTestChart.add(chart, update);

        tabSheet.add("Графики", vlTestChart);


//        List<DataFile> dataFileList = new ArrayList<>();
//        dataFileList.add(new DataFile("Иванов", "Иван", "Петрович", "123123123"));
//        dataFileList.add(new DataFile("Петров", "Сергей", "Олегович", "893567356"));
//        dataFileList.add(new DataFile("Кабисашвилинидиянц", "Андрей", "Дмитриевич", "544353453"));
//
//        grid.setItems(dataFileList);

        add(tabSheet);
    }

}
