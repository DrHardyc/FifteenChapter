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
@Table(schema = "regul", name = "adr_mj")
@XmlRootElement
public class AdrMJ {
    @Id
    private Long id;
    private String vidAdrKlassif;
    @MappedCollection(idColumn = "adrmj_id")
    private AdresRF adresRF;
    @MappedCollection(idColumn = "adrmj_id")
    private AdrFIASEGRIPType adrFIASEGRIPType;

    @XmlAttribute(name = "ВидАдрКлассиф")
    public void setVidAdrKlassif(String vidAdrKlassif) {
        this.vidAdrKlassif = vidAdrKlassif;
    }
    @XmlElement(name = "АдресРФ")
    public void setAdresRF(AdresRF adresRF) {
        this.adresRF = adresRF;
    }
    @XmlElement(name = "АдрМЖФИАС")
    public void setAdrFIASEGRIPType(AdrFIASEGRIPType adrFIASEGRIPType) {
        this.adrFIASEGRIPType = adrFIASEGRIPType;
    }

    public AdrMJ() {
    }

    public AdrMJ(AdresRF adresRF) {
        this.adresRF = adresRF;
    }
}
