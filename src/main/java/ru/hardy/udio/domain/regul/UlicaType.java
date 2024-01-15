package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.137 */

@Getter
@Setter
@Table(schema = "regul", name = "ulica_type")
@XmlRootElement
public class UlicaType {
    @Id
    private Long id;

    private String type;
    private String name;

    @XmlAttribute(name = "ТипУлица")
    public void setType(String type) {
        this.type = type;
    }
    @XmlAttribute(name = "НаимУлица")
    public void setName(String name) {
        this.name = name;
    }

    public UlicaType(){}

    public UlicaType(String name){
        this.name = name;
    }
}
