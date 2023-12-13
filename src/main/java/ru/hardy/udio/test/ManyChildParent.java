package ru.hardy.udio.test;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

//@Getter
@Setter
@Table(schema = "udio_datacontrol", name = "test")

@XmlRootElement(name = "Файл")
public class ManyChildParent {
    @Id
    private Long id;


    private String name;

    private String date;

    private String number;
    @XmlTransient
    private OneParent parent;

    @XmlAttribute(name = "ИдФайл")
    public String getName() {
        return name;
    }

    @XmlAttribute(name = "ВерсФорм")
    public String getDate() {
        return date;
    }

    @XmlAttribute(name = "ТипИнф")
    public String getNumber() {
        return number;
    }
//    @XmlElement(name = "ИдОтпр")
//    public OneParent getParent() {
//        return parent;
//    }
}
