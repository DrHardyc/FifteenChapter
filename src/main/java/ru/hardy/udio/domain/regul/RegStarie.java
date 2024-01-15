package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.42 */
@Getter
@Setter
@Table(schema = "regul", name = "reg_starie")
@XmlRootElement
public class RegStarie {
    @Id
    private Long id;

    private String regNom;
    private String date;
    private String name;

    @XmlAttribute(name = "РегНом")
    public void setRegNom(String regNom) {
        this.regNom = regNom;
    }
    @XmlAttribute(name = "ДатаРег")
    public void setDate(String date) {
        this.date = date;
    }
    @XmlAttribute(name = "НаимРО")
    public void setName(String name) {
        this.name = name;
    }
}
