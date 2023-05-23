package ru.hardy.udio.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.report.AgeLimit;
import ru.hardy.udio.domain.report.VisitType;
import ru.hardy.udio.domain.report.WorkingAgeSex;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.service.report.DNTherapistReportService;
import ru.hardy.udio.service.report.KARDIOReport;
import ru.hardy.udio.service.report.ONKOReport;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class ExcelService {

    private XSSFWorkbook workbook = new XSSFWorkbook();
    private CellStyle style;

    private final CellStyle headerStyle = workbook.createCellStyle();

    private FileInputStream excelFile;
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
            createValueCell(row, dnGet.getPeopleSex(), 3);
            createValueCell(row, dnGet.getDiag(), 4);
            createValueCell(row, String.valueOf(dnGet.getSpecialization()), 5);
            createValueCell(row, String.valueOf(dnGet.getAge()), 6);
            createValueCell(row, dnGet.getPeopleInv(), 7);
            createValueCell(row, dnGet.getDate1String(), 8);
            createValueCell(row, dnGet.getDateCallString(), 9);

            indexCount++;
        }

        File currDir = new File("reports\\" + UUID.randomUUID() + "_общий.xlsx");
        try {
            outputStream = new FileOutputStream(currDir);
            workbook.write(outputStream);
            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return currDir;
    }

    public void getDNTherapistReportSample(List<DNGet> dnGets, String month, String year, String filename){
        try {
            excelFile = new FileInputStream("samples\\DNTh.xlsx");
            workbook = new XSSFWorkbook(excelFile);
            outputStream = new FileOutputStream("reports\\" + filename);
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
                        break;
                    }
                }
            }
            if(colIndex > -1) break;
        }
        List<String> diags = new ArrayList<>();
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
        DNTherapistReportService dnTherapistReportService = new DNTherapistReportService(dnGets);

        int diagCount = 0;
        int wasCount;
        for (String diag : diags) {
            wasCount = 0;
            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnTherapistReportService.getCountDN(workingAgeSex, diag));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnTherapistReportService.getCountDNPr(workingAgeSex, diag, month, year));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnTherapistReportService.getCountCalling(workingAgeSex, diag, month, year));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnTherapistReportService.getCountDN(workingAgeSex, diag));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnTherapistReportService.getCountDNPr(workingAgeSex, diag, month, year));
                wasCount++;
            }

            for (VisitType visitType : VisitType.values()) {
                for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                    sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                            .setCellValue(dnTherapistReportService.getCountVisit(visitType, workingAgeSex, diag, month, year));
                    wasCount++;
                }
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnTherapistReportService.getCountHospitalize(workingAgeSex, diag, month, year));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnTherapistReportService.getCountDeath(workingAgeSex, diag, month, year));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnTherapistReportService.getCountAmbulance(workingAgeSex, diag, month, year));
                wasCount++;
            }

            for (WorkingAgeSex workingAgeSex : WorkingAgeSex.values()) {
                sheet.getRow(rowIndex + diagCount).getCell(colIndex + wasCount)
                        .setCellValue(dnTherapistReportService.getCountInv(workingAgeSex, diag));
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

    public void getONKOReport(List<DNGet> dnGets, String month, String year, String filename){
        try {
            excelFile = new FileInputStream("samples\\ONKO.xlsx");
            workbook = new XSSFWorkbook(excelFile);
            outputStream = new FileOutputStream("reports\\" + filename);
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
                        break;
                    }
                }
            }
            if(colIndex > -1) break;
        }

        ONKOReport onkoReport = new ONKOReport(dnGets);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double all1 = onkoReport.getCountOnko(AgeLimit.all);
        double all4 = onkoReport.getCountOnkoSpec(AgeLimit.all, month, year, "9,41,49,39,102,19", "1,2,3");

        sheet.getRow(rowIndex).getCell(colIndex)
                .setCellValue(all1);
        sheet.getRow(rowIndex).getCell(colIndex + 1)
                .setCellValue(onkoReport.getCountOnko(AgeLimit.older_18));
        sheet.getRow(rowIndex).getCell(colIndex + 2)
                .setCellValue(onkoReport.getCountOnko(AgeLimit.younger_18));
        sheet.getRow(rowIndex).getCell(colIndex + 3).setCellValue(all4);

        sheet.getRow(rowIndex).getCell(colIndex + 4)
                .setCellValue(onkoReport.getCountOnkoSpec(AgeLimit.older_18, month, year, "9,41,39", "1,2"));
        sheet.getRow(rowIndex).getCell(colIndex + 5)
                .setCellValue(onkoReport.getCountOnkoSpec(AgeLimit.older_18, month, year, "9,41,39", "3"));
        sheet.getRow(rowIndex).getCell(colIndex + 6)
                .setCellValue(onkoReport.getCountOnkoSpec(AgeLimit.younger_18, month, year, "49,39,102,19", "1,2"));
        sheet.getRow(rowIndex).getCell(colIndex + 7)
                .setCellValue(onkoReport.getCountOnkoSpec(AgeLimit.younger_18, month, year, "49,39,102,19", "3"));
        sheet.getRow(rowIndex).getCell(colIndex + 8).setCellValue(decimalFormat.format(all4 / all1  * 100));

        try {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getKARDIOReport(List<DNGet> dnGets, String month, String year, String filename){
        try {
            excelFile = new FileInputStream("samples\\KARDIO.xlsx");
            workbook = new XSSFWorkbook(excelFile);
            outputStream = new FileOutputStream("reports\\" + filename);
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
                        break;
                    }
                }
            }
            if(colIndex > -1) break;
        }

        KARDIOReport kardioReport = new KARDIOReport(dnGets);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double all1 = kardioReport.getCountKARDIO();
        double all2 = kardioReport.getCountKARDIOBars(month, year);

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

}
