package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.135 */
@Getter
@Setter
@Table(schema = "regul", name = "akr_fil_pred_type")
@XmlRootElement
public class AkrFilPredType {
    @Id
    private Long id;

    private String inn;
    private String kpp;
    private String numberRAFP;
    private String dateAkrRAFP;
    private String namePredUL;
    private String nameLong;
    private String oksm;
    private String name;
    private String number;
    private String codeIOStrReg;

    @XmlAttribute(name = "ИНН")
    public void setInn(String inn) {
        this.inn = inn;
    }
    @XmlAttribute(name = "КПП")
    public void setKpp(String kpp) {
        this.kpp = kpp;
    }
    @XmlAttribute(name = "НомерРАФП")
    public void setNumberRAFP(String numberRAFP) {
        this.numberRAFP = numberRAFP;
    }
    @XmlAttribute(name = "ДатаАкрРАФП")
    public void setDateAkrRAFP(String dateAkrRAFP) {
        this.dateAkrRAFP = dateAkrRAFP;
    }
    @XmlAttribute(name = "НаимПредЮЛ")
    public void setNamePredUL(String namePredUL) {
        this.namePredUL = namePredUL;
    }
    @XmlAttribute(name = "НаимЮЛПолн")
    public void setNameLong(String nameLong) {
        this.nameLong = nameLong;
    }
    @XmlAttribute(name = "ОКСМ")
    public void setOksm(String oksm) {
        this.oksm = oksm;
    }
    @XmlAttribute(name = "НаимСтран")
    public void setName(String name) {
        this.name = name;
    }
    @XmlAttribute(name = "РегНомер")
    public void setNumber(String number) {
        this.number = number;
    }
    @XmlAttribute(name = "КодИОСтрРег")
    public void setCodeIOStrReg(String codeIOStrReg) {
        this.codeIOStrReg = codeIOStrReg;
    }
}
