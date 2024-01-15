package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.87 */
@Getter
@Setter
@Table(schema = "regul", name = "adr_in_egrul_type")
@XmlRootElement
public class AdrInEGRULType {
    @Id
    private Long id;

    private String oksm;
    private String nameCountry;
    private String adrIn;

    @XmlAttribute(name = "ОКСМ")
    public void setOksm(String oksm) {
        this.oksm = oksm;
    }
    @XmlAttribute(name = "НаимСтран")
    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }
    @XmlAttribute(name = "АдрИн")
    public void setAdrIn(String adrIn) {
        this.adrIn = adrIn;
    }
}
