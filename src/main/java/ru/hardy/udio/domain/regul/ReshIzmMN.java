package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.10 */
@Getter
@Setter
@Table(schema = "regul", name = "resh_izm_mn")
@XmlRootElement(name = "СвРешИзмМН")
public class ReshIzmMN {
    @Id
    private Long id;

    private String textReshIzmMN;
    @MappedCollection(idColumn = "reshizmmn_id")
    private RegionType regionType;
    @MappedCollection(idColumn = "reshizmmn_id")
    private RaionType raionType;
    @MappedCollection(idColumn = "reshizmmn_id")
    private GorodType gorodType;
    @MappedCollection(idColumn = "reshizmmn_id")
    private NaselPunktType naselPunktType;

    @XmlAttribute(name = "ТекстРешИзмМН")
    public void setTextReshIzmMN(String textReshIzmMN) {
        this.textReshIzmMN = textReshIzmMN;
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
}
