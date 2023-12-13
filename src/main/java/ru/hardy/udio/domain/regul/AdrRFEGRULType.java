package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.88 */
@Getter
@Setter
@Table(schema = "regul", name = "adr_rf_egrul_type")
@XmlRootElement(name = "АдрРФЕГРЮЛТип")
public class AdrRFEGRULType {
    @Id
    private Long id;

    private String index;
    private String kodRegion;
    private String kodAdrKladr;
    private String dom;
    private String korpus;
    private String kvart;
    @MappedCollection(idColumn = "adrrfegrultype_id")
    private RegionType regionType;
    @MappedCollection(idColumn = "adrrfegrultype_id")
    private RaionType raionType;
    @MappedCollection(idColumn = "adrrfegrultype_id")
    private GorodType gorodType;
    @MappedCollection(idColumn = "adrrfegrultype_id")
    private NaselPunktType naselPunktType;
    @MappedCollection(idColumn = "adrrfegrultype_id")
    private UlicaType ulicaType;

    @XmlAttribute(name = "Индекс")
    public void setIndex(String index) {
        this.index = index;
    }
    @XmlAttribute(name = "КодРегион")
    public void setKodRegion(String kodRegion) {
        this.kodRegion = kodRegion;
    }
    @XmlAttribute(name = "КодАдрКладр")
    public void setKodAdrKladr(String kodAdrKladr) {
        this.kodAdrKladr = kodAdrKladr;
    }
    @XmlAttribute(name = "Дом")
    public void setDom(String dom) {
        this.dom = dom;
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
