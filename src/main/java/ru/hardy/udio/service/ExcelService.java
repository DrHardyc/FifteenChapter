package ru.hardy.udio.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.report.AgeLimit;
import ru.hardy.udio.domain.report.VisitType;
import ru.hardy.udio.domain.report.WorkingAgeSex;
import ru.hardy.udio.domain.struct.*;
import ru.hardy.udio.service.reportservice.DNOnkoTherapiReportService;
import ru.hardy.udio.service.reportservice.KARDIOReportService;
import ru.hardy.udio.service.reportservice.ONKOReportService;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ExcelService {


    private SexService sexService;


    private DataFilePatientService dataFilePatientService;

    private final UtilService utilService = new UtilService();
    private XSSFWorkbook workbook = new XSSFWorkbook();
    private final CellStyle style;

    private final CellStyle headerStyle = workbook.createCellStyle();

    private InputStream excelFile;
    private FileOutputStream outputStream;
    public ExcelService(){
        this.style = workbook.createCellStyle();
        this.style.setWrapText(true);
        this.style.setAlignment(HorizontalAlignment.CENTER);
        this.style.setVerticalAlignment(VerticalAlignment.CENTER);
        this.style.setBorderTop(BorderStyle.THIN);
        this.style.setBorderBottom(BorderStyle.THIN);
        this.style.setBorderLeft(BorderStyle.THIN);
        this.style.setBorderRight(BorderStyle.THIN);

        //headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setBold(true);
        headerStyle.setFont(font);

    }

    public ExcelService(SexService sexService, DataFilePatientService dataFilePatientService){
        this.style = workbook.createCellStyle();
        this.style.setWrapText(true);
        this.style.setAlignment(HorizontalAlignment.CENTER);
        this.style.setVerticalAlignment(VerticalAlignment.CENTER);
        this.style.setBorderTop(BorderStyle.THIN);
        this.style.setBorderBottom(BorderStyle.THIN);
        this.style.setBorderLeft(BorderStyle.THIN);
        this.style.setBorderRight(BorderStyle.THIN);

        //headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setBold(true);
        headerStyle.setFont(font);
        this.sexService = sexService;
        this.dataFilePatientService = dataFilePatientService;

    }

    public File getDNOut(Collection<DNOut> dnOutList){

        Sheet sheet = workbook.createSheet("Общий отчет");
        Row header = sheet.createRow(0);

        createHeaderCell(header, "ФИО", 0);
        createHeaderCell(header, "Возраст", 1);
        createHeaderCell(header, "Пол", 2);
        createHeaderCell(header, "Диагноз", 3);
        createHeaderCell(header, "Дата смерти", 4);
        createHeaderCell(header, "Дата взятия", 5);

        int indexCount = 1;
        for (DNOut dnOut : dnOutList){
            Row row = sheet.createRow(indexCount);
            createValueCell(row, dnOut.getFIO(), 0);
            createValueCell(row, dnOut.getAge(), 1);
            createValueCell(row, dnOut.getSex(), 2);
            createValueCell(row, dnOut.getDiag(), 3);
            createValueCell(row, dnOut.getDsString(), 4);
            createValueCell(row, dnOut.getDate_1String(), 5);

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

        Sheet sheet = workbook.createSheet("Общий отчет");
        Row header = sheet.createRow(0);

        createHeaderCell(header, "ФИО", 0);
        createHeaderCell(header, "МО прикрепления", 1);
        createHeaderCell(header, "МО", 2);
        createHeaderCell(header, "Пол", 3);
        createHeaderCell(header, "Диагноз", 4);
        createHeaderCell(header, "Специальность врача", 5);
        createHeaderCell(header, "Возраст", 6);
        createHeaderCell(header, "Инвалидность", 7);
        createHeaderCell(header, "Дата взятия", 8);
        createHeaderCell(header, "Дата вызова", 9);

        int indexCount = 1;
        for (DNGet dnGet : dnGetList){
            Row row = sheet.createRow(indexCount);
            createValueCell(row, dnGet.getFIO(), 0);
            createValueCell(row, String.valueOf(dnGet.getMOAttach()), 1);
            createValueCell(row, String.valueOf(dnGet.getMo()), 2);
            createValueCell(row, String.valueOf(dnGet.getPeopleSex()), 3);
            createValueCell(row, dnGet.getDiag(), 4);
            createValueCell(row, String.valueOf(dnGet.getSpecialization()), 5);
            createValueCell(row, String.valueOf(dnGet.getAge()), 6);
            createValueCell(row, String.valueOf(dnGet.getPeopleInv()), 7);
            createValueCell(row, dnGet.getDate1String(), 8);
            createValueCell(row, dnGet.getDateCallString(), 9);

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

    public void getDNOnkoTherapiReportSample(List<DNGet> dnGets, String monthBeg, String monthEnd, String yearBeg, String yearEnd, String filename, int spec){
        try {
            String dir;
            if (spec == 1) dir = "C:\\udio\\samples\\DNTh.xlsx";
            else dir = "C:\\udio\\samples\\DNOnko.xlsx";
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
        List<String> diags = new ArrayList<>();
        if (spec == 1) {
            diags.add("I20.1, I20.8, I20.9, I25.0, I25.1, I25.2, I25.5, I25.6, I25.8, I25.9");
            diags.add("I10, I11, I12, I13, I15");
            diags.add("I50.0, I50.1, I50.9");
            diags.add("I48");
            diags.add("I47");
            diags.add("I65.2");
            diags.add("R73.0, R73.9");
            diags.add("E11");
            diags.add("I69.0, I69.1, I69.2, I69.3, I69.4, I67.8");
            diags.add("E78");
            diags.add("K20");
            diags.add("K21.0");
            diags.add("K21.0");
            diags.add("K25");
            diags.add("K26");
            diags.add("K29.4; K29.5");
            diags.add("K31.7");
            diags.add("K86");
            diags.add("J44.0, J44.8, J44.9");
            diags.add("J47.0");
            diags.add("J45.0, J45.1, J45.8, J45.9");
            diags.add("J12, J13, J14");
            diags.add("J84.1");
            diags.add("N18.1");
            diags.add("N18.1");
            diags.add("N18.9");
            diags.add("M81.5");
        } else {
            diags.add("C44");
            diags.add(diagStringBuilder(0, 96, "C", 44));
            diags.add("C00");
            diags.add(diagStringBuilder(1, 9, "C" ));
            diags.add(diagStringBuilder(10, 13, "C"));
            diags.add("C15");
            diags.add("C16");
            diags.add("C18");
            diags.add(diagStringBuilder(19, 21, "C"));
            diags.add("C22");
            diags.add("C25");
            diags.add("C32");
            diags.add("C33, C34");
            diags.add("C40, C41");
            diags.add("C43");
            diags.add(diagStringBuilder(47, 49, "C"));
            diags.add("C50");
            diags.add("C53");
            diags.add("C54");
            diags.add("C56");
            diags.add("C61");
            diags.add("C64");
            diags.add("C67");
            diags.add("C73");
            diags.add(diagStringBuilder(81, 86, "C") + ", C88, C90, C96");
            diags.add(diagStringBuilder(91, 95, "C"));
            diags.add(diagStringBuilder(0, 9, "D"));
        }
        DNOnkoTherapiReportService dnOnkoTherapiReportService = new DNOnkoTherapiReportService(dnGets);

        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statementBars = dbjdbcConfig.getBars();
        Statement statementSrz = dbjdbcConfig.getSRZ();

        int diagCount = 0;
        int wasCount;
        for (String diag : diags) {
            wasCount = 0;
            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountDN(workingAgeSex, diag));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountDNPr(workingAgeSex, diag, monthBeg, monthEnd, yearBeg, yearEnd, statementBars));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountCalling(workingAgeSex, diag, monthBeg, monthEnd, yearBeg, yearEnd));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountDN(workingAgeSex, diag));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountDNPr(workingAgeSex, diag, monthBeg, monthEnd, yearBeg, yearEnd, statementBars));
                wasCount++;
            }

            for (VisitType visitType : VisitType.values()) {
                for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                    sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                            .setCellValue(dnOnkoTherapiReportService.getCountVisit(visitType, workingAgeSex, diag, monthBeg, monthEnd, yearBeg, yearEnd, statementBars));
                    wasCount++;
                }
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountHospitalize(workingAgeSex, diag, monthBeg, monthEnd, yearBeg, yearEnd, statementBars));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountDeath(workingAgeSex, diag, monthBeg, monthEnd, yearBeg, yearEnd, statementSrz));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountAmbulance(workingAgeSex, diag, monthBeg, monthEnd, yearBeg, yearEnd, statementBars));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnOnkoTherapiReportService.getCountInv(workingAgeSex, diag));
                wasCount++;
            }
            diagCount++;
        }

        try {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createHeaderCell(Row header, String name, int index){
        Cell headerCell = header.createCell(index);
        headerCell.setCellValue(name);
        headerCell.setCellStyle(headerStyle);
    }

    private void createValueCell(Row row, String value, int index){
        Cell cell = row.createCell(index);
        cell.setCellStyle(style);
        cell.setCellValue(value);
    }

    public void getONKOReport(List<DNGet> dnGets, String monthBeg, String monthEnd, String yearBeg, String yearEnd, String filename){
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
        ONKOReportService onkoReportService = new ONKOReportService(dnGets);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double all1 = onkoReportService.getCountOnko(AgeLimit.all);
        int older_18_1 = onkoReportService.getCountOnkoSpec(AgeLimit.older_18, monthBeg, monthEnd, yearBeg, yearEnd, "9,41,39", "1,2", statement);
        int older_18_2 = onkoReportService.getCountOnkoSpec(AgeLimit.older_18, monthBeg, monthEnd, yearBeg, yearEnd, "9,41,39", "3", statement);
        int younger_18_1 = onkoReportService.getCountOnkoSpec(AgeLimit.younger_18, monthBeg, monthEnd, yearBeg, yearEnd, "49,39,102,19", "1,2", statement);
        int younger_18_2 = onkoReportService.getCountOnkoSpec(AgeLimit.younger_18, monthBeg, monthEnd, yearBeg, yearEnd, "49,39,102,19", "3", statement);
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
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getKARDIOReport(List<DNGet> dnGets, String monthBeg, String monthEnd, String yearBeg, String yearEnd, String filename){
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
        KARDIOReportService kardioReportService = new KARDIOReportService(dnGets);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double all1 = kardioReportService.getCountKARDIO();
        double all2 = kardioReportService.getCountKARDIOBars(monthBeg, monthEnd, yearBeg, yearEnd, statement);

        sheet.getRow(rowIndex).getCell(colIndex).setCellValue(all1);
        sheet.getRow(rowIndex).getCell(colIndex + 1).setCellValue(all2);
        sheet.getRow(rowIndex).getCell(colIndex + 2).setCellValue(decimalFormat.format(all2 / all1 * 100));

        try {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DataFile loadFromExcelOnkoOther(DataFile dataFile, InputStream inputStream ) throws ParseException {

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
                        "and p.DR  = PARSE('" + dr + "' as date)");
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

    public String diagStringBuilder(int startCount, int endCount, String diag, int exception){
        StringBuilder result = new StringBuilder();
        for(int i = startCount; i <= endCount; i++){
            if (i != exception){
                if (i < 10){
                    if (i == startCount){
                        result.append(diag).append("0").append(i);
                    } else result.append(", ").append(diag).append("0").append(i);
                } else result.append(", ").append(diag).append(i);
            }
        }
        return result.toString();
    }

    private String diagStringBuilder(int startCount, int endCount, String diag){
        StringBuilder result = new StringBuilder();
        for(int i = startCount; i <= endCount; i++){
            if (i < 10){
                if (i == startCount){
                    result.append(diag).append("0").append(i);
                } else result.append(", ").append(diag).append("0").append(i);
            } else result.append(", ").append(diag).append(i);
        }
        return result.toString();
    }
}
