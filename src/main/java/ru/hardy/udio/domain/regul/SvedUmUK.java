package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.28 */
@Getter
@Setter
@Table(schema = "regul", name = "sved_umuk")
@XmlRootElement
public class SvedUmUK {
    @Id
    private Long id;

    private String velUmUK;
    private String dateResh;

    @XmlAttribute(name = "ВелУмУК")
    public void setVelUmUK(String velUmUK) {
        this.velUmUK = velUmUK;
    }
    @XmlAttribute(name = "ДатаРеш")
    public void setDateResh(String dateResh) {
        this.dateResh = dateResh;
    }
}
