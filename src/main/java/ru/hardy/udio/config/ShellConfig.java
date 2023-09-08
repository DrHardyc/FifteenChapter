package ru.hardy.udio.config;

import com.vaadin.flow.component.PushConfiguration;
import com.vaadin.flow.component.ReconnectDialogConfiguration;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Pre;
import com.vaadin.flow.component.page.*;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.communication.PushMode;

@Push(PushMode.AUTOMATIC)
public class ShellConfig implements AppShellConfigurator, VaadinServiceInitListener {
    @Override
    public void serviceInit(ServiceInitEvent serviceInitEvent) {
        serviceInitEvent.getSource().addUIInitListener(uiInitEvent -> {
            LoadingIndicatorConfiguration indicator = uiInitEvent.getUI()
                    .getLoadingIndicatorConfiguration();
            indicator.setApplyDefaultTheme(false);

        });
    }

//    @Override
//    public void configurePage(AppShellSettings settings) {
//        settings.setViewport("width=device-width, initial-scale=1");
//        settings.setPageTitle("A cool vaadin app");
//        settings.setBodySize("100vw", "100vh");
//        settings.addMetaTag("author", "bunny");
//        settings.addFavIcon("icon", "icons/icon-192.png", "192x192");
//        settings.addLink("shortcut icon", "icons/favicon.ico");
//
//        settings.addInlineFromFile(
//                TargetElement.BODY,
//                Inline.Position.APPEND,
//                "custom.html",
//                Inline.Wrapping.AUTOMATIC);
//        settings.addInlineWithContents(Inline.Position.PREPEND,
//                "console.log(\"foo\");", Inline.Wrapping.JAVASCRIPT);
//    }

}




