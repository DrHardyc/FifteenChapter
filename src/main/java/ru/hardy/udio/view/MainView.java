package ru.hardy.udio.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.hardy.udio.service.ServiceUtil;

import javax.annotation.security.PermitAll;

@Route("")
@PermitAll
public class MainView extends AppLayout {
    public MainView(){
        String authenticationName = SecurityContextHolder.getContext().getAuthentication().getName();
        DrawerToggle toggle = new DrawerToggle();
        H1 title = new H1("Диспансеризация и онкология версия 1.0_gamma. Пользователь " + authenticationName);
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        ServiceUtil su = new ServiceUtil();
        Tabs tabs = su.getTabs();
        addToDrawer(tabs);
        addToNavbar(toggle, title);
        //getElement().getThemeList().add("dark");
        //add(grid);
    }
}