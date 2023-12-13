package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.93 */
@Getter
@Setter
@Table(schema = "regul", name = "type_name_p_type")
@XmlRootElement(name = "ТипНаимПТип")
public class TypeNamePType {
    @Id
    private Long id;

    private String typeElement;
    private String nameElement;

    @XmlAttribute(name = "Тип")
    public void setTypeElement(String typeElement) {
        this.typeElement = typeElement;
    }
    @XmlAttribute(name = "Наим")
    public void setNameElement(String nameElement) {
        this.nameElement = nameElement;
    }
}
