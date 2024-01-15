package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.109 */
@Getter
@Setter
@Table(schema = "regul", name = "dov_upr_gl_type")
@XmlRootElement
public class DovUprFLType {
    @Id
    private Long id;

    private String ogrnip;
    @MappedCollection(idColumn = "dovupfltype_id")
    private FLEGRULType flegrulType;
    @MappedCollection(idColumn = "dovupfltype_id")
    private PolFLType flegrulTypePol;
    @MappedCollection(idColumn = "dovupfltype_id")
    private RojdEGRULType rojdEGRULType;
    @MappedCollection(idColumn = "dovupfltype_id")
    private GrajdType grajdType;
    @MappedCollection(idColumn = "dovupfltype_id")
    private UdLichEGRULType udLichEGRULType;

    @XmlAttribute(name = "ОГРНИП")
    public void setOgrnip(String ogrnip) {
        this.ogrnip = ogrnip;
    }
    @XmlElement(name = "СвФЛ")
    public void setFlegrulType(FLEGRULType flegrulType) {
        this.flegrulType = flegrulType;
    }
    @XmlElement(name = "СвПолФЛ")
    public void setFlegrulTypePol(PolFLType flegrulTypePol) {
        this.flegrulTypePol = flegrulTypePol;
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
