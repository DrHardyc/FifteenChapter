package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.114 */
@Getter
@Setter
@Table(schema = "regul", name = "not_ud_dog_zal")
@XmlRootElement(name = "СвНотУдДогЗалТип")
public class NotUdDogZalType {
    @Id
    private Long id;

    private String number;
    private String date;
    @MappedCollection(idColumn = "notuddogzal_id")
    private FLEGRULType notarius;

    @XmlAttribute(name = "Номер")
    public void setNumber(String number) {
        this.number = number;
    }
    @XmlAttribute(name = "Дата")
    public void setDate(String date) {
        this.date = date;
    }
    @XmlElement(name = "СвНотариус")
    public void setNotarius(FLEGRULType notarius) {
        this.notarius = notarius;
    }
}
