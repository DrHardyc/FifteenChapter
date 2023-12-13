package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.84 */
@Getter
@Setter
@Table(schema = "regul", name = "pred_doc")
@XmlRootElement(name = "СведПредДок")
public class PredDoc {
    @Id
    private Long id;

    private String name;
    private String number;
    private String date;

    @XmlAttribute(name = "НаимДок")
    public void setName(String name) {
        this.name = name;
    }
    @XmlAttribute(name = "НомДок")
    public void setNumber(String number) {
        this.number = number;
    }
    @XmlAttribute(name = "ДатаДок")
    public void setDate(String date) {
        this.date = date;
    }
}
