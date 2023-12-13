package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "name_sokr_type")
@XmlRootElement(name = "СвНаимСокрТип")
public class NameSokrType {
    @Id
    private Long id;

    private String nameShort;

    @XmlAttribute(name = "НаимСокр")
    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }
}
