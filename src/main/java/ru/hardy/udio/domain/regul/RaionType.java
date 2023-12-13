package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.103 */
@Getter
@Setter
@Table(schema = "regul", name = "raion_type")
@XmlRootElement(name = "РайонТип")
public class RaionType {
    @Id
    private Long id;

    private String type;
    private String name;

    @XmlAttribute(name = "ТипРайон")
    public void setType(String type) {
        this.type = type;
    }
    @XmlAttribute(name = "НаимРайон")
    public void setName(String name) {
        this.name = name;
    }
}
