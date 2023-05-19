package ru.hardy.udio.service;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellFill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.report.DNTherapistReport.VisitType;
import ru.hardy.udio.domain.report.DNTherapistReport.WorkingAgeSex;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.domain.task.ReportTask;
import ru.hardy.udio.repo.DNGetRepo;
import ru.hardy.udio.service.report.DNTherapistReportService;

import java.io.*;
import java.util.*;

@Service
public class ExcelService {

    private final XSSFWorkbook workbook = new XSSFWorkbook();


    public File getOtherDNGets(Collection<DNGet> dnGetList){

        Sheet sheet = workbook.createSheet("Общий отчет");
//        sheet.setColumnWidth(0, 7000);
//        sheet.setColumnWidth(1, 8000);
        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        createHeaderCell(headerStyle, header, "ФИО", 0);
        createHeaderCell(headerStyle, header, "МО прикрепления", 1);
        createHeaderCell(headerStyle, header, "МО", 2);
        createHeaderCell(headerStyle, header, "Пол", 3);
        createHeaderCell(headerStyle, header, "Диагноз", 4);
        createHeaderCell(headerStyle, header, "Специальность врача", 5);
        createHeaderCell(headerStyle, header, "Возраст", 6);
        createHeaderCell(headerStyle, header, "Инвалидность", 7);
        createHeaderCell(headerStyle, header, "Дата взятия", 8);
        createHeaderCell(headerStyle, header, "Дата вызова", 9);

        int indexCount = 1;
        for (DNGet dnGet : dnGetList){
            Row row = sheet.createRow(indexCount);
            createValueCell(null, row, dnGet.getFIO(), 0);
            createValueCell(null, row, String.valueOf(dnGet.getMOAttach()), 1);
            createValueCell(null, row, String.valueOf(dnGet.getMo()), 2);
            createValueCell(null, row, dnGet.getPeopleSex(), 3);
            createValueCell(null, row, dnGet.getDiag(), 4);
            createValueCell(null, row, String.valueOf(dnGet.getSpecialization()), 5);
            createValueCell(null, row, String.valueOf(dnGet.getAge()), 6);
            createValueCell(null, row, dnGet.getPeopleInv(), 7);
            createValueCell(null, row, dnGet.getDate1String(), 8);
            createValueCell(null, row, dnGet.getDateCallString(), 9);

            indexCount++;
        }

        File currDir = new File("reports\\" + UUID.randomUUID() + "_общий.xlsx");
        try {
            FileOutputStream outputStream = new FileOutputStream(currDir);
            workbook.write(outputStream);
            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return currDir;
    }


    public XSSFWorkbook getDNTherapistReportSample(List<DNGet> dnGets, String month, String year, String filename){

        FileInputStream excelFile;
        XSSFWorkbook workbook;
        FileOutputStream outputStream;
        try {
            excelFile = new FileInputStream("samples\\DNTh.xlsx");
            workbook = new XSSFWorkbook(excelFile);
            outputStream = new FileOutputStream("reports\\" + filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
;

        Sheet sheet = workbook.getSheetAt(0);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

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
        return workbook;
    }

    private void createHeaderCell(CellStyle cellStyle, Row header, String name, int index){

        Cell headerCell = header.createCell(index);
        headerCell.setCellValue(name);
        headerCell.setCellStyle(cellStyle);
    }

    private void createValueCell(CellStyle cellStyle, Row row, String value, int index){
        Cell cell = row.createCell(index);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(value);
    }


}
