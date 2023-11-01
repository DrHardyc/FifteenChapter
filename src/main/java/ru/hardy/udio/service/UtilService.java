package ru.hardy.udio.service;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.report.DateInterval;
import ru.hardy.udio.view.*;
import ru.hardy.udio.view.MOView;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UtilService {

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
            nav.addItem(sniXVChapter);
        }
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
}
