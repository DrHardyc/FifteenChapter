package ru.hardy.udio.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.hardy.udio.domain.User;
import ru.hardy.udio.service.ServiceUtil;
import ru.hardy.udio.service.UserService;




@Route("")
@PermitAll
public class MainView extends AppLayout {

    @Autowired
    private UserService userService;

    private final Avatar avatar = new Avatar();

    public MainView(){

        DrawerToggle toggle = new DrawerToggle();

        H3 title = new H3("Диспансерное наблюдение v1.1_betta");
        title.getStyle().set("margin", "0");
        ServiceUtil su = new ServiceUtil();
        Tabs tabs = su.getTabs();
        tabs.setAutoselect(true);

        addToDrawer(tabs);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(avatar);
        verticalLayout.setSizeFull();
        horizontalLayout.add(title, verticalLayout);
        addToNavbar(toggle, horizontalLayout);
        String authenticationName = SecurityContextHolder.getContext().getAuthentication().getName();
        avatar.setName(authenticationName);
    }

    @Override
    public void onAttach(AttachEvent attachEvent){
        User user = userService.getWithName(SecurityContextHolder.getContext().getAuthentication().getName());
        Tooltip.forComponent(avatar).withText(user.getFio()).setPosition(Tooltip.TooltipPosition.BOTTOM_START);
    }

}