package ru.hardy.udio.report;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class Reports {
    public void createPDFReport(String filename, String orgName, String inn, String kpp, String ogrn, String regNum,
                                String address){
        try {
            List<String> textList = new ArrayList<>();
            PDPage page = new PDPage();
            PDDocument document = new PDDocument();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setLeading(14.5f);
            addNewText(document, contentStream, 100, 750, TNRType.BD, "Свидетельство о регистрации страхования в территориальном фонде");
            addNewText(document, contentStream, 90, 735, TNRType.BD, "обязательного медицинского страхования при обязательном страховании");
            textList.add("Настоящим подтверждается, что в соответсвии с Законом Российской Федерации от 28 июня 1991 г.");
            textList.add("№1449-1 \"О медицинском страховании граждан Российской Федерации\" на основании сведений");
            textList.add("из единого Реестра Юридических лиц (Индивидуальных предпринимателей)");
            textList.add("заявитель:");
            addNewText(document, contentStream, 50, 690, TNRType.T, textList);
            textList.add(orgName);
            addNewText(document, contentStream, 50, 610, TNRType.BD, textList);
            textList.add("ИНН:");
            addNewText(document, contentStream, 50, 550, TNRType.T, textList);
            textList.add(inn);
            addNewText(document, contentStream, 90, 550, TNRType.BD, textList);
            textList.add("КПП:");
            addNewText(document, contentStream, 180, 550, TNRType.T, textList);
            textList.add(kpp);
            addNewText(document, contentStream, 220, 550, TNRType.BD, textList);
            textList.add("ОГРН:");
            addNewText(document, contentStream, 310, 550, TNRType.T, textList);
            textList.add(ogrn);
            addNewText(document, contentStream, 360, 550, TNRType.BD, textList);
            textList.add("Место нахождения (место жительства):");
            addNewText(document, contentStream, 50, 520, TNRType.T, textList);
            textList.add(address);
            addNewText(document, contentStream, 50, 505, TNRType.T, textList);
            textList.add("Зарегистрирован в качестве страхователя в Территориальном фонде ОМС РСО-Алания");
            textList.add("Регистрационный номер страхователя:");
            addNewText(document, contentStream, 50, 490, TNRType.T, textList);
            textList.add(regNum);
            addNewText(document, contentStream, 260, 475, TNRType.BD, textList);
            textList.add("Наименование и вдрес территориального фонда обязательного медицинского страхования,");
            textList.add("осуществившего регистрацию страхователя: ");
            addNewText(document, contentStream, 50, 420, TNRType.T, textList);
            textList.add("Территориальный фонд ОМС РСО-Алания, ");
            addNewText(document, contentStream, 280, 405, TNRType.BD, textList);
            textList.add("г. Владикавказ, ул. К.Маркса 48");
            addNewText(document, contentStream, 50, 390, TNRType.BD, textList);
            textList.add("Контактный телефон территориального фонда обязательного медицинского страхования:");
            addNewText(document, contentStream, 50, 375, TNRType.T, textList);
            textList.add("8(8672) 29-09-70 доб. 1084");
            addNewText(document, contentStream, 50, 360, TNRType.BD, textList);
            textList.add("Регистрацию страхователя осуществил специалист отдела формирования финансововых средств");
            textList.add("и статистической отчетночти в системе ОМС.");
            addNewText(document, contentStream, 50, 345, TNRType.T, textList);
            contentStream.close();
            document.save(filename);
            document.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addNewLLine(PDPageContentStream contentStream, int count){
        try {
            contentStream.beginText();
            for (int i = 0; i < count; i++){
                contentStream.newLine();
            }
            contentStream.endText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addNewText(PDDocument document, PDPageContentStream contentStream, float tx, float ty,
                            TNRType tnrType, List<String> textList){
        try {
            contentStream.beginText();
            contentStream.newLineAtOffset(tx, ty);
            contentStream.setFont(PDType0Font.load(document, new File(getFontString(tnrType))), 12);
            for (String text : textList){
                contentStream.showText(text);
                contentStream.newLine();
            }
            contentStream.endText();
            textList.clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void addNewText(PDDocument document, PDPageContentStream contentStream, float tx, float ty,
                            TNRType tnrType, String text){
        try {
            contentStream.setFont(PDType0Font.load(document, new File(getFontString(tnrType))), 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(tx, ty);
            contentStream.showText(text);
            contentStream.endText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private String getFontString(TNRType tnrType) throws IOException {
        switch (tnrType){
            case I -> {
                return "./fonts/timesi.ttf";
            }
            case T -> {
                return "./fonts/times.ttf";
            }
            case BD -> {
                return "./fonts/timesbd.ttf";
            }
            case BI -> {
                return "./fonts/timesbi.ttf";
            }
            default -> {
                return null;
            }
        }
    }

    private enum TNRType{
        T, BI, I, BD
    }
}
