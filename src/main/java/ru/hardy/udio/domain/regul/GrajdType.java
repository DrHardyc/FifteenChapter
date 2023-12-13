package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.107 */
@Getter
@Setter
@Table(schema = "regul", name = "grajd_type")
@XmlRootElement(name = "СвГраждТип")
public class GrajdType {
    @Id
    private Long id;

    private String code;
    private String oksm;
    private String nameCountry;

    @XmlAttribute(name = "КодГражд")
    public void setCode(String code) {
        this.code = code;
    }
    @XmlAttribute(name = "ОКСМ")
    public void setOksm(String oksm) {
        this.oksm = oksm;
    }
    @XmlAttribute(name = "НаимСтран")
    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }
}
