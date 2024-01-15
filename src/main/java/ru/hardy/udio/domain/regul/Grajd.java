package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "grajd")
@XmlRootElement(name = "СвГражд")
public class Grajd {
    @Id
    private Long id;

    private String vidGrajd;
    private String oksm;
    private String nameCountry;

    @XmlAttribute(name = "ВидГражд")
    public void setVidGrajd(String vidGrajd) {
        this.vidGrajd = vidGrajd;
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
