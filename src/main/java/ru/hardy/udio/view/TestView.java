package ru.hardy.udio.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.domain.struct.DataFile;
import ru.hardy.udio.service.*;
import ru.hardy.udio.service.SRZ.DBFSearchService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Route(layout = MainView.class)
@RolesAllowed({"ROLE_ADMIN"})
public class TestView extends VerticalLayout {

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private SexService sexService;

    @Autowired
    private DataFilePatientService dataFilePatientService;

    @Autowired
    private DNGetService dnGetService;

    @Autowired
    private DataFileService dataFileService;

    public TestView() throws SQLException {

        Avatar avatar = new Avatar();

        Anchor anchor = new Anchor(new StreamResource("DNTh.xlsx",
                () -> {
                    try {
                        return new FileInputStream(("samples\\DNTh.xlsx"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }),
                "A document");

        anchor.getElement().setAttribute("router-ignore", true);

        Button button = new Button("Test");
        Span span = new Span(String.valueOf(22));
        Tooltip tooltip = Tooltip.forComponent(span);
        tooltip.setText(span.getText() + " выполненных новых задач");
        Span caption = new Span("Выполненные");
        //caption.getStyle().set("background", "#F5F5F5");

        Span span1 = createBadge(3);

        TabSheet tabSheet = new TabSheet();
        Tab tab = new Tab(caption, span);
       // tab.getStyle().set("background", "#7CFC00");
        Tab tab1 = new Tab(new Span("Выполняются"), span1);
        HorizontalLayout horizontalLayout = new HorizontalLayout(avatar, span);
        HorizontalLayout horizontalLayout1 = new HorizontalLayout();
        //horizontalLayout.add(new TextField("asdf"));
//        tabSheet.add(tab, horizontalLayout);
//        tabSheet.add(tab1, horizontalLayout1);

        span.getStyle().set("border-radius", "10px");
        span.getStyle().set("background", "#ffcccc");
        span.getStyle().set("padding", "2px");
        span.getStyle().set("font-size", "x-small");
        span.getStyle().set("margin-left", "-15px");
        span.getStyle().set("margin-bottom", "20px");

        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);

        TextField textField = new TextField("Код МО");
        upload.addSucceededListener(event -> {
            ExcelService excelService = new ExcelService(sexService, dataFilePatientService);
            System.out.println(event.getFileName().substring(0, 6));
            try {
                peopleService.processingFromExcel(excelService.loadFromExcel(
                        new DataFile(event.getFileName(), Date.from(Instant.now()), Integer.parseInt(event.getFileName().substring(0, 6)), 123123L),
                        buffer.getInputStream(event.getFileName())));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
        //add(avatar);
        add(horizontalLayout);
        button.addClickListener(ev -> {

        });

        add(button, anchor, textField, upload);
    }

    private void updateMODNGet(List<DNGet> allDnGets) throws SQLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getSRZ();

        for (DNGet dnGet : allDnGets){
            ResultSet resultSet = statement.executeQuery("select p.lpu from PEOPLE p join HISTFDR h on h.pid = p.id " +
                    "where (concat(p.FAM, ' ', p.IM, ' ', p.OT) = '" + dnGet.getPeople().getFam() + " " +
                    dnGet.getPeople().getIm() + " " + dnGet.getPeople().getOt() + "' " +
                    " or concat(h.FAM, ' ', h.IM, ' ', h.OT) = '" + dnGet.getPeople().getFam() + " " +
                    dnGet.getPeople().getIm() + " " + dnGet.getPeople().getOt() + "') " +
                    "and p.DR  = PARSE('" + dateFormat.format(dnGet.getPeople().getDr()) + "' as date)");
            if(resultSet.next()) {
                if (resultSet.getString(1) != null && !resultSet.getString(1).isEmpty()) {
                    dnGet.setMo(resultSet.getInt(1));
                    dnGetService.save(dnGet);
                }
            }
        }
    }

    private Span createBadge(int value) {
        Span badge = new Span(String.valueOf(value));
        badge.getElement().getThemeList().add("badge small contrast");
        badge.getStyle().set("margin-inline-start", "var(--lumo-space-xs)");
        return badge;
    }

    @Override
    public void onAttach(AttachEvent attachEvent){
        Button btnEditMO = new Button("Обновить МО");
        btnEditMO.addClickListener(e -> {
            try {
                ExcelService excelService = new ExcelService(sexService, dataFilePatientService);
                peopleService.processingFromExcel(excelService.loadFromExcel1());
                updateMODNGet(dnGetService.getAll());
            } catch (SQLException err) {
                throw new RuntimeException(err);
            }
        });
        add(btnEditMO);

        Button btnSearchInSRZ = new Button("Поиск через дбф");
        btnSearchInSRZ.addClickListener(e -> {
            DBFSearchService dbfSearchService = new DBFSearchService();
            DataFile dataFile = new DataFile();
            dataFile.setDataFilePatient(dataFilePatientService.getNoSearchFromSRZ());
            DataFile dataFile1 = dbfSearchService.getDataFromDBF(dataFile);
            System.out.println(dataFile1.getDataFilePatient());
        });

        add(btnSearchInSRZ);

    }
}


