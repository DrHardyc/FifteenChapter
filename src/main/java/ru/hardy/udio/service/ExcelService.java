package ru.hardy.udio.service;

import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.provider.hierarchy.TreeDataProvider;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.api.individualhistoryonkocase.InsuranceCase;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequestRecord;
import ru.hardy.udio.domain.api.schedulepianddispplot.DTO.SchedulePIAndDispPlotDTO;
import ru.hardy.udio.domain.api.volumemedicalcare.dto.VolumeMedicalCareDTO;
import ru.hardy.udio.domain.report.AgeLimit;
import ru.hardy.udio.domain.report.Efficiency;
import ru.hardy.udio.domain.report.VisitType;
import ru.hardy.udio.domain.report.WorkingAgeSex;
import ru.hardy.udio.domain.struct.*;
import ru.hardy.udio.domain.struct.dto.DNOutDto;
import ru.hardy.udio.service.reportservice.DNOnkoTherapiReportService;
import ru.hardy.udio.service.reportservice.EfficiencyService;
import ru.hardy.udio.service.reportservice.KARDIOReportService;
import ru.hardy.udio.service.reportservice.ONKOReportService;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class ExcelService {
    @Autowired
    private SexService sexService;
    @Autowired
    DNOnkoTherapiReportService dnOnkoTherapiReportService;
    @Autowired
    private KARDIOReportService kardioReportService;
    @Autowired
    ONKOReportService onkoReportService;

    private final UtilService utilService = new UtilService();
    private InputStream excelFile;

    public ExcelService(){


    }

    public File getDNOutDto(Collection<DNOutDto> dnOutList){
        FileOutputStream outputStream;
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Общий отчет");
        Row header = sheet.createRow(0);

        createHeaderCell(header, "ФИО", 0, workbook);
        createHeaderCell(header, "Возраст", 1, workbook);
        createHeaderCell(header, "Пол", 2, workbook);
        createHeaderCell(header, "Диагноз", 3, workbook);
        createHeaderCell(header, "Дата смерти", 4, workbook);
        createHeaderCell(header, "Дата взятия", 5, workbook);

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        int indexCount = 1;
        for (DNOutDto dnOutDto : dnOutList){
            Row row = sheet.createRow(indexCount);
            createValueCell(row, dnOutDto.getFIO(), 0, workbook);
            createValueCell(row, String.valueOf(dnOutDto.getAge()), 1, workbook);
            createValueCell(row, dnOutDto.getStringSexId(), 2, workbook);
            createValueCell(row, dnOutDto.getDiags(), 3, workbook);
            createValueCell(row, dateFormat.format(dnOutDto.getDs()), 4, workbook);
            createValueCell(row, dnOutDto.getDate_1(), 5, workbook);

            indexCount++;
        }

        File currDir = new File("C:\\udio\\reports\\" + UUID.randomUUID() + "_снятые.xlsx");
        try {
            outputStream = new FileOutputStream(currDir);
            workbook.write(outputStream);
            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return currDir;
    }

    public File getOtherDNGets(Collection<DNGet> dnGetList){
        FileOutputStream outputStream;
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Общий отчет");
        Row header = sheet.createRow(0);

        createHeaderCell(header, "ФИО", 0, workbook);
        createHeaderCell(header, "МО прикрепления", 1, workbook);
        createHeaderCell(header, "МО", 2, workbook);
        createHeaderCell(header, "Пол", 3, workbook);
        createHeaderCell(header, "Диагноз", 4, workbook);
        createHeaderCell(header, "Специальность врача", 5, workbook);
        createHeaderCell(header, "Возраст", 6, workbook);
        createHeaderCell(header, "Инвалидность", 7, workbook);
        createHeaderCell(header, "Дата взятия", 8, workbook);
        createHeaderCell(header, "Дата вызова", 9, workbook);

        int indexCount = 1;
        for (DNGet dnGet : dnGetList){
            Row row = sheet.createRow(indexCount);
            createValueCell(row, dnGet.getFIO(), 0, workbook);
            createValueCell(row, String.valueOf(dnGet.getMOAttach()), 1, workbook);
            createValueCell(row, String.valueOf(dnGet.getMo()), 2, workbook);
            createValueCell(row, String.valueOf(dnGet.getSex()), 3, workbook);
            createValueCell(row, dnGet.getDiag(), 4, workbook);
            createValueCell(row, String.valueOf(dnGet.getSpecialization()), 5, workbook);
            createValueCell(row, String.valueOf(dnGet.getAge()), 6, workbook);
            createValueCell(row, String.valueOf(dnGet.getPeopleInv()), 7, workbook);
            createValueCell(row, dnGet.getDate1String(), 8, workbook);
            createValueCell(row, dnGet.getDateCallString(), 9, workbook);

            indexCount++;
        }

        File currDir = new File("C:\\udio\\reports\\" + UUID.randomUUID() + "_общий.xlsx");
        try {
            outputStream = new FileOutputStream(currDir);
            workbook.write(outputStream);
            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return currDir;
    }

    public void getDNOnkoTherapiReportSample(String monthBeg, String monthEnd, String yearBeg, String yearEnd, String filename, String spec){
        FileOutputStream outputStream;
        XSSFWorkbook workbook;
        try {
            String dir = "";
            if (spec.equals("76")) dir = "C:\\udio\\samples\\DNTh.xlsx";
            else if (spec.equals("41")) dir = "C:\\udio\\samples\\DNOnko.xlsx";
            excelFile = new FileInputStream(dir);
            workbook = new XSSFWorkbook(excelFile);
            outputStream = new FileOutputStream("C:\\udio\\reports\\" + filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Sheet sheet = workbook.getSheetAt(0);

        int colIndex = -1;
        int rowIndex = -1;
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING) {
                    if (cell.getStringCellValue().contains("qry1.test") && cell.getStringCellValue().contains(":row")) {
                        colIndex = cell.getColumnIndex();
                        rowIndex = cell.getRowIndex();
                    }
                    if (cell.getStringCellValue().contains("[monthBeg]")) {
                        cell.setCellValue(cell.getStringCellValue().replace("[monthBeg]", utilService.getStringMonth(monthBeg)));
                    }
                    if (cell.getStringCellValue().contains("[monthEnd]")) {
                        cell.setCellValue(cell.getStringCellValue().replace("[monthEnd]", utilService.getStringMonth(monthEnd)));
                    }
                    if (cell.getStringCellValue().contains("[yearBeg]")) {
                        cell.setCellValue(cell.getStringCellValue().replace("[yearBeg]", yearBeg));
                    }
                }
            }
            if(colIndex > -1) break;
        }
        List<List<String>> diagsList = new ArrayList<>();


        if (spec.equals("76")) {
            diagsList.add(Arrays.asList("I20.1", "I20.8", "I20.9", "I25.0", "I25.1", "I25.2", "I25.5", "I25.6", "I25.8", "I25.9"));
            diagsList.add(utilService.diagStringBuilder(Arrays.asList("I10", "I11", "I12", "I13", "I15")));
            diagsList.add(Arrays.asList("I50.0", "I50.1", "I50.9"));
            diagsList.add(utilService.diagStringBuilder(List.of("I48")));
            diagsList.add(utilService.diagStringBuilder(List.of("I47")));
            diagsList.add(List.of("I65.2"));
            diagsList.add(Arrays.asList("R73.0", "R73.9"));
            diagsList.add(utilService.diagStringBuilder(List.of("E11")));
            diagsList.add(Arrays.asList("I69.0", "I69.1", "I69.2", "I69.3", "I69.4", "I67.8"));
            diagsList.add(utilService.diagStringBuilder(List.of("E78")));
            diagsList.add(utilService.diagStringBuilder(List.of("K20")));
            diagsList.add(List.of("K21.0"));
            diagsList.add(List.of("K21.0"));
            diagsList.add(utilService.diagStringBuilder(List.of("K25")));
            diagsList.add(utilService.diagStringBuilder(List.of("K26")));
            diagsList.add(Arrays.asList("K29.4", "K29.5"));
            diagsList.add(List.of("K31.7"));
            diagsList.add(utilService.diagStringBuilder(List.of("K86")));
            diagsList.add(Arrays.asList("J44.0", "J44.8", "J44.9"));
            diagsList.add(List.of("J47.0"));
            diagsList.add(Arrays.asList("J45.0", "J45.1", "J45.8", "J45.9"));
            diagsList.add(utilService.diagStringBuilder(Arrays.asList("J12", "J13", "J14")));
            diagsList.add(List.of("J84.1"));
            diagsList.add(List.of("N18.1"));
            diagsList.add(List.of("N18.1"));
            diagsList.add(List.of("N18.9"));
            diagsList.add(List.of("M81.5"));
        } else if (spec.equals("41")){
            diagsList.add(utilService.diagStringBuilder(List.of("C44")));
            diagsList.add(utilService.diagStringBuilder(0, 96, "C", 44));
            diagsList.add(utilService.diagStringBuilder(List.of("C00")));
            diagsList.add(utilService.diagStringBuilder(1, 9, "C" ));
            diagsList.add(utilService.diagStringBuilder(10, 13, "C"));
            diagsList.add(utilService.diagStringBuilder(List.of("C15")));
            diagsList.add(utilService.diagStringBuilder(List.of("C16")));
            diagsList.add(utilService.diagStringBuilder(List.of("C18")));
            diagsList.add(utilService.diagStringBuilder(19, 21, "C"));
            diagsList.add(utilService.diagStringBuilder(List.of("C22")));
            diagsList.add(utilService.diagStringBuilder(List.of("C25")));
            diagsList.add(utilService.diagStringBuilder(List.of("C32")));
            diagsList.add(utilService.diagStringBuilder(Arrays.asList("C33", "C34")));
            diagsList.add(utilService.diagStringBuilder(Arrays.asList("C40", "C41")));
            diagsList.add(utilService.diagStringBuilder(List.of("C43")));
            diagsList.add(utilService.diagStringBuilder(47, 49, "C"));
            diagsList.add(utilService.diagStringBuilder(List.of("C50")));
            diagsList.add(utilService.diagStringBuilder(List.of("C53")));
            diagsList.add(utilService.diagStringBuilder(List.of("C54")));
            diagsList.add(utilService.diagStringBuilder(List.of("C56")));
            diagsList.add(utilService.diagStringBuilder(List.of("C61")));
            diagsList.add(utilService.diagStringBuilder(List.of("C64")));
            diagsList.add(utilService.diagStringBuilder(List.of("C67")));
            diagsList.add(utilService.diagStringBuilder(List.of("C73")));
            diagsList.add(utilService.diagStringBuilder(Arrays.asList("C81", "C82", "C83", "C84", "C85", "C86", "C88", "C90", "C96")));
            diagsList.add(utilService.diagStringBuilder(91, 95, "C"));
            diagsList.add(utilService.diagStringBuilder(0, 9, "D"));
        }

        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statementBars = dbjdbcConfig.getBars();
        Statement statementSrz = dbjdbcConfig.getSRZ();

        int diagCount = 0;
        int wasCount;
        List<String> intervals = new ArrayList<>();
        intervals.add(monthBeg);
        intervals.add(monthEnd);
        intervals.add(yearBeg);
        intervals.add(yearEnd);
        for (List<String> diags : diagsList) {
            wasCount = 0;
            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountDN(workingAgeSex, diags, spec, 1, intervals));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountDNPr(workingAgeSex, diags, statementBars, spec, 1, intervals));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountCalling(workingAgeSex, diags, spec));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountDN(workingAgeSex, diags, spec, 2, null));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountDNPr(workingAgeSex, diags, statementBars, spec, 2, null));
                wasCount++;
            }

            for (VisitType visitType : VisitType.values()) {
                for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                    sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                            .setCellValue(dnOnkoTherapiReportService.getCountVisit(visitType, workingAgeSex, diags, statementBars));
                    wasCount++;
                }
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountHospitalize(workingAgeSex, diags, statementBars, spec));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountDeath(workingAgeSex, diags, statementSrz, spec));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountAmbulance(workingAgeSex, diags, statementBars, spec));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountInv(workingAgeSex, diags, spec));
                wasCount++;
            }
            diagCount++;
        }

        try {
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getONKOReport(String monthBeg, String monthEnd, String yearBeg, String yearEnd, String filename){
        FileOutputStream outputStream;
        XSSFWorkbook workbook;
        try {
            excelFile = new FileInputStream("C:\\udio\\samples\\ONKO.xlsx");
            workbook = new XSSFWorkbook(excelFile);
            outputStream = new FileOutputStream("C:\\udio\\reports\\" + filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Sheet sheet = workbook.getSheetAt(0);

        int colIndex = -1;
        int rowIndex = -1;
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING) {
                    if (cell.getStringCellValue().contains("qry1.test")) {
                        colIndex = cell.getColumnIndex();
                        rowIndex = cell.getRowIndex();
                    }
                    if (cell.getStringCellValue().contains("[monthBeg]")) {
                        cell.setCellValue(cell.getStringCellValue().replace("[monthBeg]", utilService.getStringMonth(monthBeg)));
                    }
                    if (cell.getStringCellValue().contains("[monthEnd]")) {
                        cell.setCellValue(cell.getStringCellValue().replace("[monthEnd]", utilService.getStringMonth(monthEnd)));
                    }
                    if (cell.getStringCellValue().contains("[yearBeg]")) {
                        cell.setCellValue(cell.getStringCellValue().replace("[yearBeg]", yearBeg));
                    }
                }
            }
            if(colIndex > -1) break;
        }

        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getBars();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double all1 = onkoReportService.getCountOnko(AgeLimit.all);
        int older_18_1 = onkoReportService.getCountOnkoSpec(AgeLimit.older_18, monthBeg, monthEnd, yearBeg, yearEnd, "9,41,39", Arrays.asList("1", "2"), statement);
        int older_18_2 = onkoReportService.getCountOnkoSpec(AgeLimit.older_18, monthBeg, monthEnd, yearBeg, yearEnd, "9,41,39", List.of("3"), statement);
        int younger_18_1 = onkoReportService.getCountOnkoSpec(AgeLimit.younger_18, monthBeg, monthEnd, yearBeg, yearEnd, "49,39,102,19", Arrays.asList("1", "2"), statement);
        int younger_18_2 = onkoReportService.getCountOnkoSpec(AgeLimit.younger_18, monthBeg, monthEnd, yearBeg, yearEnd, "49,39,102,19", List.of("3"), statement);
        double all4 = older_18_1 + older_18_2 + younger_18_1 + younger_18_2;

        sheet.getRow(rowIndex).getCell(colIndex)
                .setCellValue(all1);
        sheet.getRow(rowIndex).getCell(colIndex + 1)
                .setCellValue(onkoReportService.getCountOnko(AgeLimit.older_18));
        sheet.getRow(rowIndex).getCell(colIndex + 2)
                .setCellValue(onkoReportService.getCountOnko(AgeLimit.younger_18));

        sheet.getRow(rowIndex).getCell(colIndex + 3).setCellValue(all4);
        sheet.getRow(rowIndex).getCell(colIndex + 4)
                .setCellValue(older_18_1);
        sheet.getRow(rowIndex).getCell(colIndex + 5)
                .setCellValue(older_18_2);
        sheet.getRow(rowIndex).getCell(colIndex + 6)
                .setCellValue(younger_18_1);
        sheet.getRow(rowIndex).getCell(colIndex + 7)
                .setCellValue(younger_18_2);
        sheet.getRow(rowIndex).getCell(colIndex + 8).setCellValue(decimalFormat.format(all4 / all1  * 100));

        try {
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getKARDIOReport(String monthBeg, String monthEnd, String yearBeg, String yearEnd, String filename){
        FileOutputStream outputStream;
        XSSFWorkbook workbook;
        try {
            excelFile = new FileInputStream("C:\\udio\\samples\\KARDIO.xlsx");
            workbook = new XSSFWorkbook(excelFile);
            outputStream = new FileOutputStream("C:\\udio\\reports\\" + filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Sheet sheet = workbook.getSheetAt(0);

        int colIndex = -1;
        int rowIndex = -1;
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING) {
                    if (cell.getStringCellValue().contains("qry1.test")) {
                        colIndex = cell.getColumnIndex();
                        rowIndex = cell.getRowIndex();

                        //break;
                    }
                    if (cell.getStringCellValue().contains("[monthBeg]")) {
                       cell.setCellValue(cell.getStringCellValue().replace("[monthBeg]", utilService.getStringMonth(monthBeg)));
                    }
                    if (cell.getStringCellValue().contains("[monthEnd]")) {
                        cell.setCellValue(cell.getStringCellValue().replace("[monthEnd]", utilService.getStringMonth(monthEnd)));
                    }
                    if (cell.getStringCellValue().contains("[yearBeg]")) {
                        cell.setCellValue(cell.getStringCellValue().replace("[yearBeg]", yearBeg));
                    }

                }
            }
            if(colIndex > -1) break;
        }

        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getBars();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double all1 = kardioReportService.getCountKARDIO();
        double all2 = kardioReportService.getCountKARDIOBars(monthBeg, monthEnd, yearBeg, yearEnd, statement);

        sheet.getRow(rowIndex).getCell(colIndex).setCellValue(all1);
        sheet.getRow(rowIndex).getCell(colIndex + 1).setCellValue(all2);
        sheet.getRow(rowIndex).getCell(colIndex + 2).setCellValue(decimalFormat.format(all2 / all1 * 100));

        try {
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DataFile loadFromExcelOnkoOther(DataFile dataFile, InputStream inputStream ) throws ParseException {
        XSSFWorkbook workbook;
        List<DataFilePatient> dataFilePatientList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        SimpleDateFormat dateFormatDr = new SimpleDateFormat("dd.MM.yyyy");
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheetAt(0);
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getSRZ();

        String fio = "";
        String dr = "";
        Sex w = null;
        Date date_1 = null;
        String diag = "";

        for (int count = 5; count < sheet.getLastRowNum() + 1; count++) {
            for (int levelCount = 0; levelCount < 4; levelCount++) {
                if (sheet.getRow(count - levelCount).getCell(1).getStringCellValue() != null
                        && !sheet.getRow(count - levelCount).getCell(1).getStringCellValue().isEmpty()) {
                    if (levelCount == 0){
                        fio = sheet.getRow(count).getCell(1).getStringCellValue();
                        dr = sheet.getRow(count).getCell(2).getStringCellValue();
                        w = sexService.getByName(sheet.getRow(count).getCell(3).getStringCellValue());
                        date_1 = dateFormat.parse(sheet.getRow(count).getCell(4).getStringCellValue());
                        diag = sheet.getRow(count).getCell(6).getStringCellValue();
                    } else {
                        fio = sheet.getRow(count - levelCount).getCell(1).getStringCellValue();
                        dr = sheet.getRow(count - levelCount).getCell(2).getStringCellValue();
                        w = sexService.getByName(sheet.getRow(count - levelCount).getCell(3).getStringCellValue());
                        date_1 = dateFormat.parse(sheet.getRow(count - levelCount).getCell(4).getStringCellValue());
                        diag = sheet.getRow(count - levelCount).getCell(6).getStringCellValue();
                    }
                    System.out.println(fio + " " + dr + " " + w.getName() + " " + diag);
                    break;
                }
            }

            try {
                ResultSet resultSet = statement.executeQuery("select p.enp, p.lpu, p.id from PEOPLE p join HISTFDR h on h.pid = p.id " +
                        "where (concat(p.FAM, ' ', p.IM, ' ', p.OT) = '" + fio + "' " +
                        " or concat(h.FAM, ' ', h.IM, ' ', h.OT) = '" + fio + "') " +
                        "and p.DR = PARSE('" + dr + "' as date)");
                if(resultSet.next()){
                    int mo_attach = 0;
                    if (resultSet.getString(2) != null && !resultSet.getString(2).isEmpty()){
                        mo_attach = resultSet.getInt(2);
                    }
                    dataFilePatientList.add(new DataFilePatient(
                            parseFIO(fio)[0], parseFIO(fio)[1], parseFIO(fio)[2], dateFormatDr.parse(dr), resultSet.getString(1),
                            mo_attach, w, 0, "", diag, null, 41, date_1, null,
                            1, resultSet.getLong(3), dataFile));
                } else {
                    dataFilePatientList.add(new DataFilePatient(
                            parseFIO(fio)[0], parseFIO(fio)[1], parseFIO(fio)[2], dateFormatDr.parse(dr), "", 0,
                            w, 0, "", diag, null, 41, date_1, null,
                            2, 0L,  dataFile));

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        dataFile.setDataFilePatient(dataFilePatientList);
        return dataFile;
    }

    public DataFile loadFromExcelOnkoChild(DataFile dataFile, InputStream inputStream){
        XSSFWorkbook workbook;
        List<DataFilePatient> dataFilePatientList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheetAt(0);
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getSRZ();

        String fio;
        Date dr;
        Sex w;
        Date date_1;
        String diag;

        for (int count = 3; count < sheet.getLastRowNum() + 1; count++) {
            fio = sheet.getRow(count).getCell(3).getStringCellValue();
            dr = sheet.getRow(count).getCell(4).getDateCellValue();
            w = sexService.getByName(sheet.getRow(count).getCell(5).getStringCellValue());
            date_1 = sheet.getRow(count).getCell(6).getDateCellValue();
            diag = sheet.getRow(count).getCell(8).getStringCellValue();
            System.out.println(fio);
            try {
                ResultSet resultSet = statement.executeQuery("select p.enp, p.lpu, p.id from PEOPLE p join HISTFDR h on h.pid = p.id " +
                        "where (concat(p.FAM, ' ', p.IM, ' ', p.OT) = '" + sheet.getRow(count).getCell(3).getStringCellValue() + "' " +
                        " or concat(h.FAM, ' ', h.IM, ' ', h.OT) = '" + sheet.getRow(count).getCell(3).getStringCellValue() + "') " +
                        "and p.DR  = PARSE('" + dateFormat.format(sheet.getRow(count).getCell(4).getDateCellValue()) + "' as date)");
                if(resultSet.next()){
                    int mo_attach = 0;
                    if (resultSet.getString(2) != null && !resultSet.getString(2).isEmpty()){
                        mo_attach = resultSet.getInt(2);
                    }
                    dataFilePatientList.add(new DataFilePatient(
                            parseFIO(fio)[0], parseFIO(fio)[1], parseFIO(fio)[2], dr, resultSet.getString(1),
                            mo_attach, w, 0, "", diag, null, 41, date_1,
                            null, 1, resultSet.getLong(3), dataFile));
                } else {
                    dataFilePatientList.add(new DataFilePatient(
                            parseFIO(fio)[0], parseFIO(fio)[1], parseFIO(fio)[2], dr, "", 0,
                            w, 0, "", diag, null, 41, date_1, null, 2,
                            0L,  dataFile));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        dataFile.setDataFilePatient(dataFilePatientList);
        return dataFile;
    }

    public DataFile loadFromExcelFromBarsMO(DataFile dataFile, InputStream inputStream ) throws ParseException {
        XSSFWorkbook workbook;
        List<DataFilePatient> dataFilePatientList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheetAt(0);
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getSRZ();

        String fio;
        Date dr;
        Sex w;
        Date date_1;
        String diag;

        for (int count = 2; count < sheet.getLastRowNum(); count++) {
            if (sheet.getRow(count).getCell(6).getDateCellValue() != null
                    && sheet.getRow(count).getCell(3).getDateCellValue() != null) {
                fio = sheet.getRow(count).getCell(1).getStringCellValue();
                dr = sheet.getRow(count).getCell(3).getDateCellValue();
                w = sexService.getByName(sheet.getRow(count).getCell(2).getStringCellValue());
                date_1 = sheet.getRow(count).getCell(6).getDateCellValue();
                diag = sheet.getRow(count).getCell(0).getStringCellValue();
                System.out.println(fio);
                try {
                    ResultSet resultSet = statement.executeQuery("select p.enp, p.lpu, p.id from PEOPLE p join HISTFDR h on h.pid = p.id " +
                            "where (concat(p.FAM, ' ', p.IM, ' ', p.OT) = '" + fio + "' " +
                            " or concat(h.FAM, ' ', h.IM, ' ', h.OT) = '" + fio + "') " +
                            "and p.DR  = PARSE('" + dateFormat.format(dr) + "' as date)");
                    if (resultSet.next()) {
                        int mo_attach = 0;
                        if (resultSet.getString(2) != null && !resultSet.getString(2).isEmpty()) {
                            mo_attach = resultSet.getInt(2);
                        }
                        if (resultSet.getString(1) == null || resultSet.getString(1).isEmpty()){
                            dataFilePatientList.add(new DataFilePatient(
                                    parseFIO(fio)[0], parseFIO(fio)[1], parseFIO(fio)[2], dr, resultSet.getString(1),
                                    mo_attach, w, 0, "", diag, null, 76, date_1, null,
                                    6, resultSet.getLong(3), dataFile));
                        } else {
                            dataFilePatientList.add(new DataFilePatient(
                                    parseFIO(fio)[0], parseFIO(fio)[1], parseFIO(fio)[2], dr, resultSet.getString(1),
                                    mo_attach, w, 0, "", diag, null, 76, date_1, null,
                                    1, resultSet.getLong(3), dataFile));
                        }
                    } else {
                        dataFilePatientList.add(new DataFilePatient(
                                parseFIO(fio)[0], parseFIO(fio)[1], parseFIO(fio)[2], dr, "", 0,
                                w, 0, "", diag, null, 76, date_1, null,
                                2, 0L, dataFile));

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        dataFile.setDataFilePatient(dataFilePatientList);
        return dataFile;
    }


    public DataFile tmpLoadDeadNewFormat(DataFile dataFile, InputStream inputStream) throws ParseException {
        XSSFWorkbook workbook;
        List<DataFilePatient> dataFilePatientList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat dateFormatFromXls = new SimpleDateFormat("yyyy-MM-dd");
        try {

            workbook =  new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheetAt(0);
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getSRZ();

        String fam;
        String im;
        String ot;
        Date dr;
        Sex w;
        Date date_1;
        String diag;

        for (int count = 1; count < sheet.getLastRowNum() + 1; count++) {
            fam = sheet.getRow(count).getCell(3).getStringCellValue();
            im = sheet.getRow(count).getCell(4).getStringCellValue();
            ot = sheet.getRow(count).getCell(5).getStringCellValue();
            dr = dateFormatFromXls.parse(sheet.getRow(count).getCell(2).getStringCellValue());
            w = sexService.getById((long) sheet.getRow(count).getCell(13).getNumericCellValue());
            date_1 = dateFormatFromXls.parse(sheet.getRow(count).getCell(0).getStringCellValue());
            diag = sheet.getRow(count).getCell(1).getStringCellValue();
            System.out.println(fam + " " + im + " " + ot);
            try {
                ResultSet resultSet = statement.executeQuery("select p.enp, p.lpu, p.id from PEOPLE p join HISTFDR h on h.pid = p.id " +
                        "where (concat(p.FAM, ' ', p.IM, ' ', p.OT) = '" + fam + " " + im + " " + ot + "' " +
                        " or concat(h.FAM, ' ', h.IM, ' ', h.OT) = '" + fam + " " + im + " " + ot + "') " +
                        "and p.DR  = PARSE('" + dateFormat.format(dr) + "' as date)");
                if(resultSet.next()){
                    int mo_attach = 0;
                    if (resultSet.getString(2) != null && !resultSet.getString(2).isEmpty()){
                        mo_attach = resultSet.getInt(2);
                    }
                    dataFilePatientList.add(new DataFilePatient(
                            fam, im, ot, dr, resultSet.getString(1),
                            mo_attach, w, 0, "", diag, null, 41, date_1,
                            null, 1, resultSet.getLong(3), dataFile));
                } else {
                    dataFilePatientList.add(new DataFilePatient(
                            fam, im, ot, dr, "", 0,
                            w, 0, "", diag, null, 41, date_1, null, 2,
                            0L,  dataFile));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        dataFile.setDataFilePatient(dataFilePatientList);
        return dataFile;
    }


    public void getEfficiencyReport(String monthBeg, String monthEnd, String yearBeg, String yearEnd, String filename, String disp, int part){
        OutputStream outputStream;
        XSSFWorkbook workbook;
        try {
            workbook = new XSSFWorkbook();
            outputStream = new FileOutputStream("C:\\udio\\reports\\" + filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.createSheet(yearBeg);

        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getBars();
        EfficiencyService efficiencyService = new EfficiencyService();
        List<Efficiency> efficiencies = efficiencyService.getAll(statement, monthBeg, monthEnd, yearBeg, yearEnd, part);

        Row row150007 = sheet.createRow(1);
        row150007.createCell(1).setCellValue("150007");
        row150007.createCell(2).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150007") && disp.contains(c.getDisp())).toList().size());
        row150007.createCell(3).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150007")).toList().size());

        Row row150009 = sheet.createRow(2);
        row150009.createCell(1).setCellValue("150009");
        row150009.createCell(2).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150009") && disp.contains(c.getDisp())).toList().size());
        row150009.createCell(3).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150009")).toList().size());

        Row row150010 = sheet.createRow(3);
        row150010.createCell(1).setCellValue("150010");
        row150010.createCell(2).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150010") && disp.contains(c.getDisp())).toList().size());
        row150010.createCell(3).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150010")).toList().size());

        Row row150012 = sheet.createRow(4);
        row150012.createCell(1).setCellValue("150012");
        row150012.createCell(2).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150012") && disp.contains(c.getDisp())).toList().size());
        row150012.createCell(3).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150012")).toList().size());

        Row row150014 = sheet.createRow(5);
        row150014.createCell(1).setCellValue("150014");
        row150014.createCell(2).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150014") && disp.contains(c.getDisp())).toList().size());
        row150014.createCell(3).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150014")).toList().size());

        Row row150016 = sheet.createRow(6);
        row150016.createCell(1).setCellValue("150016");
        row150016.createCell(2).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150016") && disp.contains(c.getDisp())).toList().size());
        row150016.createCell(3).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150016")).toList().size());

        Row row150019 = sheet.createRow(7);
        row150019.createCell(1).setCellValue("150019");
        row150019.createCell(2).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150019") && disp.contains(c.getDisp())).toList().size());
        row150019.createCell(3).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150019")).toList().size());

        Row row150035 = sheet.createRow(8);
        row150035.createCell(1).setCellValue("150035");
        row150035.createCell(2).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150035") && disp.contains(c.getDisp())).toList().size());
        row150035.createCell(3).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150035")).toList().size());

        Row row150041 = sheet.createRow(9);
        row150041.createCell(1).setCellValue("150041");
        row150041.createCell(2).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150041") && disp.contains(c.getDisp())).toList().size());
        row150041.createCell(3).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150041")).toList().size());

        Row row150036 = sheet.createRow(10);
        row150036.createCell(1).setCellValue("150036");
        row150036.createCell(2).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150036") && disp.contains(c.getDisp())).toList().size());
        row150036.createCell(3).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150036")).toList().size());

        Row row150112 = sheet.createRow(11);
        row150112.createCell(1).setCellValue("150112");
        row150112.createCell(2).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150112") && disp.contains(c.getDisp())).toList().size());
        row150112.createCell(3).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150112")).toList().size());

        Row row150013 = sheet.createRow(12);
        row150013.createCell(1).setCellValue("150013");
        row150013.createCell(2).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150013") && disp.contains(c.getDisp())).toList().size());
        row150013.createCell(3).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150013")).toList().size());

        Row row150048 = sheet.createRow(13);
        row150048.createCell(1).setCellValue("150048");
        row150048.createCell(2).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150048") && disp.contains(c.getDisp())).toList().size());
        row150048.createCell(3).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150048")).toList().size());

        Row row150042 = sheet.createRow(14);
        row150042.createCell(1).setCellValue("150042");
        row150042.createCell(2).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150042") && disp.contains(c.getDisp())).toList().size());
        row150042.createCell(3).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150042")).toList().size());

        Row row150043 = sheet.createRow(15);
        row150043.createCell(1).setCellValue("150043");
        row150043.createCell(2).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150043") && disp.contains(c.getDisp())).toList().size());
        row150043.createCell(3).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150043")).toList().size());

        Row row150044 = sheet.createRow(16);
        row150044.createCell(1).setCellValue("150044");
        row150044.createCell(2).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150044") && disp.contains(c.getDisp())).toList().size());
        row150044.createCell(3).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150044")).toList().size());

        Row row150045 = sheet.createRow(17);
        row150045.createCell(1).setCellValue("150045");
        row150045.createCell(2).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150045") && disp.contains(c.getDisp())).toList().size());
        row150045.createCell(3).setCellValue(efficiencies.stream().filter(c -> c.getMo().equals("150045")).toList().size());

        try {
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] parseFIO(String fio){
        String[] str = new String[3];
        str[0] = fio.substring(0, fio.indexOf(" ")).trim();
        try {
            str[1] = fio.substring(fio.indexOf(" "), fio.indexOf(" ", fio.indexOf(" ") + 1)).trim();
        } catch (Exception e){
            str[1] = fio.substring(fio.indexOf(" ", fio.indexOf(" "))).trim();
        }
        try {
            str[2] = fio.substring(fio.indexOf(" ", fio.indexOf(" ") + 1)).trim();
        } catch (Exception e){
            str[2] = "";
        }
        return str;
    }

    private CellStyle getCStyle(Workbook workbook){
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        return cellStyle;
    }

    private XSSFFont  getCFont(Workbook workbook){
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setBold(true);

        return font;
    }

    private CellStyle getHStyle(Workbook workbook){
        CellStyle headerStyle = ((XSSFWorkbook) workbook).createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);


        headerStyle.setFont(getCFont(workbook));
        return headerStyle;
    }

    private void createHeaderCell(Row header, String name, int index, Workbook workbook){
        Cell headerCell = header.createCell(index);
        headerCell.setCellValue(name);
        headerCell.setCellStyle(getHStyle(workbook));
    }

    private void createValueCell(Row row, String value, int index, Workbook workbook){
        Cell cell = row.createCell(index);
        cell.setCellStyle(getCStyle(workbook));
        cell.setCellValue(value);
    }

    public File getPADataPatientRequestRecord(Set<PADataPatientRequestRecord> paDataPatientRequestRecordSet) {
        FileOutputStream outputStream;
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Общий отчет");
        Row header = sheet.createRow(0);

        createHeaderCell(header, "Основной диагноз", 0, workbook);
        createHeaderCell(header, "Сопутствующий диагноз", 1, workbook);
        createHeaderCell(header, "Диагноз осложнения", 2, workbook);
        createHeaderCell(header, "Код профмероприятия", 3, workbook);
        createHeaderCell(header, "Тип профмероприятия", 4, workbook);
        createHeaderCell(header, "Дата включения в группу Д-наблюдения", 5, workbook);
        createHeaderCell(header, "Период прохождения профмероприятий", 6, workbook);
        createHeaderCell(header, "Код специальности врача", 7, workbook);
        createHeaderCell(header, "Плановый период (месяц) проведения следующего профмероприятия", 8, workbook);
        createHeaderCell(header, "Место проведения профмероприятия", 9, workbook);
        createHeaderCell(header, "Дата посещения/обращения застрахованного лица ", 10, workbook);
        createHeaderCell(header, "Результат профмероприятия", 11, workbook);
        createHeaderCell(header, "Описание результата профмероприятия", 12, workbook);


        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        int indexCount = 1;
        for (PADataPatientRequestRecord paDataPatientRequestRecord : paDataPatientRequestRecordSet){
            Row row = sheet.createRow(indexCount);
            createValueCell(row, paDataPatientRequestRecord.getMainDiagnosis(), 0, workbook);
            createValueCell(row, String.valueOf(paDataPatientRequestRecord.getConcomitantDiagnosis()), 1, workbook);
            createValueCell(row, paDataPatientRequestRecord.getDiagnosisComplications(), 2, workbook);
            createValueCell(row, String.valueOf(paDataPatientRequestRecord.getCodeTypePreventiveActions()), 3, workbook);
            createValueCell(row, paDataPatientRequestRecord.getNameTypePreventiveActions(), 4, workbook);
            createValueCell(row, dateFormat.format(paDataPatientRequestRecord.getDateInclude()), 5, workbook);
            createValueCell(row, paDataPatientRequestRecord.getPeriodPA(), 6, workbook);
            createValueCell(row, String.valueOf(paDataPatientRequestRecord.getSpecialtyDoctorCode()), 7, workbook);
            createValueCell(row, String.valueOf(paDataPatientRequestRecord.getScheduledMonthAdmission()), 8, workbook);
            createValueCell(row, String.valueOf(paDataPatientRequestRecord.getLocationInspection()), 9, workbook);
            createValueCell(row, dateFormat.format(paDataPatientRequestRecord.getDateInsuranceCase()), 10, workbook);
            createValueCell(row, String.valueOf(paDataPatientRequestRecord.getResultDispensaryAppointmentDoctor()), 11, workbook);
            createValueCell(row, String.valueOf(paDataPatientRequestRecord.getResultDispensaryAppointment()), 12, workbook);

            indexCount++;
        }

        File currDir = new File("C:\\udio\\reports\\" + UUID.randomUUID() + "_снятые.xlsx");
        try {
            outputStream = new FileOutputStream(currDir);
            workbook.write(outputStream);
            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return currDir;
    }
    public File getIndividualInformingRequestRecord(Set<IndividualInformingRequestRecord> individualInformingRequestRecords) {
        FileOutputStream outputStream;
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Общий отчет");
        Row header = sheet.createRow(0);

        createHeaderCell(header, "Основной диагноз", 0, workbook);
        createHeaderCell(header, "Очередность информирования", 1, workbook);
        createHeaderCell(header, "Способ информирования", 2, workbook);
        createHeaderCell(header, "Дата информирования", 3, workbook);
        createHeaderCell(header, "Планируемая дата следующего информирования", 4, workbook);
        createHeaderCell(header, "Дата добавления записи", 5, workbook);

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        int indexCount = 1;
        for (IndividualInformingRequestRecord individualInformingRequestRecord : individualInformingRequestRecords){
            Row row = sheet.createRow(indexCount);
            createValueCell(row, individualInformingRequestRecord.getMainDiagnosis(), 0, workbook);
            createValueCell(row, String.valueOf(individualInformingRequestRecord.getSequenceInformation()), 1, workbook);
            createValueCell(row, individualInformingRequestRecord.getWayInforming(), 2, workbook);
            createValueCell(row, dateFormat.format(individualInformingRequestRecord.getDateNotification()), 3, workbook);
            createValueCell(row, dateFormat.format(individualInformingRequestRecord.getPlannedNotificationDate()), 4, workbook);
            createValueCell(row, dateFormat.format(individualInformingRequestRecord.getDateBeg()), 5, workbook);

            indexCount++;
        }

        File currDir = new File("C:\\udio\\reports\\" + UUID.randomUUID() + "_снятые.xlsx");
        try {
            outputStream = new FileOutputStream(currDir);
            workbook.write(outputStream);
            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return currDir;
    }


    public File getInsuranceCase(Set<InsuranceCase> insuranceCaseSet) {
        FileOutputStream outputStream;
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Общий отчет");
        Row header = sheet.createRow(0);

        createHeaderCell(header, "Код МО прикрепления", 0, workbook);
        createHeaderCell(header, "Код МО", 1, workbook);
        createHeaderCell(header, "Контактные данные", 2, workbook);
        createHeaderCell(header, "Номер счета", 3, workbook);
        createHeaderCell(header, "Дата счета", 4, workbook);
        createHeaderCell(header, "Посещение/обращение", 5, workbook);
        createHeaderCell(header, "Дата начала лечения", 6, workbook);
        createHeaderCell(header, "Дата окончания лечения", 7, workbook);
        createHeaderCell(header, "Код основного диагноза", 8, workbook);
        createHeaderCell(header, "Код сопутствующего диагноза", 9, workbook);
        createHeaderCell(header, "Код диагноза осложнения", 10, workbook);
        createHeaderCell(header, "Результат", 11, workbook);
        createHeaderCell(header, "Сведения о Д-наблюдении", 12, workbook);

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        int indexCount = 1;
        for (InsuranceCase insuranceCase : insuranceCaseSet){
            Row row = sheet.createRow(indexCount);
            createValueCell(row, String.valueOf(insuranceCase.getCodeMOAttach()), 0, workbook);
            createValueCell(row, String.valueOf(insuranceCase.getCodeMO()), 1, workbook);
            createValueCell(row, insuranceCase.getContactDetails(), 2, workbook);
            createValueCell(row, insuranceCase.getInvoiceNumber(), 3, workbook);
            createValueCell(row, dateFormat.format(insuranceCase.getInvoiceDate()), 4, workbook);
            createValueCell(row, insuranceCase.getInsuranceCaseName(), 5, workbook);
            createValueCell(row, dateFormat.format(insuranceCase.getTreatmentStartDate()), 6, workbook);
            createValueCell(row, dateFormat.format(insuranceCase.getTreatmentEndDate()), 7, workbook);
            createValueCell(row, insuranceCase.getMainDiagnosis(), 8, workbook);
            createValueCell(row, insuranceCase.getConcomitantDiagnosis(), 9, workbook);
            createValueCell(row, insuranceCase.getComplicationsDiagnosis(), 10, workbook);
            createValueCell(row, insuranceCase.getResultSeeking(), 11, workbook);
            createValueCell(row, insuranceCase.getInformationDO(), 12, workbook);

            indexCount++;
        }

        File currDir = new File("C:\\udio\\reports\\" + UUID.randomUUID() + "_снятые.xlsx");
        try {
            outputStream = new FileOutputStream(currDir);
            workbook.write(outputStream);
            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return currDir;
    }

    public File getSchedulePIAndDispPlotRequestRecordDTO(TreeGrid<SchedulePIAndDispPlotDTO> treeGrid) {
        FileOutputStream outputStream;
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Общий отчет");
        Row header = sheet.createRow(0);

        ((TreeDataProvider<SchedulePIAndDispPlotDTO>)
                treeGrid.getDataProvider()).getTreeData().getChildren(
                ((TreeDataProvider<SchedulePIAndDispPlotDTO>)
                        treeGrid.getDataProvider()).getTreeData().getRootItems().get(0));
        createHeaderCell(header, "Наименование отделения", 0, workbook);
        createHeaderCell(header, "Январь", 1, workbook);
        createHeaderCell(header, "Февраль", 2, workbook);
        createHeaderCell(header, "Март", 3, workbook);
        createHeaderCell(header, "Апрель", 4, workbook);
        createHeaderCell(header, "Май", 5, workbook);
        createHeaderCell(header, "Июнь", 6, workbook);
        createHeaderCell(header, "Июль", 7, workbook);
        createHeaderCell(header, "Август", 8, workbook);
        createHeaderCell(header, "Сентябрь", 9, workbook);
        createHeaderCell(header, "Октябрь", 10, workbook);
        createHeaderCell(header, "Ноябрь", 11, workbook);
        createHeaderCell(header, "Декабрь", 12, workbook);


        int indexCount = 1;
        for (SchedulePIAndDispPlotDTO parent :
                ((TreeDataProvider<SchedulePIAndDispPlotDTO>)treeGrid.getDataProvider()).getTreeData().getRootItems()) {
            Row rowParent = sheet.createRow(indexCount);
            createValueCell(rowParent, parent.getName(), 0, workbook);
            createValueCell(rowParent, String.valueOf(parent.getMonth1()), 1, workbook);
            createValueCell(rowParent, String.valueOf(parent.getMonth2()), 2, workbook);
            createValueCell(rowParent, String.valueOf(parent.getMonth3()), 3, workbook);
            createValueCell(rowParent, String.valueOf(parent.getMonth4()), 4, workbook);
            createValueCell(rowParent, String.valueOf(parent.getMonth5()), 5, workbook);
            createValueCell(rowParent, String.valueOf(parent.getMonth6()), 6, workbook);
            createValueCell(rowParent, String.valueOf(parent.getMonth7()), 7, workbook);
            createValueCell(rowParent, String.valueOf(parent.getMonth8()), 8, workbook);
            createValueCell(rowParent, String.valueOf(parent.getMonth9()), 9, workbook);
            createValueCell(rowParent, String.valueOf(parent.getMonth10()), 10, workbook);
            createValueCell(rowParent, String.valueOf(parent.getMonth11()), 11, workbook);
            createValueCell(rowParent, String.valueOf(parent.getMonth12()), 12, workbook);
            indexCount++;
            for (SchedulePIAndDispPlotDTO child :
                    ((TreeDataProvider<SchedulePIAndDispPlotDTO>)treeGrid.getDataProvider()).getTreeData().getChildren(parent)) {
                Row rowChild = sheet.createRow(indexCount);
                createValueCell(rowChild, child.getName(), 0, workbook);
                createValueCell(rowChild, String.valueOf(child.getMonth1()), 1, workbook);
                createValueCell(rowChild, String.valueOf(child.getMonth2()), 2, workbook);
                createValueCell(rowChild, String.valueOf(child.getMonth3()), 3, workbook);
                createValueCell(rowChild, String.valueOf(child.getMonth4()), 4, workbook);
                createValueCell(rowChild, String.valueOf(child.getMonth5()), 5, workbook);
                createValueCell(rowChild, String.valueOf(child.getMonth6()), 6, workbook);
                createValueCell(rowChild, String.valueOf(child.getMonth7()), 7, workbook);
                createValueCell(rowChild, String.valueOf(child.getMonth8()), 8, workbook);
                createValueCell(rowChild, String.valueOf(child.getMonth9()), 9, workbook);
                createValueCell(rowChild, String.valueOf(child.getMonth10()), 10, workbook);
                createValueCell(rowChild, String.valueOf(child.getMonth11()), 11, workbook);
                createValueCell(rowChild, String.valueOf(child.getMonth12()), 12, workbook);
                indexCount++;
            }
        }


        File currDir = new File("C:\\udio\\reports\\" + UUID.randomUUID() + "_план-график.xlsx");
        try {
            outputStream = new FileOutputStream(currDir);
            workbook.write(outputStream);
            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return currDir;

    }

    public File getVolumeMedicalCareDTO(TreeGrid<VolumeMedicalCareDTO> treeGrid) {
        FileOutputStream outputStream;
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Общий отчет");
        Row header = sheet.createRow(0);

        ((TreeDataProvider<VolumeMedicalCareDTO>)
                treeGrid.getDataProvider()).getTreeData().getChildren(
                ((TreeDataProvider<VolumeMedicalCareDTO>)
                        treeGrid.getDataProvider()).getTreeData().getRootItems().get(0));
        createHeaderCell(header, "Наименование отделения", 0, workbook);
        createHeaderCell(header, "вчера", 1, workbook);
        createHeaderCell(header, "позавчера", 2, workbook);
        createHeaderCell(header, "2 дня назад", 3, workbook);
        createHeaderCell(header, "3 дня назад", 4, workbook);
        createHeaderCell(header, "4 дня назад", 5, workbook);
        createHeaderCell(header, "5 дней назад", 6, workbook);
        createHeaderCell(header, "6 дней назад", 7, workbook);


        int indexCount = 1;
        for (VolumeMedicalCareDTO parent :
                ((TreeDataProvider<VolumeMedicalCareDTO>) treeGrid.getDataProvider()).getTreeData().getRootItems()) {
            Row rowParent = sheet.createRow(indexCount);
            createValueCell(rowParent, parent.getName(), 0, workbook);
            createValueCell(rowParent, String.valueOf(parent.getDay1()), 1, workbook);
            createValueCell(rowParent, String.valueOf(parent.getDay2()), 2, workbook);
            createValueCell(rowParent, String.valueOf(parent.getDay3()), 3, workbook);
            createValueCell(rowParent, String.valueOf(parent.getDay4()), 4, workbook);
            createValueCell(rowParent, String.valueOf(parent.getDay5()), 5, workbook);
            createValueCell(rowParent, String.valueOf(parent.getDay6()), 6, workbook);
            createValueCell(rowParent, String.valueOf(parent.getDay7()), 7, workbook);
            indexCount++;
            for (VolumeMedicalCareDTO child :
                    ((TreeDataProvider<VolumeMedicalCareDTO>) treeGrid.getDataProvider()).getTreeData().getChildren(parent)) {
                Row rowChild = sheet.createRow(indexCount);
                createValueCell(rowChild, child.getName(), 0, workbook);
                createValueCell(rowChild, String.valueOf(child.getDay1()), 1, workbook);
                createValueCell(rowChild, String.valueOf(child.getDay2()), 2, workbook);
                createValueCell(rowChild, String.valueOf(child.getDay3()), 3, workbook);
                createValueCell(rowChild, String.valueOf(child.getDay4()), 4, workbook);
                createValueCell(rowChild, String.valueOf(child.getDay5()), 5, workbook);
                createValueCell(rowChild, String.valueOf(child.getDay6()), 6, workbook);
                createValueCell(rowChild, String.valueOf(child.getDay7()), 7, workbook);
                indexCount++;
            }
        }

        File currDir = new File("C:\\udio\\reports\\" + UUID.randomUUID() + "_объемы.xlsx");
        try {
            outputStream = new FileOutputStream(currDir);
            workbook.write(outputStream);
            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return currDir;

    }
}
