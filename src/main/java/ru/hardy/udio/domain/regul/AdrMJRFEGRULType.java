package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.88 */
@Getter
@Setter
@Table(schema = "regul", name = "adr_mj_rf_egrul_type")
@XmlRootElement(name = "АдрРФЕГРЮЛТип")
public class AdrMJRFEGRULType {
    @Id
    private Long id;

    private String index;
    private String code;
    private String codeCLADR;
    private String house;
    private String korpus;
    private String kvart;
    @MappedCollection(idColumn = "adrmjrfegrultype_id")
    private RegionType regionType;
    @MappedCollection(idColumn = "adrmjrfegrultype_id")
    private RaionType raionType;
    @MappedCollection(idColumn = "adrmjrfegrultype_id")
    private GorodType gorodType;
    @MappedCollection(idColumn = "adrmjrfegrultype_id")
    private NaselPunktType naselPunktType;
    @MappedCollection(idColumn = "adrmjrfegrultype_id")
    private UlicaType ulicaType;

    @XmlAttribute(name = "Индекс")
    public void setIndex(String index) {
        this.index = index;
    }
    @XmlAttribute(name = "КодРегион")
    public void setCode(String code) {
        this.code = code;
    }
    @XmlAttribute(name = "КодАдрКладр")
    public void setCodeCLADR(String codeCLADR) {
        this.codeCLADR = codeCLADR;
    }
    @XmlAttribute(name = "Дом")
    public void setHouse(String house) {
        this.house = house;
    }
    @XmlAttribute(name = "Корпус")
    public void setKorpus(String korpus) {
        this.korpus = korpus;
    }
    @XmlAttribute(name = "Кварт")
    public void setKvart(String kvart) {
        this.kvart = kvart;
    }
    @XmlElement(name = "Регион")
    public void setRegionType(RegionType regionType) {
        this.regionType = regionType;
    }
    @XmlElement(name = "Район")
    public void setRaionType(RaionType raionType) {
        this.raionType = raionType;
    }
    @XmlElement(name = "Город")
    public void setGorodType(GorodType gorodType) {
        this.gorodType = gorodType;
    }
    @XmlElement(name = "НаселПункт")
    public void setNaselPunktType(NaselPunktType naselPunktType) {
        this.naselPunktType = naselPunktType;
    }
    @XmlElement(name = "Улица")
    public void setUlicaType(UlicaType ulicaType) {
        this.ulicaType = ulicaType;
    }
}
