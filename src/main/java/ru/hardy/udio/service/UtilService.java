package ru.hardy.udio.service;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.component.upload.UploadI18N;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.report.DateInterval;
import ru.hardy.udio.view.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UtilService {

    public static Date DateTo900Format(int minusDay){
        try {
            return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
                    .parse((new SimpleDateFormat("dd.MM.yyyy ")
                            .format(Date.from(Instant
                                    .now()
                                    .minus(minusDay, ChronoUnit.DAYS)))) + " 09:00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static SideNav getTabs(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SideNav nav = new SideNav();

        if (authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
            SideNavItem sideNavItem = new SideNavItem("Администратор");
            sideNavItem.addItem(new SideNavItem("Админ", AdminView.class, VaadinIcon.TERMINAL.create()));
            sideNavItem.addItem(new SideNavItem("Тестирование", TestView.class, VaadinIcon.TAGS.create()));
            nav.addItem(sideNavItem);
        }
        if (authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_TFOMS"))) {
            SideNavItem sniXVChapter = new SideNavItem("ХV Глава");
            sniXVChapter.addItem(new SideNavItem("Люди", PeopleView.class, VaadinIcon.USERS.create()));
            sniXVChapter.addItem(new SideNavItem("Мед. организации", MOView.class, VaadinIcon.HOSPITAL.create()));
            sniXVChapter.addItem(new SideNavItem("Отчеты", ReportView.class, VaadinIcon.FILE_TABLE.create()));
            sniXVChapter.addItem(new SideNavItem("Задачи", TaskView.class, VaadinIcon.TASKS.create()));
            //sniXVChapter.addItem(new SideNavItem("Задачи", MainRView.class, VaadinIcon.TASKS.create()));
            nav.addItem(sniXVChapter);
        }
        if (authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_REGUL"))) {
            SideNavItem sniRegUL = new SideNavItem("Регистрация ЮЛ");
            sniRegUL.addItem(new SideNavItem("ЮЛ/ИП", RegULView.class, VaadinIcon.BRIEFCASE.create()));
            sniRegUL.addItem(new SideNavItem("Загрузка", RegULUploadView.class, VaadinIcon.UPLOAD.create()));
            nav.addItem(sniRegUL);
        }
        nav.addItem(new SideNavItem("Выход", LogoutView.class));
        return nav;
    }
    public BufferedReader getHBBufferedReader(String strURL) throws IOException {
        URL url;
        url = new URL(strURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
    }

    public Date transformDate(String month, String year, DateInterval dateinterval){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        switch (dateinterval){
            case minDate -> {
                try {
                    if (month.equals("1")){
                        return dateFormat.parse("26.12." + (Integer.parseInt(year) - 1));
                    } else {
                        return dateFormat.parse("26." + (Integer.parseInt(month) - 1) + "." + year);
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            case maxDate -> {
                try {
                    return dateFormat.parse("25." + Integer.parseInt(month) + "." + year);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    public String transformStringArrayForBars(List<String> diags){
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (String diag : diags){
            if (count == 0)
                stringBuilder.append("'").append(diag).append("'");
            else stringBuilder.append(", '").append(diag).append("'");
            count++;
        }
        return stringBuilder.toString();
    }

    public String getStringMonth(String month){
        return switch (month) {
            case "1" -> "январь";
            case "2" -> "февраль";
            case "3" -> "март";
            case "4" -> "апрель";
            case "5" -> "май";
            case "6" -> "июнь";
            case "7" -> "июль";
            case "8" -> "август";
            case "9" -> "сентябрь";
            case "10" -> "октябрь";
            case "11" -> "ноябрь";
            case "12" -> "декабрь";
            default -> "";
        };
    }

    public boolean parseAge(String value, String searchTerm) {
        try
        {
            Integer.parseInt(value);
            Integer.parseInt(searchTerm.substring(1));
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public List<String> diagStringBuilder(int startCount, int endCount, String diag, int exception){
        List<String> strings = new ArrayList<>();
        for(int i = startCount; i <= endCount; i++){
            if (i != exception) {
                strings.add(diag);
                for (int j = 0; j < 10; j++) {
                    if (i < 10) {
                        strings.add(diag + "0" + i + "." + j);
                    } else strings.add(diag + i + "." + j);
                }
            }
        }
        return strings;
    }

    public List<String> diagStringBuilder(int startCount, int endCount, String diag){
        List<String> strings = new ArrayList<>();
        for(int i = startCount; i <= endCount; i++) {
            if (i < 10){
                strings.add(diag + "0" + i);
            } else strings.add(diag + i);
            for (int j = 0; j < 10; j++) {
                if (i < 10) {
                    strings.add(diag + "0" + i + "." + j);
                } else strings.add(diag + i + "." + j);
            }
        }
        return strings;
    }

    public List<String> diagStringBuilder(List<String> diags){
        List<String> strings = new ArrayList<>();
        for(String diag : diags) {
            strings.add(diag);
            for (int j = 0; j < 10; j++) {
                strings.add(diag + "." + j);
            }
        }
        return strings;
    }

    public static LocalDate dateToLocalDate(Date date){
        if (date != null) {
            return Instant.ofEpochMilli(date.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }
        return null;
    }

    public static UploadI18N uploadI18N(){

        UploadI18N uploadI18N = new UploadI18N();
        uploadI18N.setError(new UploadI18N.Error());
        uploadI18N.getError().setFileIsTooBig("Размер файла превышает 2 Мб");
        uploadI18N.getError().setIncorrectFileType("Расширение должно соответствовать .xml");
        uploadI18N.getError().setTooManyFiles("Нужно прикрепить не более 1 файла");

        //add files
        uploadI18N.setAddFiles(new UploadI18N.AddFiles());
        uploadI18N.getAddFiles().setOne("Загрузить файл");
        uploadI18N.getAddFiles().setMany("Загрузить файлы");

        //uploading
        uploadI18N.setUploading(new UploadI18N.Uploading());
        //uploading errors
        uploadI18N.getUploading().setError(new UploadI18N.Uploading.Error());
        uploadI18N.getUploading().getError().setForbidden("В доступе отказано!");
        uploadI18N.getUploading().getError().setServerUnavailable("Сервер недоступен!");
        uploadI18N.getUploading().getError().setUnexpectedServerError("Внезапная ошибка сервера!");
        //uploading status
        uploadI18N.getUploading().setStatus(new UploadI18N.Uploading.Status());
        uploadI18N.getUploading().getStatus().setConnecting("Подключение...");
        uploadI18N.getUploading().getStatus().setHeld("Ожидание...");
        uploadI18N.getUploading().getStatus().setProcessing("Обработка...");
        uploadI18N.getUploading().getStatus().setStalled("Ожидание...");
        //uploading remaining time
        uploadI18N.getUploading().setRemainingTime(new UploadI18N.Uploading.RemainingTime());
        uploadI18N.getUploading().getRemainingTime().setPrefix("Осталось");
        uploadI18N.getUploading().getRemainingTime().setUnknown("Неизвестно");
        uploadI18N.setDropFiles(new UploadI18N.DropFiles());
        uploadI18N.getDropFiles().setOne("перетащите файл");
        uploadI18N.getDropFiles().setMany("перетащите файлы");

        uploadI18N.setUnits( new UploadI18N.Units() );
        uploadI18N.getUnits().setSize( new ArrayList<>(Arrays.asList("б", "кБ", "Мб", "Гб", "Тб", "Пб")));

        return uploadI18N;
    }
}
