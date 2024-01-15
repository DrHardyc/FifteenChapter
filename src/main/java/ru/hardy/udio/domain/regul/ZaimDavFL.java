package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.57 */
@Getter
@Setter
@Table(schema = "regul", name = "zaim_dav_fl")
@XmlRootElement
public class ZaimDavFL {
    @Id
    private Long id;

    @MappedCollection(idColumn = "zaimdavfl_id")
    private FLEGRULType flegrulType;
    @MappedCollection(idColumn = "zaimdavfl_id")
    private PolFLType polFLType;
    @MappedCollection(idColumn = "zaimdavfl_id")
    private RojdEGRULType rojdEGRULType;
    @MappedCollection(idColumn = "zaimdavfl_id")
    private GrajdType grajdType;
    @MappedCollection(idColumn = "zaimdavfl_id")
    private UdLichEGRULType udLichEGRULType;

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
