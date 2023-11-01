package ru.hardy.udio.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.hardy.udio.domain.User;
import ru.hardy.udio.service.UserService;
import ru.hardy.udio.service.UtilService;

@Route("")
@PermitAll
public class MainView extends AppLayout{

    @Autowired
    private UserService userService;

    private final H3 avatar = new H3();

    public MainView() {

        DrawerToggle toggle = new DrawerToggle();
        H5 titleVersion = new H5("Разработчик Cherminel IT. Whatsapp 918-742-28-78");
        addToDrawer(UtilService.getTabs());
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(avatar);
        verticalLayout.setSizeFull();
        horizontalLayout.add(verticalLayout);
        addToNavbar(toggle, horizontalLayout);

        HorizontalLayout hlVersion = new HorizontalLayout();
        hlVersion.setAlignSelf(FlexComponent.Alignment.END, titleVersion);
        hlVersion.setSizeFull();
        hlVersion.add(titleVersion);
        addToDrawer(hlVersion);

        Footer footer = new Footer(new Span("ИР ТФОМС РСО-А v1.4.4"));
        addToNavbar(footer);
    }

    @Override
    public void onAttach(AttachEvent attachEvent){
        User user = userService.getWithName(SecurityContextHolder.getContext().getAuthentication().getName());
        avatar.setText(user.getFio());
        Tooltip.forComponent(avatar).withText(user.getPosition()).setPosition(Tooltip.TooltipPosition.BOTTOM_START);
        avatar.getStyle().set("margin", "auto");
    }
}