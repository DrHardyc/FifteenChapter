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
import java.util.Date;

@Service
public class ServiceUtil{

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
                    if (month.equals("12")){
                        return dateFormat.parse("25.01." + (Integer.parseInt(year) + 1));
                    } else {
                        return dateFormat.parse("25." + (Integer.parseInt(month) + 1) + "." + year);
                    }
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

    public String transformStringArrayForBars(String diags){
        int count = 0;
        StringBuilder resultStr = new StringBuilder();
        String[] str = diags.replaceAll("\\s", "").trim().split(",");
        while (count < str.length){
            if (count == 0){
                resultStr.append("'").append(str[count]).append("'");
            } else  resultStr.append(",'").append(str[count]).append("'");
            count++;
        }
        return resultStr.toString();
    }
}
