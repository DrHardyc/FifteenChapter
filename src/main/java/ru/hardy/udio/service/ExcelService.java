package ru.hardy.udio.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.struct.DNGet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class ExcelService {


    public File testRead() {

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("test");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        Row header = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Name");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Age");
        headerCell.setCellStyle(headerStyle);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        Row row = sheet.createRow(2);
        Cell cell = row.createCell(0);
        cell.setCellValue("John Smith");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue(20);
        cell.setCellStyle(style);

        File currDir = new File("report\\temp.xlsx");
        FileOutputStream outputStream = null;
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

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Общий отчет");
//        sheet.setColumnWidth(0, 7000);
//        sheet.setColumnWidth(1, 8000);

        Row header = sheet.createRow(0);

        createHeaderCell(workbook, header, "ФИО", 0);
        createHeaderCell(workbook, header, "МО прикрепления", 1);
        createHeaderCell(workbook, header, "МО", 2);
        createHeaderCell(workbook, header, "Пол", 3);
        createHeaderCell(workbook, header, "Диагноз", 4);
        createHeaderCell(workbook, header, "Специальность врача", 5);
        createHeaderCell(workbook, header, "Возраст", 6);
        createHeaderCell(workbook, header, "Инвалидность", 7);
        createHeaderCell(workbook, header, "Дата взятия", 8);
        createHeaderCell(workbook, header, "Дата вызова", 9);

        int indexCount = 1;
        for (DNGet dnGet : dnGetList){
            Row row = sheet.createRow(indexCount);
            createValueCell(workbook, row, dnGet.getFIO(), 0);
            createValueCell(workbook, row, String.valueOf(dnGet.getMOAttach()), 1);
            createValueCell(workbook, row, String.valueOf(dnGet.getMo()), 2);
            createValueCell(workbook, row, dnGet.getPeopleSex(), 3);
            createValueCell(workbook, row, dnGet.getDiag(), 4);
            createValueCell(workbook, row, String.valueOf(dnGet.getProfil()), 5);
            createValueCell(workbook, row, String.valueOf(dnGet.getAge()), 6);
            createValueCell(workbook, row, dnGet.getPeopleInv(), 7);
            createValueCell(workbook, row, dnGet.getDate1String(), 8);
            createValueCell(workbook, row, dnGet.getDateCallString(), 9);

            indexCount++;
        }

        File currDir = new File("report\\" + UUID.randomUUID() + "_общий.xlsx");
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(currDir);
            workbook.write(outputStream);
            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return currDir;
    }

    private void createHeaderCell(XSSFWorkbook workbook, Row header, String name, int index){
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(index);
        headerCell.setCellValue(name);
        headerCell.setCellStyle(headerStyle);
    }

    private void createValueCell(XSSFWorkbook workbook, Row row, String value, int index){

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        Cell cell = row.createCell(index);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }
}
