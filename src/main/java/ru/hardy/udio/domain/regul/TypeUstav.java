package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.21 */
@Getter
@Setter
@Table(schema = "regul", name = "type_ustav")
@XmlRootElement(name = "СвТипУстав")
public class TypeUstav {
    @Id
    private Long id;
    private String nomTypeUstav;

    @XmlAttribute(name = "НомТипУстав")
    public void setNomTypeUstav(String nomTypeUstav) {
        this.nomTypeUstav = nomTypeUstav;
    }
}
