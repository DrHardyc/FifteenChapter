package ru.hardy.udio.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import jakarta.annotation.security.RolesAllowed;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.hardy.udio.domain.User;
import ru.hardy.udio.domain.regul.FileUL;
import ru.hardy.udio.domain.regul.LoadSettings;
import ru.hardy.udio.report.Reports;
import ru.hardy.udio.service.UserService;
import ru.hardy.udio.service.regulservice.FileUlService;
import ru.hardy.udio.service.regulservice.LoadSettingsService;
import ru.hardy.udio.view.dialog.DialogGen;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Route(layout = MainView.class)
@RolesAllowed("ROLE_REGUL")
public class RegULUploadView extends VerticalLayout {

    @Autowired
    private FileUlService fileUlService;

    @Autowired
    private UserService userService;

    @Autowired
    private LoadSettingsService loadSettingsService;

    private final TextField tfPathOut = new TextField("Куда сохраняем архив исполненных файлов");
    private final TextField tfPathIn = new TextField("Откуда берем файлы");

    private LoadSettings loadSettings = null;
    private User user = null;
    public RegULUploadView() {
        Button btnAdd = new Button(new Icon(VaadinIcon.PLUS));
        btnAdd.addClickListener(event -> {
            new DialogGen("50", "50", "Добавление новой записи")
                    .getAddNewRegUL(fileUlService, user).open();
        });
        tfPathIn.setWidth("30vw");
        tfPathOut.setWidth("30vw");
        Button btnSave = new Button("Ок");
        Dialog dialog = new DialogGen("40", "35", "Настройка каталогов")
                .getLoadSettingDialog(tfPathIn, tfPathOut, btnSave);
        btnSave.addClickListener(event -> {
            if (loadSettings != null){
                loadSettings.setPathOut(tfPathOut.getValue());
                loadSettings.setPathIn(tfPathIn.getValue());
                loadSettingsService.update(loadSettings, user.getId());
                dialog.close();
            } else {
                if (tfPathOut.getValue().isEmpty() || tfPathIn.getValue().isEmpty()){
                    Notification.show("Пути загрузки/сохранения файлов должны быть заполнены");
                } else {
                    LoadSettings loadSettingsNew = new LoadSettings();
                    loadSettingsNew.setPathIn(tfPathIn.getValue());
                    loadSettingsNew.setPathOut(tfPathOut.getValue());
                    loadSettingsNew.setUserId(user.getId());
                    loadSettingsService.add(loadSettingsNew);
                    dialog.close();
                }
            }
        });

        Button btnSettings = savePaths(dialog);
        Upload multiFileUpload = uploadFiles();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(btnAdd, btnSettings);
        add(horizontalLayout, multiFileUpload);
    }

    private Button savePaths(Dialog dialog) {
        Button btnSettings = new Button(new Icon(VaadinIcon.COG_O));
        btnSettings.addClickListener(event -> {
            dialog.addAttachListener(attachEvent -> {
                if (loadSettings != null){
                    tfPathOut.setValue(loadSettings.getPathOut());
                    tfPathIn.setValue(loadSettings.getPathIn());
                }
            });
            dialog.open();
        });
        return btnSettings;
    }

    private Upload uploadFiles() {
        MultiFileMemoryBuffer multiFileMemoryBuffer = new MultiFileMemoryBuffer();
        Upload multiFileUpload = new Upload(multiFileMemoryBuffer);

        multiFileUpload.setAcceptedFileTypes(".xml");
        multiFileUpload.addSucceededListener(succeededEvent -> {
            if (loadSettings == null){
                Notification.show("Настроены пути ахвации!");
            } else {
                if (fileUlService.getByIdFile(succeededEvent.getFileName().replaceAll(".xml", ""))) {
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
            }
        });

        multiFileUpload.addStartedListener(startedEvent -> {
            Notification.show("Нанинаем обработку...");
        });
        multiFileUpload.addAllFinishedListener(allFinishedEvent -> {
            try {
                FileOutputStream fos = new FileOutputStream(loadSettings.getPathOut() +
                        "\\success_" + new SimpleDateFormat("dd_MM_yy__HH_mm_ss").format(Date.from(Instant.now())) + ".zip");
                ZipOutputStream zipOut = new ZipOutputStream(fos);
                for (String srcFile : multiFileMemoryBuffer.getFiles()) {
                    File fileToZip = new File(loadSettings.getPathIn() + "\\" + srcFile);
                    FileInputStream fis = new FileInputStream(fileToZip);
                    ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                    zipOut.putNextEntry(zipEntry);

                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fis.read(bytes)) >= 0) {
                        zipOut.write(bytes, 0, length);
                    }
                    fis.close();
                    if (!new File(loadSettings.getPathIn() + "\\" + srcFile).delete()) {
                        System.out.println(loadSettings.getPathIn() + "\\" + srcFile + " no delete");
                    }
                }
                zipOut.close();
                fos.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Notification.show("Обработка завершена. Загружено " + multiFileMemoryBuffer.getFiles().size() + ".");
            multiFileMemoryBuffer.getFiles().clear();
        });
        return multiFileUpload;
    }

    @Override
    public void onAttach(AttachEvent attachEvent) {
        user = userService.getWithName(SecurityContextHolder.getContext().getAuthentication().getName());
        loadSettings = loadSettingsService.getWithUser(userService
                .getWithName(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()));
    }
}

