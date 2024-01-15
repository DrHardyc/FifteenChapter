package ru.hardy.udio.view.regul;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hardy.udio.domain.regul.FileUL;
import ru.hardy.udio.domain.regul.SenderPeople;
import ru.hardy.udio.service.regulservice.FileUlService;
import ru.hardy.udio.view.MainView;

@Route(layout = MainView.class)
@PermitAll
public class MainRView extends VerticalLayout {

    @Autowired
    private FileUlService fileUlService;

    public MainRView(){



    }

    @Override
    public void onAttach(AttachEvent attachEvent) {
        FileUL fileUL = new FileUL();
        //fileUL.setId_file("sdfsdfgsdfg");
        fileUL.setFormatVersion("4.07");
       // fileUL.setInfoType(InfoType.ЕГРЮЛ_ЗАКР_СВЕД);
        fileUL.setQuantityDoc("2");
        fileUL.setTransmissionProgramVersion("1.0.0.1");

        //fileUL.setInfoType(InfoType.ЕГРЮЛ_ОГР_СВЕД);

        SenderPeople senderPeople = new SenderPeople();
        senderPeople.setEmail("asdfasdf@adsfasdf.ry");
        senderPeople.setPhone("92844556644");
        senderPeople.setPosition("hz");
        //fileUL.setSenderPeople(senderPeople);
        Button button = new Button("Сохранить");
        button.addClickListener(ev -> {
            fileUlService.add(fileUL);
        });
        add(button);
    }
}
