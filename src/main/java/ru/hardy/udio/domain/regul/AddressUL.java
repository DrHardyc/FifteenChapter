package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

/** @version  таблица 4.7 */
@Getter
@Setter
@Table(schema = "regul", name = "address_ul")
@XmlRootElement(name = "СвАдресЮЛ")
public class AddressUL {
    @Id
    private Long id;

    private String vidAdrKlassif;
    @MappedCollection(idColumn = "addressul_id")
    private MNUL mnul;
    @MappedCollection(idColumn = "addressul_id")
    private AdrFIASEGRULType adrFIASEGRULType;
    @MappedCollection(idColumn = "addressul_id")
    private AdrRFEGRULType adrRFEGRULType;
    @MappedCollection(idColumn = "addressul_id")
    private Set<NedAdrUl> nedAdrUl;
    @MappedCollection(idColumn = "addressul_id")
    private ReshIzmMN reshIzmMN;

    @XmlAttribute(name = "ВидАдрКлассиф")
    public void setVidAdrKlassif(String vidAdrKlassif) {
        this.vidAdrKlassif = vidAdrKlassif;
    }

    @XmlElement(name = "СвМНЮЛ")
    public void setMnul(MNUL mnul) {
        this.mnul = mnul;
    }

    @XmlElement(name = "СвАдрЮЛФИАС")
    public void setAdrFIASEGRULType(AdrFIASEGRULType adrFIASEGRULType) {
        this.adrFIASEGRULType = adrFIASEGRULType;
    }
    @XmlElement(name = "АдресРФ")
    public void setAdrRFEGRULType(AdrRFEGRULType adrRFEGRULType) {
        this.adrRFEGRULType = adrRFEGRULType;
    }
    @XmlElement(name = "СвНедАдресЮЛ")
    public void setNedAdrUl(Set<NedAdrUl> nedAdrUl) {
        this.nedAdrUl = nedAdrUl;
    }
    @XmlElement(name = "СвРешИзмМН")
    public void setReshIzmMN(ReshIzmMN reshIzmMN) {
        this.reshIzmMN = reshIzmMN;
    }
}
