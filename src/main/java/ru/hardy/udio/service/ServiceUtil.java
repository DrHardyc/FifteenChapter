package ru.hardy.udio.service;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.hardy.udio.view.AdminView;
import ru.hardy.udio.view.LogoutView;
import ru.hardy.udio.view.ReportView;
import ru.hardy.udio.view.TestView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ClientInfoStatus;

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
}
