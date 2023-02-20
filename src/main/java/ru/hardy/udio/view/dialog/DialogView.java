package ru.hardy.udio.view.dialog;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.service.TokenService;

import javax.swing.*;

import java.util.UUID;

import static javax.swing.text.StyleConstants.Alignment;

@Service
public class DialogView extends Dialog {

    public Dialog getKeyGenDialog(TokenService tokenService){
        Button btnKeyGen = new Button("Создать");

        TextField tfKeyGen = new TextField();
        tfKeyGen.setWidthFull();

        TextField tfLpu = new TextField();
        tfLpu.setWidthFull();
        tfKeyGen.setReadOnly(true);

        Dialog dialog = new Dialog();

        VerticalLayout vlKeyGen = new VerticalLayout();
        vlKeyGen.setHorizontalComponentAlignment(FlexComponent.Alignment.END, btnKeyGen);
        vlKeyGen.setWidth("500px");

        btnKeyGen.addClickListener(e -> {
            if(tfLpu.getValue().isEmpty()) Notification.show("Введите код LPU");
            else {
                tfKeyGen.setValue(tokenService.getHashWithKey(tokenService.genToken(tfLpu.getValue()).getKey()));
            }
        });

        vlKeyGen.add(tfLpu, tfKeyGen, btnKeyGen);
        dialog.add(vlKeyGen);

        return dialog;
    }
}
