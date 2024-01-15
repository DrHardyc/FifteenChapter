package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.124 */
@Getter
@Setter
@Table(schema = "regul", name = "reg_in_ul_egrul_type")
@XmlRootElement
public class RegInULEGRULType {
    @Id
    private Long id;

    private String oksm;
    private String nameCountry;
    private String date;
    private String number;
    private String name;
    private String codeNPSStrReg;
    private String adrCountry;

    @XmlAttribute(name = "ОКСМ")
    public void setOksm(String oksm) {
        this.oksm = oksm;
    }
    @XmlAttribute(name = "НаимСтран")
    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }
    @XmlAttribute(name = "ДатаРег")
    public void setDate(String date) {
        this.date = date;
    }
    @XmlAttribute(name = "РегНомер")
    public void setNumber(String number) {
        this.number = number;
    }
    @XmlAttribute(name = "НаимРегОрг")
    public void setName(String name) {
        this.name = name;
    }
    @XmlAttribute(name = "КодНПСтрРег")
    public void setCodeNPSStrReg(String codeNPSStrReg) {
        this.codeNPSStrReg = codeNPSStrReg;
    }
    @XmlAttribute(name = "АдрСтр")
    public void setAdrCountry(String adrCountry) {
        this.adrCountry = adrCountry;
    }
}
