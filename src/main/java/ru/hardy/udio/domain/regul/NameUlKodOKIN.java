package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблице 4.6 */
@Getter
@Setter
@Table(schema = "regul", name = "name_ul_kod_okin")
@XmlRootElement(name = "СвНаимЮЛКодОКИН")
public class NameUlKodOKIN {
    @Id
    private Long id;

    private String kodOkin;
    private String nameOkin;

    @XmlAttribute(name = "КодОКИН")
    public void setKodOkin(String kodOkin) {
        this.kodOkin = kodOkin;
    }
    @XmlAttribute(name = "НаимОКИН")
    public void setNameOkin(String nameOkin) {
        this.nameOkin = nameOkin;
    }
}
