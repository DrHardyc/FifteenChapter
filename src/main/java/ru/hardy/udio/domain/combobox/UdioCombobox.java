package ru.hardy.udio.domain.combobox;

import com.vaadin.flow.component.combobox.ComboBox;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UdioCombobox<T> extends ComboBox<T> {

    public UdioCombobox(){
        this.setRequiredIndicatorVisible(true);
    }


}
