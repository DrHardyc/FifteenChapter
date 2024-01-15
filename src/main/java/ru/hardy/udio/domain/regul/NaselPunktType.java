package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.101 */
@Getter
@Setter
@Table(schema = "regul", name = "nasel_punkt_type")
@XmlRootElement
public class NaselPunktType {
    @Id
    private Long id;

    private String type;
    private String name;

    @XmlAttribute(name = "ТипНаселПункт")
    public void setType(String type) {
        this.type = type;
    }
    @XmlAttribute(name = "НаимНаселПункт")
    public void setName(String name) {
        this.name = name;
    }

    public NaselPunktType(){}

    public NaselPunktType(String name){
        this.name = name;
    }
}
