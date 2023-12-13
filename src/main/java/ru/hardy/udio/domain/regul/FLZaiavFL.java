package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.80 */
@Getter
@Setter
@Table(schema = "regul", name = "fl_zaiav_fl")
@XmlRootElement(name = "СвФЛ")
public class FLZaiavFL {
    @Id
    private Long id;
    @MappedCollection(idColumn = "flzaiavfl_id")
    private FIOINN fioinn;
    @MappedCollection(idColumn = "flzaiavfl_id")
    private RojdFL rojdFL;
    @MappedCollection(idColumn = "flzaiavfl_id")
    private UDLichFL udLichFL;

    @XmlElement(name = "СвФИОИНН")
    public void setFioinn(FIOINN fioinn) {
        this.fioinn = fioinn;
    }
    @XmlElement(name = "СвРожд")
    public void setRojdFL(RojdFL rojdFL) {
        this.rojdFL = rojdFL;
    }
    @XmlElement(name = "УдЛичнФЛ")
    public void setUdLichFL(UDLichFL udLichFL) {
        this.udLichFL = udLichFL;
    }
}
