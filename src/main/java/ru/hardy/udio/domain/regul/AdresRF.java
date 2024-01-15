package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "adr_rf_type_vip")
@XmlRootElement(name = "АдрРФТипВып")
public class AdresRF {
    @Id
    private Long id;

    private String index;
    private String codeRegion;
    private String codeAdrKladr;
    private String dom;
    private String korp;
    private String kvart;
    @MappedCollection(idColumn = "adrrftypevip_id")
    private RegionType regionType;
    @MappedCollection(idColumn = "adrrftypevip_id")
    private RaionType raionType;
    @MappedCollection(idColumn = "adrrftypevip_id")
    private GorodType gorodType;
    @MappedCollection(idColumn = "adrrftypevip_id")
    private NaselPunktType naselPunktType;
    @MappedCollection(idColumn = "adrrftypevip_id")
    private UlicaType ulicaType;

    @XmlAttribute(name = "Индекс")
    public void setIndex(String index) {
        this.index = index;
    }
    @XmlAttribute(name = "КодРегион")
    public void setCodeRegion(String codeRegion) {
        this.codeRegion = codeRegion;
    }
    @XmlAttribute(name = "КодАдрКладр")
    public void setCodeAdrKladr(String codeAdrKladr) {
        this.codeAdrKladr = codeAdrKladr;
    }
    @XmlAttribute(name = "Дом")
    public void setDom(String dom) {
        this.dom = dom;
    }
    @XmlAttribute(name = "Корпус")
    public void setKorp(String korp) {
        this.korp = korp;
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

    public AdresRF() {
    }

    public AdresRF(String index, RegionType regionType, RaionType raionType, GorodType gorodType, NaselPunktType naselPunktType,
                   UlicaType ulicaType, String dom, String korp, String kvart) {
        this.index = index;
        this.regionType = regionType;
        this.raionType = raionType;
        this.gorodType = gorodType;
        this.naselPunktType = naselPunktType;
        this.ulicaType = ulicaType;
        this.dom = dom;
        this.korp = korp;
        this.kvart = kvart;
    }
}
