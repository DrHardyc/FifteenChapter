package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.113 */
@Getter
@Setter
@Table(schema = "regul", name = "no_type")
@XmlRootElement
public class NOType {
    @Id
    private Long id;

    private String code;
    private String name;

    @XmlAttribute(name = "КодНО")
    public void setCode(String code) {
        this.code = code;
    }
    @XmlAttribute(name = "НаимНО")
    public void setName(String name) {
        this.name = name;
    }
}
