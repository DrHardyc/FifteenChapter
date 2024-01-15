package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

/** @version  таблица 4.15 */
@Getter
@Setter
@Table(schema = "regul", name = "reg_org")
@XmlRootElement
public class RegOrg {
    @Id
    private Long id;

    private String kodNO;
    private String nameNO;
    private String adrRO;

    @XmlAttribute(name = "КодНО")
    public void setKodNO(String kodNO) {
        this.kodNO = kodNO;
    }
    @XmlAttribute(name = "НаимНО")
    public void setNameNO(String nameNO) {
        this.nameNO = nameNO;
    }
    @XmlAttribute(name = "АдрРО")
    public void setAdrRO(String adrRO) {
        this.adrRO = adrRO;
    }

    public RegOrg(){}

    public RegOrg(String nameNO){
        this.nameNO = nameNO;
    }
}
