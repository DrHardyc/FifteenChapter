package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.37 */
@Getter
@Setter
@Table(schema = "regul", name = "diskv")
@XmlRootElement(name = "СвДискв")
public class Diskv {
    @Id
    private Long id;

    private String dateNachDiskv;
    private String dateOkonchDiskv;
    private String dateResh;

    @XmlAttribute(name = "ДатаНачДискв")
    public void setDateNachDiskv(String dateNachDiskv) {
        this.dateNachDiskv = dateNachDiskv;
    }
    @XmlAttribute(name = "ДатаОкончДискв")
    public void setDateOkonchDiskv(String dateOkonchDiskv) {
        this.dateOkonchDiskv = dateOkonchDiskv;
    }
    @XmlAttribute(name = "ДатаРеш")
    public void setDateResh(String dateResh) {
        this.dateResh = dateResh;
    }
}
