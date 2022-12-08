package ru.hardy.udio.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;
import ru.hardy.udio.view.RegisterView;

@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener { //
    @Override
    public void serviceInit(ServiceInitEvent event){
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::beforeEnter); //
        });
    }
    private void beforeEnter(BeforeEnterEvent event){
        if(!SecurityUtils.isAccessGranted(event.getNavigationTarget())) { //
            if(SecurityUtils.isUserLoggedIn()) { //
                event.rerouteToError(NotFoundException.class); //
            }
        }
        if (event.getLocation().getPath().equals("register")) {
            event.rerouteTo(RegisterView.class);
        };
    }
}