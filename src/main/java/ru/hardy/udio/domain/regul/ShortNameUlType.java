package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблице 4.111 */
@Getter
@Setter
@Table(schema = "regul", name = "short_name_ul_type")
@XmlRootElement
public class ShortNameUlType {
    @Id
    private Long id;
    private String nameSokr;

    @XmlAttribute(name = "НаимСокр")
    public void setNameSokr(String nameSokr) {
        this.nameSokr = nameSokr;
    }

    public ShortNameUlType(){}
    public ShortNameUlType(String shortName){
        this.nameSokr = shortName;

    }
}
