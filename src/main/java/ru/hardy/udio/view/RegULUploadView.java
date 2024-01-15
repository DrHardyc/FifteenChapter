package ru.hardy.udio.view;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hardy.udio.domain.regul.FileUL;
import ru.hardy.udio.service.regulservice.FileUlService;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


@Route(layout = MainView.class)
@RolesAllowed("ROLE_REGUL")
public class RegULUploadView extends VerticalLayout {

    @Autowired
    private FileUlService fileUlService;
    public RegULUploadView(){

        MultiFileMemoryBuffer multiFileMemoryBuffer = new MultiFileMemoryBuffer();
        Upload multiFileUpload = new Upload(multiFileMemoryBuffer);
        multiFileUpload.setAcceptedFileTypes(".xml");
        multiFileUpload.addSucceededListener(succeededEvent -> {
            if (fileUlService.getByIdFile(succeededEvent.getFileName().replaceAll(".xml", ""))){
                try {
                    InputStreamReader reader = new InputStreamReader(multiFileMemoryBuffer.getInputStream(succeededEvent.getFileName()), "windows-1251");
                    JAXBContext context = JAXBContext.newInstance(FileUL.class);
                    fileUlService
                            .add((FileUL) context
                                    .createUnmarshaller()
                                    .unmarshal(reader));
                } catch (JAXBException | UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            } else Notification.show("Файл с именем" + succeededEvent.getFileName() + " ранее уже был загружен");
        });
        add(multiFileUpload);
    }
}

