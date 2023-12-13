package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.105 */
@Getter
@Setter
@Table(schema = "regul", name = "resh_sud_type")
@XmlRootElement(name = "РешСудТип")
public class ReshSudType {
    @Id
    private Long id;

    private String name;
    private String number;
    private String date;

    @XmlAttribute(name = "НаимСуда")
    public void setName(String name) {
        this.name = name;
    }
    @XmlAttribute(name = "Номер")
    public void setNumber(String number) {
        this.number = number;
    }
    @XmlAttribute(name = "Дата")
    public void setDate(String date) {
        this.date = date;
    }
}
