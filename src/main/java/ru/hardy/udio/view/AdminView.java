package ru.hardy.udio.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.Role;
import ru.hardy.udio.domain.deamon.Deamon;
import ru.hardy.udio.domain.regul.*;
import ru.hardy.udio.domain.regul.importfromfms.Platel;
import ru.hardy.udio.domain.regul.importfromfms.PlatelMapper;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.security.SecurityUtils;
import ru.hardy.udio.service.*;
import ru.hardy.udio.service.deamonservice.DeamonService;
import ru.hardy.udio.service.deamonservice.SearchDead;
import ru.hardy.udio.service.regulservice.FileUlService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
    private DeamonService deamonService;
    @Autowired
    private SearchDead searchDead;
    @Autowired
    private FileUlService fileUlService;

    private final Grid<Deamon> gridDeamon = new Grid<>();

    private final Button btnCryptToken = new Button("Получить токен");

    private final TextField tfClearToken = new TextField("Чистый токен");
    private final TextField tfCryptToken = new TextField("Измененный токен");
    private final TextField tfCodeMO = new TextField("Код МО");
    public AdminView(){

        TabSheet tabSheet = new TabSheet();

        Button btnTest = new UIUtilService().InitButtonOK(new Button("Test"));
        VerticalLayout vlTesting = new VerticalLayout();

        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);

        vlTesting.add(upload);
        upload.addSucceededListener(e -> {
            System.out.println(buffer.getFileName());
        });
        btnTest.addClickListener(e -> {

        });
        btnTest.addClickListener(buttonClickEvent -> {
            String file1 = "src/main/resources/zipTest/test1.txt";
            String file2 = "src/main/resources/zipTest/test2.txt";
            final List<String> srcFiles = Arrays.asList(file1, file2);
            final FileOutputStream fos;
            try {
                fos = new FileOutputStream(Paths.get(file1).getParent().toAbsolutePath() + "/sucess_" + new SimpleDateFormat("dd_MM_yy__HH_mm_ss").format(Date.from(Instant.now())) + ".zip");
                ZipOutputStream zipOut = new ZipOutputStream(fos);
                for (String srcFile : srcFiles) {
                    File fileToZip = new File(srcFile);
                    FileInputStream fis = new FileInputStream(fileToZip);
                    ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                    zipOut.putNextEntry(zipEntry);

                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fis.read(bytes)) >= 0) {
                        zipOut.write(bytes, 0, length);
                    }
                    fis.close();
                    if (!new File(srcFile).delete()){
                        System.out.println(srcFile + " no delete");
                    };
                }
                zipOut.close();
                fos.close();
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        });
        vlTesting.add(btnTest);

        tabSheet.add("Тестирование", vlTesting);

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
            userService.addUser(tfUserName.getValue(), pfPassword.getValue(), Collections.singleton(Role.ROLE_TFOMS));
        });
        tabSheet.add("Пользователь", vlUsers);

        //генерация ключей
        Dialog dGenKey = new Dialog();
        Button btnGetKey = new Button("Создать");
        Span span = new Span("asdfa");
        TextField tfLpuKeyGen = new TextField();
        tfLpuKeyGen.setPlaceholder("Введите код ЛПУ");

        TextField tfHashKey = new TextField();
        tfHashKey.setPlaceholder("Введите hash");

        dGenKey.add(tfLpuKeyGen, span, btnGetKey);

        vlTestGrid.add(grid);
        Button btnTestGrid = new Button("Test grid");
        btnTestGrid.addClickListener(e -> {
            grid.setItems(peopleService.getAll());
        });

        vlTestGrid.add(btnTestGrid);
        tabSheet.add("Тест Grid", vlTestGrid);

        grid.addColumn(People::getSurname);
        grid.addColumn(People::getName);
        grid.addColumn(People::getPatronymic);
        grid.addColumn(People::getDateBirth);

        VerticalLayout vlTestChart = new VerticalLayout();
        Button btnApexChart = new Button("Создать");
        vlTestChart.add(btnApexChart);
        btnApexChart.addClickListener(buttonClickEvent -> {
            new Dialog(new AChartService().getTest()).open();
        });


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

        VerticalLayout vlToken = new VerticalLayout();
        vlToken.add(tfClearToken, tfCryptToken, tfCodeMO, btnCryptToken);

        tabSheet.add("Токен", vlToken);

        VerticalLayout vlRegUL = new VerticalLayout();
        Button btnImport = new Button("Импорт из FMS");
        vlRegUL.add(btnImport);
        tabSheet.add("Регистрация ЮЛ", vlRegUL);
        btnImport.addClickListener(buttonClickEvent -> {
            DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();

            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(dbjdbcConfig.getFMSDataSource());
            RowMapper<Platel> platelRowMapper = new PlatelMapper();
            List<Platel> platelList = jdbcTemplate.query("select p.NAMEF, p.NAMESC, p.INN, p.OGRN, p.KPP, " +
                    "p.REGORG, p.STATUS, p.VIDREG, p.DTSTART, p.DTEND, p.REGNPF, p.PF, p.REGNFSS, p.FSS, p.REGNFOMS, " +
                    "p.FOMS, p.FILEN, p.DTPRIS, p.PRIN, p.KUSER, p.CHIS, p.VPDT, p.VPKTO, " +
                    "b.NUM, b.BIK, b.TIP, " +
                    "h.REGNUM, h.DTREG, h.DTZAP, " +
                    "a.OKATO, a.INDEKS, a.REGION, a.RAION, a.GOROD, a.NASPUNKT, a.STREET, a.DOM, a.KORP, a.KVART, a.KODGOROD, a.TEL " +
                    "from PLATEL p " +
                    "inner join BANKREK b on b.regid = p.id " +
                    "inner join HISTORY h on h.ORGID = p.ID " +
                    "inner join ADDRESS a on a.ID = p.ADDRSS", platelRowMapper);

            FileUL fileUL = new FileUL();
            fileUL.setIdFile("from srz/fms ul");
            Set<DocumentUL> documentULSet = new HashSet<>();
            for (Platel platel : platelList) {
                DocumentUL documentUL = new DocumentUL();
                documentUL.setIdDoc(platel.getFilen());
                if (!platel.getVidreg().toLowerCase().contains("глава кресть") && !platel.getVidreg().toLowerCase().contains("предприн")) {
                    PersonUL personUL = new PersonUL();
                    personUL.setNameUl(new NameUL(platel.getNamef(), new ShortNameUlType(platel.getNamesc())));
                    personUL.setInn(platel.getInn());
                    personUL.setOgrn(platel.getOgrn());
                    personUL.setKpp(platel.getKpp());
                    personUL.setRegOrg(new RegOrg(platel.getRegorg()));

                    Set<StatusUl> statusULSet = new HashSet<>();
                    statusULSet.add(new StatusUl(new Status(platel.getStatus())));
                    personUL.setStatusUl(statusULSet);
                    personUL.setObrUL(new ObrUL(new SpObrUl(platel.getVidreg()), platel.getDtreg()));
                    Set<ZapEGRUL> zapEGRULSet = new HashSet<>();
                    zapEGRULSet.add(new ZapEGRUL(platel.getDtzap()));
                    personUL.setZapEGRUL(zapEGRULSet);
                    personUL.setRegPF(new RegPF(platel.getRegnpf(), new OrgPF(platel.getPf())));
                    personUL.setRegFSS(new RegFSS(platel.getRegnfss(), new OrgFSS(platel.getFss())));
                    personUL.setRegNFoms(platel.getRegnfoms());
                    personUL.setAddressUL(new AddressUL(new AdrRFEGRULType(platel.getIndeks(),
                            new RegionType(platel.getRegion()),
                            new RaionType(platel.getRaion()),
                            new GorodType(platel.getGorod()),
                            new NaselPunktType(platel.getNaspunkt()),
                            new UlicaType(platel.getStreet()),
                            platel.getDom(),
                            platel.getKorp(),
                            platel.getKvart())));

                    documentUL.setPersonUL(personUL);
                    documentULSet.add(documentUL);
                } else {
                    PersonIP personIP = new PersonIP();
                    FL fl = new FL();
                    List<String> fio = List.of(platel.getNamef().split(" "));
                    fl.setFioRus(new FIOIP(fio.get(0), fio.get(1), fio.get(2)));
                    personIP.setFl(fl);
                    personIP.setInnFl(platel.getInn());
                    personIP.setOgrnIp(platel.getOgrn());
                    personIP.setRegOrg(new RegOrg(platel.getRegorg()));
                    personIP.setStatus(new StatusIP(new Status(platel.getStatus())));
                    personIP.setNameVidIp(platel.getVidreg());
                    personIP.setRegPF(new RegPFIP(new OrgPFIP(platel.getRegnpf(), platel.getPf())));
                    personIP.setRegFSSIP(new RegFSSIP(new OrgFSSIP(platel.getRegnfss(), platel.getFss())));
                    personIP.setRegNFoms(platel.getRegnfoms());
                    personIP.setAdrMJ(new AdrMJ(new AdresRF(platel.getIndeks(),
                            new RegionType(platel.getRegion()),
                            new RaionType(platel.getRaion()),
                            new GorodType(platel.getGorod()),
                            new NaselPunktType(platel.getNaspunkt()),
                            new UlicaType(platel.getStreet()),
                            platel.getDom(),
                            platel.getKorp(),
                            platel.getKvart())));

                    documentUL.setPersonIP(personIP);
                    documentULSet.add(documentUL);
                }
            }
            fileUL.setDocumentUL(documentULSet);
            fileUlService.addFromFMS(fileUL);
        });



        add(tabSheet);
    }

    @Override
    public void onAttach(AttachEvent attachEvent) {
        gridDeamon.setItems(deamonService.getAll());
        btnCryptToken.addClickListener(e -> {
            SecurityUtils securityUtils = new SecurityUtils();
            String cryptToken = securityUtils.encodeString(tfClearToken.getValue());
            tfCryptToken.setValue(cryptToken);
            if (!tokenService.checkToken(tfClearToken.getValue())) {
                tokenService.addNewToken(cryptToken, Integer.parseInt(tfCodeMO.getValue()));
            }
        });
    }
}
