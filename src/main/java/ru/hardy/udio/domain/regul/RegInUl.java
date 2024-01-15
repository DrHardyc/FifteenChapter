package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.14 */
@Getter
@Setter
@Table(schema = "regul", name = "reg_in_ul")
@XmlRootElement
public class RegInUl {
    @Id
    private Long id;

    private String inn;
    private String nameRu;
    private String nameEn;
    private String oksm;
    private String nameCountry;
    private String dateReg;
    private String regNum;
    private String codeIOStrReg;

    @XmlAttribute(name = "ИННЮЛ")
    public void setInn(String inn) {
        this.inn = inn;
    }
    @XmlAttribute(name = "НаимЮЛПолнРус")
    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }
    @XmlAttribute(name = "НаимЮЛПолнЛат")
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
    @XmlAttribute(name = "ОКСМ")
    public void setOksm(String oksm) {
        this.oksm = oksm;
    }
    @XmlAttribute(name = "НаимСтран")
    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }
    @XmlAttribute(name = "ДатаРег")
    public void setDateReg(String dateReg) {
        this.dateReg = dateReg;
    }
    @XmlAttribute(name = "РегНомер")
    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }
    @XmlAttribute(name = "КодИОСтрРег")
    public void setCodeIOStrReg(String codeIOStrReg) {
        this.codeIOStrReg = codeIOStrReg;
    }
}
