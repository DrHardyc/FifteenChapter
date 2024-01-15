package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.4 */
@Getter
@Setter
@Table(schema = "regul", name = "fl")
@XmlRootElement
public class FL {
    @Id
    private Long id;
    private String sex;
    @MappedCollection(idColumn = "fl_id")
    private FIOIP fioRus;
    @MappedCollection(idColumn = "fl_id")
    private FIOIP fioLat;

    @XmlAttribute(name = "Пол")
    public void setSex(String sex) {
        this.sex = sex;
    }
    @XmlElement(name = "ФИОРус")
    public void setFioRus(FIOIP fioRus) {
        this.fioRus = fioRus;
    }
    @XmlElement(name = "ФИОЛат")
    public void setFioLat(FIOIP fioLat) {
        this.fioLat = fioLat;
    }
}
