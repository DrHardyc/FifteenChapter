package ru.hardy.udio.domain.button;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UdioButton extends Button {

    public UdioButton(String caption, BtnVariant btnVariant){
        switch (btnVariant){
            case OK -> {}
            case SEARCH -> {
                this.setText(caption);
                this.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            }
            case CANCEL -> {}
            case XLS -> {
                StreamResource src = new StreamResource("icons8-excel-20.png",
                        () -> getClass()
                                .getResourceAsStream("/images/icons8-excel-20.png"));
                Image img = new Image(src, "Vaadin logo");
                this.setIcon(img);
                this.setText(caption);
                //this.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
            }
        }

    }


}
