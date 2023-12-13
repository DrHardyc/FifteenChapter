package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.12 */
@Getter
@Setter
@Table(schema = "regul", name = "obr_ul")
@XmlRootElement(name = "СвОбрЮЛ")
public class ObrUL {
    @Id
    private Long id;

    private String statusMKF;
    private String ogrn;
    private String dateOgrn;
    private String regNom;
    private String dateReg;
    private String nameRO;
    private String KodUl;
    @MappedCollection(idColumn = "obrul_id")
    private SpObrUl obrUl;
    @MappedCollection(idColumn = "obrul_id")
    private RegInUl regInUl;

    @XmlAttribute(name = "СтатусМКФ")
    public void setStatusMKF(String statusMKF) {
        this.statusMKF = statusMKF;
    }
    @XmlAttribute(name = "ОГРН")
    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }
    @XmlAttribute(name = "ДатаОГРН")
    public void setDateOgrn(String dateOgrn) {
        this.dateOgrn = dateOgrn;
    }
    @XmlAttribute(name = "РегНом")
    public void setRegNom(String regNom) {
        this.regNom = regNom;
    }
    @XmlAttribute(name = "ДатаРег")
    public void setDateReg(String dateReg) {
        this.dateReg = dateReg;
    }
    @XmlAttribute(name = "НаимРО")
    public void setNameRO(String nameRO) {
        this.nameRO = nameRO;
    }
    @XmlAttribute(name = "ИдКодЮЛ")
    public void setKodUl(String kodUl) {
        KodUl = kodUl;
    }
    @XmlElement(name = "СпОбрЮЛ")
    public void setObrUl(SpObrUl obrUl) {
        this.obrUl = obrUl;
    }
    @XmlElement(name = "СвРегИнЮЛ")
    public void setRegInUl(RegInUl regInUl) {
        this.regInUl = regInUl;
    }
}
