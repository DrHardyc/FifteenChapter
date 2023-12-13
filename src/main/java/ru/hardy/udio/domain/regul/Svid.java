package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.85*/
@Getter
@Setter
@Table(schema = "regul", name = "svid")
@XmlRootElement(name = "СвСвид")
public class Svid {
    @Id
    private Long id;

    private String serial;
    private String number;
    private String dateSvid;

    @XmlAttribute(name = "Серия")
    public void setSerial(String serial) {
        this.serial = serial;
    }
    @XmlAttribute(name = "Номер")
    public void setNumber(String number) {
        this.number = number;
    }
    @XmlAttribute(name = "ДатаВыдСвид")
    public void setDateSvid(String dateSvid) {
        this.dateSvid = dateSvid;
    }
}
