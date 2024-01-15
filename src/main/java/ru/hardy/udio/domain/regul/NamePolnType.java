package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.110 */
@Getter
@Setter
@Table(schema = "regul", name = "name_poln_type")
@XmlRootElement
public class NamePolnType {
    @Id
    private Long id;
    private String nameLong;

    @XmlAttribute(name = "НаимПолн")
    public void setNameLong(String nameLong) {
        this.nameLong = nameLong;
    }
}
