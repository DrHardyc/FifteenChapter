package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.27 */
@Getter
@Setter
@Table(schema = "regul", name = "ust_kap")
@XmlRootElement
public class UstKap {
    @Id
    private Long id;

    private String nameVidKap;
    private String sumKap;
    @MappedCollection(idColumn = "ustkap_id")
    private DrobType drobType;
    @MappedCollection(idColumn = "ustkap_id")
    private SvedUmUK svedUmUK;

    @XmlAttribute(name = "НаимВидКап")
    public void setNameVidKap(String nameVidKap) {
        this.nameVidKap = nameVidKap;
    }
    @XmlAttribute(name = "СумКап")
    public void setSumKap(String sumKap) {
        this.sumKap = sumKap;
    }
    @XmlElement(name = "ДоляРубля")
    public void setDrobType(DrobType drobType) {
        this.drobType = drobType;
    }
    @XmlElement(name = "СведУмУК")
    public void setSvedUmUK(SvedUmUK svedUmUK) {
        this.svedUmUK = svedUmUK;
    }
}
