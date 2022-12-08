package ru.hardy.udio.service;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.server.StreamResource;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class UIUtil {
    public Button InitButtonOK(Button button){
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        return button;
    }

    public Anchor initAnchorDownload(File currDir){
        StreamResource streamResource = new StreamResource(currDir.getName(), () -> getStream(currDir));
        Anchor anchor = new Anchor(streamResource,
                String.format("%s (%d KB)", currDir.getName(), (int) currDir.length() / 1024));
        anchor.getElement().setAttribute("download", true);
        anchor.getStyle().set("margin", "7% auto");
        return anchor;
    }

    private InputStream getStream(File file) {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return stream;
    }
}
