package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.45 */
@Getter
@Setter
@Table(schema = "regul", name = "lico_upr_nasl")
@XmlRootElement(name = "ЛицоУпрНасл")
public class LicoUprNasl {
    @Id
    private Long id;

    @Column("ogrn_ip")
    private String ogrnip;
    private String dateOtkrNasl;
    @MappedCollection(idColumn = "licouprnasl_id")
    private FLEGRULType flegrulType;
    @MappedCollection(idColumn = "licouprnasl_id")
    private PolFLType polFLType;
    @MappedCollection(idColumn = "licouprnasl_id")
    private RojdEGRULType rojdEGRULType;
    @MappedCollection(idColumn = "licouprnasl_id")
    private GrajdType grajdType;
    @MappedCollection(idColumn = "licouprnasl_id")
    private UdLichEGRULType udLichEGRULType;

    @XmlAttribute(name = "ОГРНИП")
    public void setOgrnip(String ogrnip) {
        this.ogrnip = ogrnip;
    }
    @XmlAttribute(name = "ДатаОткрНасл")
    public void setDateOtkrNasl(String dateOtkrNasl) {
        this.dateOtkrNasl = dateOtkrNasl;
    }
    @XmlElement(name = "СвФЛ")
    public void setFlegrulType(FLEGRULType flegrulType) {
        this.flegrulType = flegrulType;
    }
    @XmlElement(name = "СвПолФЛ")
    public void setPolFLType(PolFLType polFLType) {
        this.polFLType = polFLType;
    }
    @XmlElement(name = "СвРождФЛ")
    public void setRojdEGRULType(RojdEGRULType rojdEGRULType) {
        this.rojdEGRULType = rojdEGRULType;
    }
    @XmlElement(name = "СвГраждФЛ")
    public void setGrajdType(GrajdType grajdType) {
        this.grajdType = grajdType;
    }
    @XmlElement(name = "УдЛичнФЛ")
    public void setUdLichEGRULType(UdLichEGRULType udLichEGRULType) {
        this.udLichEGRULType = udLichEGRULType;
    }
}
