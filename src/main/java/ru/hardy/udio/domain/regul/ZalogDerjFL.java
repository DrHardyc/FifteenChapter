package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.117 */
@Getter
@Setter
@Table(schema = "regul", name = "zalog_derj_fl")
@XmlRootElement
public class ZalogDerjFL {
    @Id
    private Long id;

    @MappedCollection(idColumn = "zalogderjfl_id")
    private FLEGRULType flegrulType;
    @MappedCollection(idColumn = "zalogderjfl_id")
    private PolFLType polFLType;
    @MappedCollection(idColumn = "zalogderjfl_id")
    private RojdEGRULType rojdEGRULType;
    @MappedCollection(idColumn = "zalogderjfl_id")
    private GrajdType grajdType;
    @MappedCollection(idColumn = "zalogderjfl_id")
    private UdLichEGRULType udLichEGRULType;
    @MappedCollection(idColumn = "zalogderjfl_id")
    private NotUdDogZalType notUdDogZalType;

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
    @XmlElement(name = "СвНотУдДогЗал")
    public void setNotUdDogZalType(NotUdDogZalType notUdDogZalType) {
        this.notUdDogZalType = notUdDogZalType;
    }
}
