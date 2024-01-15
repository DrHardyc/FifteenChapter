package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.106 */
@Getter
@Setter
@Table(schema = "regul", name = "ak_rafp_type")
@XmlRootElement
public class AkRAFPType {
    @Id
    private Long id;

    private String number;
    @XmlAttribute(name = "НомерРАФП")
    public void setNumber(String number) {
        this.number = number;
    }
}
