package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.54 */
@Getter
@Setter
@Table(schema = "regul", name = "in_pr_dog_inv_tov")
@XmlRootElement
public class InPrDogInvTov {
    @Id
    private Long id;

    private String name;
    private String number;
    private String date;

    @MappedCollection(idColumn = "inprdoginvtov_id")
    private FIOIP fioType;

    @XmlAttribute(name = "НаимДог")
    public void setName(String name) {
        this.name = name;
    }
    @XmlAttribute(name = "НомерДог")
    public void setNumber(String number) {
        this.number = number;
    }
    @XmlAttribute(name = "Дата")
    public void setDate(String date) {
        this.date = date;
    }
    @XmlElement(name = "ФИОНотариус")
    public void setFioType(FIOIP fioType) {
        this.fioType = fioType;
    }
}
