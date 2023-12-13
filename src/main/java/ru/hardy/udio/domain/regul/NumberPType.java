package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.94 */

@Getter
@Setter
@Table(schema = "regul", name = "number_p_type")
@XmlRootElement(name = "НомерПТип")
public class NumberPType {
    @Id
    private Long id;

    private String type;
    private String number;

    @XmlAttribute(name = "Тип")
    public void setType(String type) {
        this.type = type;
    }
    @XmlAttribute(name = "Номер")
    public void setNumber(String number) {
        this.number = number;
    }
}
