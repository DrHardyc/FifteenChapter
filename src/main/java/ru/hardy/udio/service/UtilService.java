package ru.hardy.udio.service;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UtilService {

    public Tabs getTabs(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Tabs tabs = new Tabs();
        if (authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
            tabs.add(createTab(VaadinIcon.TERMINAL, "Админка", "Админская страница", AdminView.class));
            tabs.add(createTab(VaadinIcon.TAGS, "Testing", "Страница для тестирования", TestView.class));
        }
        if (authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_TFOMS"))) {
            tabs.add(createTab(VaadinIcon.FILE_TABLE, "Отчеты", "Отчеты", ReportView.class));
            tabs.add(createTab(VaadinIcon.TASKS, "Задачи", "Задачи", TaskView.class));
        }
        tabs.add(createTab(VaadinIcon.EXIT_O, "Выход", "Выйти из программы", LogoutView.class));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }
    private Tab createTab(VaadinIcon viewIcon, String viewName, String tooltip, Class<? extends Component> aClass){
        Icon icon = viewIcon.create();
        icon.getStyle()
                .set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");
        RouterLink link = new RouterLink(aClass);
        link.add(icon, new Span(viewName));
//        link.setRoute(aClass);
        return new Tab(link);
    }

    public BufferedReader getHBBufferedReader(String strURL) throws IOException {
        URL url;
        url = new URL(strURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return new BufferedReader(new InputStreamReader(con.getInputStream()));
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

    public String transformDiag(String diag){
        if(diag!= null && diag.substring(diag.indexOf(".") + 1).equals("0") && diag.length() == 5){
            return diag.substring(0, 3);
        } else return diag;
    }

    public String transformDiag(List<String> diags){
        StringBuilder returnDiag = new StringBuilder();
        int diagCount = 0;
        if (diags != null){
            for (String diag : diags){
                if (diagCount == 0){
                    returnDiag.append(diag);
                } else returnDiag.append(", ").append(diag);

                diagCount ++;
            }
        }
        return returnDiag.toString();
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
}
